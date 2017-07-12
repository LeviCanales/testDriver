package puntuacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class editTxt {
	private String nombre;
	private int puntos;
	
	public editTxt(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	private File archivo;
	private FileWriter flujo;
	private BufferedWriter bw;
	public void writer(){
		try {
			archivo = new File(new File("puntosJuego1.txt").getAbsolutePath());
			flujo = new FileWriter(archivo);
			bw = new BufferedWriter(flujo);
			bw.write(nombre + "," + puntos);
			bw.close();
			flujo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
