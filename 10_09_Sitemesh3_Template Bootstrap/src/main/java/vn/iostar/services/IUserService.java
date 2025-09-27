package vn.iostar.services;

import vn.iostar.models.UserModel;

public interface IUserService {
	void insert(UserModel user);
	UserModel login(String username, String password);
	
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    UserModel findByUserName(String username);
    UserModel findOne(int id);
    
    void updateUser(UserModel user);
    boolean resetPasswordByEmail(String email, String newPassword);
}
