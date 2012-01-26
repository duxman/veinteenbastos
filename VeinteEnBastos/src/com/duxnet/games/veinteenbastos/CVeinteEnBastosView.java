package com.duxnet.games.veinteenbastos;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CVeinteEnBastosView extends SurfaceView implements SurfaceHolder.Callback
{



	private int AltoScreen;
	private int AnchoScreen;
	
	private CJuego Juego;
	
	private Bitmap bmpBarajaCompleta;
	private Bitmap bmpFondoCarta;
	private Bitmap bmpTapete;
	
	private SurfaceHolder holder;
	
	public CVeinteEnBastosView(Context context,int altoscr,int anchoscr) 
	{
		super(context);
	
		AltoScreen=altoscr;
		AnchoScreen=anchoscr;
		
		holder = getHolder();
        holder.addCallback(this);
        CargarGraficos();
        Juego=new CJuego(bmpBarajaCompleta, bmpFondoCarta,bmpTapete, altoscr, anchoscr);          	  	  	               
	}
	public void CargarGraficos()
	{
		bmpBarajaCompleta = BitmapFactory.decodeResource(getResources(),R.drawable.baraja);
		bmpFondoCarta=BitmapFactory.decodeResource(getResources(),R.drawable.detras);		
		bmpTapete=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tapete),AnchoScreen ,AltoScreen, true);			
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) 
	{
		//TODO Auto-generated method stub		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{ 	 
	  render();	  
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{		
	}	
	private void render()
	{	
		Canvas c = null;      	   
        try 
        {
     	   	    
     	   	  c = holder.lockCanvas();
               synchronized (holder) 
               {
             	  this.onDraw(c);
             	  
               }

        } 
        finally 
        {
               if (c != null) 
               {
            	   holder.unlockCanvasAndPost(c);
               }
        }	
	}
	@Override
    public boolean onTouchEvent(MotionEvent event) 
    {
		float x = event.getX();
        float y = event.getY();
        
        CCarta c=Juego.colision(x, y);
        if(c!=null)
        {
        	if(c.isMarcada())      	
        		c.setMarcada(false);
        	else
        		c.setMarcada(true);        	
        }    
        render();
		return super.onTouchEvent(event);
    }
    protected void onDraw(Canvas canvas) 
    {      
    	
    	Juego.Pintar(canvas);    	    	 	    	   	    	 
    }
}