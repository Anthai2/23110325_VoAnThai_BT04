package vn.iostar.dao;
import vn.iostar.entity.Users;
import java.util.List;

import vn.iostar.models.UserModel;

public interface UserDao {
	Users get(String username);

    void insert(Users user);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    Users getUserByEmail(String email);

    boolean updatePasswordByEmail(String email, String newPassword);
	
	
}
