import java.util.Scanner;

public class PlayerActions
{
    private Player player;
    private Field field;
    private Enemy enemy;
    private GameRule rules;
    private int moveCount = 0;
    private boolean defeat = false;
    private boolean victory = false;
    private boolean surrender = false;
    private int damageDone = 0;
    
    public PlayerActions(Player playerObject, Field fieldObject, Enemy enemyObject)
    {
        player = playerObject;
        field = fieldObject;
        enemy = enemyObject;
    }
    
    public void aMenu()
    {
      Scanner actionListener = new Scanner(System.in);
      String action;
      
      System.out.print('\u000c');
      
      if(victory == true)
      {
          System.out.println("Victory!!! You managed to kill the wizard in " + moveCount + " moves."); 
          moveCount = 0;
          victory = false;
      }
      else if(defeat == true)
      {
          System.out.println("Defeat. You died to the evil wizard dealing " + damageDone + " damage in total.");
          damageDone = 0;
          defeat = false;
      }
      else if(surrender == true)
      {
          System.out.println("You fled from an evil wizard ransacking your lands. Your people hope that next time you will be more corageous... ");
          surrender = false;
      }
      
      field.printField();
      System.out.println("Total move count: " + moveCount);
      System.out.println("Enemy remaining health: " + enemy.getHealth());
      System.out.println("Your health: " + player.getHealth());
      System.out.println("Enter the action you would like to perform");
      action = actionListener.nextLine();
      performAction(action);
    }
    
    public void performAction(String playerAction)
    {
        rules = new GameRule(player, field, enemy);
        boolean moveValidity = false;
        String move = playerAction;
        
        if(move.equals("w") || move.equals("a")|| move.equals("s")|| move.equals("d"))
        {
            moveValidity = rules.moveCheck(player, move);
        }
        else if(move.equals("stop"))
        {
            System.exit(0);
        }
        else if(move.equals("reset") || move.equals("forfeit") || move.equals("surrender"))
        {
            resetGame(0);
        }
        else
        {
            aMenu();
        }
        
        defeat = rules.checkLose();
        if(defeat == true)
        {
           resetGame(2); 
        }
        
        if(moveValidity == true && victory == false && defeat == false)
        {
            enemy.attackPlayer();
            victory = rules.checkVictory();
            if(victory == true)
            {
                resetGame(1);
            }
            
            if(move.equals("d"))
            {
                player.moveRight(field);
            }
            else if(move.equals("s"))
            {
                player.moveDown(field);
            }
            else if(move.equals("a"))
            {
                player.moveLeft(field);
            }
            else if(move.equals("w"))
            {
                player.moveUp(field);
            }

            if(victory==false)
            {
                moveCount++;                
            }
            field.setField();
            aMenu();
        }
        else
        {
            aMenu();
        }
    }
    
    public void resetGame(int reason)
    {
        if(reason == 0)
        {
            surrender = true;
        }
        else if(reason == 2)
        {
            int damageDone = enemy.getMaxHealth() - enemy.getHealth();
        }
        
        //moveCount = 0;
        player.reset();
        enemy.reset();
        field.resetField();
    }
}
