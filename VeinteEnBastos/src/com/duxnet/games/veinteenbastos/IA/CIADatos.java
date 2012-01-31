package com.duxnet.games.veinteenbastos.IA;
public class CIADatos 
{
	 /// as, tres, rey, sota, caballo son 0 si lo tiene, 1 si no lo tiene,'2' ha salido
	 /// medianas indica el número de cartas que tiene entre 2 y caballo,
	 /// pequeñas indica el número de cartas que tiene entre 2 y 7
	 /// haycante se pone a 1 cuando sota y rey están a 1 y no se ha cantado	
	 public	int 	as; 
	 public	int 	tres;
	 public	int 	rey;
	 public	int 	sota;
	 public	int 	caballo;
	 public	int 	medianas;
	 public	int 	pequenas;
	 public	boolean haycante;
	 public	int 	quedanporsalir;/*Indica el numero de cartas del palo que quedan por salir*/
}
