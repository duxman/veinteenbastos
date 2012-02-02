package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.duxnet.games.veinteenbastos.enums.ePalo;

import android.graphics.Canvas;

public class CMano  extends CListaCartas
{
	private int m_numcar;
	private List<CPalo> m_Palos;
	private CPalo m_PaloTriunfo;
	//==================================================================
    // Constructores
    //==================================================================
	public CMano()
	{
		super();
		m_Palos=new ArrayList<CPalo>();
		m_Palos.add(new CPalo());
		m_Palos.add(new CPalo());
		m_Palos.add(new CPalo());
		m_PaloTriunfo=new CPalo();		
	}
	public CPalo DamePalo(ePalo palo)
	{
		CPalo rtn=null;				
		Iterator<CPalo> itpalo = m_Palos.iterator();
		while(rtn==null  && itpalo.hasNext())
		{
			CPalo p=itpalo.next();
			if(p.getEPalo()==palo)
			{	
				rtn=p;
			}					
		}
		return rtn;
	}
	public void RellenaPalosMano(ePalo triunfo)
	{			
		Iterator<CCarta> itcarta=getCartas().iterator();
		while(itcarta.hasNext())
		{
			CCarta c=itcarta.next();
			if(c.getPaloCarta()==triunfo)
			{
				m_PaloTriunfo.setPaloBaraja(c.getPaloCarta().getId());
				m_PaloTriunfo.rellenaDatos(c,1);				
				m_PaloTriunfo.add(c);
				Collections.sort(m_PaloTriunfo,CCarta.CompararPosCarta);
			}
			else
			{
				Iterator<CPalo> itpalo = m_Palos.iterator();
				while(itpalo.hasNext())
				{
					CPalo p=itpalo.next();
					if(p.getEPalo()==c.getPaloCarta())
					{
						p.setPaloBaraja(c.getPaloCarta().getId());
						p.rellenaDatos(c,1);				
						p.add(c);
						Collections.sort(p,CCarta.CompararPosCarta);
					}					
				}				
			}			
		}
		Collections.sort(getPalos());
	}		
	//==================================================================
    // Metodos Ordenadores
    //==================================================================
	// Ordena por el ordinal de la carta dejando la baraja en este formato 
	// OROS,COPAS,ESPADAS,BASTOS del AS al REY
	//
	// Metodo sobrecargado para la ordenacion por defecto en realidad lo 
	// que hace es llamar al metodo de ordenacion superior
	public void Ordenar() 
	{	
		OrdenarPorOrdinal();
	}		
	//==================================================================
    // Metodos contar
    //==================================================================
	public int NumBrescas(ePalo p)
	{
		return NumCartas(p, 8);
	}
	public int NumBrescas()
	{
		return NumCartas(8);
	}	
	//==================================================================
    // Metodos Pintar
    //==================================================================
	
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y,boolean Horizontal)
	{
		Pintar(vista, canvas, Alto, Ancho, X, Y, Ancho/2, Horizontal, false);
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y,boolean Horizontal,int separacion)
	{
		Pintar(vista, canvas, Alto, Ancho, X, Y, separacion, Horizontal,false);
	}
	
	//==================================================================
    // Metodos Generales
    //==================================================================
	@Override
	public boolean add(CCarta object) 	
	{
		boolean rtn=false;		
		boolean encontrado=false;
		rtn=getCartas().add(object);
		Iterator<CPalo> itpalo = m_Palos.iterator();
		if(object.getPaloCarta()==m_PaloTriunfo.getEPalo())
		{
			m_PaloTriunfo.setPaloBaraja(object.getPaloCarta().getId());
			m_PaloTriunfo.rellenaDatos(object,1);				
			m_PaloTriunfo.add(object);
			Collections.sort(m_PaloTriunfo,CCarta.CompararPosCarta);
		}
		else
		{
			while(!encontrado && itpalo.hasNext())
			{
				CPalo p=itpalo.next();
				if(p.getEPalo()==object.getPaloCarta())
				{
					p.setPaloBaraja(object.getPaloCarta().getId());
					p.rellenaDatos(object,1);				
					p.add(object);
					encontrado=true;
					Collections.sort(p,CCarta.CompararPosCarta);
				}					
			}
			
		}
		Collections.sort(getPalos());
		setNumcar(getNumcartas());
		return rtn;
		
	}	
	@Override
	public CCarta remove(int location) 
	{	
		CCarta rtn=getCartas().get(location);
		this.remove(rtn);				
		return  rtn; 		
	}
	@Override
	public boolean remove(Object o) 
	{	
		boolean rtn=false;						
		boolean encontrado=false;
		CCarta object=((CCarta) o);
		
		Iterator<CPalo> itpalo = m_Palos.iterator();
		if(object.getPaloCarta()==m_PaloTriunfo.getEPalo())
		{
			m_PaloTriunfo.rellenaDatos(object,2);				
			rtn=(m_PaloTriunfo.remove(object) && getCartas().remove(object));
			Collections.sort(m_PaloTriunfo,CCarta.CompararPosCarta);
		}
		else
		{
			while(!encontrado && itpalo.hasNext())
			{
				CPalo p=itpalo.next();
				if(p.getEPalo()==object.getPaloCarta())
				{					
					p.rellenaDatos(object,2);				
					rtn=(p.remove(object) && getCartas().remove(object));
					encontrado=true;
					Collections.sort(p,CCarta.CompararPosCarta);
				}					
			}			
		}
		setNumcar(getNumcartas());
		Collections.sort(getPalos());
		return rtn;		
	}	
	
	public CCarta tirarCarta(int i)
	{
		CCarta rtn=null;
		rtn=get(i);
		if(rtn!=null) remove(i);
		return rtn;		
	}
	public void AddNumcar() 
	{
		m_numcar++;
	}		
	public void DelNumcar() 
	{
		m_numcar--;
	}
	//==================================================================
    // IA?
    //==================================================================
	
	//==================================================================
    // Propiedades
    //==================================================================
	public int getNumcar() 
	{
		return m_numcar;
	}
	public void setNumcar(int numcar) 
	{
		m_numcar = numcar;
	}	
	public List<CPalo> getPalos() 
	{
		return m_Palos;
	}
	public void setPalos(List<CPalo> palos) 
	{
		m_Palos = palos;
	}
	public CPalo getPaloTriunfo() {
		return m_PaloTriunfo;
	}
	public void setPaloTriunfo(CPalo paloTriunfo) {
		m_PaloTriunfo = paloTriunfo;
	}	
}