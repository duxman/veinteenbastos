package com.duxnet.games.veinteenbastos.IA;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
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
		 CPalo miPalo;	 
		 boolean colocado;
		 int n; //contadores
		 int idx=getMano().getPalos().indexOf(Carta.getPaloCarta());
		 misDatos=getMano().getPalos().get(idx).getDatos();
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
			     miPalo = getMano().getPaloTriunfo();
			 }
			 else 
			 {
			     miPalo = getMano().getPalos().get(0);
			     getMano().getPalos().get(1).setDatos(misDatos);
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
			 
			 if (Carta.getPaloCarta()== getEstado().getPaloTriunfo())
			 {
			     miPalo = getMano().getPaloTriunfo();
			 }
			 else if((Carta.getPaloCarta() == getMano().getPalos().get(0).getEPalo()) || (getMano().getPalos().get(0).size() == 0))
			 {
			     miPalo = getMano().getPalos().get(0);
			 }
			 else if((Carta.getPaloCarta() == getMano().getPalos().get(1).getEPalo()) || (getMano().getPalos().get(1).size() == 0))
			 {
			     miPalo = getMano().getPalos().get(1);
			 }
			 else
			 {
				    miPalo = getMano().getPalos().get(2);
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
		 Collections.sort(getMano().getPalos());		 	
	}
	public void ActualizarJugada(CCarta Carta)
	{
		CIADatos misDatos;
		
		int idx=getMano().getPalos().indexOf(Carta.getPaloCarta());
		misDatos=getMano().getPalos().get(idx).getDatos();
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
		getEstado().Mesa.add(getEstado().getTurnoActual()-1, Carta);
		if (getEstado().getTurnoActual()Comp == getEstado().getTurnoActual()) 
		{
		   getEstado().cartaComp = Carta;
		}		
		getEstado().getTurnoActual()++;		
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
				if(p.getDatos().medianas>0)
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;
				}
				else if(!p.getDatos().haycante && ((p.getDatos().sota==1 && p.getDatos().rey==2) || (p.getDatos().rey==1 && p.getDatos().sota==2)))
				{
					 //Buscamos echar sota o rey, teniendo en cuenta no romper un cante y si la otra figura a salido ya				
					rtn=p.DamePrimeraCarta(false);
					descartado=true;				
				}
				else if(getMano().getPaloTriunfo().size()>0)
				{
					//Buscamos echar triunfo bajo
					if(getMano().getPaloTriunfo().getDatos().medianas>0)
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
						else if (getMano().getPaloTriunfo().getDatos().caballo == '1') 
						{
							rtn=getMano().getPaloTriunfo().DamePrimeraCarta(false);
							descartado=true;
						}												
					}					
				}
				else if((p.size()>1) && (p.getDatos().as==1 && p.getDatos().tres==1))
				{
					//Echamos un tres respaldado por un AS
					rtn=p.getCartas().get(p.size()-2);
						descartado=true;																		
				}
				//ultimas oportunidades
				else if((p.size()>0) && (p.getDatos().as==2 && p.getDatos().tres==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;						
				}
				else if((p.size()>0) && (p.getDatos().as==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;			
				}
				else if((p.size()>0) && (p.getDatos().tres==1))
				{
					rtn=p.DamePrimeraCarta(false);
					descartado=true;			
				}									
			}
		}
		if(!descartado && getEstado().Mano.size()>0)
		{
			rtn=getEstado().Mano.DamePrimeraCarta(false);			
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
				if(p.getEPalo()== getEstado().cartaComp.getPaloCarta())
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
			if((cartadescarte.getPosCarta()<=7 && cartadescarte.getPaloCarta()!=getEstado().cartaComp.getPaloCarta()) || (cartadescarte2==cartadescarte))
			{
				rtn=cartadescarte;
			}
			else
			{
				if((cartadescarte.getPosCarta()<=7 && cartadescarte2.getPosCarta()>7 && cartadescarte.getPaloCarta()!=getEstado().cartaComp.getPaloCarta())  
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
			if(getMano().getPalos().get(0).getDatos().as!=1 && getMano().getPalos().get(0).getDatos().tres!=1)
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
		if ((getEstado().getNum_ronda()!= 4) && (getEstado().getTurnoActual() > 2) && getEstado().dificil)
		{
			   rtn=DescartarCartaSegundo();
		}
		else if (getEstado().getNum_ronda() == 4 && !getEstado().ultimas && getEstado().dificil)
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
