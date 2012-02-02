package com.duxnet.games.veinteenbastos.IA;
public class CDatos 
{
	/// as, tres, rey, sota, caballo son 0 si lo tiene, 1 si no lo tiene,'2' ha salido
	/// medianas indica el número de cartas que tiene entre 2 y caballo,
	/// pequeñas indica el número de cartas que tiene entre 2 y 7
	/// haycante se pone a 1 cuando sota y rey están a 1 y no se ha cantado	
	private 	int 	as; 
	private 	int 	tres;
	private 	int 	rey;
	private 	int 	sota;
	private 	int 	caballo;
	private 	int 	medianas;
	private 	int 	pequenas;
	private 	boolean haycante;
	//Indica el numero de cartas del palo que quedan por salir
	private 	int 	quedanporsalir;
	private 	int 	numcartas;
	
	public int getAs() {
		return as;
	}
	public void setAs(int as) {
		this.as = as;
	}
	public int getTres() {
		return tres;
	}
	public void setTres(int tres) {
		this.tres = tres;
	}
	public int getRey() {
		return rey;
	}
	public void setRey(int rey) {
		this.rey = rey;
	}
	public int getSota() {
		return sota;
	}
	public void setSota(int sota) {
		this.sota = sota;
	}
	public int getCaballo() {
		return caballo;
	}
	public void setCaballo(int caballo) {
		this.caballo = caballo;
	}
	public int getMedianas() {
		return medianas;
	}
	public void setMedianas(int medianas) {
		this.medianas = medianas;
	}
	public int getPequenas() {
		return pequenas;
	}
	public void setPequenas(int pequenas) {
		this.pequenas = pequenas;
	}
	public boolean isHaycante() {
		return haycante;
	}
	public void setHaycante(boolean haycante) {
		this.haycante = haycante;
	}
	public int getQuedanporsalir() {
		return quedanporsalir;
	}
	public void setQuedanporsalir(int quedanporsalir) {
		this.quedanporsalir = quedanporsalir;
	}
	public int getNumcartas() {
		return numcartas;
	}
	public void setNumcartas(int numcartas) {
		this.numcartas = numcartas;
	}
}
