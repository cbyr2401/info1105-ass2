/*
 * This is use to store the key-value pairs
 *  for Linear Probing and Double Hashing.
 */

public class HashMapNode<K extends Comparable<K>, V> {
	private K key;
	private V value;
	
	//constructor
	public HashMapNode(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	// get methods
	public K getKey(){
		return this.key;
	}
	public V getValue(){
		return this.value;
	}
	// set method
	public void setValue(V newValue){
		this.value = newValue;
	}
}
