package com.duxnet.games.veinteenbastos;

import java.util.Iterator;
import android.graphics.Canvas;

public class CMano  extends CListaCartas
{
	public CMano()
	{
		super();
	}
	public CCarta tirarCarta(int i)
	{
		CCarta rtn=null;
		rtn=get(i);
		if(rtn!=null) remove(i);
		return rtn;		
	}
	public CCarta TirarCarta()
	{
		return tirarCarta(DecidirCarta());					
	}
	private int DecidirCarta() 
	{
		int rtn=0;
		///---------------------------------
		///---------------------------------
		/// AQUI hay que implementar el metodo de que carta tengo que tirar 
		/// No estoy seguro si debe de estar aqui o en la clase juego para la IA
		/// Posiblemente cambie de sitio
		///---------------------------------
		///---------------------------------
		return rtn;	
	}	
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y,boolean Horizontal)
	{
		Pintar(vista, canvas, Alto, Ancho, X, Y, Ancho/2, Horizontal, false);
	}
	
	public void Ordenar() 
	{
		Iterator <CCarta> iterador= iterator();  			  
		while (iterador.hasNext()) 
		{  
			CCarta c=iterador.next();
			c.setPesoOrden(c.getOrdinalCarta());	    			    	   		     
	    }  				
		super.Ordenar();
	}		
	
}