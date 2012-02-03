/*************************************************************************/
/*									 */
/*	GUIÑOTE - PRACTICAS LABORATORIO DE PROGRAMACION 2004/05	   	 */
/*									 */
/*	Autores:			David Martinez Mercado		 */
/*					David Benito			 */
/*					Gonzalo Ruiz Manzanares		 */
/*					Jorge Roy Enfedaque		 */
/*									 */
/*		Módulo principal de la IA				 */
/*									 */
/*		Ultima modificacion:	20/01/2005 20:00		 */
/*									 */
/*************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "IAbrain.h"

/*********************************************************************************************/
/* Funcion que devuelve la carta que echa la IA de arrastre				     */
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*          Carta: estructura en donde se devuelve la carta seleccionada		     */	 
/* Salida:  Carta seleccionada en el puntero *Carta					     */
/*********************************************************************************************/
void Arrastre(struct tpEstadoIA *EstadoIA, struct tpcarta *Carta)
{
 bool yaesta;
 struct tpPaloX *miPalo;
 struct tpcarta CartaAux;
 int i;

 yaesta = 0;
 if (EstadoIA->turno == 1){
   if ((EstadoIA->nTriunfosIniciales == 6) && (EstadoIA->dificil == 1)){
     if (EstadoIA->JugadaTriunfo == 1){/*el mejor caso, 6 triunfos con el as, el 3 y el rey*/
       /*Arrastramos en todas las rondas*/
       *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
     }
     else if (EstadoIA->JugadaTriunfo == 2){/*6 triunfos, entre ellos el as y el tres*/
       /*Arrastramos en todas las rondas, primero con cartas altas y luego con bajas
        así nos aseguramos las 10 últimas*/
       if ((*EstadoIA->num_ronda == 5) || (*EstadoIA->num_ronda == 6)){
         *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
     else {/*tiene seis triunfos, pero no son altos, arrastramos con cartas bajas*/
       *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
     }
   }/*fin con seis triunfos iniciales*/

   if ((EstadoIA->nTriunfosIniciales == 5) && (EstadoIA->dificil == 1)){
     if (EstadoIA->JugadaTriunfo == 2){/*5 triunfos, entre ellos el as ,el tres y el rey*/
       /*Arrastramos primero con cartas altas (para quitar los triunfos), luego echamos la
         carta que no es triunfo y por ultimo echamos los triunfos que quedan*/
       if ((*EstadoIA->num_ronda == 5) || (*EstadoIA->num_ronda == 6) ||
       (*EstadoIA->num_ronda == 7)){
         *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
     else if (EstadoIA->JugadaTriunfo == 2){/*5 triunfos, entre ellos el as y el tres*/
       /*Misma táctica que para JugadaTriunfo == 1, pero sólo arrastramos las dos primeras rondas*/
       if ((*EstadoIA->num_ronda == 5) || (*EstadoIA->num_ronda == 6)){
         *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
     else if (EstadoIA->JugadaTriunfo == 3){
       /*tras la primera ronda, el único que nos ha echado triunfo es el compañero por
        lo que no seguimos arrastrando*/
       *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
     }
     else if (EstadoIA->JugadaTriunfo == 4){/*Han salido tres triunfos en la primera ronda
       echa triunfos pequeños para quitar los triunfos que quedan y se queda el triunfo
       más alto para asegurarse las últimas*/
       if ((*EstadoIA->num_ronda != 9) && (*EstadoIA->num_ronda != 10)){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
     else{/*caso por defecto, tenemos cinco triunfos pero no tenemos ninguno alto
          arrastramos con los triunfos pequeños con la esperanza de quitar los otros
          triunfos de la mesa y nos guardamos el triunfo más alto para elfinal*/
       if ((*EstadoIA->num_ronda != 9) && (*EstadoIA->num_ronda != 10)){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
   }/*fin con 5 triunfos iniciales*/

   if ((EstadoIA->nTriunfosIniciales == 4) && (EstadoIA->dificil == 1)){
     if (*EstadoIA->num_ronda == 5){
       if ((EstadoIA->JugadaTriunfo == 1)
           && (EstadoIA->Mano.carta[0].valor >= '8') && (EstadoIA->Mano.carta[1].valor >= '8')){
         /*Las dos cartas que acompañan a los triunfos son brescas, en vez de seguir el caso
          general, realizamos la jugada 5, que consiste en arrastrar primero con los triunfos altos
          para quitar los triunfos, posteriormente echar las brescas, que como no hay triunfos hay
          pocas posibilidades de que las maten y guardarnos el último triunfo para las últimas*/
         EstadoIA->JugadaTriunfo = 5;
       }
     }
     if (EstadoIA->JugadaTriunfo == 5){
       if (*EstadoIA->num_ronda <= 7){
         *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
       }
       else{
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
     else if (EstadoIA->JugadaTriunfo == 3){ /*Igual que en caso de triunfos iniciales 5*/
       *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
     }
     else if (EstadoIA->JugadaTriunfo == 6){
       /*Realizando el primer arrastre de la jugada 5, resulta que han salido muchos
        triunfos, cambiamos el plan inicial, y echamos las brescas en el segundo turno*/
       if ((*EstadoIA->num_ronda == 6) || (*EstadoIA->num_ronda >= 9)){
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
       else if(*EstadoIA->num_ronda == 7){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
       else if(*EstadoIA->num_ronda == 8){
         *Carta = EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1];yaesta = 1;
       }
     }
     else{/*caso general*/
       if (*EstadoIA->num_ronda ==5){
         /*Intentamos echar una carta que no sea triunfo, pero ponemos como condición
         que falten de salir 4 cartas o más de ese palo, para garantizar el menor número
         de posibilidades de que los otros echen triunfo*/
         if ((EstadoIA->Palo1.Datos->quedanporsalir >= 4) && (EstadoIA->Palo1.Datos->quedanporsalir >= EstadoIA->Palo2.Datos->quedanporsalir)){
           *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
         }
         else if (EstadoIA->Palo2.N > 1){
            if (EstadoIA->Palo2.Datos->quedanporsalir>=4){
              *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
             }
         }
         if (yaesta == 0){/*Si no se dan las condiciones anteriores echamos el triunfo más pequeño*/
           *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
         }
       }
       else if (*EstadoIA->num_ronda == 6){
         /*Tenemos dos casos, o que hayamos echado una carta distinta de triunfo en la
          primera ronda, o que hayamos salido con triunfo. En el primer caso, echamos triunfo
          en el segundo, echamos carta distinta de triunfo.*/
         if ((EstadoIA->Mano.carta[0].palo != EstadoIA->Triunfo->palo) && (EstadoIA->Mano.carta[1].palo != EstadoIA->Triunfo->palo)){
             /*Hemos salido de triunfo en la primera ronda, echamos distinto de triunfo*/
           if (EstadoIA->Mano.carta[0].valor >= EstadoIA->Mano.carta[1].valor){
             *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
           }
           else {
             *Carta = EstadoIA->Mano.carta[1]; yaesta = 1;
           }
         }
         if (yaesta == 0){/*Echamos el triunfo más pequeño*/
           *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
         }
       }
       else if (*EstadoIA->num_ronda == 7){
       /*Arrastramos de triunfo para intentar descartarlos de la partida*/
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
       else { /*Echamos la carta distinta de triunfo que queda y a partir de ahí en orden ascendente*/
         *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
       }
     }
   }/*fin con 4 triunfos iniciales*/

   if ((EstadoIA->nTriunfosIniciales == 3) && (EstadoIA->dificil == 1)){
     if (*EstadoIA->num_ronda <= 6){
       /*Intentamos echar bresca de cuyo palo queden todavía, al menos, 4 cartas por salir*/
       if (((EstadoIA->Palo1.Datos->as == '1') || ((EstadoIA->Palo1.Datos->tres == '1') && (EstadoIA->Palo1.Datos->as == '0')))
           && (EstadoIA->Palo1.Datos->quedanporsalir >= 4)){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       if (((EstadoIA->Palo2.Datos->as == '1') || ((EstadoIA->Palo2.Datos->tres == '1') && (EstadoIA->Palo2.Datos->as == '0')))
           && (EstadoIA->Palo2.Datos->quedanporsalir >= 4) && (yaesta == 0)){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       if (((EstadoIA->Palo3.Datos->as == '1') || ((EstadoIA->Palo3.Datos->tres == '1') && (EstadoIA->Palo3.Datos->as == '0')))
           && (EstadoIA->Palo3.Datos->quedanporsalir >= 4) && (yaesta == 0)){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }
       if (yaesta == 0){
         Descarte(EstadoIA,Carta);
         yaesta = 1;
       }
     }
     else if (EstadoIA->turno <= 4){
       /*Quedan pocas cartas, intentamos arrastrar con algún triunfo para que se descarten*/
       if (EstadoIA->PaloT.N >= 1){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
       else{
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
     else{ /*Echamos las cartas que quedan, empezando por la más baja*/
       *Carta = EstadoIA->Mano.carta[0]; yaesta = 1;
     }
   }/*fin con 3 triunfos iniciales*/

   if (yaesta == 0){/*Resto de casos, la IA no puede tomar muchas iniciativas, si no puede echar bresca, se descarta*/
     if (*EstadoIA->num_ronda <= 8){
       /*Intentamos echar bresca de cuyo palo queden todavía, al menos, 4 cartas por salir*/
       if (((EstadoIA->Palo1.Datos->as == '1') || ((EstadoIA->Palo1.Datos->tres == '1') && (EstadoIA->Palo1.Datos->as == '0')))
           && (EstadoIA->Palo1.Datos->quedanporsalir >= 4)){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       if (((EstadoIA->Palo2.Datos->as == '1') || ((EstadoIA->Palo2.Datos->tres == '1') && (EstadoIA->Palo2.Datos->as == '0')))
           && (EstadoIA->Palo2.Datos->quedanporsalir >= 4) && (yaesta == 0)){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       if (((EstadoIA->Palo3.Datos->as == '1') || ((EstadoIA->Palo3.Datos->tres == '1') && (EstadoIA->Palo3.Datos->as == '0')))
           && (EstadoIA->Palo3.Datos->quedanporsalir >= 4) && (yaesta == 0)){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }
       if (yaesta == 0){
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
     else {
       *Carta = EstadoIA->Mano.carta[0]; yaesta =  1;
     }
   }/*fin de otros casos*/
 }/*fin del turno 1*/

 if (EstadoIA->turno == 2){
   if (EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo){/*el jugador 1 no ha salido de triunfo*/
     /*Buscamos el PaloX cuyo palo coincide con el palo de carta1*/
     if (EstadoIA->Palo1.N > 0){
       if (EstadoIA->Palo1.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo1; yaesta = 1;
       }
     }
     if (EstadoIA->Palo2.N > 0){
       if (EstadoIA->Palo2.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo2; yaesta = 1;
       }
     }
     if (EstadoIA->Palo3.N > 0){
       if (EstadoIA->Palo3.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo3; yaesta = 1;
       }
     }
     if (yaesta == 1){ /*Si yaesta == 0 entonces no hay cartas del palo de arrastre en la mano*/
       yaesta = 0;
       if (miPalo->Palo[miPalo->N-1].valor < EstadoIA->Mesa.jugador1.palo){
         /*La carta más alta disponible del palo de arrastre es pequeña, la IA
          no puede montar y se descarta de la carta más pequeña posible del palo de arrastre*/
          *Carta = miPalo->Palo[0]; yaesta = 1;
       }
       else { /*Alguna carta puede montar a carta1*/
         /*Si tenemos la carta más alta del palo de arrastre, la echamos*/
         if ((miPalo->Datos->as == '1') || ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '1')) ||
             ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '2') && (miPalo->Datos->rey == '1') && (miPalo->Datos->haycante == 0)) ||
             ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '2') && (miPalo->Datos->rey == '2') && (miPalo->Datos->sota == '1'))){
           *Carta = miPalo->Palo[miPalo->N-1]; yaesta = 1;
         }
         else{/*Si no es posible, se echa la primera carta que monte, aunque no sea la más alta*/
           for (i=0;i<miPalo->N;i++){
             if ((miPalo->Palo[i].valor > EstadoIA->Mesa.jugador1.valor) && (yaesta ==0)){
               *Carta = miPalo->Palo[i]; yaesta = 1;
             }
           }
         }
       }
     }
     /*Si yaesta == 0 quiere decir que no tenemos ninguna carta del palo de arrastre
     en la mano y como carta1 distinto de triunfo, echamos el triunfo más pequeño*/
     if ((EstadoIA->PaloT.N >= 1) && (yaesta == 0)){
       *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
     }
   }/*fin de carta1 != triunfo*/
   else{/*carta1.palo == triunfo, buscamos montar con el triunfo más pequeño posible*/
     for (i=0;i<EstadoIA->PaloT.N;i++){
       if ((yaesta == 0) && (EstadoIA->PaloT.Palo[i].valor > EstadoIA->Mesa.jugador1.valor)){
         *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
       }
     }
     /*Si yaesta == 0, entonces no hemos podido montar, pero aun así hay que intentar
     echar algún triunfo para continuar el arrastre, echamos el más bajo*/
     if ((EstadoIA->PaloT.N >= 1) && (yaesta == 0)){
       *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
     }
   }
   /*Llegados aquí, si yaesta == 0, entonces quiere decir que no podemos echar ni triunfo
     ni continuar el arrastre porque no tenemos cartas de ese mismo palo, entonces descarte*/
   if (yaesta == 0){
     Descarte(EstadoIA,Carta); yaesta = 1;
   }
 }/*fin turno 2*/

 if (EstadoIA->turno == 3){
   /*Determinamos si el jugador2 ha conseguido montar y guardamos en CartaAux la carta más alta*/
   if (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador2,EstadoIA->Triunfo->palo)){
     CartaAux = EstadoIA->Mesa.jugador1;
   }
   else{
     CartaAux = EstadoIA->Mesa.jugador2;
   }
   if (EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo){/*el jugador 1 no ha salido de triunfo*/
     /*Buscamos el PaloX cuyo palo coincide con el palo de carta1*/
     if (EstadoIA->Palo1.N > 0){
       if (EstadoIA->Palo1.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo1; yaesta = 1;
       }
     }
     if (EstadoIA->Palo2.N > 0){
       if (EstadoIA->Palo2.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo2; yaesta = 1;
       }
     }
     if (EstadoIA->Palo3.N > 0){
       if (EstadoIA->Palo3.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo3; yaesta = 1;
       }
     }
     if (yaesta == 1){ /*Si yaesta == 0 entonces no hay cartas del palo de arrastre en la mano*/
       yaesta = 0;
       if (cartaMayor(&CartaAux,&miPalo->Palo[miPalo->N-1], EstadoIA->Triunfo->palo)){
         /*La carta más alta disponible del palo de arrastre es pequeña o bien carta2 es triunfo, la IA
          no puede montar y se descarta de la carta más pequeña posible del palo de arrastre*/
          *Carta = miPalo->Palo[0]; yaesta = 1;
       }
       else { /*Alguna carta puede montar a CartaAux*/
         /*Si tenemos la carta más alta del palo de arrastre, la echamos, pero sólo si estamos
          en los dos primeros arrastres. El segundo jugador puede permitirse montar más
          libremente con cartas altas porque el cuarto jugador puede ganar la baza si el tercero
          monta y, si no lo hace, entonces el cuarto aun puede cargar con más puntos*/
         if ((*EstadoIA->num_ronda <= 6) && ((miPalo->Datos->as == '1') ||
             ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '1')) || ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '2') && (miPalo->Datos->rey == '1') && (miPalo->Datos->haycante == 0)) ||
             ((miPalo->Datos->as == '2') && (miPalo->Datos->tres == '2') && (miPalo->Datos->rey == '2') && (miPalo->Datos->sota == '1')))){
           *Carta = miPalo->Palo[miPalo->N-1]; yaesta = 1;
         }
         else{/*Si no es posible, se echa la primera carta que monte, aunque no sea la más alta*/
           for (i=0;i<miPalo->N;i++){
             if ((miPalo->Palo[i].valor > EstadoIA->Mesa.jugador1.valor) && (yaesta ==0)){
               *Carta = miPalo->Palo[i]; yaesta = 1;
             }
           }
         }
       }
     }
     /*Si yaesta == 0 quiere decir que no tenemos ninguna carta del palo de arrastre
     en la mano y como carta1 distinto de triunfo, echamos el triunfo más pequeño.
     Si carta2 es triunfo, entonces la montaremos si es posible, si no, se descarta*/
     if ((EstadoIA->PaloT.N >= 1) && (yaesta == 0) && (EstadoIA->Mesa.jugador2.palo != EstadoIA->Triunfo->palo)){
       *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
     }
     else if (yaesta == 0){/*Hay que montar a carta2*/
       for (i=0;i<EstadoIA->PaloT.N;i++){
         if ((yaesta == 0) && (EstadoIA->PaloT.Palo[i].valor > EstadoIA->Mesa.jugador2.valor)){
           *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
         }
       }
       if (yaesta == 0) {/*nos descartamos*/
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
   }/*fin de carta1 != triunfo*/
   else{/*carta1.palo == triunfo, buscamos montar con el triunfo más pequeño posible
          a CartaAux que es la mas alta de carta1 y carta2*/
     for (i=0;i<EstadoIA->PaloT.N;i++){
       if ((yaesta == 0) && (EstadoIA->PaloT.Palo[i].valor > CartaAux.valor)){
         *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
       }
     }
     /*Si yaesta == 0, entonces no hemos podido montar, pero aun así hay que intentar
     echar algún triunfo para continuar el arrastre, echamos el más bajo*/
     if ((EstadoIA->PaloT.N >= 1) && (yaesta == 0)){
       *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
     }
   }
   /*Llegados aquí, si yaesta == 0, entonces quiere decir que no podemos echar ni triunfo
     ni continuar el arrastre porque no tenemos cartas de ese mismo palo, entonces descarte*/
   if (yaesta == 0){
     Descarte(EstadoIA,Carta); yaesta = 1;
   }
 }/*fin turno 3*/

 if (EstadoIA->turno == 4){
   if (EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo){/*el jugador 1 no ha salido de triunfo*/
     /*Buscamos el PaloX cuyo palo coincide con el palo de carta1*/
     if (EstadoIA->Palo1.N > 0){
       if (EstadoIA->Palo1.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo1; yaesta = 1;
       }
     }
     if (EstadoIA->Palo2.N > 0){
       if (EstadoIA->Palo2.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo2; yaesta = 1;
       }
     }
     if (EstadoIA->Palo3.N > 0){
       if (EstadoIA->Palo3.Palo[0].palo == EstadoIA->Mesa.jugador1.palo){
         miPalo = &EstadoIA->Palo3; yaesta = 1;
       }
     }
     if (yaesta == 1){ /*Si yaesta == 0 entonces no hay cartas del palo de arrastre en la mano*/
       yaesta = 0;
       if (vaNuestra(EstadoIA)){ /*Si va nuestra echamos lo más alto que tenemos*/
         *Carta = miPalo->Palo[miPalo->N-1]; yaesta = 1;
       }
       /*Si no va nuestra puede ser porque carta3 es triunfo, en cuyo caso nos descartamos
       de la más baja, o bien porque carta1 o carta3 sean las más altas, y entonces las
       montaríamos con lo más alto que tuvieramos, si no puede se descarta de la más pequeña*/
       if ((yaesta == 0) && (EstadoIA->Mesa.jugador3.palo == EstadoIA->Triunfo->palo)){
         *Carta = miPalo->Palo[0]; yaesta = 1;
       }
       /*carta3 no es triunfo*/
       if (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador3,EstadoIA->Triunfo->palo)){
         CartaAux = EstadoIA->Mesa.jugador1;
       }
       else{
         CartaAux = EstadoIA->Mesa.jugador3;
       } /*Intentamos montar con la más alta*/
       if ((yaesta == 0) && (miPalo->Palo[miPalo->N-1].valor > CartaAux.valor)){
         *Carta = miPalo->Palo[miPalo->N-1]; yaesta = 1;
       }
       else if (yaesta == 0){/*No es posible, echamos la más baja*/
         *Carta = miPalo->Palo[0]; yaesta = 1;
       }
     }
     /*No tenemos cartas del palo de arrastre, si no vaNuestra hay que intentar montar con triunfo y si
     no es posible se descarta. Si vaNuestra, entonces echa puntos.*/
     if ((yaesta == 0) && (vaNuestra(EstadoIA) == 0)){
       if (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador3,EstadoIA->Triunfo->palo)){
         CartaAux = EstadoIA->Mesa.jugador1;
       }
       else {
         CartaAux = EstadoIA->Mesa.jugador3;
       }
       for (i=0;i<EstadoIA->PaloT.N;i++){
         if ((yaesta == 0) && (cartaMayor(&EstadoIA->PaloT.Palo[i],&CartaAux,EstadoIA->Triunfo->palo))){
           *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
         }
       }
       if (yaesta == 0){
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
     else if (yaesta == 0){/*vaNuestra, entonces echamos puntos*/
       /*buscamos ases*/
       if (EstadoIA->Palo1.Datos->as == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->as == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->as == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       } /*buscamos treses*/
       else if (EstadoIA->Palo1.Datos->tres == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->tres == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->tres == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       } /*buscamos reyes sin romper cantes*/
       else if ((EstadoIA->Palo1.Datos->rey == '1') && (EstadoIA->Palo1.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if ((EstadoIA->Palo2.Datos->rey == '1') && (EstadoIA->Palo2.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if ((EstadoIA->Palo3.Datos->rey == '1') && (EstadoIA->Palo3.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }/*buscamos sotas*/
       else if (EstadoIA->Palo1.Datos->sota == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->sota == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->sota == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }/*Buscamos caballos*/
       else if (EstadoIA->Palo1.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }
       /*Si yaesta == 0, no podemos echar ningún punto, nos descartamos*/
       if (yaesta == 0){
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
   }/*fin de carta1 != triunfo*/
   else{/*En este punto, quiere decir que se arrastra de triunfo. Lo primero intentamos echar
     triunfo, que es el palo de arrastre, si no y si además vaNuestra entonces intentamos echar puntos.
     Si no vaNuestra, se descarta*/
     /*Intentamos echar triunfo, si vaNuestra el más bajo, si no vaNuestra, montamos*/
     if (vaNuestra(EstadoIA)){
       if (EstadoIA->PaloT.N > 0){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
     }
     else{
       /*Primero, determinamos la carta que tenemos que montar, la más alta de la mesa*/
       if (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador3,EstadoIA->Triunfo->palo)){
         CartaAux = EstadoIA->Mesa.jugador1;
       }
       else {
         CartaAux = EstadoIA->Mesa.jugador3;
       }
       for (i=0;i<EstadoIA->PaloT.N;i++){
         if ((yaesta == 0) && (cartaMayor(&EstadoIA->PaloT.Palo[i],&CartaAux,EstadoIA->Triunfo->palo))){
           *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
         }
       }
       /*Si no hemos conseguido montar, entonces intentamos echar triunfo porque es el palo de arrastre*/
       if ((yaesta == 0) && (EstadoIA->PaloT.N >= 1)){
         *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
       }
     }
     /*No tenemos triunfos en la mano, si vaNuestra intentamos echar puntos al compañero*/
     if (vaNuestra(EstadoIA) && (yaesta == 0)){
       /*buscamos ases*/
       if (EstadoIA->Palo1.Datos->as == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->as == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->as == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       } /*buscamos treses*/
       else if (EstadoIA->Palo1.Datos->tres == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->tres == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->tres == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       } /*buscamos reyes sin romper cantes*/
       else if ((EstadoIA->Palo1.Datos->rey == '1') && (EstadoIA->Palo1.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if ((EstadoIA->Palo2.Datos->rey == '1') && (EstadoIA->Palo2.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if ((EstadoIA->Palo3.Datos->rey == '1') && (EstadoIA->Palo3.Datos->haycante == 0)){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }/*buscamos sotas*/
       else if (EstadoIA->Palo1.Datos->sota == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->sota == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->sota == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }/*Buscamos caballos*/
       else if (EstadoIA->Palo1.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Datos->caballo == '1'){
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }
       /*Si yaesta == 0, no podemos echar ningún punto, nos descartamos*/
       if (yaesta == 0){
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
     else if (yaesta == 0){/*no va nuestra y no tenemos ninguna carta del palo de arrastre,nos descartamos*/
       Descarte(EstadoIA,Carta); yaesta = 1;
     }
   }/*fin de carta1 == triunfo*/
 }/*fin turno 4*/

 if (yaesta == 0){/*No deberíamos llegar aquí nunca*/
   *Carta = EstadoIA->Mano.carta[0];
 }
}

/*********************************************************************************************/
/* Funcion que devuelve la carta que echa la IA de vueltas				     */
/* Entrada: EstadoIA: estructura en la que se guardan los datos de la IA		     */
/*          Carta: estructura en donde se devuelve la carta seleccionada		     */	 
/* Salida:  Carta seleccionada en el puntero *Carta					     */
/*********************************************************************************************/
void Vueltas(struct tpEstadoIA *EstadoIA, struct tpcarta *Carta)
{
 bool yaesta;
 struct tpcarta CartaAux;
 int i,cantes;

 yaesta = 0;

 if (EstadoIA->turno == 1){
   /*Casos especiales de vueltas*/
   if ((EstadoIA->DatosTriunfo->as == '1') && (EstadoIA->dificil == 1)){/*salir del as, con la esperanza de que el compañero cargue*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '2') && (EstadoIA->DatosTriunfo->tres == '1') && (EstadoIA->dificil == 1)){/*lo mismo con el tres*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '2') && (EstadoIA->DatosTriunfo->tres == '2') /*lo mismo con el rey*/
       && (EstadoIA->DatosTriunfo->rey == '1') && (EstadoIA->DatosTriunfo->haycante == 0) && (EstadoIA->dificil == 1)){
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((yaesta == 0) && (EstadoIA->DatosTriunfo->tres == '1') && (*EstadoIA->misPuntos <= 60) && (EstadoIA->dificil == 1)){
     /*Si lleva pocos puntos, echar el tres de triunfo a pesar de que el as no haya salido*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   /*Casos generales de turno 1 de vueltas*/
   /*Si llevamos pocos puntos (<= 40) o bien nuestros puntos <= 60 y los contrarios
    >= 90, arriesgamos y salimos de bresca para ganar puntos*/
   if ((yaesta == 0) && ((*EstadoIA->misPuntos <= 40) || ((*EstadoIA->misPuntos <= 60) && (*EstadoIA->susPuntos >= 90)))){
     if ((EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1].valor == '9')  || (EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1].valor  == '8')) {
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1].valor == '9')  || (EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1].valor  == '8')) {
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1].valor == '9')  || (EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1].valor  == '8')) {
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     }
   }
   /*Buscamos salir de triunfo pequeño siempre y cuando tengamos al menos, dos triunfos, y no sea el siete*/
   if ((EstadoIA->PaloT.N >= 2) && (yaesta == 0)){
     if ((EstadoIA->PaloT.Palo[0].valor <= '5') && ((EstadoIA->PaloT.Palo[0].valor != '4') || (EstadoIA->Triunfo->valor < '4'))){
       *Carta = EstadoIA->PaloT.Palo[0]; yaesta = 1;
     }
   }
   /*Intentamos descartarnos de una carta sin puntos que tenga brescas encima, para evitar
     que nos la monten*/
   if ((yaesta == 0) && (EstadoIA->Palo1.Datos->pequenas > 0) && (numBrescas(EstadoIA,EstadoIA->Palo1) >= numBrescas(EstadoIA,EstadoIA->Palo2))
        && (numBrescas(EstadoIA,EstadoIA->Palo1) >= numBrescas(EstadoIA,EstadoIA->Palo3))
        && (numBrescas(EstadoIA,EstadoIA->Palo1) >= 1) && (EstadoIA->dificil == 1)) {
     *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.Datos->pequenas-1]; yaesta = 1;
   }
   if ((yaesta == 0) && (EstadoIA->Palo2.Datos->pequenas > 0) && (numBrescas(EstadoIA,EstadoIA->Palo2) >= numBrescas(EstadoIA,EstadoIA->Palo3))
        && (numBrescas(EstadoIA,EstadoIA->Palo2) >= 1) && (EstadoIA->dificil == 1)) {
     *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.Datos->pequenas-1]; yaesta = 1;
   }
   if ((yaesta == 0) && (EstadoIA->Palo3.Datos->pequenas > 0) && (numBrescas(EstadoIA,EstadoIA->Palo3) >= 1) && (EstadoIA->dificil == 1)) {
     *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.Datos->pequenas-1]; yaesta = 1;
   }
   /*Llegados aquí, sólo queda descartarse*/
   if (yaesta == 0){
     Descarte(EstadoIA,Carta); yaesta = 1;
   }
 }/*fin del turno 1*/

 if (EstadoIA->turno == 2){
   /*Casos especiales de vueltas*/
   if ((EstadoIA->DatosTriunfo->as == '1') && (EstadoIA->dificil == 1)){/*salir del as, con la esperanza de que el compañero cargue*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '2') && ((EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo) || 
      ((EstadoIA->Mesa.jugador1.palo == EstadoIA->Triunfo->palo) && (EstadoIA->Mesa.jugador1.valor != '9'))) && (EstadoIA->DatosTriunfo->tres == '1') && (EstadoIA->dificil == 1)){/*lo mismo con el tres*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '2') && ((EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo) || 
      ((EstadoIA->Mesa.jugador1.palo == EstadoIA->Triunfo->palo) && (EstadoIA->Mesa.jugador1.valor >= '8'))) &&
      (EstadoIA->DatosTriunfo->tres == '2') /*lo mismo con el rey*/
       && (EstadoIA->DatosTriunfo->rey == '1') && (EstadoIA->DatosTriunfo->haycante == 0) && (EstadoIA->dificil == 1)){
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   if ((yaesta == 0) && (EstadoIA->DatosTriunfo->tres == '1') && (*EstadoIA->misPuntos <= 60) 
       && (EstadoIA->dificil == 1) && ((EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo) || 
      ((EstadoIA->Mesa.jugador1.palo == EstadoIA->Triunfo->palo) && (EstadoIA->Mesa.jugador1.valor != '9')))){
     /*Si lleva pocos puntos, echar el tres de triunfo a pesar de que el as no haya salido*/
     *Carta = EstadoIA->Mano.carta[EstadoIA->Mano.numCar-1]; yaesta = 1;
   }
   /*Casos generales del turno 2*/
   /*Si carta1 no es triunfo, intentamos montar, teniendo en cuenta que los otros no puedan
   salirse en caso de tener que montar con caballo,sota o rey*/
   if ((yaesta == 0) && (EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo)){
     if (puedoMontar(EstadoIA,Carta,&EstadoIA->Mesa.jugador1,EstadoIA->Triunfo->palo)){
       yaesta = ((EstadoIA->dificil == 0) || (Carta->valor < '5') || ((Carta->valor >= '5') && (valor_carta(Carta) + valor_carta(&EstadoIA->Mesa.jugador1) + *EstadoIA->susPuntos + 11 < 101)));
     }
   }
   /*Intentamos montar con triunfo pequeño*/
   for (i=0;i<EstadoIA->PaloT.Datos->medianas;i++){
     if ((yaesta == 0) && cartaMayor(&EstadoIA->PaloT.Palo[i],&EstadoIA->Mesa.jugador1,EstadoIA->Triunfo->palo) && ((EstadoIA->PaloT.Palo[i].valor != '4') || (EstadoIA->Triunfo->valor < '4'))){
       *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
     }
   }
   /*Si carta1 es bresca y tenemos menos de 80 puntos y aun no hemos montado intentamos montar hasta con el rey de triunfo, sin romper cante*/
   if ((EstadoIA->Mesa.jugador1.valor >= '8') && (yaesta == 0) && (*EstadoIA->misPuntos <= 80) && (EstadoIA->dificil == 1)){
     if ((EstadoIA->DatosTriunfo->sota == '1') && (EstadoIA->DatosTriunfo->haycante == 0)){
       Carta->palo = EstadoIA->Triunfo->palo; Carta->valor = '6'; yaesta = 1;
     }
     else if ((EstadoIA->DatosTriunfo->rey == '1') && (EstadoIA->DatosTriunfo->haycante == 0)){
       Carta->palo = EstadoIA->Triunfo->palo; Carta->valor = '7'; yaesta = 1;
     }
   }
   /*Nos descartamos*/
   if (yaesta == 0){
     Descarte(EstadoIA,Carta); yaesta = 1;
   }
 }/*fin del turno 2*/

 if (EstadoIA->turno == 3){
   /*Casos especiales de vueltas*/
   if ((EstadoIA->DatosTriunfo->as == '1') && (EstadoIA->DatosTriunfo->tres == '1') && (EstadoIA->dificil == 1)){
     *Carta = EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '1') && (yaesta == 0) && (EstadoIA->dificil == 1) &&
       (11 + *EstadoIA->misPuntos + 20*EstadoIA->Palo1.Datos->haycante + 20*EstadoIA->Palo2.Datos->haycante + 20*EstadoIA->Palo3.Datos->haycante + 40*EstadoIA->PaloT.Datos->haycante >= 101)){
     *Carta = EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1]; yaesta = 1;
   }
   /*Si va del compañero, y observamos que ha echado el as de triunfo, tres de triunfo
   o rey de triunfo, buscamos cargarle con bresca, en caso contrario se descarta*/
   if ((yaesta == 0) && (vaNuestra(EstadoIA))){
     if ((EstadoIA->Mesa.jugador1.valor >= '7') && (EstadoIA->Mesa.jugador1.palo == EstadoIA->Triunfo->palo) && (EstadoIA->dificil == 1)){
       if (EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1].valor >= '8') {
         *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1].valor >= '8') {
         *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
       }
       else if (EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1].valor >= '8') {
         *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
       }
     }
     if (yaesta == 0){
       Descarte(EstadoIA,Carta); yaesta = 1;
     }
   }
   else if (yaesta == 0){/*va de los otros, intentamos montar*/
     /*Si tienen peligro de salirse con un posible as del cuarto jugador intentamos montar con triunfo*/
     if (*EstadoIA->susPuntos + valor_carta(&EstadoIA->Mesa.jugador1) + valor_carta(&EstadoIA->Mesa.jugador1) + 11>= 101){
       for (i=0;i<EstadoIA->PaloT.Datos->medianas;i++){
         if ((yaesta == 0) && (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->PaloT.Palo[i],EstadoIA->Triunfo->palo))){
           *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
	 }
       }
     }
     /*Intentamos montar normalmente*/
     yaesta = puedoMontar(EstadoIA,Carta,&EstadoIA->Mesa.jugador2,'7');
     /*Intentamos montar con triunfo pequeño*/
     for (i=0;i<EstadoIA->PaloT.Datos->pequenas-1;i++){
       if ((yaesta == 0) && (cartaMayor(&CartaAux,&EstadoIA->PaloT.Palo[i],EstadoIA->Triunfo->palo))){
         if (EstadoIA->dificil == 0){
	   *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
         }
 	 else if (((EstadoIA->PaloT.Palo[i].valor == '4') && (EstadoIA->Triunfo->valor < '6')) || (EstadoIA->PaloT.Palo[i].valor != 4)) {/*IA dificil*/
	   *Carta = EstadoIA->PaloT.Palo[i]; yaesta = 1;
	 }
       }	
     }
     /*Ya solo queda descartarse*/
     if (yaesta == 0){
       Descarte(EstadoIA,Carta); yaesta = 1;
     }
   }
 }/*fin del turno 3*/

 if (EstadoIA->turno == 4){
   /*Casos especiales de vueltas*/
   if ((EstadoIA->DatosTriunfo->as == '1') && (EstadoIA->DatosTriunfo->tres == '1') && (EstadoIA->dificil == 1)){
     *Carta = EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1]; yaesta = 1;
   }
   if ((EstadoIA->DatosTriunfo->as == '1') && (yaesta == 0) && (EstadoIA->dificil == 1) &&
       (11 + *EstadoIA->misPuntos + 20*EstadoIA->Palo1.Datos->haycante + 20*EstadoIA->Palo2.Datos->haycante + 20*EstadoIA->Palo3.Datos->haycante + 40*EstadoIA->PaloT.Datos->haycante >= 101)){
     *Carta = EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1]; yaesta = 1;
   }
   /*Casos generales de turno 4*/
   /*Si va del compañero buscaremos cargarle con puntos, si no puede se descarta*/
   if ((yaesta == 0) && vaNuestra(EstadoIA)){
     /*buscamos ases*/
     if (EstadoIA->Palo1.Datos->as == '1'){
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo2.Datos->as == '1'){
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo3.Datos->as == '1'){
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     } /*buscamos treses*/
     else if (EstadoIA->Palo1.Datos->tres == '1'){
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo2.Datos->tres == '1'){
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo3.Datos->tres == '1'){
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     } /*buscamos reyes sin romper cantes*/
     else if ((EstadoIA->Palo1.Datos->rey == '1') && (EstadoIA->Palo1.Datos->haycante == 0) && (EstadoIA->Palo1.Datos->sota == '2')){
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo2.Datos->rey == '1') && (EstadoIA->Palo2.Datos->haycante == 0) && (EstadoIA->Palo2.Datos->sota == '2')){
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo3.Datos->rey == '1') && (EstadoIA->Palo3.Datos->haycante == 0) && (EstadoIA->Palo3.Datos->sota == '2')){
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     }/*buscamos sotas*/
     else if ((EstadoIA->Palo1.Datos->sota == '1') && (EstadoIA->Palo1.Datos->rey == '2')){
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo2.Datos->sota == '1' && (EstadoIA->Palo2.Datos->rey == '2'))){
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if ((EstadoIA->Palo3.Datos->sota == '1' && (EstadoIA->Palo3.Datos->rey == '2'))){
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     }/*Buscamos caballos*/
     else if (EstadoIA->Palo1.Datos->caballo == '1'){
       *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo2.Datos->caballo == '1'){
       *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
     }
     else if (EstadoIA->Palo3.Datos->caballo == '1'){
       *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
     }
     /*Si yaesta == 0, no podemos echar ningún punto, nos descartamos*/
     if (yaesta == 0){
       Descarte(EstadoIA,Carta); yaesta = 1;
     }
   }
   else if (yaesta == 0){ /*va de los otros*/
     /*Lo primero, si no hay triunfos en mesa, intentamos matar con bresca != triunfo*/
     /*Determinamos la carta más alta*/
     if (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador3,EstadoIA->Triunfo->palo)){
       CartaAux = EstadoIA->Mesa.jugador1;
     }
     else {
       CartaAux = EstadoIA->Mesa.jugador3;
     }
     if ((EstadoIA->Mesa.jugador1.palo != EstadoIA->Triunfo->palo) && (EstadoIA->Mesa.jugador2.palo != EstadoIA->Triunfo->palo)
         && (EstadoIA->Mesa.jugador3.palo != EstadoIA->Triunfo->palo)){
       if (EstadoIA->Palo1.N > 0){
         if ((EstadoIA->Palo1.Palo[0].palo == CartaAux.palo) && (EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1].valor >= '8')
              && (EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1].valor >= CartaAux.valor)) {
          *Carta = EstadoIA->Palo1.Palo[EstadoIA->Palo1.N-1]; yaesta = 1;
         }
       }
       if (EstadoIA->Palo2.N > 0){
         if ((EstadoIA->Palo2.Palo[0].palo == CartaAux.palo) && (EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1].valor >= '8')
              && (EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1].valor >= CartaAux.valor)) {
           *Carta = EstadoIA->Palo2.Palo[EstadoIA->Palo2.N-1]; yaesta = 1;
         }
       }
       if (EstadoIA->Palo3.N > 0){
         if ((EstadoIA->Palo3.Palo[0].palo == CartaAux.palo) && (EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1].valor >= '8')
              && (EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1].valor >= CartaAux.valor)) {
           *Carta = EstadoIA->Palo3.Palo[EstadoIA->Palo3.N-1]; yaesta = 1;
         }
       }
       if (yaesta == 0) {
         Descarte(EstadoIA,Carta); yaesta = 1;
       }
     }
     /*Buscamos jugadas ganadoras, es decir, miramos si echando determinada carta de
      la mano ganamos la baza y la partida. Para ello tenemos en cuenta los puntos de los posibles cantes*/
     cantes = EstadoIA->Palo1.Datos->haycante*20 + EstadoIA->Palo2.Datos->haycante*20 + EstadoIA->Palo3.Datos->haycante*20 + EstadoIA->PaloT.Datos->haycante*40;
     if (EstadoIA->dificil == 0){
     	cantes = 0;} /*La IA facil no tiene en cuenta el valor de los cantes*/
     for (i=0;i<EstadoIA->Mano.numCar;i++){
       if (cartaMayor(&CartaAux,&EstadoIA->Mano.carta[i],EstadoIA->Triunfo->palo) && (yaesta == 0) &&
           (*EstadoIA->misPuntos + valor_carta(&EstadoIA->Mesa.jugador1) + valor_carta(&EstadoIA->Mesa.jugador2) + valor_carta(&EstadoIA->Mesa.jugador3) + valor_carta(&EstadoIA->Mano.carta[i]) + cantes >= 101)){
         if ((EstadoIA->Mano.carta[i].valor == '6') && (i < 5)){
           if ((EstadoIA->Mano.carta[i+1].valor != '7') || (EstadoIA->Mano.carta[i+1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
         else if ((EstadoIA->Mano.carta[i].valor == '7') && (i < 5)){
           if ((EstadoIA->Mano.carta[i+1].valor != '6') || (EstadoIA->Mano.carta[i+1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
       }
     }
     /*Si los otros se salen si se deja pasar la baza hay que montar, de momento, preservamos los cantes*/
     for (i=0;i<EstadoIA->Mano.numCar;i++){
       if (cartaMayor(&CartaAux,&EstadoIA->Mano.carta[i],EstadoIA->Triunfo->palo) && (yaesta == 0) &&
           (*EstadoIA->susPuntos + valor_carta(&EstadoIA->Mesa.jugador1) + valor_carta(&EstadoIA->Mesa.jugador2) + valor_carta(&EstadoIA->Mesa.jugador3) >= 101)){
         if ((EstadoIA->Mano.carta[i].valor == '6') && (i < 5)){
           if ((EstadoIA->Mano.carta[i+1].valor != '7') || (EstadoIA->Mano.carta[i+1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
         else if ((EstadoIA->Mano.carta[i].valor == '7') && (i > 0)){
           if ((EstadoIA->Mano.carta[i-1].valor != '6') || (EstadoIA->Mano.carta[i-1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
       }
     }
     /*Los otros se van y antes no hemos podido montar, repetimos la operación, esta vez, rompiendo cantes si es necesario*/
     for (i=0;i<EstadoIA->Mano.numCar;i++){
       if (cartaMayor(&CartaAux,&EstadoIA->Mano.carta[i],EstadoIA->Triunfo->palo) && (yaesta == 0) &&
           (*EstadoIA->susPuntos + valor_carta(&EstadoIA->Mesa.jugador1) + valor_carta(&EstadoIA->Mesa.jugador2) + valor_carta(&EstadoIA->Mesa.jugador3) >= 101)){
         *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
       }
     }
    /*Si lleva el triunfo más alto en juego, mata para después salir con él y asegurarse otra baza*/
     for (i=0;i<EstadoIA->Mano.numCar;i++){
       if ((EstadoIA->dificil == 1) && cartaMayor(&CartaAux,&EstadoIA->Mano.carta[i],EstadoIA->Triunfo->palo) && (yaesta == 0) && ((EstadoIA->DatosTriunfo->as == '1') || ((EstadoIA->DatosTriunfo->as == '2') && (EstadoIA->DatosTriunfo->tres == '1')) ||
           ((EstadoIA->DatosTriunfo->as == '2') && (EstadoIA->DatosTriunfo->tres == '2') && (EstadoIA->DatosTriunfo->rey == '1')))) {
         if ((EstadoIA->Mano.carta[i].valor == '6') && (i < 5)){
           if ((EstadoIA->Mano.carta[i+1].valor != '7') || (EstadoIA->Mano.carta[i+1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
         else if ((EstadoIA->Mano.carta[i].valor == '7') && (i > 0)){
           if ((EstadoIA->Mano.carta[i-1].valor != '6') || (EstadoIA->Mano.carta[i-1].palo != EstadoIA->Mano.carta[i].palo)){
             *Carta = EstadoIA->Mano.carta[i]; yaesta = 1;
           }
         }
       }
     }
     /*Solo queda descartarse*/
     if (yaesta == 0){
      Descarte(EstadoIA,Carta); yaesta = 1;
     }
   }
 }/*fin del turno 4*/
 
 if (yaesta == 0){/*No deberíamos llegar aquí nunca*/
   *Carta = EstadoIA->Mano.carta[0];
 }
}

