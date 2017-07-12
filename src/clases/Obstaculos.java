package clases;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import juego.Juego;

public class Obstaculos extends Objetos{
	private boolean perJuego = false;
	private Random rnd = new Random();

	public Obstaculos(int x, int y, int velocidad, String llaveImagen) {
		super(x, y, velocidad, llaveImagen);
	}
	
	public boolean isPerJuego() {
		return perJuego;
	}

	public void setPerJuego(boolean perJuego) {
		this.perJuego = perJuego;
	}

	public void pintar(Graphics2D g2D,
			BufferedImage imagen,
			ImageObserver canvas){
		g2D.drawImage(imagen, 
        		x, 
        		y, 
        		canvas);
	}
	int xv;
	public void mover(){
		if (x<=-3000){
			x = 3000 + (int)((rnd.nextDouble()*150));
			xv = (int)((rnd.nextDouble()*3));
		}
		x-=(velocidad+xv);
	}
	
}
