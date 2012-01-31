package com.duxnet.games.veinteenbastos.IA;

import java.util.Iterator;
import java.util.Properties;

import org.w3c.dom.CDATASection;

import com.duxnet.games.veinteenbastos.CBaza;
import com.duxnet.games.veinteenbastos.CCarta;
import com.duxnet.games.veinteenbastos.CJugada;
import com.duxnet.games.veinteenbastos.CMano;
import com.duxnet.games.veinteenbastos.CPalo;
import com.duxnet.games.veinteenbastos.GlobalVar;
import com.duxnet.games.veinteenbastos.enums.eCarta;
import com.duxnet.games.veinteenbastos.enums.ePalo;








public class CIA
{
	GlobalVar m_gloval;
	public CIA()
	{
		m_gloval=GlobalVar.getInstance();
	}
	
	public void IA_RobaCarta(CIAEstado EstadoIA, CCarta Carta)
	{
	 CIADatos misDatos;
	 CPalo miPalo,miPaloTemp;	 
	 boolean colocado;
	 int n,i; /*contadores*/
	 misDatos = EstadoIA.Palos.get(Carta.getPaloCarta().getId()).getDatos();
	 /*Miramos su valor y modificamos los datos necesarios*/
	 misDatos.quedanporsalir--;
	 
	 switch(Carta.getECarta())
	 {
	 	case AS:
	 		misDatos.as = '1';	
	 	break;
	 	case TRES:
	 		misDatos.tres=1;	
	 	break;
	 	case REY:
	 	 misDatos.rey=1;
	 	 if(misDatos.sota==1)
	 		misDatos.haycante = true;	 	 
	 	break;
	 	case SOTA:
	 	
		 	 misDatos.sota=1;
		 	 if(misDatos.rey==1)
		 		misDatos.haycante = true;	 	
		break;
	 	case CABALLO:
	 	 misDatos.caballo=1;
	 	 misDatos.medianas++;
	 	break;
	 	default:
	 		misDatos.pequenas++;
	 		misDatos.medianas++;
	 	break;	 
	 }	 	 
	 if(EstadoIA.Mano.getNumcar()==0)
	 {
		 EstadoIA.Mano.add(0, Carta);
		 EstadoIA.Mano.AddNumcar();
		 if (Carta.getECarta()== EstadoIA.Triunfo.getECarta())
		 {
		     miPalo = EstadoIA.PaloTriunfo;
		 }
		 else 
		 {
		     miPalo = EstadoIA.Palos.get(0);
		     EstadoIA.Palos.get(1).setDatos(misDatos);
		 }		 
		 miPalo.add(Carta);
	 }
	 else
	 {
		 colocado = false; 
		 n = 0;
		 while ((!colocado) && (n < EstadoIA.Mano.getNumcar()))
		 {
		     
			 if ((EstadoIA.Mano.get(n).getECarta()== Carta.getECarta()))
		     {
				 if((EstadoIA.Mano.get(n).getValorCarta()< Carta.getValorCarta()))
					 n++;
				 else
					 colocado = true;
		     }
		     //A partir de aquí, se asume que EstadoIA->mano.carta[n].palo != Carta.palo
		     else
		     {
		    	 if (Carta.getECarta()== EstadoIA.Triunfo.getECarta())
		    		 n++;
		    	 else
		    	 {
		    		 if ((EstadoIA.Mano.get(n).getECarta().getId()> Carta.getECarta().getId()) || (EstadoIA.Mano.get(n).getECarta()== Carta.getECarta()))
		    		    colocado = true;		    		 
		    		 else 
		    			 n++;
		    	 }
		     }		     
		 }
		 if (colocado)
		 {   
			 EstadoIA.Mano.add(n,Carta);
			 EstadoIA.Mano.AddNumcar();
		 }
		 
		 if (Carta.getPaloCarta()== EstadoIA.Triunfo.getPaloCarta())
		 {
		     miPalo = EstadoIA.PaloTriunfo;
		 }
		 else if((Carta.getPaloCarta() == EstadoIA.Palos.get(0).getEPalo()) || (EstadoIA.Palos.get(0).size() == 0))
		 {
		     miPalo = EstadoIA.Palos.get(0);
		 }
		 else if((Carta.getPaloCarta() == EstadoIA.Palos.get(1).getEPalo()) || (EstadoIA.Palos.get(1).size() == 0))
		 {
		     miPalo = EstadoIA.Palos.get(1);
		 }
		 else
		 {
			    miPalo = EstadoIA.Palos.get(2);
		 }
	 }
/*
	 //Modificamos la mano
	 
	
	   
	   
	   //Ordenamos los PalosX
	   
	   if (miPalo->N == 0){
	     miPalo->Palo[0] = Carta;
	     miPalo->Datos = misDatos;
	     }
	   else {
	     colocado = 0; n = 0;
	     while ((colocado == 0) && (miPalo->N > n)) {
	       if (miPalo->Palo[n].valor < Carta.valor){
	         n++;
	         }
	       else{
	         colocado = 1;
	       }
	     }//end while
	     if (colocado == 1){ //Si colocado vale 1, entonces n es el lugar donde debería estar la carta
	       for (i = 5; i > n; i--){
	         miPalo->Palo[i] = miPalo->Palo[i-1];
	       }
	     } //Si colocado vale 0 implica que la carta debe ir en ultimo lugar
	     miPalo->Palo[n] = Carta;
	   }
	   miPalo->N++;*/
	 }
}
