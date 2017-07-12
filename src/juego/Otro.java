package juego;

 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
 import javax.swing.JMenu;
 import javax.swing.JMenuBar;
 import javax.swing.JMenuItem;
 
 
 public class Otro extends JFrame {
    
    public Otro(){
       
    }
    public void criaJanela(){
       this.setTitle("JMenuBar");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(1024,768);
       this.setVisible(true);
       
       JMenuBar barramenu = new JMenuBar();
       JMenu mnucadastro = new JMenu("Menu");
       JMenuItem mnufuncionario = new JMenuItem("Puntaje");
       JMenuItem mnuproduto = new JMenuItem("Reiniciar Juego");
       JMenuItem mnusair = new JMenuItem("Salir");
       
       mnucadastro.add(mnufuncionario);
       mnucadastro.add(mnuproduto);
       mnucadastro.addSeparator();
       mnucadastro.add(mnusair);
       
       barramenu.add(mnucadastro);
       
       setJMenuBar(barramenu);
       mnusair.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}
	});
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       // TODO Auto-generated method stub
       Otro main = new Otro();
       main.criaJanela();
    }
 
 }