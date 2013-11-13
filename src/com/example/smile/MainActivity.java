package com.example.smile;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static final String PATH = "jokes.xml";    
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";    
    static final String KEY_DESC = "description";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		final TextView txt_test = (TextView) findViewById(R.id.txt);
		final Button button_next = (Button) findViewById(R.id.btn_next);
		final TextView txt_number = (TextView) findViewById(R.id.txt_number);
		final TextView txt_title = (TextView) findViewById(R.id.txt_title);
		
		final XMLParser parser = new XMLParser();		
		AssetManager manager = getAssets();
		InputStream stream;
		try {			
			stream = manager.open(PATH);		
			Document doc = parser.getDocument(stream);        			
	        final NodeList nodelist = doc.getElementsByTagName(KEY_ITEM);
		
		txt_number.setText(getID(nodelist, 0));
		txt_title.setText(getTitle(nodelist,0,parser));
		txt_test.setText(getJoke(nodelist,0,parser));
		
		
		
		button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String sIndex = txt_number.getText().toString(); 
    	        sIndex = sIndex.substring(0,sIndex.indexOf('/'));
    	        int index = Integer.parseInt(sIndex);    	        
    	        txt_number.setText(getID(nodelist, index));
    			txt_title.setText(getTitle(nodelist, index, parser));
    			txt_test.setText(getJoke(nodelist, index, parser));
            }
        });
		
		} catch(IOException e1) {
			txt_number.setText("Exeption");
			txt_title.setText("Exeption");
			txt_test.setText("testing not good " + e1);
		}
		
	}

	public final String getID(NodeList nodelist,int index) {
		index = index % nodelist.getLength();
		return index + 1 + "/" + nodelist.getLength();
	}
	
	public final String getTitle(NodeList nodelist,int index, XMLParser parser) {
		index = index % nodelist.getLength();
		Element e = (Element) nodelist.item(index);
		return parser.getValue(e, KEY_NAME);
	}
	
	public final String getJoke(NodeList nodelist,int index, XMLParser parser) {
		index = index % nodelist.getLength();
		Element e = (Element) nodelist.item(index);
		return parser.getValue(e, KEY_DESC);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
