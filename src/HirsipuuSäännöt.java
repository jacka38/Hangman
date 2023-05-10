package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HirsipuuSäännöt {
    //Getting the rules for the hangman

    public HirsipuuSäännöt() {

        Tulostasäännöt();
    }

    private String säännöt;

    private void Tulostasäännöt() {

        StringBuilder rules = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("src/Säännöt.txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rules.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        säännöt = rules.toString();
    }

    public String GetSäännöt() {

        return säännöt;
    }

}