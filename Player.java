import greenfoot.*;

public class Player extends Actor
{
    private int speed = 4;
    private int size = 30;
    private boolean isAlive = true;

    public Player()
    {
        setImage(new GreenfootImage(size, size));
        GreenfootImage img = getImage();
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, size-1, size-1);
    }

    public void act()
    {
        if (isAlive) {
            movePlayer();
            checkCollision();
        }
    }

    private void movePlayer()
    {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
        }

        // Keep player within bounds
        if (getX() < 0) setLocation(0, getY());
        if (getX() > getWorld().getWidth()-1) setLocation(getWorld().getWidth()-1, getY());
        if (getY() < 0) setLocation(getX(), 0);
        if (getY() > getWorld().getHeight()-1) setLocation(getX(), getWorld().getHeight()-1);
    }

    private void checkCollision()
    {
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            isAlive = false;
            ((MyWorld)getWorld()).gameOver();
        }
    }
}