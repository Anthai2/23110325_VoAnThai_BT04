package vn.iostar.services.impl;


import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.*;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserService implements IUserService {

	UserDaoImpl userDao = new UserDaoImpl();
	
	
	@Override
	public UserModel login(String username, String password) {
		
		 UserModel user = this.FindByUserName(username);
		 if (user != null && password.equals(user.getPassword())) {
		 return user;
		 }
		 return null;
		 
	}

	@Override
	public UserModel FindByUserName(String username) {
		
		return userDao.findByUsername(username);
	}

}
