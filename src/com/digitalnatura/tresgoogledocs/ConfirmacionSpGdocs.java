package com.digitalnatura.tresgoogledocs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ConfirmacionSpGdocs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.confirmescaleta);

        
        String titulo = this.getIntent().getExtras().get("titulo").toString();
        String keyword = this.getIntent().getExtras().get("urlnova").toString();

        
        Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG).show();
	}
	

}
