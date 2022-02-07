import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Box extends Actor
{
    int currentLevel;
    double dx,dy;
    boolean gravity = true;
    boolean SpaceAlreadyPressed = false;
    boolean isSpaceKeyDownOnce()
    {
      boolean PressState = Greenfoot.isKeyDown("Space");
      if(PressState == false)
        {
         SpaceAlreadyPressed = false;
         return false;
        }
      else
        {
         if(SpaceAlreadyPressed == true)
            {
             return false;
            }
         else
            {
             SpaceAlreadyPressed = true;
             return true;
            }
        }
    }
    Color FixGetColorAt(int X,int Y)
    {
        MyWorld world = (MyWorld)getWorld();
        if (X >= 0 && X <= 1199 && Y >= 0 && Y <= 799)
        {
           return world.getColorAt(X,Y); 
        }
        else
        {
           return Color.WHITE;
        }
    }
    void FixSetLocation(int X,int Y)
    {
        
        if (X >= 0 && X <= 1199 && Y >= 0 && Y <= 799)
        {
           setLocation(X,Y); 
        }
    }
    
    public void exist()
    {
      MyWorld world = (MyWorld)getWorld();  
      setLocation(getX()+(int)Math.round(dx), getY()+(int)Math.round(dy));
      if (gravity == true)
         {
          dy = dy + 0.25;
         }
      else
         {
          dy = dy - 0.25;
         }
    }
    public void coolisionDet()
    {
        if (FixGetColorAt(getX(),getY()+25).equals(Color.BLACK) == true
      || FixGetColorAt(getX(),getY()-25).equals(Color.BLACK) == true)
          {
           dy = 0;
           if (FixGetColorAt(getX(),getY()+25).equals(Color.BLACK) == true)
              {
               FixSetLocation(getX(),getY()-1);  
              }
           else
              {
               FixSetLocation(getX(),getY()+1); 
              }
          }
      if (FixGetColorAt(getX()-15,getY()).equals(Color.BLACK) == true
      || (FixGetColorAt(getX()+15,getY()).equals(Color.BLACK) == true))
         {
          dx = 0; 
          if(FixGetColorAt(getX()-15,getY()).equals(Color.BLACK) == true)
             {
              FixSetLocation(getX()+1,getY());
             }
         else
             {
              FixSetLocation(getX()-1,getY()); 
             } 
         }
    }
    public void GravitySwitch(){
        if (isSpaceKeyDownOnce())
         {
          if(gravity==false)
             {
              gravity = true;
             }
          else
             {
              gravity = false;
             }
         }
    }
    public void destroy()
    {
        MyWorld world = (MyWorld)getWorld();
        if (FixGetColorAt(getX(),getY()).equals(Color.RED) == true)
         {   
          world.removeObject(this);
         }
    }
    
    /**
     * Act - do whatever the Box wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
         MyWorld world = (MyWorld)getWorld();  
      setLocation(getX()+(int)Math.round(dx), getY()+(int)Math.round(dy));
      exist();
      coolisionDet();
      GravitySwitch();
      destroy();
    }
}
