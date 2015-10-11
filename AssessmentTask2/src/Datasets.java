import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Datasets {
	// change hash map type here:
	DoubleHashMap<String, Double> hashMap;
	
	public Datasets(){
		// empty
	}
	
	
	public Datasets(int mul, int mod, int size){
		//this.hashMap = new HashMap<>(size,mul,mod);
	}
	
	public Datasets(int mul, int mod, int size, int mod2){
		this.hashMap = new DoubleHashMap<>(size,mul,mod,mod2);
	}
	
	
	/*
	 *  Dataset A: bot IP Addresses (datasetA.txt)
	 */
	public void exploreData(String pathToFile) throws FileNotFoundException, IOException {
		// instantiate hash maps
		
		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null) {
				String[] pieces = line.trim().split("\\s+");
				if (pieces.length == 4) {
					// TODO: put data into hash maps
					hashMap.put(pieces[0], Double.parseDouble(pieces[1]));
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		// TODO: print collision statistics
		System.out.println("PUT COLLISIONS: " + hashMap.putCollisions());
	 	System.out.println("TOTAL COLLISIONS: " + hashMap.totalCollisions());
	 	System.out.println("MAX COLLISIONS: " + hashMap.maxCollisions());
	 	//System.out.println("FAILURES: " + hashMap.putFailures());
	}
	
	/*
	 *   Dataset B: Passwords (datasetB.txt)
	 */
	
	public void printHashCollisions(String pathToFile) throws FileNotFoundException, IOException {
		HashMap<Long, List<String>> map = new HashMap<Long, List<String>>(50000, 1, 56897);
		SimplePasswordManager spm = new SimplePasswordManager();
		int passwordCounter = 0;
		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null) {
				String password = line.trim();
				Long passwordHash = spm.hashPassword(password);
				// TODO: if passwordHash is in a, add password to its list value
				if(map.get(passwordHash) != null){
					map.get(passwordHash).add(password);
				}else{
					map.put(passwordHash, new ArrayList<String>());
					map.get(passwordHash).add(password);					
				}
				// else, instantiate a new ArrayList and add password to it
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		List<Long> hashes = map.keys();
		for (Long hash : hashes) {
			List<String> passwords = map.get(hash);
			if (passwords.size() > 1) {
				// all passwords in this list have the same hash representation
				passwordCounter += passwords.size();
			}
		}
		System.out.println(passwordCounter + " passwords have the same HASH VALUE as something else.");
	}
	
}
