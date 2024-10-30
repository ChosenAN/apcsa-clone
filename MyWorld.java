import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class MyWorld extends World
{
    private boolean enemiesActive = true;
    private List<Enemy> enemies;
    private int level = 1;
    private int score = 0;
    private int enemyCount = 5; // Starting number of enemies
    private int enemySpeed = 2; // Starting speed of enemies
    private ScoreBoard scoreBoard;
    private LevelDisplay levelDisplay;
    private Player player;
    private boolean gameOver = false;

    public MyWorld()
    {    
        super(800, 600, 1); 
        prepare();
    }

    private void prepare()
    {
        // Create and add player
        player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);

        // Create and add score display
        scoreBoard = new ScoreBoard("Score: " + score);
        addObject(scoreBoard, 70, 20);

        // Create and add level display
        levelDisplay = new LevelDisplay("Level: " + level);
        addObject(levelDisplay, 70, 50);

        spawnEnemies();
    }

    private void spawnEnemies()
    {
        enemies = new ArrayList<>();
        for (int i = 0; i < enemyCount; i++) {
            addNewEnemy();
        }
    }

    private void addNewEnemy()
    {
        Enemy enemy = new Enemy(enemySpeed);
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(enemy, x, y);
        enemies.add(enemy);
    }

    public void increaseScore(int points)
    {
        score += points;
        scoreBoard.update("Score: " + score);
    }

    public void act()
    {
        if (!gameOver) {
            if (getObjects(Enemy.class).isEmpty()) {
                levelUp();
            }
        }
    }

    private void levelUp()
    {
        level++;
        enemyCount += 2;
        enemySpeed++;
        levelDisplay.update("Level: " + level);
        spawnEnemies();
    }

    public void gameOver()
    {
        gameOver = true;
        showText("GAME OVER\nFinal Score: " + score + "\nPress 'R' to restart", getWidth()/2, getHeight()/2);
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}