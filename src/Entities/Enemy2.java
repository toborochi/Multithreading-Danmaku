
package Entities;

import States.FreeStates.GameState;

public class Enemy2 extends ThreadEntity {
    public int life = 20;
    
    public Enemy2(String spriteName,int width,int height,double x,double y){
        super(spriteName, width, height, x, y);
    }
    
    boolean flipflop = true;
    int speed = 1;
    Bullet bala;
    
    @Override
    public void tick(){
        
        
         synchronized(this){
            if(life==0){
            destroy=true;
            }
        }
        
       if(flipflop){
           setY(y+speed);
           if(getY()>256){
               if(flipflop){
                   double r = 36;
                   for(int i=0;i<10;++i){
                       bala = new Bullet("BalaEnemigo", 16, 16,getX(),getY());
                       synchronized(GameState.synBullet){
                        GameState.synBullet.add(bala);
                        bala.xspeed=Math.cos(  Math.toRadians(r*i) )*2;
                        bala.yspeed=Math.sin( Math.toRadians(r*i) )*2;
                        bala.start();
                     }
                   }
                   flipflop=!flipflop;
               }
           }
       }else{
           setY(y-speed);
           if(getY()<32){
               flipflop=!flipflop;
           }
       }
        
    
    }
}
