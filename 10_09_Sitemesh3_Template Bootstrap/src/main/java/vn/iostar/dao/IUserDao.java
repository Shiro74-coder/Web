package vn.iostar.dao;

import vn.iostar.models.UserModel;

public interface IUserDao {
	
	void insert(UserModel user);                     
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    UserModel findOne(int id);                      
    UserModel findByUserName(String username);
    
    void updateUser(UserModel user);
    boolean updatePasswordByEmail(String email, String newPassword);
}
