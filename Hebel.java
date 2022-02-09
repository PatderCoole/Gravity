import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hebel extends Actor
{
    boolean is_active = false;
    
    public void interact()
    {
        if (isTouching(Player.class))
        {
            is_active = true;
            setImage("Phone2.png");
        }
    }
    public void check_LevelPosition()
    {
        MyWorld world = (MyWorld)getWorld();
       if(world.currentLevel == 1)
            {
            setLocation(80, 115);
            }
       if(world.currentLevel == 2)
            {
            setLocation(400, 250);    
            }
       if(world.currentLevel == 3)
            {
            setLocation(1300, 1300);    
            } 
    }
    
    public void act()
    {
       MyWorld world = (MyWorld)getWorld();
       check_LevelPosition();
       interact();     
    }
}
