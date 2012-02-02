package com.duxnet.games.veinteenbastos;
import java.util.Comparator;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.duxnet.games.veinteenbastos.enums.*;

public class CCarta implements Comparable<Object>
{
	//==================================================================
    // Propiedades
    //==================================================================
	private long m_PesoOrden;
	private int m_ValorCarta;
	private int m_PosCarta;
	private int m_IdCarta;
	private ePalo  m_PaloCarta;
	private eCarta m_TipoCarta;
	private eLugar m_OcupacionCarta;
	private int m_OrdinalCarta;
	private int m_X;
	private int m_Y;
	private int m_Alto;
	private int m_Ancho;
	private boolean marcada;
	private String m_NombreCarta;
	private String m_LogCarta;
	private Bitmap m_ImagenCarta;	
	private Bitmap m_FondoCarta;	

	//==================================================================
    // Constructores
    //==================================================================
	public CCarta(int carta,int palo,Bitmap bmpcarta,Bitmap fondo) 
	{		
		setPaloCarta(ePalo.getPalo(palo));		
		setTipoCarta(eCarta.getCarta(carta));
		setImagenCarta(bmpcarta);
		setFondoCarta(fondo);
		setValorCarta(getTipoCarta().getValor());
		setPosCarta(getTipoCarta().getPos());
		setOrdinalCarta(10*(palo)+(carta-1));
		setIdCarta(carta);
		setNombreCarta(getTipoCarta().name() + " de "+ getPaloCarta().name());
		setLogCarta(getNombreCarta() + "Id : " + getIdCarta() + "Puntos " + getValorCarta());
		setPesoOrden(getIdCarta());
		setX(0);
		setY(0);
		setAlto(bmpcarta.getHeight());
		setAncho(bmpcarta.getWidth());
	}	
	//==================================================================
    // Metodos Comparadores
    //==================================================================	
	public int compareTo(Object another) 
	{
		if (this.getPosCarta()== ((CCarta) another).getPosCarta())
            return 0;
        else if ((this.getPosCarta()) > ((CCarta) another).getPosCarta())
            return 1;
        else
            return -1;
	}
	
	public int MayorValor(Object another) 
	{
		if (this.getValorCarta()== ((CCarta) another).getValorCarta())
            return 0;
        else if ((this.getValorCarta()) > ((CCarta) another).getValorCarta())
            return 1;
        else
            return -1;
	}
	
	public int MayorId(Object another)
	{
		if (this.getIdCarta()== ((CCarta) another).getIdCarta())
            return 0;
        else if ((this.getIdCarta()) > ((CCarta) another).getIdCarta())
            return 1;
        else
            return -1;
	}	
	public static final Comparator<CCarta> CompararPesoOrden= new Comparator<CCarta>() 
	{
		public int compare(CCarta o1, CCarta o2) 
		{
			//comprueba si los objectos que le llegan son del tipo de la clase
			if (o1.getPesoOrden() == o2.getPesoOrden() || !(o1 instanceof CCarta) || !(o2 instanceof CCarta )) 
			{
				return 0;
			}
			else
			{							
				// realiza las operaciones de comparación entre las fechas de las clases o1 y o2
				return o1.getPesoOrden()<= o2.getPesoOrden() ? ((int) (o1.getPesoOrden() >= o2.getPesoOrden() ? 0: 1)): -1;
			}
		}
	};	
	public static final Comparator<CCarta> CompararPosCarta= new Comparator<CCarta>() 
	{
		public int compare(CCarta o1, CCarta o2) 
		{
			//comprueba si los objectos que le llegan son del tipo de la clase			
			if (o1.getPosCarta() == o2.getPosCarta() || !(o1 instanceof CCarta) || !(o2 instanceof CCarta )) 
			{
				return 0;
			}
			else
			{							
				// realiza las operaciones de comparación entre las fechas de las clases o1 y o2
				return o1.getPosCarta()<= o2.getPosCarta() ? ((int) (o1.getPosCarta() >= o2.getPosCarta() ? 0: 1)): -1;
			}
		}
	};	
	public static final Comparator<CCarta> CompararValorCarta= new Comparator<CCarta>() 
	{
		public int compare(CCarta o1, CCarta o2) 
		{
			//comprueba si los objectos que le llegan son del tipo de la clase			
			if (o1.getValorCarta() == o2.getValorCarta() || !(o1 instanceof CCarta) || !(o2 instanceof CCarta )) 
			{
				return 0;
			}
			else
			{							
				// realiza las operaciones de comparación entre las fechas de las clases o1 y o2
				return o1.getValorCarta()<= o2.getValorCarta() ? ((int) (o1.getValorCarta() >= o2.getValorCarta() ? 0: 1)): -1;
			}
		}
	};	
	public static final Comparator<CCarta> CompararIdCarta= new Comparator<CCarta>() 
	{
		public int compare(CCarta o1, CCarta o2) 
		{
			//comprueba si los objectos que le llegan son del tipo de la clase			
			if (o1.getOrdinalCarta() == o2.getOrdinalCarta() || !(o1 instanceof CCarta) || !(o2 instanceof CCarta )) 
			{
				return 0;
			}
			else
			{							
				// realiza las operaciones de comparación entre las fechas de las clases o1 y o2
				return o1.getOrdinalCarta()<= o2.getOrdinalCarta() ? ((int) (o1.getOrdinalCarta() >= o2.getOrdinalCarta() ? 0: 1)): -1;
			}
		}
	};
	

	
	//==================================================================
    // Metodos
    //==================================================================	
	public boolean Tocada(float x2, float y2) 
    {
        return x2 > getX() && x2 < getX() + getAncho() && y2 > getY() && y2 < getY() + getAlto();
    }		
	public void PintarBorde(Canvas canvas)
	{
		Paint p=new Paint();
		p.setColor(Color.RED);
		p.setStrokeWidth(3);
		p.setStyle(Paint.Style.STROKE);
		canvas.drawRect(getX(), getY(), getX()+getAncho(), getY()+getAlto(),p);				
	}	
	
