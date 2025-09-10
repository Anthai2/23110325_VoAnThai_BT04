package vn.iostar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.UserDao;
import vn.iostar.entity.Users;

public class UserDaoImpl implements UserDao {

    @Override
    public Users get(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            var q = em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class);
            q.setParameter("username", username);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Users user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            var q = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
            q.setParameter("email", email);
            return q.getResultStream().findFirst().orElse(null) != null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            var q = em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class);
            q.setParameter("username", username);
            return q.getResultStream().findFirst().orElse(null) != null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            var q = em.createQuery("SELECT u FROM Users u WHERE u.phone = :phone", Users.class);
            q.setParameter("phone", phone);
            return q.getResultStream().findFirst().orElse(null) != null;
        } finally {
            em.close();
        }
    }

    @Override
    public Users getUserByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            var q = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
            q.setParameter("email", email);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            int updated = em.createQuery(
                            "UPDATE Users u SET u.password = :pwd WHERE u.email = :email")
                    .setParameter("pwd", newPassword)
                    .setParameter("email", email)
                    .executeUpdate();

            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}