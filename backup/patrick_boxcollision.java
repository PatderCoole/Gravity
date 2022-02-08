public void BoxReaction()
    {
      MyWorld world = (MyWorld)getWorld(); 
        if(isTouching(Box.class))
      {
           List Boxes = world.getObjects(Box.class);
           float shortest_dist = -1;
           Box Nearest = null;
           for(int i = 0; i < Boxes.size(); i++)
           {
               Box b = (Box)Boxes.get(i);
               float dist = (float)Math.sqrt((b.getX()^2)+(b.getY()^2));
               if(dist < shortest_dist || shortest_dist == -1)
               {
                   shortest_dist = dist;
                   Nearest = b;
               }
           }
           if(Nearest != null)
           {
               if(Nearest.getX()<getX())
               {
                   dx = 0;
                   FixSetLocation(getX()+1,getY());
               }
               else if(Nearest.getX()>getX())
                    {
                      dx = 0;
                      FixSetLocation(getX()-1,getY());
                    }
               if(Nearest.getY()<getY())
               {
                 dy = 0;
                 FixSetLocation(getX(),getY()+1); 
               }
               else if(Nearest.getY()>getY())
                    {
                      dy = 0;
                      FixSetLocation(getX(),getY()-1);
                    }
           }
      }
    }