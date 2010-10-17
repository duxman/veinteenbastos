package com.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

public class CMano 
{
	private List<CCartas> Cartas;
	public CMano()
	{
		setCartas(new ArrayList<CCartas>(6));
	}
	public void setCartas(List<CCartas> cartas) 
	{
		Cartas = cartas;
	}
	public List<CCartas> getCartas() 
	{
		return Cartas;
	}	
	public CCartas TirarCarta()
	{
		CCartas c=DecidirCarta();
		Cartas.remove(c);
		return c;				
	}
	public void AddCarta(CCartas carta)
	{
		Cartas.add(carta);
	}
	private CCartas DecidirCarta() 
	{
		return null;
		// TODO Auto-generated method stub		
	}
}
