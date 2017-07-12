package juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Nada implements ActionListener{
	JFrame ventana;
	private JButton btnComenzar;
	JButton jugar;
	JButton creditos;
	JButton salir;
	JButton instrucc;
	JButton regresar;
	JButton records;
	private boolean comenzar = false;

	public Nada() {
		inicializarVentana();
		inicializarComponentes();
		ubicarComponentes();
		agregarComponentes();
		registrarEventos();
		ventana.setVisible(true);
	}
	
	public void inicializarVentana(){
		ventana = new JFrame("Juego Ventana");
		ventana.setSize(438, 660);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(null);
		ventana.setResizable(false);
		
	}
	
	public void inicializarComponentes(){
		btnComenzar = new JButton("Comenzar el Juego");
	}
	
	public void ubicarComponentes(){
		btnComenzar.setBounds(100, 50, 150, 50);
	}
	
	private void agregarComponentes() {
		ventana.add(btnComenzar);
	}
	
	private void registrarEventos(){
		btnComenzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comenzar = true;
				
			}
		});
	}

	public static void main(String[] args) {
		
		if(new Nada().comenzar){
			new Juego();
		}else{
			System.out.println("nada xD");
			//Diseñar un mula de menu, el new Juego() es el mero.
		}
		

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
