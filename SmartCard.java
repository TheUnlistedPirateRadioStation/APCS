
import java.text.DecimalFormat;

public class SmartCard
{
	// Fields
	private static DecimalFormat dFormat = new DecimalFormat ("$0.00");
	private double myValue;
	private Station myStation;
	private boolean hasBoarded;
	private boolean hasDisembarked;
	// Constructors
	public SmartCard (double initialValue)
	{
		myValue = initialValue;
		hasBoarded = false;
		hasDisembarked = true;
	}
	// Instance Methods
	public void addMoney (double amountToAdd)
	{
		myValue += amountToAdd;
		System.out.println ("Updated Balance is: " + dFormat.format (myValue));
	}
	public void board (Station boardingStation)
	{
		if (myValue < 0.50 || hasDisembarked == false || hasBoarded == true)
		{
			System.out.println ("Please see the Station Manager");
			System.exit (0);
		}
		myStation = boardingStation;
		hasDisembarked = false;
		hasBoarded = true;
	}
	public void cost (Station destStation)
	{
		int numOfZoneCrosses = Math.abs (destStation.getZone () - myStation.getZone ());
		double multipleZoneCharge = (numOfZoneCrosses * 0.75) + 0.5;
		System.out.println ("Cost from " + myStation.getName () + " to " + 
				destStation.getName () + " is " + dFormat.format (multipleZoneCharge));	
	}	
	public void disembark (Station destStation)
	{
		if (hasBoarded == false)
		{
			System.out.println ("Please see the Station Manager");
			System.exit (0);
		}
		cost (destStation);
		int numOfZoneCrosses = Math.abs (destStation.getZone () - myStation.getZone ());
		myValue -= (numOfZoneCrosses * 0.75) + 0.50;
		myStation = destStation;
		hasBoarded = false;
		hasDisembarked = true;
		if (myValue <= 0.00)
		{
			System.out.println ("Insufficent Balance");
			System.out.println ("Please see the Station Manager");
			System.exit (0);
		}
		System.out.println ("Balance is " + dFormat.format (myValue));
	}
}