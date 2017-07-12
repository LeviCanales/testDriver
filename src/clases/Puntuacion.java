package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import juego.Juego;

public class Puntuacion extends Posicion{
	private Integer llave;
	private String llaveImagen;
	private int cont = 0;
	private int u = 0;
	private int d = 0;
	private int c = 0;
	private int m = 0;

	
	
	public Puntuacion(int x, int y, String llaveImagen) {
		super(x, y);
		this.llaveImagen = llaveImagen;
	}
	public Puntuacion(int x, int y, Integer llave) {
		super(x, y);
		this.llave = llave;
	}


	public Integer getLlave() {
		return llave;
	}


	public void setLlave(Integer llave) {
		this.llave = llave;
	}

	public String getLlaveImagen() {
		return llaveImagen;
	}

	public void setLlaveImagen(String llaveImagen) {
		this.llaveImagen = llaveImagen;
	}
	
	

	public int getU() {
		return u;
	}
	public void setU(int u) {
		this.u = u;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	
	public void pintar(Graphics2D g2D,
			BufferedImage imagen,
			ImageObserver canvas){
		g2D.drawImage(imagen, 
        		x, 
        		y, 
        		canvas);
	}
	
	public void vidas(){
		if(cont>=30){
			llaveImagen = "s1";
			cont=0;
		}else if((cont>=10)&&(cont<20)){
			llaveImagen = "s2";
		}else if((cont>=20)&&(cont<30)){
			llaveImagen = "s3";
		}cont++;
		}
		
	public void puntuarU(){
		if(u<1000){
			llave = (u/100);
			u++;
		}else u=0;
		
	}
	public void puntuarD(){
		if(d<10000){
			llave = (d/1000);
			d++;
		}else d=0;
		
	}
	public void puntuarC(){
		if(c<100000){
			llave = (c/10000);
			c++;
		}else c=0;
		
	}
	public void puntuarM(){
		if(m<1000000){
			llave = (m/100000);
			m++;
		}else m=0;	
	}

}
