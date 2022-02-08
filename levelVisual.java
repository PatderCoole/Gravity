import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class levelVisual here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class levelVisual extends Actor
{
    public levelVisual()
    {
        setImage("visual_lvl1.png");
        //setLocation(0, 0);
    }
    
    public void changeVisual(int level)
    {
        setImage("visual_lvl" + Integer.toString(level) + ".png");
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
