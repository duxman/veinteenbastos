package com.duxnet.games.veinteenbastos;

class CJugador
{
	private String m_Nombre;
	private int m_NumeroJugador;
	private boolean m_Real;
	private CMano m_mano;
	
	public CJugador(String n,int nj,boolean r)
	{
		setNombre(n);
		setNumeroJugador(nj);
		setReal(r);
		setMano(new CMano());
	}
	private void setNombre(String nombre) {
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
	private void setReal(boolean real) {
		m_Real = real;
	}
	@SuppressWarnings("unused")
	private boolean isReal() {
		return m_Real;
	}

	public void setMano(CMano mano) {
		this.m_mano = mano;
	}
	public CMano getMano() {
		return m_mano;
	}
}
