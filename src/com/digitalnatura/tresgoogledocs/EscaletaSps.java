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


import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

//import com.digitalnatura.jsonparser.JsonParserActivity;
//import com.digitalnatura.jsonparser.ObjetoEscaleta;
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




public class EscaletaSps {
	
	
	
	
	
	

	private static String LOGTAG;
//	private ArrayList<ObjetoEscaleta> oEscaleta;
	private String TAG = "logaendo";
	private String intext;
	private String lugar;
	private String dianoche;
	private String accion;
	private String personajes;



	ArrayList<ObjetoEscaleta> xestorEscaletas( ArrayList<ObjetoEscaleta> oEscaleta, String title,   String keyword)  throws IOException {
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
            SharedPreferences settings = ListaFollas.getContext().getSharedPreferences("MyPrefs", 0);
    	    String tokenp = settings.getString("token", null);
            
//            oauth2:
            headers.setAuthorization("Bearer "+tokenp);
            
//            headers.set("name", "xesgarcia@gmail.com");
            req.setEnableGZipContent(true);
            req.setHeaders(headers);
            
            
            
            
 
        }
    });
	
	
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full?alt=json");
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/worksheets/tZxfWbnb54F3gYX2F8epY_A/private/full?alt=json");
	// WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
	WiseUrl urld = new WiseUrl(keyword);
	WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/list/"+urld.getPathParts().get(3)+"/od6/private/full?alt=json");
	
  	




	 oEscaleta = new ArrayList<ObjetoEscaleta>();
	 
	 
	 url.title = title;
//     url.title_exact = exact;
     

 	String id = null;
 	String id1 = null;

     HttpResponse response = wiseRequestFactory.buildGetRequest(url).execute();
//     HttpRequest request = wiseRequestFactory.buildGetRequest(url);


     
     
     String hols = response.parseAsString();
     
     
     
     
     JacksonFactory f = new JacksonFactory();

		JsonParser jParser = f.createJsonParser(hols);
		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
			 while (jParser.nextToken() != JsonToken.END_OBJECT) {
				 String fieldname = jParser.getCurrentName();	
				 while (jParser.nextToken() != JsonToken.END_OBJECT) {
	        	 if ("openSearch$totalResults".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 id1 = jParser.getText();
	        		 }   	
	        	 if ("gsx$intext".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 intext = jParser.getText();
	        		 }  
	        	 if ("gsx$dianoche".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 dianoche = jParser.getText();
	        		 }  
	        	 if ("gsx$lugar".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 lugar = jParser.getText();
	        		 } 
	        	 if ("gsx$accion".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 accion = jParser.getText();
	        		 } 
	        	 if ("gsx$personajes".equals(fieldname)){
//	        		 while (jParser.nextToken() != JsonToken.END_OBJECT) {
         	            	 personajes = jParser.getText();
	        		 } 
	        	 
	        	 
	        	 
	        	 
				 }   				
			 }   	
			 
			 
			 
			 
			 if (intext!=null){
				 oEscaleta.add(new ObjetoEscaleta(intext, dianoche, lugar, accion, personajes));
			}
			 
			 }
		 jParser.close();
		 
		 if (oEscaleta.size()>0){
		 oEscaleta.remove(oEscaleta.size()-1);
		 }
		
//	 }
		
    
     

    	 
    	 
    	 
     
//list.add(s);
//list.add(t);
return oEscaleta;


  

     


//	return  list;
	
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




