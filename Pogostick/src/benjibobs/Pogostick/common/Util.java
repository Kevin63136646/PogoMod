package benjibobs.Pogostick.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Util {
	
	public static String[] changelog = null;
	
	public static String newVersion = Pogostick.version;
	
	public static boolean hasUpdate(){
		
		newVersion = getTextFromSite("http://benjibobs.webege.com/mods/updatecheck/pogo.html");
	    
		if(!Pogostick.version.equalsIgnoreCase(newVersion) && !newVersion.equalsIgnoreCase("")){
			
			String temp = getTextFromSite("http://benjibobs.webege.com/mods/changelog/pogo.html").replace(".V.", newVersion);
			
			changelog = temp.split(".NL.");
			
			return true;
		
		}
		
		return false;
		
	}
	
	public static String getTextFromSite(String s){
		
		URL url;
		InputStream is = null;
	    BufferedReader br;
	    String line;
	    String result = "";
		
	    try{
	    	
	    	url = new URL(s);
	    	is = url.openStream();
	    	br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	        	
	            result = result + line;
	            
	        }
	        
	        return result;
	    	
	    } catch (MalformedURLException e) {
	    	
	         e.printStackTrace();
	         
	         return "";
	         
	    } catch (IOException e1) {
	    	
	         e1.printStackTrace();
	         return "";
	         
	    } finally {
	    	
	        try {
	        	
	            if (is != null) is.close();
	            
	        } catch (IOException e2) {
	            
	        	// nothing to see here
	        	return "";
	        	
	        }
	    }

	}
	
}
