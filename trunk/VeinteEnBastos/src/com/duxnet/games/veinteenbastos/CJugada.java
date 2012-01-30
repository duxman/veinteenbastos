package com.duxnet.games.veinteenbastos;


import com.duxnet.games.veinteenbastos.enums.*;
public class CJugada extends CListaCartas 
{	
	private ePalo triunfo;
	private int mano;
	
	public CJugada(ePalo t,int m)
	{		
		setTriunfo(t);
		setMano(m);
	}			
	public void setTriunfo(ePalo triunfo) 
	{
		this.triunfo = triunfo;
	}
	public ePalo getTriunfo() 
	{
		return triunfo;
	}
	public void setMano(int mano) 
	{
		this.mano = mano;
	}
	public int getMano() 
	{
		return mano;
	}				
	private int PersonaGanadora(int pos1,int pos2)
	{
		int rtn=0;	
		CCarta carta1=get(pos1);
		CCarta carta2=get(pos2);
		if(carta1.getPaloCarta()==carta2.getPaloCarta())
		{
			if(carta1.getValorCarta()==carta2.getValorCarta())
			{
				rtn=(carta1.getIdCarta()>carta2.getIdCarta())?pos1:pos2;								
			}
			else
			{
				rtn=(carta1.getValorCarta()>carta2.getValorCarta())?pos1:pos2;				
			}										
		}
		else
		{
			if(carta1.getPaloCarta()==getTriunfo())
			{
				rtn=pos1;				
			}
			else if(carta2.getPaloCarta()==getTriunfo())
			{
				rtn=pos2;				
			}
			else
			{
				if(pos1==getMano())
				{
					rtn=pos1;					
				}
				else if (pos2==getMano())
				{
					rtn=pos2;
									
				}
				else
				{
					rtn=(getMano()>pos1)?pos2:pos1;					
				}
			}			
		}
		return rtn;			
	}
	public int QuienGana()
	{		
		int rtn=0;
		if(size()==2)
		{
			rtn=PersonaGanadora(1,2);
		}
		if(size()==4)
		{
			int p1=PersonaGanadora(1,3);
			int p2=PersonaGanadora(2,4);
			rtn=PersonaGanadora(p1,p2);
		}				
		return rtn;	
	}

}
