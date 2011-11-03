package com.digitalnatura.tresgoogledocs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ConfirmacionSpGdocs extends Activity {


private String titulo;
private String keyword;
private BbddHelper helper;
private ArrayList<ObjetoEscaleta> oEscaleta;


//	private static final Button Button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.confirmescaleta);

        
         titulo = this.getIntent().getExtras().get("titulo").toString();
         keyword = this.getIntent().getExtras().get("urlnova").toString();

        
        Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG).show();
        
//        ObjetoEscaleta obxetoescaleta = new ObjetoEscaleta();
        
        

        
         helper = new BbddHelper(this, null, null, 0);
		
		Button button = (Button) findViewById(R.id.ok);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				
				
				//
				
				// FileOutputStream f = new FileOutputStream(destinationFile);

				
				String _id = helper.getID();
				helper.insertarTitulo(_id, titulo, keyword);
				

				finish();
			}
		});
		
	    
	}
	
	
	  protected class InitTask extends AsyncTask<Context, Integer, String> {

		  private String keyword;
		 
		private boolean renovartoken;

		private Object retornojedi;

		private String authToken;



		@Override
		    protected void onPreExecute() {
			
		
				
			 
			  
				     
		  }

		@Override
		protected String doInBackground(Context... params) {
			
			// TODO Auto-generated method stub
			 EscaletaSps escaletas = new EscaletaSps();
			    
			 oEscaleta = new ArrayList<ObjetoEscaleta>();
			    
			    try {
			    	
			    	oEscaleta= escaletas.xestorEscaletas(escaletas, "", authToken, null);
	     	    	 renovartoken= true;
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					renovartoken=false;
//					   handleException(e);
					   return null;
					
				}
			    
			   
			    
			   
			    
			     return null;
			     
		
			     
			    
			   
			   
			    
			
		}
		
		  
		@Override
		protected void onPostExecute(String result) {
			
			 

			
		

//			setListAdapter(fileList);
			
			if (renovartoken){
//				String[] array = retornojedi.get(0);
				
//				getThis().setListAdapter(new ArrayAdapter<String>(getThis(),
//		 				android.R.layout.simple_list_item_1, retornojedi.get(0)));
				
				
				
				
				
				
//				dialog.dismiss();
				
				
				
//				  keyword = retornojedi.get(0)[0];
//			 dialog.dismiss();
//			
//			 
//			  setContentView(R.layout.main);
//			    TextView mResultado = (TextView)findViewById(R.id.texto);   
//		     	  mResultado.setText("Holla "+ keyword);
		     	  }
//			dialog.dismiss();
			
		}

		  
	  }
	
	
	
	

}
