import java.util.ArrayList;
import java.util.List;

public class HashMap<K extends Comparable<K>, V> {
	private ArrayList<HashMapNode<K,V>> map;
	private int hashMul;
	private int hashMod;
	
	
	// construct a HashMap with 4000 places and given hash parameters
	public HashMap(int multiplier, int modulus){
		this.map = new ArrayList<>(4000);
		this.hashMod = modulus;
		this.hashMul = multiplier;
		
	}
	// construct a HashMap with given capacity and given hash parameters
	public HashMap(int hashMapSize, int multiplier, int modulus){
		this.map = new ArrayList<>(hashMapSize);
		this.hashMod = modulus;
		this.hashMul = multiplier;
	}
	// hashing
	public int hash(K key){
		return this.hashMul * Math.abs(hashCode(key)) % this.hashMod;
	}
	// size (return the number of nodes currently stored in the map)
	public int size(){
		return this.map.size();
	}
	public boolean isEmpty(){
		return size()==0;
	}
	// interface methods
	public List<K> keys(){
		return null; 
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