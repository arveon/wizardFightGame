import java.util.Random;
public class Enemy
{
    private int h;
    private int v;
    private String type;
    private final int MAXHP = 25; //25
    private int curHealth;
    private Player player;
    
  public Enemy(int vCoordinate, int hCoordinate, String typeOfTile, Player playerObject)
  {
      v = vCoordinate;
      h = hCoordinate;
      type = typeOfTile;
      curHealth = MAXHP;
      player = playerObject;
  }
  
  public int getVCoordinate()
  {
      return v;
  }
  
  public int getHCoordinate()
  {
      return h;
  }
  
  public int getHealth()
  {
      return curHealth;
  }
  
  public int getMaxHealth()
  {
      return MAXHP;
  }
  
  public String getType()
  {
      return type;
  }
  
  public void changeAttackedEnemyCoords(int playerVPos,int playerHPos, int damageReceived)
  {
      int playerV = playerVPos;
      int playerH = playerHPos;
      
      getDamage(damageReceived);
      
      if(curHealth<1)
      {
          h = 0;
          v = 0;
      }
      else
      {
          int tempH;
          int tempV;
          Random randomCoords = new Random();      
          
          do
          {
              tempH = randomCoords.nextInt(7) + 1;
              tempV = randomCoords.nextInt(7) + 1;
          }while(tempH == playerH && tempV ==playerV);
          
          h=tempH;
          v=tempV;
      }
  }
  
  public void getDamage(int damageReceived)
  {
      int dmg = damageReceived;
      curHealth -= damageReceived;
  }
  
  public void reset()
  {
      h = 1;
      v = 1;
      curHealth = MAXHP;
  }
  
  public void attackPlayer()
  {
      int damageDone;
      int crit;
      Random randomChance = new Random();
      
      crit = randomChance.nextInt(101);
      
      damageDone = ((crit > 10) ? 1:2);
      player.getDamage(damageDone);
  }
}