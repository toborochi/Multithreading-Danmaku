
package States.FreeStates;

import opsis.Game;
import Input.KeyInput;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import States.State;
import States.StateManager;
import Utils.Fonts;

public class MenuState implements State {
    private final String[]  options = {"Start","Instructions","Exit"};
    public int              current = 0;
    
    
    private Texture texture;
    private SpriteSheet sheet;
    private Sprite sprite;
    
    @Override
    public void init(){
           sheet = new SpriteSheet(new Texture("Menu"),opsis.Game.WIDTH,opsis.Game.HEIGHT);
        sprite = new Sprite(sheet,1,1);
    }
    
    @Override
    public void enter(){
        
    }
    
    @Override
    public void tick(StateManager manager){
        if(KeyInput.wasPressed(KeyEvent.VK_DOWN) && current+1<options.length){
            current++;
        }
        
        if(KeyInput.wasPressed(KeyEvent.VK_UP) && current-1>=0){
            current--;
        }
        
        if(KeyInput.wasPressed(KeyEvent.VK_ENTER)){
            select(manager);
        }
    }
    
    boolean show = false;
    
    public void select(StateManager manager){
        switch(current){
            case 0: 
                manager.setState("level1");
                break;
            case 1: 
                break;
            case 2: 
                opsis.Game.INSTANCE.stop();
                break;
        }
    }
    
    @Override
    public void  exit(){
        
    }
    
    @Override
    public String getName(){
        return "menu";
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        sprite.render(g,(double)0,(double)0,0);
        
        Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,72), Color.red, Game.TITTLE,180, 200);
        Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,20), Color.red, "Revenge of Operating Systems",210, 220);
            
        if(current==1){
            Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,16), Color.YELLOW, "Move with: WASD", 292, 400 );
            Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,16), Color.YELLOW, "Shoot with: SPACE", 292, 420 );
        }
        
        for(int i=0;i<options.length;++i){
            if(i==current){
                
                Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,28), Color.YELLOW, options[i], 190, 260+i*22 );
            }else{
                Fonts.drawString(g, new Font("AvantGarde",Font.PLAIN,24), Color.WHITE, options[i], 180,260+i*22);
            }
                
        }
    }

}