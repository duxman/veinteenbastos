
/*********************************************************************************************/
/* Funcion que devuelve la carta de descarte seg�n un algoritmo general (v�lido casi siempre)*/
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*	    carta: puntero a la carta de descarte					     */
/*********************************************************************************************/


/*********************************************************************************************/
/* Funcion que devuelve la carta de descarte evitando descartarse del mismo palo que el      */
/* compa�ero (v�lido s�lo si turno es igual a tres o cuatro)
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*	    carta: puntero a la carta de descarte					     */
/*********************************************************************************************/


/*********************************************************************************************/
/* Funcion que devuelve la carta de descarte haciendo especial incapi� en las cartas	     */
/* solitarias (v�lido s�lo si ronda es igual a cuatro)			     		     */
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*	    carta: puntero a la carta de descarte					     */
/*********************************************************************************************/
void DescarteEspecial(struct tpEstadoIA *EstadoIA, struct tpcarta *Carta)
{
 if (EstadoIA->Palo1.N == 1){/*la carta suelta m�s peque�a y distinta de triunfo, si la hay, estara en Palo1*/
   if ((EstadoIA->Palo1.Datos->as != '1') && (EstadoIA->Palo1.Datos->tres != '1')){
     /*Nos aseguramos de que no es una bresca*/
     *Carta = EstadoIA->Palo1.Palo[0];
   }
   else {
     DescarteGeneral(EstadoIA,Carta);
   }
 }
 else {
   DescarteGeneral(EstadoIA,Carta);
 }
}

/*********************************************************************************************/
/* Funcion que devuelve la carta de descarte, llama a una de las tres anteriores segun las   */
/* circunstancias y el estado de la partida						     */
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*	    carta: puntero a la carta de descarte					     */
/*********************************************************************************************/
void Descarte(struct tpEstadoIA *EstadoIA, struct tpcarta *Carta)
{
 if ((*EstadoIA->num_ronda != 4) && (EstadoIA->turno > 2) && (EstadoIA->dificil == 1)){
   DescartarCartaDiferente(EstadoIA,Carta);
   }
 else if ((*EstadoIA->num_ronda == 4) && (*EstadoIA->ultimas == 0) && (EstadoIA->dificil == 1)){
   DescarteEspecial(EstadoIA,Carta);
   }
 else {
   DescarteGeneral(EstadoIA,Carta);
 }
}

