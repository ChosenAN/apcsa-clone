import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int speed = 10; // adjust this value to change the enemy's speed
    private int direction = 0; // current direction
    private int steps = 0; // number of steps to move in the current direction
    private int maxSteps; // maximum number of steps to move in a direction

    /**
     * Constructor for objects of class Enemy.
     */
    public Enemy()
    {
        // Call the resizeImage method to resize the image
        resizeImage();

        // Set the maximum number of steps to a random value between 10 and 50
        maxSteps = Greenfoot.getRandomNumber(40) + 10;
    }

    /**
     * Resize the enemy's image
     */
    public void resizeImage()
    {
        GreenfootImage image = getImage();
        image.scale(20, 20);
        setImage(image);
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Move the enemy randomly
        moveRandomly();
    }

    /**
     * Move the enemy randomly
     */
    private void moveRandomly()
    {
        if (steps == 0) {
            // Choose a new direction
            direction = Greenfoot.getRandomNumber(8);
            steps = maxSteps;
        }

        switch (direction) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
            case 4:
                moveUpLeft();
                break;
            case 5:
                moveUpRight();
                break;
            case 6:
                moveDownLeft();
                break;
            case 7:
                moveDownRight();
                break;
        }

        steps--;
    }

    /**
     * Move the enemy up
     */
    private void moveUp()
    {
        setLocation(getX(), getY() - speed);
    }

    /**
     * Move the enemy down
     */
    private void moveDown()
    {
        setLocation(getX(), getY() + speed);
    }

    /**
     * Move the enemy left
     */
    private void moveLeft()
    {
        setLocation(getX() - speed, getY());
    }

    /**
     * Move the enemy right
     */
    private void moveRight()
    {
        setLocation(getX() + speed, getY());
    }

    /**
     * Move the enemy up and left
     */
    private void moveUpLeft()
    {
        setLocation(getX() - speed, getY() - speed);
    }

    /**
     * Move the enemy up and right
     */
    private void moveUpRight()
    {
        setLocation(getX() + speed, getY() - speed);
    }

    /**
     * Move the enemy down and left
     */
    private void moveDownLeft()
    {
        setLocation(getX() - speed, getY() + speed);
    }

    /**
     * Move the enemy down and right
     */
    private void moveDownRight()
    {
        setLocation(getX() + speed, getY() + speed);
    }
}