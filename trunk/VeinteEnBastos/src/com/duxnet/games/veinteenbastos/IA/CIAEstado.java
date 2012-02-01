package com.duxnet.games.veinteenbastos.IA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.duxnet.games.veinteenbastos.*;


public class CIAEstado 
{
	 CMano Mano; /*Contendr� las seis cartas de la mano de la IA*/
	 List<CPalo> Palos;
	 CPalo PaloTriunfo;
	 CJugada Mesa;	 	 	 	 
	 int turno; /*Indica el turno en el que estamos*/
	 int miTurno; /*Guarda el turno en que la IA echa carta*/
	 int turnoComp; /*Guarda el turno del compa�ero*/	
	 CCarta cartaComp; /*Guarda la carta echada por el compa�ero*/	 
	 /*Estos datos estar�n apuntados desde la lista Palos*/
	 /*Est�n aqu� por si hay que hacer alguna consulta*/
	 int JugadaTriunfo; /*Casos particulares en el arrastre*/
	 int nTriunfosIniciales; /*Triunfos con los que inici� el arrastre*/
	 CBaza misPuntos; /*Puntero a los puntos de su equipo*/
	 CBaza susPuntos; /*Puntero los puntos del otro equipo*/
	 CCarta Triunfo; /*Puntero a la carta de triunfo*/
	 boolean ultimas; /*Puntero al booleano de las ultimas*/
	 boolean vueltas; /*Puntero al booleano de las vueltas*/
	 int num_ronda; /*Puntero al contador de rondas*/
	 boolean dificil; /*Booleano que indica si la IA esta en modo dificil o facil*/
	
	 private GlobalVar m_Glogal;
	 public CIAEstado()
	 {
		 setGlogal(GlobalVar.getInstance());		 
	 }
	public  void init(int nJugador, int puntos1, int puntos2, CCarta Tr, boolean ult, boolean vuel, int ronda, boolean difi)
	 {		 
		 Palos=new ArrayList<CPalo>();
		 Iterator<CPalo> it = getGlogal().getBaraja().getPalos().iterator();
		 while(it.hasNext())
		 {
			 CPalo p=it.next();
			 p.getDatos().as=0;
			 p.getDatos().tres=0;
			 p.getDatos().sota=0;
			 p.getDatos().caballo=0;
			 p.getDatos().rey=0;
			 p.getDatos().medianas = 0;
			 p.getDatos().pequenas = 0;
			 p.getDatos().haycante = false;
			 p.getDatos().quedanporsalir = 10;
			 
			 if(p.getEPalo()!=Tr.getPaloCarta())
				 Palos.add(p);			 
			 else
				 PaloTriunfo=new CPalo(p.getPaloBaraja(),p.getBitmap());
		 }	
		 
		 Mano = new CMano();		 		
		 turno =1;
		 miTurno = 0;
		 turnoComp = 0;
		 JugadaTriunfo = 0;
		 nTriunfosIniciales = 0;
		 Triunfo = new  CCarta(Tr.getIdCarta(), Tr.getPaloCarta().getId(), Tr.getImagenCarta(), Tr.getFondoCarta());
		 ultimas = ult;
		 vueltas = vuel;
		 num_ronda = ronda;
		 dificil = difi;
		 
		 if(nJugador==0 || nJugador==2)
		 {
			 misPuntos= new CBaza();
			 misPuntos.setPuntos(puntos1);
			 susPuntos= new CBaza();
			 susPuntos.setPuntos(puntos2);			 
		 }
		 else 
		 {
			 misPuntos= new CBaza();
			 misPuntos.setPuntos(puntos2);
			 susPuntos= new CBaza();
			 susPuntos.setPuntos(puntos1);
		 }
	}
	private GlobalVar getGlogal() {
		return m_Glogal;
	}
	private void setGlogal(GlobalVar glogal) {
		m_Glogal = glogal;
	}
	 
}
