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
			int hash1 = hash(key);
			
			if(this.map[hash1 % this.map.length] == null){
				this.map[hash1 % this.map.length] = new HashMapNode<>(key, value);
				this.numberOfItems += 1;
				return null;
			}
			
			int hash2 = secondaryHash(key);
			int current = -1;
			int initialIndex = (hash1 + 0 * hash2) % this.map.length;

			for(int j=0; current != initialIndex; j++){

				current = (hash1 + (j * hash2)) % this.map.length; 

				if(this.map[current] == null){
					this.map[current] = new HashMapNode<>(key, value);
					this.numberOfItems += 1;
					return null;
				}else{
					if(this.map[current].getKey().equals(key)){
						V temp = this.map[current].getValue();
						this.map[current].setValue(value);;
						return temp;
					}
				}
			}
			throw new RuntimeException("Double Hashing failed to find a free position");
			/*
			 * TODO:  Check if we need to go over the map again.
			 */
			
			//keep java happy:
			//return null;
		}

		// Gets the hash index, then either returns the value at this index from the
		// map or returns null.

		public V get(K key) {
			int index = hash(key) % this.map.length;
						
			if(this.map[index] == null){
				return null; // nothing in map for that with single hash
			}else if(this.map[index].getKey().equals(key)){
				System.out.println("Ez find... " + index + " Key: " + key);
				return this.map[index].getValue();
				
			}else{
				int h = hash(key) + (0 * secondaryHash(key));
				int current = -1;
				System.out.println("In Else statement");
				for(int i = 1; current != h; i++){
					current = hash(key) + (i * secondaryHash(key));
					index = current % this.map.length;
					
					System.out.println("Index: " + index + " Key: " + key + " map reference: " + this.map[index]);
					
					if(this.map[index] == null) return null;
					else if(this.map[index].getKey().equals(key)) return this.map[index].getValue();
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