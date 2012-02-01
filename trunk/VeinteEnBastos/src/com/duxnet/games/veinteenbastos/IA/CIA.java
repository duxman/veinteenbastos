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
	
	public void QuitarCarta(CCarta Carta)
	{
		 CPalo miPalo=null;		 			 
		
		 /*Quitamos la carta de la mano*/		 
		 getEstado().Mano.remove(Carta);
		 		 	
		 /*Buscamos el PaloX asociado al palo de Carta*/
		 if (Carta.getPaloCarta() == getEstado().Triunfo.getPaloCarta())
		 {
		   miPalo = getEstado().PaloTriunfo;
		 }
		 else
		 {
			 Iterator<CPalo> it=getEstado().Palos.iterator();
			 while(it.hasNext())
			 {
				 CPalo p=it.next();
				 if(Carta.getPaloCarta()==p.getEPalo())
					 miPalo=p;
			 }		
		 }
		 switch(Carta.getECarta())
		 {
		 	case AS:
		 		miPalo.getDatos().as = 2;	
		 	break;
		 	case TRES:
		 		miPalo.getDatos().tres=2;	
		 	break;
		 	case REY:
		 		miPalo.getDatos().rey=2;		 	 	 	
		 	break;
		 	case SOTA:		 	
		 		miPalo.getDatos().sota=2;			 	 	 
			break;
		 	case CABALLO:
		 		miPalo.getDatos().caballo=2;
		 		miPalo.getDatos().medianas++;
		 	break;
		 	default:
		 		miPalo.getDatos().pequenas--;
		 		miPalo.getDatos().medianas--;
		 	break;	 
		 }	
		 Collections.sort(getEstado().Palos);		 			
	}
	
	void TirarCarta(CCarta Carta)
	{
		getEstado().miTurno=getEstado().turno;
		if (getEstado().turno == 1)
			getEstado().turnoComp = 3;			
		else if (getEstado().turno == 2)
			getEstado().turnoComp = 4;			 
		else if (getEstado().turno == 3)			 
			getEstado().turnoComp = 1;		
		else if (getEstado().turno == 4)
			getEstado().turnoComp = 2;
		
		 //getEstado().cartaComp = getEstado().Mesa.jugador2;
		 //getEstado().cartaComp = getEstado().Mesa.jugador1;
		//DAVID ????: en donde pone vueltas antes ponia otra vez ultimas
		
		if (!getEstado().ultimas && !getEstado().vueltas)
		 {
		   Idas(Carta);  //printf("idas\n");
		 }
		 else if (getEstado().ultimas) 
		 {
		   Arrastre(Carta); //printf("arrastre\n");
		 }
		 else{
		   Vueltas(Carta);  //printf("vueltas\n");
		 }		 
		 //printf("carta echada: %c%c\n",Carta.getPaloCarta(),Carta->valor);
		 QuitarCarta(Carta);
	}

	
	
	int TriunfosEnMesa()
	{
		int n=0;		
		Iterator<CCarta> it = getEstado().Mesa.iterator();
		while(it.hasNext())
		{
			if(it.next().getPaloCarta()==getEstado().Triunfo.getPaloCarta())
				n++;
		}			  
		return n;
	}
	
	public void FinRondaIA()	
	{
	 /*Inicializamos algunas variables de la ronda*/
		 getEstado().turnoComp = 0;
		 getEstado().turno = 1;
		 getEstado().miTurno = 0;
		 //getEstado().Mesa.cpuntosMesa = 0;

		 /*Comprobamos si es la ronda 4 de Idas o Vueltas, y en consecuencia comienza las
		   jugadas del arrastre*/
		 if (getEstado().num_ronda == 4 && !getEstado().ultimas)
		 {
		   /*Comienza el arrastre*/
		   getEstado().nTriunfosIniciales = getEstado().PaloTriunfo.size();
		   if (getEstado().PaloTriunfo.size() >= 3)
		   {
			  if(getEstado().PaloTriunfo.TieneECarta(eCarta.AS) && getEstado().PaloTriunfo.TieneECarta(eCarta.TRES) && getEstado().PaloTriunfo.TieneECarta(eCarta.REY))
			  {
				  getEstado().JugadaTriunfo = 1; /*Tiene los tres triunfos más altos*/
			  }
		   }
		   if ((getEstado().PaloTriunfo.size() >= 3) && (getEstado().JugadaTriunfo == 0))
		   {
		     if (getEstado().PaloTriunfo.TieneECarta(eCarta.AS) && getEstado().PaloTriunfo.TieneECarta(eCarta.TRES))
		     {
		    	 getEstado().JugadaTriunfo = 2; /*Tiene los dos triunfos más altos*/
		     }
		   }
		 }	 
		 if ((getEstado().nTriunfosIniciales == 5) && (getEstado().num_ronda == 1))
		 {
		   if (getEstado().JugadaTriunfo == 1) 
		   {
			   if((TriunfosEnMesa() == 2) && (getEstado().PaloTriunfo.getDatos().quedanporsalir >= 3 || getEstado().cartaComp.getPaloCarta()== getEstado().Triunfo.getPaloCarta()))
			   {
				   getEstado().JugadaTriunfo = 3;
			   }
			   if (((TriunfosEnMesa() == 2) && (getEstado().PaloTriunfo.getDatos().quedanporsalir <= 2)) || (TriunfosEnMesa() == 3) || (TriunfosEnMesa() == 4))
			   {
				   getEstado().JugadaTriunfo = 1;
			   }
		   }
		   else if (getEstado().JugadaTriunfo == 2) 
		   {
		     if (((TriunfosEnMesa() == 2) && (getEstado().PaloTriunfo.getDatos().quedanporsalir >= 2)) || ((TriunfosEnMesa() == 2) && (getEstado().cartaComp.getPaloCarta() == getEstado().Triunfo.getPaloCarta())))
		     {
		       getEstado().JugadaTriunfo = 3;
		     }
		     else if ((TriunfosEnMesa() == 3) && (getEstado().PaloTriunfo.getDatos().quedanporsalir == 2)) 
		     {
		       getEstado().JugadaTriunfo = 4;
		     }
		     else 
		     {
		       getEstado().JugadaTriunfo = 2;
		     }
		   }
		 }
		 if (getEstado().nTriunfosIniciales == 4) 
		 {
		   if ((getEstado().JugadaTriunfo == 5) && (getEstado().num_ronda == 1))
		   {
			   if ((TriunfosEnMesa() == 2) && (getEstado().cartaComp.getPaloCarta() == getEstado().Triunfo.getPaloCarta()))
			   {
				   getEstado().JugadaTriunfo = 3;
			   }
			   else if ((TriunfosEnMesa() == 3) || (TriunfosEnMesa() == 2))
			   {
				   getEstado().JugadaTriunfo = 6;
			   }
		   }
		   if ((getEstado().JugadaTriunfo == 5) && (getEstado().num_ronda == 2) && (TriunfosEnMesa() == 2))
		   {
			   getEstado().JugadaTriunfo = 3;
		   }
		 }
	}	
	public CCarta puedoMontar(CCarta laCarta, char limite)
	{
	 CCarta rtn=null;
	 boolean encontrado=false;	 
	 
	 CPalo paloTemp=new CPalo();
	 if(laCarta.getPaloCarta()!=getEstado().Triunfo.getPaloCarta())
	 {
		 Iterator<CPalo> it = getEstado().Palos.iterator();
		 while(!encontrado && it.hasNext())
		 {
			 paloTemp=it.next();			 
			 if(paloTemp.getEPalo()==laCarta.getPaloCarta())
				 encontrado=true;				
		 }		 		 
		 if(encontrado)
		 {
			 Iterator<CCarta> it1=paloTemp.iterator();
			 CCarta ctmp;
			 encontrado=false;
			 while(!encontrado && it1.hasNext())
			 {
				 ctmp=it1.next();
				 if(ctmp.getPosCarta()>laCarta.getPosCarta() && ctmp.getPosCarta()<=limite 
						 && ((ctmp.getECarta()!=eCarta.SOTA && ctmp.getECarta()!=eCarta.REY) 
						 || (ctmp.getECarta()!=eCarta.SOTA && !paloTemp.getDatos().haycante) 
						 || (ctmp.getECarta()!=eCarta.REY && !paloTemp.getDatos().haycante)))
				{
					 encontrado=true;
					 rtn=ctmp;
				}				 				 
			 }			 
		 }
	 }		 			 
	 return rtn;
	}
	public boolean cartaMayor(CCarta carta1, CCarta carta2, ePalo triunfo)
	{
		 if ((carta1.getPaloCarta() == triunfo)&&(carta2.getPaloCarta() != triunfo))
		 {
		   return true;
		 }
		 else if ((carta1.getPaloCarta() != triunfo)&&(carta2.getPaloCarta() == triunfo))
		 {
		   return false;
		 }
		 else if (carta1.getPaloCarta() != carta2.getPaloCarta())
		 {
		   return true;
		 }
		 else if((carta1.getPaloCarta() == carta2.getPaloCarta()) && (carta1.getPosCarta() > carta2.getPosCarta()))
		 {
		   return true;
		 }
		 else 
		 {
		   return false;
		 }
	}
	public boolean vaNuestra()
	{
	 ///Repensar como saber esto creo que lo tengo en jugada
		/*if (getEstado().turno == 3)
	 {
	   return cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador2,EstadoIA->Triunfo->palo);
	 }
	 else { //turno == 4
	   return (
	   		//carta del jugador 1 no sea mayor que la carta del jugador2
	           (cartaMayor(&EstadoIA->Mesa.jugador1,&EstadoIA->Mesa.jugador2,EstadoIA->Triunfo->palo)== false)
	           //una vez que la carta del jugador2 manda sobre la del 1, entonces tiene que ser mayor que la del tres
	           && cartaMayor(&EstadoIA->Mesa.jugador2,&EstadoIA->Mesa.jugador3,EstadoIA->Triunfo->palo));
	 }*/
		return false;
	}
	public boolean CambioTriunfo(CCarta c)
	{	 		
		boolean rtn=false;
		if ((getEstado().num_ronda == 4 || getEstado().vueltas) && (getEstado().Mano.tieneCarta(c) && (getEstado().Triunfo.getIdCarta() >= c.getIdCarta())))
		{

			QuitarCarta(c);
			RobaCartaIA(getEstado().Triunfo);
			rtn=true;
		}
		return rtn;
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
	public void CambiarSiete(CCarta c)
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
