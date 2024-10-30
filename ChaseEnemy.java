import greenfoot.*;

public class ChaseEnemy extends BaseEnemy
{
    public ChaseEnemy()
    {
        super(4, Color.BLUE);
    }
    
    @Override
    public void act()
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (player != null) {
            // Calculate direction to player
            int dx = player.getX() - getX();
            int dy = player.getY() - getY();
            double angle = Math.atan2(dy, dx);
            
            // Move towards player
            int x = getX() + (int)(Math.cos(angle) * speed);
            int y = getY() + (int)(Math.sin(angle) * speed);
            
            setLocation(x, y);
        }
        
        stayInBounds();
    }
}