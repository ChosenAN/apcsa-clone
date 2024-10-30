import greenfoot.*;

public class RandomEnemy extends BaseEnemy
{
    private int moveCounter = 0;
    private int moveTime;
    private int direction;
    
    public RandomEnemy()
    {
        super(2, Color.RED);
        direction = Greenfoot.getRandomNumber(360);
        moveTime = Greenfoot.getRandomNumber(50) + 50;
    }
    
    @Override
    public void act()
    {
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
            if (direction >= 360) {
                direction -= 360;
            }
        }
    }
}