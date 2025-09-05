package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl implements IUserDao {

    private UserModel mapRow(ResultSet rs) throws Exception {
        UserModel u = new UserModel();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setImages(rs.getString("images"));
        u.setFullname(rs.getString("fullname"));
        u.setEmail(rs.getString("email"));
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        u.setCreatedDate(rs.getDate("createDate")); 
        return u;
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<>();
        try (Connection conn = (Connection) new JPAConfig();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; 
    }

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users " +
                     " (email, username, fullname, password, avatar, roleid, phone, createDate) " +
                     " VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = (Connection) new JPAConfig();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreatedDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = (Connection) new JPAConfig();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn =(Connection) new JPAConfig();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        //System.out.println(userDao.findById(1));
        for (UserModel u : userDao.findAll()) {
            System.out.println(u);
        }
    }
}
