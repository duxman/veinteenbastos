package com.duxnet.games.veinteenbastos;

import android.graphics.Canvas;
import android.graphics.Point;

import com.duxnet.games.veinteenbastos.IA.CEstado;
import com.duxnet.games.veinteenbastos.enums.ePosicion;

public class CJugador extends CEstado
{
	private String m_Nombre;
	private int m_NumeroJugador;
	private boolean m_Real;
	private CMano m_mano;
	private ePosicion m_posicion;		
	private Point m_lugar;
	private boolean Horizontal;
	private Point p;
	private Point c;
	
	public CJugador(String n,int nj,boolean r,ePosicion e)
	{				
		super();
		c=getGlogal().getDimCartas();
		setNombre(n);
		setNumeroJugador(nj);
		setReal(r);
		setPosicion(e);
		setMano(new CMano());
		setLugar(new Point());
		
	}
	public void Pintar(Canvas canvas)
	{	
		Point tmppos=new Point(getLugar());
		if(getPosicion()==ePosicion.ABAJO)
			getMano().Pintar(true, canvas, c.y, c.x, tmppos.x, tmppos.y,Horizontal,c.x);
		else
			getMano().Pintar(false, canvas, c.y, c.x, tmppos.x, tmppos.y,Horizontal);
	}
	private void setNombre(String nombre) 
	{
		m_Nombre = nombre;
	}
	@SuppressWarnings("unused")
	private String getNombre() 
	{
		return m_Nombre;
	}
	private void setNumeroJugador(int numeroJugador) {
		m_NumeroJugador = numeroJugador;
	}
	@SuppressWarnings("unused")
	private int getNumeroJugador() 
	{
		return m_NumeroJugador;
	}
	public void setReal(boolean real) {
		m_Real = real;
	}
	public boolean isReal() 
	{
		return m_Real;
	}
	public void setMano(CMano mano) {
		this.m_mano = mano;
	}
	public CMano getMano() {
		return m_mano;
	}
	public ePosicion getPosicion() {
		return m_posicion;
	}
	public void setPosicion(ePosicion posicion) {
		m_posicion = posicion;
	}
	public Point getLugar() 
	{
		
		int numcartas=getMano().size();
		p=GlobalVar.getInstance().getDimPantalla();
		switch(getPosicion())
		{			
			case ARRIBA:
				m_lugar.x=(p.x/2)-(c.x*numcartas)/3;
				m_lugar.y=10;
				Horizontal=true;
			break;
			case ABAJO:
				m_lugar.x=(int)((p.x/2)-(c.x*numcartas)/1.5);
				m_lugar.y=(p.y-c.y)-50;
				Horizontal=true;
			break;
			case IZQUIERDA:
				m_lugar.x=10;
				m_lugar.y=(p.y/2)-(c.y*numcartas)/3;
				Horizontal=false;
			break;
			case DERECHA:
				m_lugar.x=(p.x-c.x)-10;
				m_lugar.y=(p.y/2)-(c.y*numcartas)/3;
				Horizontal=false;
			break;
		}
		
		return m_lugar;
	}
	private void setLugar(Point lugar) {
		m_lugar = lugar;
	}
}
