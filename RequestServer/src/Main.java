import data.MySqlData;
import data.ServerData;

public class Main {

	public static void main(String[] args) {
		ServerData sd=new MySqlData("centralserverdb","root","");
		sd.getPin(1);
		sd.getPins();
		sd.getPinsInput();
		sd.getPinsOutput();
		sd.insertInputPin(3,(float)11.2, "Termostat Cada", "DTH11 ");
		sd.insertOutputPin(5, 1, "Lumina baie");
		//sd.removeOutputPinbyPin_no(5);
	}

}
