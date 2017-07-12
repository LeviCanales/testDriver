package clases;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import juego.Juego;

public class Auto extends Objetos{
	
	private String nombreJugador;
	private boolean perSalta;
	private boolean perBaja;
	private boolean tipoVelocidad = true;
	private int n=0;
	private int contadorImagen = 0;
	private boolean perAga;
	private boolean perSube;
	private int carro = 0;
	
	public Auto(String nombreJugador, int x, int y, int velocidad, String llaveImagen,
			boolean perSalta,
			boolean perBaja) {
		super(x, y, velocidad, llaveImagen);
		this.nombreJugador = nombreJugador;
		this.perSalta = perSalta;
		this.perBaja = perBaja;
	}
	
	
		
	public String getNombreJugador() {
		return nombreJugador;
	}


	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public boolean isPerSalta() {
		return perSalta;
	}

	public void setPerSalta(boolean perSalta) {
		this.perSalta = perSalta;
	}

	public boolean isPerBaja() {
		return perBaja;
	}

	public void setPerBaja(boolean perBaja) {
		this.perBaja = perBaja;
	}

	public boolean isTipoVelocidad() {
		return tipoVelocidad;
	}


	public void setTipoVelocidad(boolean tipoVelocidad) {
		this.tipoVelocidad = tipoVelocidad;
	}

	public boolean isPerAga() {
		return perAga;
	}

	public void setPerAga(boolean perAga) {
		this.perAga = perAga;
	}

	public boolean isPerSube() {
		return perSube;
	}

	public void setPerSube(boolean perSube) {
		this.perSube = perSube;
	}

	public int getCarro() {
		return carro;
	}



