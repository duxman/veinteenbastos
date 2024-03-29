package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.duxnet.games.veinteenbastos.IA.CDatos;
import com.duxnet.games.veinteenbastos.enums.eCarta;
import com.duxnet.games.veinteenbastos.enums.ePalo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class CListaCartas extends CDatos implements List<CCarta>
{
	private List<CCarta> Cartas;		
	//==================================================
	// Constructores
	//==================================================
	public CListaCartas()
	{		
		
		Cartas=new ArrayList<CCarta>();			
	}
	//==================================================================
    // Metodos Ordenadores
    //==================================================================
	public void Ordenar() 
	{
		try
		{
			Collections.sort(Cartas);
		}
		catch(Exception e)
		{	
		}
	}
	public void OrdenarPorOrdinal()
	{
		Collections.sort(getCartas(),CCarta.CompararIdCarta);		
	}
	public void OrdenarPorPeso()
	{
		Collections.sort(getCartas(),CCarta.CompararPesoOrden);		
	}
	public void OrdenarPorPosicion()
	{
		Collections.sort(getCartas(),CCarta.CompararPosCarta);		
	} 
	public void OrdenarPorValor()
	{
		Collections.sort(getCartas(),CCarta.CompararValorCarta);		
	}
	//==================================================
	// Metodos
	//==================================================
	public int NumCartas()
	{
		return size();
	}
	public int NumCartas(ePalo p)
	{
		int rtn=0;
		Iterator<CCarta> it =iterator();
		while(it.hasNext())
		{
			if(it.next().getPaloCarta()==p)
				rtn++;
		}
		return rtn;		
	}
	public int NumCartas(ePalo p,int valor)
	{
		int rtn=0;
		Iterator<CCarta> it =iterator();
		while(it.hasNext())
		{
			CCarta c=it.next();
			if(c.getPaloCarta()==p && c.getPosCarta()>=valor )
				rtn++;
		}
		return rtn;		
	}
	public int NumCartas(int valor)
	{
		int rtn=0;
		Iterator<CCarta> it =iterator();
		while(it.hasNext())
		{
			CCarta c=it.next();
			if(c.getPosCarta()>=valor )
				rtn++;
		}
		return rtn;		
	}
	public boolean tieneCarta(CCarta Carta)
	{
		boolean rtn=false;
		if(indexOf(Carta)>=0)
			rtn=true;		
		return rtn;
	}
	public boolean tieneCarta(ePalo p, eCarta c)
	{
		boolean rtn=false;
		Iterator<CCarta> it =iterator();
		while(!rtn && it.hasNext())
		{
			CCarta c1=it.next();
			if(c1.getPaloCarta()==p && c1.getECarta()==c)
				rtn=true;
		}
		return rtn;	
	}
	public int idxCarta(eCarta c)
	{
		int  rtn=-1;
		Iterator<CCarta> it =iterator();
		while(rtn==-1 && it.hasNext())
		{
			CCarta c1=it.next();
			if(c1.getECarta()==c)
				rtn=indexOf(c1);			
		}
		return rtn;	
	}
	public int idxCarta(ePalo p, eCarta c)
	{
		int  rtn=-1;
		Iterator<CCarta> it =iterator();
		while(rtn==-1 && it.hasNext())
		{
			CCarta c1=it.next();
			if(c1.getECarta()==c && c1.getPaloCarta()==p)
				rtn=indexOf(c1);			
		}
		return rtn;	
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
			c=Cartas.get(0);
			Cartas.remove(0);
		}
		else
		{
			c=Cartas.get(0);			
		}
		return c;
		
	}
	public CCarta DameUltimaCarta()
	{
		return DameUltimaCarta(true);
		
	}
	public CCarta Tocada(float x,float y)
	{
		CCarta rtn=null;
		if(size()>0)
		{
			Iterator<CCarta> it = iterator();
			while(rtn==null && it.hasNext())
			{
				CCarta c=it.next();			
				if(c.Tocada(x, y)) rtn=c;												
			}
		}		
		return rtn;
	}
	public CCarta DameUltimaCarta(boolean quitar)
	{
		CCarta c;
		int Ultima=Cartas.size()-1;
		if(quitar)
		{
			c=Cartas.get(Ultima);
			Cartas.remove(Ultima);
		}
		else
		{
			c=Cartas.get(Ultima);			
		}
		return c;
	}
	
	public int Contar()
	{
		int rtn=0;
		Iterator<CCarta> it=iterator();
		while(it.hasNext())
			rtn+=it.next().getValorCarta();
		return rtn;
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{
		Pintar(vista,canvas,Alto,Ancho,X,Y,Ancho/2,true,false);
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y,int separacion,boolean Horizontal,boolean contar)
	{
		int posX=X;
    	int posY=Y;
    	Paint p = new Paint();
    	for(int i=Cartas.size()-1;i>=0;i--)
    	{
    		if(Horizontal)
    			posX=(int) (posX+separacion);
    		else
    			posY=(int) (posY+separacion);
    			
    		CCarta c=Cartas.get(i);    		    		
    		c.Pintar(vista,canvas,Alto,Ancho,posX,posY);
    		if(contar)
    		{    		
    			p.setTextSize(50);
    			p.setFakeBoldText(true);
    			p.setColor(Color.RED);    			
    			canvas.drawText(String.valueOf(size()),(posX+(10)),(posY+(Alto/2)), p);
    		}
    	}
	}	
	//==================================================
	// Propiedades
	//==================================================
	public List<CCarta> getCartas() 
	{
		return Cartas;
	}	
	//==================================================
	// I List
	//==================================================
	public void setCartas(List<CCarta> cartas) {
		Cartas = cartas;
	}


	public boolean add(CCarta object) 	
	{
		return Cartas.add(object);
	}


	public void add(int location, CCarta object) 
	{
		Cartas.add(location, object);			
	}


	public boolean addAll(Collection<? extends CCarta> arg0) 
	{	
		return Cartas.addAll(arg0);
	}


	public boolean addAll(int arg0, Collection<? extends CCarta> arg1) 
	{	
		return Cartas.addAll(arg0, arg1);
	}


	public void clear() 
	{
		Cartas.clear();
	}


	public boolean contains(Object object) 
	{
		return Cartas.contains(object);
	}


	public boolean containsAll(Collection<?> arg0) 
	{	
		return Cartas.containsAll(arg0);
	}


	public CCarta get(int location) 
	{
			return Cartas.get(location);
	}


	public int indexOf(Object object) 
	{
		return Cartas.indexOf(object);
	}


	public boolean isEmpty() 
	{	
		return Cartas.isEmpty();
	}


	public Iterator<CCarta> iterator() 
	{			
		return Cartas.iterator();
	}
	public int lastIndexOf(Object object) 
	{	
		return Cartas.lastIndexOf(object);
	}
	public ListIterator<CCarta> listIterator() 
	{			
		return Cartas.listIterator();
	}


	public ListIterator<CCarta> listIterator(int location) 
	{		
		return Cartas.listIterator(location);
	}


	public CCarta remove(int location) 
	{	
		return Cartas.remove(location);
	}


	public boolean remove(Object object) 
	{	
		return Cartas.remove(object);
	}
	
	public boolean removeAll(Collection<?> arg0) 
	{		
		return Cartas.removeAll(arg0);
	}


	public boolean retainAll(Collection<?> arg0) 
	{	
		return Cartas.retainAll(arg0);
	}


	public CCarta set(int location, CCarta object) 
	{		
		return Cartas.set(location, object);
	}


	public int size() 
	{
		return Cartas.size();
	}


	public List<CCarta> subList(int start, int end) 
	{
		return Cartas.subList(start, end);
	}


	public Object[] toArray() 
	{
		return Cartas.toArray();
	}


	public <T> T[] toArray(T[] array) 
	{
		return Cartas.toArray(array);
	}	
}

