import greenfoot.*;

public class TrailPart extends Actor
{
    private boolean invincible;
    
    public TrailPart(int size, boolean invincible)
    {
        this.invincible = invincible;
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(invincible ? Color.CYAN : Color.YELLOW);
        img.fillOval(0, 0, size-1, size-1);
        setImage(img);
    }
    
    public void setTransparency(int transparency)
    {
        getImage().setTransparency(transparency);
    }
    
    public void setInvincible(boolean invincible)
    {
        this.invincible = invincible;
        GreenfootImage img = getImage();
        img.setColor(invincible ? Color.CYAN : Color.YELLOW);
        img.clear();
        img.fillOval(0, 0, img.getWidth()-1, img.getHeight()-1);
    }
}