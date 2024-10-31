import greenfoot.*;
import java.util.LinkedList;

public class Player extends Actor
{
    private double vx = 0, vy = 0;
    private double drag = 0.95;
    private double acceleration = 0.5;
    private double maxSpeed = 5;
    private int size = 30;
    private boolean alive = true;
    private boolean isVisible = true;
    private GreenfootImage playerImage;
    private GreenfootImage invisibleImage;
    
    // Trail variables
    private LinkedList<Actor> trail;
    private int trailLength = 10;
    private int trailDelay = 5;
    private int trailCounter = 0;

    public Player()
    {
        playerImage = new GreenfootImage(size, size);
        playerImage.setColor(Color.YELLOW);
        playerImage.fillOval(0, 0, size-1, size-1);

        invisibleImage = new GreenfootImage(size, size);
        invisibleImage.setTransparency(0);

        setImage(playerImage);
        
        // Initialize trail
        trail = new LinkedList<>();
    }

    public void act()
    {
        if (alive) {
            movePlayer();
            updateTrail();
            checkCollision();
        }
    }

    private void movePlayer()
    {
        // Apply acceleration based on key presses
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) vx -= acceleration;
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) vx += acceleration;
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) vy -= acceleration;
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) vy += acceleration;

        // Apply drag
        vx *= drag;
        vy *= drag;

        // Limit speed
        double speed = Math.sqrt(vx*vx + vy*vy);
        if (speed > maxSpeed) {
            vx = (vx / speed) * maxSpeed;
            vy = (vy / speed) * maxSpeed;
        }

        // Update position
        setLocation(getX() + (int)vx, getY() + (int)vy);

        // Keep player within bounds
        if (getX() < 0) { setLocation(0, getY()); vx = 0; }
        if (getX() > getWorld().getWidth()-1) { setLocation(getWorld().getWidth()-1, getY()); vx = 0; }
        if (getY() < 0) { setLocation(getX(), 0); vy = 0; }
        if (getY() > getWorld().getHeight()-1) { setLocation(getX(), getWorld().getHeight()-1); vy = 0; }
    }

    private void updateTrail()
    {
        trailCounter++;
        if (trailCounter >= trailDelay) {
            trailCounter = 0;

            // Add new trail segment
            TrailSegment segment = new TrailSegment(size / 2);
            getWorld().addObject(segment, getX(), getY());
            trail.addFirst(segment);

            // Remove old trail segments if trail is too long
            while (trail.size() > trailLength) {
                Actor oldSegment = trail.removeLast();
                getWorld().removeObject(oldSegment);
            }
        }

        // Update transparency of trail segments
        int transparency = 255;
        int fadeStep = 255 / trailLength;
        for (Actor segment : trail) {
            segment.getImage().setTransparency(transparency);
            transparency -= fadeStep;
        }
    }

    private void checkCollision()
    {
        if (!((MyWorld)getWorld()).isProtected()) {
            Actor enemy = getOneIntersectingObject(BaseEnemy.class);
            if (enemy != null) {
                alive = false;
                ((MyWorld)getWorld()).gameOver();
            }
        }
    }
    
    public void toggleVisibility()
    {
        isVisible = !isVisible;
        setImage(isVisible ? playerImage : invisibleImage);
    }
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;
        setImage(isVisible ? playerImage : invisibleImage);
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    
    public void reset()
    {
        alive = true;
        isVisible = true;
        setImage(playerImage);
        vx = 0;
        vy = 0;
        clearTrail();
    }
    
    private void clearTrail()
    {
        for (Actor segment : trail) {
            getWorld().removeObject(segment);
        }
        trail.clear();
    }
    
    public void updateForLevel(int level)
    {
        // Adjust player properties based on level
        maxSpeed = 5 + (level * 0.5);
        trailLength = 10 + (level * 2);
    }
    
    // Inner class for trail segments
    private class TrailSegment extends Actor
    {
        public TrailSegment(int size)
        {
            GreenfootImage img = new GreenfootImage(size, size);
            img.setColor(Color.YELLOW);
            img.fillOval(0, 0, size-1, size-1);
            setImage(img);
        }
    }
}