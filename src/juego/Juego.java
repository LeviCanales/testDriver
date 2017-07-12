package juego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Auto;
import clases.Calle;
import clases.Contenido;
import clases.Fondo;
import clases.Item;
import clases.Objetos;
import clases.Obstaculos;
import clases.Personaje;
import clases.Puntuacion;
import puntuacion.editTxt;
import puntuacion.workTxt;

public class Juego extends Canvas implements KeyListener{
	private JFrame ventana;
	
	private BufferStrategy dobleBuffer;
	private Graphics2D g2D;
	
	private boolean jugando  = false;

	public static final int ANCHO_VENTANA = 960/*DEFINA SUS PROPIAS DIMENSIONES*/;
	public static final int ALTO_VENTANA = 600/*DEFINA SUS PROPIAS DIMENSIONES*/;
	
	int lastFpsTime; //Variable auxiliar para calculo de la pausa del ciclo principal
	int fps; //Fotogramas por segundo
	
	public HashMap<String,BufferedImage> imagenes = new HashMap<String,BufferedImage>();
	public HashMap<String,BufferedImage> obstaculos = new HashMap<String,BufferedImage>();
	private HashMap<String,BufferedImage> vidas = new HashMap<String,BufferedImage>();
	private HashMap<Integer,BufferedImage> puntuaciones = new HashMap<Integer,BufferedImage>();
	private HashMap<String,BufferedImage> item = new HashMap<String,BufferedImage>();
	private HashMap<String,BufferedImage> content = new HashMap<String,BufferedImage>();
	public Auto auto; //Lo mejor es almacenar los objetos de juego en un ArrayList
	private Calle calle1;
	private Calle calle2;
	private Calle calle3;
	private Fondo fondo1;
	private Fondo fondo2;
	public Obstaculos bache;
	private Obstaculos carro;
	private Obstaculos cono;
	private Obstaculos undi;
	private Obstaculos moto;
	private Obstaculos cuatri;
	private Puntuacion semaforo;
	private Puntuacion unidad;
	private Puntuacion decena;
	private Puntuacion centena;
	private Item ptovida;
	private Item other;
	private Personaje instructora;
	private String nombre;
	
