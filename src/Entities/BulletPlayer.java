/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import States.FreeStates.GameState;

/**
 *
 * @author Asus
 */
public class BulletPlayer extends ThreadEntity {
    
    int yspeed = 0,xspeed=0;
    
    public BulletPlayer(String spriteName,int width,int height,double x,double y){
        super(spriteName, width, height, x, y);
    }
    
    @Override
    public void tick(){
        
        
        synchronized(GameState.synlist){
            for(Enemy enemigo:GameState.synlist){
                double d = Math.sqrt( (getX()-enemigo.getX())*(getX()-enemigo.getX()) + (getY()-enemigo.getY())*(getY()-enemigo.getY()) );
                    if(d<32){
                        enemigo.life--;
                        destroy=true;
                    }
            }
        }
        
        synchronized(GameState.enemy){
            double d = Math.sqrt( (getX()-GameState.enemy.getX())*(getX()-GameState.enemy.getX()) + (getY()-GameState.enemy.getY())*(getY()-GameState.enemy.getY()) );
                    if(d<32){
                        GameState.enemy.life--;
                        destroy=true;
                    }
        }
        
        synchronized(GameState.enemy2){
            double d = Math.sqrt( (getX()-GameState.enemy2.getX())*(getX()-GameState.enemy2.getX()) + (getY()-GameState.enemy2.getY())*(getY()-GameState.enemy2.getY()) );
                    if(d<32){
                        GameState.enemy2.life--;
                        destroy=true;
                    }
        }
        
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
