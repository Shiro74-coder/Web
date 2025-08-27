package conn;

public class UserServiceImpl implements UserService {
    private final AccountDAO dao = new AccountDAO();

    @Override
    public Account login(String username, String password) {
        Account u = get(username);
        if (u != null && password != null && password.equals(u.getPassword())) {
            return u;
        }
        return null;
    }

    @Override
    public Account get(String username) {
        return dao.getByUsername(username);
    }
}
