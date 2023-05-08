package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Tilastot {


    File viimeisinpeli;
    File top3;
    List<String> vikapeli = new ArrayList<>();
    List<String> top1score = new ArrayList<>();
    List<String> top2score = new ArrayList<>();
    List<String> top3score = new ArrayList<>();
    List<String> huonopeli = new ArrayList<>();
    List<String> templist = new ArrayList<>();
    JTextArea top1text = new JTextArea();
    JTextArea top2text = new JTextArea();
    JTextArea top3text = new JTextArea();
    JTextArea vikatext = new JTextArea();

    public Tilastot(){
        retrieveTop3();

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
            fw.write(points+"," + guesses+"," + vaikeustaso+"," + word+"," + kategorialista.getSelectedItem().toString());
            fw.close();
        }
        catch(Exception e){

        }
    }


    public void writeTop3List(int points, String word, JComboBox kategorialista, ButtonGroup group, int guesses) {

        String vaikeustaso = "Helppo";
        String[] tempScore = {""};

        for(Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()){
                vaikeustaso = button.getText();
            }
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/Tilastotiedostot/LastGame.txt"), "UTF-8"));
            String lastgame = reader.readLine();
            tempScore = lastgame.split(",");
            reader.close();
        }
        catch(Exception e){

        }
        if(top1score.isEmpty()){
            try{
                FileWriter fw = new FileWriter(top3);
                fw.write(points+"," + guesses+"," + vaikeustaso+"," + word+"," + kategorialista.getSelectedItem().toString());
                fw.close();
            }
            catch(Exception E){

            }
        } 
        else{
            templist = compareTop3(points);
            if(templist.equals(top1score)){
                top1score = new ArrayList<>(Arrays.asList(tempScore));
                templist = top1score;
            }
            if(templist.equals(top2score)){
                top2score = new ArrayList<>(Arrays.asList(tempScore));
                templist = top2score;
            }
            if(templist.equals(top3score)){
                top3score = new ArrayList<>(Arrays.asList(tempScore));
                templist = top3score;
            }

            try{
                FileWriter fw = new FileWriter(top3);
                fw.write(String.join(",", top1score) + "\n" 
                + String.join(",", top2score) + "\n" + String.join(",", top3score));
                fw.close();
            }
            catch(Exception e){

            }
        } 


    }

    public List<String> compareTop3(int points){

        int top1points = 0;
        int top2points = 0;
        int top3points = 0;

        if(!top1score.isEmpty()){
            top1points = Integer.parseInt(top1score.get(0));
        }
        if(!top2score.isEmpty()){
            top2points = Integer.parseInt(top2score.get(0));
        }
        if(!top3score.isEmpty()){
            top3points = Integer.parseInt(top3score.get(0));
        }

        if(points >= top1points){
            top3score = top2score;
            top2score = top1score;
            return top1score;
        }
        if(points >= top2points && points <= top1points){
            top3score = top2score;
            return top2score;
        }
        if(points >= top3points && points <= top2points){
            return top3score;
        }

        return huonopeli;
    }

    public void retrieveTop3(){


        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/Tilastotiedostot/top3.txt"), "UTF-8"));
            
            String scoreLine = reader.readLine();
            if(scoreLine != null){
                String[] tempScore = scoreLine.split(",");
                top1score = new ArrayList<>(Arrays.asList(tempScore));
            }
            scoreLine = reader.readLine();
            if(scoreLine != null){
                String[] tempScore = scoreLine.split(",");
                top2score = new ArrayList<>(Arrays.asList(tempScore));
            }

            scoreLine = reader.readLine();
            if(scoreLine != null){
                String[] tempScore = scoreLine.split(",");
                top3score = new ArrayList<>(Arrays.asList(tempScore));
            }
            reader.close();


            BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("src/Tilastotiedostot/LastGame.txt"), "UTF-8"));
            scoreLine = reader1.readLine();
            if(scoreLine != null){
                String[] tempScore = scoreLine.split(",");
                vikapeli = new ArrayList<>(Arrays.asList(tempScore));
            }
            reader1.close();
            
        }
        catch(Exception e){
            
        }
    }

    public void createTextareas(){


        JTextArea emptytop1 = new JTextArea("\nPisteet: -" + "\nArvaukset: -" + "\nVaikeustaso: -"  + "\nSana: -" + "\nKategoria: -");
        JTextArea emptytop2 = new JTextArea("\nPisteet: -" + "\nArvaukset: -" + "\nVaikeustaso: -"  + "\nSana: -" + "\nKategoria: -");
        JTextArea emptytop3 = new JTextArea("\nPisteet: -" + "\nArvaukset: -" + "\nVaikeustaso: -"  + "\nSana: -" + "\nKategoria: -");
        JTextArea emptyvika = new JTextArea("\nPisteet: -" + ", Arvaukset: -"+ ", Vaikeustaso: -"+ ", Sana: -" + ", Kategoria: -" + "\n\n");
        vikatext = emptyvika;
        top1text = emptytop1;
        top2text = emptytop2;
        top3text = emptytop3;
        top1text.setEditable(false);
        top2text.setEditable(false);
        top3text.setEditable(false);
        vikatext.setEditable(false);

        if (!top1score.isEmpty()){
            top1text = new JTextArea("\nPisteet: " + top1score.get(0) + "\nArvaukset: " + top1score.get(1)
                    + "\nVaikeustaso: " + top1score.get(2)
                    + "\nSana: " + top1score.get(3) + "\nKategoria: " + top1score.get(4));
            top1text.setEditable(false);
        }
        if(!top2score.isEmpty()){
            top2text = new JTextArea("\nPisteet: " + top2score.get(0) + "\nArvaukset: " + top2score.get(1)
                    + "\nVaikeustaso: " + top2score.get(2)
                    + "\nSana: " + top2score.get(3) + "\nKategoria: " + top2score.get(4));
            top2text.setEditable(false);
        }
        if(!top3score.isEmpty()){
            top3text = new JTextArea("\nPisteet: " + top3score.get(0) + "\nArvaukset: " + top3score.get(1)
                    + "\nVaikeustaso: " + top3score.get(2)
                    + "\nSana: " + top3score.get(3) + "\nKategoria: " + top3score.get(4));
            top3text.setEditable(false);
        }
        if(!vikapeli.isEmpty()){
            vikatext = new JTextArea("\nPisteet: " + vikapeli.get(0) + ", Arvaukset: " + vikapeli.get(1)
                    + ", Vaikeustaso: " + vikapeli.get(2)
                    + ", Sana: " + vikapeli.get(3) + ", Kategoria: " + vikapeli.get(4) + "\n\n");
            vikatext.setEditable(false);
        }
        

        
    }


    public JTextArea getTop1text() {
        return top1text;
    }




    public JTextArea getTop2text() {
        return top2text;
    }



    public JTextArea getTop3text() {
        return top3text;
    }


    public JTextArea getVikatext() {
        return vikatext;
    }
    
    
}
