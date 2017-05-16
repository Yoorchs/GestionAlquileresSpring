package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;

public class Piso implements Serializable, Comparable<Piso>{

	/**
	 * 
	 */
	public static final int CODIGO_NULO = 0;
	private static final long serialVersionUID = 1L;
	private long codigo;
	private String direccion;
	private int precionoche;
	private int superficie;
	private String referenciacatastral;
	private boolean alquilado;
	
	public Piso() {
		super();
		this.alquilado = false;
	}
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPrecionoche() {
		return precionoche;
	}

	public void setPrecionoche(int precioNoche) {
		this.precionoche = precioNoche;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public String getReferenciacatastral() {
		return referenciacatastral;
	}

	public void setReferenciacatastral(String referenciacatastral) {
		this.referenciacatastral = referenciacatastral;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}


	@Override
	public int compareTo(Piso o) {
		return this.referenciacatastral.compareToIgnoreCase(o.getReferenciacatastral());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piso other = (Piso) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Piso [codigo=" + codigo + ", direccion=" + direccion + ", precionoche=" + precionoche + ", superficie="
				+ superficie + ", referenciacatastral=" + referenciacatastral + ", alquilado=" + alquilado + "]";
	}

}
