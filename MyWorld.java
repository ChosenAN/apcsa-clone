import greenfoot.*;
import java.util.List;

public class MyWorld extends World {
    private boolean enemiesActive = true;
    private int level = 1;
    private int score = 0;
    private ScoreBoard scoreBoard;
    private LevelDisplay levelDisplay;
    private Player player;
    private boolean gameOver = false;
    private int levelTimer = 0;
    private static final int LEVEL_DURATION = 900; // 20 seconds at 60 fps
    private static final int MAX_LEVEL = 10; // Update to 10 levels
    private int protectedTime = 180; // 3 seconds of protected time at 60 fps

    public MyWorld() {    
        super(1600, 800, 1);
        // Set black background
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        prepare();
    }

    private void prepare() {
        player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);

        scoreBoard = new ScoreBoard("Score: " + score);
        addObject(scoreBoard, 70, 20);

        levelDisplay = new LevelDisplay("Level: " + level);
        addObject(levelDisplay, 70, 50);

        spawnEnemies();
    }

    private void spawnEnemies() {
        removeObjects(getObjects(BaseEnemy.class));
        
        int bounceEnemies = 0;
        int randomEnemies = 0;
        int chaseEnemies = 0;
        
        switch(level) {
            case 1:
                bounceEnemies = 5;
                break;
            case 2:
                bounceEnemies = 3;
                randomEnemies = 3;
                break;
            case 3:
                bounceEnemies = 2;
                randomEnemies = 3;
                chaseEnemies = 2;
                break;
            case 4:
                bounceEnemies = 1;
                randomEnemies = 4;
                chaseEnemies = 3;
                break;
            case 5:
                randomEnemies = 3;
                chaseEnemies = 5;
                break;
            case 6:
                bounceEnemies = 4;
                randomEnemies = 5;
                chaseEnemies = 3;
                break;
            case 7:
                bounceEnemies = 3;
                randomEnemies = 4;
                chaseEnemies = 5;
                break;
            case 8:
                bounceEnemies = 2;
                randomEnemies = 6;
                chaseEnemies = 6;
                break;
            case 9:
                bounceEnemies = 1;
                randomEnemies = 7;
                chaseEnemies = 7;
                break;
            case 10:
                bounceEnemies = 0;
                randomEnemies = 8;
                chaseEnemies = 8;
                break;
            case 50:
                bounceEnemies = 10;
                randomEnemies = 10;
                chaseEnemies = 15;
                break;
            case 100:
                bounceEnemies = 25;
                randomEnemies = 10;
                chaseEnemies = 5;
                break;
            case 1000:
                chaseEnemies = 7;
                break;
        }
        
        spawnEnemyType(bounceEnemies, "BounceEnemy");
        spawnEnemyType(randomEnemies, "RandomEnemy");
        spawnEnemyType(chaseEnemies, "ChaseEnemy");
    }
    private void spawnEnemyType(int count, String enemyType)
    {
        for (int i = 0; i < count; i++) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            BaseEnemy enemy;
            switch(enemyType) {
                case "BounceEnemy":
                    enemy = new BounceEnemy(2 + level);
                    break;
                case "RandomEnemy":
                    enemy = new RandomEnemy(1 + level);
                    break;
                case "ChaseEnemy":
                    enemy = new ChaseEnemy(2 + level);
                    break;
                default:
                    enemy = new RandomEnemy(1 + level);
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
            levelTimer++;
            if (protectedTime > 0) {
                protectedTime--;
                if (protectedTime % 10 == 0) {  // Flash the player every 10 acts
                    player.toggleVisibility();
                }
                if (protectedTime == 0) {
                    player.setVisible(true);  // Ensure player is visible when protection ends
                }
            }
            if (levelTimer >= LEVEL_DURATION) {
                nextLevel();
            }
        }
        if (gameOver && Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }

    private void nextLevel()
    {
        level++;
        if (level > MAX_LEVEL) {
            gameWon();
        } else {
            resetLevel();
        }
    }

    private void resetLevel()
    {
        levelTimer = 0;
        protectedTime = 180;  // Reset protected time
        
        showText("Level " + level, getWidth()/2, getHeight()/2);
        Greenfoot.delay(180);
        showText("", getWidth()/2, getHeight()/2);
        
        levelDisplay.update("Level: " + level);
        
        player.setLocation(getWidth()/2, getHeight()/2);
        player.setVisible(true);  // Ensure player is visible at start of level
        
        removeObjects(getObjects(BaseEnemy.class));
        spawnEnemies();
        
        score += (level - 1) * 100;
        scoreBoard.update("Score: " + score);
    }

    public void gameOver()
    {
        gameOver = true;
        removeObjects(getObjects(BaseEnemy.class));
        showText("GAME OVER\nFinal Score: " + score + "\nPress 'R' to restart", getWidth()/2, getHeight()/2);
    }

    private void gameWon()
    {
        gameOver = true;
        removeObjects(getObjects(BaseEnemy.class));
        showText("Congratulations! You've completed all levels!\nFinal Score: " + score + "\nPress 'R' to play again", getWidth()/2, getHeight()/2);
    }

    public boolean isProtected()
    {
        return protectedTime > 0;
    }
}