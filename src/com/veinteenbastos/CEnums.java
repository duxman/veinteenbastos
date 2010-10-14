package com.veinteenbastos;

enum eJugadores
{
	PARES(2,4),IMPARES(1,3);
	private int JugadorA;
	private int JugadorB;
	private eJugadores(int a,int b) 
	{
		JugadorA=a;
		JugadorB=b;
	}
	public static eJugadores quePareja(int j)
	{
		if(j==2 || j==4 )
			return PARES;
		if(j==1 || j==3 )
			return IMPARES;	
		return null;			
	}
}
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


