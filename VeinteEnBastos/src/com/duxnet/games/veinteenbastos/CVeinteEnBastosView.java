package com.duxnet.games.veinteenbastos;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CVeinteEnBastosView extends SurfaceView implements SurfaceHolder.Callback
{

	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	private int AltoCarta;
	private int AnchoCarta;
	
	private CBaraja BarajaCompleta;
	private CMano Mano;
	private Bitmap bmpBarajaCompleta;
	private Bitmap bmpFondoCarta;
	private SurfaceHolder holder;
	//private GameLoopThread gameLoopThread;	
	private int cartaAct;
	
	public CVeinteEnBastosView(Context context) 
	{
		super(context);
	

		//gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
        holder.addCallback(this);
        CargarGraficos();
        cartaAct=0;
	}
	public void CargarGraficos()
	{
		bmpBarajaCompleta = BitmapFactory.decodeResource(getResources(),R.drawable.baraja);
		bmpFondoCarta=BitmapFactory.decodeResource(getResources(),R.drawable.detras);
		AltoCarta=(bmpBarajaCompleta.getHeight() / BMP_ROWS);
		AnchoCarta=(bmpBarajaCompleta.getWidth() / BMP_COLUMNS);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
	  BarajaCompleta= new CBaraja(bmpBarajaCompleta,bmpFondoCarta);
	  Mano=new CMano();
	  BarajaCompleta.Repartir(Mano);
	  
	  render();
	  /*gameLoopThread.setRunning(true);
      gameLoopThread.start();
      */            
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		/*boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) 
        {
               try 
               {
                     gameLoopThread.join();
                     retry = false;
               } 
               catch (InterruptedException e) 
               {
               }
        }
		*/
	}
	private void render()
	{	
		render(null);
	}
	private void render(CCarta carta)
	{	
		Canvas c = null;      	   
        try 
        {
     	   	    
     	   	  c = holder.lockCanvas();
               synchronized (holder) 
               {
             	  this.onDraw(c,carta);
             	  
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
        boolean tocada=false;
        CCarta c;
		synchronized (getHolder()) 
        {					
			if(BarajaCompleta.size()>0 && BarajaCompleta.DameUltimaCarta(false).Tocada(x, y))
	    	{
	    		tocada=true;
	    		render(BarajaCompleta.DameUltimaCarta(true));
	    	}
			Iterator <CCarta> iterador= Mano.getCartas().iterator();  		  
		    while (!tocada && iterador.hasNext()) 
		    {  		    	
		    	c=iterador.next();
		    	if(c.Tocada(x, y))
		    	{
		    		tocada=true;
		    		Mano.getCartas().remove(c);
		    		render(c);
		    	}
		    }
		    if(!tocada)	render();
        }
		return super.onTouchEvent(event);
    }
    protected void onDraw(Canvas canvas,CCarta carta) 
    {      
    	int posX=10;
    	int posY=10;
    	canvas.drawColor(Color.GREEN);
    	
    	if(Mano.getCartas().size()>0)
    	{
    		Mano.OrdenarMano();
    		Mano.Pintar(true,canvas,AltoCarta,AnchoCarta,posX,posY);
    	}
    	if(BarajaCompleta.size()>0)
    	{
    		posY=posY+AltoCarta+10;    	
    		BarajaCompleta.Pintar(false,canvas,AltoCarta,AnchoCarta,posX,posY);
    	}
    	if(carta!=null)
    	{
    		posX=posX+AnchoCarta+90;  
    		carta.Pintar(true, canvas,AltoCarta,AnchoCarta,posX,posY);
    	}
    	/*posX=(posX+15)+(BarajaCompleta.getAnchoCarta());
    	canvas.drawBitmap(BarajaCompleta.getCarta(cartaAct).getImagenCarta(), posX,posX+BarajaCompleta.getAnchoCarta()+150 , null);    	
    	canvas.drawText(BarajaCompleta.getCarta(cartaAct).getLog(), posX,posX+BarajaCompleta.getAnchoCarta()+150, new Paint());
    	Log.w("Loger", BarajaCompleta.getCarta(cartaAct).getLog());
    	*/
    	//BarajaCompleta.getCarta(cartaAct);    	
    	//canvas.drawBitmap(BarajaCompleta.getCarta(cartaAct).getImagenCarta());
    	
    	//canvas.drawBitmap(BarajaCompleta.getPalo(c.getPaloCarta()).getBitmap(), 10, 10, null);
    	    	      
    }
}
