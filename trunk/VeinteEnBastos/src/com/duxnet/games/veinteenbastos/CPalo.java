package com.duxnet.games.veinteenbastos;
import java.util.Iterator;

import android.graphics.Bitmap;
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
		inicializaPalo();
	}
	public CPalo()
	{		
		inicializaPalo();
	}
	private void inicializaPalo()
	{
		 setAs(0);
		 setTres(0);
		 setSota(0);
		 setCaballo(0);
		 setRey(0);
		 setMedianas(0);
		 setPequenas(0);
		 setHaycante(false);
		 setQuedanporsalir(10);
	}	
	public void rellenaDatos(CCarta carta,int valor)
	{
		 setQuedanporsalir(getQuedanporsalir()-1);
		 switch(carta.getECarta())
		 {
		 	case AS:
		 		setAs(valor);	
		 	break;
		 	case TRES:
		 		setTres(valor);
		 	break;
		 	case REY:
		 		setRey(valor);
		 		if(getSota()==1)
		 			setHaycante(true);		 	 
		 	break;
		 	case SOTA:
		 		setSota(valor);
			 	 if(getRey()==1)
			 		setHaycante(true);				 			 
			break;
		 	case CABALLO:
		 		setCaballo(valor);
		 		if(valor==1)
		 			setMedianas(getMedianas()+1);
		 		else if(valor==2)
		 			setMedianas(getMedianas()-1);
		 	break;
		 	default:
		 		if(valor==1)
		 		{
		 			setPequenas(getPequenas()+1);
		 			setMedianas(getMedianas()+1);
		 		}
		 		else if(valor==2)
		 		{
		 			setPequenas(getPequenas()-1);
		 			setMedianas(getMedianas()-1);
		 		}		 				 		
		 	break;	 
		 }	 	
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
