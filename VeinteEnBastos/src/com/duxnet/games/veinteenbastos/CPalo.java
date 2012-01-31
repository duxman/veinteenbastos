package com.duxnet.games.veinteenbastos;
import android.graphics.Bitmap;
import com.duxnet.games.veinteenbastos.IA.*;
import com.duxnet.games.veinteenbastos.enums.ePalo;



public class CPalo extends CListaCartas
{   
	private int PaloBaraja; 
	private Bitmap bmpPalo;
	private CIADatos m_Datos;
	public CPalo(int Palo,Bitmap palo)
	{	
		setPaloBaraja(Palo);
		setDatos(new CIADatos());
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
	public ePalo getEPalo() 
	{
		return ePalo.getPalo(PaloBaraja);
	}
	public CIADatos getDatos() {
		return m_Datos;
	}
	public void setDatos(CIADatos datos) {
		m_Datos = datos;
	}
}
