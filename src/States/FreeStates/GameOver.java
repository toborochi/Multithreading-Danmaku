package States.FreeStates;

import Entities.GameObject;
import Entities.Player;
import States.State;
import States.StateManager;
import Utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*; 
import opsis.Game;

public class GameOver implements State{
    
    @Override
    public void init() {
    }

    @Override
    public void enter() {
 
    }

    @Override
    public void tick(StateManager stateManager) {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,72), Color.red, "GAME OVER",opsis.Game.WIDTH/2-250, 275);
        Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,16), Color.yellow, "Close the Window to play Again",opsis.Game.WIDTH/2-150, 290);
    }
     @Override
    public void exit() {
    }

    @Override
    public String getName() {
        return "gameover";
    }
        
}
