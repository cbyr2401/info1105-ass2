import java.util.ArrayList;

public class DoubleHashMap<K extends Comparable<K>, V> {
	private ArrayList<HashMapNode<K,V>> map;
	private int hashMul;
	private int hashMod;
	private int hashMod2;
	private int numberOfItems;
	// updated construction
	// construct a HashMap with 4000 places and given hash parameters
	public DoubleHashMap(int multiplier, int modulus, int secondaryModulus){
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.map = new ArrayList<>(4000);
		this.numberOfItems = 0;
	}
	
	// construct a HashMap with given capacity and given hash parameters
	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus){
		this.hashMul = multiplier;
		this.hashMod = modulus;
		this.hashMod2 = secondaryModulus;
		this.map = new ArrayList<>(hashMapSize);
		this.numberOfItems = 0;
	}
	
	/*
	 * TODO:  Copy and Paste Methods from HashMap.java
	 */
	
	public int secondaryHash(K key){
		return this.hashMod2 - (Math.abs(key.hashCode()) % this.hashMod2);
	}
}