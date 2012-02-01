package com.duxnet.games.veinteenbastos.enums;

public enum eCarta 
{
	AS(1,11,9),DOS(2,0,0),TRES(3,10,8),CUATRO(4,0,1),CINCO(5,0,2),SEIS(6,0,3),SIETE(7,0,4),SOTA(8,3,6),CABALLO(9,2,5),REY(10,4,7);
	private final int id;
	private final int valor;
	private final int pos;
	private eCarta(int i,int v,int p) {id=i;valor=v;pos=p;}
	public int getValor() {return valor;}
	public int getId() {return id;}
	public int getPos() {return pos;}
	public static eCarta getCarta(int i)
	{		
		switch(i)
		{
			case 1:
				return eCarta.AS;
			case 2:
				return eCarta.DOS;
			case 3:
				return eCarta.TRES;
			case 4:
				return eCarta.CUATRO;
			case 5:
				return eCarta.CINCO;
			case 6:
				return eCarta.SEIS;
			case 7:
				return eCarta.SIETE;
			case 8:
				return eCarta.SOTA;
			case 9:
				return eCarta.CABALLO;				
			case 10:
				return eCarta.REY;
			default:
				return null;											
		}
	}	
}
