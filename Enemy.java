import greenfoot.*;

public class Enemy extends Actor
{
    private int speed;
    private int size = 20;
    private int direction;
    private int moveCounter = 0;
    private int moveTime;

    public Enemy(int speed)
    {
        this.speed = speed;
        setImage(new GreenfootImage(size, size));
        GreenfootImage img = getImage();
        img.setColor(Color.RED);
        img.fillOval(0, 0, size-1, size-1);
        
        direction = Greenfoot.getRandomNumber(360);
        moveTime = Greenfoot.getRandomNumber(50) + 50;
    }

    public void act()
    {
        move();
        checkBounds();
    }

    private void move()
    {
        moveCounter++;
        if (moveCounter >= moveTime) {
            direction = Greenfoot.getRandomNumber(360);
            moveCounter = 0;
            moveTime = Greenfoot.getRandomNumber(50) + 50;
        }
        
        setRotation(direction);
        move(speed);
    }

    private void checkBounds()
    {
        if (getX() <= 0 || getX() >= getWorld().getWidth()-1) {
            direction = 180 - direction;
        }
        if (getY() <= 0 || getY() >= getWorld().getHeight()-1) {
            direction = 360 - direction;
        }
    }
}