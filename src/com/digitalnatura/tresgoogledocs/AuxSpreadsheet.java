package com.digitalnatura.tresgoogledocs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


import android.os.Environment;

import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Key;
import com.google.api.client.auth.oauth2.draft10.AccessProtectedResource;




//import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.json.jackson.JacksonFactory;




public class AuxSpreadsheet {
	
	
	
	
	
	

	List<String[]> xestorFollas(AuxSpreadsheet xestorFollas, String title, final String token11, Boolean exact)  throws IOException {
	// TODO Auto-generated method stub
	
		
		
		
	HttpRequestFactory wiseRequestFactory;
	HttpTransport transport = AndroidHttp.newCompatibleTransport();
	wiseRequestFactory = transport.createRequestFactory(new HttpRequestInitializer() {
        public void initialize(HttpRequest req) throws IOException {
        
            GoogleHeaders headers = new GoogleHeaders();
            
            headers.gdataVersion = "3";
            headers.setApplicationName("dosgoogledocs");
            
//            client login:
//            headers.setGoogleLogin(token11);
            
            
//            oauth2:
            headers.setAuthorization("Bearer "+token11);
            
//            headers.set("name", "xesgarcia@gmail.com");
            req.setEnableGZipContent(true);
            req.setHeaders(headers);
            
            
            
            
 
        }
    });
	
	
	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full?alt=json");
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

	 url.title = title;
     url.title_exact = exact;
     

 	String id = null;
 	String id1 = null;

     HttpResponse response = wiseRequestFactory.buildGetRequest(url).execute();
//     HttpRequest request = wiseRequestFactory.buildGetRequest(url);

     
//     InputStream jsonresponse = response.getContent();
     
     
     String hols = response.parseAsString();
     
     
	 File newTextFile = new File(Environment.getExternalStorageDirectory()+"/"+"a.json");
     FileWriter fw = new FileWriter(newTextFile);
     fw.write(hols);
     fw.close();
     
     
     
     
     
     
     
//     String array = "";
//     String hols = request.toString();
     
     List<String[]> list = new ArrayList<String[]>();
     JacksonFactory f = new JacksonFactory();     
     JsonParser jParser = f.createJsonParser(hols);
     
     
    	 while (jParser.nextToken() != JsonToken.END_OBJECT) {
    			 while (jParser.nextToken() != JsonToken.END_OBJECT) {
    				 String fieldname = jParser.getCurrentName();	
    	        	 if ("openSearch$totalResults".equals(fieldname)){
    	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
                	            	 id1 = jParser.getText();
    	        		 }   					 
    				 }   				
    			 }   		
    	 }
    	 
    	 jParser.close();
    	 int resultados = Integer.parseInt(id1);
    	 String[] s = new String[resultados];
    	 String[] t = new String[resultados];
    	 
    	 JacksonFactory jf = new JacksonFactory();     
         JsonParser jParser1 = jf.createJsonParser(hols);
    	 
    	 for (int i = 0; i < resultados; i++) {
    		 while (jParser1.nextToken() != JsonToken.END_OBJECT) {
    			 while (jParser1.nextToken() != JsonToken.END_OBJECT) {
    				 String fieldname = jParser1.getCurrentName();	
    	        	 if ("title".equals(fieldname)){
    	        		 while (jParser1.nextToken() != JsonToken.END_OBJECT) {
                	            	 id = jParser1.getText();
                	            	 
                	            	 s[i] = id;
                	            	  
                	            	 
                	            	  
    	           		 }   	
    	        		
    				 }   	else if("content".equals(fieldname)) {
    					 while (jParser1.nextToken() != JsonToken.END_OBJECT) {
        	            	 id = jParser1.getText();
        	            	 t[i] = id;
        	            	  
        	            	 
        	            	  
           		 }   	
    					 
    				 }
    			 }   		
    	 }
//    		 list.add(id);
//    		  array = array+id+",\n";
    	 }
    	 jParser1.close();
    	 
    	 
    	 
//    	 id = jParser.getText();
    	 
    	 
    	 
     
list.add(s);
list.add(t);


  

     


	return  list;
	
}
	
	
	
static class WiseUrl extends GenericUrl {
    @Key String title;
    @Key("title-exact") Boolean title_exact;
    @Key String fields;
    @Key String sq;
    @Key String orderby;
    @Key Boolean reverse;

    WiseUrl(String url) {
        super(url);
    }

}

}




