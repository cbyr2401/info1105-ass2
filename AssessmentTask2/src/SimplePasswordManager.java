import java.util.ArrayList;
import java.util.List;

public class SimplePasswordManager {
	private ChainingHashMap<String, Long> map;

	// construct a SimplePasswordManager with 4000 places and default hash
	// parameters
	// multiplier = 1 and modulus = 4271
	public SimplePasswordManager() {

		this.map = new ChainingHashMap<>(1,4271);
	}

	// construct a SimplePasswordManager with the supplied parameters
	public SimplePasswordManager(int size, int multiplier, int modulus) {
		this.map = new ChainingHashMap<>(size, multiplier, modulus);
	}

	/*
	 * Implement a researched Hash Method for passwords here:
	 */

	// hashing
	public Long hashPassword(String password) {
		return (long) (Math.abs(password.hashCode()) * 512);
	}

	// interface methods
	// return an array of the usernames of the users currently stored
	public List<String> listUsers() {
		return map.keys();
	}

	public String authenticate(String username, String password) {
		if(this.map.get(username) == null) return "No such user exists.";
		else if(this.map.get(username).equals(hashPassword(password))) return username;
		else return "Failed to authenticate user.";
	}

	public String addNewUser(String username, String password) {
		if(this.map.get(username) == null){
			this.map.put(username, hashPassword(password));
			return username;
		}else{
			return "User already exists.";
		}
	}

	public String deleteUser(String username, String password) {
		if(this.map.get(username) == null) return "No such user exists.";
		else if(this.map.get(username).equals(hashPassword(password))){
			this.map.remove(username);
			return username;
		}
		else return "Failed to authenticate user.";
		
	}

	public String resetPassword(String username, String oldPassword, String newPassword) {
		if(this.map.get(username) == null) return "No such user exists.";
		else if(this.map.get(username).equals(hashPassword(oldPassword))){
			this.map.put(username, hashPassword(newPassword));
			return username;
		}
		else return "Failed to authenticate user.";
	}
}
