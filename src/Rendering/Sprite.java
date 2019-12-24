
package Rendering;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Sprite {
    
    private BufferedImage image;
    
    public Sprite(SpriteSheet spriteSheet,int x,int y){
        this.image = spriteSheet.getTexture().getImage().getSubimage( (x*spriteSheet.getWidth())-spriteSheet.getWidth() ,
                (y*spriteSheet.getHeight())-spriteSheet.getHeight(), 
                spriteSheet.getWidth(), 
                spriteSheet.getHeight());
    }
    
    public void render(Graphics2D g,double x,double y,double angle){
        AffineTransform at = new AffineTransform();
        at.rotate(angle); // NOPE! Esta mal hecho la transformacion. Basicamente no rotar.
        at.translate(x, y);
        g.drawImage(image, at, null);
        
    }
}