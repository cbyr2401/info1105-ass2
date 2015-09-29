
public class ChainingHashMapNode<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private ChainingHashMapNode<K,V> nextNode;
	
	// construction
	public ChainingHashMapNode(K key, V value){
		this.key = key;
		this.value = value;
		this.nextNode = null;
	}

	// get methods
	public K getKey(){
		return this.key;
	}
	public V getValue(){
		return this.value;
	}
	public ChainingHashMapNode<K, V> getNext(){
		return this.nextNode;
	}
	// set methods
	public void setValue(V newValue){
		this.value = newValue;
	}
	public void setNext(ChainingHashMapNode<K, V> next){
		this.nextNode = next;
	}

}
