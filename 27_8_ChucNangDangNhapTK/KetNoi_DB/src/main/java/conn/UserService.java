package conn;

public interface UserService {
    Account login(String username, String password);
    Account get(String username);
}
