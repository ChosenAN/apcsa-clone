import greenfoot.*;

public class LivesDisplay extends Actor {
    private String prefix;
    private int lives;
    private static final int WIDTH = 150;
    private static final int HEIGHT = 30;
    
    public LivesDisplay() {
        prefix = "Lives: ";
        lives = 3;
        updateImage();
    }
    
    private void updateImage() {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setTransparency(128);
        image.setColor(Color.BLACK);
        image.fill();
        
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 20));
        image.drawString(prefix + lives, 10, 20);
        
        int heartSize = 20;
        int startX = 80;
        int y = 5;
        
        for (int i = 0; i < lives; i++) {
            drawHeart(image, startX + (i * (heartSize + 5)), y, heartSize);
        }
        
        setImage(image);
    }
    
    private void drawHeart(GreenfootImage image, int x, int y, int size) {
        image.setColor(Color.RED);
        image.fillOval(x, y + size/4, size/2, size/2);
        image.fillOval(x + size/2, y + size/4, size/2, size/2);
        int[] xPoints = {x, x + size/2, x + size};
        int[] yPoints = {y + size/2, y + size, y + size/2};
        image.fillPolygon(xPoints, yPoints, 3);
    }
    
    public void update(int newLives) {
        if (this.lives != newLives) {
            this.lives = newLives;
            updateImage();
        }
    }
}