
public class Main
{
  public static void main(String args[])
  {
      Player player = new Player();
      Enemy enemy = new Enemy(1,1, "MAGE", player);
      Field field = new Field(player,enemy);
      PlayerActions processor = new PlayerActions(player, field, enemy);
      
      //field.generateHealthPacks(player.getVPosition(), player.getHPosition(), enemy.getVCoordinate(), enemy.getHCoordinate());
      field.setField();      
      processor.aMenu();
    
  }
  
  
  
}
