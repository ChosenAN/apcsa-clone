import greenfoot.*;

public abstract class BaseEnemy extends Actor
{
    protected int speed;
    protected int size = 20;
    protected Color color;
    
    public BaseEnemy(int speed)
    {
        this.speed = speed;
    }
    
    public void destroy()
    {
        MyWorld world = (MyWorld) getWorld();
        world.increaseScore(10);
        world.removeObject(this);
    }
    
    protected boolean atWorldEdge()
    {
        if (getWorld() == null) return false;
        return (getX() <= 0 || getX() >= getWorld().getWidth()-1 ||
                getY() <= 0 || getY() >= getWorld().getHeight()-1);
    }
    
    protected void stayInBounds()
    {
        if (getWorld() == null) return;
        
        if (getX() <= 0) {
            setLocation(1, getY());
        }
        if (getX() >= getWorld().getWidth()-1) {
            setLocation(getWorld().getWidth()-2, getY());
        }
        if (getY() <= 0) {
            setLocation(getX(), 1);
        }
        if (getY() >= getWorld().getHeight()-1) {
            setLocation(getX(), getWorld().getHeight()-2);
        }
    }
    
    public abstract void act();
}