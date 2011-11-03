package com.digitalnatura.tresgoogledocs;

public class ObjetoEscaleta {

	String timestamp = null;
	String ordensec = null;
	String intext = null;
	String dianoche = null;
	String lugar = null;
	String accion = null;
	String personajes = null;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrdensec() {
		return ordensec;
	}

	public void setOrdensec(String ordensec) {
		this.ordensec = ordensec;
	}

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
		return "ObjetoEscaleta [timestamp=" + timestamp + ", ordensec="
				+ ordensec + ", intext=" + intext + ", dianoche=" + dianoche
				+ ", lugar=" + lugar + ", accion=" + accion + ", personajes="
				+ personajes + "]";
	}

}
