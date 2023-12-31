import java.util.ArrayList;
import java.util.List;
/*
 *   Chaining Hash Map
 */
public class ChainingHashMap<K extends Comparable<K>, V> {
	private ChainingHashMapNode<K, V>[] map;
	private int hashMul;
	private int hashMod;
	private int numberOfItems;
	private ChainingHashMapNode<K,V> defunct = new ChainingHashMapNode<>(null,null);
	
	// construct a HashMap with 4000 places and given hash parameters
	public ChainingHashMap(int multiplier, int modulus){
		this.map = (ChainingHashMapNode<K,V>[]) new ChainingHashMapNode[4000];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
		
	}
	
	// construct a HashMap with given capacity and given hash parameters
	public ChainingHashMap(int hashMapSize, int multiplier, int modulus){
		this.map = (ChainingHashMapNode<K,V>[]) new ChainingHashMapNode[hashMapSize];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
	}
	
	// size (return the number of nodes currently stored in the map)
	public int size(){
		return this.numberOfItems;
	}
	
	public boolean isEmpty(){
		return (size()==0);
	}
	
	// interface
	public int[] getFullestBuckets(){
		int maxNodes = 0;
		int numNodes = 0;
		int[] binCounts = new int[this.map.length];
		
		ChainingHashMapNode<K,V> current;
		
		for (int i = 0; i < this.map.length; i++) {
			current = this.map[i];
			if(current != null){
				// there is a node in the array, but how many are unknown.
				boolean done = false;
				while(!done){
					numNodes += 1;
					if(current.getNext() != null){
						// there are more nodes:
						current = current.getNext();
					}else{
						done = true;
						// check / update values for getting largest bracket
						if(numNodes > maxNodes){
							maxNodes = numNodes;
						}
						binCounts[i] = numNodes;
						numNodes = 0;
					}
				}
			}
		}
		numNodes = 0;
		for(int i=0; i < this.map.length; i++){
			if(binCounts[i] == maxNodes){
				numNodes++;
			}
		}
		return new int[] {maxNodes, numNodes};
	}
	public List<K> keys(){
		List<K> myList = new ArrayList<K>();
		for (int i = 0; i < this.map.length; i++) {
				if(this.map[i] != null){
					// there is a node in the array, but how many are unknown.
					boolean done = false;
					ChainingHashMapNode<K,V> current = this.map[i];
					while(!done){
						myList.add(current.getKey());
						if(current.getNext() != null){
							// there are more nodes:
							current = current.getNext();
						}else{
							done = true;
						}
					}
				}
			}
		return myList;
	}
	
	public V put(K key, V value){
		int index = hash(key) % this.map.length;
		
		if(this.map[index] == null){
			// add first node for that bin
			this.map[index] = new ChainingHashMapNode<>(key, value);
			this.numberOfItems += 1;
			return null;
		}else{
			boolean done = false;
			ChainingHashMapNode<K,V> current = this.map[index];
			while(!done){
				
				if(current.getKey().equals(key)){
					// element is the same, update value and return
					V temp = current.getValue();
					current.setValue(value);
					return temp;
				}
				if(current.getNext() == null){
					// add element to the end of the linked list:
					current.setNext(new ChainingHashMapNode<K,V>(key, value));
					this.numberOfItems += 1;
					return null;
				}
				current = current.getNext();
			}
		}
		return null;
	}
	public V get(K key){
		int index = hash(key) % this.map.length;
		
		if(this.map[index] == null){
			// bin is empty, therefore does not exist
			return null;
		}else{
			// there is stuff in the bin, but what?
			boolean done = false;
			ChainingHashMapNode<K,V> current = this.map[index];
			while(!done){
				if(current.getKey().equals(key)){
					// element is located, return value
					return current.getValue();
				}
				if(current.getNext() != null){
					// there are more elements
					current = current.getNext();
				}else{
					return null;
				}
			}
		}
		return null;
	}
	public V remove(K key){
		int index = hash(key) % this.map.length;
		
		if(this.map[index] == null){
			// bin is empty, therefore does not exist
			return null;
		}else{
			// there is stuff in the bin, but what?
			ChainingHashMapNode<K,V> current = this.map[index];
			ChainingHashMapNode<K,V> previous = null;
			ChainingHashMapNode<K,V> tempNext = null;
			/*
			 * To remove:
			 *   1. GET bin - using hash(key)
			 *   2. FIND element
			 *   3. GET the next element of remove node (if it exists)
			 *   4. PUT the next element of remove node into previous node
			 *   5. return value
			 */
			while(current != null){
				if(current.getKey().equals(key)){
					// found item
					V tempValue = current.getValue();
					tempNext = current.getNext();
					
					if(tempNext != null){
						// has next element
						if(previous != null){
							// has previous element, link next and previous
							previous.setNext(tempNext);
						}else{
							// it is the first item in the list and has nodes after it:
							this.map[index] = tempNext;
						}
					}else{
						// does not have next element
						if(previous != null){
							// is last element in list:
							previous.setNext(null);
						}else{
							// is first element in list:
							this.map[index] = null;
						}
					}
					this.numberOfItems -= 1;
					return tempValue;
				}else{
					// there are more items in the list:
					previous = current;
					current = current.getNext();
				}	
			}
		}
		//keep java happy:
		return null;
	}
	
	// hashing
	public int hash(K key){
		return Math.abs(this.hashMul * key.hashCode()) % this.hashMod;
	}
}
