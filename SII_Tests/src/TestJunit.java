import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import main.MessageUtil;

public class TestJunit {
	
   @Test
   public void testZeroParameter() {	 
	  Float testZero = Float.valueOf((float) 0);
	   
      assertEquals(testZero, MessageUtil.division(1, 0));
   }
   
   @Test
   public void testResult() {
	   float numberAsPrimitive = (float) 3.5;
	   Float testNumber = Float.valueOf(numberAsPrimitive);
	   
	   assertEquals(testNumber, MessageUtil.division(7, 2));
   }
   
   @Test
   public void testReturnType() {
	   assertTrue(MessageUtil.division(7, 3) instanceof Float);
   }
   
}