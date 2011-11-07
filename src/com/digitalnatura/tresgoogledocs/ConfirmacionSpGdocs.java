package com.digitalnatura.tresgoogledocs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.digitalnatura.tresgoogledocs.ListaFollas.InitTask;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ConfirmacionSpGdocs extends Activity {


public static String LOGTAG;
private String titulo;
private String keyword;
private BbddHelper helper;
private ArrayList<ObjetoEscaleta> oEscaleta;
//protected ProgressDialog dialog;


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

//			private ProgressDialog dialog;

			public void onClick(View v) {
				
			
				
			

				 InitTask initTask = new InitTask();
			     	initTask.execute(ConfirmacionSpGdocs.this);
				
				
				//
				
				// FileOutputStream f = new FileOutputStream(destinationFile);

			
				

			}
		});
		
	    
	}
	
	
	  protected Context getContext() {
	// TODO Auto-generated method stub
	return this;
}


	protected class InitTask extends AsyncTask<Context, Integer, String> {

//		  private String keyword;
		 
		private boolean renovartoken;

		private Object retornojedi;

		private String authToken;

		private String TAG= "logaendo";



		@Override
		    protected void onPreExecute() {
			
		
//			 
			  
				     
		  }

		@Override
		protected String doInBackground(Context... params) {
			
			// TODO Auto-generated method stub
			 EscaletaSps escaletas = new EscaletaSps();
			    
			 oEscaleta = new ArrayList<ObjetoEscaleta>();
			 
			 
			 
			    
			    try {
			    	oEscaleta= escaletas.xestorEscaletas(oEscaleta, titulo, keyword);
//	     	    	 renovartoken= true;
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
//					renovartoken=false;
//					   handleException(e);
					   return null;
					
				}
			    
			    
			    Log.e(ConfirmacionSpGdocs.LOGTAG, "algo " + oEscaleta.size()+" "+ oEscaleta.get(0).getLugar());
				
//				String _id  d, titulo, keyword);
			   
			    
			     return null;
			     
		
			     
			    
			   
			   
			    
			
		}
		
		  
		@Override
		protected void onPostExecute(String result) {
			
			 

//			dialog.dismiss();
			
			
			
//				IGUAL QUE OS CLASES DOS OBJETOS TEÑEN UN MÉTODO TOSTRING TAMÉN SE LLES PODERÍA FACER
//			UN MÉTODO TOARRAY QUE ME DEA O ARRAY QUE NECESITO
			
			
			
	         String[] myList = new String[] {"Hello","World","Foo","Bar"};   
	         
	         ArrayList<String> your_array_list =  oEscaleta.get(0).toArrayList();
//	         your_array_list.add("foo");
//	         your_array_list.add("bar");
	         
	        

	         
	         ListView lv = new ListView(ConfirmacionSpGdocs.this);
//	         lv.setAdapter(new ArrayAdapter<String>(ConfirmacionSpGdocs.this,android.R.layout.simple_list_item_1,myList));
	         ArrayAdapter<String> arrayAdapter =       new ArrayAdapter<String>(ConfirmacionSpGdocs.this,android.R.layout.simple_list_item_1, your_array_list);
	         lv.setAdapter(arrayAdapter);

	         setContentView(lv);
//			


//			setListAdapter(fileList);
			
//			if (renovartoken){
//				String[] array = retornojedi.get(0);
				
//				getThis().setListAdapter(new ArrayAdapter<String>(getThis(),
//		 				android.R.layout.simple_list_item_1, retornojedi.get(0)));
				
				
				
				
//			finish();

				
//				dialog.dismiss();
				
				
				
//				  keyword = retornojedi.get(0)[0];
//			 dialog.dismiss();
//			
//			 
//			  setContentView(R.layout.main);
//			    TextView mResultado = (TextView)findViewById(R.id.texto);   
//		     	  mResultado.setText("Holla "+ keyword);
//		     	  }
//			dialog.dismiss();
			
		}

		  
	  }
	
	
	
	

}
