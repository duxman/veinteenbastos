package com.duxnet.games.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class CBaraja extends CListaCartas
{
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 10;
	private int m_AltoCarta;
	private int m_AnchoCarta;
	private int m_AnchoPalo;
	private Bitmap m_BarajaCompleta;
	private Bitmap m_ReversoCarta;
	public List<CPalo> m_Palos;	
	
	//==================================================
	// Constructores
	//==================================================	
	public CBaraja(Bitmap Baraja,Bitmap ReversoCartas)
	{
		super();
		setBarajaCompleta(Baraja);
		setReversoCarta(ReversoCartas);
		setAltoCarta(getBarajaCompleta().getHeight() / BMP_ROWS);
		setAnchoCarta(getBarajaCompleta().getWidth() / BMP_COLUMNS);		
        GlobalVar.getInstance().setDimCartas(new Point(getAnchoCarta(),getAltoCarta()));
		setAnchoPalo(getBarajaCompleta().getWidth());
		CreaBaraja(false);
		Ordenar();
	}
	
	//==================================================
	// Metodos
	//==================================================	
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{		
		Pintar(vista, canvas, Alto, Ancho, X, Y, 0, false, true);		
	}
	
	private void CreaBaraja(boolean desordenada)
	{		
	    int srcY ;
	    int srcX ; 
	    m_Palos=new ArrayList<CPalo>();
		for(int contPalo=0;contPalo<4;contPalo++)
		{						
			srcY=contPalo*getAltoCarta();						
			Bitmap BmpPalo=Bitmap.createBitmap(getBarajaCompleta(), 0, srcY, getAnchoPalo(), getAltoCarta());
			m_Palos.add(new CPalo(contPalo, BmpPalo));			
			for(int contCarta=1;contCarta<11;contCarta++)
			{				
				srcX=(contCarta-1)*getAnchoCarta();				
				Bitmap bmpcarta=Bitmap.createBitmap(BmpPalo, srcX,0, getAnchoCarta(), getAltoCarta());				
				CCarta carta= new CCarta(contCarta,contPalo,bmpcarta,getReversoCarta());								
				if(desordenada)
					carta.DamePesoOrdenAleatorio();
				else
					carta.setPesoOrden(carta.getOrdinalCarta());
				add(carta);
			}		
		}		
	}
	//==================================================
	// Propiedades
	//==================================================
	public int getAnchoCarta() {
		return m_AnchoCarta;
	}
	public void setAnchoCarta(int anchoCarta) {
		m_AnchoCarta = anchoCarta;
	}
	public int getAltoCarta() {
		return m_AltoCarta;
	}
	public void setAltoCarta(int altoCarta) {
		m_AltoCarta = altoCarta;
	}
	public int getAnchoPalo() {
		return m_AnchoPalo;
	}
	public void setAnchoPalo(int anchoPalo) {
		m_AnchoPalo = anchoPalo;
	}
	public Bitmap getBarajaCompleta() {
		return m_BarajaCompleta;
	}
	public void setBarajaCompleta(Bitmap barajaCompleta) {
		m_BarajaCompleta = barajaCompleta;
	}
	public Bitmap getReversoCarta() {
		return m_ReversoCarta;
	}
	public void setReversoCarta(Bitmap reversoCarta) {
		m_ReversoCarta = reversoCarta;
	}		
}
