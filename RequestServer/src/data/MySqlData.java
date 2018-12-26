package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MySqlData implements ServerData {
private String dbname,driver,uname,pass;
	public MySqlData(String dbname, String uname, String pass) {
	this.dbname = dbname;
	this.uname = uname;
	this.pass = pass;
	this.driver="com.mysql.cj.jdbc.Driver";
}

	@Override
	public Pin getPin(int pin_no) {
		int pin_num=-1;
	String type = "",name="";
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from pins where Pin_No="+pin_no);  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			type=rs.getString(2);
			name=rs.getString(3);
				System.out.println(pin_num+" "+type+" "+name);  
			}con.close();  
			}catch(Exception e){ System.out.println(e);return null;}  
			  
		return new Pin(pin_num,type,name);
	}


	@Override
	public PinOutput getOutputPinbyPin_no(int pin_no) {
		int pin_num=-1,value=-1;
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select op.Pin_No, op.value from out_pins op  where op.Pin_No="+pin_no);  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			value=rs.getInt(2);
				System.out.println(pin_num+" "+value);  
			}con.close();  
			}catch(Exception e){ System.out.println(e);return null;}  
			  
		return new PinOutput(pin_num,value);
	}

	@Override
	public PinOutput getOutputPinbyPin_noUpdate(int pin_no) {
		int pin_num=-1,value=-1;
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select op.Pin_No, op.value from out_pins op  where op.Pin_No="+pin_no);  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			value=rs.getInt(2);
				System.out.println(pin_num+" "+value);  
			}con.close();  
			}catch(Exception e){ System.out.println(e);return null;}  
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();   
			stmt.executeUpdate("UPDATE out_pins op SET op.CHANGED=FALSE where op.Pin_No="+pin_no);  
			con.close();  
			}catch(Exception e){ System.out.println(e);return null;}
			  
		return new PinOutput(pin_num,value);
	}
	
	@Override
	public PinInput getIntputPinbyPin_no(int pin_no) {
		int pin_num=-1;
		Timestamp timestamp=null;
		float value=0;
		String sensor = "";
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select ip.Pin_No, ip.Value , ip.Sensor,ip.TimeStamp from in_pins ip where ip.Pin_No="+pin_no);  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			value=rs.getFloat(2);
			sensor=rs.getString(3);
			timestamp=rs.getTimestamp(4);
				System.out.println(pin_num+" "+value+" "+sensor+" "+timestamp);  
			}con.close();  
			}catch(Exception e){ System.out.println(e);return null;}  
			  
		return new PinInput(pin_num,value,sensor,timestamp);
	}

	@Override
	public void insertInputPin(int pin_no, float value, String name, String sensor) {
		this.removePinByPin_no(pin_no);
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("INSERT INTO pins ( Pin_No , TYPE , NAME ) VALUES ("+pin_no+",'IN','"+name+"') ");
			con.close();  
			}catch(Exception e)
		{ System.out.println(e);}  
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("INSERT INTO in_pins ( Pin_No , Value , Sensor ) VALUES ("+pin_no+","+value+",'"+sensor+"') ");
			con.close();   
			}catch(Exception e)
		{ System.out.println(e);}

	}

	@Override
	public void insertOutputPin(int pin_no, int value, String name) {
		this.removePinByPin_no(pin_no);
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("INSERT INTO pins ( Pin_No , TYPE , NAME ) VALUES ("+pin_no+",'OUT','"+name+"') ");
			con.close();  
			}catch(Exception e)
		{ System.out.println(e);}  
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("INSERT INTO out_pins ( Pin_No , Value ) VALUES ("+pin_no+","+value+") ");
			con.close();   
			}catch(Exception e)
		{ System.out.println(e);}

	}

	@Override
	public void removeInputPinbyPin_no(int pin_no) {
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass); 
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("delete p,ip from in_pins ip inner join pins p on p.Pin_No=ip.Pin_No where p.type='IN' AND p.Pin_No="+pin_no);
			con.close();  
			}catch(Exception e)
		{ System.out.println(e);}  

	}

	@Override
	public void removeOutputPinbyPin_no(int pin_no) {
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("delete p,op from out_pins op inner join pins p on p.Pin_No=op.Pin_No where p.type='OUT' AND p.Pin_No="+pin_no);
			con.close();  
			}catch(Exception e)
		{ System.out.println(e);}   

	}

	@Override
	public void removePinByPin_no(int pin_no) {
		Pin p=this.getPin(pin_no);
		if(p.type.equals("IN"))
			this.removeInputPinbyPin_no(pin_no);
		else if(p.type.equals("OUT"))
			this.removeOutputPinbyPin_no(pin_no);
		
	}
	
	
	@Override
	public List<Pin> getPins() {
		int pin_num=-1;
		String type = "",name="";
		List<Pin> lp=new ArrayList<Pin>();
			try{  
				Class.forName(this.driver);  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass); 
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from pins");  
				while(rs.next())  
				{
				pin_num=rs.getInt(1);
				type=rs.getString(2);
				name=rs.getString(3);
					System.out.println(pin_num+" "+type+" "+name);
					lp.add(new Pin(pin_num,type,name));
				}con.close();  
				}catch(Exception e){ System.out.println(e);}  
				  
			return lp;
	}
	

	@Override
	public List<PinOutput> getPinsOutput() {
		int pin_num=-1,value=-1;
		List<PinOutput> lp=new ArrayList<PinOutput>();
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from out_pins");  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			value=rs.getInt(2);
				System.out.println(pin_num+" "+value); 
				lp.add(new PinOutput(pin_num,value));
			}con.close();  
			}catch(Exception e){ System.out.println(e);}  
			  
		return lp;
	}
	
	public List<PinOutput> getPinsOutputChanged(boolean update)
	{int pin_num=-1,value=-1;
	List<PinOutput> lp=new ArrayList<PinOutput>();
	try{  
		Class.forName(this.driver);  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from out_pins where changed=true");  
		while(rs.next())  
		{
		pin_num=rs.getInt(1);
		value=rs.getInt(2);
			System.out.println(pin_num+" "+value); 
			lp.add(new PinOutput(pin_num,value));
		}con.close();  
		}catch(Exception e){ System.out.println(e);}  
	if(update) {
	try{  
		Class.forName(this.driver);  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();   
		stmt.executeUpdate("UPDATE out_pins op SET op.CHANGED=FALSE ");  
		con.close();  
		}catch(Exception e){ System.out.println(e);}}
	return lp;}

	@Override
	public List<PinInput> getPinsInput() {
		int pin_num=-1;
		Timestamp timestamp=null;
		float value=0;
		String sensor = "";
		List<PinInput> lp=new ArrayList<PinInput>();
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from in_pins");  
			while(rs.next())  
			{
			pin_num=rs.getInt(1);
			value=rs.getFloat(2);
			sensor=rs.getString(3);
			timestamp=rs.getTimestamp(4);
				System.out.println(pin_num+" "+value+" "+sensor+" "+timestamp);  
				lp.add(new PinInput(pin_num,value,sensor,timestamp));
			}con.close();  
			}catch(Exception e){ System.out.println(e);}  
			  
		return lp;
	}

	@Override
	public void updateInputPinValue(int pin_no, float value) {
		try{  
			Class.forName(this.driver);  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/"+dbname,uname,pass);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement(); 
			stmt.executeUpdate("UPDATE in_pins SET value="+value+" WHERE Pin_No="+pin_no);
			con.close();  
			}catch(Exception e)
		{ System.out.println(e);} 		
	}

	@Override
	public void tunonOutputPin(int pin_no) {
		PinOutput po=this.getOutputPinbyPin_no(pin_no);
		Pin p=this.getPin(pin_no);
		if(po!=null&p!=null)
		this.insertOutputPin(pin_no, 1, p.name);
		
	}

	@Override
	public void tunoffOutputPin(int pin_no) {
		PinOutput po=this.getOutputPinbyPin_no(pin_no);
		Pin p=this.getPin(pin_no);
		if(po!=null&p!=null)
		this.insertOutputPin(pin_no, 0, p.name);
		
	}

	@Override
	public void toggleOutputPin(int pin_no) {
		PinOutput po=this.getOutputPinbyPin_no(pin_no);
		Pin p=this.getPin(pin_no);
		if(po!=null&p!=null)
		{int value=this.getOutputPinbyPin_no(pin_no).value==0?1:0;
		this.insertOutputPin(pin_no, value, p.name);}
		
	}
}
