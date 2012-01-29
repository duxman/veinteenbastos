package com.duxnet.games.veinteenbastos;

import android.graphics.Point;

public class GlobalVar 
{
	private Point m_DimPantalla;
	private Point m_DimCartas;
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

}

