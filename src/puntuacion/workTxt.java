package puntuacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class workTxt {
	private HashMap<String,Integer> puntos = new HashMap<String,Integer>();
	
	private File archivo;
	private FileReader flujo;
	private BufferedReader bf;
	private String linea;

	private String nombre;
	private int puntoss;
	private boolean termino = false;
	
	public workTxt(String nombre, int puntoss) {
		this.nombre = nombre;
		this.puntoss = puntoss;
	}
	
	public workTxt(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntoss() {
		return puntoss;
	}

	public void setPuntoss(int puntos) {
		this.puntoss = puntos;
	}
	
	public boolean isTermino() {
		return termino;
	}

	public void setTermino(boolean termino) {
		this.termino = termino;
	}


	private String campos[];
	public void subir(){
		try {
			archivo = new File(new File("puntosJuego.txt").getAbsolutePath());
			flujo = new FileReader(archivo);
			bf = new BufferedReader(flujo);
			while ((linea = bf.readLine())!=null){
				campos = linea.split(",");
			}
			flujo.close();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		susti();
	}
	
	public void susti(){
		for(int j=0;j<(campos.length-2);j=j+2){
			for(int i=0; i<campos.length; i = i+2){
				if(nombre.equals(campos[i])){
					nombre += " I";
				}
			}
		}

		for(int i=0; i<campos.length; i = i+2){
			puntos.put(campos[i], Integer.valueOf(campos[(i+1)]));
			//System.out.println(((i/2)+1)+". "+campos[i]+". " + puntos.get(campos[i]));
		}
		
		String x;
		//int z=0;
		for(int i=0;i<(campos.length-2);i=i+2){
			for(int j=0;j<(campos.length-2);j=j+2){
				if((puntos.get(campos[j]))<(puntos.get(campos[j+2]))){
				    x = campos[j+2];
					campos[j+2] = campos[j];
					campos[j] = x;
					//System.out.println(x);
					
					/*z = puntos.get(campos[j]);
					puntos.put(campos[j],puntos.get(campos[j+2]));
					puntos.put(campos[j+2],z);*/
				}
			}
		}
		
		/*for(int i=0; i<campos.length; i = i+2){
			System.out.println(((i/2)+1)+". "+campos[i]+". " + puntos.get(campos[i]));
		}*/
		meter();

		
	}
	
	public void meter(){
		/*for(int i=0; i<campos.length; i = i+2){
			puntos.put(campos[i], Integer.valueOf(campos[(i+1)]));
			
			System.out.println(campos.length);
			System.out.println(nombre + (puntoss>puntos.get(campos[0])));
		}*/
		
		System.out.println(nombre);
		if(puntoss>=puntos.get(campos[18])){
			puntos.remove(campos[18]);
			campos[18] = nombre;
			puntos.put(campos[18], puntoss);
			//System.out.println("tamaño " + campos.length);
			String x;
			for(int i=0;i<(campos.length-2);i=i+2){
				for(int j=0;j<(campos.length-2);j=j+2){
					if((puntos.get(campos[j]))<(puntos.get(campos[j+2]))){
					    x = campos[j+2];
						campos[j+2] = campos[j];
						campos[j] = x;
						/*z = puntos.get(campos[j]);
						puntos.put(campos[j],puntos.get(campos[j+2]));
						puntos.put(campos[j+2],z);*/
					}
				}
			}
		}
		imprimir();
		
	}
	
	private FileWriter flujo1;
	private BufferedWriter bw;
	public void imprimir(){
		
		
			try {
				archivo = new File(new File("puntosJuego.txt").getAbsolutePath());
				flujo1 = new FileWriter(archivo);
				bw = new BufferedWriter(flujo1);
				for(int i=0;i<(campos.length);i=i+2){
					if(i>0){
						bw.write(",");
					}
					bw.write(campos[i] + "," + puntos.get(campos[i]));
				}
				termino = true;
				bw.close();
				flujo1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	String fin = "";
	public String toString(){
		try {
			archivo = new File(new File("puntosJuego.txt").getAbsolutePath());
			flujo = new FileReader(archivo);
			bf = new BufferedReader(flujo);
			while ((linea = bf.readLine())!=null){
				campos = linea.split(",");
			}
			flujo.close();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<campos.length; i = i+2){
			puntos.put(campos[i], Integer.valueOf(campos[(i+1)]));
			//System.out.println(((i/2)+1)+". "+campos[i]+". " + puntos.get(campos[i]));
		}
		for(int i=0; i<campos.length; i = i+2){
			fin += (((i/2)+1)+". "+campos[i]+". " + puntos.get(campos[i]) + "\n");
		}
		return fin;
	}
	
	int ma = 0;
	public int record(){
		try {
			archivo = new File(new File("puntosJuego.txt").getAbsolutePath());
			flujo = new FileReader(archivo);
			bf = new BufferedReader(flujo);
			while ((linea = bf.readLine())!=null){
				campos = linea.split(",");
			}
			flujo.close();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<campos.length; i = i+2){
			puntos.put(campos[i], Integer.valueOf(campos[(i+1)]));
			//System.out.println(((i/2)+1)+". "+campos[i]+". " + puntos.get(campos[i]));
		}
		ma = puntos.get(campos[0]);
		return ma;
	}

}
