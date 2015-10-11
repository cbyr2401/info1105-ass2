import java.util.ArrayList;
import java.util.List;
/*
 * This is the HashMap class for Linear Probing.
 *  NOTE: defunct node means that if you query the position it is in
 *         it will not be 'null'.  The key and value will be 'null'
 */
public class HashMap<K extends Comparable<K>, V> {
	private HashMapNode<K, V>[] map;
	private int hashMul;
	private int hashMod;
	private int numberOfItems;
	private HashMapNode<K,V> defunct = new HashMapNode<>(null,null);
	
	//Variables used for statistics
	private int putCollisions;
	private int totalTally;
	private int maxTally;

	// construct a HashMap with 4000 places and given hash parameters
	public HashMap(int multiplier, int modulus) {
		this.map = (HashMapNode<K,V>[]) new HashMapNode[4000];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
		// for stats:
		resetStatistics();
	}

	// construct a HashMap with given capacity and given hash parameters
	public HashMap(int hashMapSize, int multiplier, int modulus) {
		this.map = (HashMapNode<K,V>[]) new HashMapNode[hashMapSize];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
		// for stats
		resetStatistics();
	}

	// hashing
	public int hash(K key) {
		return Math.abs(this.hashMul * key.hashCode()) % this.hashMod;
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
				if(this.map[i].getKey() != null){
					// due to defunct
					myList.add(this.map[i].getKey());
				}
			}
		}
		return myList;
	}

	
	public V put(K key, V value) {
		int index = hash(key) % this.map.length;
		
		/*
		 * This part of the method deals with any collisions, using linear
		 * probing. If the keys are identical, it will replace the value,
		 * however it isn't an identical key, it will probe for either the key
		 * later in the array, or the first null value. 
		 */

		int counter = 0;
		for(int i = index; i < this.map.length; i++){
			if(this.map[i] == null || this.map[i] == this.defunct){
				// found an empty node, put value in
				this.map[i] = new HashMapNode<>(key, value);
				this.numberOfItems += 1;
				
				// statistics collection:
				if(counter > this.maxTally) this.maxTally = counter;
				
				return null;
			}else{
				if(this.map[i].getKey().equals(key)){
					// node with same key, replace value
					V temp = this.map[i].getValue();
					this.map[i].setValue(value);
					
					// statistics collection:
					if(counter > this.maxTally) this.maxTally = counter;
					return temp;
				}
				if(i==index) this.putCollisions++;
				this.totalTally++;
				counter++;
			}
		}
		
		// wrap around:
		for(int i = 0; i < index; i++){
			if(this.map[i] == null || this.map[i] == this.defunct){
				// found an empty node, put value in
				this.map[i] = new HashMapNode<>(key, value);
				this.numberOfItems += 1;
				
				// statistics collection:
				if(counter > this.maxTally) this.maxTally = counter;
				return null;
			}else{
				
				if(this.map[i].getKey().equals(key)){
					// node with same key, replace value
					V temp = this.map[i].getValue();
					this.map[i].setValue(value);
					
					// statistics collection:
					if(counter > this.maxTally) this.maxTally = counter;
					return temp;
				}
				this.totalTally++;
				counter++;
			}
		}
		return null;
	}

	// Gets the hash index, then either returns the value at this index from the
	// map or returns null.

	public V get(K key) {
		int index = hash(key) % this.map.length;
		
		if(this.map[index] == null) return null; // nothing in map for that:
		
		else{
			for(int i = index; i < this.map.length; i++){
				if(this.map[i] == null) return null;
				else if(this.map[i] == this.defunct) continue;
				else if(this.map[i].getKey().equals(key)) return this.map[i].getValue();
				
			}
			for(int i = 0; i < index; i++){
				if(this.map[i] == null) return null;
				else if(this.map[i] == this.defunct) continue;
				else if(this.map[i].getKey().equals(key)) return this.map[i].getValue();
			}
		}
		// keep java happy:
		return null;
	}

	public V remove(K key) {
		int index = hash(key) % this.map.length;
		
		if (this.map[index] == null) return null; //not in the map
		
		else{
			for(int i = index; i < this.map.length; i++){
				if(this.map[i] == null) return null;
				else if(this.map[i] == this.defunct) continue;
				else if(this.map[i].getKey().equals(key)) {
					V temp = this.map[i].getValue();
					this.map[i] = this.defunct;
					this.numberOfItems -= 1;
					return temp;
				}
			}
			for(int i = 0; i < index; i++){
				if(this.map[i] == null) return null;
				else if(this.map[i] == this.defunct) continue;
				else if(this.map[i].getKey().equals(key)) {
					V temp = this.map[i].getValue();
					this.map[i] = this.defunct;
					this.numberOfItems -= 1;
					return temp;
				}
			}
		}
		return null;
	}
	
	/*
	 * Statistics Collection Methods:
	 */
	
	public int putCollisions(){
		return this.putCollisions;
	}
	public int totalCollisions(){
		return this.totalTally;
	}
	public int maxCollisions(){
		return this.maxTally;
	}
	public void resetStatistics(){
		this.putCollisions=0;
		this.totalTally=0;
		this.maxTally=0;
	}
}