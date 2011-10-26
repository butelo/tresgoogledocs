package com.digitalnatura.tresgoogledocs;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class TresGoogleDocsActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView aboutText = (TextView) findViewById(R.id.texto);
        aboutText.setMovementMethod(LinkMovementMethod.getInstance());

    }
}