package main;
public class MessageUtil {

   public static Float division (float iFirst, float iSecond) {
	   if ( iFirst == 0 || iSecond ==0) {
		   return (float) 0;
	   }
	   return Float.valueOf(iFirst/iSecond);
   }
   
}  