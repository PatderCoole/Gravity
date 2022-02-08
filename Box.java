import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    
    public void CollisionDetection(){
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
      if (FixGetColorAt(getX()-25,getY()).equals(Color.BLACK) == true
      || (FixGetColorAt(getX()+25,getY()).equals(Color.BLACK) == true))
         {
          dx = 0; 
          if(FixGetColorAt(getX()-25,getY()).equals(Color.BLACK) == true)
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
    
    //made by H4x0r_000
    void ReactOnBoxCollision()
    {
        List<Box> boxes = getWorld().getObjects(Box.class);
        
        for(Box b : boxes)
        {
            if(b == this)
            {
                return;
            }
            
            double xdist = b.getX() - getX();
            double ydist = b.getY() - getY();
            
            double distMag = Math.sqrt(Math.pow(xdist, 2) + Math.pow(ydist, 2));
            
            double xdir = xdist / distMag;
            double ydir = ydist / distMag;
            
            double plrBorderDistX = (xdir / (Math.abs(xdir) >= Math.abs(ydir) ? Math.abs(xdir) : Math.abs(ydir))) * 25;
            double plrBorderDistY = (ydir / (Math.abs(xdir) >= Math.abs(ydir) ? Math.abs(xdir) : Math.abs(ydir))) * 25;
            
            double intersectDistX = Math.abs(xdist) - (Math.abs(plrBorderDistX) * 2);
            double intersectDistY = Math.abs(ydist) - (Math.abs(plrBorderDistY) * 2);
            
            if(intersectDistX < 0 || intersectDistY < 0)
            {
                if(intersectDistX < intersectDistY)
                {
                    FixSetLocation((int)Math.round(getX() + (xdist >= 0 ? intersectDistX : (intersectDistX * (-1)))), getY());
                }
                else if(intersectDistY < intersectDistX)
                {
                    FixSetLocation(getX(),((int)Math.round(getY() + (ydist >= 0 ? intersectDistY : (intersectDistY * (-1))))));
                    dy = 0;
                }
                else
                {
                    FixSetLocation((int)Math.round(getX() + (xdist >= 0 ? intersectDistX : (intersectDistX * (-1)))),((int)Math.round(getY() + (ydist >= 0 ? intersectDistY : (intersectDistY * (-1))))));
                    dy = 0;
                }
            }
        }
    }
    
    /**
     * Act - do whatever the Box wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld)getWorld();  
        exist();
        setLocation(getX()+(int)Math.round(dx), getY()+(int)Math.round(dy));
        CollisionDetection();
        ReactOnBoxCollision();
        GravitySwitch();
        destroy();
    }
}
