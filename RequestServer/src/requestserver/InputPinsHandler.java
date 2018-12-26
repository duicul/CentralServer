package requestserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data.ServerData;

public class InputPinsHandler implements HttpHandler {
	private ServerData sd;
	
	public InputPinsHandler(ServerData sd) {
		super();
		this.sd = sd;}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		boolean err=false;
	    InputStream is=exchange.getRequestBody();
	    InputStreamReader isr =  new InputStreamReader(is,"utf-8");
	    BufferedReader br = new BufferedReader(isr);
	    int b;
	    String buf = "";
	    while ((b = br.read()) != -1) {
	        buf+=((char) b);}
	    is.close();
	    br.close();
	    isr.close();
	    JSONObject obj; 
	    try {
			obj = new JSONObject(buf); 
			System.out.println("|"+obj.toString()+"|");
			if(!err){
				for(int i=1;i<=20;i++){
					try {
						float value=(float) obj.getDouble(i+"");
						sd.updateInputPinValue(i, value);}
					catch (JSONException e) {
						e.printStackTrace();}
				}
			}
	    }
	    catch (JSONException e) {
			e.printStackTrace();
			err=true;}
	    
	    
	    
	    //set message length!!!
	    String resp= err ? "Error":"Okay";
	    exchange.sendResponseHeaders(200, resp.getBytes().length);
	    OutputStream os = exchange.getResponseBody();
	    OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
	    
	    osw.write(resp);
	    
	    
	    osw.flush();
	    osw.close();
	    os.flush();
	    os.close();

	    System.out.println("data sent "+resp);

	}

}