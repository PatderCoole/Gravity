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