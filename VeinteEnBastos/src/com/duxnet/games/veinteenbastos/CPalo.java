package com.duxnet.games.veinteenbastos;
import java.util.Iterator;

import android.graphics.Bitmap;
import com.duxnet.games.veinteenbastos.IA.*;
import com.duxnet.games.veinteenbastos.enums.eCarta;
import com.duxnet.games.veinteenbastos.enums.ePalo;



public class CPalo extends CListaCartas implements Comparable<Object>
{   
	private int PaloBaraja; 
	private Bitmap bmpPalo;	
	public CPalo(int Palo,Bitmap palo)
	{	
		setPaloBaraja(Palo);		
		bmpPalo=palo;	
	}
	public CPalo()
	{			
	}
	public boolean TieneECarta(eCarta ec)
	{
		boolean rtn=false;
		Iterator<CCarta> it=iterator();
		while(!rtn && it.hasNext())
		{
			if(ec==it.next().getECarta())
				rtn=true;			
		}		
		return rtn;
	}
	public Bitmap getBitmap()
	{
		return bmpPalo;
	}
	public int getPaloBaraja() {
		return PaloBaraja;
	}
	public void setPaloBaraja(int paloBaraja) {
		PaloBaraja = paloBaraja;
	}
	public ePalo getEPalo() 
	{
		return ePalo.getPalo(PaloBaraja);
	}	
	public int compareTo(Object another) 
	{
		if (this.size()== ((CPalo) another).size())
            return 0;
        else if ((this.size()) > ((CPalo) another).size())
            return 1;
        else
            return -1;
	}	
}
