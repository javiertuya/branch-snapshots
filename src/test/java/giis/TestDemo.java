package giis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDemo {
	@Test
	public void testDemoString() {
		Demo d=new Demo();
		assertEquals("ABCD",d.uCase("Abcd"));
	}
	@Test
	public void testDemoInt() {
		Demo d=new Demo();
		assertTrue(d.getValue()>0);
	}
}
