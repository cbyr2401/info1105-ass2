import static org.junit.Assert.*;

import org.junit.Test;


public class ChainingHashMapTests {

	@Test
	public void testConstruction() {
		ChainingHashMap<Integer, Integer> map= new ChainingHashMap<>(69,69);
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.get(0));
		
	}

}
