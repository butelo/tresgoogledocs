package com.digitalnatura.tresgoogledocs;

//proyeto vacio, a clase que fai android cando creas un proyecto
//package com.digitalnatura.proyectovacio;

import java.io.IOException;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.digitalnatura.dosgoogledocs.AuxSpreadsheet;
//import com.digitalnatura.filemanager.R;
//import com.digitalnatura.dosgoogledocs.BbddHelper;
import com.digitalnatura.tresgoogledocs.ListaFollas.InitTask;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;

public class ListaFollas extends ListActivity {
	/** Called when the activity is first created. */

	private static final String AUTH_TOKEN_TYPE = "oauth2:https://spreadsheets.google.com/feeds";

	private static final String PREF = "MyPrefs";
	private static final int DIALOG_ACCOUNTS = 0;
	private static final int MENU_ACCOUNTS = 0;
	public static final int REQUEST_AUTHENTICATE = 0;

	private static Context mContext;

//	private static final AlertDialog dialog = null;
	  

	private final HttpTransport transport = AndroidHttp
			.newCompatibleTransport();

	public String authToken;
	public GoogleAccountManager accountManager;
	  GoogleAccessProtectedResource accessProtectedResource = new GoogleAccessProtectedResource(null);

	private String TAG  = "oauth2";

