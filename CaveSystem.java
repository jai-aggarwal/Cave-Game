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

public class CaveSystem {
    private int[][] caves;
    
    private int[] trophy_location;
    private int[][] sword_location;
    private int[][] armor_location;
    private int food_amount;
    private int[][] food_location;
    
    public CaveSystem(int num_caves)
    {
        int squareRoot = (int)Math.floor(Math.sqrt(num_caves));
        int quotient = (int)(Math.ceil((double)(num_caves)/
                (double)(squareRoot)));
        
        caves = new int[squareRoot][quotient];
        //Now we initialize all existing caves to 1.
        int counter = 0;
        int i = 0;
        outerloop:
        while(i < quotient)
        {
            int j = 0;
            while(j < squareRoot)
            {
                caves[j][i] = 1;
                j++;
                counter++;
                if (counter == num_caves)
                {
                    break outerloop;
                }
            }
            i++;
        }
        
        trophy_location = new int[2];
        sword_location = new int[3][2];
        armor_location = new int[3][2];
        food_amount = (int)(Math.ceil((double)(num_caves) / (double)(6)));
        food_location = new int[food_amount][2];
        
        Random r = new Random();
        trophy_location = changeCoordinates();
        for (int j = 0; j < 3; j++)
        {
            while(true)
            {
                sword_location[j] = changeCoordinates();
                boolean sameCave = false;
                for(int k = j - 1; k >= 0; k--)
                {
                    if (sword_location[j][0] == sword_location[k][0] &&
                            sword_location[j][1] == sword_location[k][1])
                    {
                        sameCave = true;
                    }
                }
                if(sameCave == false)
                {
                    break;
                }
            }     
        }
        for (int j = 0; j < 3; j++)
        {
            while(true)
            {
                armor_location[j] = changeCoordinates();
                boolean sameCave = false;
                for(int k = j - 1; k >= 0; k--)
                {
                    if (armor_location[j][0] == armor_location[k][0] &&
                            armor_location[j][1] == armor_location[k][1])
                    {
                        sameCave = true;
                    }
                }
                if(sameCave == false)
                {
                    break;
                }
            }     
        }
        
        for(int j = 0; j < food_amount; j++)
        {
            food_location[j] = changeCoordinates();
        }
    }
    
    public int[] changeCoordinates()
    {
        int ns, ew;
        Random r = new Random();
        while(true)
        {
            ns = r.nextInt(caves.length);
            ew = r.nextInt(caves[0].length);
            if (caves[ns][ew] != 0)
            {
                break;
            }       
        }
        int[] array = new int[]{ns, ew};
        return array;
    }
    
    public int[][] returnCaves()
    {
        return caves;
    }
    
    public int returnCave(int nsCoordinate, int ewCoordinate)
    {
        //This method returns the value (1 or 0) of a specific cave.
        try
        {
            return caves[nsCoordinate][ewCoordinate];
        }
        catch(IndexOutOfBoundsException e)
        {
            return 0;
        }
            
    }
    
    
    public void setTrophyLocation(int nsCoordinate, int ewCoordinate)
    {
        trophy_location = new int[] {nsCoordinate, ewCoordinate};
    }
    
    public int[] getTrophyLocation()
    {
        return trophy_location;
    }
    
    public void setSwordLocation(int nsCoordinate, int ewCoordinate, int s)
    {
        sword_location[s] = new int[] {nsCoordinate, ewCoordinate};
    }
    
    public int[] getSwordLocation(int s)
    {
        return sword_location[s];
    }
    
    public void setArmorLocation(int nsCoordinate, int ewCoordinate, int a)
    {
        armor_location[a] = new int[] {nsCoordinate, ewCoordinate};
    }
    
    public int[] getArmorLocation(int a)
    {
        return armor_location[a];
    }
    
    public void setFoodLocation(int nsCoordinate, int ewCoordinate, int f)
    {
        food_location[f] = new int[] {nsCoordinate, ewCoordinate};
    }
    
    public int[] getFoodLocation(int f)
    {
        return food_location[f];
    }
    
    public int getAmountOfFood()
    {
        return food_amount;        
    }
    
    public void foodDisappear(int f)
    {
        food_location[f] = new int[]{-1, 1}; //This way we won't run into the 
        //food again
    }
    
    
}
