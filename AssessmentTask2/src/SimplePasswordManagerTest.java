import static org.junit.Assert.*;

import org.junit.Test;


public class SimplePasswordManagerTest {

	@Test
	public void testConstruction() {
		SimplePasswordManager spm = new SimplePasswordManager();
		
		spm.addNewUser("jim", "abcde");
		
		assertEquals("jim", spm.authenticate("jim", "abcde"));
		assertEquals("Failed to authenticate user.", spm.authenticate("jim", "abce"));

	}

}
