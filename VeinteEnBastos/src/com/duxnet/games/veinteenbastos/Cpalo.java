package com.duxnet.games.veinteenbastos;
import android.graphics.Bitmap;



public class Cpalo 
{   
	private int PaloBaraja; 
	private Bitmap bmpPalo;
	public Cpalo(int Palo,Bitmap palo)
	{	
		setPaloBaraja(Palo);
		bmpPalo=palo;	
	}	
	public Bitmap getBitmap()
	{
		return bmpPalo;
	}
	public int getPaloBaraja() {
		return PaloBaraja;
	}
	public void setPaloBaraja(int paloBaraja) {
		PaloBaraja = paloBaraja;
	}
}
