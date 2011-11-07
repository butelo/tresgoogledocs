package com.digitalnatura.tresgoogledocs;

import java.util.ArrayList;

public class ObjetoEscaleta {

//	String ordensec = null;
	String intext ;
	String dianoche ;
	String lugar ;
	String accion ;
	String personajes ;
	 ArrayList<String> arraylist;

	public ObjetoEscaleta( String intext2, String dianoche2,
			String lugar2, String accion2, String personajes2) {
		
//		this.ordensec = pOrden;
		this.intext = intext2;
		this.dianoche = dianoche2;
		this.lugar = lugar2;
		

		this.accion = accion2;
		this.personajes = personajes2;
		
	}
	

//	public String getOrdensec() {
//		return ordensec;
//	}
//
//	public void setOrdensec(String ordensec) {
//		this.ordensec = ordensec;
//	}

	public String getIntext() {
		return intext;
	}

	public void setIntext(String intext) {
		this.intext = intext;
	}

	public String getDianoche() {
		return dianoche;
	}

	public void setDianoche(String dianoche) {
		this.dianoche = dianoche;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getPersonajes() {
		return personajes;
	}

	public void setPersonajes(String personajes) {
		this.personajes = personajes;
	}
	

	@Override
	public String toString() {
		return String
				.format("ObjetoEscaleta [ intext=%s, dianoche=%s, lugar=%s, accion=%s, personajes=%s]",
						 intext, dianoche, lugar, accion, personajes);
	}



	public ArrayList<String> toArrayList() {
		// TODO Auto-generated method stub
		arraylist = new ArrayList<String>();
		arraylist.add(getIntext());
		arraylist.add(getDianoche());
		arraylist.add(getLugar());
		arraylist.add(getAccion());
		arraylist.add(getPersonajes());
		
		
		
		return arraylist;
	}


	
	
	
	

	
}
