package com.duxnet.games.veinteenbastos;

public enum eCarta 
{
	AS(1,11),DOS(2,0),TRES(3,10),CUATRO(4,0),CINCO(5,0),SEIS(6,0),SIETE(7,0),SOTA(8,3),CABALLO(9,2),REY(10,4);
	private final int id;
	private final int valor;
	private eCarta(int i,int v) {id=i;valor=v;}
	public int getValor() {return valor;}
	public int getId() {return id;}
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
