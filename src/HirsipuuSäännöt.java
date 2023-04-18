package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HirsipuuSäännöt{


    public HirsipuuSäännöt(){

        Tulostasäännöt();

    }

    private String säännöt;

    private void Tulostasäännöt(){


        StringBuilder rules = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader("src/Testi123.txt"))){
            String line; 
            while((line = reader.readLine()) != null){
                rules.append(line).append("\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        säännöt = rules.toString();
        
    }

    public String GetSäännöt(){

        return säännöt;
    }

}