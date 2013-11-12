package com.example.smile;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.renderscript.Element;
import android.app.Activity;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static final String PATH = "jokes.xml";
    // XML node keys
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";    
    static final String KEY_DESC = "description";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Let the magic begin
		// double trouble
		//coment
		
		final TextView txt_test = (TextView) findViewById(R.id.txt);
		final Button button_next = (Button) findViewById(R.id.btn_next);
		
		button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	XMLParser parser = new XMLParser();		
        		AssetManager manager = getAssets();
        		InputStream stream;
        		try{
        			stream = manager.open(PATH);		
        			Document doc = parser.getDocument(stream);        			
        	        NodeList nl = doc.getElementsByTagName(KEY_ITEM);	
        	        
        	        Element e = (Element) nl.item(0);        	        
        	        String joke = parser.getValue(e, KEY_DESC);
        	        
        	        //Element e = (Element) nl.item(0);
        	        
        	        //txt_test.setText(parser.getValue(doc.getElementById(1), KEY_DESC));
        	                

        		}catch(IOException e1){txt_test.setText("testing not good " + e1);}            	
            }
        });
		
		
                
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
