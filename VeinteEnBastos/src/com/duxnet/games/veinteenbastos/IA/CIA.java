package com.duxnet.games.veinteenbastos.IA;

import java.util.Iterator;
import com.duxnet.games.veinteenbastos.*;
import com.duxnet.games.veinteenbastos.enums.*;

public class CIA
{
	//private GlobalVar	m_Gloval;
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
	/*private GlobalVar getGloval() 
	{
		return m_Gloval;
	}
	private void setGloval(GlobalVar gloval) 
	{
		m_Gloval = gloval;
	}	
	*/
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
		//setGloval(Jugador.getGlogal());
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
		    	 getEstado().setJugadaTriunfo(2); /*Tiene los dos triunfos más altos*/
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
}
