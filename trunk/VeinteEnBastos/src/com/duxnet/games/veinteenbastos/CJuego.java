package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.duxnet.games.veinteenbastos.enums.ePalo;
import com.duxnet.games.veinteenbastos.enums.ePosicion;

import android.R.dimen;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class CJuego 
{
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	
	private Bitmap m_bmpTapete;
	
	private Point m_DimCartas;
	private Point m_DimPantalla; 
	
	//private CPareja m_ParejaPar;
	//private CPareja m_ParejaImpar;
	private List<CJugador> m_Jugadores;
	private CBaraja m_baraja;	
	private CCarta  m_triunfo;		
	
	public CJuego(Bitmap bc,Bitmap bf,Bitmap bt)	
	{		
		
		m_DimPantalla=new Point(GlobalVar.getInstance().getDimPantalla());
		m_bmpTapete=bt;
		m_baraja=new CBaraja(bc,bf);
		
		m_DimCartas = new Point((bc.getWidth() / BMP_COLUMNS),(bc.getHeight() / BMP_ROWS));
		
		GlobalVar.getInstance().setDimCartas(m_DimCartas);
		
		
		
		m_Jugadores=new ArrayList<CJugador>();
		m_Jugadores.add(new CJugador("Jugador1", 1, false,ePosicion.ARRIBA));
		m_Jugadores.add(new CJugador("Jugador2", 2, false,ePosicion.IZQUIERDA));
		m_Jugadores.add(new CJugador("Jugador3", 3, false,ePosicion.ABAJO));
		m_Jugadores.add(new CJugador("Jugador4", 4, false,ePosicion.DERECHA));					
		NuevoJuego();
	}
	public void NuevoJuego()
	{
		this.repartir(1);		
	}
		
	public void Pintar(Canvas canvas)
	{	
		PintarFondo(canvas);
		PintarMazo(canvas);
		PintarJugadores(canvas);
		
	}
	private void PintarFondo(Canvas canvas)
	{
		canvas.drawColor(Color.rgb(51,102,51));
    	canvas.drawBitmap(m_bmpTapete, 0,0 , null);				
	}
	private void PintarMazo(Canvas canvas)
	{
		
		
		int x= (m_DimPantalla.x/2)-(m_DimCartas.x);
		int y= (int)(m_DimCartas.y*1.5);
				
		if(m_baraja.size()>0)
			m_baraja.Pintar(false, canvas, m_DimCartas.y, m_DimCartas.x, x, y);					
	
		if(getCartaTriunfo()!=null)	
			getCartaTriunfo().Pintar(true, canvas, m_DimCartas.y, m_DimCartas.x, x+m_DimCartas.x, y);		
		
	}	
	private void PintarJugadores(Canvas canvas)
	{
		Iterator<CJugador> it=m_Jugadores.iterator();
		while(it.hasNext())
		{
			it.next().Pintar(canvas);
		}
	}
	
	
	/*private CPareja quePareja(int jugador)
	{
		if (jugador%2!=0)
			return m_ParejaImpar;
		  else
			  return m_ParejaPar;
	}*/
	/*private CJugador queJugador(int jugador)
	{
		if (jugador%2!=0)
		{
			if(jugador==1)
				return m_ParejaImpar.getJugadorA();
			else
				return m_ParejaImpar.getJugadorB();
		}			
		else
		{
			if(jugador==2)
				return m_ParejaPar.getJugadorA();
			else
				return m_ParejaPar.getJugadorB();
		}
	}*/
	public void repartir(int quien)	
	{
		
		for(int turno=0;turno<2;turno++)
		{						
			for(int Jugador=0;Jugador<m_Jugadores.size();Jugador++)
			{
				for(int carta=0;carta<3;carta++)
				{
					m_Jugadores.get(Jugador).getMano().add(m_baraja.DamePrimeraCarta(true));					
				}
			}		
		}
		m_triunfo=m_baraja.DamePrimeraCarta(true);
	}
	
	public void jugada(int Mano)
	{		
	}
	public void setCartaTriunfo(CCarta cartaTriunfo) 
	{		
		m_triunfo=cartaTriunfo;
	}
	public CCarta getCartaTriunfo() 
	{
		return m_triunfo;
	}
}