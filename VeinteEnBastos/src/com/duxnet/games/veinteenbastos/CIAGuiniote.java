package com.duxnet.games.veinteenbastos;

import java.util.Iterator;

public class CIAGuiniote
{
	
	private CMano mano;
	private CJugada jugada;
	private CBaza misBazas;
	private CBaza susBazas;
	private int jugadores;
	private boolean decara;
	private boolean ultimas;
	private boolean arrastre;
	private boolean vueltas;
	private CCarta triunfo;
	
	public CIAGuiniote(CMano m ,CJugada j, CBaza mb,CBaza sb, boolean ult,boolean arr,boolean vue,boolean cara,int ju,CCarta tr)
	{		
		mano=m;
		jugada=j;
		misBazas=mb;
		susBazas=sb;
		ultimas=ult;
		arrastre=arr;
		vueltas=vue;
		decara=cara;
		jugadores=ju;
		triunfo=tr;
			
	}
		
	public CCarta DecidirCarta()
	{
		CCarta rtn=null;
		if(decara && jugada.size()>0)
		{		
		}				
		return rtn;	
	}
	private CCarta TengoCartaMayor(CCarta c)
	{
		CCarta rtn=null;
		@SuppressWarnings("unused")
		Iterator<CCarta> it=mano.iterator();
		if(c.getPaloCarta()!=triunfo.getPaloCarta())
		{
			if(c.getValorCarta()>0)
			{
				if(ultimas)
				{
					
				}
				else
				{
					while
					
				}
			}
			else
			{
				if(ultimas)
				{	
					
				}
				else
				{
					
				}												
			}
		}		
		return rtn;
	}
}
