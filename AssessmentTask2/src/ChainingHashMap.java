import java.util.ArrayList;
import java.util.List;
/*
 * Hi there this is a test, what is happening? yo
 */
public class ChainingHashMap<K extends Comparable<K>, V> {
	private HashMapNode<K, V>[] map;
	private int hashMul;
	private int hashMod;
	private int numberOfItems;
	private HashMapNode<K,V> defunct = new HashMapNode<>(null,null);
	
	// construct a HashMap with 4000 places and given hash parameters
	public ChainingHashMap(int multiplier, int modulus){
		this.map = (HashMapNode<K,V>[]) new HashMapNode[4000];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
		
	}
	// construct a HashMap with given capacity and given hash parameters
	public ChainingHashMap(int hashMapSize, int multiplier, int modulus){
		this.map = (HashMapNode<K,V>[]) new HashMapNode[hashMapSize];
		this.hashMod = modulus;
		this.hashMul = multiplier;
		this.numberOfItems = 0;
	}
	// hashing
	public int hash(K key){
		return 0;
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
		return null;
	}
	public List<K> keys(){
		return new ArrayList<K>();
	}
	public V put(K key, V value){
		return null;
	}
	public V get(K key){
		return null;
	}
	public V remove(K key){
		return null;
	}
}
