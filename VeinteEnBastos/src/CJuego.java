/*package com.veinteenbastos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CJugador
{
	private String Nombre;
	private int NumeroJugador;
	private boolean Real;
	private CMano mano;
	public CJugador(String n,int nj,boolean r)
	{
		setNombre(n);
		setNumeroJugador(nj);
		setReal(r);
		setMano(new CMano());
	}
	private void setNombre(String nombre) {
		Nombre = nombre;
	}
	private String getNombre() 
	{
		return Nombre;
	}
	private void setNumeroJugador(int numeroJugador) {
		NumeroJugador = numeroJugador;
	}
	private int getNumeroJugador() 
	{
		return NumeroJugador;
	}
	private void setReal(boolean real) {
		Real = real;
	}
	private boolean isReal() {
		return Real;
	}

	public void setMano(CMano mano) {
		this.mano = mano;
	}

	public CMano getMano() {
		return mano;
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
	private List<CBazas> Bazas;
	private CPareja ParejaPar;
	private CPareja ParejaImpar;
	private CBaraja baraja;
	private CCartas CartaTriunfo;	
	
	public CJuego()
	{
		setBazas(new ArrayList<CBazas>(2));
		setParejaPar(new CPareja(new CJugador("Jugador2", 2, false),new CJugador("Jugador4",4,false)));
		setParejaImpar(new CPareja(new CJugador("Jugador1", 1, false),new CJugador("Jugador3",3,false)));
		baraja=new CBaraja(false, true, true);		
		
	}
	public void setBazas(List<CBazas> bazas) {
		Bazas = bazas;
	}
	public List<CBazas> getBazas() {
		return Bazas;
	}
	public void setParejaPar(CPareja parejaPar) {
		ParejaPar = parejaPar;
	}
	public CPareja getParejaPar() {
		return ParejaPar;
	}
	public void setParejaImpar(CPareja parejaImpar) {
		ParejaImpar = parejaImpar;
	}
	public CPareja getParejaImpar() {
		return ParejaImpar;
	}
	private CPareja quePareja(int jugador)
	{
		if (jugador%2!=0)
			return ParejaImpar;
		  else
			  return ParejaPar;
	}
	private CJugador queJugador(int jugador)
	{
		if (jugador%2!=0)
		{
			if(jugador==1)
				return ParejaImpar.getJugadorA();
			else
				return ParejaImpar.getJugadorB();
		}			
		else
		{
			if(jugador==2)
				return ParejaPar.getJugadorA();
			else
				return ParejaPar.getJugadorB();
		}
	}
	public void repartir(int quien)	
	{
		int jugador=quien;
		for(int itandas=1;itandas<3;itandas++)	
		{			
			for(int ijugadores=1;ijugadores<5;ijugadores++)
			{
				
				
				jugador=(jugador>4)?(ijugadores+jugador-5):(ijugadores+jugador-1);	
				for(int icartas=1;icartas<4;icartas++)	
				{						
					queJugador(jugador).getMano().AddCarta(baraja.DamePrimeraCarta());					
				}
				jugador++;
			}
		}		
		setCartaTriunfo(baraja.DamePrimeraCarta());			
	}
	public void jugada(int Mano)
	{
		int jugador=Mano;
		CJugada j = new CJugada(ePalos.GetPalo(getCartaTriunfo().palo),Mano);
		for(int i =1;i<5;i++)
		{
			jugador=(jugador>4)?(i+jugador-5):(i+jugador-1);	
			j.Add(jugador, queJugador(Mano).getMano().TirarCarta());
			jugador++;
		}
		int idquiengana=j.QuienGana();
		Iterator<CCartas> itr = j.getCartas().iterator();
        while(itr.hasNext())
        {
        	CCartas c = (CCartas)itr.next();
        	quePareja(idquiengana).getBaza().getBaza().add(c);   
        }
        quePareja(idquiengana).setPuntos(quePareja(idquiengana).getBaza().CuentaPuntos(false));   
	}
	public void setCartaTriunfo(CCartas cartaTriunfo) {
		CartaTriunfo = cartaTriunfo;
	}
	public CCartas getCartaTriunfo() {
		return CartaTriunfo;
	}

}
*/