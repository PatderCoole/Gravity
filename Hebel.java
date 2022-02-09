import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Hebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hebel extends Actor
{
    int id = 0;
    public boolean is_active = false;
    public Hebel(int ID)
    {
        id = ID;
    }
    public void interact()
    {
        if (isTouching(Player.class))
        {
            if(is_active == false)
            {
               MyWorld world = (MyWorld)getWorld();
            List<Wand> Listname = world.getObjects(Wand.class);
            for(Wand w: Listname)
            {
                if(w.id==id)
                {
                    w.toggle();
                }
            } 
            }
            is_active = true;
            setImage("Phone2.png");
        }
    }
    
    
    public void act()
    {
       MyWorld world = (MyWorld)getWorld();
       interact();
       if(world.currentLevel == 1)
       {
           setRotation(180);
       }
    }
}
