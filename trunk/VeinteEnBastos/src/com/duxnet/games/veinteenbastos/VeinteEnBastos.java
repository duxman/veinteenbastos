package com.duxnet.games.veinteenbastos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;

public class VeinteEnBastos extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //View myText = new View(this);        
		//myText.setText("Guiñote");	
		//myText.setId(R.layout.main);
        setContentView(new CVeinteEnBastosView(this,getWindowManager().getDefaultDisplay().getHeight(),getWindowManager().getDefaultDisplay().getWidth()));       
		//addContentView(myText, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		//setContentView(R.layout.main);
        //addContentView(new CVeinteEnBastosView(this),new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));        
    }
}