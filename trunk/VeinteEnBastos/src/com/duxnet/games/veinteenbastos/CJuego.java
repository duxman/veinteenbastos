package com.duxnet.games.veinteenbastos;

import java.util.Iterator;
import com.duxnet.games.veinteenbastos.enums.ePalo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CJuego 
{
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	
	private int AltoCarta;
	private int AnchoCarta;
	private int AltoScreen;
	private int AnchoScreen;
	
	//private Bitmap bmpBarajaCompleta;
	//private Bitmap bmpFondoCarta;
	private Bitmap bmpTapete;
	
	private CPareja ParejaPar;
	private CPareja ParejaImpar;	
	private CBaraja baraja;
	private CCarta CartaTriunfo;
	
	
	public CJuego(Bitmap bc,Bitmap bf,Bitmap bt,int altoscr,int anchoscr)	
	{		
		AltoScreen=altoscr;
		AnchoScreen=anchoscr;
		bmpTapete=bt;
		baraja= new CBaraja(bc,bf);
		AltoCarta=(bc.getHeight() / BMP_ROWS);
		AnchoCarta=(bc.getWidth() / BMP_COLUMNS);
		
		setParejaPar(new CPareja(new CJugador("Jugador2", 2, false),new CJugador("Jugador4",4,false)));
		setParejaImpar(new CPareja(new CJugador("Jugador1", 1, false),new CJugador("Jugador3",3,false)));
		baraja=new CBaraja(bc,bf);	
		NuevoJuego();
	}
	public void NuevoJuego()
	{
		this.repartir(1);
		
	}
	private void PintarFondo(Canvas canvas)
	{
		canvas.drawColor(Color.rgb(51,102,51));
    	canvas.drawBitmap(bmpTapete, 0,0 , null);				
	}
	private void PintarMazo(Canvas canvas)
	{
		int x= (AnchoScreen/2)-(AnchoCarta*2)+50;
		int y= (AltoScreen/2)-(AltoCarta);
		if(baraja.size()>0)
		{
			baraja.Pintar(false, canvas, AltoCarta, AnchoCarta, x, y);
			Paint p = new Paint();
			p.setTextSize(10);
			p.setFakeBoldText(true);
			canvas.drawText(String.valueOf(baraja.size()), x+20,y+20, p);			
		}
		if(getCartaTriunfo()!=null)	
			getCartaTriunfo().Pintar(true, canvas, AltoCarta, AnchoCarta, x+AnchoCarta, y);		
		
	}	
	public CCarta colision(float x,float y)
	{
		CCarta rtn=null;
		if(getCartaTriunfo().Tocada(x,y))
		{
			rtn=getCartaTriunfo();
		}
		else
		{
			if(baraja.Tocada(x, y))
			{
				rtn=baraja.DameUltimaCarta(false);			
			}						
			else
			{
				CCarta c;
				c=getParejaPar().getJugadorA().getMano().Tocada(x, y);
				if(c!=null)
				{
					rtn=c;									
				}
				c=getParejaPar().getJugadorB().getMano().Tocada(x, y);
				if(c!=null)
				{
					rtn=c;					
				}
				c=getParejaImpar().getJugadorA().getMano().Tocada(x, y);
				if(c!=null)
				{
					rtn=c;
				}
				c=getParejaImpar().getJugadorB().getMano().Tocada(x, y);
				if(c!=null)
				{
					rtn=c;
				}					
			}
		}					
		return rtn;
	}
	public void Pintar(Canvas canvas)
	{	
		PintarFondo(canvas);
		PintarMazo(canvas);
		PintarMano(canvas,getParejaPar().getJugadorA().getMano(),1);
		PintarMano(canvas,getParejaPar().getJugadorB().getMano(),2);
		PintarMano(canvas,getParejaImpar().getJugadorA().getMano(),3);
		PintarMano(canvas,getParejaImpar().getJugadorB().getMano(),4);	    	
		
	}
	public void PintarMano(Canvas canvas,CMano mano,int pos)
	{ 		
		int oX=0,oY=0;
		boolean Horizontal=false;
		switch(pos)
		{
			case 1:
				oX=(int)((AnchoScreen/2)-(((mano.getCartas().size()*AnchoCarta))/2))+25;					
				oY=10;		
				Horizontal=true;
			break;			
			case 2:			
				oX=10;
				oY=(int)((AltoScreen/2)-((mano.getCartas().size()*AltoCarta)/2))+25;
				Horizontal=false;
			break;
			case 3:
				oX=(int)((AnchoScreen/2)-((mano.getCartas().size()*AnchoCarta)/2))+25;
				oY=(AltoScreen-AltoCarta)-30;				
				Horizontal=true;
			break;
			case 4:
				oX=(AnchoScreen-AnchoCarta)-10;
				oY=(int)((AltoScreen/2)-((mano.getCartas().size()*AltoCarta)/2))+25;
				Horizontal=false;
			break;
		
		}
		mano.Pintar(true, canvas, AltoCarta, AnchoCarta, oX, oY,Horizontal);
		
		
	}
	
	public void setParejaPar(CPareja parejaPar) 
	{
		ParejaPar = parejaPar;
	}
	public CPareja getParejaPar() 
	{
		return ParejaPar;
	}
	public void setParejaImpar(CPareja parejaImpar) 
	{
		ParejaImpar = parejaImpar;
	}
	public CPareja getParejaImpar() 
	{
		return ParejaImpar;
	}
	private CPareja quePareja(int jugador)
	{
		if (jugador%2!=0)
			return ParejaImpar;
		  else
			  return ParejaPar;
	}
	private CJugador queJugador(int jugador)
	{
		if (jugador%2!=0)
		{
			if(jugador==1)
				return ParejaImpar.getJugadorA();
			else
				return ParejaImpar.getJugadorB();
		}			
		else
		{
			if(jugador==2)
				return ParejaPar.getJugadorA();
			else
				return ParejaPar.getJugadorB();
		}
	}
	public void repartir(int quien)	
	{	
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<6;j++)
			{
				queJugador(i+1).getMano().AddCarta(baraja.DamePrimeraCarta(true));
			}						
		}				
		setCartaTriunfo(baraja.DamePrimeraCarta(true));			
	}
	public void jugada(int Mano)
	{
		int jugador=Mano;
		CJugada j = new CJugada(ePalo.getPalo(getCartaTriunfo().getPalo()),Mano);
		
		for(int i =1;i<5;i++)
		{
			jugador=(jugador>4)?(i+jugador-5):(i+jugador-1);	
			j.Add(jugador, queJugador(Mano).getMano().TirarCarta());
			jugador++;
		}
		int idquiengana=j.QuienGana();
		Iterator<CCarta> itr = j.getCartas().iterator();
        while(itr.hasNext())
        {
        	CCarta c = (CCarta)itr.next();
        	quePareja(idquiengana).getBaza().add(c);   
        }
        quePareja(idquiengana).setPuntos(quePareja(idquiengana).getBaza().CuentaPuntos(false));   
	}
	public void setCartaTriunfo(CCarta cartaTriunfo) 
	{
		CartaTriunfo = cartaTriunfo;
	}
	public CCarta getCartaTriunfo() 
	{
		return CartaTriunfo;
	}
}