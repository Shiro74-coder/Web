package conn;

public class UserServiceImpl implements UserService {
	private final AccountDAO dao = new AccountDAO();

	@Override
	public Account login(String username, String password) {
		Account u = get(username);
		return (u != null && password != null && password.equals(u.getPassword())) ? u : null;
	}

	@Override
	public Account get(String username) {
		return dao.getByUsername(username);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return dao.existsUsername(username);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return dao.existsEmail(email);
	}

	@Override
	public boolean register(String username, String password, String fullname, String email, String phone) {
		if (checkExistUsername(username))
			return false;
		if (email != null && !email.isBlank() && checkExistEmail(email))
			return false;
		Account a = new Account();
		a.setUsername(username);
		a.setPassword(password);
		a.setFullname(fullname);
		a.setEmail(email);
		a.setPhone(phone);
		a.setRole("USER");
		dao.insert(a);
		return true;
	}

	@Override
	public boolean verifyUsernameEmail(String username, String email) {
		return dao.existsByUsernameAndEmail(username, email);
	}

	@Override
    public boolean changePassword(String username, String newPassword) {
        return dao.updatePassword(username, newPassword);
	}
}
