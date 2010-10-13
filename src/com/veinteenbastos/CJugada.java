package com.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

public class CJugada 
{
	private List<CCartas> Cartas;
	private ePalos triunfo;
	public CJugada(ePalos t)
	{
		setCartas(new ArrayList<CCartas>(4));
		setTriunfo(t);
	}
	public CJugada(CCartas c1,CCartas c2,CCartas c3,CCartas c4)
	{
		setCartas(new ArrayList<CCartas>(4));
		Cartas.add(c1);
		Cartas.add(c2);
		Cartas.add(c3);
		Cartas.add(c4);		
	}
	public void Add(int pos,CCartas c)
	{
		Cartas.add(pos, c);	
	}
	public void setCartas(List<CCartas> cartas) 
	{
		Cartas = cartas;
	}
	public List<CCartas> getCartas() 
	{
		return Cartas;
	}	
	public void setTriunfo(ePalos triunfo) 
	{
		this.triunfo = triunfo;
	}
	public ePalos getTriunfo() 
	{
		return triunfo;
	}
	public int QuienGana()
	{
		int rtn = 0;
		
		if(Cartas.get(4).soyMayor(Cartas.get(3), false, triunfo.getId()))
		{
			if(Cartas.get(4).soyMayor(Cartas.get(2), false, triunfo.getId()))
			{
				if(Cartas.get(4).soyMayor(Cartas.get(1), false, triunfo.getId()))
				{
					rtn=4;
				}
				else
				{
					rtn=1;
				}
			}
			else
			{
				if(Cartas.get(2).soyMayor(Cartas.get(1), false, triunfo.getId()))
				{
					rtn=2;				
				}
				else
				{
					rtn=1;
				}
			}
		}
		else
		{
			if(Cartas.get(3).soyMayor(Cartas.get(2), false, triunfo.getId()))
			{
				if(Cartas.get(3).soyMayor(Cartas.get(1), false, triunfo.getId()))
				{
					rtn=3;
				}
				else
				{
					rtn=1;
				}
			}
			else
			{
				if(Cartas.get(2).soyMayor(Cartas.get(1), false, triunfo.getId()))
				{
					rtn=2;				
				}
				else
				{
					rtn=1;
				}
			}
			
		}
		return rtn;	
	}
}
