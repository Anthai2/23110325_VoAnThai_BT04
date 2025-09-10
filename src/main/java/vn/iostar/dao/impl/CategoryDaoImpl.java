package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.CategoryDao;
import vn.iostar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public Category findById(int cateId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, cateId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c ORDER BY c.cate_id DESC";
            TypedQuery<Category> q = em.createQuery(jpql, Category.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findAll(int page, int pageSize) { // page: 0-based
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c ORDER BY c.cate_id DESC";
            TypedQuery<Category> q = em.createQuery(jpql, Category.class);
            q.setFirstResult(page * pageSize);
            q.setMaxResults(pageSize);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findByCategoryName(String catname) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.cate_name LIKE :kw ORDER BY c.cate_id DESC";
            TypedQuery<Category> q = em.createQuery(jpql, Category.class);
            q.setParameter("kw", "%" + catname + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.id = :uid ORDER BY c.cate_id DESC";
            TypedQuery<Category> q = em.createQuery(jpql, Category.class);
            q.setParameter("uid", userId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Category create(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);
            tx.commit();
            return category;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Category update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Category merged = em.merge(category);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int cateId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Category c = em.find(Category.class, cateId);
            if (c != null) em.remove(c);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
