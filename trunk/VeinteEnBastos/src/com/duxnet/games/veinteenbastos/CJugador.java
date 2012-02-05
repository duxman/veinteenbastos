package com.duxnet.games.veinteenbastos;

import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Point;

import com.duxnet.games.veinteenbastos.IA.CEstado;
import com.duxnet.games.veinteenbastos.IA.CIA;
import com.duxnet.games.veinteenbastos.enums.ePosicion;

public class CJugador extends CEstado implements Comparable<CJugador>
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
	private CIA MiCerebro;
	private int m_Turno;
	
	public CJugador(String n,int nj,boolean r,ePosicion e,int turno)
	{				
		super();
		c=getGlogal().getDimCartas();
		setNombre(n);
		setNumeroJugador(nj);
		setReal(r);
		setPosicion(e);
		setMano(new CMano());
		setLugar(new Point());
		setTurno(turno);
		if(!isReal())
			MiCerebro= new CIA(this);		
	}
	public void Procesando(CCarta c)
	{		
		///Implementar nuevo metodo al que se le pase ademas el nivel para contar mas o menos
		///Partimos de la premisa que con mas datos el sitema elegira mejor la carta a tirar
		MiCerebro.ActualizarJugada(c);
	}
	public CCarta MeToca()
	{
		return MiCerebro.TirarCarta();
		
		///Implementar los metodos de juego de los jugadores;		
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
	public int compareTo(CJugador another) 
	{
		if (this.getTurno()== another.getTurno())
            return 0;
        else if (this.getTurno()> another.getTurno())
            return 1;
        else
            return -1;
	}
	public int getTurno() {
		return m_Turno;
	}
	public void setTurno(int turno) {
		m_Turno = turno;
	}
}
