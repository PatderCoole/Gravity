import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wand extends Actor
{
    public int id=0;
    String image;
    boolean solid = true;
    public Wand(int new_id, String Image)
    {
        id = new_id;
        image = Image;
    }
    public Wand(boolean s, int new_id, String Image)
    {
        solid = s;
        id = new_id;
        image = Image;
    }
    public void toggle()
    {
       solid = (solid == false)? true:false;
       
       
        /*
       if (solid == false)
       {
           solid = true;
       }
       else
       {
           solid = false;
       }*/
    }
    
    
    public void act()
    {
       
        if (solid == true)
        {
            setImage(image);
        }
        else
        {
            setImage("Free.png");
        }
    }
}
