package sf.crom.restclient.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import sf.crom.pojo.Address;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	URL url;
		try {
			url = new URL("http://localhost:8090/restsvc/services/hello/address/");
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			 if (conn.getResponseCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		                    + conn.getResponseCode());
		        }

		        BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));

		        String output;
		       
		        while ((output = br.readLine()) != null) {
		            Gson gson = new Gson();
		            Address address = gson.fromJson(output, Address.class);
		            System.out.println("Gson city "+address.getCity());
		            System.out.println("Gson State "+address.getState());
		            System.out.println("Gson zip "+address.getZip());

		        }
		        /*JsonArray array = new JsonArray(output);

		        for (int i =0; i < array.size(); i++) {
		            JSONObject row = array.getJSONObject(i);
		            String user = row.getString("username");
		            System.out.println(user);
		        }*/

		        conn.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }
}
