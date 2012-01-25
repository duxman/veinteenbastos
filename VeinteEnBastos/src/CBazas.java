/*package com.veinteenbastos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CBazas 
{
	private List<CCartas> Baza;
	
	public void setBaza(List<CCartas> baza)
	{
		Baza = baza;
	}
	public List<CCartas> getBaza() 
	{
		return Baza;
	}
	public CBazas()
	{
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
*/