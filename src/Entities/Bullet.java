
package Entities;

import States.FreeStates.GameState;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends ThreadEntity {
    
    double yspeed = 0,xspeed = 0;
    
    public Bullet(String spriteName,int width,int height,double x,double y){
        super(spriteName, width, height, x, y);
    }
    
    @Override
    public void tick(){
        
        setY(y+yspeed);
        setX(x+xspeed);
        
        if(getY()>opsis.Game.HEIGHT+32){
            destroy=true;
        }
        
        if(getX()>opsis.Game.WIDTH+32){
            destroy=true;
        }
        
        if(getY()<-32){
            destroy=true;
        }
        
        if(getX()<-32){
            destroy=true;
        }
        
    }
}
