import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int currentLevel = 0;
    public int seconds = 0;
    int frames = 0;
    GreenfootImage level;
    GreenfootSound gfs = new GreenfootSound("001.mp3");
    levelVisual lvlVis;
    
    public void started(){
        gfs.playLoop();
    }
    public void stopped(){
        gfs.pause();
    }
    public int getLevel()
    {
        return currentLevel;
    }
    public void nextLevel()
    {
        currentLevel++;
        setLevel(currentLevel);
        changeLevel();
    }
    public MyWorld()
    {    
         super(1200, 800, 1);
         setLevel(0);
         lvlVis = new levelVisual();
         changeLevel();
         addObject(new Spawn(),165,550);
         addObject(new Hebel(),1300,1300);
         
         addObject(lvlVis, getWidth() / 2, getHeight() / 2);
         
         //remove later
         addObject(new Box(),384,465);
         setPaintOrder(Player.class, Box.class, Hebel.class, Spawn.class, levelVisual.class);
    }
    public void changeLevel()
    {
        
        if(currentLevel == 3)
             {
              level = new GreenfootImage("level4.png");
              lvlVis.changeVisual(4);
             }
        if(currentLevel == 2)
             {
              level = new GreenfootImage("level3.png");
              lvlVis.changeVisual(3);
             }
        if(currentLevel == 1)
             {
              level = new GreenfootImage("level2.png");
              lvlVis.changeVisual(2);
             }
        if(currentLevel == 0)
             {
              level = new GreenfootImage("level1.png");
              lvlVis.changeVisual(1);
             }          
        setBackground(level);
        
        
        List spawns = getObjects(Spawn.class);
        if(spawns.size() > 0)
        {
          Spawn s = (Spawn) spawns.get(0); 
          s.act();  
        }
        
    }
    public void setLevel(int x)
    {
        currentLevel = x;
    }
    public void removeAllObjects() {
    removeObjects(getObjects(null));
    }
    public void Timer()
    {
        frames++;
        if (frames == 60)
        {
            frames = 0;
            seconds++;
        }
    }
}