	private Contenido edif1;
	private Contenido edif2;
	private Contenido edif3;
	private Contenido nubes;
	private Contenido sol;
	private int v = 3;
	private boolean pausa = true;
	boolean menu = true;
	AudioClip clip = Applet.newAudioClip(getClass().getResource("/recursos/BLAST9.WAV"));
	AudioClip clip1 = Applet.newAudioClip(getClass().getResource("/recursos/BRAKING6.WAV"));
	String prob;
	public Juego(){
		cargarImagenes();
		inicializarObjetosJuego();
		//Crear la ventana y establecer sus propiedades
		int opcion = 0;
		do{
			prob = ((String) JOptionPane.showInputDialog(null,
					"Adentrate a un manejo"
					+ "\nnunca antes visto:","Test Driver",JOptionPane.QUESTION_MESSAGE
							, new ImageIcon(getClass().getResource("/recursos/llantaicon.png"))
							,new Object[]{"Empezar Juego","Ver Puntuacion","Salir"},
							"Empezar Juego"));
			if(prob.equals("Empezar Juego")){
				opcion = 1;
			} else if(prob.equals("Ver Puntuacion")){
				opcion = 2;
			} else if(prob.equals("Salir")){
				opcion = 0;
			}
			
			switch(opcion){
				case 0:
					JOptionPane.showMessageDialog(null, "Gracias por Jugar");
					break;
				case 1:{
					ventana = new JFrame(); //Crear instancia de la ventana
					//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminar aplicacion cuando se de click en la X
					ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA); //Establecer las dimensiones de la ventana
					ventana.setLocationRelativeTo(null); //Centrar ventana en el escritorio
					ventana.setTitle("Test Driver"); //Definir el titulo de la ventana
					 //Agregar el Canvas (lienzo) a la ventana
					nombre = new String((String) new JOptionPane().showInputDialog(null,
							"Ingrese su nombre:","Test Driver",JOptionPane.WARNING_MESSAGE
							, new ImageIcon(getClass().getResource("/recursos/tIMMY.png"))
							,null,"Timmy"));
					ventana.setResizable(false);
					ventana.getContentPane().add(this);
					ventana.setVisible(true); //Mostrar ventana
					ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					metodo();
					createBufferStrategy(2); //Crear una estrategia de doble buffer (metodo optimo para videojuegos)
					dobleBuffer = getBufferStrategy(); //Obtener una instancia de BufferStrategy (para luego dibujar los componentes)
					
					jugando = true; //Definir la variable logica de juego en true	
					
					//this.requestFocus(); //Solicitar el foco para que los eventos del teclado puedan ser capturados por el Canvas.
					//this.addKeyListener(this); //Agregar interfaz de escucha para poder interceptar las teclas pulsadas por el usuario.
					cicloPrincipal();//Ejecutar el ciclo principal del juego (Este no necesariamente debe llamarse desde aqui)
					
				}
					break;
				case 2:{
					JOptionPane.showMessageDialog(null, new workTxt().toString());
				}					
					
			}			
		}while(opcion!=0);
	}
	
	public void metodo(){
		this.requestFocus(); //Solicitar el foco para que los eventos del teclado puedan ser capturados por el Canvas.
		this.addKeyListener(this);
	}

	public void inicializarObjetosJuego(){
		fondo1 = new Fondo(0,0,2,"fondo1");
		fondo2 = new Fondo(1694,0,2,"fondo2");
		auto = new Auto(nombre, 10, 450, v, "Auto01", false, false);
		calle1 = new Calle(0, 440,v,"calle1");
		calle2 = new Calle(2430, 440,v,"calle2");
		calle3 = new Calle(7420, 330,v,"calle3");
		bache = new Obstaculos(980, 440, v, "bache");
		carro = new Obstaculos(1900, 440, v, "carro");
		cono = new Obstaculos(2820, 430, v, "cono");
		undi = new Obstaculos(3740, 440, v, "undi");
		moto = new Obstaculos(4660, 440, v, "moto");
		cuatri= new Obstaculos(5580, 440, v, "cuatri");
		semaforo = new Puntuacion(900, 20, "s3");
		unidad = new Puntuacion(800, 15, 0);
		decena = new Puntuacion(720, 15, 0);
		centena = new Puntuacion(640, 15, 0);
		ptovida = new Item(6500, 350, v, "vida");
		other = new Item(3500, 350, v, "other");
		
		edif1 = new Contenido(400, 160, 2, "edif1");
		edif2 = new Contenido(1500, 180, 2, "edif2");
		edif3 = new Contenido(2600, 220, 2, "edif3");
		nubes = new Contenido(300, 10, 1, "cloud");
		sol = new Contenido(800, 10, 1, "sun");
		instructora = new Personaje(460, 110, 4, "ins0");
	}
	
	//Cargar Imagenes
	public void cargarImagenes(){
		//Es mas conveniente almacenar las imagenes en un hashmap para poder darle un alias.
		try {
			imagenes.put("fondo1", ImageIO.read(getClass().getResource("/recursos/Fondo Br1.jpg")));
			imagenes.put("fondo2", ImageIO.read(getClass().getResource("/recursos/Fondo Br2.jpg")));

			imagenes.put("calle1", ImageIO.read(getClass().getResource("/recursos/Calle2.jpg")));
			imagenes.put("calle2", ImageIO.read(getClass().getResource("/recursos/Calle2.jpg")));
			imagenes.put("calle3", ImageIO.read(getClass().getResource("/recursos/Calle3.png")));
			imagenes.put("Auto01", ImageIO.read(getClass().getResource("/recursos/Auto01.png")));
			imagenes.put("Auto02", ImageIO.read(getClass().getResource("/recursos/Auto02.png")));
			imagenes.put("Auto03", ImageIO.read(getClass().getResource("/recursos/Auto03.png")));
			imagenes.put("Auto04", ImageIO.read(getClass().getResource("/recursos/Auto04.png")));
			imagenes.put("Auto05", ImageIO.read(getClass().getResource("/recursos/Auto05.png")));
			imagenes.put("Auto06", ImageIO.read(getClass().getResource("/recursos/Auto06.png")));
			imagenes.put("Auto07", ImageIO.read(getClass().getResource("/recursos/Auto07.png")));
			imagenes.put("Auto08", ImageIO.read(getClass().getResource("/recursos/Auto08.png")));
			imagenes.put("Auto09", ImageIO.read(getClass().getResource("/recursos/Auto09.png")));
			imagenes.put("Auto10", ImageIO.read(getClass().getResource("/recursos/Auto10.png")));
			imagenes.put("Auto11", ImageIO.read(getClass().getResource("/recursos/Auto11.png")));
			imagenes.put("Auto12", ImageIO.read(getClass().getResource("/recursos/Auto12.png")));
			imagenes.put("Auto13", ImageIO.read(getClass().getResource("/recursos/Auto13.png")));
			imagenes.put("Auto14", ImageIO.read(getClass().getResource("/recursos/Auto14.png")));
			imagenes.put("Auto15", ImageIO.read(getClass().getResource("/recursos/Auto15.png")));
			imagenes.put("Auto16", ImageIO.read(getClass().getResource("/recursos/Auto16.png")));
			imagenes.put("Auto17", ImageIO.read(getClass().getResource("/recursos/Auto17.png")));
			imagenes.put("Auto18", ImageIO.read(getClass().getResource("/recursos/Auto18.png")));
			imagenes.put("Auto19", ImageIO.read(getClass().getResource("/recursos/Auto19.png")));
			imagenes.put("Auto20", ImageIO.read(getClass().getResource("/recursos/Auto20.png")));
			
			imagenes.put("Spsal1", ImageIO.read(getClass().getResource("/recursos/Sprsalt01.png")));
			imagenes.put("Spsal2", ImageIO.read(getClass().getResource("/recursos/Sprsalt02.png")));
			imagenes.put("Spsal3", ImageIO.read(getClass().getResource("/recursos/Sprsalt03.png")));
			imagenes.put("Spsal4", ImageIO.read(getClass().getResource("/recursos/Sprsalt04.png")));
			imagenes.put("Spsal5", ImageIO.read(getClass().getResource("/recursos/Sprsalt05.png")));
			imagenes.put("Spsal6", ImageIO.read(getClass().getResource("/recursos/Sprsalt06.png")));
			imagenes.put("Spsal7", ImageIO.read(getClass().getResource("/recursos/Sprsalt07.png")));
			imagenes.put("Spsal8", ImageIO.read(getClass().getResource("/recursos/Sprsalt08.png")));
			imagenes.put("Spsal9", ImageIO.read(getClass().getResource("/recursos/Sprsalt09.png")));
			imagenes.put("Spsal10", ImageIO.read(getClass().getResource("/recursos/Sprsalt10.png")));
			imagenes.put("Spsal11", ImageIO.read(getClass().getResource("/recursos/Sprsalt11.png")));
			imagenes.put("Spsal12", ImageIO.read(getClass().getResource("/recursos/Sprsalt12.png")));
			imagenes.put("Spsal13", ImageIO.read(getClass().getResource("/recursos/Sprsalt13.png")));
			imagenes.put("Spsal14", ImageIO.read(getClass().getResource("/recursos/Sprsalt14.png")));
			imagenes.put("Spsal15", ImageIO.read(getClass().getResource("/recursos/Sprsalt15.png")));
			imagenes.put("Spsal16", ImageIO.read(getClass().getResource("/recursos/Sprsalt16.png")));
			imagenes.put("Spsal17", ImageIO.read(getClass().getResource("/recursos/Sprsalt17.png")));
			imagenes.put("Spsal18", ImageIO.read(getClass().getResource("/recursos/Sprsalt18.png")));
			imagenes.put("Spsal19", ImageIO.read(getClass().getResource("/recursos/Sprsalt19.png")));
			imagenes.put("Spsal20", ImageIO.read(getClass().getResource("/recursos/Sprsalt20.png")));
			
			imagenes.put("aga1", ImageIO.read(getClass().getResource("/recursos/Aga01.png")));
			imagenes.put("aga2", ImageIO.read(getClass().getResource("/recursos/Aga02.png")));
			imagenes.put("aga3", ImageIO.read(getClass().getResource("/recursos/Aga03.png")));
			imagenes.put("aga4", ImageIO.read(getClass().getResource("/recursos/Aga04.png")));
			imagenes.put("aga5", ImageIO.read(getClass().getResource("/recursos/Aga05.png")));
			imagenes.put("aga6", ImageIO.read(getClass().getResource("/recursos/Aga06.png")));
			imagenes.put("aga7", ImageIO.read(getClass().getResource("/recursos/Aga07.png")));
			imagenes.put("aga8", ImageIO.read(getClass().getResource("/recursos/Aga08.png")));
			imagenes.put("aga9", ImageIO.read(getClass().getResource("/recursos/Aga09.png")));
			imagenes.put("aga10", ImageIO.read(getClass().getResource("/recursos/Aga10.png")));
			imagenes.put("aga11", ImageIO.read(getClass().getResource("/recursos/Aga11.png")));
			imagenes.put("aga12", ImageIO.read(getClass().getResource("/recursos/Aga12.png")));
			imagenes.put("aga13", ImageIO.read(getClass().getResource("/recursos/Aga13.png")));
			imagenes.put("aga14", ImageIO.read(getClass().getResource("/recursos/Aga14.png")));
			imagenes.put("aga15", ImageIO.read(getClass().getResource("/recursos/Aga15.png")));
			imagenes.put("aga16", ImageIO.read(getClass().getResource("/recursos/Aga16.png")));
			imagenes.put("aga17", ImageIO.read(getClass().getResource("/recursos/Aga17.png")));
			imagenes.put("aga18", ImageIO.read(getClass().getResource("/recursos/Aga18.png")));
			imagenes.put("aga19", ImageIO.read(getClass().getResource("/recursos/Aga19.png")));
			imagenes.put("aga20", ImageIO.read(getClass().getResource("/recursos/Aga20.png")));
			
			imagenes.put("coche1", ImageIO.read(getClass().getResource("/recursos/coche01.png")));
			imagenes.put("coche2", ImageIO.read(getClass().getResource("/recursos/coche02.png")));
			imagenes.put("coche3", ImageIO.read(getClass().getResource("/recursos/coche03.png")));
			imagenes.put("coche4", ImageIO.read(getClass().getResource("/recursos/coche04.png")));
			imagenes.put("coche5", ImageIO.read(getClass().getResource("/recursos/coche05.png")));
			imagenes.put("coche6", ImageIO.read(getClass().getResource("/recursos/coche06.png")));
			imagenes.put("coche7", ImageIO.read(getClass().getResource("/recursos/coche07.png")));
			imagenes.put("coche8", ImageIO.read(getClass().getResource("/recursos/coche08.png")));
			imagenes.put("coche9", ImageIO.read(getClass().getResource("/recursos/coche09.png")));
			imagenes.put("coche10", ImageIO.read(getClass().getResource("/recursos/coche10.png")));
			imagenes.put("coche11", ImageIO.read(getClass().getResource("/recursos/coche11.png")));
			imagenes.put("coche12", ImageIO.read(getClass().getResource("/recursos/coche12.png")));
			imagenes.put("coche13", ImageIO.read(getClass().getResource("/recursos/coche13.png")));
			imagenes.put("coche14", ImageIO.read(getClass().getResource("/recursos/coche14.png")));
			imagenes.put("coche15", ImageIO.read(getClass().getResource("/recursos/coche15.png")));
			imagenes.put("coche16", ImageIO.read(getClass().getResource("/recursos/coche16.png")));
			imagenes.put("coche17", ImageIO.read(getClass().getResource("/recursos/coche17.png")));
			imagenes.put("coche18", ImageIO.read(getClass().getResource("/recursos/coche18.png")));
			imagenes.put("coche19", ImageIO.read(getClass().getResource("/recursos/coche19.png")));
			imagenes.put("coche20", ImageIO.read(getClass().getResource("/recursos/coche20.png")));
			
			imagenes.put("cosal01", ImageIO.read(getClass().getResource("/recursos/cosal01.png")));
			imagenes.put("cosal02", ImageIO.read(getClass().getResource("/recursos/cosal02.png")));
			imagenes.put("cosal03", ImageIO.read(getClass().getResource("/recursos/cosal03.png")));
			imagenes.put("cosal04", ImageIO.read(getClass().getResource("/recursos/cosal04.png")));
			imagenes.put("cosal05", ImageIO.read(getClass().getResource("/recursos/cosal05.png")));
			imagenes.put("cosal06", ImageIO.read(getClass().getResource("/recursos/cosal06.png")));
			imagenes.put("cosal07", ImageIO.read(getClass().getResource("/recursos/cosal07.png")));
			imagenes.put("cosal08", ImageIO.read(getClass().getResource("/recursos/cosal08.png")));
			imagenes.put("cosal09", ImageIO.read(getClass().getResource("/recursos/cosal09.png")));
			imagenes.put("cosal10", ImageIO.read(getClass().getResource("/recursos/cosal10.png")));
			imagenes.put("cosal11", ImageIO.read(getClass().getResource("/recursos/cosal11.png")));
			imagenes.put("cosal12", ImageIO.read(getClass().getResource("/recursos/cosal12.png")));
			imagenes.put("cosal13", ImageIO.read(getClass().getResource("/recursos/cosal12.png")));
			imagenes.put("cosal14", ImageIO.read(getClass().getResource("/recursos/cosal14.png")));
			imagenes.put("cosal15", ImageIO.read(getClass().getResource("/recursos/cosal15.png")));
			imagenes.put("cosal16", ImageIO.read(getClass().getResource("/recursos/cosal16.png")));
			imagenes.put("cosal17", ImageIO.read(getClass().getResource("/recursos/cosal17.png")));
			imagenes.put("cosal18", ImageIO.read(getClass().getResource("/recursos/cosal18.png")));
			imagenes.put("cosal19", ImageIO.read(getClass().getResource("/recursos/cosal19.png")));
			imagenes.put("cosal20", ImageIO.read(getClass().getResource("/recursos/cosal20.png")));
			imagenes.put("cosal21", ImageIO.read(getClass().getResource("/recursos/cosal21.png")));
			imagenes.put("cosal22", ImageIO.read(getClass().getResource("/recursos/cosal22.png")));
			imagenes.put("cosal23", ImageIO.read(getClass().getResource("/recursos/cosal23.png")));
			
			imagenes.put("coaga1", ImageIO.read(getClass().getResource("/recursos/coaga01.png")));
			imagenes.put("coaga2", ImageIO.read(getClass().getResource("/recursos/coaga02.png")));
			imagenes.put("coaga3", ImageIO.read(getClass().getResource("/recursos/coaga03.png")));
			imagenes.put("coaga4", ImageIO.read(getClass().getResource("/recursos/coaga04.png")));
			imagenes.put("coaga5", ImageIO.read(getClass().getResource("/recursos/coaga05.png")));
			imagenes.put("coaga6", ImageIO.read(getClass().getResource("/recursos/coaga06.png")));
			imagenes.put("coaga7", ImageIO.read(getClass().getResource("/recursos/coaga07.png")));
			imagenes.put("coaga8", ImageIO.read(getClass().getResource("/recursos/coaga08.png")));
			imagenes.put("coaga9", ImageIO.read(getClass().getResource("/recursos/coaga09.png")));
			imagenes.put("coaga10", ImageIO.read(getClass().getResource("/recursos/coaga10.png")));
			imagenes.put("coaga11", ImageIO.read(getClass().getResource("/recursos/coaga11.png")));
			imagenes.put("coaga12", ImageIO.read(getClass().getResource("/recursos/coaga12.png")));
			imagenes.put("coaga13", ImageIO.read(getClass().getResource("/recursos/coaga13.png")));
			imagenes.put("coaga14", ImageIO.read(getClass().getResource("/recursos/coaga14.png")));
			imagenes.put("coaga15", ImageIO.read(getClass().getResource("/recursos/coaga15.png")));
			imagenes.put("coaga16", ImageIO.read(getClass().getResource("/recursos/coaga16.png")));
			imagenes.put("coaga17", ImageIO.read(getClass().getResource("/recursos/coaga17.png")));
			imagenes.put("coaga18", ImageIO.read(getClass().getResource("/recursos/coaga18.png")));
			imagenes.put("coaga19", ImageIO.read(getClass().getResource("/recursos/coaga19.png")));
			imagenes.put("coaga20", ImageIO.read(getClass().getResource("/recursos/coaga20.png")));
			
			imagenes.put("fire1", ImageIO.read(getClass().getResource("/recursos/SprsaltFire.png")));
			imagenes.put("fire2", ImageIO.read(getClass().getResource("/recursos/cosalFire.png")));
			
			obstaculos.put("bache", ImageIO.read(getClass().getResource("/recursos/BacheR.png")));
			obstaculos.put("carro", ImageIO.read(getClass().getResource("/recursos/Carro.png")));
			obstaculos.put("cono", ImageIO.read(getClass().getResource("/recursos/Cono.png")));
			obstaculos.put("undi", ImageIO.read(getClass().getResource("/recursos/undimiento.png")));
			obstaculos.put("moto", ImageIO.read(getClass().getResource("/recursos/Moto2.png")));
			obstaculos.put("cuatri", ImageIO.read(getClass().getResource("/recursos/CuatriMoto.png")));
			
			vidas.put("s1", ImageIO.read(getClass().getResource("/recursos/sema l1r.png")));
			vidas.put("s2", ImageIO.read(getClass().getResource("/recursos/sema l2r.png")));
			vidas.put("s3", ImageIO.read(getClass().getResource("/recursos/sema l3r.png")));
			
			puntuaciones.put(0, ImageIO.read(getClass().getResource("/recursos/0.png")));
			puntuaciones.put(1, ImageIO.read(getClass().getResource("/recursos/1.png")));
			puntuaciones.put(2, ImageIO.read(getClass().getResource("/recursos/2.png")));
			puntuaciones.put(3, ImageIO.read(getClass().getResource("/recursos/3.png")));
			puntuaciones.put(4, ImageIO.read(getClass().getResource("/recursos/4.png")));
			puntuaciones.put(5, ImageIO.read(getClass().getResource("/recursos/5.png")));
			puntuaciones.put(6, ImageIO.read(getClass().getResource("/recursos/6.png")));
			puntuaciones.put(7, ImageIO.read(getClass().getResource("/recursos/7.png")));
			puntuaciones.put(8, ImageIO.read(getClass().getResource("/recursos/8.png")));
			puntuaciones.put(9, ImageIO.read(getClass().getResource("/recursos/9.png")));
			
			item.put("vida", ImageIO.read(getClass().getResource("/recursos/item1.png")));
			item.put("other", ImageIO.read(getClass().getResource("/recursos/item2.png")));
			
			content.put("edif1", ImageIO.read(getClass().getResource("/recursos/Edif1.png")));
			content.put("edif2", ImageIO.read(getClass().getResource("/recursos/Edif2.png")));
			content.put("edif3", ImageIO.read(getClass().getResource("/recursos/Edif3.png")));
			content.put("cloud", ImageIO.read(getClass().getResource("/recursos/nubes.png")));
			content.put("sun", ImageIO.read(getClass().getResource("/recursos/sol.png")));
			content.put("ins0", ImageIO.read(getClass().getResource("/recursos/Instructura.png")));
			content.put("ins1", ImageIO.read(getClass().getResource("/recursos/Instructura1.png")));
			content.put("ins2", ImageIO.read(getClass().getResource("/recursos/Instructura2.png")));
			content.put("ins3", ImageIO.read(getClass().getResource("/recursos/Instructura3.png")));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Metodo para pintar los componentes del juego
	
	workTxt work;
	editTxt edit;
	Font fuente = new Font("Monospaced", Font.BOLD, 50);
	
	private void pintar(){
		
        g2D = (Graphics2D)dobleBuffer.getDrawGraphics(); //Obtener la instancia de Graphics para pintar los elementos
        
        //Puede borrar las siguientes 4 lineas
       /* g2D.setColor(Color.BLACK); //Definir el color negro en el contexto
        g2D.fillRect(0, 0, ANCHO_VENTANA, ALTO_VENTANA); //Dibujar un rectangulo
        g2D.setColor(Color.WHITE); //Definir el color blanco en el contexto
        g2D.drawString("Dibuje en esta seccion los componentes",30,30);*/ //Dibujar un string
        
        //Pintar el auto
        //g2D.drawImage(ImageIO.read(getClass().getResource("/recursos/Fondo.jpg")), 0, 0, this);
        
        fondo1.pintar(g2D,imagenes.get(fondo1.getLlaveImagen()), this);
        fondo2.pintar(g2D,imagenes.get(fondo2.getLlaveImagen()), this);
        
        sol.pintar(g2D,content.get(sol.getLlaveImagen()), this);
        nubes.pintar(g2D,content.get(nubes.getLlaveImagen()), this);
        edif1.pintar(g2D,content.get(edif1.getLlaveImagen()), this);
        edif2.pintar(g2D,content.get(edif2.getLlaveImagen()), this);
        edif3.pintar(g2D,content.get(edif3.getLlaveImagen()), this);
        
        calle1.pintar(g2D,imagenes.get(calle1.getLlaveImagen()), this);
        calle2.pintar(g2D,imagenes.get(calle2.getLlaveImagen()), this);
        calle3.pintar(g2D,imagenes.get(calle3.getLlaveImagen()), this);
        semaforo.pintar(g2D, vidas.get(semaforo.getLlaveImagen()), this);
        
        bache.pintar(g2D, obstaculos.get(bache.getLlaveImagen()), this);
        carro.pintar(g2D, obstaculos.get(carro.getLlaveImagen()), this);
        cono.pintar(g2D, obstaculos.get(cono.getLlaveImagen()), this);
        undi.pintar(g2D, obstaculos.get(undi.getLlaveImagen()), this);
        moto.pintar(g2D, obstaculos.get(moto.getLlaveImagen()), this);
        cuatri.pintar(g2D, obstaculos.get(cuatri.getLlaveImagen()), this);
        
        unidad.pintar(g2D, puntuaciones.get(unidad.getLlave()), this);
        decena.pintar(g2D, puntuaciones.get(decena.getLlave()), this);
        centena.pintar(g2D, puntuaciones.get(centena.getLlave()), this);
        
        auto.pintar(g2D, imagenes.get(auto.getLlaveImagen()), this);
        
        ptovida.pintar(g2D, item.get(ptovida.getLlaveImagen()), this);
        other.pintar(g2D, item.get(other.getLlaveImagen()), this);
        
        if(instructora.isSigue()){
        	instructora.pintar(g2D, content.get(instructora.getLlaveImagen()), this);
        }
        
        g2D.setColor(Color.YELLOW);
        g2D.setFont(fuente);
        g2D.drawString((("TOP:"+ new workTxt().record() +" Vamos " + nombre)), 50, 75);
        
        if(perdio){
        	if (i4<=1){
        		clip.play();
        	}
        	
        	
        	
        	if(auto.getCarro()==0){
        		auto.setLlaveImagen("fire1");
        	}else if(auto.getCarro()==1){
        		auto.setLlaveImagen("fire2");
        	}
        	i4++;
        	if((i4>=50)&&(i4<200)){
        		
        		g2D.setColor(Color.BLACK); //Definir el color negro en el contexto
                g2D.fillRect(0, 0, ANCHO_VENTANA, ALTO_VENTANA);
                g2D.setColor(Color.WHITE); //Definir el color blanco en el contexto
                //g2D.drawString("Game Over",30,30);
        	}
        	if(i4>=200){
        		//System.out.println(((unidad.getLlave()+(decena.getLlave()*10)+(centena.getLlave()*100))));
        		
        		work = new workTxt(nombre, (((unidad.getLlave()+(decena.getLlave()*10)+(centena.getLlave()*100)))));
        		edit = new editTxt(nombre, (((unidad.getLlave()+(decena.getLlave()*10)+(centena.getLlave()*100)))));
        		
        		edit.setPuntos(((unidad.getLlave()+(decena.getLlave()*10)+(centena.getLlave()*100))));
        		edit.setNombre(nombre);
        		edit.writer();
        		//work.setPuntoss(((unidad.getLlave()+(decena.getLlave()*10)+(centena.getLlave()*100))));
        		//work.setNombre(nombre);
        		work.subir();
        		if(work.isTermino()){
        			
        			System.exit(0);
        		}
        		
        		
        	}
        }
        
        if(perdio){
        	if((i4>=50)){
        		g2D.setColor(Color.GREEN);
                g2D.drawRect((auto.getX()+20), auto.getY(), (imagenes.get(auto.getLlaveImagen()).getWidth()-40), (imagenes.get(auto.getLlaveImagen()).getHeight()-30));
                g2D.drawRect((bache.getX()+40), (bache.getY()+80), (obstaculos.get(bache.getLlaveImagen()).getWidth()-90), (obstaculos.get(bache.getLlaveImagen()).getHeight()-80));
                g2D.drawRect((carro.getX()+20), (carro.getY()+20), (obstaculos.get(carro.getLlaveImagen()).getWidth()-40), (obstaculos.get(carro.getLlaveImagen()).getHeight()-20));
                g2D.drawRect((cono.getX()+20), (cono.getY()+20), (obstaculos.get(cono.getLlaveImagen()).getWidth()-40), (obstaculos.get(cono.getLlaveImagen()).getHeight()-20));
                g2D.drawRect((undi.getX()+20), (undi.getY()+20), (obstaculos.get(undi.getLlaveImagen()).getWidth()-40), (obstaculos.get(undi.getLlaveImagen()).getHeight()-20));
                g2D.drawRect((moto.getX()+20), (moto.getY()+20), (obstaculos.get(moto.getLlaveImagen()).getWidth()-40), (obstaculos.get(moto.getLlaveImagen()).getHeight()-20));
                g2D.drawRect((cuatri.getX()+20), (cuatri.getY()+20), (obstaculos.get(cuatri.getLlaveImagen()).getWidth()-40), (obstaculos.get(cuatri.getLlaveImagen()).getHeight()-20));
                
                g2D.drawRect((calle3.getX()), (calle3.getY()+80), (imagenes.get(calle3.getLlaveImagen()).getWidth()-10), (imagenes.get(calle3.getLlaveImagen()).getHeight()-80));
                
                g2D.drawRect((ptovida.getX()), (ptovida.getY()), (item.get(ptovida.getLlaveImagen()).getWidth()), (item.get(ptovida.getLlaveImagen()).getHeight()));
                g2D.drawRect((other.getX()), (other.getY()), (item.get(other.getLlaveImagen()).getWidth()), (item.get(other.getLlaveImagen()).getHeight()));
        	}
        	
            try {
				g2D.drawImage(ImageIO.read(getClass().getResource("/recursos/GameOver.png")), 50, 50, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        //g2D.drawRect((auto.getX()+20), auto.getY(), (imagenes.get(auto.getLlaveImagen()).getWidth()-40), (imagenes.get(auto.getLlaveImagen()).getHeight()-30));
        //g2D.drawRect((bache.getX()+40), (bache.getY()+80), (obstaculos.get(bache.getLlaveImagen()).getWidth()-90), (obstaculos.get(bache.getLlaveImagen()).getHeight()-80));
        
        dobleBuffer.show(); //Mostrar lo que se ha dibujado
       
	}
	
	
	int i1 = 0;
	int i2 = 0;
	int i3 = 0;
	int i4 = 0;
	int i5 = 0;
	int i6 = 0;
	boolean perdio = false;
	public void colisiones(){
		Rectangle r1 = new Rectangle((auto.getX()+20), auto.getY(), (imagenes.get(auto.getLlaveImagen()).getWidth()-40), (imagenes.get(auto.getLlaveImagen()).getHeight()-30));
        Objetos r2 = new Objetos((bache.getX()+40), (bache.getY()+80), (obstaculos.get(bache.getLlaveImagen()).getWidth()-90), (obstaculos.get(bache.getLlaveImagen()).getHeight()-80),r1);
        Objetos r3 = new Objetos((carro.getX()+20), (carro.getY()+20), (obstaculos.get(carro.getLlaveImagen()).getWidth()-40), (obstaculos.get(carro.getLlaveImagen()).getHeight()-20),r1);
        Objetos r4 = new Objetos((cono.getX()+20), (cono.getY()+20), (obstaculos.get(cono.getLlaveImagen()).getWidth()-40), (obstaculos.get(cono.getLlaveImagen()).getHeight()-20),r1);
        Objetos r5 = new Objetos((undi.getX()+20), (undi.getY()+20), (obstaculos.get(undi.getLlaveImagen()).getWidth()-40), (obstaculos.get(undi.getLlaveImagen()).getHeight()-20),r1);
        Objetos r6 = new Objetos((moto.getX()+20), (moto.getY()+20), (obstaculos.get(moto.getLlaveImagen()).getWidth()-40), (obstaculos.get(moto.getLlaveImagen()).getHeight()-20),r1);
        Objetos r7 = new Objetos((cuatri.getX()+20), (cuatri.getY()+20), (obstaculos.get(cuatri.getLlaveImagen()).getWidth()-40), (obstaculos.get(cuatri.getLlaveImagen()).getHeight()-20),r1);
        Objetos r8 = new Objetos((calle3.getX()), (calle3.getY()+80), (imagenes.get(calle3.getLlaveImagen()).getWidth()-10), (imagenes.get(calle3.getLlaveImagen()).getHeight()-80),r1);
        
        Objetos ri1 = new Objetos((ptovida.getX()), (ptovida.getY()), (item.get(ptovida.getLlaveImagen()).getWidth()), (item.get(ptovida.getLlaveImagen()).getHeight()),r1);
        Objetos ri2 = new Objetos((other.getX()), (other.getY()), (item.get(other.getLlaveImagen()).getWidth()), (item.get(other.getLlaveImagen()).getHeight()),r1);
        if(r2.colision()||r3.colision()||r4.colision()||r5.colision()||
        		r6.colision()||r7.colision()||r8.colision()){
        	i1++;
        	if((i1<30)){
        		semaforo.setLlaveImagen("s3");
        	}
        	if((i1>=30)&&(i1<60)){
        		semaforo.setLlaveImagen("s2");
        	}
        	if((i1>=60)&&(i1<90)){
        		semaforo.setLlaveImagen("s1");
        	}
        	if(i1>=90){
        		perdio = true;
        	}
        }
        
        //Colisiones con Items:
        
        if(ri2.colision()){
        	i2++;
        	if((i2>=15)&&(auto.getCarro()==0)){
        		auto.setCarro(1);
        		other.setY(-1000);
        		i2=0;
        	}else if((i2>=15)&&(auto.getCarro()==1)){
        		auto.setCarro(0);
        		other.setY(-1000);
        		i2=0;
        	}
        	
        }
        
        if(other.getX()<=-2995){
        	
        	other.setY(350);
        }
        
        if(ri1.colision()){
        	i3++;
        	if((i3>=15)){
        		i1=0;
        		semaforo.setLlaveImagen("s3");
        		ptovida.setY(-1000);
        	}
        }
        
        if(ptovida.getX()<=-2995){
        	i3=0;
        	ptovida.setY(350);
        }

        int y = auto.getY();
        if((r2.colision())){
        	i1++;
        	//System.out.print("1. " + i1 + ". " + ((r2.colision())) + "\n");
        	if(i1>=15){
        		perdio = true;
        		auto.setY(y+=auto.getVelocidad());
        	} 
        }
        
        /*if(!r2.colision()){
        	//perdio = false;
    		i1=0;
    	}
        
        if(r3.colision()){
        	i2++;
        	System.out.print("2. " + i2 + ". " + ((r3.colision())) + "\n");
        }
        if(r4.colision()){
        	i3++;
        	System.out.print("3. " + i3 + ". " + ((r4.colision())) + "\n");
        }
        if(r5.colision()){
        	i4++;
        	System.out.print("4. " + i4 + ". " + ((r5.colision())) + "\n");
        }
        if(r6.colision()){
        	i5++;
        	System.out.print("5. " + i5 + ". " + ((r6.colision())) + "\n");
        }
        if(r7.colision()){
        	i6++;
        	System.out.print("6. " + i6 + ". " + ((r7.colision())) + "\n");
        }*/
	}
	
	public void actualizar(){
		//Agregue aqui el codigo necesario para actualizar los componentes de juego y construir la logica del juego
		if(pausa){
			fondo1.mover();
			fondo2.moverDos();
			
			edif1.mover();
			edif2.mover();
			edif3.mover();
			nubes.moverDos();
			sol.moverTres();
			
			calle1.mover();
			calle2.moverDos();
			calle3.moverTres();
			bache.mover();
			carro.mover();
			cono.mover();
			undi.mover();
			moto.mover();
			cuatri.mover();
			
			//semaforo.vidas();
			
			
			if(!perdio){
				auto.saltar();
				auto.bajar();
				auto.agacha();
				auto.subir();
				
				unidad.puntuarU(); 
				decena.puntuarD();
				centena.puntuarC();
			}
			
			ptovida.mover();
			other.mover();
			if(instructora.isSigue()){
				instructora.saltar();
			}
			colisiones();
		}
	}

	public void cicloPrincipal(){
       //variables para el calculo del tiempo para la pausa, este codigo es opcional, si lo desea puede definir un valor para la pausa en duro.
	   long lastLoopTime = System.nanoTime(); 
       final int TARGET_FPS = 60;
       final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
       
       while (jugando){
    	   //Calculo del tiempo optimo y uniforme para la pausa.
    	   long now = System.nanoTime();
    	   long updateLength = now - lastLoopTime;
    	   lastLoopTime = now;
    	   double delta = updateLength / ((double)OPTIMAL_TIME);
    	   lastFpsTime += updateLength;
    	   fps++;
    	   if (lastFpsTime >= 1000000000){
    		   //System.out.println("(FPS: "+fps+")");
    		   lastFpsTime = 0;
    		   fps = 0;
    	   }
    	   //Fin del calculo del tiempo optimo y uniforme para la pausa.
    	   
    	   //Metodos para pintar el lienzo y actualizar los componentes de juego
    	   pintar();    	   
    	   actualizar();
    	   
    	   //Aplicar la pausa.
    	   try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );} //Puede sustituir el valor de la pausa por un valor fijo
    	   catch(Exception e){};
       }
    }
		
	//Metodo que se ejecuta cuando el usuario presiona una tecla
	public void keyPressed(KeyEvent e){
		
        switch(e.getKeyCode()){
        	case KeyEvent.VK_SPACE:{
        		if(((auto.getY()>=440))){
            		//if(((auto.getY()>=480)))
            			auto.setPerSalta(true);
            		
            		
            		auto.setPerBaja(false);
            		auto.setPerSube(false);
            		//System.out.println(auto.getY());
            		//if(auto.getY()<=380)
            			//auto.setPerSalta(false);
            			//auto.setLlaveImagen("auto_furioso");
        		    }break;
        		}
        	case KeyEvent.VK_Z:{
        		if((auto.getY()>=440)){
        			auto.setTipoVelocidad(false);
        			fondo1.setVelocidad(5);
        			fondo2.setVelocidad(5);
        			edif1.setVelocidad(7);
        			edif2.setVelocidad(7);
        			edif3.setVelocidad(7);
        			nubes.setVelocidad(3);
        			sol.setVelocidad(3);
        			
        			calle1.setVelocidad(9);
            		calle2.setVelocidad(9);
            		calle3.setVelocidad(9);
            		bache.setVelocidad(9);
            		carro.setVelocidad(9);
            		cono.setVelocidad(9);
            		undi.setVelocidad(9);
            		moto.setVelocidad(9);
            		cuatri.setVelocidad(9);
            		ptovida.setVelocidad(9);
            		other.setVelocidad(9);
            		
        		}
        	}break;
        	
        	case KeyEvent.VK_X:{
        		if(((auto.getY()>=440))){
        			if(i5<=1){
        				clip1.play();
        			}
        			i5++;
        			auto.setPerAga(true);
        			auto.setPerSube(false);
        		}
        	}break;
        	
        	case KeyEvent.VK_P:{
        		if(pausa){
        			pausa = false;
        		}else{
        			pausa = true;
        		}
        	}
		}
    }

	//Metodo que se ejecuta cuando el usuario suelta una tecla
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
	        case KeyEvent.VK_SPACE:{
	        	auto.setPerSalta(false);
	        	auto.setPerBaja(true);
				//auto.setLlaveImagen("auto");
	        }break;
	        case KeyEvent.VK_Z:{
	        	auto.setTipoVelocidad(true);
	        	fondo1.setVelocidad(2);
    			fondo2.setVelocidad(2);
	        	calle1.setVelocidad(v);
        		calle2.setVelocidad(v);
        		calle3.setVelocidad(v);
        		/*System.out.println("Y = " + auto.getY() + " perSalta = " + auto.isPerSalta() +
        		" perBaja = " + auto.isPerBaja() + " perAga = " + auto.isPerAga()
        		+ " autoSube = " + auto.isPerSube() + " llave " + auto.getLlaveImagen());
        		*/
        		edif1.setVelocidad(2);
        		edif2.setVelocidad(2);
        		edif3.setVelocidad(2);
        		nubes.setVelocidad(1);
        		sol.setVelocidad(1);
        		
        		bache.setVelocidad(v);
        		carro.setVelocidad(v);
        		
        		cono.setVelocidad(v);
        		undi.setVelocidad(v);
        		moto.setVelocidad(v);
        		cuatri.setVelocidad(v);
        		ptovida.setVelocidad(v);
        		other.setVelocidad(2);
        		
	        }break;
	        case KeyEvent.VK_X:{
	        	i5=0;
	        	auto.setPerAga(false);
    			auto.setPerSube(true);

        	}break;
	        case KeyEvent.VK_P:{}
        }
    }
    //Sin uso, pero a fuerzas se debe redefinir
  	public void keyTyped(KeyEvent e) {
  	}
	
    public static void main(String[] args){
		new Juego(); //Crear un objeto del tipo Juego.
	}
}