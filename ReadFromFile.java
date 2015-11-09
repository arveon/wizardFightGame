import java.io.*;

public class ReadFromFile
{
    public static String[][] loadMap()
    {
        String file = "maps/map1.txt";
        String line;
        String[][] field = new String[10][10];
        int fieldWidth = 10;
        int fieldHeight = 10;
        
        BufferedReader bufferedReader;
        try
        {
            FileReader filereader = new FileReader(file);
            bufferedReader = new BufferedReader(filereader);
            
            for(int v = 0;v<fieldHeight;v++)
            {
                line = bufferedReader.readLine();
                for(int h = 0;h<fieldWidth;h++)
                {
                   field[v][h] = line.substring(h,h+1);                   
                }                
            }
            
            bufferedReader.close();
            return field; 
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File Not Found!");
        }
        catch(IOException ex)
        {
            System.out.print("Something went WRONG!");
            ex.printStackTrace();
        }
        return null;
        
    }
}
