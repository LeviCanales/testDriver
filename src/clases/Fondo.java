package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Fondo extends Objetos {

	public Fondo(int x, int y, int velocidad, String llaveImagen) {
		super(x, y, velocidad, llaveImagen);
		// TODO Auto-generated constructor stub
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
		if (x<=-1694)
			x = 1694;
		x-=velocidad;
	}
	public void moverDos(){
		if (x<=-1694)
			x = 1694;
		x-=velocidad;
	}

}
