package com.duxnet.games.veinteenbastos;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;

public class GlobalVar
{
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	private Point m_DimPantalla;
	private Point m_DimCartas;
	private CJuego m_juego;
	private CBaraja m_Baraja;
	private CJugada m_Jugada;	
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
	public CJugada getJugada() 
	{
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
		setBaraja(bc, bf);		
		m_juego = new CJuego(bc, bf, bt, jugadores);						
	}
}

