/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import States.FreeStates.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Enemy extends ThreadEntity {
      
    
    public int life = 15;
    
    public Enemy(String spriteName,int width,int height,double x,double y){
        super(spriteName, width, height, x, y);
    }
    int speed = 2;
    boolean flipflop = false;
    
    int acum = 0;
    int t = 0;
    int angulo = 0;
    
    Bullet bala;
    @Override
    public void tick(){
        
       
        
        /*
        synchronized(GameState.synlist){
            
            List<Enemy> found = new ArrayList();
            
            for(Enemy enemigo : GameState.synlist){
                if(enemigo.life>0 && acum>60){
                   
                    acum=0;
                    if( enemigo.id!=id){
                      enemigo.life--;
                    if(enemigo.life==0){
                        enemigo.destroy=true;
                        found.add(enemigo);
                    }
                       
                    }
                }
            }
            if(found.size()>0){
                GameState.synlist.removeAll(found);
            }
            
        }
    
        
        
        acum++;*/
        
        
        
         synchronized(this){
            if(life==0){
            destroy=true;
            }
        }
       
        if(t>60){
            bala = new Bullet("BalaEnemigo", 16, 16,getX(),getY());
            synchronized(GameState.synBullet){
                 GameState.synBullet.add(bala);
                 bala.yspeed=3;
                  bala.start();
            }
           
           
            t=0;
        }
        
        t++;
        
        
        
        
        if(flipflop){
            setX(x+speed);
        }else
        {
            setX(x-speed);
        }
        
        setY(y + Math.sin(Math.toRadians(angulo)));
        
        
        if(getX()<=0){setX(0);flipflop=!flipflop;}
        if(getX()>=opsis.Game.WIDTH){setX(opsis.Game.WIDTH);flipflop=!flipflop;}
        if(getY()<=0)setY(0);
        if(getY()>=opsis.Game.HEIGHT)setY(opsis.Game.HEIGHT); 
    
        angulo++;
        angulo%=360;
    
    }
    
}
