package com.veinteenbastos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CBazas 
{
	private List<CCartas> Baza;
	private eJugadores BazaPareja;
	
	public void setBaza(List<CCartas> baza)
	{
		Baza = baza;
	}
	public List<CCartas> getBaza() 
	{
		return Baza;
	}
	public void setBazaPareja(eJugadores bazaPareja) {
		BazaPareja = bazaPareja;
	}
	public eJugadores getBazaPareja() {
		return BazaPareja;
	}
	
	public CBazas(eJugadores J)
	{
		BazaPareja=J;
		Baza=new ArrayList<CCartas>();		
	}
	public int CuentaPuntos(boolean diezUltimas)
	{
		int rtn=0;
		rtn=(diezUltimas)?10:0;		
		Iterator<CCartas> itr = Baza.iterator();
        while(itr.hasNext())
        {        	
        	CCartas c = (CCartas)itr.next();
        	rtn+=c.puntos;      
        }		
		return rtn;			
	}

}
