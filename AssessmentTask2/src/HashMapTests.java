import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapTests {

	
	@Test
	public void testConstruction(){
		HashMap<Integer, Integer> m = new HashMap<>(10,10);
		assertEquals(0, m.size());
		assertEquals(true, m.isEmpty());
		assertEquals(0, m.keys().size());
		assertEquals(null, m.get(0));
	}
	
	@Test
	public void testTinyMap(){
		HashMap<Integer, Integer> m = new HashMap<>(10,10);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(1, m.size());
		assertEquals(false, m.isEmpty());
		assertEquals(1, m.keys().size());		
		
	}
	
	@Test
	public void testTinyCollisions(){
		HashMap<Integer, Integer> m = new HashMap<>(10,3);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(15, (int)m.put(0, 25));
		assertEquals(25, (int)m.get(0));
		
		m.put(1, 100);
		assertEquals(100, (int)m.get(1));
		
		m.put(10000, 125);
		assertEquals(125, (int)m.get(10000));
		
		
		
	}

}
