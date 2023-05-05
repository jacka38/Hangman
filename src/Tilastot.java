package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class Tilastot {


    File viimeisinpeli;
    File top3;
    List<String> top1score = new ArrayList<>();
    List<String> top2score = new ArrayList<>();
    List<String> top3score = new ArrayList<>();
    List<String> huonopeli = new ArrayList<>();
    List<String> templist = new ArrayList<>();

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
            BufferedReader reader = new BufferedReader(new FileReader("src/Tilastotiedostot/LastGame.txt"));
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
            BufferedReader reader = new BufferedReader(new FileReader("src/Tilastotiedostot/top3.txt"));
            
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
            
        }
        catch(Exception e){
            
        }
    }
    
}
