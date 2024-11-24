import greenfoot.*;
import java.util.List;

public class RandomEnemy extends BaseEnemy {
    private int moveCounter = 0;
    private int direction;
    private int moveTime;

    public RandomEnemy(int speed) {
        super(speed);
        setImage(new GreenfootImage("redalien.png"));
        direction = Greenfoot.getRandomNumber(360);
        moveTime = Greenfoot.getRandomNumber(50) + 50;
    }

    @Override
    public void act() {
        moveCounter++;
        if (moveCounter >= moveTime) {
            direction = Greenfoot.getRandomNumber(360);
            moveCounter = 0;
            moveTime = Greenfoot.getRandomNumber(50) + 50;
        }
        setRotation(direction);
        move(speed);
        if (atWorldEdge()) {
            direction += 180;
        }
        checkCollision();
    }

    @Override
    protected void reverseDirection() {
        direction = Greenfoot.getRandomNumber(360);
    }
}