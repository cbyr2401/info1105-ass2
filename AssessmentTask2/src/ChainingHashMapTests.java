import static org.junit.Assert.*;

import java.util.Arrays;

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
		//System.out.println(m.keys());
		assertEquals(125, (int)m.get(15));	
		assertEquals(3, m.keys().size());
		
		assertEquals(null, m.put(11, 11));
		assertEquals(null, m.put(12, 12));
		assertEquals(null, m.put(13, 13));
		assertEquals(null, m.put(127, 127));
		
		assertEquals(127, (int)m.get(127));
		
	}
	
	@Test(timeout=1000)
	public void testGetFullestBrackets(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
		
		m.put(0, 15);
		
		assertEquals(15, (int)m.get(0));
		assertEquals(15, (int)m.put(0, 25));
		assertEquals(25, (int)m.get(0));
		
		m.put(1, 100);
		assertEquals(100, (int)m.get(1));
		
		assertEquals(null, m.put(15, 125));
		//System.out.println(m.keys());
		assertEquals(125, (int)m.get(15));	
		assertEquals(3, m.keys().size());
		
		assertEquals(null, m.put(11, 11));
		assertEquals(null, m.put(12, 12));
		assertEquals(null, m.put(13, 13));
		assertEquals(null, m.put(127, 127));
		
		
	}
	
	@Test(timeout=1000)
	public void testLargeMap(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
		
		// no collisions
		m.put(0, 5);
		m.put(1, 15);
		m.put(2, 25);
		m.put(3, 35);
		m.put(4, 45);
		m.put(5, 55);
		m.put(6, 65);
		m.put(7, 75);
		
		// collisions
		m.put(8, 85);
		m.put(9, 95);
		m.put(10, 100);
		m.put(11, 101);
		m.put(12, 102);
		m.put(13, 103);
		m.put(14, 104);
		
		assertEquals(15, m.keys().size());
		assertEquals(3, m.getFullestBuckets()[0]);
		assertEquals(1, m.getFullestBuckets()[1]);
		assertEquals(false, m.isEmpty());
		assertEquals(15, m.size());
		
		
		m.put(15, 105);
		assertEquals(3, m.getFullestBuckets()[0]);
		assertEquals(2, m.getFullestBuckets()[1]);
		
		// collisions
		m.put(15, 105);
		m.put(16, 106);
		m.put(17, 107);
		m.put(18, 108);
		m.put(19, 109);
		m.put(20, 200);
		m.put(21, 201);
		m.put(22, 202);
		m.put(23, 203);
		m.put(24, 204);
		m.put(25, 205);
		m.put(26, 206);
		m.put(27, 207);
		m.put(28, 208);
		
		
		assertEquals(29, m.keys().size());
		assertEquals(5, m.getFullestBuckets()[0]);
		assertEquals(1, m.getFullestBuckets()[1]);
		assertEquals(false, m.isEmpty());
		assertEquals(29, m.size());
		
		// check that we can get all values back:
		assertEquals(208, (int)m.get(28));
		assertEquals(207, (int)m.get(27));
		assertEquals(206, (int)m.get(26));
		assertEquals(205, (int)m.get(25));
		assertEquals(204, (int)m.get(24));
		assertEquals(203, (int)m.get(23));
		assertEquals(202, (int)m.get(22));
		assertEquals(201, (int)m.get(21));
		assertEquals(200, (int)m.get(20));
		assertEquals(109, (int)m.get(19));
		assertEquals(108, (int)m.get(18));
		assertEquals(107, (int)m.get(17));
		assertEquals(106, (int)m.get(16));
		assertEquals(105, (int)m.get(15));
		assertEquals(104, (int)m.get(14));
		assertEquals(103, (int)m.get(13));
		assertEquals(102, (int)m.get(12));
		assertEquals(101, (int)m.get(11));
		assertEquals(100, (int)m.get(10));
		assertEquals(95, (int)m.get(9));
		assertEquals(85, (int)m.get(8));
		assertEquals(75, (int)m.get(7));
		assertEquals(65, (int)m.get(6));
		assertEquals(55, (int)m.get(5));
		assertEquals(45, (int)m.get(4));
		assertEquals(35, (int)m.get(3));
		assertEquals(25, (int)m.get(2));
		assertEquals(15, (int)m.get(1));
		assertEquals(5, (int)m.get(0));
		
		// same keys, different values:
		//assertEquals(209, (int)m.put(29, 629));
		assertEquals(102, (int)m.put(12, 612));
		// all of these are in the same bin:
		assertEquals(15, (int)m.put(1, 601));
		assertEquals(85, (int)m.put(8, 608));
		assertEquals(105, (int)m.put(15, 615));
		assertEquals(202, (int)m.put(22, 622));
		m.put(29, 629);
		//assertEquals(null, (int);
		
		// check values stored correctly and did not mess up counts:
		assertEquals(30, m.keys().size());
		assertEquals(5, m.getFullestBuckets()[0]);
		assertEquals(2, m.getFullestBuckets()[1]);
		assertEquals(false, m.isEmpty());
		assertEquals(30, m.size());
		
		// check that we can get all values back:
		assertEquals(629, (int)m.get(29));
		assertEquals(208, (int)m.get(28));
		assertEquals(207, (int)m.get(27));
		assertEquals(206, (int)m.get(26));
		assertEquals(205, (int)m.get(25));
		assertEquals(204, (int)m.get(24));
		assertEquals(203, (int)m.get(23));
		assertEquals(622, (int)m.get(22));
		assertEquals(201, (int)m.get(21));
		assertEquals(200, (int)m.get(20));
		assertEquals(109, (int)m.get(19));
		assertEquals(108, (int)m.get(18));
		assertEquals(107, (int)m.get(17));
		assertEquals(106, (int)m.get(16));
		assertEquals(615, (int)m.get(15));
		assertEquals(104, (int)m.get(14));
		assertEquals(103, (int)m.get(13));
		assertEquals(612, (int)m.get(12));
		assertEquals(101, (int)m.get(11));
		assertEquals(100, (int)m.get(10));
		assertEquals(95, (int)m.get(9));
		assertEquals(608, (int)m.get(8));
		assertEquals(75, (int)m.get(7));
		assertEquals(65, (int)m.get(6));
		assertEquals(55, (int)m.get(5));
		assertEquals(45, (int)m.get(4));
		assertEquals(35, (int)m.get(3));
		assertEquals(25, (int)m.get(2));
		assertEquals(601, (int)m.get(1));
		assertEquals(5, (int)m.get(0));
		
		
		// test remove method:
		System.out.println(m.keys());
		
		assertEquals(629, (int)m.remove(29));
		assertEquals(208, (int)m.remove(28));
		assertEquals(207, (int)m.remove(27));
		assertEquals(206, (int)m.remove(26));
		assertEquals(205, (int)m.remove(25));
		assertEquals(204, (int)m.remove(24));
		
		assertEquals(null, m.get(29));
		assertEquals(null, m.get(28));
		assertEquals(null, m.get(27));
		assertEquals(null, m.get(26));
		assertEquals(null, m.get(25));
		assertEquals(null, m.get(24));
		
		assertEquals(95, (int)m.remove(9));
		assertEquals(608, (int)m.remove(8));
		assertEquals(75, (int)m.remove(7));
		assertEquals(65, (int)m.remove(6));
		assertEquals(55, (int)m.remove(5));
		assertEquals(45, (int)m.remove(4));
		
		//test get values:
		assertEquals(null, m.get(29));
		assertEquals(null, m.get(28));
		assertEquals(null, m.get(27));
		assertEquals(null, m.get(26));
		assertEquals(null, m.get(25));
		assertEquals(null, m.get(24));
		assertEquals(203, (int)m.get(23));
		assertEquals(622, (int)m.get(22));
		assertEquals(201, (int)m.get(21));
		assertEquals(200, (int)m.get(20));
		assertEquals(109, (int)m.get(19));
		assertEquals(108, (int)m.get(18));
		assertEquals(107, (int)m.get(17));
		assertEquals(106, (int)m.get(16));
		assertEquals(615, (int)m.get(15));
		assertEquals(104, (int)m.get(14));
		assertEquals(103, (int)m.get(13));
		assertEquals(612, (int)m.get(12));
		assertEquals(101, (int)m.get(11));
		assertEquals(100, (int)m.get(10));
		assertEquals(null, m.get(9));
		assertEquals(null, m.get(8));
		assertEquals(null, m.get(7));
		assertEquals(null, m.get(6));
		assertEquals(null, m.get(5));
		assertEquals(null, m.get(4));
		assertEquals(35, (int)m.get(3));
		assertEquals(25, (int)m.get(2));
		assertEquals(601, (int)m.get(1));
		assertEquals(5, (int)m.get(0));
		
		// check values stored correctly and did not mess up counts:
		assertEquals(30-12, m.keys().size());
		assertEquals(3, m.getFullestBuckets()[0]);
		assertEquals(0, m.getFullestBuckets()[1]);
		assertEquals(false, m.isEmpty());
		assertEquals(30-12, m.size());	
	}
	
	
	@Test
	public void testStatistics(){
		ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,7);
		
		// no collisions
		m.put(0, 5);
		m.put(1, 15);
		m.put(2, 25);
		m.put(3, 35);
		m.put(4, 45);
		m.put(5, 55);
		m.put(6, 65);
		m.put(7, 75);
		
		// collisions
		m.put(8, 85);
		m.put(9, 95);
		m.put(10, 100);
		m.put(11, 101);
		m.put(12, 102);
		m.put(13, 103);
		m.put(14, 104);
		
		
		
		
		
	}

}
