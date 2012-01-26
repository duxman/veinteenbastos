package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CMano 
{
	private List<CCarta> Cartas;
	public CMano()
	{
		setCartas(new ArrayList<CCarta>(6));
	}
	public void setCartas(List<CCarta> cartas) 
	{
		Cartas = cartas;
	}
	public List<CCarta> getCartas() 
	{
		return Cartas;
	}	
	public CCarta TirarCarta(int i)
	{	
		CCarta c=Cartas.get(i);
		Cartas.remove(i);
		return c;		
	}
	public CCarta TirarCarta()
	{
		CCarta c=DecidirCarta();
		Cartas.remove(c);
		return c;				
	}
	public void AddCarta(CCarta carta)
	{
		Cartas.add(carta);
	}
	private CCarta DecidirCarta() 
	{
		return null;
		// TODO Auto-generated method stub		
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y,boolean Horizontal)
	{
		int posX=X;
    	int posY=Y;    	    	
    	for(int i=Cartas.size()-1;i>=0;i--)
    	{
    		if(Horizontal)
    			posX=(int) (posX+(Ancho/1.7));
    		else
    			posY=(int) (posY+(Alto/1.7));
    			
    		CCarta c=Cartas.get(i);    		    		
    		c.Pintar(vista,canvas,Alto,Ancho,posX,posY);
	    	c.setMarcada(false);
    	}
		/*Iterator <CCarta> iterador= Cartas.iterator();  		  
	    while (iterador.hasNext()) 
	    {  
	    		    	
    		if(Horizontal)
    			posX=(int) (i*(Ancho/1.5));
    		else
    			posY=(int) (i*(Alto/1.5));
    			
    		CCarta c=iterador.next();    		
    		c.Pintar(vista,canvas,Alto,Ancho,posX,posY);
	    	c.setMarcada(false);	    	    		    
    		i++;	    	    
	    } */ 
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{
		Pintar(vista,canvas,Alto,Ancho,X,Y,true);
	}
	public List<Bitmap> getBitmaps()
	{
		List<Bitmap> BmpMano=new ArrayList<Bitmap>(6);
		for(int i=0;i<Cartas.size();i++)
		{
			
			BmpMano.add(Cartas.get(i).getImagenCarta());
		}
		return BmpMano;
	}
	public void OrdenarMano() 
	{
		try
		{
			Iterator <CCarta> iterador= Cartas.iterator();  
			  
		    while (iterador.hasNext()) 
		    {  
		    	CCarta c=iterador.next();
		    	c.setPesoOrden(10*(c.getPalo())+(c.getId()-1));		    	   		      
		    }  
			Collections.sort(Cartas);
		}
		catch(Exception e)
		{	
		}
	}
	public CCarta Tocada(float x , float y)
	{
		CCarta rtn=null;
		boolean tocada=false;
		
		Iterator <CCarta> iterador= getCartas().iterator();  		  
	    while (!tocada && iterador.hasNext()) 
	    {  		    	
	    	rtn=iterador.next();
	    	if(rtn.Tocada(x, y))
	    	{
	    		tocada=true;	    		
	    	}	    		    
	    }
	    if(!tocada)
	    	rtn=null;
		return rtn;
	}	
}