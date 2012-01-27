package com.duxnet.games.veinteenbastos;


import java.util.ArrayList;
import java.util.List;

import com.duxnet.games.veinteenbastos.enums.ePalo;

public class CJugada extends CListaCartas 
{	
	private ePalo triunfo;
	private int mano;
	
	public CJugada(ePalo t,int m)
	{		
		setTriunfo(t);
		setMano(m);
	}
	public CJugada(CCarta c1,CCarta c2,CCarta c3,CCarta c4,ePalo t,int m)
	{
		setCartas(new ArrayList<CCarta>(4));
		setTriunfo(t);
		setMano(m);
		add(c1);
		add(c2);
		add(c3);
		add(c4);		
	}
	public CJugada(List<CCarta> cartas, ePalo t,int m)
	{		
		setTriunfo(t);
		setMano(m);
		setCartas(cartas);
	}
	
	public void setTriunfo(ePalo triunfo) 
	{
		this.triunfo = triunfo;
	}
	public ePalo getTriunfo() 
	{
		return triunfo;
	}
	public void setMano(int mano) {
		this.mano = mano;
	}
	public int getMano() {
		return mano;
	}		
	private boolean esTriunfo(int palo)
	{
		boolean rtn=false;
		if(palo==getTriunfo().getId())
			rtn=true;
		return rtn;
	}
	private boolean esTriunfo(ePalo palo)
	{
		boolean rtn=false;
		if(palo==getTriunfo())
			rtn=true;
		return rtn;
	}
	
	private int PersonaGanadora(int pos1,int pos2)
	{
		int rtn=0;		
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
