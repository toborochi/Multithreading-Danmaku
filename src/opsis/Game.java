
package opsis;

import Input.KeyInput;
import States.FreeStates.GameOver;
import States.FreeStates.GameState;
import States.FreeStates.MenuState;
import States.StateManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
    
    // Main data
    public static String TITTLE = "OPSIS 1.0";
    public static final int WIDTH = 720;
    public static final int HEIGHT = WIDTH / 4 * 3;
    
    public static boolean running;
    
    // Manager de Niveles
    private StateManager stateManager;
    
    public static Game INSTANCE;
    
    public Game(){

        addKeyListener(new KeyInput());

        stateManager = new StateManager();

        // Niveles
        stateManager.addState(new MenuState());
        stateManager.addState(new GameState());
        stateManager.addState(new GameOver());
        
        INSTANCE = this;
    }
    
    private void tick() {
        stateManager.tick();
    }
    
    private void render(){  
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////////////////////////
        
        stateManager.render(g2d);

        ////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    

    @Override
    public void run() {
        
        running = true;
        requestFocus();
        double target = 60.0;
        double nsPerTick = 1000000000.0 / target;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        // Delta
        double unprocessed = 0.0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        // Mientras 'running' ejecutamos el run a ~60fps
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (unprocessed >= 0.5) {
                tick();
                KeyInput.update();
                unprocessed=0;
                tps++;
                canRender = true;
            } else canRender = false;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                //System.out.printf("FPS:"+fps+" TPS:"+tps);
                fps = 0;
                tps = 0;
            }

        }

        System.exit(0);
    }
    

    public void stop() {
        if (!running) return;
        running = false;
    }
}