	public long DamePesoOrdenAleatorio()
	{				
		Random r = new Random();
		int iAleatorio = (int)(Math.random()*10000);
		long semilla =(long)(Math.abs(Math.sqrt(Math.tanh(r.nextInt(iAleatorio))))*r.nextInt(iAleatorio));		
		r.setSeed(semilla);	
		long rtn=r.nextLong();
		setPesoOrden(rtn);
		return rtn;								
	}
	public void Pintar(boolean vista,Canvas canvas,int Alto,int Ancho,int X,int Y)
	{
		int posX=X;
    	int posY=Y;    	
    	Bitmap b;
    	
    	if(vista)
    		b=getImagenCarta();
    	else
    		b=getFondoCarta();
    	
    	canvas.drawBitmap(b, posX, posY, null);
    	setX(posX);
    	setY(posY);	  
    	if(isMarcada())
    		PintarBorde(canvas);
	}

	//==================================================================
    // Metodos de Acceso
    //==================================================================
	public long getPesoOrden() 
	{
		return m_PesoOrden;
	}

	public void setPesoOrden(long rtn) 
	{
		m_PesoOrden = rtn;
	}

	public int getValorCarta() 
	{
		return m_ValorCarta;
	}
	

	public void setValorCarta(int valorCarta) {
		m_ValorCarta = valorCarta;
	}

	public int getIdCarta() 
	{
		return m_IdCarta;
	}
	public eCarta getECarta() 
	{
		return eCarta.getCarta(m_IdCarta);
	}

	public void setIdCarta(int idCarta) {
		m_IdCarta = idCarta;
	}

	public ePalo getPaloCarta() {
		return m_PaloCarta;
	}

	public void setPaloCarta(ePalo paloCarta) {
		m_PaloCarta = paloCarta;
	}

	public int getOrdinalCarta() {
		return m_OrdinalCarta;
	}

	public void setOrdinalCarta(int ordinalCarta) {
		m_OrdinalCarta = ordinalCarta;
	}

	public int getX() {
		return m_X;
	}

	public void setX(int x) {
		m_X = x;
	}

	public int getY() {
		return m_Y;
	}

	public void setY(int y) {
		m_Y = y;
	}

	public int getAlto() {
		return m_Alto;
	}

	public void setAlto(int alto) {
		m_Alto = alto;
	}

	public int getAncho() {
		return m_Ancho;
	}

	public void setAncho(int ancho) {
		m_Ancho = ancho;
	}

	public boolean isMarcada() {
		return marcada;
	}

	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}

	public String getNombreCarta() {
		return m_NombreCarta;
	}

	public void setNombreCarta(String nombreCarta) {
		m_NombreCarta = nombreCarta;
	}

	public String getLogCarta() {
		return m_LogCarta;
	}

	public void setLogCarta(String logCarta) {
		m_LogCarta = logCarta;
	}

	public Bitmap getFondoCarta() {
		return m_FondoCarta;
	}

	public void setFondoCarta(Bitmap fondoCarta) {
		m_FondoCarta = fondoCarta;
	}

	public Bitmap getImagenCarta() {
		return m_ImagenCarta;
	}

	public void setImagenCarta(Bitmap imagenCarta) {
		m_ImagenCarta = imagenCarta;
	}



	public eCarta getTipoCarta() {
		return m_TipoCarta;
	}



	public void setTipoCarta(eCarta tipoCarta) {
		m_TipoCarta = tipoCarta;
	}



	public eLugar getOcupacionCarta() {
		return m_OcupacionCarta;
	}



	public void setOcupacionCarta(eLugar ocupacionCarta) {
		m_OcupacionCarta = ocupacionCarta;
	}

	public int getPosCarta() {
		return m_PosCarta;
	}

	public void setPosCarta(int posCarta) {
		m_PosCarta = posCarta;
	}
}