package States.FreeStates;


import Entities.Bullet;
import Entities.BulletPlayer;
import Entities.Enemy;
import Entities.Enemy2;
import Entities.Enemy3;
import Entities.GameObject;
import Entities.Player;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import States.State;
import States.StateManager;
import Utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*; 
import opsis.Game;


public class GameState implements State {

    
    Player player = new Player("NaveJugador", 64, 64,opsis.Game.WIDTH/2 ,opsis.Game.HEIGHT/2+128);
    //Enemy enemy = new Enemy("NaveEnemiga", 64, 64,opsis.Game.WIDTH/2+64 ,opsis.Game.HEIGHT/2 - 64);
    //Enemy enemy2 = new Enemy("NaveEnemiga", 64, 64,opsis.Game.WIDTH/2 ,opsis.Game.HEIGHT/2);
    
    
    

    public static Enemy2 enemy;
    public static Enemy3 enemy2;
    List<Enemy> enemyList = new ArrayList();
    public static List<Enemy> synlist;
    public static List<Bullet> synBullet = new ArrayList();
    public static List<BulletPlayer> synBulletP = new ArrayList();
    public static boolean debug = false;
    
   
    @Override
    public void init() {

        enemy = new Enemy2("NaveEnemiga2", 64, 64, opsis.Game.WIDTH/2, 64);
        
        /// ESTE ES EL ENEMIGO QUE PIDIO EN CLASE
        enemy2 = new Enemy3("NaveEnemiga3", 64, 64, 64, 64);
       
        int r = opsis.Game.WIDTH / 4;
        
       for(int i=0;i<4;++i){
           enemyList.add(new Enemy("NaveEnemiga", 64, 64,r*i ,(opsis.Game.HEIGHT/2 - 96) - (64*(i%2)) ));
           enemyList.get(i).id=i;
       }
       
       try{
       synlist = Collections.synchronizedList(enemyList);
       }catch(IllegalArgumentException e){
           System.out.println(e); 
       }
    }

    @Override
    public void enter() {
        enemy.start();
        enemy2.start();
        //enemy.start();
        //enemy2.start();
        for(int i=0;i<enemyList.size();++i){
            enemyList.get(i).start();
        }
    }

    @Override
    public void tick(StateManager stateManager) {
        player.tick();
        
        synchronized(player){
         
        if(player.life==0){
            stateManager.setState("gameover");
        }
           
        }
    }

    @Override
    public void render(Graphics2D g) {
        
        g.setColor(new Color(10, 20, 25) );
        g.fillRect(0, 0, opsis.Game.WIDTH, opsis.Game.HEIGHT);
        Fonts.drawString(g, new Font("AvantGarde",Font.BOLD,14), Color.YELLOW, "Life: "+player.life, 16, 32);
        
        player.render(g);
        
        
        synchronized(enemy){
            if(enemy.life>0){
                enemy.render(g);
            }
        }
        
        synchronized(enemy2){
            if(enemy2.life>0){
                enemy2.render(g);
            }
        }
        
        
         synchronized(synBulletP){
             
               List<BulletPlayer> found = new ArrayList();
               for (BulletPlayer bala: synBulletP) {
                 if(bala.destroy==true){
                     found.add(bala);
                 }else
                 {
                     bala.render(g);
                 }
                 
               }
               synBulletP.removeAll(found);
          }
        
          synchronized(synlist){
             
              List<Enemy> found = new ArrayList();
               for (Enemy enemigo: synlist) {
                   
                   if(enemigo.life==0){
                       found.add(enemigo);
                   }
                    enemigo.render(g);
                }
               synlist.removeAll(found);
          }
          
           synchronized(synBullet){
             
               List<Bullet> found = new ArrayList();
               for (Bullet bala: synBullet) {
                 if(bala.destroy==true){
                     found.add(bala);
                 }else
                 {
                     bala.render(g);
                 }
                 
               }
               synBullet.removeAll(found);
          }
           
          
          
        
    }
        
      
    

    @Override
    public void exit() {
    }

    @Override
    public String getName() {
        return "level1";
    }

}