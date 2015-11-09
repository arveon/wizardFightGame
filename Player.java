import java.util.Random;
public class Player
{
    private int v;
    private int h;
    private Field field;
    private final int MAXHP = 35;
    private int curHealth;
    private MedPack[] mPack;
    
    public Player()
    {
        curHealth = MAXHP;
        h = 5;
        v = 5;
    }
    
    public int getVPosition()
    {
        return v;
    }
    
    public int getHPosition()
    {
        return h;
    }  
    
    public int getHealth()
    {
        return curHealth;
    }    
    
    public void setVPosition(int VPosition)
    {
        v = VPosition;
    }
    
    public void setHPosition(int HPosition)
    {
        h = HPosition;
    }
    
    public void moveRight(Field fieldObject)
    {
        field = fieldObject;
        int playerVPosition;
        int playerHPosition;        
        
        playerVPosition = getVPosition();
        playerHPosition = getHPosition();
        //System.out.println("PLayer VERTICAL position was "+playerVPosition+". HORIZONTAL was "+playerHPosition);
        field.removePlayerTrail(playerVPosition, playerHPosition);
        setHPosition(playerHPosition+1);
        //System.out.println("PLayer VETRICAL position is "+getVPosition()+". HORIZONTAL is "+getHPosition());                
    }
    
    public void moveDown(Field fieldObject)
    {
        field = fieldObject;
        int playerVPosition;
        int playerHPosition;
                
        playerVPosition = getVPosition();
        playerHPosition = getHPosition();
        
        field.removePlayerTrail(playerVPosition, playerHPosition);   
        //System.out.println("THe tile is now set to: " +field.getTile(playerVPosition, playerHPosition));
        setVPosition(playerVPosition+1);
        
    }
    
    public void moveLeft(Field fieldObject)
    {
        field = fieldObject;
        int playerVPosition;
        int playerHPosition;
          
        playerVPosition = getVPosition();
        playerHPosition = getHPosition();

        field.removePlayerTrail(playerVPosition, playerHPosition);
        System.out.println("Previous position removed!");
        setHPosition(playerHPosition-1);
    }
    
    public void moveUp(Field fieldObject)
    {
        field = fieldObject;
        int playerVPosition;
        int playerHPosition;
         
        playerVPosition = getVPosition();
        playerHPosition = getHPosition();
        
        field.removePlayerTrail(playerVPosition, playerHPosition);  
        System.out.println("Previous position removed!");
        setVPosition(playerVPosition-1);        
    }
    
    public void reset()
    {
        //field.removePlayerTrail(getVPosition(), getHPosition());
        v = 5;
        h = 5;
        curHealth = MAXHP;
    }
    
    public void attackEnemy(Enemy enemyObject,int enemyVPos, int enemyHPos)
    {
        Random basicDamage = new Random();
        int vCoord = enemyVPos;
        int hCoord = enemyHPos;        
        Enemy enemy = enemyObject;
        
        int playerVPos = getVPosition();
        int playerHPos = getHPosition();        
        
        int damageDone = basicDamage.nextInt(3) + 1;
        enemy.changeAttackedEnemyCoords(playerVPos, playerHPos, damageDone);
    }
    
    public void getDamage(int damageTaken)
    {
        curHealth -= damageTaken;
    }
    
    public void healDamage(int newPlayerVPos, int newPlayerHPos)
    {
        int tempV;
        int tempH;
        int theory;
        int i = 0;
        //mPack = medPackArray;
        System.out.println("Number of healthpacks on the field: " + mPack.length);
        while(i<mPack.length)
        {
            tempV = mPack[i].getVCoordinate();
            tempH = mPack[i].getHCoordinate();
            
            if(tempV == newPlayerVPos && tempH == newPlayerHPos)
            {
                theory = curHealth + mPack[i].getAmountHealed();
                if(theory>MAXHP)
                {
                    int healed = MAXHP - curHealth;
                    System.out.println("You picked up a health pack. Healed for " + healed + " HP.");
                    curHealth = MAXHP;
                }
                else
                {
                    System.out.println("You picked up a health pack. Healed for " + mPack[i].getAmountHealed() + " HP.");
                    curHealth = theory;
                }
                break;
            }
            else
            {
                i++;
            }
        }
    }
    
    public void setMedPacks(MedPack[] mpack)
    {
        mPack = mpack;
    }
}
