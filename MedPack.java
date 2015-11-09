public class MedPack
{
    private int h;
    private int v;
    private int strength;
    private static final int MPNumber = 4;
    
    public MedPack(int hCoordinate, int vCoordinate, int amountToHeal)
    {
        h = hCoordinate;
        v = vCoordinate;
        strength = amountToHeal;
    }
    
    public static int getNumber()
    {
        return MPNumber;
    }
    
    public int getAmountHealed()
    {
        return strength;
    }
    
    public int getVCoordinate()
    {
        return v;
    }
    
    public int getHCoordinate()
    {
        return h;
    }
    
    public void setHCoordinate(int hCoordinate)
    {
        h = hCoordinate;
    }
    
    public void setVCoordinate(int vCoordinate)
    {
        v = vCoordinate;
    }
    
    public void removeHealthPack()
    {
        v = 0;
        h = 0;
    }
}
