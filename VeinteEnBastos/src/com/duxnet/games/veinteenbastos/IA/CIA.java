package com.duxnet.games.veinteenbastos.IA;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.CDATASection;

import com.duxnet.games.veinteenbastos.CBaza;
import com.duxnet.games.veinteenbastos.CCarta;
import com.duxnet.games.veinteenbastos.CJugada;
import com.duxnet.games.veinteenbastos.CListaCartas;
import com.duxnet.games.veinteenbastos.CMano;
import com.duxnet.games.veinteenbastos.CPalo;
import com.duxnet.games.veinteenbastos.GlobalVar;
import com.duxnet.games.veinteenbastos.enums.eCarta;
import com.duxnet.games.veinteenbastos.enums.ePalo;

public class CIA
{
	private GlobalVar m_gloval;
	private CIAEstado m_Estado;
	
	public CIA()
	{
		setGloval(GlobalVar.getInstance());
		m_Estado=getGloval().CreaEstado();
	}
	
	void FinRondaIA()	
	{
	 /*Inicializamos algunas variables de la ronda*/
	 EstadoIA->turnoComp = 0;
	 EstadoIA->turno = 1;
	 EstadoIA->puntosMesa = 0;
	 EstadoIA->miTurno = 0;

	 /*Comprobamos si es la ronda 4 de Idas o Vueltas, y en consecuencia comienza las
	   jugadas del arrastre*/
	 if ((*EstadoIA->num_ronda == 4) && (*EstadoIA->ultimas == 0)){
	   /*Comienza el arrastre*/
	   EstadoIA->nTriunfosIniciales = EstadoIA->PaloT.N;
	   if (EstadoIA->PaloT.N >= 3){
	     if ((EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1].valor == '9')
	       && (EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-2].valor == '8')
	       && (EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-3].valor == '7')){
	       EstadoIA->JugadaTriunfo = 1; /*Tiene los tres triunfos más altos*/
	     }
	   }
	   if ((EstadoIA->PaloT.N >= 2) && (EstadoIA->JugadaTriunfo == 0)){
	     if ((EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-1].valor == '9')
	       && (EstadoIA->PaloT.Palo[EstadoIA->PaloT.N-2].valor == '8')){
	       EstadoIA->JugadaTriunfo = 2; /*Tiene los dos triunfos más altos*/
	     }
	   }
	 }
	 /*Condiciones especiales del arrastre*/
	 if ((EstadoIA->nTriunfosIniciales == 5) && (*EstadoIA->num_ronda == 1)){
	   if (EstadoIA->JugadaTriunfo == 1) {
	     if (((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->PaloT.Datos->quedanporsalir >= 3))
	         || ((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->cartaComp.palo == EstadoIA->Triunfo->palo))){
	       EstadoIA->JugadaTriunfo = 3;
	     }
	     if (((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->PaloT.Datos->quedanporsalir <= 2))
	         || (nTriunfosMesa(EstadoIA) == 3) || (nTriunfosMesa(EstadoIA) == 4)){
	       EstadoIA->JugadaTriunfo = 1;
	     }
	   }
	   else if (EstadoIA->JugadaTriunfo == 2) {
	     if (((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->PaloT.Datos->quedanporsalir >= 2))
	         || ((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->cartaComp.palo == EstadoIA->Triunfo->palo))){
	       EstadoIA->JugadaTriunfo = 3;
	     }
	     else if ((nTriunfosMesa(EstadoIA) == 3) && (EstadoIA->PaloT.Datos->quedanporsalir == 2)) {
	       EstadoIA->JugadaTriunfo = 4;
	     }
	     else {
	       EstadoIA->JugadaTriunfo = 2;
	     }
	   }
	 }/*nº de triunfos iniciales 5*/

	 if (EstadoIA->nTriunfosIniciales == 4) {
	   if ((EstadoIA->JugadaTriunfo == 5) && (*EstadoIA->num_ronda == 1)){
	     if ((nTriunfosMesa(EstadoIA) == 2) && (EstadoIA->cartaComp.palo == EstadoIA->Triunfo->palo)){
	       EstadoIA->JugadaTriunfo = 3;
	       }
	     else if ((nTriunfosMesa(EstadoIA) == 3) || (nTriunfosMesa(EstadoIA) == 2)){
	       EstadoIA->JugadaTriunfo = 6;
	     }
	   }
	   if ((EstadoIA->JugadaTriunfo == 5) && (*EstadoIA->num_ronda == 2) && (nTriunfosMesa(EstadoIA) == 2)){
	     EstadoIA->JugadaTriunfo = 3;
	   }
	 }/*fin nº de triunfos iniciales 4*/
	}
	public boolean CambioTriunfo(CCarta c)
	{	 		
	 if ((getEstado().num_ronda == 4 || getEstado().vueltas) && (tieneCarta(c)) && (getEstado().Triunfo.getIdCarta() >= c.getIdCarta()))
	 {

	     QuitarCarta(c);
	     RobaCartaIA(getEstado().Triunfo);
	     return true;
	 }
	 return false;
	}
	public void AnularCantes(String acciones)
	{
		String rtn=acciones;
		byte[] aux=rtn.getBytes();
		Iterator<CPalo> it =getEstado().Palos.iterator();
		while(it.hasNext())
		{
			CPalo palo=it.next();
			if(aux[palo.getPaloBaraja()]==1)
				palo.getDatos().haycante=false;					
		}
	}
	
	void CambiarSiete(CCarta c)
	{	 
	 QuitarCarta(c);
	 RobaCartaIA(getEstado().Triunfo);
	}
	
	public String PuedoCantar(String acciones)
	{
		String rtn=acciones;
		byte[] aux=rtn.getBytes();
		int i=0;
		Iterator<CPalo> it =getEstado().Palos.iterator();
		while(it.hasNext())
		{
			CPalo palo=it.next();
			if(palo.getDatos().haycante)
			{
				palo.getDatos().haycante=false;				
				aux[i]='1';				
			}			
			i++;			
		}
		rtn=String.valueOf(aux);
		return rtn;
	}
	
	public void RobaCartaIA( CCarta Carta)
	{
		 CIADatos misDatos;
		 CPalo miPalo,miPaloTemp;	 
		 boolean colocado;
		 int n,i; //contadores
		 int idx=getEstado().Palos.indexOf(Carta.getPaloCarta());
		 misDatos=getEstado().Palos.get(idx).getDatos();
		 //Miramos su valor y modificamos los datos necesarios
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
		 if(getEstado().Mano.getNumcar()==0)
		 {
			 getEstado().Mano.add(0, Carta);
			 getEstado().Mano.AddNumcar();
			 if (Carta.getECarta()== getEstado().Triunfo.getECarta())
			 {
			     miPalo = getEstado().PaloTriunfo;
			 }
			 else 
			 {
			     miPalo = getEstado().Palos.get(0);
			     getEstado().Palos.get(1).setDatos(misDatos);
			 }		 
			 miPalo.add(Carta);
		 }
		 else
		 {
			 colocado = false; 
			 n = 0;
			 while ((!colocado) && (n < getEstado().Mano.getNumcar()))
			 {
			     
				 if ((getEstado().Mano.get(n).getECarta()== Carta.getECarta()))
			     {
					 if((getEstado().Mano.get(n).getValorCarta()< Carta.getValorCarta()))
						 n++;
					 else
						 colocado = true;
			     }
			     //A partir de aqu�, se asume que m_Estado->mano.carta[n].palo != Carta.palo
			     else
			     {
			    	 if (Carta.getECarta()== getEstado().Triunfo.getECarta())
			    		 n++;
			    	 else
			    	 {
			    		 if ((getEstado().Mano.get(n).getECarta().getId()> Carta.getECarta().getId()) || (getEstado().Mano.get(n).getECarta()== Carta.getECarta()))
			    		    colocado = true;		    		 
			    		 else 
			    			 n++;
			    	 }
			     }		     
			 }
			 if (colocado)
			 {   
				 getEstado().Mano.add(n,Carta);
				 getEstado().Mano.AddNumcar();
			 }
			 
			 if (Carta.getPaloCarta()== getEstado().Triunfo.getPaloCarta())
			 {
			     miPalo = getEstado().PaloTriunfo;
			 }
			 else if((Carta.getPaloCarta() == getEstado().Palos.get(0).getEPalo()) || (getEstado().Palos.get(0).size() == 0))
			 {
			     miPalo = getEstado().Palos.get(0);
			 }
			 else if((Carta.getPaloCarta() == getEstado().Palos.get(1).getEPalo()) || (getEstado().Palos.get(1).size() == 0))
			 {
			     miPalo = getEstado().Palos.get(1);
			 }
			 else
			 {
				    miPalo = getEstado().Palos.get(2);
			 }
			 if (miPalo.size()== 0)
			 {
			     miPalo.add(0,Carta);
			     miPalo.setDatos(misDatos);
			 }
			 else 
			 {
			     colocado = false; n = 0;
			     while (!colocado && miPalo.size()> n) 
			     {
			       if (miPalo.get(n).getValorCarta()< Carta.getValorCarta())		       
			         n++;		       
			       else
			         colocado = true;		       
			     }
			     if (colocado)
			       	miPalo.add(n,Carta);
			 }		 	   
		 }		 
		 Collections.sort(getEstado().Palos);		 	
	}
	
	public void ActualizarJugada(CCarta Carta)
	{
		CIADatos misDatos;
		
		int idx=getEstado().Palos.indexOf(Carta.getPaloCarta());
		misDatos=getEstado().Palos.get(idx).getDatos();
		misDatos.quedanporsalir--;
		switch(Carta.getECarta())
		 {
		 	case AS:
		 		misDatos.as = '2';	
		 	break;
		 	case TRES:
		 		misDatos.tres=2;	
		 	break;
		 	case REY:
		 		misDatos.rey=2;		 	 	 	
		 	break;
		 	case SOTA:		 	
			 	 misDatos.sota=2;			 	 	 
			break;
		 	case CABALLO:
		 		misDatos.caballo=2;		 	 
		 	break;		 	
		 }	 
		getEstado().Mesa.add(getEstado().turno-1, Carta);
		if (getEstado().turnoComp == getEstado().turno) 
		{
		   getEstado().cartaComp = Carta;
		}		
		getEstado().turno++;		
	}

	public CIAEstado getEstado() 
	{
		return m_Estado;
	}

	public void setEstado(CIAEstado estado) 
	{
		m_Estado = estado;
	}

	private GlobalVar getGloval() 
	{
		return m_gloval;
	}

	private void setGloval(GlobalVar gloval) 
	{
		m_gloval = gloval;
	}	
}
