import greenfoot.*;

public class LevelDisplay extends Actor
{
    public LevelDisplay(String text)
    {
        updateImage(text);
    }
    
    public void update(String text)
    {
        updateImage(text);
    }
    
    private void updateImage(String text)
    {
        setImage(new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 0)));
    }
}