	public void setCarro(int carro) {
		this.carro = carro;
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
		if(!perAga){
			if(carro == 0){
				if(tipoVelocidad){
					if(contadorImagen>=40){
						llaveImagen = "Auto01";
						contadorImagen=0;
					}else if(contadorImagen == 2){
						llaveImagen = "Auto02";
					}else if(contadorImagen == 4){
						llaveImagen = "Auto03";
					}else if(contadorImagen == 6){
						llaveImagen = "Auto04";
					}else if(contadorImagen == 8){
						llaveImagen = "Auto05";
					}else if(contadorImagen == 10){
						llaveImagen = "Auto06";
					}else if(contadorImagen == 12){
						llaveImagen = "Auto07";
					}else if(contadorImagen == 14){
						llaveImagen = "Auto08";
					}else if(contadorImagen == 16){
						llaveImagen = "Auto09";
					}else if(contadorImagen == 18){
						llaveImagen = "Auto10";
					}else if(contadorImagen == 20){
						llaveImagen = "Auto11";
					}else if(contadorImagen == 22){
						llaveImagen = "Auto12";
					}else if(contadorImagen == 24){
						llaveImagen = "Auto13";
					}else if(contadorImagen == 26){
						llaveImagen = "Auto14";
					}else if(contadorImagen == 28){
						llaveImagen = "Auto15";
					}else if(contadorImagen == 30){
						llaveImagen = "Auto16";
					}else if(contadorImagen == 32){
						llaveImagen = "Auto17";
					}else if(contadorImagen == 34){
						llaveImagen = "Auto18";
					}else if(contadorImagen == 36){
						llaveImagen = "Auto19";
					}else if(contadorImagen == 38){
						llaveImagen = "Auto20";
					}
					contadorImagen++;
				}else{
					if(contadorImagen>=20){
						llaveImagen = "Auto01";
						contadorImagen=0;
					}else if(contadorImagen == 1){
						llaveImagen = "Auto02";
					}else if(contadorImagen == 2){
						llaveImagen = "Auto03";
					}else if(contadorImagen == 3){
						llaveImagen = "Auto04";
					}else if(contadorImagen == 4){
						llaveImagen = "Auto05";
					}else if(contadorImagen == 5){
						llaveImagen = "Auto06";
					}else if(contadorImagen == 6){
						llaveImagen = "Auto07";
					}else if(contadorImagen == 7){
						llaveImagen = "Auto08";
					}else if(contadorImagen == 8){
						llaveImagen = "Auto09";
					}else if(contadorImagen == 9){
						llaveImagen = "Auto10";
					}else if(contadorImagen == 10){
						llaveImagen = "Auto11";
					}else if(contadorImagen == 11){
						llaveImagen = "Auto12";
					}else if(contadorImagen == 12){
						llaveImagen = "Auto13";
					}else if(contadorImagen == 13){
						llaveImagen = "Auto14";
					}else if(contadorImagen == 14){
						llaveImagen = "Auto15";
					}else if(contadorImagen == 15){
						llaveImagen = "Auto16";
					}else if(contadorImagen == 16){
						llaveImagen = "Auto17";
					}else if(contadorImagen == 17){
						llaveImagen = "Auto18";
					}else if(contadorImagen == 18){
						llaveImagen = "Auto19";
					}else if(contadorImagen == 19){
						llaveImagen = "Auto20";
					}contadorImagen++;
				}
			}else if(carro == 1){
				if(tipoVelocidad){
					if(contadorImagen>=40){
						llaveImagen = "coche1";
						contadorImagen=0;
					}else if(contadorImagen == 2){
						llaveImagen = "coche2";
					}else if(contadorImagen == 4){
						llaveImagen = "coche3";
					}else if(contadorImagen == 6){
						llaveImagen = "coche4";
					}else if(contadorImagen == 8){
						llaveImagen = "coche5";
					}else if(contadorImagen == 10){
						llaveImagen = "coche6";
					}else if(contadorImagen == 12){
						llaveImagen = "coche7";
					}else if(contadorImagen == 14){
						llaveImagen = "coche8";
					}else if(contadorImagen == 16){
						llaveImagen = "coche9";
					}else if(contadorImagen == 18){
						llaveImagen = "coche10";
					}else if(contadorImagen == 20){
						llaveImagen = "coche11";
					}else if(contadorImagen == 22){
						llaveImagen = "coche12";
					}else if(contadorImagen == 24){
						llaveImagen = "coche13";
					}else if(contadorImagen == 26){
						llaveImagen = "coche14";
					}else if(contadorImagen == 28){
						llaveImagen = "coche15";
					}else if(contadorImagen == 30){
						llaveImagen = "coche16";
					}else if(contadorImagen == 32){
						llaveImagen = "coche17";
					}else if(contadorImagen == 34){
						llaveImagen = "coche18";
					}else if(contadorImagen == 36){
						llaveImagen = "coche19";
					}else if(contadorImagen == 38){
						llaveImagen = "coche20";
					}
					contadorImagen++;
				}else{
					if(contadorImagen>=20){
						llaveImagen = "coche1";
						contadorImagen=0;
					}else if(contadorImagen == 1){
						llaveImagen = "coche2";
					}else if(contadorImagen == 2){
						llaveImagen = "coche3";
					}else if(contadorImagen == 3){
						llaveImagen = "coche4";
					}else if(contadorImagen == 4){
						llaveImagen = "coche5";
					}else if(contadorImagen == 5){
						llaveImagen = "coche6";
					}else if(contadorImagen == 6){
						llaveImagen = "coche7";
					}else if(contadorImagen == 7){
						llaveImagen = "coche8";
					}else if(contadorImagen == 8){
						llaveImagen = "coche9";
					}else if(contadorImagen == 9){
						llaveImagen = "coche10";
					}else if(contadorImagen == 10){
						llaveImagen = "coche11";
					}else if(contadorImagen == 11){
						llaveImagen = "coche12";
					}else if(contadorImagen == 12){
						llaveImagen = "coche13";
					}else if(contadorImagen == 13){
						llaveImagen = "coche14";
					}else if(contadorImagen == 14){
						llaveImagen = "coche15";
					}else if(contadorImagen == 15){
						llaveImagen = "coche16";
					}else if(contadorImagen == 16){
						llaveImagen = "coche17";
					}else if(contadorImagen == 17){
						llaveImagen = "coche18";
					}else if(contadorImagen == 18){
						llaveImagen = "coche19";
					}else if(contadorImagen == 19){
						llaveImagen = "coche20";
					}contadorImagen++;
				}
				
			}
			
		}
		

		if ((y>(300+this.n))&&(perSalta)){
			
			if(carro==0){
				if((y>=450)&&(y<=445)){
					llaveImagen = "Spsal2";
				}else if((y>=440)&&(y<=445))
					llaveImagen = "Spsal3";
				else if((y>=430)&&(y<=445))
					llaveImagen = "Spsal4";
				else if((y>=420)&&(y<=445))
					llaveImagen = "Spsal5";
				else if((y>=410)&&(y<=445))
					llaveImagen = "Spsal6";
				else if((y>=400)&&(y<=445))
					llaveImagen = "Spsal7";
				else if((y>=390)&&(y<=445))
					llaveImagen = "Spsal8";
				else if((y>=380)&&(y<=445))
					llaveImagen = "Spsal9";
				else if((y>=370)&&(y<=445))
					llaveImagen = "Spsal10";
				else if((y>=360)&&(y<=445))
					llaveImagen = "Spsal11";
				else if((y>=350)&&(y<=445))
					llaveImagen = "Spsal12";
				else if((y>=340)&&(y<=445))
					llaveImagen = "Spsal13";
				else if((y>=330)&&(y<=445))
					llaveImagen = "Spsal14";
				else if((y>=320)&&(y<=445))
					llaveImagen = "Spsal15";
			}else if (carro == 1){
				if((y>=450)&&(y<=445)){
					llaveImagen = "cosal02";
				}else if((y>=445)&&(y<=445))
					llaveImagen = "cosal03";
				else if((y>=440)&&(y<=445))
					llaveImagen = "cosal04";
				else if((y>=435)&&(y<=445))
					llaveImagen = "cosal05";
				else if((y>=430)&&(y<=445))
					llaveImagen = "cosal06";
				else if((y>=425)&&(y<=445))
					llaveImagen = "cosal07";
				else if((y>=415)&&(y<=445))
					llaveImagen = "cosal08";
				else if((y>=405)&&(y<=445))
					llaveImagen = "cosal09";
				else if((y>=395)&&(y<=445))
					llaveImagen = "cosal10";
				else if((y>=385)&&(y<=445))
					llaveImagen = "cosal11";
				else if((y>=375)&&(y<=445))
					llaveImagen = "cosal12";
				else if((y>=365)&&(y<=445))
					llaveImagen = "cosal13";
				else if((y>=355)&&(y<=445))
					llaveImagen = "cosal14";
				else if((y>=350)&&(y<=445))
					llaveImagen = "cosal15";
				else if((y>=345)&&(y<=445))
					llaveImagen = "cosal16";
				else if((y>=335)&&(y<=445))
					llaveImagen = "cosal17";
				else if((y>=325)&&(y<=445))
					llaveImagen = "cosal18";
				else if((y>=320)&&(y<=445))
					llaveImagen = "cosal19";
				else if((y>=315)&&(y<=445))
					llaveImagen = "cosal20";
			}
			
			
			
			this.y-=velocidad;
		}
		
		else if((y<=450)){
			
			if(carro == 0){
				if(y<=310){
					llaveImagen = "Spsal16";
				}else if(y<=340)
					llaveImagen = "Spsal17";
				else if(y<=370)
					llaveImagen = "Spsal18";
				else if(y<=400)
					llaveImagen = "Spsal19";
				else if(y<=430)
					llaveImagen = "Spsal20";
			}else if(carro == 1){
				if(y<=310){
					llaveImagen = "cosal20";
				}else if(y<=350)
					llaveImagen = "cosal21";
				else if(y<=390)
					llaveImagen = "cosal22";
				else if(y<=430)
					llaveImagen = "cosal23";
			}
			
			
				
			this.n=155;	
			this.y+=velocidad;
		}else this.n=0;
		
		
	}
	
