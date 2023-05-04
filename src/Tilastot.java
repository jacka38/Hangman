package src;

import java.io.File;
import java.io.FileWriter;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class Tilastot {


    File viimeisinpeli;
    File top3;

    public Tilastot(){

    }


    public void setFileTilastot(){

        try{
            viimeisinpeli = new File("src/Tilastotiedostot/LastGame.txt");
            top3 = new File("src/Tilastotiedostot/top3.txt");
            if(!viimeisinpeli.exists()){
                viimeisinpeli.createNewFile();
            }
            if(!top3.exists()){
                top3.createNewFile();
            }
        }
        catch(Exception e){

        }
    }

    public void writeLastGame(int points, String word, JComboBox kategorialista, ButtonGroup group, int guesses){


        String vaikeustaso = "Helppo";
       
        for(Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()){
                vaikeustaso = button.getText();
            }
        }

        try{
            FileWriter fw = new FileWriter(viimeisinpeli);
            fw.write("Pisteet: " + points + "\n" + "Arvaukset: " + guesses +"\n" + "Vaikeustaso: " + vaikeustaso + "\n" + "Sana: " + word + "\n" + "Kategoria: " + kategorialista.getSelectedItem().toString());
            fw.close();
        }
        catch(Exception e){

        }
    }
    
}