	List<String[]> retornojedi = null;

	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (chequearConexion()) {
			// tv = (TextView)findViewById(R.id.textView1);
			// text = "probando 1, 2, 3";
			// setContentView(R.layout.main);
			// TextView mResultado = (TextView)findViewById(R.id.texto);
			// mResultado.setText("Hello, 2.2");
			// initTask = new InitTask();
			// initTask.execute(this);
			accountManager = new GoogleAccountManager(this);
			// Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);
			mContext = this;

			gotAccount(false);
			
			dialog = new ProgressDialog(ListaFollas.this);

			  
			dialog.setMessage(getString(R.string.listafollas));
			dialog.show();

		} else {

			Toast.makeText(ListaFollas.this, getText(R.string.nointernet),
					Toast.LENGTH_SHORT).show();
			ListaFollas.this.finish();

		}

	}
	
	
	void gotAccount(boolean tokenExpired) {
	    SharedPreferences settings = getSharedPreferences(PREF, 0);
	    String accountName = settings.getString("accountName", null);
	    Account account = accountManager.getAccountByName(accountName);
	    if (account != null) {
	    	
	    	
	    	
	      if (tokenExpired) {
	    	  
	    	  
	    	  
	    	  
	        accountManager.invalidateAuthToken(accessProtectedResource.getAccessToken());
	        accessProtectedResource.setAccessToken(null);
	      }
	      gotAccount(account);
	      return;
	    }
	    showDialog(DIALOG_ACCOUNTS);
	  }



	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ACCOUNTS:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select a Google account");
			final Account[] accounts = accountManager.getAccounts();
			final int size = accounts.length;
			String[] names = new String[size];
			for (int i = 0; i < size; i++) {
				names[i] = accounts[i].name;
			}
			builder.setItems(names, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

					gotAccount(accounts[which]);

				}
			});
			return builder.create();
		}
		return null;
	}

	
	
	
	void gotAccount(final Account account) {
	    SharedPreferences settings = getSharedPreferences(PREF, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putString("accountName", account.name);
	    editor.commit();
	    accountManager.manager.getAuthToken(
	        account, AUTH_TOKEN_TYPE, true, new AccountManagerCallback<Bundle>() {

	          public void run(AccountManagerFuture<Bundle> future) {
	            try {
	              Bundle bundle = future.getResult();
	              if (bundle.containsKey(AccountManager.KEY_INTENT)) {
	            	  
	                Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
	                intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_NEW_TASK);
	                startActivityForResult(intent, REQUEST_AUTHENTICATE);
	                
	              } else if (bundle.containsKey(AccountManager.KEY_AUTHTOKEN)) {
	                accessProtectedResource.setAccessToken(
	                    bundle.getString(AccountManager.KEY_AUTHTOKEN));
	                authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
	                
	                SharedPreferences settings = getSharedPreferences(PREF, 0);
	        	    SharedPreferences.Editor editor = settings.edit();
	        	    editor.putString("token", authToken);
	        	    editor.commit();
	                
	                
	        	    
	                
	                
	                
	                
	                onAuthToken();
	              }
	            } catch (Exception e) {
	              handleException(e);
	            }
	          }
	        }, null);
	  }

	

	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    switch (requestCode) {
	      case REQUEST_AUTHENTICATE:
	        if (resultCode == RESULT_OK) {
	          gotAccount(false);
	        } else {
	          showDialog(DIALOG_ACCOUNTS);
	        }
	        break;
	    }
	  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_ACCOUNTS, 0, "Switch Account");
	    return true;
	  }

	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case MENU_ACCOUNTS:
	        showDialog(DIALOG_ACCOUNTS);
	        return true;
	    }
	    return false;
	  }


	
	
	  void handleException(Exception e) {
		    e.printStackTrace();
		    if (e instanceof HttpResponseException) {
		      HttpResponse response = ((HttpResponseException) e).getResponse();
		      int statusCode = response.getStatusCode();
//		      authToken = statusCode+"";
		      try {
		        response.ignore();
		      } catch (IOException e1) {
		        e1.printStackTrace();
		      }
		      // TODO(yanivi): should only try this once to avoid infinite loop
		      if (statusCode == 401) {
		    	  
		    	  
		        gotAccount(true);
		        Log.e(TAG  , "fixo esto cagondiola");
		        return;
		      }
		      try {
		        Log.e(TAG  , response.parseAsString());
		      } catch (IOException parseException) {
		        parseException.printStackTrace();
		      }
		    }
		    Log.e(TAG, e.getMessage(), e);
		  }
	  

	  
	  
	  
	
	  void onAuthToken() {
//		    new GoogleAccessProtectedResource(authToken) {
//
//		      @Override
//		      protected void onAccessToken(String accessToken) {
//		        gotAccount(true);
//		      }
//		    };
//		    
		    
		    
		    
		    
//		    buzz.setAccessToken(authToken);
		    
//		    TextView mResultado = (TextView)findViewById(R.id.texto);
//	     	  mResultado.setText("Hello, 2.2");
//		    Toast.makeText(this, "You selected: " + authToken, Toast.LENGTH_LONG).show();
		    
		   
		    
		    
//		    final ListView activitiesListView = (ListView) findViewById(R.id.activities);
		    // TODO(yanivi): refresh activities
		    // TODO(yanivi): set logging level
//		    registerForContextMenu(activitiesListView);
		    
		    
			
		  
	     	 InitTask initTask = new InitTask();
	     	initTask.execute(this);
		  }
	
	
	
	  protected class InitTask extends AsyncTask<Context, Integer, String> {

		  private String keyword;
		 
		private boolean renovartoken;



		@Override
		    protected void onPreExecute() {
			
		
				
			 
			  
				     
		  }

		@Override
		protected String doInBackground(Context... params) {
			
			// TODO Auto-generated method stub
			 AuxSpreadsheet obxXestorFollas = new AuxSpreadsheet();
			    
			    
			    
			    try {
			    	
	     	    	retornojedi= obxXestorFollas.xestorFollas( "", authToken, null);
	     	    	 renovartoken= true;
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					renovartoken=false;
					   handleException(e);
					   return null;
					
				}
			    
			   
			    
			   
			    
			     return null;
			     
		
			     
			    
			   
			   
			    
			
		}
		
		  
		@Override
		protected void onPostExecute(String result) {
			
			 

			
		

//			setListAdapter(fileList);
			
			if (renovartoken){
//				String[] array = retornojedi.get(0);
				
				getThis().setListAdapter(new ArrayAdapter<String>(getThis(),
		 				android.R.layout.simple_list_item_1, retornojedi.get(0)));
				
				
				
				
				
				
				dialog.dismiss();
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
	  
	  
	  @Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			// Get the item that was clicked
//			Object o = this.getListAdapter().getItem(position);
			String keyword = retornojedi.get(1)[position];
			String titulo = retornojedi.get(0)[position];
			
			
			
			
			
			
			Intent i = new Intent(this, ConfirmacionSpGdocs.class);
			// Esta información se recuperará en el objeto Bundle de onCreate
			i.putExtra("urlnova", keyword);
			i.putExtra("titulo", titulo);
			
			startActivity(i);
			
			
			
			
		}
	
	
	
	
	
	
	private boolean chequearConexion() {

		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		}
		return connected;

	}


	public ListActivity getThis() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public static Context getContext() {

		return mContext;
	}

}