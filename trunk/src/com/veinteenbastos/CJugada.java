package com.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

public class CJugada 
{
	private List<CCartas> Cartas;
	private ePalos triunfo;
	private int mano;
	
	public CJugada(ePalos t,int m)
	{
		setCartas(new ArrayList<CCartas>(4));
		setTriunfo(t);
		setMano(m);
	}
	public CJugada(CCartas c1,CCartas c2,CCartas c3,CCartas c4,ePalos t,int m)
	{
		setCartas(new ArrayList<CCartas>(4));
		setTriunfo(t);
		setMano(m);
		Cartas.add(c1);
		Cartas.add(c2);
		Cartas.add(c3);
		Cartas.add(c4);		
	}
	public void setTriunfo(ePalos triunfo) 
	{
		this.triunfo = triunfo;
	}
	public ePalos getTriunfo() 
	{
		return triunfo;
	}
	public void setMano(int mano) {
		this.mano = mano;
	}
	public int getMano() {
		return mano;
	}
	public void setCartas(List<CCartas> cartas) 
	{
		Cartas = cartas;
	}
	public List<CCartas> getCartas() 
	{
		return Cartas;
	}	

	
	public void Add(int pos,CCartas c)

	{
		Cartas.add(pos, c);	
	}
	private boolean esTriunfo(int palo)
	{
		boolean rtn=false;
		if(palo==getTriunfo().getId())
			rtn=true;
		return rtn;
	}
	private int PersonaGanadora(int pos1,int pos2)
	{
		int rtn=0;	
		int marca=Cartas.get(getMano()).palo;
		int marca1=Cartas.get(pos1).palo;
		int marca2=Cartas.get(pos2).palo;		
		int valor1=Cartas.get(pos1).getValorCarta(getTriunfo());
		int valor2=Cartas.get(pos2).getValorCarta(getTriunfo());
		
		if(esTriunfo(marca))
		{	
			if(esTriunfo(marca1) && esTriunfo(marca2))			
				rtn=(valor1>valor2)?pos1:pos2;		
			else
			{
				if(esTriunfo(marca1))
					rtn=pos1;
				else if(esTriunfo(marca2))
					rtn=pos2;
				else
				{
					if(marca2==marca1)
						rtn=(valor1>valor2)?pos1:pos2;
					else
						rtn=(getMano()<=pos1)?pos1:pos2;											
				}
			}
		}
		else
		{					
			if(marca2==marca1)
			{
				rtn=(valor1>valor2)?pos1:pos2;			
			}	
			else if(marca2!=marca1)				
			{
				if(esTriunfo(marca1))
					rtn=pos1;
				else if(esTriunfo(marca2))
					rtn=pos2;			
				else if(!esTriunfo(marca1) && !esTriunfo(marca2))
				{
					if(marca==marca1)
						rtn=pos1;				
					if(marca==marca2)
						rtn=pos2;
					if(marca!=marca2 && marca!=marca1)
					{
						rtn=(getMano()<=pos1)?pos1:pos2;					
					}			
				}				
			}
		}							
		return rtn;			
	}
	public int QuienGana()
	{		
		int ParejaImpar=PersonaGanadora(1,3);
		int ParejaPar=PersonaGanadora(2,4);		
		int ganador=PersonaGanadora(ParejaImpar,ParejaPar);
		return ganador;	
	}

}
