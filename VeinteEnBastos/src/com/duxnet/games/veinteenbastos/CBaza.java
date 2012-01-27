package com.duxnet.games.veinteenbastos;

import java.util.Iterator;

public class CBaza extends CListaCartas
{
	private int m_puntos;
	public CBaza()
	{
		super();
	}
	
	public int CuentaPuntos(boolean diezUltimas)
	{
		int rtn=0;
		
		rtn=(diezUltimas)?10:0;		
		Iterator<CCarta> itr = iterator();
        
		while(itr.hasNext()) itr.next().getValorCarta();
		
		return rtn;			
	}

	public int getPuntos() 
	{
		m_puntos=CuentaPuntos(false);
		return m_puntos;
	}

	public void setPuntos(int puntos) 
	{
		m_puntos = puntos;
	}

}