package com.veinteenbastos;

import java.util.ArrayList;
import java.util.List;

public class CJuego 
{
	private List<CMano> Manos;
	private 
	
	public CJuego() 
	{
		setManos(new ArrayList<CMano>(4));				
	}

	/**
	 * @param manos the manos to set
	 */
	public void setManos(List<CMano> manos) {
		Manos = manos;
	}

	/**
	 * @return the manos
	 */
	public List<CMano> getManos() {
		return Manos;
	}

}
