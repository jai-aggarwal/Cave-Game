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
public class User {
    
    private int[] position;
    private int hp;
    private int sword;
    private int armor;
    private CaveSystem cs; 
    
    public User(CaveSystem cs2)
    {
        cs = cs2;
        int ns, ew; 
        position = new int[2];
        Random r = new Random();
        while(true)
        {
            ns = r.nextInt(cs.returnCaves().length);
            ew = r.nextInt(cs.returnCaves()[0].length);
            if (cs.returnCave(ns, ew) != 0 && !(ns == cs.getTrophyLocation()[0]
                    && ew == cs.getTrophyLocation()[1]))
            {
                break;
            }    
        }
        position = new int[]{ns, ew};
        hp = 10;
        sword = -1;
        armor = -1;
    }
    
    public int move(String direction)
    {
        if (direction.equalsIgnoreCase("N"))
        {
            if(cs.returnCave(position[0] - 1, position[1]) == 1)
            {
                position[0]--;
                hp--;
                return 0;
            }
           
        }
        else if (direction.equalsIgnoreCase("S"))
        {
            if(cs.returnCave(position[0] + 1, position[1]) == 1)
            {
                position[0]++;
                hp--;
                return 0;
            }
        }
        else if(direction.equalsIgnoreCase("E"))
        {
            if(cs.returnCave(position[0], position[1] + 1) == 1)
            {
                position[1]++;
                hp--;
                return 0;
            }
        }
        else if(direction.equalsIgnoreCase("W"))
        {
            if(cs.returnCave(position[0], position[1] - 1) == 1)
            {
                position[1]--;
                hp--;
                return 0;
            }
        }
        return -1;
    }
    
    public int checkMonster(Monster m)
    {
        int[] mPosition = m.getMonsterPosition();
        if(position[0] == mPosition[0] && position[1] == mPosition[1])
        {
            return 2;
        }
        else if(checkMonsterAdjacent(m))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    public boolean checkMonsterAdjacent(Monster m)
    {
        int[] mPosition = m.getMonsterPosition();
        if(position[0] + 2 == mPosition[0] && position[1] == mPosition[1])
        {
            return true;
        }
        else if (position[0] - 2 == mPosition[0] && position[1] == mPosition[1])
        {
            return true;
        }
        else if(position[0] == mPosition[0] && position[1] + 2 == mPosition[1])
        {
            return true;
        }
        else if(position[0] == mPosition[0] && position[1] - 2 == mPosition[1])
        {
            return true;
        }
        else if(position[0] - 1 == mPosition[0] && position[1] + 1 == mPosition[1])
        {
            return true;
        }
        else if(position[0] + 1 == mPosition[0] && position[1] + 1 == mPosition[1])
        {
            return true;
        }
        else if(position[0] + 1 == mPosition[0] && position[1] - 1 == mPosition[1])
        {
            return true;
        }
        else if(position[0] - 1 == mPosition[0] && position[1] -1 == mPosition[1])
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getHp()
    {
        return hp;
    }
    
    public void getHurt()
    {
        hp -= (4 - armor);
        if(hp < 0)
        {
            hp = 0;
        }
    }
    
    public void attackMonster(Monster m)
    {
        if(sword != 2)
        {
            m.hurt((sword + 1) * 2);
        }
        else
            m.hurt(8);
    }
    
    public int checkFood()
    {
        int foodAmount = cs.getAmountOfFood();
        int foodsInCave = 0;
        for (int i = 0; i < foodAmount; i++)
        {
            int[] fPos = cs.getFoodLocation(i);
            if (position[0] == fPos[0] && position[1] == fPos[1])
            {
                foodsInCave++;
                cs.foodDisappear(i);
            }            
        }
        return foodsInCave;
        
    }
    
    public void eatFood()
    {
        hp += 2;
        if(hp > 10)
        {
            hp = 10;
        }
    }
    
    public boolean checkTrophy()
    {
        int[] tPos = cs.getTrophyLocation();
        if(position[0] == tPos[0] && position[1] == tPos[1])
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int checkSword()
    {
        for (int i = 0; i < 3; i++)
        {
            int[] sPos = cs.getSwordLocation(i);
            if(position[0] == sPos[0] && position[1] == sPos[1])
            {
                return i;
            }
        }
        return -1;
    }
    
    public void pickUpSword(int swordNum)
    {
        if (sword != -1)
        {
            cs.setSwordLocation(position[0], position[1], sword); //we drop 
            //the current sword.
        }
        sword = swordNum;        
        cs.setSwordLocation(-1, -1, swordNum); //this way we won't run into the
        //sword in that location in the cave until we drop it.
    }
    
    public void pickUpArmor(int armorNum)
    {
        if (armor != -1)
        {
            cs.setArmorLocation(position[0], position[1], armor); //we drop 
            //the current armor.
        }
        armor = armorNum;        
        cs.setArmorLocation(-1, -1, armorNum); //this way we won't run into the
        //armor in that location in the cave until we drop it.
    }
    
    public int checkArmor()
    {
        for (int i = 0; i < 3; i++)
        {
            int[] aPos = cs.getArmorLocation(i);
            if(position[0] == aPos[0] && position[1] == aPos[1])
            {
                return i;
            }
        }
        return -1;
    }
    
    public int getSword()
    {
        return sword;
    }
    
    public int getArmor()
    {
        return armor;
    }
}
