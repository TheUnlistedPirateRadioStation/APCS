

public class Station
{
	// Fields
	private String myName;
	private int myZone;
	// Constructors
	public Station (String name, int zone)
	{
		myName = name;
		myZone = zone;
	}
	// Accessors
	public String getName ()
	{
		return myName;
	}
	public int getZone ()
	{
		return myZone;
	}
}