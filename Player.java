import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int speed = 5;
    
    public void resizeImage()
    {
        GreenfootImage image = getImage();
        image.scale(20, 20);
        setImage(image);
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check if the left arrow key is pressed
        if (Greenfoot.isKeyDown("left")) {
            // Move the player to the left
            move(-speed);
        }
        // Check if the right arrow key is pressed
        if (Greenfoot.isKeyDown("right")) {
            // Move the player to the right
            move(speed);
        }
        // Check if the up arrow key is pressed
        if (Greenfoot.isKeyDown("up")) {
            // Move the player up
            setLocation(getX(), getY() - speed);
        }
        // Check if the down arrow key is pressed
        if (Greenfoot.isKeyDown("down")) {
            // Move the player down
            setLocation(getX(), getY() + speed);
        }
    }
}
