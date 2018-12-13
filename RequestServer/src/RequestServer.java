import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RequestServer {
public static void main(String args[])
{HttpServer server;
try {
	server = HttpServer.create(new InetSocketAddress(8765), 0);
} catch (IOException e) {
	e.printStackTrace();
	return;
}
HttpContext context = server.createContext("/outputpins");
context.setHandler( new OutputPinsHandler() );
server.start();
	
}
}
