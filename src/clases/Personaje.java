package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Personaje extends Objetos {
	private int contadorImagen = 0;
	private boolean sigue = true;

	public Personaje(int x, int y, int velocidad, String llaveImagen) {
		super(x, y, velocidad, llaveImagen);
	}
	
	
	public boolean isSigue() {
		return sigue;
	}



	public void setSigue(boolean sigue) {
		this.sigue = sigue;
	}



	public void pintar(Graphics2D g2D,
			BufferedImage imagen,
			ImageObserver canvas){
		g2D.drawImage(imagen, 
        		x, 
        		y, 
        		canvas);
	}
	public void saltar(){
		
		//System.out.println("contadorImagen: "+contadorImagen);
				
				if((contadorImagen>=160)){
					llaveImagen = "ins0";
				}else if(contadorImagen == 140){
					llaveImagen = "ins1";
				}else if(contadorImagen == 145){
					llaveImagen = "ins2";
				}else if(contadorImagen == 150){
					llaveImagen = "ins3";
				}else if(contadorImagen == 155){
					sigue = false;
				}contadorImagen++;
	}

}
