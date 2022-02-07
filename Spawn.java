import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Spawn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spawn extends Actor
{
    /**
     * Act - do whatever the Spawn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld)getWorld();
        List PlayerList = world.getObjects(Player.class);
        if(PlayerList.size() == 0)
        {
            world.addObject(new Player(),getX(),getY());
        }
        
        if(world.currentLevel == 0)
        {
            setLocation(165, 550);
        }
        if(world.currentLevel == 1)
        {
            setLocation(100, 600);
        }
        if(world.currentLevel == 2)
        {
            setLocation(825, 650);
        }
        if(world.currentLevel == 3)
        {
            setLocation(100, 600);
        }
    }
}
