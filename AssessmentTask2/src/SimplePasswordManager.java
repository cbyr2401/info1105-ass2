import java.util.ArrayList;
import java.util.List;

public class SimplePasswordManager {
	private ArrayList<HashMapNode<Integer,Long>> map;
	private int hashMul;
	private int hashMod;
	
	// construct a SimplePasswordManager with 4000 places and default hash parameters
	// multiplier = 1 and modulus = 4271
	public SimplePasswordManager(){
		this.hashMul = 1;
		this.hashMod = 4271;
		this.map = new ArrayList<>(4000);
	}
	// construct a SimplePasswordManager with the supplied parameters
	public SimplePasswordManager(int size, int multiplier, int modulus){
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.map = new ArrayList<>(size);
	}
	
	/*
	 * Implement a researched Hash Method for passwords here:
	 */
	
	// hashing
	public Long hashPassword(String password){
		return (long)(this.hashMul * (Math.abs(password.hashCode()) % this.hashMod));
	}
	
	// interface methods
	// return an array of the usernames of the users currently stored
	public List<String> listUsers(){
		return new ArrayList<String>();
	}
	public String authenticate(String username, String password){
		return null;
	}
	public String addNewUser(String username, String password){
		return null;
	}
	public String deleteUser(String username, String password){
		return null;
	}
	public String resetPassword(String username, String oldPassword, String newPassword){
		return null;
	}
}
