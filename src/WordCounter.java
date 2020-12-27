import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.SSLHandshakeException;

public class WordCounter
{
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr)
    {
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException, SSLHandshakeException
    {
    	try 
    	{
    		URL url = new URL(this.urlStr);
    		URLConnection conn = url.openConnection();
    		InputStream in = conn.getInputStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	
    		String retVal = "";
    	
    		String line = null;
    		
    		while ((line = br.readLine()) != null)
    		{
    		    retVal = retVal + line + "\n";
    		}
    	
    		return retVal;
    	}
    	catch(SSLHandshakeException e) 
    	{
    		return "SSL";
    	}
		catch(IOException e) 
    	{
			return "IO";
    	}
    }
    
    public int countKeyword(String keyword) throws IOException
    {
		if (content == null)
		{
		    content = fetchContent();
		}
		
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1)
		{
		    retVal++;
		    fromIdx = found + keyword.length();
		    
		}
	
		return retVal;
    }
}