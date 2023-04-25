package giis;
import org.junit.*;

import giis.Demo;

public class TestDemo {
	@Test
	public void testDemoString() {
		Demo d=new Demo();
		Assert.assertEquals("ABCD",d.uCase("Abcd"));
	}
	@Test
	public void testDemoInt() {
		Demo d=new Demo();
		Assert.assertTrue(d.getValue()>0);
	}
}
