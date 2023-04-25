package src;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HirsipuuHaeSana {
    
    final String[] Kategoriat = {"Maat", "Automerkit", "Jaskanseksifantasiat"};

    public HirsipuuHaeSana(){

    }


    public String SanaTiedosto(String file){


        if(file.equals("Satunnainen")){
            Random rand = new Random(System.currentTimeMillis());
            int randomKategoria = rand.nextInt(Kategoriat.length);
            file = Kategoriat[randomKategoria];
        }
        String filename = "src/" + file + ".txt";

        List<String> sanat = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line; 
            while((line = reader.readLine()) != null){
                String[] words = line.split(" ");
                for(String word : words){
                    sanat.add(word);
                }
                
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    
        Random rand = new Random(System.currentTimeMillis());
        String randomWord = sanat.get(rand.nextInt(sanat.size()));


        return randomWord;

        
    
    }
}
