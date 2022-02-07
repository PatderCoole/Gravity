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
         changeLevel();
         addObject(new Spawn(),165,550);
         addObject(new Hebel(),1300,1300);
    }
    public void changeLevel()
    {
        if(currentLevel == 3)
             {
              level = new GreenfootImage("level4.png");
             }
        if(currentLevel == 2)
             {
              level = new GreenfootImage("level3.png");
             }
        if(currentLevel == 1)
             {
              level = new GreenfootImage("level2.png");
             }
        if(currentLevel == 0)
             {
              level = new GreenfootImage("level1.png");
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
