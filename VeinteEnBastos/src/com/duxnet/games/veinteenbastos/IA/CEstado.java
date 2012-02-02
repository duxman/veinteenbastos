package com.duxnet.games.veinteenbastos.IA;

import com.duxnet.games.veinteenbastos.*;
import com.duxnet.games.veinteenbastos.enums.ePalo;

public class CEstado 
{	 	 
	 private GlobalVar m_Glogal; //Clase Global donde se guardan algunos datos necesarios para la aplicacion
     private CJugada JugadaActual; //Indica la referencia a la jugada actual	 	 	 	 
	 private int TurnoActual; //Indica el turno en el que estamos
	 private int miTurno; //turno en el que me toca jugar
	 private int turnoComp; //Turno de mi compi	
	 private CCarta cartaComp; //Carta del Compi	 	 
	 private int JugadaTriunfo; //En Caso de Arrastre necesitare este dato para ver que tengo que hacer
	 private int nTriunfosIniciales; //Cuantos triunfos quedan por salir 	 
	 private CCarta Triunfo; //Carta que indica el triunfo
	 private ePalo PaloTriunfo; //Cuando vamos de ultima no disponemos de la carta para comparar el triunfo
	 private boolean ultimas; //Indica a la IA que vamos de ultimas
	 private boolean vueltas; //Indica a la IA que vamos de vueltas
	 private int num_ronda; //En que ronda estamos
	 private int nivel; //Valor con que nivel de juego esta jugando la IA
	 					//Debo pensar como definir esto ademas de solo contar cartas		
	 
	public CEstado()
	{
		setGlogal(GlobalVar.getInstance());		 
	}
	public  void init(int nJugador, int puntos1, int puntos2, CCarta Tr, boolean ult, boolean vuel, int ronda, int difi)
	 {		 		 		 		 		 	
		setTurnoActual(1);
		setMiTurno(0);
		setTurnoComp(0);
		setJugadaTriunfo(0);
		setnTriunfosIniciales(0);
		setTriunfo(new  CCarta(Tr.getIdCarta(), Tr.getPaloCarta().getId(), Tr.getImagenCarta(), Tr.getFondoCarta()));
		setUltimas(ult);
		setVueltas(vuel);
		setNum_ronda(ronda);
		setNivel(difi);		 		
	}
	public GlobalVar getGlogal() 
	{
		return m_Glogal;
	}
	private void setGlogal(GlobalVar glogal) {
		m_Glogal = glogal;
	}
	CJugada getJugadaActual() {
		return JugadaActual;
	}
	void setJugadaActual(CJugada jugadaActual) {
		JugadaActual = jugadaActual;
	}
	int getTurnoActual() {
		return TurnoActual;
	}
	void setTurnoActual(int turnoActual) {
		TurnoActual = turnoActual;
	}
	int getMiTurno() {
		return miTurno;
	}
	void setMiTurno(int miTurno) {
		this.miTurno = miTurno;
	}
	int getTurnoComp() {
		return turnoComp;
	}
	void setTurnoComp(int turnoComp) {
		this.turnoComp = turnoComp;
	}
	CCarta getCartaComp() {
		return cartaComp;
	}
	void setCartaComp(CCarta cartaComp) {
		this.cartaComp = cartaComp;
	}
	int getJugadaTriunfo() {
		return JugadaTriunfo;
	}
	void setJugadaTriunfo(int jugadaTriunfo) {
		JugadaTriunfo = jugadaTriunfo;
	}
	int getnTriunfosIniciales() {
		return nTriunfosIniciales;
	}
	void setnTriunfosIniciales(int nTriunfosIniciales) {
		this.nTriunfosIniciales = nTriunfosIniciales;
	}
	CCarta getTriunfo() {
		return Triunfo;
	}
	void setTriunfo(CCarta triunfo) {
		Triunfo = triunfo;
	}
	ePalo getPaloTriunfo() {
		return PaloTriunfo;
	}
	void setPaloTriunfo(ePalo paloTriunfo) {
		PaloTriunfo = paloTriunfo;
	}
	boolean isUltimas() {
		return ultimas;
	}
	void setUltimas(boolean ultimas) {
		this.ultimas = ultimas;
	}
	boolean isVueltas() {
		return vueltas;
	}
	void setVueltas(boolean vueltas) {
		this.vueltas = vueltas;
	}
	int getNum_ronda() {
		return num_ronda;
	}
	void setNum_ronda(int num_ronda) {
		this.num_ronda = num_ronda;
	}
	int getNivel() {
		return nivel;
	}
	void setNivel(int nivel) {
		this.nivel = nivel;
	}
	 
}
