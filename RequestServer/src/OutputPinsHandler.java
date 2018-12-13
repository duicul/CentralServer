import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.json.JSONException;
import org.json.JSONObject;

public class OutputPinsHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String response = "{\"10\":\"True\"}";
	    JSONObject obj = new JSONObject();
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
	    try {
	    	for(int i=0;i<=20;i++)
	    		if(new Random().nextFloat()>0.7)
			     obj.put(i+"",new Random().nextFloat()>0.5?"True":"False");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    System.out.println("|"+obj.toString()+"|");
	    //set message length!!!
	    exchange.sendResponseHeaders(200, obj.toString().getBytes().length);
	    OutputStream os = exchange.getResponseBody();
	    OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
	    osw.write(obj.toString());
	    System.out.println("|"+obj.toString()+"|");
	    osw.flush();
	    osw.close();
	    os.flush();
	    os.close();
	    System.out.println("data sent "+buf);

	}

}