	public void bajar(){
		if((y<453)&&(perBaja)){
			this.y+=velocidad;
		}
	}
	
	int cont = 0;
	public void agacha(){
		
		if(perAga&&(y>=453)&&(y<=473)){
			
			if(carro == 0){
				if(y<=454){
					llaveImagen = "aga2";
				}else if(y<=455)
					llaveImagen = "aga3";
				else if(y<=456)
					llaveImagen = "aga4";
				else if(y<=457)
					llaveImagen = "aga5";
				else if(y<=458)
					llaveImagen = "aga6";
				else if(y<=459)
					llaveImagen = "aga7";
				else if(y<=460)
					llaveImagen = "aga8";
				else if(y<=462)
					llaveImagen = "aga9";
				else if(y<=464)
					llaveImagen = "aga10";
				else if(y<=466)
					llaveImagen = "aga11";
				else if(y<=468)
					llaveImagen = "aga12";
				else if(y<=370)
					llaveImagen = "aga13";
				else if(y<=372)
					llaveImagen = "aga14";
				else if(y<=374)
					llaveImagen = "aga15";
			}else if(carro == 1){
				if(y<=454){
					llaveImagen = "coaga2";
				}else if(y<=455)
					llaveImagen = "coaga3";
				else if(y<=456)
					llaveImagen = "coaga4";
				else if(y<=458)
					llaveImagen = "coaga5";
				else if(y<=460)
					llaveImagen = "coaga6";
				else if(y<=462)
					llaveImagen = "coaga7";
				else if(y<=464)
					llaveImagen = "coaga8";
				else if(y<=466)
					llaveImagen = "coaga9";
				else if(y<=468)
					llaveImagen = "coaga10";
				else if(y<=470)
					llaveImagen = "coaga11";
				else if(y<=472)
					llaveImagen = "coaga12";
				else if(y<=374)
					llaveImagen = "coaga13";
			}
			this.y+=velocidad;
		}/*else if((y>=450)&&(perSube)){
			this.y-=velocidad;
		}*/
	}
	
	public void subir(){
		if((y>=453)&&(perSube)){
			
			if(carro == 0){
				if(y>=472){
					llaveImagen = "aga16";
				}else if(y>=467)
					llaveImagen = "aga17";
				else if(y>=462)
					llaveImagen = "aga18";
				else if(y>=457)
					llaveImagen = "aga19";
				else if(y>=452){
					llaveImagen = "aga20";
					perSube = false;
				}
					
			}else if(carro == 1){
				if(y>=471){
					llaveImagen = "coaga14";
				}else if(y>=468)
					llaveImagen = "coaga15";
				else if(y>=465)
					llaveImagen = "coaga16";
				else if(y>=462)
					llaveImagen = "coaga17";
				else if(y>=459)
					llaveImagen = "coaga18";
				else if(y>=456)
					llaveImagen = "coaga19";
				else if(y>=454)
					llaveImagen = "coaga20";
				else if(y==453){
					perSube = false;
				}
			}
			
			this.y-=velocidad;
		}
	}
	
}

