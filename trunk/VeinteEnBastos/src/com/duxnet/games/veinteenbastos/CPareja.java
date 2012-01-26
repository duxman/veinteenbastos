package com.duxnet.games.veinteenbastos;


class CPareja
{
	private CJugador m_JugadorA;
	private CJugador m_JugadorB;
	private String m_NombreGrupo;
	private int m_Puntos;
	private CBaza m_Baza;
	public CPareja(CJugador a,CJugador b)
	{
		setJugadorA(a);
		setJugadorB(b);
		setPuntos(0);
		setBaza(new CBaza());		
	}	
	public void setJugadorA(CJugador jugadorA) {
		m_JugadorA = jugadorA;
	}
	public CJugador getJugadorA() {
		return m_JugadorA;
	}
	public void setJugadorB(CJugador jugadorB) {
		m_JugadorB = jugadorB;
	}
	public CJugador getJugadorB() {
		return m_JugadorB;
	}
	public void setNombreGrupo(String nombreGrupo) {
		m_NombreGrupo = nombreGrupo;
	}
	public String getNombreGrupo() {
		return m_NombreGrupo;
	}
	public void setPuntos(int puntos) {
		m_Puntos = puntos;
	}
	public int getPuntos() {
		return m_Puntos;
	}
	public void setBaza(CBaza baza) {
		m_Baza = baza;
	}
	public CBaza getBaza() {
		return m_Baza;
	}
}