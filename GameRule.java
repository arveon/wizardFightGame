import java.util.Random;
public class GameRule
{
    private Player player;
    private Field field;
    private Enemy enemy;
    private String move;
    private MedPack[] mPack;
    
    
    public GameRule(Player playerObject, Field fieldObject, Enemy enemyObject)
    {
        player = playerObject;
        field = fieldObject;
        enemy = enemyObject;
    }
    
    public boolean moveCheck(Player player, String playerMove)
    {
        boolean moveIsValid = false;
        move = playerMove;
        //player = player;
        //enemy = enemy;
        String nextTile;
        int playerVPos = player.getVPosition();
        int playerHPos = player.getHPosition();
        
       if(move.equals("d"))
       {
           playerHPos++;           
       }
       else if(move.equals("s"))
       {
           playerVPos++;
       }
       else if(move.equals("a"))
       {
           playerHPos--;
       }
       else if(move.equals("w"))
       {
           playerVPos--;
       }
       
       nextTile = field.getTile(playerVPos, playerHPos);           
       
       if(nextTile.equals("£"))
       {
           player.attackEnemy(enemy, playerVPos, playerHPos);
           field.removePreviousHealthPacks();
           generateHealthPacks(playerVPos, playerHPos, enemy.getVCoordinate(), enemy.getHCoordinate());
           moveIsValid = true;
       }
       if(nextTile.equals("H") || nextTile.equals("h") || nextTile.equals("S"))
       {
           player.healDamage(playerVPos, playerHPos);
           field.removeUsedHealthPack(playerVPos, playerHPos);
           moveIsValid = true;
       }
       else if(nextTile.equals("0"))
       {
           moveIsValid = true;
       }
       else if(nextTile.equals("/"))
       {
           moveIsValid = false;
       }
       return moveIsValid;
    }
    
    public boolean checkVictory()
    {
        if(enemy.getHealth()<1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean checkLose()
    {
        if (player.getHealth()<1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    
    public void generateHealthPacks(int playerVPosition, int playerHPosition, int enemyVPosition, int enemyHPosition)
    {
        int number = MedPack.getNumber();
        Random randomCoord = new Random();
        int hTemp;
        int vTemp;
        int packCounter = 0;
        mPack = new MedPack[number];
        
        do
        {
            hTemp = randomCoord.nextInt(7) + 1;
            vTemp = randomCoord.nextInt(7) + 1;             
            
            if(!field.getTile(vTemp,hTemp).equals("/") && !field.getTile(vTemp, hTemp).equals("£") & !field.getTile(vTemp, hTemp).equals("1"))
            {
                mPack[packCounter] = new MedPack(vTemp, hTemp, randomHealth());
                packCounter++;
            }
            
            
        }while(packCounter<mPack.length);
        
        field.setMedPacks(mPack);
        player.setMedPacks(mPack);
    }
    
    public int randomHealth()
    {
        Random randomNum = new Random();
        int healthPackStrength;
        int critChance;
        
        critChance = randomNum.nextInt(101);
        
        if(critChance < 40)
        {
            healthPackStrength = randomNum.nextInt(3) + 7;            
        }
        else if(critChance < 99)
        {
            healthPackStrength = randomNum.nextInt(3) + 2;
        }
        else 
        {
            healthPackStrength = 1000;
        }
        
        return healthPackStrength;
    }
    
    
    
    
    
    
    
    
    
    
}