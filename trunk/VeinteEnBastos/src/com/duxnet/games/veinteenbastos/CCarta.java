package com.duxnet.games.veinteenbastos;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CCarta implements Comparable<Object>
{
	private Bitmap m_ImagenCarta;	
	private Bitmap m_FondoCarta;
	private long m_PesoOrden;
	private int m_Palo;
	private int m_id;
	private int m_valor;
	private String m_NombreCarta;
	private String m_NombrePalo;
	private String m_Carta;
	private int posX;
	private int posY;
	private int Ancho;
	private int Alto;
	private boolean marcada;
	
	private eLugar DondeEsta;
	
	public CCarta(int carta,int palo,Bitmap bmpcarta,Bitmap fondo) 
	{		
		setPalo(palo);
		setValor(eCarta.getCarta(carta).getValor());
		setId(eCarta.getCarta(carta).getId());
		setNombrePalo(ePalo.getPalo(palo).name());
		setNombreCarta(eCarta.getCarta(carta).name());
		setCarta(getNombreCarta() + " DE " + getNombrePalo());		
		setImagenCarta(bmpcarta);
		setFondoCarta(Bitmap.createScaledBitmap(fondo, fondo.getWidth()/2, fondo.getHeight()/2, true));
		setPesoOrden(10*(getPalo())+(getId()-1));
	}	
	public String getLog()
	{
		return getCarta()+" id carta : "+getId()+" valor carta : "+ getValor();
	}
	public long getPesoOrden() {
		return m_PesoOrden;
	}
	public void setPesoOrden(long l) {
		m_PesoOrden = l;
	}
	public int compareTo(Object another) 
	{
		if (this.getPesoOrden()== ((CCarta) another).getPesoOrden())
            return 0;
        else if ((this.getPesoOrden()) > ((CCarta) another).getPesoOrden())
            return 1;
        else
            return -1;
	}
	public int getPalo() {
		return m_Palo;
	}
	public void setPalo(int palo) {
		m_Palo = palo;
	}
	public int getId() {
		return m_id;
	}
	public void setId(int id) {
		m_id = id;
	}
	public int getValor() {
		return m_valor;
	}
	public void setValor(int valor) {
		m_valor = valor;
	}
	public Bitmap getImagenCarta() {
		return m_ImagenCarta;
	}
	public void setImagenCarta(Bitmap imagenCarta) {
		m_ImagenCarta = imagenCarta;
	}
	public String getNombreCarta() {
		return m_NombreCarta;
	}
	public void setNombreCarta(String nombreCarta) {
		m_NombreCarta = nombreCarta;
	}
	public String getNombrePalo() {
		return m_NombrePalo;
	}
	public void setNombrePalo(String nombrePalo) {
		m_NombrePalo = nombrePalo;
	}
	public String getCarta() {
		return m_Carta;
	}
	public void setCarta(String carta) {
		m_Carta = carta;
	}
	public Bitmap getFondoCarta() {
		return m_FondoCarta;
	}
	public void setFondoCarta(Bitmap fondoCarta) {
		m_FondoCarta = fondoCarta;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public eLugar getDondeEsta() {
		return DondeEsta;
	}
	public void setDondeEsta(eLugar dondeEsta) {
		DondeEsta = dondeEsta;
	}
	public boolean Tocada(float x2, float y2) 
    {
        return x2 > posX && x2 < posX + getAncho() && y2 > posY && y2 < posY + getAlto();
    }
	public int getAncho() {
		return Ancho;
	}
	public void setDimensiones(int ancho,int alto) 
	{
		Ancho = ancho;
		Alto = alto;
	}
	public int getAlto() {
		return Alto;
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{
		int posX=X;
    	int posY=Y;    	
    	Bitmap b;
    	
    	if(vista)
    		b=getImagenCarta();
    	else
    		b=getFondoCarta();	    		
    	canvas.drawBitmap(b, posX, posY, null);
    	setPosX(posX);
    	setPosY(posY);	     
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}	
}
