package com.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

class CJugador
{
	private String Nombre;
	private int NumeroJugador;
	private boolean Real;
	public CJugador()
	{			
	}
	public CJugador(String n,int nj,boolean r)
	{
		setNombre(n);
		setNumeroJugador(nj);
		setReal(r);
	}
	private void setNombre(String nombre) {
		Nombre = nombre;
	}
	private String getNombre() {
		return Nombre;
	}
	private void setNumeroJugador(int numeroJugador) {
		NumeroJugador = numeroJugador;
	}
	private int getNumeroJugador() {
		return NumeroJugador;
	}
	private void setReal(boolean real) {
		Real = real;
	}
	private boolean isReal() {
		return Real;
	}
}

class CPareja
{
	private CJugador JugadorA;
	private CJugador JugadorB;
	private String NombreGrupo;
	private int Puntos;
	private CBazas Baza;
	public CPareja(CJugador a,CJugador b)
	{
		setJugadorA(a);
		setJugadorB(b);
		setPuntos(0);
		setBaza(new CBazas());		
	}
	public void setJugadorA(CJugador jugadorA) {
		JugadorA = jugadorA;
	}
	public CJugador getJugadorA() {
		return JugadorA;
	}
	public void setJugadorB(CJugador jugadorB) {
		JugadorB = jugadorB;
	}
	public CJugador getJugadorB() {
		return JugadorB;
	}
	public void setNombreGrupo(String nombreGrupo) {
		NombreGrupo = nombreGrupo;
	}
	public String getNombreGrupo() {
		return NombreGrupo;
	}
	public void setPuntos(int puntos) {
		Puntos = puntos;
	}
	public int getPuntos() {
		return Puntos;
	}
	public void setBaza(CBazas baza) {
		Baza = baza;
	}
	public CBazas getBaza() {
		return Baza;
	}
}



public class CJuego 
{
	private List<CMano> Manos;	
	private List<CBazas> Bazas;
	public CJuego()
	{
		setManos(new ArrayList<CMano>(4));	
		setBazas(new ArrayList<CBazas>(2));	
	}
	public void setJugador(int numJugador)
	{
		
	}
	/**
	 * @param manos the manos to set
	 */
	public void setManos(List<CMano> manos) {
		Manos = manos;
	}

	/**
	 * @return the manos
	 */
	public List<CMano> getManos() {
		return Manos;
	}
	public void setBazas(List<CBazas> bazas) {
		Bazas = bazas;
	}
	public List<CBazas> getBazas() {
		return Bazas;
	}

}
