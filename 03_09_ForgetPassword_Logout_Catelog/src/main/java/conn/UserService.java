package conn;

public interface UserService {
    Account login(String username, String password);
    Account get(String username);

    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
    boolean register(String username, String password, String fullname, String email, String phone);
    
    boolean verifyUsernameEmail(String username, String email);
    boolean changePassword(String username, String newPassword);
}
