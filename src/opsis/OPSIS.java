/**
 * Faculty of Engineering in Computer Science and Telecomunications
 * Author: Anez Vladimirovna Leonardo
 */


package opsis;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class OPSIS {
  
    public static void main(String[] args) {
        
        // Instancia del Juego
        final Game game = new Game();
        JFrame frame = new JFrame(Game.TITTLE);
        frame.add(game);
        frame.setSize(Game.WIDTH, Game.HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Bye!");
                game.stop();
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        //  Iniciamos la ventana
        game.run();
    }
    
}
