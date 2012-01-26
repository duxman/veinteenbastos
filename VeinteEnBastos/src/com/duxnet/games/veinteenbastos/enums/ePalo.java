package com.duxnet.games.veinteenbastos.enums;
public enum ePalo
{
	OROS(0),COPAS(1),ESPADAS(2),BASTOS(3);
	private final int id;
	ePalo(int id) {this.id=id;}
	public int getId(){return id;}	
	public String getPalo(){return this.name();}
	public static ePalo getPalo(int i)
	{
		switch(i)
		{				
			case 0:
				return ePalo.OROS;
			case 1:
				return ePalo.COPAS;
			case 2:
				return ePalo.ESPADAS;
			case 3:
				return ePalo.BASTOS;
		}
		return null;
	}
};