package src;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HirsipuuHaeSana { // Class to get a random word from chosen category
    
    final String[] Kategoriat = {"Maat", "Automerkit", "Englannin kieli", "Elokuvat", "Julkkikset"};

    public HirsipuuHaeSana(){

    }

    public String SanaTiedosto(String file) {
        if(file.equals("Satunnainen")){
            Random rand = new Random(System.currentTimeMillis());
            int randomKategoria = rand.nextInt(Kategoriat.length);
            file = Kategoriat[randomKategoria];
        }
        String filename = "src/Kategoriat/" + file + ".txt";
    
        List<String> sanat = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){
            String line; 
            while((line = reader.readLine()) != null){
                sanat.add(line);
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
