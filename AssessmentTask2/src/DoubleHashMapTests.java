import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DoubleHashMapTests {
	
	
	 @Rule
     public ExpectedException exception = ExpectedException.none();
	
	
	@Test
	public void testConstruction(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,10,7);
		assertEquals(0, m.size());
		assertEquals(true, m.isEmpty());
		assertEquals(0, m.keys().size());
		
		assertEquals(null, m.get(0));
	}
	
	@Test
	public void testTinyMap(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,10,7);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(1, m.size());
		assertEquals(false, m.isEmpty());
		assertEquals(1, m.keys().size());		
		
	}
	
	@Test
	public void testTinyCollisions(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,10,7);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		// double hash
		assertEquals(15, (int)m.put(0, 25));
		assertEquals(25, (int)m.get(0));
		
		assertEquals(null, m.put(1, 100));
		
		/*try {
			//do a single put operation here
			
		} catch(RuntimeException e) {
			if(!e.getMessage().equals("Double Hashing failed to find a free position")) {
				throw e;
			}
		}*/
		System.out.println(m.keys());
		assertEquals(100, (int)m.get(1));
		
		m.put(10000, 125);
		assertEquals(125, (int)m.get(10000));		
	}
	
	@Test
	public void testTinyHardCollisions(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,7,4);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(15, (int)m.put(0, 25));
		assertEquals(25, (int)m.get(0));
		
		m.put(1, 100);
		assertEquals(100, (int)m.get(1));
		
		assertEquals(null, m.put(15, 125));
		assertEquals(125, (int)m.get(15));	
		assertEquals(3, m.keys().size());
		
		assertEquals(null, m.put(11, 11));
		assertEquals(null, m.put(12, 12));
		assertEquals(null, m.put(13, 13));
		assertEquals(null, m.put(127, 127));
		
		assertEquals(127, (int)m.get(127));
		
	}
	
	@Test
	public void testHashFunction(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,10,7);
		
		assertEquals(0, m.hash(10000));
		assertEquals(0, m.hash(15));
		assertEquals(0, m.hash(25));
		assertEquals(0, m.hash(50));
		assertEquals(0, m.hash(1));
		
		assertEquals(3, m.secondaryHash(10000));
		assertEquals(6, m.secondaryHash(15));
		assertEquals(3, m.secondaryHash(25));
		assertEquals(6, m.secondaryHash(50));
		assertEquals(6, m.secondaryHash(1));
		
		assertEquals(0, m.hash(11));
		assertEquals(0, m.hash(12));
		assertEquals(0, m.hash(13));
		assertEquals(0, m.hash(127));
		
		assertEquals(3, m.secondaryHash(11));
		assertEquals(2, m.secondaryHash(12));
		assertEquals(1, m.secondaryHash(13));
		assertEquals(6, m.secondaryHash(127));
		
		DoubleHashMap<Integer, Integer> map = new DoubleHashMap<>(10,7,4);
		
		assertEquals(5, map.hash(10000));
		assertEquals(3, map.hash(15));
		assertEquals(5, map.hash(25));
		assertEquals(3, map.hash(50));
		
		//assertEquals(4, map.secondaryHash(10000));
		assertEquals(1, map.secondaryHash(15));
		assertEquals(3, map.secondaryHash(25));
		assertEquals(2, map.secondaryHash(50));
		assertEquals(3, map.secondaryHash(1));
		
		assertEquals(5, map.hash(11));
		assertEquals(1, map.hash(12));
		assertEquals(4, map.hash(13));
		assertEquals(3, map.hash(127));
		
		assertEquals(1, map.secondaryHash(11));
		assertEquals(4, map.secondaryHash(12));
		assertEquals(3, map.secondaryHash(13));
		assertEquals(1, map.secondaryHash(127));
	}
	
	@Test
	public void testSmallMap(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,7,4);
		
		assertEquals(true, m.isEmpty());
		assertEquals(0, m.keys().size());
		assertEquals(null, m.get(0));
		
		m.put(0, 5);
		m.put(1, 15);
		m.put(2, 25);
		m.put(3, 35);
		m.put(4, 45);
		m.put(5, 55);
		
		assertEquals(5, (int)m.get(0));
		assertEquals(15, (int)m.get(1));
		assertEquals(25, (int)m.get(2));
		assertEquals(35, (int)m.get(3));
		assertEquals(45, (int)m.get(4));
		assertEquals(55, (int)m.get(5));
		
		assertEquals(6, m.keys().size());
		assertEquals(6, m.size());
		
		assertEquals(false, m.isEmpty());
	}
	
	@Test
	public void testSmallRemoval(){
		DoubleHashMap<Integer, Integer> m = new DoubleHashMap<>(10,7,4);
		
		m.put(0, 5);
		m.put(1, 15);
		m.put(2, 25);
		m.put(3, 35);
		m.put(4, 45);
		m.put(5, 55);
		
		assertEquals(6, m.keys().size());
		assertEquals(6, m.size());
		
		assertEquals(5, (int)m.remove(0));
		assertEquals(5, m.size());
		assertEquals(5, m.keys().size());
		
		assertEquals(35, (int)m.remove(3));
		assertEquals(4, m.size());
		assertEquals(4, m.keys().size());
		
		assertEquals(55, (int)m.remove(5));
		assertEquals(3, m.size());
		assertEquals(3, m.keys().size());
	}

}
