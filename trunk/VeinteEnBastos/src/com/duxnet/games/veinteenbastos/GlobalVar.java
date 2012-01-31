package com.duxnet.games.veinteenbastos;

import android.graphics.Bitmap;
import android.graphics.Point;

public class GlobalVar 
{
	private Point m_DimPantalla;
	private Point m_DimCartas;
	private CBaraja m_Baraja;
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

}

