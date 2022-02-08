import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import java.util.List;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    GreenfootSound igs = new GreenfootSound("Jump.wav");
    GreenfootSound dgs = new GreenfootSound("Yoda.mp3");
    GreenfootSound ags = new GreenfootSound("Ding.mp3");
    GreenfootSound wgs = new GreenfootSound("Moai.mp3");
    Color Magenta = new Color(255,1,206);
    int currentLevel;
    double dx,dy;
    boolean gravity = true;
    boolean SpaceAlreadyPressed = false;
    boolean toutchingBox = false;
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
    void DebugLog(String str)
    {
        JOptionPane.showMessageDialog(null, str, "Debug Message", JOptionPane.INFORMATION_MESSAGE);
    }
    void FixSetLocation(int X,int Y)
    {
        if (X >= 0 && X <= 1199 && Y >= 0 && Y <= 799)
        {
           setLocation(X,Y); 
        }
    }
    
    
    
    public void Jump(){
        if (Greenfoot.isKeyDown("up") && (FixGetColorAt(getX(),getY()+22).equals(Color.BLACK) == true || toutchingBox == true) && gravity == true )
         {
          dy = -10;
          igs.play();
         }
      if (Greenfoot.isKeyDown("down") && (FixGetColorAt(getX(),getY()-22).equals(Color.BLACK) == true || toutchingBox == true) && gravity == false)
         {
          dy = +10;
          igs.play();
         }
    }
    public void respawn()
    {
        MyWorld world = (MyWorld)getWorld();
        List spawns = world.getObjects(Spawn.class);
          Spawn spawn = (Spawn)(spawns.get(0));
          int x = spawn.getX();
          int y = spawn.getY();
          FixSetLocation(x, y);
    }
    public void Dying(){
        if (FixGetColorAt(getX(),getY()).equals(Color.RED) == true)
         {
          respawn();
          dgs.play();
         }
    }
    public void LevelComplete(){
        if (FixGetColorAt(getX(),getY()).equals(new Color(0,255,0)) == true)
         {
          MyWorld world = (MyWorld)getWorld();   
          world.nextLevel();
          respawn();
          ags.play();
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
    public void Walking(){
        if (Greenfoot.isKeyDown("left") && gravity == true)
         {
         dx = -2.75;
         setImage("elephant2.png");
         }
        if (Greenfoot.isKeyDown("right") && gravity == true)
         {
         dx = 2.5;
         setImage("elephant.png");
         }
         
        if (gravity == true)
          {
          dy = dy + 0.25;
          setRotation(0);
          }
        else
          {
          dy = dy - 0.25;
          setRotation(180);
          if (Greenfoot.isKeyDown("left"))
                {
                 dx = -2.75;
                 setImage("elephant.png");
                }
          if (Greenfoot.isKeyDown("right"))
                {
                 dx = 2.5;
                 setImage("elephant2.png");
                }
          }
    }
    public void CollisionDetection(){
        if (FixGetColorAt(getX(),getY()+21).equals(Color.BLACK) == true || FixGetColorAt(getX(),getY()-21).equals(Color.BLACK) == true)
          {
           dy = 0;
           if(FixGetColorAt(getX(),getY()+21).equals(Color.BLACK) == true && FixGetColorAt(getX(),getY()-21).equals(Color.BLACK) == true)
               {
                   if(gravity == true)
                   {
                       FixSetLocation(getX(),getY()-1);
                   }
                   else
                   {
                       FixSetLocation(getX(),getY()+1);
                   }
               }
           else if (FixGetColorAt(getX(),getY()+21).equals(Color.BLACK) == true)
              {
               FixSetLocation(getX(),getY()-1);  
              }
           else
              {
               FixSetLocation(getX(),getY()+1); 
              }
          }
      if (FixGetColorAt(getX()-34,getY()).equals(Color.BLACK) == true || (FixGetColorAt(getX()+34,getY()).equals(Color.BLACK) == true))
         {
          dx = 0; 
          if(FixGetColorAt(getX()-34,getY()).equals(Color.BLACK) == true)
             {
              FixSetLocation(getX()+1,getY());
             }
          else
             {
              FixSetLocation(getX()-1,getY()); 
             } 
         }
    }
    public void Restart(){
         if (FixGetColorAt(getX(),getY()).equals(Magenta) == true)
         {
          MyWorld world = (MyWorld)getWorld();   
          world.setLevel(0);
          world.changeLevel();
          wgs.play();
          respawn();
         }
    }
    
    
    //made by H4x0r_000
    void ReactOnBoxCollision()
    {
        List<Box> boxes = getWorld().getObjects(Box.class);
        
        for(Box b : boxes)
        {
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
                toutchingBox = true;
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
            else
            {
                toutchingBox = false;
            }
        }
    }
    
    
    public void act() 
    {
      MyWorld world = (MyWorld)getWorld();  
      setLocation(getX()+(int)Math.round(dx), getY()+(int)Math.round(dy));
      dx = dx * 0.95;
      Walking();
      Jump();   
      ReactOnBoxCollision();
      GravitySwitch();
      Dying();
      LevelComplete();
      CollisionDetection();
      Restart();
      world.Timer();
     }
} 
 
    
    

