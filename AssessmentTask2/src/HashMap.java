import java.util.ArrayList;
import java.util.List;
/*
 * This is the HashMap class for Linear Probing.
 */
public class HashMap<K extends Comparable<K>, V> {
	private ArrayList<HashMapNode<K, V>> map;
	private int hashMul;
	private int hashMod;
	private int numberOfItems;

	// construct a HashMap with 4000 places and given hash parameters
	public HashMap(int multiplier, int modulus) {
		this.map = new ArrayList<>(4000);
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
		System.out.println(map.size());
	}

	// construct a HashMap with given capacity and given hash parameters
	public HashMap(int hashMapSize, int multiplier, int modulus) {
		this.map = new ArrayList<>(hashMapSize);
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
	}

	// hashing
	public int hash(K key) {
		return this.hashMul * Math.abs(key.hashCode()) % this.hashMod;
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
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i) != null) {
				myList.add(map.get(i).getKey());
			}
		}
		return myList;
	}

	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		int index = hash(key);
		HashMapNode<K,V> node = new HashMapNode<>(key, value);
		if (get(key) == null) {
			this.map.add(index, node);
			this.numberOfItems += 1;
			return null;
		}

		/*
		 * This part of the method deals with any collisions, using linear
		 * probing. If the keys are identical, it will replace the value,
		 * however it isn't an identical key, it will probe for either the key
		 * later in the array, or the first null value. contains a nested for
		 * loop incase the index reaches the last value of the map array
		 */
		else {
			
			for(int i=index; i < this.map.size(); i++){
				if(this.map.get(i).getKey() == key) {
					V temp = this.map.get(i).getValue();
					this.map.get(i).setValue(value);
					return temp;
				}
				if(this.map.get(i) == null){
					this.map.add(i, node);
					this.numberOfItems += 1;
					return null;
				}
			}
			
			for(int i=0; i < index; i++){
				if(this.map.get(i).getKey() == key) {
					V temp = this.map.get(i).getValue();
					this.map.get(i).setValue(value);
					return temp;
				}
				if(this.map.get(i) == null){
					this.map.add(i, node);
					this.numberOfItems += 1;
					return null;
				}
			}
			
			/* for (int i = index+1; i < map.size(); i++) {
				if (this.map.get(i).getKey() == key) {
					V temp = this.map.get(i).getValue();
					this.map.get(i).setValue(value);
					return temp;
				} else if (map.get(i) == null) {
					map.add(i, node);
					this.numberOfItems += 1;
					return null;
				}
				if (i == map.size()) {
					for (int j = 0; j < map.size(); j++) {
						if (map.get(j).getKey() == key) {
							V temp = map.get(j).getValue();
							map.get(j).setValue(value);
							return temp;
						} else if (map.get(j) == null) {
							map.add(j, node);
							this.numberOfItems += 1;
							return null;
						}
					}
				}
			} */
		}
		return null;
	}

	// Gets the hash index, then either returns the value at this index from the
	// map or returns null.

	public V get(K key) {
		//if(false){
		//	return null;
		//}else{
			int index = hash(key);
			for(int i=index; i < this.map.size(); i++){
				//if(this.map.get(i) == null) return null;
				if(this.map.get(i).getKey() == key) return this.map.get(i).getValue();
			}
			for(int i=0; i < index; i++){
				if(this.map.get(i).getKey() == key) return this.map.get(i).getValue();
			}
			return null;
			
			/* if (map.get(index) == null) {
				return null;
			} else if (map.get(index).getKey() == key) {
				return map.get(index).getValue();
			}

			for (int i = index + 1; i < map.size(); i++) {
				if (map.get(i).getKey() == key) {
					return map.get(i).getValue();
				}
				if (map.get(i).getKey() == null) {
					return null;
				}
				if (i == map.size()) {
					for (int j = 0; j < map.size(); j++) {
						if (map.get(j).getKey() == key) {
							return map.get(j).getValue();
						}
						if (map.get(j).getKey() == null) {
							return null;
						}
					}
				}
			}
			return null; */
		//}
		

	}

	public V remove(K key) {
		int index = hash(key);
		if (map.get(index) == null) {
			return null;
		} else if (map.get(index).getKey() == key) {
			V temp = map.get(index).getValue();
			map.get(index).setValue(null);
			return temp;
		}

		for (int i = index + 1; i < map.size(); i++) {
			if (map.get(i).getKey() == key) {
				return map.get(i).getValue();
			}
			if (map.get(i).getKey() == null) {
				return null;
			}
			if (i == map.size()) {
				for (int j = 0; j < map.size(); j++) {
					if (map.get(j).getKey() == key) {
						return map.get(j).getValue();
					}
					if (map.get(j).getKey() == null) {
						return null;
					}
				}
			}
		}
		return null;
	}
}