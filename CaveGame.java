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

public class CaveGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Jai's Cave Game!");
        System.out.println();
        System.out.println();
        
        boolean game = true;
        outerloop:
        while (game)
        {
            int caves = 0;
            while(true)
            {
                try
                {
                    System.out.print("Enter how many caves you would like to "
                            + "generate: ");
                    caves = input.nextInt();
                    if(caves < 16 || caves > 100)
                    {
                        System.out.println();
                        System.out.println("The number of caves must be between 16 "
                                + "and 100.");
                        System.out.println();
                    }
                    else
                    {
                        break;
                    }
                }
                catch(InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Invalid input: you must enter an integer"
                            + "between 16 and 100.");
                    System.out.println();
                    input.next();
                }     

            }
            CaveSystem cs = new CaveSystem(caves);
            Monster monster = new Monster(caves, cs);
            User user = new User(cs);
            boolean notEnd = true;
            while (notEnd)
            {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("You now have " + user.getHp() + " health.");
                if(user.getHp() <= 0)
                {
                    System.out.println("You died! Game over!");
                    break;
                }
                int monsterNearby = user.checkMonster(monster);
                if(monsterNearby == 2)
                {
                    System.out.println("A monster in the cave attacked you!");
                    user.getHurt();
                    if(user.getHp() <= 0)
                    {
                        System.out.println("You died! Game over!");
                        break;
                    }
                    System.out.println("You now have " + user.getHp() + 
                            " health.");
                    if(user.getSword() > -1)
                    {
                        System.out.println("You attacked the monster!");
                        user.attackMonster(monster);
                        if (monster.getHp() <= 0)
                        {
                            monster.die();
                            System.out.println("You killed the monster!");
                        }
                    }
                    
                }
                else if (monsterNearby == 1)
                {
                    System.out.println("Watch out! There's a monster nearby!");
                }
                if (user.checkTrophy())
                {
                    System.out.println("You found the trophy! You won the game!");
                    break;
                }
                int foods = user.checkFood();
                if(foods > 0)
                {
                    System.out.println("There are " + foods + 
                            " food items in the cave!");
                    System.out.println("You ate the food.");
                    for (int i = 0; i < foods; i++)
                    {
                        user.eatFood();
                    }
                    System.out.println("Your health is now " + user.getHp() + ".");
                }
                if(user.checkSword() > -1)
                {
                    System.out.println("There is a sword in the cave!");
                    if(user.getSword() > -1)
                    {
                        System.out.println("You dropped your current sword and "
                                + "picked up the new one.");
                    }
                    else
                    {
                        System.out.println("You picked up the sword.");
                    }
                    user.pickUpSword(user.checkSword());
                    
                }
                if(user.checkArmor() > -1)
                {
                    System.out.println("There is an armor in the cave!");
                    if(user.getArmor() > -1)
                    {
                        System.out.println("You dropped your current armor and "
                                + "picked up the new one.");
                    }
                    else
                    {
                        System.out.println("You picked up the armor.");
                    }
                    user.pickUpArmor(user.checkArmor());
                    
                }
                monster.move();
                while(true)
                {
                    System.out.println("Which direction would you like to head? "
                            + "(N/S/E/W)");
                    String direction = input.nextLine();
                    if(direction.equalsIgnoreCase("N") || 
                            direction.equalsIgnoreCase("S") ||
                            direction.equalsIgnoreCase("E") || 
                            direction.equalsIgnoreCase("W"))
                    {
                        int moveVar = user.move(direction);
                        if (moveVar == 0)
                        {
                            System.out.println();
                            System.out.println();
                            break;
                        }
                        else
                        {
                            System.out.println("You can't move! Choose another direction.");
                        }
                    }
                    else if(direction.equalsIgnoreCase("X"))
                    {
                        break outerloop;
                    }
                    else
                    {
                        System.out.println("You entered an invalid input.");
                    }
                    
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Enter Y to play again.");
            String yesNo = input.nextLine();
            if (!yesNo.equalsIgnoreCase("Y"))
            {
               break; 
            }
        }

        //TESTING BELOW THIS POINT
            
        /*System.out.println();
        System.out.println(caves);
        CaveSystem cs = new CaveSystem(caves);
        int[][] caveSys = cs.returnCaves();
        for(int i = 0; i < caveSys.length; i++)
        {
            for(int j = 0; j < caveSys[i].length; j++)
            {
                System.out.println(caveSys[i][j]);
            }
        }
        System.out.println();
        System.out.println(cs.returnCave(3, 5));

        System.out.println();
        System.out.println();
        System.out.println();
        Monster m = new Monster(caves, cs);
        System.out.println("Enter 'm'");
        String s = input.nextLine();
        s = input.nextLine();
        while(s.equalsIgnoreCase("M"))
        {
           System.out.println(m.getMonsterPosition()[0]);
           System.out.println(m.getMonsterPosition()[1]);
           System.out.println();
           System.out.println(m.getDest()[0]);
           System.out.println(m.getDest()[1]);
           m.move();
           System.out.println();
           System.out.println(cs.getTrophyLocation()[0]);
           System.out.println(cs.getTrophyLocation()[1]);
           System.out.println();
           for (int i = 0; i < 3; i++)
           {
               System.out.println(cs.getSwordLocation(i)[0]);
               System.out.println(cs.getSwordLocation(i)[1]);
               System.out.println();
           }
           for (int i = 0; i < 3; i++)
           {
               System.out.println(cs.getArmorLocation(i)[0]);
               System.out.println(cs.getArmorLocation(i)[1]);
               System.out.println();
           }
           for(int i = 0; i < cs.getAmountOfFood(); i++)
           {
               System.out.println(cs.getFoodLocation(i)[0]);
               System.out.println(cs.getFoodLocation(i)[1]);
               System.out.println();
           }
           System.out.println();
           System.out.println("Enter 'm'");
           s = input.nextLine();*/
           
        
    }
    
}
