import greenfoot.*;
import java.util.List;

public abstract class BaseEnemy extends Actor {
    protected int speed;

    public BaseEnemy(int speed) {
        this.speed = speed;
    }

    public void destroy() {
        MyWorld world = (MyWorld) getWorld();
        world.increaseScore(10);
        world.removeObject(this);
    }

    protected boolean atWorldEdge() {
        return (getX() <= 0 || getX() >= getWorld().getWidth() - 1 ||
                getY() <= 0 || getY() >= getWorld().getHeight() - 1);
    }

    protected void stayInBounds() {
        if (getWorld() == null) return;

        if (getX() <= 0) {
            setLocation(1, getY());
        }
        if (getX() >= getWorld().getWidth() - 1) {
            setLocation(getWorld().getWidth() - 2, getY());
        }
        if (getY() <= 0) {
            setLocation(getX(), 1);
        }
        if (getY() >= getWorld().getHeight() - 1) {
            setLocation(getX(), getWorld().getHeight() - 2);
        }
    }

    protected void checkCollision() {
        List<BaseEnemy> enemies = getWorld().getObjects(BaseEnemy.class);
        for (BaseEnemy enemy : enemies) {
            if (enemy != this && isTouching(enemy.getClass())) {
                reverseDirection();
            }
        }
    }

    protected abstract void reverseDirection();

    public abstract void act();
}