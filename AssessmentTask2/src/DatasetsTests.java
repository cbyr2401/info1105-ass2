import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class DatasetsTests {

/*	@Test
	public void testHashMapDataMine() throws FileNotFoundException, IOException {
		Datasets set1 = new Datasets(1,4000,4000);
		Datasets set2 = new Datasets(10,4000,4000);
		Datasets set3 = new Datasets(1,4271,4000);
		Datasets set4 = new Datasets(5,4271,4000);
		Datasets set5 = new Datasets(1,4271,2000);
		
		System.out.println("**** SET 1 ****");
		set1.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 2 ****");
		set2.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 3 ****");
		set3.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 4 ****");
		set4.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 5 ****");
		set5.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");

	}*/
	
	/*@Test
	public void testDoubleHashMapDataMine() throws FileNotFoundException, IOException {
		Datasets set1 = new Datasets(1,4271,2000,1);
		Datasets set2 = new Datasets(1,4271,2000,223);
		Datasets set3 = new Datasets(1,4271,2000,647);
		Datasets set4 = new Datasets(1,4271,4000,1);
		Datasets set5 = new Datasets(1,4271,4000,223);
		Datasets set6 = new Datasets(1,4271,4000,627);
		//try {
		System.out.println("**** SET 1 ****");
		set1.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 2 ****");
		set2.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 3 ****");
		set3.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 4 ****");
		set4.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 5 ****");
		set5.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 6 ****");
		set6.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");

		//} catch (RuntimeException e) {
		//	if (!e.getMessage().equals("Double Hashing failed to find a free position")) {
		//		throw e;
		//	}
		//}
		
	}*/
	
	
	@Test 
	public void testChainingHashMap() throws FileNotFoundException, IOException{
		Datasets set1= new Datasets(1, 4000, 4000, "test");
		Datasets set2= new Datasets(10, 4000, 4000, "test");
		Datasets set3= new Datasets(1, 4271, 4000, "test");
		Datasets set4= new Datasets(5, 4271, 4000, "test");
		Datasets set5= new Datasets(1, 4271, 2000, "test");
		
		System.out.println("**** SET 1 Chaining ****");
		set1.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 2 Chaining ****");
		set2.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 3 Chaining****");
		set3.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 4 Chaining ****");
		set4.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		System.out.println("**** SET 5 Chaining ****");
		set5.exploreData("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetA.txt");
		
	}
	
	
	/*@Test
	public void testPasswordDataMine() throws FileNotFoundException, IOException{
		Datasets set1 = new Datasets();
		
		set1.printHashCollisions("C:\\Users\\Cian Byrne\\Documents\\repos\\java_info1105_ass2\\datasetB.txt");
	}*/
	
}
