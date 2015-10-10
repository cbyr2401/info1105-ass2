import static org.junit.Assert.*;

import org.junit.Test;


public class ChainingHashMapTests {

	@Test
	public void testConstruction() {
		ChainingHashMap<Integer, Integer> map= new ChainingHashMap<>(69,69);
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.get(0));
		assertEquals(0, map.keys().size());
		assertEquals(2, map.getFullestBuckets().length);
	}
	
	@Test(timeout=1000)
	public void testSmallMap(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
		
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
	public void testHashFunction(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,10);
		
		assertEquals(0, m.hash(10000));
		assertEquals(0, m.hash(15));
		assertEquals(0, m.hash(25));
		assertEquals(0, m.hash(50));
		assertEquals(0, m.hash(1));
		
		assertEquals(0, m.hash(11));
		assertEquals(0, m.hash(12));
		assertEquals(0, m.hash(13));
		assertEquals(0, m.hash(127));
		
		ChainingHashMap<Integer, Integer> map = new ChainingHashMap<>(10,7);
		
		assertEquals(5, map.hash(10000));
		assertEquals(3, map.hash(15));
		assertEquals(5, map.hash(25));
		assertEquals(3, map.hash(50));
		
		assertEquals(5, map.hash(11));
		assertEquals(1, map.hash(12));
		assertEquals(4, map.hash(13));
		assertEquals(3, map.hash(127));
	}
	
	@Test
	public void testSmallRemoval(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
		
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
	
	
	@Test(timeout=1000)
	public void testTinyCollisions(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
				
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(15, (int)m.put(0, 25));
		assertEquals(25, (int)m.get(0));
		
		m.put(1, 100);
		assertEquals(100, (int)m.get(1));
		
		assertEquals(null, m.put(15, 125));
		System.out.println(m.keys());
		assertEquals(125, (int)m.get(15));	
		assertEquals(3, m.keys().size());
		
		assertEquals(null, m.put(11, 11));
		assertEquals(null, m.put(12, 12));
		assertEquals(null, m.put(13, 13));
		assertEquals(null, m.put(127, 127));
		
		assertEquals(127, (int)m.get(127));
		
		
		
	}
	

}
