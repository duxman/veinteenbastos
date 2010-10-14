/**
 * 
 */
package com.veinteenbastos;
/**
 * @author duxman
 *
 */
@SuppressWarnings("rawtypes")
public class CCartas implements Comparable
{
	int valor;
	int puntos;
	int palo;
	int peso;
	String descripcion;
	public CCartas()
	{
	}
	public CCartas(eCartas c,ePalos p)
	{
		valor=c.getId();
		puntos=c.getPuntos();
		descripcion=c.getSubNombre() + " de " + p.getNombre();		
		palo=p.getId();
		peso=(10*(p.getId()-1)+(c.getId()-1));
	}
	public CCartas(int v,int p, String d,int pa)
	{
		valor=v;
		puntos=p;
		descripcion=d;		
		palo=pa;
		peso=(10*(pa-1)+(v-1));
	}
	public int getValorCarta(ePalos triunfo)
	{
		int rtn = 10+valor+puntos;		
		if(triunfo.getId()==this.palo)
			rtn+=15;										
		return rtn;		
	}
	public boolean soyMayor(CCartas otra,boolean esMano,int Triunfo)
	{
		boolean rtn=false;
		boolean esTriunfo = false,soyTriunfo = false;
		if(otra.palo==Triunfo)
			esTriunfo=true;
		if(this.palo==Triunfo)
			soyTriunfo=true;
		if(otra.palo==this.palo)
		{
			if(this.puntos>otra.puntos)
			{
				rtn=true;
			}
			else
			{
				if(this.valor>otra.valor)
				{
					rtn=true;
				}
			}
		}
		else
		{
			if(esTriunfo)
			{
				rtn=false;				
			}
			else
			{
				if(soyTriunfo)
				{
					return true;
				}
				else 
				{
					if(esMano)
					{
						rtn=false;
					}
					else
					{
						rtn=true;
					}
				}	
			}
					
		}
		return rtn;
	}
	@Override
	public String toString() 
	{
        return "Carta " + this.descripcion;
    }

    public int compareTo(Object o1) 
    {
        if (this.peso == ((CCartas) o1).peso)
            return 0;
        else if ((this.peso) > ((CCartas) o1).peso)
            return 1;
        else
            return -1;
    }

}
