package com.duxnet.games.veinteenbastos;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CVeinteEnBastosView extends SurfaceView implements SurfaceHolder.Callback,Runnable
{
	private int AltoScreen;
	private int AnchoScreen;
	
	private CJuego Juego;
	
	private Bitmap bmpBarajaCompleta;
	private Bitmap bmpFondoCarta;
	private Bitmap bmpTapete;
	private SurfaceHolder holder;
	private GlobalVar m_global;
	
	public CVeinteEnBastosView(Context context,int altoscr,int anchoscr) 
	{
		super(context);		
		init( altoscr, anchoscr);		
	}
		
	private void init(int altoscr,int anchoscr)
	{
		holder = getHolder();
        holder.addCallback(this);
        m_global=GlobalVar.getInstance();
        
        this.AnchoScreen = anchoscr;
		this.AltoScreen = altoscr;		
        GlobalVar.getInstance().setDimPantalla(new Point(AnchoScreen,AltoScreen));		
		CargarGraficos();	
		m_global.CreateJuego(bmpBarajaCompleta,bmpFondoCarta,bmpTapete,2);
		Juego=m_global.getJuego();

	}
	
	public void CargarGraficos()
	{
		bmpBarajaCompleta = BitmapFactory.decodeResource(getResources(),R.drawable.baraja);
		bmpFondoCarta=BitmapFactory.decodeResource(getResources(),R.drawable.detras);				
		bmpTapete=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tapete),AnchoScreen ,AltoScreen, true);		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) 
	{				
		this.AnchoScreen = width;
		this.AltoScreen = height;
		bmpTapete=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tapete),AnchoScreen ,AltoScreen, true);
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
        
        Juego.Tocado(x, y);        
        render();
        Thread thread = new Thread(this);
        thread.start();
        return super.onTouchEvent(event);
		
    }
    protected void onDraw(Canvas canvas) 
    {      
    	
    	Juego.Pintar(canvas);    	    	 	    	   	    	 
    }

    public void run() 
	{
		// TODO Auto-generated method stub    
    	m_global.getJuego().tirada();
		
		handler.sendEmptyMessage(0);		
	}	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) 
		{
			render();
			
		}
	};
}
