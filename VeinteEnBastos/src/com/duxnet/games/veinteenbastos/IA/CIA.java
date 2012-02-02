package com.duxnet.games.veinteenbastos.IA;

import java.util.Iterator;
import com.duxnet.games.veinteenbastos.*;
import com.duxnet.games.veinteenbastos.enums.*;

public class CIA
{
	private GlobalVar	m_Gloval;
	private CEstado 	m_Estado;
	private CMano 		m_Mano;
	public CEstado getEstado() 
	{
		return m_Estado;
	}
	public void setEstado(CEstado estado) 
	{
		m_Estado = estado;
	}	
	private GlobalVar getGloval() 
	{
		return m_Gloval;
	}
	private void setGloval(GlobalVar gloval) 
	{
		m_Gloval = gloval;
	}		
	private CMano getMano() 
	{
		return m_Mano;
	}
	private void setMano(CMano mano) 
	{
		m_Mano = mano;
	}
	public CIA(CJugador Jugador)
	{
		setMano(Jugador.getMano());
		setGloval(Jugador.getGlogal());
		setEstado(Jugador);
	}
	public void QuitarCarta(CCarta Carta)
	{
		getMano().remove(Carta); 				 		
	}
	public void TirarCarta(CCarta Carta)
	{
	/*	getEstado().miTurno=getEstado().getTurno();
		if (getEstado().getTurno() <=2 )
		{
			getEstado().getTurno()Comp=getEstado().miTurno+2;
		}					
		else if (getEstado().getTurno() >= 3)
		{			
			getEstado().getTurno()Comp = getEstado().miTurno-2;
			getEstado().cartaComp=getEstado().Mesa.get(getEstado().getTurno()Comp-1);
		}						 
		
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
		 QuitarCarta(Carta);*/
	}
	public int TriunfosEnMesa()
	{		
		return getEstado().getJugadaActual().NumCartas(getEstado().getPaloTriunfo());
	}
	public void FinRondaIA()	
	{
	 /*Inicializamos algunas variables de la ronda*/
		 getEstado().setTurnoComp(0);
		 getEstado().setTurnoActual(1);
		 getEstado().setMiTurno(0);

		 /*Comprobamos si es la ronda 4 de Idas o Vueltas, y en consecuencia comienza las
		   jugadas del arrastre*/
		 if (getEstado().getNum_ronda()== 4 && !getEstado().isUltimas())
		 {
		   /*Comienza el arrastre*/
		   getEstado().setnTriunfosIniciales(getMano().getPaloTriunfo().size());
		   if (getMano().getPaloTriunfo().size() >= 3)
		   {
			  if(getMano().getPaloTriunfo().TieneECarta(eCarta.AS) && getMano().getPaloTriunfo().TieneECarta(eCarta.TRES) && getMano().getPaloTriunfo().TieneECarta(eCarta.REY))
			  {
				  getEstado().setJugadaTriunfo(1); //Tiene los mas de lo mas
			  }
		   }
		   if ((getMano().getPaloTriunfo().size() >= 3) && (getEstado().getJugadaTriunfo() == 0))
		   {
		     if (getMano().getPaloTriunfo().TieneECarta(eCarta.AS) && getMano().getPaloTriunfo().TieneECarta(eCarta.TRES))
		     {
		    	 //Tiene los dos triunfos más altos
		    	 getEstado().setJugadaTriunfo(2); 
		     }
		   }
		 }	 
		 if ((getEstado().getnTriunfosIniciales() == 5) && (getEstado().getNum_ronda() == 1))
		 {
		   if (getEstado().getJugadaTriunfo() == 1) 
		   {
			   if((TriunfosEnMesa() == 2) && (getMano().getPaloTriunfo().getQuedanporsalir() >= 3 || getEstado().getCartaComp().getPaloCarta()== getEstado().getPaloTriunfo()))
			   {
				   getEstado().setJugadaTriunfo(3);
			   }
			   if (((TriunfosEnMesa() == 2) && (getMano().getPaloTriunfo().getQuedanporsalir() <= 2)) || (TriunfosEnMesa() == 3) || (TriunfosEnMesa() == 4))
			   {
				   getEstado().setJugadaTriunfo(1);
			   }
		   }
		   else if (getEstado().getJugadaTriunfo() == 2) 
		   {
		     if (((TriunfosEnMesa() == 2) && (getMano().getPaloTriunfo().getQuedanporsalir() >= 2)) || ((TriunfosEnMesa() == 2) && (getEstado().getCartaComp().getPaloCarta() == getEstado().getPaloTriunfo())))
		     {
		    	 getEstado().setJugadaTriunfo(3);
		     }
		     else if ((TriunfosEnMesa() == 3) && (getMano().getPaloTriunfo().getQuedanporsalir() == 2)) 
		     {
		    	 getEstado().setJugadaTriunfo(4);
		     }
		     else 
		     {
		    	 getEstado().setJugadaTriunfo(2);
		     }
		   }
		 }
		 if (getEstado().getnTriunfosIniciales() == 4) 
		 {
		   if ((getEstado().getJugadaTriunfo() == 5) && (getEstado().getNum_ronda() == 1))
		   {
			   if ((TriunfosEnMesa() == 2) && (getEstado().getCartaComp().getPaloCarta() == getEstado().getPaloTriunfo()))
			   {
				   getEstado().setJugadaTriunfo(3);
			   }
			   else if ((TriunfosEnMesa() == 3) || (TriunfosEnMesa() == 2))
			   {
				   getEstado().setJugadaTriunfo(6);
			   }
		   }
		   if ((getEstado().getJugadaTriunfo() == 5) && (getEstado().getNum_ronda() == 2) && (TriunfosEnMesa() == 2))
		   {
			   getEstado().setJugadaTriunfo(3);
		   }
		 }
	}	
	public CCarta puedoMontar(CCarta laCarta, char limite)
	{
	 CCarta rtn=null;
	 boolean encontrado=false;	 
	 
	 CPalo paloTemp=new CPalo();
	 if(laCarta.getPaloCarta()!=getEstado().getPaloTriunfo())
	 {
		 Iterator<CPalo> it = getMano().getPalos().iterator();
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
						 || (ctmp.getECarta()!=eCarta.SOTA && !paloTemp.isHaycante()) 
						 || (ctmp.getECarta()!=eCarta.REY && !paloTemp.isHaycante())))
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
		boolean rtn=false;
		if (getEstado().getTurnoActual() == 3)
		{
			rtn=cartaMayor(getEstado().getJugadaActual().get(0),getEstado().getJugadaActual().get(1),getEstado().getPaloTriunfo());
		}
		else 
		{ //turno == 4
			boolean  jugadores12,jugadores23;			   	
	        jugadores12=(cartaMayor(getEstado().getJugadaActual().get(0),getEstado().getJugadaActual().get(1),getEstado().getPaloTriunfo())==false);
	        jugadores23=(cartaMayor(getEstado().getJugadaActual().get(1),getEstado().getJugadaActual().get(2),getEstado().getPaloTriunfo()));
	        rtn=(jugadores12 && jugadores23);	           
		}
		return rtn;
	}
	public boolean CambioTriunfo(CCarta c)
	{	 		
		boolean rtn=false;
		if ((getEstado().getNum_ronda() == 4 || getEstado().isVueltas()) && (getMano().tieneCarta(c) && (getEstado().getTriunfo().getIdCarta() >= c.getIdCarta())))
		{

			QuitarCarta(c);
			RobaCartaIA(getEstado().getTriunfo());
			rtn=true;
		}
		return rtn;
	}
	public void AnularCantes(String acciones)
	{
		String rtn=acciones;
		byte[] aux=rtn.getBytes();
		Iterator<CPalo> it =getMano().getPalos().iterator();
		while(it.hasNext())
		{
			CPalo palo=it.next();
			if(aux[palo.getPaloBaraja()]==1)
				palo.setHaycante(false);					
		}
	}
	public void CambiarSiete(CCarta c)
	{	 
	 QuitarCarta(c);
	 RobaCartaIA(getEstado().getTriunfo());
	}
	public String PuedoCantar(String acciones)
	{
		String rtn=acciones;
		byte[] aux=rtn.getBytes();
		int i=0;
		Iterator<CPalo> it =getMano().getPalos().iterator();
		while(it.hasNext())
		{
			CPalo palo=it.next();
			if(palo.isHaycante())
			{
				palo.setHaycante(false);				
				aux[i]='1';				
			}			
			i++;			
		}
		rtn=String.valueOf(aux);
		return rtn;
	}
	public void RobaCartaIA( CCarta Carta)
	{		  			 	
		getMano().add(Carta); 		
	}
	public void ActualizarJugada(CCarta Carta)
	{
		if(Carta.getPaloCarta()!=getEstado().getPaloTriunfo())
		{
			CPalo palo=getMano().DamePalo(Carta.getPaloCarta());
			int idx=getMano().getPalos().indexOf(palo);		
			palo.rellenaDatos(Carta, 2);
			getMano().getPalos().set(idx, palo);
		}
		else
		{
			CPalo palo=getMano().getPaloTriunfo();
			palo.rellenaDatos(Carta, 2);
			getMano().setPaloTriunfo(palo);
			
		}	
		getEstado().getJugadaActual().add(getEstado().getTurnoActual()-1, Carta);
		if (getEstado().getTurnoComp()== getEstado().getTurnoActual()) 
		{
		   getEstado().setCartaComp(Carta);
		}		
		getEstado().setTurnoActual(getEstado().getTurnoComp()+1);		
	}
	public CCarta DescarteBasico()
	{
		boolean descartado=false;
		CCarta rtn=null;
	
		/*Buscamos echar una carta hasta caballo*/
		Iterator<CPalo> it = getMano().getPalos().iterator();
		while(it.hasNext() && !descartado)
		{
			CPalo p=it.next();
			if(p.size()>0)
			{
				if(p.getMedianas()>0)
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;
				}
				else if(!p.isHaycante() && ((p.getSota()==1 && p.getRey()==2) || (p.getRey()==1 && p.getSota()==2)))
				{
					 //Buscamos echar sota o rey, teniendo en cuenta no romper un cante y si la otra figura a salido ya				
					rtn=p.DamePrimeraCarta(false);
					descartado=true;				
				}
				else if(getMano().getPaloTriunfo().size()>0)
				{
					//Buscamos echar triunfo bajo
					if(getMano().getPaloTriunfo().getMedianas()>0)
					{
						if(getMano().getPaloTriunfo().DamePrimeraCarta(false).getPosCarta()!=4)
						{
							rtn=getMano().getPaloTriunfo().DamePrimeraCarta(false);
							descartado=true;
						}
						else if (getMano().getPaloTriunfo().DamePrimeraCarta(false).getPosCarta() <= '5') 
						{
							rtn=getMano().getPaloTriunfo().DamePrimeraCarta(false);
							descartado=true;
						}
						else if (getMano().getPaloTriunfo().getCaballo() == '1') 
						{
							rtn=getMano().getPaloTriunfo().DamePrimeraCarta(false);
							descartado=true;
						}												
					}					
				}
				else if((p.size()>1) && (p.getAs()==1 && p.getTres()==1))
				{
					//Echamos un tres respaldado por un AS
					rtn=p.getCartas().get(p.size()-2);
						descartado=true;																		
				}
				//ultimas oportunidades
				else if((p.size()>0) && (p.getAs()==2 && p.getTres()==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;						
				}
				else if((p.size()>0) && (p.getAs()==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;			
				}
				else if((p.size()>0) && (p.getTres()==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;			
				}									
			}
		}
		if(!descartado && getMano().size()>0)
		{
			rtn=getMano().DamePrimeraCarta(false);			
		}
		return rtn;
	}
	public CCarta DescartarCartaSegundo()
	{
			CCarta rtn,cartadescarte,cartadescarte2 = null;		
			int icd,icd2;
	
			cartadescarte=DescarteBasico();
			Iterator<CPalo> it = getMano().getPalos().iterator();
			while(it.hasNext())
			{
				CPalo p=it.next();
				if(p.getEPalo()== getEstado().getCartaComp().getPaloCarta())
				{
					//Eliminamos el palo del compañero y volvemos a eleguir la mejor carta
					getMano().getPalos().remove(p);
					cartadescarte2=DescarteBasico();
					if(cartadescarte2==null)
						cartadescarte2=cartadescarte;
					getMano().getPalos().add(p);				
				}			
			}
			icd=getMano().getPalos().indexOf(cartadescarte);
			icd2=getMano().getPalos().indexOf(cartadescarte2);
			if((cartadescarte.getPosCarta()<=7 && cartadescarte.getPaloCarta()!=getEstado().getCartaComp().getPaloCarta()) || (cartadescarte2==cartadescarte))
			{
				rtn=cartadescarte;
			}
			else
			{
				if((cartadescarte.getPosCarta()<=7 && cartadescarte2.getPosCarta()>7 && cartadescarte.getPaloCarta()!=getEstado().getCartaComp().getPaloCarta())  
						|| (cartadescarte2.getPaloCarta()==getEstado().getPaloTriunfo()) 
						|| ((getMano().getPalos().get(icd).NumCartas()+2)<= getMano().getPalos().get(icd2).NumCartas()))
				{
					rtn=cartadescarte;
				}
				else
				{
					rtn=cartadescarte2;
				}
				
			}
			return rtn;
	 }	
	public CCarta DescarteSolitario()
	{
		CCarta rtn;
		if(getMano().getPalos().get(0).size()==1)
		{
			if(getMano().getPalos().get(0).getAs()!=1 && getMano().getPalos().get(0).getTres()!=1)
				rtn=getMano().getPalos().get(0).DamePrimeraCarta(false);			
			else
				rtn=DescarteBasico();
		}
		else
			rtn=DescarteBasico();
		return rtn;	 
	}
	public CCarta Descarte()
	{
		CCarta rtn;
		if ((getEstado().getNum_ronda()!= 4) && (getEstado().getTurnoActual() > 2) && getEstado().getNivel()==3)
		{
			   rtn=DescartarCartaSegundo();
		}
		else if (getEstado().getNum_ronda() == 4 && !getEstado().isUltimas() && getEstado().getNivel()==3)
		{
			rtn=DescarteSolitario();
		}
		else
		{
			rtn=DescarteBasico();
		}
		return rtn;
	}		 


	
	public CCarta VoyDeIdas()
	{
	 boolean yaesta;
	 CCarta rtn,CartaAux;
	
	 yaesta = false;
	 if (getEstado().getTurnoActual() == 1)
	 {
	   //Si  es la ultima ronda me descarto de Bresca
	   if ( getEstado().getNum_ronda() == 4 && getEstado().getNivel() == 3)
	   if (
			   (getEstado().getTriunfo().getPosCarta()>= 8) 
			   || (getMano().getPaloTriunfo().getSota()== '1' && getEstado().getTriunfo().getPosCarta() == 7) 
			   || (getMano().getPaloTriunfo().getRey()== '1' && getEstado().getTriunfo().getPosCarta() == 6) 
		  )
	   {
		   Iterator<CPalo> itPalo=getMano().getPalos().iterator();
		   while(!yaesta && itPalo.hasNext())
		   {
			   CPalo p=itPalo.next();
			   if(p.size()>0)
			   {
				   int idx=-1;
				   if (p.getAs()== 1)
				   {
					   idx=p.idxCarta(eCarta.AS);
					   rtn=p.get(idx);
					   yaesta=true;
				   }
				   else if (p.getTres() == 1 && p.getAs() == 2)
				   {
					   idx=p.idxCarta(eCarta.TRES);
					   rtn=p.get(idx);
					   yaesta=true;
				   }				   				   		 
			   }			   
		   }
		   
	   }
	   if (!yaesta) 
	   {
	     rtn=Descarte();
	   }
	 }/*fin turno 1*/
	 else
	 if (getEstado().getTurnoActual() == 2)
	 {
	   //Si  es la ultima ronda me descarto de Bresca
	   if ( getEstado().getNum_ronda() == 4 && getEstado().getNivel() == 3)
	   if (
			   (getGloval().getJugada().get(0).getPosCarta()<8 && getGloval().getJugada().get(0).getPaloCarta()!=getEstado().getPaloTriunfo()) 
			   && ( (getMano().getPaloTriunfo().getSota()== '1' && getEstado().getTriunfo().getPosCarta() == 7) 
			   || (getMano().getPaloTriunfo().getRey()== '1' && getEstado().getTriunfo().getPosCarta() == 6)) 
		  )
	   {
		   Iterator<CPalo> itPalo=getMano().getPalos().iterator();
		   while(!yaesta && itPalo.hasNext())
		   {
			   CPalo p=itPalo.next();
			   if(p.size()>0)
			   {
				   int idx=-1;
				   if (p.getAs()== 1)
				   {
					   idx=p.idxCarta(eCarta.AS);
					   rtn=p.get(idx);
					   yaesta=true;
				   }
				   else if (p.getTres() == 1 && p.getAs() == 2)
				   {
					   idx=p.idxCarta(eCarta.TRES);
					   rtn=p.get(idx);
					   yaesta=true;
				   }				   				   		 
			   }			   
		   }		  
	   }
	   if (!yaesta) 
	   {
	     rtn=Descarte();
	   }	   
	 }
	 else
	 if (getEstado().getTurnoActual() == 2)
	 {
	   //Si  es la ultima ronda me descarto de Bresca
	   if ( getEstado().getNum_ronda() == 4 && getEstado().getNivel() == 3)
	   if (
			   (getGloval().getJugada().get(0).getPosCarta()<8 && getGloval().getJugada().get(0).getPaloCarta()!=getEstado().getPaloTriunfo()) 
			   && ( (getMano().getPaloTriunfo().getSota()== '1' && getEstado().getTriunfo().getPosCarta() == 7) 
			   || (getMano().getPaloTriunfo().getRey()== '1' && getEstado().getTriunfo().getPosCarta() == 6)) 
		  )
	   {
		   Iterator<CPalo> itPalo=getMano().getPalos().iterator();
		   while(!yaesta && itPalo.hasNext())
		   {
			   CPalo p=itPalo.next();
			   if(p.size()>0)
			   {
				   int idx=-1;
				   if (p.getAs()== 1)
				   {
					   idx=p.idxCarta(eCarta.AS);
					   rtn=p.get(idx);
					   yaesta=true;
				   }
				   else if (p.getTres() == 1 && p.getAs() == 2)
				   {
					   idx=p.idxCarta(eCarta.TRES);
					   rtn=p.get(idx);
					   yaesta=true;
				   }				   				   		 
			   }			   
		   }		  
	   }
	   if (!yaesta) 
	   {
	     rtn=Descarte();
	   }	   
	 }
	

	 if (getEstado().turno == 3){
	   /*Casos particulares de ronda 4*/
	   if ((*getEstado().num_ronda == 4) && (getEstado().dificil == 1)){
	     if ((getEstado().Triunfo->valor >= '6') && (getEstado().Mesa.jugador2.valor >= '8') && (getEstado().Mesa.jugador2.palo != getEstado().Triunfo->palo)){
	       /*Es posible que el segundo jugador haya echado la bresca para poder quedarse con el triunfo
	        la IA evita matar a no ser que tenga el siete. Si el triunfo es una sota o
	        un rey, la IA puede suponer que el segundo jugador tiene la pareja y quiere
	        intentar que la IA mate, cosa que no suceder�*/
	        CartaAux.palo = getEstado().Triunfo->palo; CartaAux.valor = '4';
	        if ((tieneCarta(EstadoIA,CartaAux)) || /*tiene el siete de triunfo, hay que matar*/
	            ((getEstado().PaloT.Datos->sota == '1') && (getEstado().Triunfo->valor == '7')) ||
	            ((getEstado().PaloT.Datos->rey == '1') && (getEstado().Triunfo->valor == '6')) ||
	            /*la carta del corro es una sota o un rey, pero la IA tiene la pareja as� que
	             sabe que el oponente no podra cantar las 40 y mata la bresca*/
	            (getEstado().Triunfo->valor <= '5')){/*el corro tiene un valor relativo bajo, se mata la bresca*/
	          /*Intentamos matar con un triunfo peque�o*/
	          if ((getEstado().PaloT.Datos->medianas >= 1) && (getEstado().PaloT.N > 1)){
	            if (getEstado().PaloT.Palo[0].valor != '4'){
	              *Carta = getEstado().PaloT.Palo[0];
	            }
	            else if ((getEstado().PaloT.Palo[1].valor == '5') && (getEstado().PaloT.N > 2)){
	              *Carta = getEstado().PaloT.Palo[1];
	            }
		    else{
		      Descarte(EstadoIA,Carta);
		    }  
	          }
	          else { /*si no podemos, nos descartamos*/
	            Descarte(EstadoIA,Carta);
	          }
	          yaesta = 1;
	        }
	     }
	   } /*fin casos particulares ronda 4*/
	   /*Comprobamos si el jugador2 ha conseguido montar*/
	   /*Si no ha montado, o si la carta que ha echado es triunfo, se descarta*/
	   if ((yaesta == 0) && (vaNuestra(EstadoIA) || (getEstado().Mesa.jugador2.palo == getEstado().Triunfo->palo))){
	     Descarte(EstadoIA,Carta); yaesta = 1;
	   }
	   /*Intentamos montar*/
	   if (yaesta == 0){
	     yaesta = puedoMontar(EstadoIA,Carta,&getEstado().Mesa.jugador2,'7');
	   }
	   /*Intentamos matar con triunfo peque�o*/
	   if ((yaesta == 0) && (getEstado().PaloT.N > 1) && (getEstado().PaloT.Datos->medianas >= 1) && (getEstado().puntosMesa >= 10)){
	     if (getEstado().PaloT.Palo[0].valor != '4'){
	        *Carta = getEstado().PaloT.Palo[0]; yaesta = 1;
	     }
	     else if ((getEstado().PaloT.Palo[1].valor == '5') && (getEstado().PaloT.N > 2)){
	       *Carta = getEstado().PaloT.Palo[1]; yaesta = 1;
	     }
	   }
	   if (yaesta == 0) { /*se descarta*/
	     Descarte(EstadoIA,Carta); yaesta = 1;
	   }
	 }/*fin turno 3*/

	 if (getEstado().turno == 4){
	   /*Condiciones especiales de ronda 4*/
	   /*Si el corro es alto y no va de la IA y no hay muchos puntos en mesa lo deja pasar*/
	   if ((getEstado().dificil == 1) && (vaNuestra(EstadoIA) == 0) && (getEstado().Triunfo->valor >= '6')
	        && (getEstado().puntosMesa < 15) && (*getEstado().num_ronda == 4)){
	     Descarte(EstadoIA,Carta); yaesta = 1;
	   } /*fin de condiciones del turno 4*/
	   /*Miramos a ver si va del compa�ero*/
	   if ((yaesta == 0) && vaNuestra(EstadoIA)){ /*Va del compa�ero, intentamos echar bresca, si no descarte*/
	     if (getEstado().Palo1.N > 0){
	       if (getEstado().Palo1.Palo[getEstado().Palo1.N-1].valor >= '8') {
	         *Carta = getEstado().Palo1.Palo[getEstado().Palo1.N-1]; yaesta = 1;
	       }
	     }
	     if (getEstado().Palo2.N > 0){
	       if ((yaesta == 0) && (getEstado().Palo2.Palo[getEstado().Palo2.N-1].valor >= '8')) {
	         *Carta = getEstado().Palo2.Palo[getEstado().Palo2.N-1]; yaesta = 1;
	       }
	     }
	     if (getEstado().Palo3.N > 0){
	       if ((yaesta == 0) && (getEstado().Palo3.Palo[getEstado().Palo3.N-1].valor >= '8')) {
	         *Carta = getEstado().Palo3.Palo[getEstado().Palo3.N-1]; yaesta = 1;
	       }
	     }
	     if (yaesta == 0) {
	       Descarte(EstadoIA,Carta); yaesta = 1;
	     }
	   }
	   else if (yaesta == 0){ /*Va del otro equipo, intentamos montar o descartarnos seg�n las cartas de la mesa*/
	     /*Si carta1 es triunfo y carta3 no es bresca, se descarta*/
	     if ((getEstado().Mesa.jugador1.palo == getEstado().Triunfo->palo) &&
	         (getEstado().Mesa.jugador3.palo != getEstado().Triunfo->palo) &&
	         (getEstado().Mesa.jugador3.valor < '8') && (yaesta == 0)){
	       Descarte(EstadoIA,Carta); yaesta = 1;
	     }
	     /*Si carta1 es triunfo y carta3 es bresca, intentamos montar*/
	     if ((getEstado().Mesa.jugador1.palo == getEstado().Triunfo->palo) &&
	         (getEstado().Mesa.jugador3.palo != getEstado().Triunfo->palo) &&
	         (getEstado().Mesa.jugador3.valor >= '8') && (yaesta == 0)){
	       if ((yaesta = puedoMontar(EstadoIA,Carta,&getEstado().Mesa.jugador1,'5')) == 0){
	         Descarte(EstadoIA,Carta); yaesta = 1;
	       }
	     }
	     /*carta1 no es triunfo, miramos a ver si carta3 es triunfo y hay puntos en la mesa*/
	     if ((getEstado().dificil == 1) && (yaesta == 0) && ((getEstado().Mesa.jugador3.palo == getEstado().Triunfo->palo))
	         && (getEstado().puntosMesa >= 11)){/*Buscamos matar la carta3*/
	       if ((yaesta = puedoMontar(EstadoIA,Carta,&getEstado().Mesa.jugador3,'5')) == 0){
	         Descarte(EstadoIA,Carta); yaesta = 1;
	       }
	     }
	     /*carta1 no es triunfo, si carta3 es triunfo y no hay puntos en la mesa, descarte*/
	     if ((yaesta == 0) && (getEstado().Mesa.jugador3.palo == getEstado().Triunfo->palo)){
	       Descarte(EstadoIA,Carta); yaesta = 1;
	     }
	     /*Ni carta1 ni carta3 son triunfos, y por ende, carta2 tampoco*/
	     /*Miramos a ver si podemos matar con as*/
	     CartaAux.palo = getEstado().Mesa.jugador1.palo; CartaAux.valor = '9';
	     if ((yaesta == 0) && tieneCarta(EstadoIA,CartaAux)){
	       *Carta = CartaAux; yaesta = 1;
	     }
	     /*Miramos a ver si podemos matar con tres*/
	     CartaAux.valor = '8';
	     if ((yaesta == 0) && tieneCarta(EstadoIA,CartaAux)
	         && (getEstado().Mesa.jugador1.valor != '9') && ((getEstado().Mesa.jugador3.palo != CartaAux.palo)
	         || ((getEstado().Mesa.jugador3.palo == CartaAux.palo) && (getEstado().Mesa.jugador3.valor != '9')))){
	       *Carta = CartaAux; yaesta = 1;
	     }
	     /*Guardamos en CartaAux la carta m�s alta entre carta1 y carta3*/
	     CartaAux = cartaMayor(&getEstado().Mesa.jugador1,&getEstado().Mesa.jugador3,getEstado().Triunfo->palo) ? getEstado().Mesa.jugador1 : getEstado().Mesa.jugador3;
	     /*Si hay 8 o mas puntos en la mesa, buscamos matar con algo que no sea triunfo*/
	     if ((yaesta == 0) && (getEstado().puntosMesa >= 8)){
	       yaesta = puedoMontar(EstadoIA,Carta,&CartaAux,'5');
	     }
	     /*Si hay 10 o m�s puntos en mesa, buscamos matar con triunfo peque�o si es IA dificil*/
	     if ((getEstado().dificil == 1) && (yaesta == 0) && (getEstado().puntosMesa >= 10) && (getEstado().PaloT.N > 1) && (getEstado().PaloT.Datos->medianas >= 1)){
	       if ((getEstado().PaloT.Palo[0].valor != '4') || (getEstado().Triunfo->valor < '6')){
	         *Carta = getEstado().PaloT.Palo[0]; yaesta = 1;
	       }
	     }
	     /*Si hay 6 o m�s puntos en mesa, buscamos matar con triunfo peque�o si es IA facil*/
	     if ((getEstado().dificil == 0) && (yaesta == 0) && (getEstado().puntosMesa >= 6) && (getEstado().PaloT.N > 1) && (getEstado().PaloT.Datos->medianas >= 1)){
	       if ((getEstado().PaloT.Palo[0].valor != '4') || (getEstado().Triunfo->valor < '6')){
	         *Carta = getEstado().PaloT.Palo[0]; yaesta = 1;
	       }
	     }
	     /*Llegados aqu�, nos descartamos*/
	     if (yaesta == 0){
	       Descarte(EstadoIA,Carta); yaesta = 1;
	     }
	   }
	 }/*fin turno 4*/

	 if (yaesta == 0){/*no deber�amos llegar aqu� nunca*/
	   *Carta = getEstado().Mano.carta[0];
	 }
	}


}
}