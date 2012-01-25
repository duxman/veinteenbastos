/*

enum ePalos 
{
	OROS(1,"Oros","Oretes"), COPAS(2,"Copas","Copazos"), ESPADAS(3,"Espadas","Espadas"), BASTOS(4,"Bastos","Bastos");
	private int IdPalo;
	private String Nombre;
	private String SubNombre;
	ePalos(int ip,String n,String s) 
	{
    	this.IdPalo = ip;
    	this.Nombre=n;
    	this.SubNombre=s;
	}
	public static ePalos GetPalo(int i)
	{		
		switch(i)
		{
			case 1:
				return ePalos.OROS;
			case 2:
				return ePalos.COPAS;
			case 3:
				return ePalos.ESPADAS;
			case 4:
				return ePalos.BASTOS;
			default:
				return null;
		}
	}
    public String getNombre() 
    {
    	return Nombre;
    }
    public String getSubNombre() 
    {
    	return SubNombre;
    }
    public int getId()
    {
    	return IdPalo;
    }
}
enum eCartas
{
	AS(1,"1","As",11),DOS(2,"2","Dos",0),TRES(3,"3","Tres",10),CUATRO(4,"4","Cuatro",0),CINCO(5,"5","Cinco",0),SEIS(6,"6","Seis",0),SIETE(7,"7","Siete",0),SOTA(8,"10","Sota",3),CABALLO(9,"11","Caballo",2),REY(10,"12","Rey",4);	
	private int IdCarta;
	private String Nombre;
	private String SubNombre;
	private int Puntos;
	eCartas(int i,String n,String s,int p) 
	{
    	IdCarta=i;
    	Nombre=n;
    	SubNombre=s;
    	Puntos=p;
	}
	public static eCartas GetCarta(int i)
	{		
		switch(i)
		{
			case 1:
				return eCartas.AS;
			case 2:
				return eCartas.DOS;
			case 3:
				return eCartas.TRES;
			case 4:
				return eCartas.CUATRO;
			case 5:
				return eCartas.CINCO;
			case 6:
				return eCartas.SEIS;
			case 7:
				return eCartas.SIETE;
			case 8:
				return eCartas.SOTA;
			case 9:
				return eCartas.CABALLO;				
			case 10:
				return eCartas.REY;
			default:
				return null;											
		}							
	}

    public String getNombre() 
    {
    	return Nombre;
    }
    public int getId()
    {
    	return IdCarta;
    }
    public String getSubNombre() 
    {
    	return SubNombre;
    }
    public int getPuntos()
    {
    	return Puntos;
    }
}
public class CCartas implements Comparable<Object>
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
*/