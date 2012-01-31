package com.duxnet.games.veinteenbastos.IA;

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
}
