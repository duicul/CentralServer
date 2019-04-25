package data;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DHT extends PinInput {
public final Double temp,humid;
	public DHT(int pin_no, String value,String name,String sensor, Timestamp timestamp) {
		super(pin_no, value, sensor,name, timestamp);
		List<Double> vals=Arrays.asList(this.value.split(" ")).stream().filter(strval -> strval.length()>0).map(realval -> Double.parseDouble(realval)).collect(Collectors.toList());
		this.temp=vals.get(0);
		this.humid=vals.get(1);
	}

	public String toString()
	{return this.pin_no+" "+this.sensor+" "+this.value+" "+this.timestamp;}

	@Override
	public String getData() {
		String resp="";
		System.out.println(this);
		System.out.println("Sensor type |"+this.sensor+"|"+this.value);
		resp+="<p>"+this.pin_no+" "+this.name+" "+this.sensor+" Temperature : "+temp+"C Humidity : "+humid+"% "+this.timestamp+"</p>";
		System.out.println(resp);
		return resp;
	}

	@Override
	public String getGauge() {
		return "";
	}
}
