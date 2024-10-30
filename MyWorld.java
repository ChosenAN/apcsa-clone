import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        player.resizeImage(); // resize the player's image
        addObject(player, 400, 300);

        for (int i = 0; i < 20; i++) {
            Enemy enemy = new Enemy();
            enemy.resizeImage();
            addObject(enemy, Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600));
        }
    }
}
