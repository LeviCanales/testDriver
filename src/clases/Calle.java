package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Calle extends Objetos{
	private Random rnd = new Random();
	
	public Calle(int x, int y, int velocidad, String llaveImagen) {
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
		if (x<=-2430)
			x = 2430;
		x-=velocidad;
	}
	public void moverDos(){
		if (x<=-2430)
			x = 2430;
		x-=velocidad;
	}
	int xv;
	public void moverTres(){
		if (x<=-3000){
			x = 3000 + (int)((rnd.nextDouble()*150));
			xv = (int)((rnd.nextDouble()*3));
		}
		x-=(velocidad+xv);
	}

}
