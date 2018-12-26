import data.MySqlData;
import data.ServerData;
import requestserver.RequestServer;

public class Main {
	public static void main(String[] args) {
		RequestServer rs=new RequestServer("centralserverdb","root","",8765);
				rs.start();
		ServerData sd=new MySqlData("centralserverdb","root","");
		sd.getPin(1);
		sd.getPins();
		sd.getPinsInput();
		sd.getPinsOutput();
		sd.insertInputPin(3,(float)11.2, "Termostat Cada", "DTH11");
		sd.insertOutputPin(5, 1, "Lumina baie");
		sd.insertOutputPin(1, 0, "Lumina hol");
		sd.tunonOutputPin(1);
		sd.tunoffOutputPin(5);
		//sd.getPinsOutputChanged(true);
		
		//sd.removeOutputPinbyPin_no(5);
		try {
			rs.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
