package src;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class HirsipuuPeliAsetukset { //Näkymä, josta valitaan asetukset pelille

    final String[] Kategoriat = {"Satunnainen", "Maat", "Automerkit", "Jaskanseksifantasiat"};
    public HirsipuuPeliAsetukset() {

    }

    final JRadioButton helppo = new JRadioButton("Helppo");;
    final JRadioButton Keskivaikea = new JRadioButton("Keskivaikea");;
    final JRadioButton Vaikea  = new JRadioButton("Vaikea");;
    final JRadioButton Mahdoton = new JRadioButton("Mahdoton"); ;
    
    public JComboBox LuoKategoriat(){

        JComboBox kategorialista = new JComboBox(Kategoriat);
       
        return kategorialista;
    }


    public JRadioButton getHelppo() {
        return helppo;
    }


    public JRadioButton getKeskivaikea() {
        return Keskivaikea;
    }



    public JRadioButton getVaikea() {
        return Vaikea;
    }


    public JRadioButton getMahdoton() {
        return Mahdoton;
    }
    
}