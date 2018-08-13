
public class Modes
{
	public static void main (String [] args)
	{
		int [] tally = {0, 0, 10, 5, 10, 0, 7, 1, 0, 6, 0, 10, 3, 0, 0, 1};
		display (tally);
		int [] modes = calculateModes (tally);
		display (modes);
		int sum = 0;
		for (int i = 0; i < tally.length; i++)
			sum += tally [i];
		System.out.println ("k = index + 1\tkth data value");
		for (int i = 1; i <= sum; i++)
			System.out.println (i + "\t\t" + kthDataValue (tally, i));
	}
	public static int [] calculateModes (int [] tally)
	{
		int maxValue = findMax (tally);
		int modesSize = 0;
		for (int i = 0; i < tally.length; i++)
			if (tally [i] == maxValue)
				modesSize++;
		int [] modes = new int [modesSize];
		for (int i = 0, j = 0; i < tally.length; i++)
			if (tally [i] == maxValue)
				modes [j++] = i;
		return modes;
	}
	public static int kthDataValue (int [] tally, int k)
	{
		int arrayIndex = 0;
		while (k > 0)
			k -= tally [arrayIndex++];
		return arrayIndex;
	}
	public static int findMax (int [] toFind)
	{
		int maxVal = toFind [0];
		for (int i = 1; i < toFind.length; i++)
			if (toFind [i] > maxVal)
				maxVal = toFind [i];
		return maxVal;
	}
	public static void display (int [] toDisplay)
	{
		for (int i = 0 ; i < toDisplay.length; i++)
			System.out.print (toDisplay [i] + " ");
		System.out.println ();
		System.out.println ();
	}	
}