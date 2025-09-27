package vn.iostar.services.impl;

import java.sql.Date;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserService implements IUserService {
	private final IUserDao userDao = new UserDaoImpl();

	 @Override
	    public UserModel login(String username, String password) {
	        UserModel u = userDao.findByUserName(username);
	        if (u != null && password != null && password.equals(u.getPassword())) {
	            return u;
	        }
	        return null;
	    }
	 
    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) return false;
        if (userDao.checkExistEmail(email)) return false;
        if (phone != null && !phone.isBlank() && userDao.checkExistPhone(phone)) return false;

        UserModel u = new UserModel();
        u.setUsername(username);
        u.setPassword(password);              
        u.setEmail(email);                    
        u.setFullname(fullname);
        u.setImages(null);
        u.setPhone(phone);
        u.setRoleid(2);
        u.setCreateDate(new Date(System.currentTimeMillis()));

        userDao.insert(u);
        return true;
    }

    @Override public boolean checkExistEmail(String email)   { return userDao.checkExistEmail(email); }
    @Override public boolean checkExistUsername(String name) { return userDao.checkExistUsername(name); }
    @Override public boolean checkExistPhone(String phone)   { return userDao.checkExistPhone(phone); }

    @Override public UserModel findByUserName(String username) { return userDao.findByUserName(username); }
    @Override public UserModel findOne(int id)                 { return userDao.findOne(id); }

    @Override public void insert(UserModel user) { userDao.insert(user); }
    @Override
    public boolean resetPasswordByEmail(String email, String newPassword){
        if (!userDao.checkExistEmail(email)) return false;
        return userDao.updatePasswordByEmail(email, newPassword);
    }
    @Override
	public void updateUser(UserModel user) {
		userDao.updateUser(user);
	}
}
