package com.duxnet.games.veinteenbastos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class VeinteEnBastos extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      
        setContentView(new CVeinteEnBastosView(this,getWindowManager().getDefaultDisplay().getHeight(),getWindowManager().getDefaultDisplay().getWidth()));       
		
    }
}