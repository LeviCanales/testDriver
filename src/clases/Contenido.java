package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Contenido extends Objetos {

	public Contenido(int x, int y, int velocidad, String llaveImagen) {
		super(x, y, velocidad, llaveImagen);
	}
	
	public void pintar(Graphics2D g2D,
			BufferedImage imagen,
			ImageObserver canvas){
		g2D.drawImage(imagen, 
        		x, 
        		y, 
        		canvas);
	}
	public void mover(){
		if (x<=-2500)
			x = 2500;
		x-=velocidad;
	}
	
	public void moverDos(){
		if (x<=-500)
			x = 960;
		x-=velocidad;
	}
	
	public void moverTres(){
		if (x<=-1000)
			x = 3000;
		x-=velocidad;
	}
	

}
