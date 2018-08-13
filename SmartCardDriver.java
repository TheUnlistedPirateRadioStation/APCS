

public class SmartCardDriver
{
	public static void main (String [] args)
	{
		Station downtown = new Station ("Downtown", 1);
		Station center = new Station ("Center City", 1);
		Station uptown  = new Station ("Uptown", 2);
		Station suburbia = new Station ("Suburb", 4);

		SmartCard jimmy = new SmartCard(20.00);	// bought with $20.00 
		jimmy.board(center);								// boarded in zone 1
		jimmy.disembark(suburbia);						// disembark in zone 4
		jimmy.disembark(uptown);						// disembark without having boarded
	}
}