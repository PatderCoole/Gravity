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
    
    //made by H4x0r_000
    void WorldCollision()
    {
        int rayLength;
        for(int direction = 0; direction < 4; direction++)
        {
            if(direction % 2 == 0)
            {
                rayLength = (int)(getImage().getHeight() / 2) + 1;
            }
            else
            {
                rayLength = (int)(getImage().getWidth() / 2) + 1;
            }
            
            boolean break_loop = false;
            for(int i = 1; i < rayLength; i++)
            {
                switch(direction)
                {
                    case 0:
                        {
                            if(FixGetColorAt(getX(), getY() + i).equals(Color.BLACK))
                            {
                                if(i == 1)
                                {
                                    break_loop = true;
                                    break;
                                }   
                                    
                                FixSetLocation(getX(), getY() - (rayLength - i));
                                dy = 0;
                            }
                        }break;
                        
                    case 1:
                        {
                            if(FixGetColorAt(getX() + i, getY()).equals(Color.BLACK))
                            {
                                if(i == 1)
                                {
                                    break_loop = true;
                                    break;
                                }
                                    
                                FixSetLocation(getX() - (rayLength - i), getY());
                            }
                        }break;
                        
                    case 2:
                        {
                            if(FixGetColorAt(getX(), getY() - i).equals(Color.BLACK))
                            {
                                if(i == 1)
                                {
                                    break_loop = true;
                                    break;
                                }
                                    
                                FixSetLocation(getX(), getY() + (rayLength - i));
                                dy = 0;
                            }
                        }break;
                        
                    case 3:
                        {
                            if(FixGetColorAt(getX() - i, getY()).equals(Color.BLACK))
                            {
                                if(i == 1)
                                {
                                    break_loop = true;
                                    break;
                                }
                                    
                                FixSetLocation(getX() + (rayLength - i), getY());
                            }
                        }
                }
                
                if(break_loop == true)
                    break;
            
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
      WorldCollision();
      Restart();
      world.Timer();
     }
} 
 
    
    

