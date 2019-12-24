package Entities;

import Input.KeyInput;
import Rendering.Texture;
import States.FreeStates.GameState;
import java.awt.event.KeyEvent;
import States.StateManager;


public class Player extends Entity {
    
    public double speed = 6;
    public int t = 0;
    public int life = 10;
    
    public Player(String spriteName,int width,int height,double x,double y){
       
        super(spriteName, width, height, x, y);
        
    }
    
    @Override 
    public void tick(){
        
        
         if(KeyInput.isDown(KeyEvent.VK_SPACE) && t>15){
            
            BulletPlayer bala = new BulletPlayer("BalaJugador", 16, 16,getX(),getY());
            
            synchronized(GameState.synBulletP){
                  bala.yspeed=-5;
                  GameState.synBulletP.add(bala);
            }
            bala.start();
            t=0;
         }
         
         synchronized(GameState.enemy){
                double d = Math.sqrt( (getX()-GameState.enemy.getX())*(getX()-GameState.enemy.getX()) + (getY()-GameState.enemy.getY())*(getY()-GameState.enemy.getY()) );
                if(d<32){
                    life=0;
                }
            }
         
         synchronized(GameState.enemy2){
                double d = Math.sqrt( (getX()-GameState.enemy2.getX())*(getX()-GameState.enemy2.getX()) + (getY()-GameState.enemy2.getY())*(getY()-GameState.enemy2.getY()) );
                if(d<32){
                    life=0;
                }
            }
         
          synchronized(GameState.synBullet){
               
                for(Bullet bala: GameState.synBullet){
                    double d = Math.sqrt( (getX()-bala.getX())*(getX()-bala.getX()) + (getY()-bala.getY())*(getY()-bala.getY()) );
                    if(d<32){
                        bala.destroy=true;
                        life--;
                        if(life<=0)life=0;
                        //System.out.println(life);
                    }
                    
                }
            }
          
          synchronized(GameState.synlist){
               
                for(Enemy enemigo: GameState.synlist){
                    double d = Math.sqrt((getX()-enemigo.getX())*(getX()-enemigo.getX()) + (getY()-enemigo.getY())*(getY()-enemigo.getY()) );
                    if(d<32){
                        life=0;
                        
                        //System.out.println(life);
                        
                    }
                    
                }
            }
         
        
         t++;
         
         
        
        if(KeyInput.isDown(KeyEvent.VK_D) || KeyInput.isDown(KeyEvent.VK_RIGHT)){
            setX(x+speed);
        }
        if(KeyInput.isDown(KeyEvent.VK_A) || KeyInput.isDown(KeyEvent.VK_LEFT)){
            setX(x-speed);
        }
        // Descomentar esto para moverse de Arriba a Abajo
        /*
        if(KeyInput.isDown(KeyEvent.VK_W) || KeyInput.isDown(KeyEvent.VK_UP)){
            setY(y-speed);
        }
        if(KeyInput.isDown(KeyEvent.VK_S) || KeyInput.isDown(KeyEvent.VK_DOWN)){
            setY(y+speed);
        }
        */
        
       
        
        //System.out.println(getX()+" "+getY());
        
        if(getX()<=0)setX(0);
        if(getX()>=opsis.Game.WIDTH)setX(opsis.Game.WIDTH);
        if(getY()<=0)setY(0);
        if(getY()>=opsis.Game.HEIGHT)setY(opsis.Game.HEIGHT);   
    }
}
