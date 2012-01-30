package com.duxnet.games.veinteenbastos;

import com.duxnet.games.veinteenbastos.enums.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
	
	public CJuego(Bitmap bc,Bitmap bf,Bitmap bt,int jugadores)	
	{		
		
		int turno = 0;
		m_DimPantalla=new Point(GlobalVar.getInstance().getDimPantalla());
		m_bmpTapete=bt;
		setBaraja(new CBaraja(bc,bf));
		
		m_DimCartas = new Point((bc.getWidth() / BMP_COLUMNS),(bc.getHeight() / BMP_ROWS));
		
		GlobalVar.getInstance().setDimCartas(m_DimCartas);
				
		m_Jugadores=new ArrayList<CJugador>();
		if(jugadores==2)
		{
			m_Jugadores.add(new CJugador("Jugador1", 1, false,ePosicion.ARRIBA));
			m_Jugadores.add(new CJugador("Jugador2", 2, true,ePosicion.ABAJO));
			turno=(int) (Math.random()*1);
		}
		else if(jugadores==4)
		{					
			m_Jugadores.add(new CJugador("Jugador1", 1, false,ePosicion.ARRIBA));
			m_Jugadores.add(new CJugador("Jugador2", 2, false,ePosicion.IZQUIERDA));
			m_Jugadores.add(new CJugador("Jugador3", 3, false,ePosicion.ABAJO));
			m_Jugadores.add(new CJugador("Jugador4", 4, false,ePosicion.DERECHA));
			turno=(int) (Math.random()*3);			
		}	
		repartir(turno);
	}

	public void Tocado(float x,float y)
	{		
		boolean tocado=false;
		CCarta c = null;		
		for(int Jugador=0;Jugador<m_Jugadores.size() && !tocado;Jugador++)
		{
			c=m_Jugadores.get(Jugador).getMano().Tocada(x, y);
			if(c!=null && m_Jugadores.get(Jugador).isReal())
			{
				tocado=true;				
			}
		}		
		if(!tocado && getBaraja().Tocada(x, y)!=null)
		{
			c=getBaraja().DamePrimeraCarta(false);
			tocado=true;
		}
		
		if(!tocado && getCartaTriunfo().Tocada(x, y))
		{
			c=getCartaTriunfo();
			tocado=true;
		}
		
		if(c!=null)	c.setMarcada(true);
		
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
				
		if(getBaraja().size()>0)
			getBaraja().Pintar(false, canvas, m_DimCartas.y, m_DimCartas.x, x, y);					
	
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
		int JugadorTurno=quien+1;
		for(int turno=0;turno<2;turno++)
		{						
			for(int Jugador=0;Jugador<m_Jugadores.size();Jugador++)
			{
				
				if(JugadorTurno>=m_Jugadores.size()) JugadorTurno=0;													
				for(int carta=0;carta<3;carta++)
				{
					m_Jugadores.get(JugadorTurno).getMano().add(getBaraja().DamePrimeraCarta(true));					
				}
				JugadorTurno++;
			}		
		}
		setTriunfo(getBaraja().DamePrimeraCarta(true));
	}
	public void CortarBaraja()
	{	
		int iPunto = (int)(Math.random()*40);		
		int Contador=0;		
		for(int i=iPunto;i<40;i++)
		{
			getBaraja().get(i).setPesoOrden(Contador);
			Contador++;			
		}
		for(int i=0;i<iPunto;i++)
		{
			getBaraja().get(i).setPesoOrden(Contador);
			Contador++;			
		}				
		getBaraja().Ordenar();
	}
	public void Barajar()
	{		

		Iterator<CCarta> it = getBaraja().iterator();
	    while(it.hasNext())
	    {
	    	it.next().DamePesoOrdenAleatorio();	    	     
	    }
	    getBaraja().Ordenar();
	}	
	
	public void jugada(int Mano)
	{		
	}
	public void setCartaTriunfo(CCarta cartaTriunfo) 
	{		
		setTriunfo(cartaTriunfo);
	}
	public CCarta getCartaTriunfo() 
	{
		return getTriunfo();
	}
	private CBaraja getBaraja() {
		return m_baraja;
	}
	private void setBaraja(CBaraja baraja) {
		m_baraja = baraja;
	}
	private CCarta getTriunfo() {
		return m_triunfo;
	}
	private void setTriunfo(CCarta triunfo) {
		m_triunfo = triunfo;
	}
}