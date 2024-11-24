import greenfoot.*;
import java.util.List;

public class BounceEnemy extends BaseEnemy {
    private int xSpeed;
    private int ySpeed;

    public BounceEnemy(int speed) {
        super(speed);
        setImage(new GreenfootImage("greenalien.png"));
        double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
        xSpeed = (int)(Math.cos(angle) * speed);
        ySpeed = (int)(Math.sin(angle) * speed);
    }

    @Override
    public void act() {
        move();
        checkCollision();
    }

    private void move() {
        setLocation(getX() + xSpeed, getY() + ySpeed);
        if (atWorldEdge()) {
            bounceOffWall();
        }
    }

    private void bounceOffWall() {
        // Reflecting off wall
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            xSpeed = -xSpeed; // Reverse x direction
            // Adjust position to prevent sticking to the wall
            if (getX() <= 0) {
                setLocation(1, getY());
            } else {
                setLocation(getWorld().getWidth() - 2, getY());
            }
        }
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            ySpeed = -ySpeed; // Reverse y direction
            // Same thing here
            if (getY() <= 0) {
                setLocation(getX(), 1);
            } else {
                setLocation(getX(), getWorld().getHeight() - 2);
            }
        }
    }

    @Override
    protected void reverseDirection() {
        //left empty on purpose
    }
}