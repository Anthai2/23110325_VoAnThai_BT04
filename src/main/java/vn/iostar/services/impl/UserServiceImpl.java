package vn.iostar.services.impl;

import vn.iostar.dao.UserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.entity.Users;
import vn.iostar.services.IUserService;

public class UserServiceImpl implements IUserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public Users login(String username, String password) {
        Users user = userDao.get(username);
        if (user == null) return null;
        // TODO: thay bằng check hash (BCrypt) nếu có
        return password != null && password.equals(user.getPassword()) ? user : null;
    }

    @Override
    public Users get(String username) {
        return userDao.get(username);
    }

    @Override
    public void insert(Users user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        // nên kiểm tra cả email/phone trùng
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email) || userDao.checkExistPhone(phone)) {
            return false;
        }
        java.sql.Date createdAt = new java.sql.Date(System.currentTimeMillis());
        Users u = new Users(email, username, fullname, password, null, 1, phone, createdAt);
        userDao.insert(u);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public Users getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        // TODO: hash newPassword trước khi lưu nếu có
        return userDao.updatePasswordByEmail(email, newPassword);
    }

	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(String trim, int i, String imageFileName) {
		// TODO Auto-generated method stub
		return false;
	}
}
