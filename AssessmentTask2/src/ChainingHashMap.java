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
	// hashing
	public int hash(K key){
		return Math.abs(this.hashMul * key.hashCode()) % this.hashMod;
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
		int maxIndex = 0;
		int numNodes = 0;

		for (int i = 0; i < this.map.length; i++) {
				if(this.map[i] != null){
					// there is a node in the array, but how many are unknown.
					boolean done = false;
					ChainingHashMapNode<K,V> current = this.map[i];
					while(!done){
						
						numNodes += 1;
						if(current.getNext() != null){
							// there are more nodes:
							current = current.getNext();
						}else{
							done = true;
							// check / update values for getting largest bracket
							if(numNodes >= maxNodes){
								maxIndex = i;
								maxNodes = numNodes;
							}
						}
					}
				}
			}
		int[] arr = {maxNodes, maxIndex};
		return arr;
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
			//System.out.println("add first node for that bin");
			this.map[index] = new ChainingHashMapNode<>(key, value);
			this.numberOfItems += 1;
			return null;
		}else{
			boolean done = false;
			ChainingHashMapNode<K,V> current = this.map[index];
			while(!done){
				
				if(current.getKey().equals(key)){
					// element is the same, update value and return
					//System.out.println("element is the same, update value and return");
					V temp = current.getValue();
					current.setValue(value);
					return temp;
				}
				if(current.getNext() == null){
					// add element to the end of the linked list:
					//System.out.println("add element to the end of the linked list");
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
			//System.out.println("bin is empty, therefore does not exist");
			return null;
		}else{
			// there is stuff in the bin, but what?
			boolean done = false;
			ChainingHashMapNode<K,V> current = this.map[index];
			while(!done){
				//System.out.print(current + " | Next:" + current.getNext() + " | Value: " + current.getValue() + " | Key: " + current.getKey());
				if(current.getKey().equals(key)){
					// element is located, return value
					//System.out.println("element is located, return value");
					return current.getValue();
				}
				if(current.getNext() != null){
					// there are more elements
					//System.out.println("there are more elements");
					current = current.getNext();
				}else{
					return null;
				}
			}
		}
		//keep java happy:
		return null;
	}
	public V remove(K key){
		int index = hash(key) % this.map.length;
		
		if(this.map[index] == null){
			// bin is empty, therefore does not exist
			//System.out.println("bin is empty, therefore does not exist");
			return null;
		}else{
			// there is stuff in the bin, but what?
			boolean done = false;
			ChainingHashMapNode<K,V> current = this.map[index];
			ChainingHashMapNode<K,V> previous = null;
			while(!done){
				//System.out.print(current + " | Next:" + current.getNext() + " | Value: " + current.getValue() + " | Key: " + current.getKey());
				if(current.getKey().equals(key)){
					// element is located, return value
					/*
					 * To remove:
					 *   1. GET element - success
					 *   2. GET the next element of remove node (if it exists)
					 *   3. PUT the next element of remove node into previous node
					 *   4. return value
					 */
					V tempValue = current.getValue();
					ChainingHashMapNode<K,V> tempNext = current.getNext();
					if(previous != null){
						// it is not the first item in the bin:
						previous.setNext(tempNext);
						current.setValue(null);
						current.setNext(null);
					}else{
						// it is the first item in the bin, so we empty that bin:
						this.map[index] = null;
					}
					this.numberOfItems -= 1;
					return tempValue;
				}
				if(current.getNext() != null){
					// there are more elements
					//System.out.println("there are more elements");
					current = current.getNext();
				}else{
					return null;
				}
			}
		}
		//keep java happy:
		return null;
	}
}
