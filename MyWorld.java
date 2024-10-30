import greenfoot.*;
import java.util.List;

public class MyWorld extends World
{
    private boolean enemiesActive = true;
    private int level = 1;
    private int score = 0;
    private int enemiesPerLevel = 5; // Starting number of enemies
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
        player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);

        scoreBoard = new ScoreBoard("Score: " + score);
        addObject(scoreBoard, 70, 20);

        levelDisplay = new LevelDisplay("Level: " + level);
        addObject(levelDisplay, 70, 50);

        spawnEnemies();
    }

    private void spawnEnemies()
    {
        removeObjects(getObjects(BaseEnemy.class));
        
        int enemiesToSpawn = enemiesPerLevel + (level - 1) * 2;
        
        for (int i = 0; i < enemiesToSpawn; i++) {
            int enemyType = Greenfoot.getRandomNumber(3);
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            BaseEnemy enemy;
            switch (enemyType) {
                case 0:
                    enemy = new RandomEnemy();
                    break;
                case 1:
                    enemy = new ChaseEnemy();
                    break;
                case 2:
                    enemy = new BounceEnemy();
                    break;
                default:
                    enemy = new RandomEnemy();
            }
            addObject(enemy, x, y);
        }
    }

    public void increaseScore(int points)
    {
        score += points;
        scoreBoard.update("Score: " + score);
    }

    public void act()
    {
        if (!gameOver) {
            checkLevelCompletion();
        }
        if (gameOver && Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }

    private void checkLevelCompletion()
    {
        if (getObjects(BaseEnemy.class).isEmpty()) {
            levelUp();
        }
    }

    private void levelUp()
    {
        level++;
        score += level * 100; // Bonus points for completing level
        scoreBoard.update("Score: " + score);
        levelDisplay.update("Level: " + level);
        spawnEnemies();
    }

    public void gameOver()
    {
        gameOver = true;
        showText("GAME OVER\nFinal Score: " + score + "\nPress 'R' to restart", getWidth()/2, getHeight()/2);
    }
}