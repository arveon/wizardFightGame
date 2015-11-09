import java.util.Random;
public class Field
{
    private String[][] field = new String[10][10];
    private Player player;
    private Enemy enemy;
    private MedPack[] mPack;
    private Random randomNum = new Random();
    boolean mPackExistence = false;
    
    public Field(Player playerObject, Enemy en)
    {
        player = playerObject;
        enemy = en;
        field = ReadFromFile.loadMap();
    }
    
    public boolean getMPackExistence()
    {
        return mPackExistence;
    }
    
    public String getTile(int vCoord, int hCoord)
    {
        return field[vCoord][hCoord];
    }
    
    public void setField()
    {
        int h;
        int v;
        h = player.getHPosition();
        v = player.getVPosition();
        field[v][h] = "1";
        
        h = enemy.getHCoordinate();
        v = enemy.getVCoordinate();
        field[v][h] = "£";
        
        if(mPackExistence == true)
        {
            placeHealthPacks();
        }
    }
    
    public void printField()
    {
        field[0][0] = "/";
        for(int v = 0; v<field.length;v++)
        {
            for(int h = 0; h<field.length;h++)
            {
                System.out.print(field[v][h]);
            }
            System.out.println();
        }     
    }
    
    public void removePlayerTrail(int prevPlayerVPos, int prevPlayerHPos)
    {
        int v = prevPlayerVPos;
        int h = prevPlayerHPos;
        
        field[v][h] = "0";
    }
    
    public void setMedPacks(MedPack[] mPackArray)
    {
        mPack = mPackArray;
        mPackExistence = true;
    }    
    
    public void removeUsedHealthPack(int healthPackVCoordinate, int healthPackHCoordinate)
    {
        int i = 0;
        int tempV;
        int tempH;
        while(i<mPack.length)
        {
            tempH = mPack[i].getHCoordinate();
            tempV = mPack[i].getVCoordinate();
            if(tempH == healthPackHCoordinate && tempV == healthPackVCoordinate)
            {
                mPack[i].removeHealthPack();
                break;
            }
            i++;
        }
    }
    
    public void resetField()
    {
        mPackExistence = false;
        field = ReadFromFile.loadMap();
        setField();
    }
    
    public void placeHealthPacks()
    {
          //placeHealthPacks
      if(mPackExistence == true)
      {
          int i = 0;
          String symbol = null;
          while(i<mPack.length)
          {
              String tile = getTile(mPack[i].getVCoordinate(), mPack[i].getHCoordinate());
              if(!tile.equals("/") && !tile.equals("1") && !tile.equals("£"))
              {
                  if(mPack[i].getAmountHealed()<5)
                  {
                      symbol = "h";
                  }
                  else if(mPack[i].getAmountHealed()==1000)
                  {
                      symbol = "S";
                  }
                  else if(mPack[i].getAmountHealed()>5)
                  {
                      symbol = "H";
                  }
                  field[mPack[i].getVCoordinate()][mPack[i].getHCoordinate()] = symbol;
                  i++;                  
              }
              else if(tile.equals("/"))
              {
                  i++;
              }
              else
              {
                  int randomV = randomNum.nextInt(8)+1;
                  int randomH = randomNum.nextInt(8)+1;
                  mPack[i].setVCoordinate(randomV);
                  mPack[i].setHCoordinate(randomH);
              }
          }
      }
    }
    
    public void removePreviousHealthPacks()
    {
        if(mPackExistence == true)
        {
            int i = 0;
            while(i<mPack.length)
            {
                int v = mPack[i].getVCoordinate();
                int h = mPack[i].getHCoordinate();
                field[v][h] = "0";
                i++;
            }   
        }
    }
}
    

