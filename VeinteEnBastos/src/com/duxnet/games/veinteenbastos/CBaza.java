package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CBaza
{
	private List<CCarta> Baza;
	
	public void setBaza(List<CCarta> baza)
	{
		Baza = baza;
	}
	public List<CCarta> getBaza() 
	{
		return Baza;
	}
	public CBaza()
	{
		Baza=new ArrayList<CCarta>();
	}
	public boolean add(CCarta c)
	{
		return Baza.add(c);
	}
	public int CuentaPuntos(boolean diezUltimas)
	{
		int rtn=0;
		rtn=(diezUltimas)?10:0;		
		Iterator<CCarta> itr = Baza.iterator();
        while(itr.hasNext())
        {        	
        	CCarta c = (CCarta)itr.next();
        	rtn+=c.getValor();      
        }		
		return rtn;			
	}

}