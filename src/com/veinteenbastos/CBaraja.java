package com.veinteenbastos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CBaraja 
{
	private List<CCartas> Baraja;
	public CBaraja(boolean ordenada,boolean barajar,boolean cortar)
	{	
		Baraja=new ArrayList<CCartas>();
		if(ordenada)
			CrearBarajaDesordenada();
		else
		{
			CrearBaraja();
			if(barajar)
			{	
				Barajar();
			}
		}
		if(cortar)
		{
			CortarBaraja();
		}
	}
	public void CortarBaraja()
	{	
		int iPunto = (int)(Math.random()*40);
		int Contador=0;		
		for(int i=iPunto;i<40;i++)
		{
			Baraja.get(i).peso=Contador;
			Contador++;			
		}
		for(int i=0;i<iPunto;i++)
		{
			Baraja.get(i).peso=Contador;
			Contador++;			
		}				
		OrdenarBaraja();
	}
	public void CortarBaraja(int numero)
	{	
		int iPunto =numero;
		int Contador=0;		
		for(int i=iPunto;i<40;i++)
		{
			Baraja.get(i).peso=Contador;
			Contador++;			
		}
		for(int i=0;i<iPunto;i++)
		{
			Baraja.get(i).peso=Contador;
			Contador++;			
		}				
		OrdenarBaraja();
	}
	private void CrearBaraja()
	{
		for(int cpalo=1;cpalo<=4;cpalo++)
		{
			for(int ccarta=1;ccarta<=10;ccarta++)
			{
				CCartas Carta=new CCartas(eCartas.GetCarta(ccarta),ePalos.GetPalo(cpalo));
				Carta.peso=DameAleatorio();
				Baraja.add(Carta);				
			}
		}
	}
	private void CrearBarajaDesordenada()
	{
		for(int cpalo=1;cpalo<=4;cpalo++)
		{
			for(int ccarta=1;ccarta<=10;ccarta++)
			{
				CCartas Carta=new CCartas(eCartas.GetCarta(ccarta),ePalos.GetPalo(cpalo));
				Carta.peso=DameAleatorio();
				Baraja.add(Carta);				
			}
		}
	}
	public void Barajar()
	{		
	
		Iterator<CCartas> itr = Baraja.iterator();
        while(itr.hasNext())
        {
        	CCartas c = (CCartas)itr.next();
        	c.peso=DameAleatorio();      
        }
        OrdenarBaraja();
	}
	@SuppressWarnings("unchecked")
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
	private int DameAleatorio()
	{		
		int iPalo = (int)(Math.random()*4);
		int iCarta = (int)(Math.random()*10);
		int iAleatorio = (int)(Math.random()*10000);
		return (int) Math.abs(Math.sqrt(Math.tanh(iAleatorio*iPalo/iCarta)));				
					
	}
	public CCartas DamePrimeraCarta()
	{
		CCartas c=Baraja.get(0);
		Baraja.remove(0);
		return c;
	}
	
	
}
