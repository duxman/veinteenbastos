package com.duxnet.games.veinteenbastos;
import com.duxnet.games.veinteenbastos.enums.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CBaraja 
{
	private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 10;
    
    private int AltoCarta;
    private int AnchoCarta;
    private int AnchoPalo;
	private List<Cpalo> PalosBaraja;
	private List<CCarta> Baraja;
	private Bitmap BarajaCompleta;
	private Bitmap CartaFondo;
	public CBaraja(Bitmap bc,Bitmap bf)
	{
		BarajaCompleta=bc;
		setCartaFondo(bf);
		setAltoCarta(bc.getHeight() / BMP_ROWS);
		setAnchoCarta(bc.getWidth() / BMP_COLUMNS);
		AnchoPalo=bc.getWidth();
		PalosBaraja=new ArrayList<Cpalo>();
		Baraja=new ArrayList<CCarta>();		
		CreaBaraja(true);
	}		
	private void CreaBaraja(boolean desordenada)
	{		
        int srcY ;
        int srcX ;        
		for(int cpalo=0;cpalo<4;cpalo++)
		{						
			srcY=cpalo*getAltoCarta();						
			Bitmap palo=Bitmap.createBitmap(BarajaCompleta, 0, srcY, AnchoPalo, getAltoCarta());
			PalosBaraja.add(new Cpalo(cpalo, palo));			
			for(int ccarta=1;ccarta<11;ccarta++)
			{				
				srcX=(ccarta-1)*getAnchoCarta();				
				Bitmap bmpcarta=Bitmap.createBitmap(palo, srcX,0, getAnchoCarta(), getAltoCarta());				
				CCarta carta= new CCarta(ccarta,cpalo,bmpcarta,getCartaFondo());
				carta.setDondeEsta(eLugar.MAZO);
				carta.setDimensiones(AnchoCarta, AltoCarta);
				if(desordenada)
					carta.setPesoOrden(DameAleatorio());											
				Baraja.add(carta);
			}		
		}
		OrdenarBaraja(); 
	}	
	public void add(CCarta c)
	{
		Baraja.add(0, c);
	}
	
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{
		int posX=X;
    	int posY=Y;    	    	
		Iterator <CCarta> iterador= Baraja.iterator();  		  
	    while (iterador.hasNext()) 
	    {  	    	
	    	CCarta c=iterador.next();
	    	c.Pintar(vista,canvas,Alto,Ancho,posX,posY);	
	    }  
	}
	public void Barajar()
	{		
	
		Iterator<CCarta> itr = Baraja.iterator();
        while(itr.hasNext())
        {
        	CCarta c = itr.next();
        	c.setPesoOrden(DameAleatorio());      
        }
        OrdenarBaraja();
	}
	public int size()
	{
		return Baraja.size();
	}
	public void CortarBaraja()
	{	
		int iPunto = (int)(Math.random()*40);
		int Contador=0;		
		for(int i=iPunto;i<40;i++)
		{
			Baraja.get(i).setPesoOrden(Contador);
			Contador++;			
		}
		for(int i=0;i<iPunto;i++)
		{
			Baraja.get(i).setPesoOrden(Contador);
			Contador++;			
		}				
		OrdenarBaraja();
	}
	public void Repartir(CMano m)
	{
		for(int i=0;i<6;i++)
		{
			CCarta c=DamePrimeraCarta();
			c.setDondeEsta(eLugar.MANO);
			m.AddCarta(c);			
		}		
	}
	private void OrdenarBaraja() 
	{
		try
		{
			Collections.sort(Baraja);
		}
		catch(Exception e)
		{	
		}
	}
	public CCarta DamePrimeraCarta()
	{
		return DamePrimeraCarta(true);
		
	}
	public CCarta DamePrimeraCarta(boolean quitar)
	{
		CCarta c;
		if(quitar)
		{
			c=Baraja.get(0);
			Baraja.remove(0);
		}
		else
		{
			c=Baraja.get(0);			
		}
		return c;
	}
	public CCarta DameUltimaCarta()
	{
		return DameUltimaCarta(true);
		
	}
	public CCarta DameUltimaCarta(boolean quitar)
	{
		CCarta c;
		int Ultima=Baraja.size()-1;
		if(quitar)
		{
			c=Baraja.get(Ultima);
			Baraja.remove(Ultima);
		}
		else
		{
			c=Baraja.get(Ultima);			
		}
		return c;
	}
	private long DameAleatorio()
	{				
		Random r = new Random();
		int iAleatorio = (int)(Math.random()*10000);
		long semilla =(long)(Math.abs(Math.sqrt(Math.tanh(r.nextInt(iAleatorio))))*r.nextInt(iAleatorio));		
		r.setSeed(semilla);				
		return r.nextInt();								
	}
	public Cpalo getPalo(int i)
	{
		return PalosBaraja.get(i);
	}		
	public CCarta getCarta(int i)
	{
		return Baraja.get(i);
	}

	public Bitmap getCartaFondo() {
		return CartaFondo;
	}
	public void setCartaFondo(Bitmap cartaFondo) {
		CartaFondo = cartaFondo;
	}
	public int getAltoCarta() 
	{
		return AltoCarta;
	}
	public void setAltoCarta(int altoCarta) 
	{
		AltoCarta = altoCarta;
	}
	public int getAnchoCarta() 
	{
		return AnchoCarta;
	}
	public void setAnchoCarta(int anchoCarta) 
	{
		AnchoCarta = anchoCarta;
	}
	public boolean Tocada(float x,float y)
	{
		boolean rtn=false;
		if(size()>0 && DameUltimaCarta(false).Tocada(x, y))
    	{    		
			rtn=true;
    	}
		return rtn;
	}

}
