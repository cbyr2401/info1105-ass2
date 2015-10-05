import java.util.ArrayList;
import java.util.List;

public class DoubleHashMap<K extends Comparable<K>, V>{
	private HashMapNode<K, V>[] map;
	private int hashMul;
	private int hashMod;
	private int hashMod2;
	private int numberOfItems;
	private HashMapNode<K,V> defunct = new HashMapNode<>(null,null);
	
	// updated construction
	// construct a HashMap with 4000 places and given hash parameters
	public DoubleHashMap(int multiplier, int modulus, int secondaryModulus){
		this.map = (HashMapNode<K,V>[]) new HashMapNode[4000];
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.numberOfItems = 0;
	}
	
	// construct a HashMap with given capacity and given hash parameters
	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus){
		this.map = (HashMapNode<K,V>[]) new HashMapNode[hashMapSize];
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.numberOfItems = 0;
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
					if(this.map[i].getKey() != null){ // due to defunct
						myList.add(this.map[i].getKey());
					}
				}
			}
			return myList;
		}

		
		public V put(K key, V value) {
			int index = hash(key) % this.map.length;
			
			if(this.map[index] == null){
				this.map[index] = new HashMapNode<>(key, value);
				this.numberOfItems += 1;
				return null;
			}
			
			/*
			 * This part of the method deals with any collisions, using linear
			 * probing. If the keys are identical, it will replace the value,
			 * however it isn't an identical key, it will probe for either the key
			 * later in the array, or the first null value. contains a nested for
			 * loop incase the index reaches the last value of the map array
			 * 
			 *  TODO:  convert for Double Hashing
			 */
			for(int i = index; i < this.map.length; i++){
				if(this.map[i] == null){
					this.map[i] = new HashMapNode<>(key, value);
					this.numberOfItems += 1;
					return null;
				}else{
					if(this.map[i].getKey().equals(key)){
						V temp = this.map[i].getValue();
						this.map[i].setValue(value);;
						return temp;
					}
				}
			}
			// wrap around:
			for(int i = 0; i < index; i++){
				if(this.map[i] == null){
					this.map[i] = new HashMapNode<>(key, value);
					this.numberOfItems += 1;
					return null;
				}else{
					if(this.map[i].getKey().equals(key)){
						V temp = this.map[i].getValue();
						this.map[i].setValue(value);;
						return temp;
					}
				}
			}
			//keep java happy:
			return null;
		}

		// Gets the hash index, then either returns the value at this index from the
		// map or returns null.

		public V get(K key) {
			int index = hash(key) % this.map.length;
			
			if(this.map[index] == null) return null; // nothing in map for that:
			
			else{
				for(int i = index; i < this.map.length; i++){
					if(this.map[index] == null) return null;
					else if(this.map[i].getKey().equals(key)) return this.map[i].getValue();
				}
				for(int i = 0; i < index; i++){
					if(this.map[i] == null) return null;
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
					if(this.map[index] == null) return null;
					else if(this.map[i].getKey().equals(key)) {
						V temp = this.map[i].getValue();
						this.map[i] = this.defunct;
						this.numberOfItems -= 1;
						return temp;
					}
				}
				for(int i = 0; i < index; i++){
					if(this.map[index] == null) return null;
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
	
	public int secondaryHash(K key){
		return this.hashMod2 - (Math.abs(key.hashCode()) % this.hashMod2);
	}
}