package com.duxnet.games.veinteenbastos;

import com.duxnet.games.veinteenbastos.IA.CIAEstado;

import android.graphics.Bitmap;
import android.graphics.Point;

public class GlobalVar 
{
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	private Point m_DimPantalla;
	private Point m_DimCartas;
	private CJuego m_juego;
	private CBaraja m_Baraja;
	private CJugada m_Jugada;
	private CIAEstado m_Estado;
    private static GlobalVar instance;
    
    

    static 
    {
        instance = new GlobalVar();
    }

    private GlobalVar() 
    {    	
    }

    public static GlobalVar getInstance() 
    {
        return GlobalVar.instance;
    }

	public Point getDimPantalla() 
	{
		return m_DimPantalla;
	}

	public void setDimPantalla(Point dimension) 
	{
		m_DimPantalla = dimension;
	}

	public Point getDimCartas() {
		return m_DimCartas;
	}

	public void setDimCartas(Point dimCartas) {
		m_DimCartas = dimCartas;
	}

	public CBaraja getBaraja() {
		return m_Baraja;
	}

	public void setBaraja(Bitmap bc,Bitmap bf)
	{
		m_Baraja=new CBaraja(bc, bf);
	}
	public void setBaraja(CBaraja baraja) 
	{
		if(m_Baraja==null)
			m_Baraja=new CBaraja(baraja.getBarajaCompleta(), baraja.getReversoCarta());
		else
			m_Baraja=baraja;			
	}

	public CJugada getJugada() {
		return m_Jugada;
	}

	public void setJugada(CJugada jugada) 
	{
		m_Jugada = jugada;
	}

	public CJuego getJuego() 
	{
		return m_juego;
	}

	public void  CreateJuego(Bitmap bc,Bitmap bf,Bitmap bt,int jugadores) 
	{
		Point DimCartas= new Point((bc.getWidth() / BMP_COLUMNS),(bc.getHeight() / BMP_ROWS));
		setDimCartas(DimCartas);
		
		m_juego = new CJuego(bc, bf, bt, jugadores);						
	}

	public CIAEstado getEstado() 
	{
		return m_Estado;
	}

	public CIAEstado CreaEstado()
	{
		m_Estado=new CIAEstado();
		return m_Estado;		
	}
	public void setEstado(CIAEstado estado)
	{
		m_Estado = estado;
	}

}

