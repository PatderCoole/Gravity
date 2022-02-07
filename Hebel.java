import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hebel extends Actor
{
    /**
     * Act - do whatever the Hebel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
       MyWorld world = (MyWorld)getWorld();
       if(world.currentLevel == 1)
            {
            setLocation(100, 150);
            }
       if(world.currentLevel == 2)
            {
            setLocation(100, 150);    
            }
    }
}
