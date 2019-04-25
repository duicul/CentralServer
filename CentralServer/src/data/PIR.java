package data;

import java.sql.Timestamp;

public class PIR extends PinInput {

	public PIR(int pin_no, String value, String name, String sensor, Timestamp timestamp) {
		super(pin_no, value, name, sensor, timestamp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getData() {
		String resp="";
		System.out.println("Sensor type |"+this.sensor+"|");
		if(this.sensor=="PIR")
		resp+="<p>"+this.pin_no+" Last movement detected by "+this.name+" on "+this.timestamp+"</p>";
	return resp;
	}

	@Override
	public String getGauge() {
		return null;
	}

}
