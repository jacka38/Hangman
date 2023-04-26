package src;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class HirsipuuArvaukset {

    public HirsipuuArvaukset(){


    }
    


    public int valittuvaikeus(ButtonGroup group){

        String vaikeustaso ="";
        int arvaustenmäärä = 7;

        for(Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()){
                vaikeustaso = button.getText();
            }
        }

        switch (vaikeustaso) {
            case "Helppo":
                arvaustenmäärä = 7;
               break;
   
            case "Keskivaikea":
                arvaustenmäärä = 5;
               break;
   
            case "Vaikea":
                arvaustenmäärä = 3;
               break;
   
            case "Mahdoton":
                arvaustenmäärä = 1;
               break;
   

            default:
               break;
         }

         return arvaustenmäärä;
    }
}