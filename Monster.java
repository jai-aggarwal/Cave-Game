/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavegame;

/**
 *
 * @author Jai
 */
import java.util.*;

public class Monster {
    
    private int[] position;
    private int numCaves;
    private CaveSystem cs;
    private int[] destination;
    private int hp;
    private boolean dead;
    
    public Monster(int caveNum, CaveSystem caveS)
    {
        numCaves = caveNum;
        cs = caveS;
        position = new int[2];
        destination = new int[2];
        position = changeCoordinates();
        destination = changeCoordinates();
        hp = 10;
        dead = false;
    }
    
    public int[] changeCoordinates()
    {
        int ns, ew;
        Random r = new Random();
        while(true)
        {
            ns = r.nextInt(cs.returnCaves().length);
            ew = r.nextInt(cs.returnCaves()[0].length);
            if (cs.returnCave(ns, ew) != 0)
            {
                break;
            }       
        }
        int[] array = new int[]{ns, ew};
        return array;
    }
    
    public void move()
    {
        if (!dead)
        {
            while (position[1] == destination[1] && position[0] == destination[0]) 
            //if position == destination, it will change its destination.
            {
                destination = changeCoordinates();
            }

            //So now for the monster to actually move, it will move east/west first
            //then north/south to its destination
            if(position[1] < destination[1])
            {
                position[1]++;
            }
            else if(position[1] > destination[1])
            {
                position[1]--;
            }
            else //position[1] is equal
            {
                if(position[0] < destination[0])
                {
                    position[0]++;
                }
                else if(position[0] > destination[0])
                {
                    position[0]--;
                }
            }
        }        
    }
    
    
    public int[] getMonsterPosition() 
    {
        return position;
    }

    public int[] getDest()
    {
        return destination;
    }
    
    public void hurt(int amount)
    {
        hp -= amount;
    }
    
    public int getHp()
    {
        return hp;
    }
    
    public void die()
    {
        dead = true;
        position = new int[]{-2, -2};
    }
            
    
}
