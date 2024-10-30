import greenfoot.*;

public class PowerUp extends Actor {
    private int rotationSpeed = 2;

    public PowerUp() {
        setImage(new GreenfootImage(20, 20));
        GreenfootImage img = getImage();
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, 19, 19);
        img.setColor(Color.ORANGE);
        img.fillOval(4, 4, 11, 11);
    }
    
    public void act() {
        turn(rotationSpeed);
        
        // Remove if touching the edge of the world
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}