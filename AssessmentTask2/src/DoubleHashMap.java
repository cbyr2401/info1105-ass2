import java.util.ArrayList;
import java.util.List;

public class DoubleHashMap<K extends Comparable<K>, V> {
	private HashMapNode<K, V>[] map;
	private int hashMul;
	private int hashMod;
	private int hashMod2;
	private int numberOfItems;
	private HashMapNode<K, V> defunct = new HashMapNode<>(null, null);

	// Variables used for statistics
	private int collisionTally;
	private int totalTally;
	private int maxTally;
	private int failures;

	// updated construction
	// construct a HashMap with 4000 places and given hash parameters
	public DoubleHashMap(int multiplier, int modulus, int secondaryModulus) {
		this.map = (HashMapNode<K, V>[]) new HashMapNode[4000];
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.numberOfItems = 0;
		resetStatistics();
	}

	// construct a HashMap with given capacity and given hash parameters
	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus) {
		this.map = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.numberOfItems = 0;
		resetStatistics();
	}

	// hashing
	public int hash(K key) {
		return Math.abs(this.hashMul * key.hashCode()) % this.hashMod;
	}

	public int secondaryHash(K key) {
		return this.hashMod2 - (Math.abs(key.hashCode()) % this.hashMod2);
	}

	// size (return the number of nodes currently stored in the map)
	public int size() {
		return this.numberOfItems;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// interface methods
	public List<K> keys() {
		List<K> myList = new ArrayList<K>();
		for (int i = 0; i < map.length; i++) {
			if (this.map[i] != null) {
				if (this.map[i].getKey() != null) { // due to defunct
					myList.add(this.map[i].getKey());
				}
			}
		}
		return myList;
	}

	public V put(K key, V value) {
		int hash1 = hash(key);
		int hash2 = secondaryHash(key);
		int current = -1;
		int initialIndex = (hash1 + 0 * hash2) % this.map.length;
		boolean flag = false;
		int max = 0;
		for (int j = 0; current < this.map.length; j++) {

			current = (hash1 + (j * hash2)) % this.map.length;

			if (this.map[current] == null || this.map[current] == this.defunct) {
				this.map[current] = new HashMapNode<>(key, value);
				this.numberOfItems += 1;
				return null;
			} else {
				if (this.map[current].getKey().equals(key)) {
					V temp = this.map[current].getValue();
					this.map[current].setValue(value);
					return temp;
				}
			}
			// This statement will only run the first time after a collision has
			// occurred.
			if (current == initialIndex) {
				if (flag){
					break;
				}else{
					flag = true;
					collisionTally++;
				}
			}
			totalTally++;
			if (j > max) {
				maxTally = j;
			}
		}
		failures++;
		throw new RuntimeException("Double Hashing failed to find a free position");
		//return null;
	}

	// Gets the hash index, then either returns the value at this index from the
	// map or returns null.

	public V get(K key) {
		int hash1 = hash(key) % this.map.length;
		int hash2 = secondaryHash(key);
		int current = -1;

		for (int j = 0; current < this.map.length; j++) {
			current = (hash1 + (j * hash2)) % this.map.length;
			if (this.map[current] == null) {
				return null;
			} else {
				if(this.map[current] == this.defunct) continue;
				else if (this.map[current].getKey().equals(key)) {
					return this.map[current].getValue();
				}
			}
		}
		return null;
	}

	public V remove(K key) {
		int hash1 = hash(key) % this.map.length;
		int hash2 = secondaryHash(key);
		int current = -1;

		for (int j = 0; current < this.map.length; j++) {
			current = (hash1 + (j * hash2)) % this.map.length;
			if (this.map[current] == null) {
				return null;
			} else {
				if (this.map[current].getKey().equals(key)) {
					V temp = this.map[current].getValue();
					this.map[current] = this.defunct;
					this.numberOfItems -= 1;
					return temp;
				}
			}
		}
		return null;
	}

	public int putCollisions() {
		return this.collisionTally;
	}

	public int totalCollisions() {
		return totalTally;
	}

	public int maxCollisions() {
		return maxTally;
	}

	public void resetStatistics() {
		this.collisionTally = 0;
		this.totalTally = 0;
		this.maxTally = 0;
		this.failures = 0;
	}

	public int putFailures() {
		return failures;
	}

}