package juego;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class TestPaneles 
{ 
   public static void main(String[] args) 
   { 
      SwingUtilities.invokeLater(new Runnable() { 

         @Override 
         public void run() { 
            JFrame aplicacion = new MiAplicacionFrame(); 
            aplicacion.setVisible(true); 
            aplicacion.setLocationRelativeTo(null); 
         } 
      }); 
   } 
} 

class MiAplicacionFrame extends JFrame 
{ 
   public static final int DEFAULT_WIDTH = 564; 
   public static final int DEFAULT_HEIGHT = 564; 
    
   private Container contentPane; 
   private JMenuItem itemNuevo; 
   private JMenuItem itemConfig; 
   private JMenuItem itemSalir; 
   private JMenuItem itemAbout; 
    
   private JPanel panelJuego; 
   private JFrame frame;
    
   public MiAplicacionFrame() 
   { 
      super("Test de paneles"); 
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); 
      setDefaultCloseOperation(3); 
      
      contentPane = getContentPane(); 
      
      initComponents(); 
      addListeners(); 
   } 
    
   private void initComponents() 
   { 
      JMenuBar barraMenu = new JMenuBar(); 
      setJMenuBar(barraMenu); 
      
      JMenu menuJuego = new JMenu("Juego"); 
      barraMenu.add(menuJuego); 
      
      itemNuevo = new JMenuItem("Nuevo juego", 'n'); 
      menuJuego.add(itemNuevo); 
      
      itemConfig = new JMenuItem("Configurar juego", 'c'); 
      menuJuego.add(itemConfig); 
      
      menuJuego.addSeparator();
      itemSalir = new JMenuItem("Salir", 's'); 
      menuJuego.add(itemSalir); 
      
      JMenu menuAyuda = new JMenu("Acerca de"); 
      barraMenu.add(menuAyuda); 
      itemAbout = new JMenuItem("About", 'a'); 
      menuAyuda.add(itemAbout); 
      
      panelJuego = new PanelJuego(); 
      panelJuego.setVisible(false); 
      contentPane.add(panelJuego); 
   } 
    
   private void addListeners() 
   { 
      ActionListener listener = new ActionListener() { 

         @Override 
         public void actionPerformed(ActionEvent evt) 
         { 
            Object obj = evt.getSource(); 
            if (obj == itemNuevo) 
               itemNuevoActionPerformed(evt); 
            else if (obj == itemConfig) 
               itemConfigActionPerformed(evt); 
            else if (obj == itemSalir) 
               itemSalirActionPerformed(evt); 
            else if (obj == itemAbout) 
               itemAboutActionPerformed(evt); 
         } 
      }; 
      itemNuevo.addActionListener(listener); 
      itemConfig.addActionListener(listener); 
      itemSalir.addActionListener(listener); 
      itemAbout.addActionListener(listener); 
   } 
    
   private void itemNuevoActionPerformed(ActionEvent evt) 
   { 
      panelJuego.setVisible(true);
   } 
    
   private void itemConfigActionPerformed(ActionEvent evt) 
   { 
      JOptionPane.showMessageDialog(this, "Esto falta implementar", "Configuraciones", JOptionPane.ERROR_MESSAGE); 
   } 
    
   private void itemSalirActionPerformed(ActionEvent evt) 
   { 
      System.exit(0); 
   } 
    
   private void itemAboutActionPerformed(ActionEvent evt) 
   { 
      JOptionPane.showMessageDialog(this, "Este juego está en desarrollo :D"); 
   } 
} 

class PanelJuego extends JPanel { 
   private JButton btnComenzar; 
    
   public PanelJuego() 
   {
	  btnComenzar = new JButton("Ingresar del juego");
	  
	  btnComenzar.setBounds(50, 50, 50, 50);
	  btnComenzar.addActionListener(new ActionListener() { 

         @Override 
         public void actionPerformed(ActionEvent e) {
        	
        	Juego j = new Juego();
        	j.setVisible(true);
         } 
      }); 
      JPanel panelSur = new JPanel(); 
      panelSur.add(btnComenzar);
      add(panelSur, BorderLayout.WEST); 
   } 
    
   @Override 
   public void paintComponent(Graphics g) 
   { 
      super.paintComponent(g); 
      g.setFont(new Font("", Font.BOLD, 18)); 
      g.setColor(Color.ORANGE); 
     
    try {
		g.drawImage(ImageIO.read(getClass().getResource("/recursos/oso.jpg")), 0, 0, new Canvas());
	} catch (IOException e) {
		e.printStackTrace();
	}
   }

}