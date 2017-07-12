package clases;

import java.awt.Rectangle;

public class Objetos extends Posicion{

	protected int velocidad;
	protected int anchoImagen;
	protected int altoImagen;
	protected String llaveImagen;
	protected Rectangle rPrime;
	protected Rectangle rS;
	
	public Objetos(int x, int y, int velocidad, String llaveImagen) {
		super(x, y);
		this.velocidad = velocidad;
		this.llaveImagen = llaveImagen;
	}
	

	public Objetos(int x, int y, int anchoImagen, int altoImagen, Rectangle rPrime) {
		super(x, y);
		this.anchoImagen = anchoImagen;
		this.altoImagen = altoImagen;
		this.rPrime = rPrime;
	}


	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getAnchoImagen() {
		return anchoImagen;
	}
	public void setAnchoImagen(int anchoImagen) {
		this.anchoImagen = anchoImagen;
	}
	public int getAltoImagen() {
		return altoImagen;
	}
	public void setAltoImagen(int altoImagen) {
		this.altoImagen = altoImagen;
	}
	public String getLlaveImagen() {
		return llaveImagen;
	}
	public void setLlaveImagen(String llaveImagen) {
		this.llaveImagen = llaveImagen;
	}
	
	public boolean colision(){
		rS= new Rectangle(x,y,anchoImagen,altoImagen);
		
		return rPrime.intersects(rS);
	}
}
