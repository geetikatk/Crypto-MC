import java.util.*;
import java.lang.*;

class Eucli {
	static public void gcdExtended(long a, long b)
	{
		long x = 0, y = 1, lastx = 1, lasty = 0, temp;
		while (b != 0)
		{
			long q = a / b;
			long r = a % b;

			a = b;
			b = r;

			temp = x;
			x = lastx - q * x;
			lastx = temp;

			temp = y;
			y = lasty - q * y;
			lasty = temp;		 
		}
	System.out.println("GCD "+a+" and its Roots x : "+ lastx +" y :"+ lasty);

	}

	public static void main(String[] args)
	{
		
		long a = 24, b = 320;
	    gcdExtended(a, b);
		
	}
}

