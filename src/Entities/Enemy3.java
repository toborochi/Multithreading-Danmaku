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
public class Enemy3 extends ThreadEntity{
    
    public int life = 10;
    
    public Enemy3(String spriteName,int width,int height,double x,double y){
        super(spriteName, width, height, x, y);
    }
    
    int path = 0;
    int speed = 2;
    int t = 0;
    Bullet bala;
    
    @Override
    public void tick(){
        
        synchronized(this){
            if(life==0){
            destroy=true;
            }
        }
        
        
        if(path==0){
            setX(x+speed);
            if(getX()>opsis.Game.WIDTH-64){
                path=1;
            }
        }else
        if(path==1){
            setY(y+speed);
            if(getY()>opsis.Game.HEIGHT/2 + 32){
                path=2;
            }
        }else
        if(path==2){
            setX(x-speed);
            if(getX()<64){
                path=3;
            }
        }else
        if(path==3){
            setY(y-speed);
            if(getY()<64){
                path=0;
            }
        }
        
        if(t>30){
            
            synchronized(GameState.synBullet){
                bala = new Bullet("BalaEnemigo", 16, 16,getX(),getY());
                 GameState.synBullet.add(bala);
                 bala.yspeed=3;
                 bala.start();
            }
            t=0;
        }
        
        t++;
    
    }
}
