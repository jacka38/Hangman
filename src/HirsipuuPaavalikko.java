package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HirsipuuPaavalikko extends JFrame implements ActionListener {
   private JButton newGameButton, rulesButton, statsButton, exitButton;
   private JPanel cardPanel, menuPanel;
   private CardLayout cardLayout;
   public JComboBox kategorialista;
   private int incorrectGuess = 0;
   private int correctGuess = 0;
   public ButtonGroup group;

   public HirsipuuPaavalikko() {
      setTitle("Hirsipuu");
      setSize(600, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Initialize UI components
      initUI();

      // Center the frame on the screen
      setLocationRelativeTo(null);


      // Make the frame visible
      setVisible(true);
   }

   private void initUI() {

      // Create a container panel to hold all the cards
      cardPanel = new JPanel(new CardLayout());
      cardLayout = (CardLayout) cardPanel.getLayout();
      cardPanel.add(createMenuCard(), "Menu");
      cardPanel.add(createNewGameCard(), "Uusi Peli");
      cardPanel.add(createRulesCard(), "Säännöt");
      cardPanel.add(createStatsCard(), "Tilastot");

      // Set the content pane of the JFrame to the container panel
      getContentPane().add(cardPanel);

      cardLayout.show(cardPanel, "Menu");

      // Set the size and visibility of the JFrame
      setSize(600, 500);
      setVisible(true);
   }

   private JPanel createMenuCard() {
      // Create the panel for the new game card
      JPanel menuCard = new JPanel(new GridBagLayout());

      // Create the title label
      JLabel titleLabel = new JLabel("HANGMAN");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
      titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Create the buttons
      newGameButton = new JButton("Uusi Peli");
      rulesButton = new JButton("Säännöt");
      statsButton = new JButton("Tilastot");
      exitButton = new JButton("Poistu");

      // Set maximum size and alignment for buttons
      Dimension largestButtonSize = new Dimension(200, newGameButton.getPreferredSize().height);
      newGameButton.setMaximumSize(largestButtonSize);
      rulesButton.setMaximumSize(largestButtonSize);
      statsButton.setMaximumSize(largestButtonSize);
      exitButton.setMaximumSize(largestButtonSize);

      // Add action listeners to the buttons
      newGameButton.addActionListener(this);
      rulesButton.addActionListener(this);
      statsButton.addActionListener(this);
      exitButton.addActionListener(this);

      // Create the panel for the menu card
      menuPanel = new JPanel();
      menuPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();  

      c.anchor = GridBagConstraints.CENTER;
      c.fill  = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(10, 0,0,0);
      c.gridx = 1;
      c.gridy = 1;
      c.weightx = 1;
      c.weighty = 0;
      menuPanel.add(titleLabel, c);

      JLabel label = new JLabel();
      Image img = new ImageIcon(this.getClass().getResource("hirsipuu2.gif")).getImage();
      Image newImage = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
      label.setIcon(new ImageIcon(newImage));
      c.insets = new Insets(10, 10,0,0);
      c.weighty = 0;
      c.gridy = 2;
      menuPanel.add(label, c); // Add the label containing the gif to the menuPanel

      c.insets = new Insets(10, 0,0,0);
      c.gridy = 3;
      c.weightx = 0.5;
      menuPanel.add(newGameButton, c);
      
      c.gridy = 4;
      c.weightx = 0.5;
      menuPanel.add(rulesButton, c);
   
      c.gridy = 5;
      c.weightx = 0.5;
      menuPanel.add(statsButton, c);
     
      c.gridy = 6;
      c.weightx = 0.5;
      c.weighty = 1;
      menuPanel.add(exitButton, c);
      
      c = new GridBagConstraints();
      c.gridheight = 2;
      c.fill = GridBagConstraints.BOTH;
      c.weightx = 0;
      c.weighty = 0;
      menuCard.add(menuPanel, c);

      return menuCard;
   }


   private JButton createBackButton() {
      // Create the back to main menu button
      JButton backButton = new JButton("Palaa päävalikkoon");
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cardPanel.add(createMenuCard(), "MainMenu");
            cardLayout.show(cardPanel, "MainMenu");
         }
      });
      return backButton;
   }

   private JButton createStartButton() {
      // Create the back to main menu button
      JButton startButton = new JButton("Aloita peli");
      startButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cardPanel.add(createPlayViewCard(), "PlayView");
            cardLayout.show(cardPanel, "PlayView");
         }
      });
      return startButton;
   }

   private JLabel createTitleLabel(String titleText) {
      // Create Title label for the cards
      JLabel titleLabel = new JLabel(titleText);
      titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
      titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      return titleLabel;
   }

   private JPanel createNewGameCard() {
      // Create the panel for the new game card
      JPanel newGameCard = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      JButton backButton = createBackButton();
      JButton startButton = createStartButton();

      JLabel gameSettingsTitle = createTitleLabel("Peli asetukset");
      c.weighty = 1;
      c.weightx = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      c.gridx = 0;
      c.gridy = 0;
      newGameCard.add(gameSettingsTitle, c);

      JLabel categoryTitle = new JLabel("Kategoriat:");

      c.gridy = 1;
      c.gridx = 1;
      c.weightx = 0;
      c.weighty = 0;


      newGameCard.add(categoryTitle, c);

      HirsipuuPeliAsetukset peliasetukset = new HirsipuuPeliAsetukset();

      kategorialista = peliasetukset.LuoKategoriat();
      kategorialista.setSelectedIndex(0);

      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      c.gridx = 2;
      c.gridy = 1;
      c.weighty = 0;
      kategorialista.addActionListener(this);
      newGameCard.add(kategorialista, c);
      

      JRadioButton helppo = peliasetukset.getHelppo();
      JRadioButton Keskivaikea = peliasetukset.getKeskivaikea();
      JRadioButton Vaikea = peliasetukset.getVaikea();
      JRadioButton Mahdoton = peliasetukset.getMahdoton();
      group = new ButtonGroup();
      group.add(helppo);
      group.add(Keskivaikea);
      group.add(Vaikea);
      group.add(Mahdoton);
      helppo.addActionListener(this);
      Keskivaikea.addActionListener(this);
      Vaikea.addActionListener(this);
      Mahdoton.addActionListener(this);

      JLabel vaikeustasotitle = new JLabel("Vaikeustasot:");

      c.gridy = 3;
      c.gridx = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      c.insets = new Insets(50, 0,0,0);
      newGameCard.add(vaikeustasotitle, c);
      c.gridx = 2;
      c.gridy = 3;
      c.insets = new Insets(50, 10,0,0);
      newGameCard.add(helppo, c);

      c.insets = new Insets(0, 10,0,0);
      c.gridy = 4;
      newGameCard.add(Keskivaikea, c);
      c.ipady = 0;
      c.gridy = 5;
      newGameCard.add(Vaikea, c);

      c.gridy = 6;
      c.weighty = 1;
      newGameCard.add(Mahdoton, c);

      c.insets = new Insets(0, 0,0,0);
      c.gridx = 4;
      c.gridy = 10;
      c.weightx = 1;
      c.fill = GridBagConstraints.LAST_LINE_END;
      c.anchor = GridBagConstraints.LAST_LINE_END;
      newGameCard.add(backButton, c);

      c.gridx = 0;
      c.gridy = 10;
      c.fill = GridBagConstraints.LAST_LINE_START;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      newGameCard.add(startButton, c);

      return newGameCard;
   }

   private JPanel createRulesCard() {

      // Create the panel for the rules card
      JPanel rulesCard = new JPanel(new BorderLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JButton backButton = createBackButton();

      JLabel rulesTitle = createTitleLabel("Säännöt");
      rulesCard.add(rulesTitle, BorderLayout.NORTH);

      bottomPanel.add(backButton, BorderLayout.LINE_END);
      rulesCard.add(bottomPanel, BorderLayout.PAGE_END);

      HirsipuuSäännöt jtn = new HirsipuuSäännöt();
      String rules = jtn.GetSäännöt();
      JTextArea rulesTextArea = new JTextArea(rules);
      rulesTextArea.setEditable(false);
      rulesTextArea.setBackground(getBackground());
      rulesTextArea.setFont(new Font("SansSerif", Font.PLAIN, 12));

      // Put the text area inside a scroll pane
      JScrollPane scrollPane = new JScrollPane(rulesTextArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

      rulesCard.add(scrollPane, BorderLayout.CENTER);

      return rulesCard;
   }

   private JPanel createStatsCard() {
      // Create the panel for the stats card

      JPanel statsCard = new JPanel(new BorderLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JButton backButton = createBackButton();

      JLabel statsTitle = createTitleLabel("Tilastot");
      statsCard.add(statsTitle, BorderLayout.NORTH);

      bottomPanel.add(backButton, BorderLayout.LINE_END);
      statsCard.add(bottomPanel, BorderLayout.PAGE_END);

      return statsCard;
   }


   private JPanel createPlayViewCard() {
      // Create the panel for the stats card
      JPanel playViewCard = new JPanel(new BorderLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JButton backButton = createBackButton();
      HirsipuuHaeSana sananvalinta = new HirsipuuHaeSana();
      String Valittusana = sananvalinta.SanaTiedosto(kategorialista.getSelectedItem().toString());

      JLabel gameTitle = createTitleLabel("Hirsipuu");
      playViewCard.add(gameTitle, BorderLayout.NORTH);

      // Create the panel for the hangman lines and word lines
      JPanel wordPanel = new JPanel();
      wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.X_AXIS));
      wordPanel.add(Box.createHorizontalGlue()); // add glue to center wordPanel horizontally

      int wordLength = Valittusana.length(); // Add the labels for each letter in the random word
      List<JLabel> labelList = addUnderscoreLabels(Valittusana, wordPanel); // Add the labels for each letter in the random word

      wordPanel.add(Box.createHorizontalGlue()); // add glue to center wordPanel horizontally

      JPanel hangmanPanel = new JPanel(new BorderLayout());
      hangmanPanel.add(wordPanel, BorderLayout.CENTER);

      JPanel buttonPanel = createKeyboardPanel();
      JPanel wordAndBottomPanel = new JPanel(new BorderLayout());
      wordAndBottomPanel.add(wordPanel, BorderLayout.CENTER);
      wordAndBottomPanel.add(bottomPanel, BorderLayout.SOUTH);

      hangmanPanel.add(wordAndBottomPanel, BorderLayout.CENTER);
      hangmanPanel.add(buttonPanel, BorderLayout.SOUTH);

      playViewCard.add(hangmanPanel, BorderLayout.CENTER);

      bottomPanel.add(backButton, BorderLayout.LINE_END);
      playViewCard.add(bottomPanel, BorderLayout.PAGE_END);

      addKeyboardButtonListeners(buttonPanel, Valittusana, labelList);
      
      backButton.addActionListener(e -> {
         showConfirmationDialog(playViewCard);
      });

      return playViewCard;
   }

   private void showConfirmationDialog(JPanel playViewCard) {
      String[] options = {"Kyllä, Palaa Päävalikkoon", "En, jatka peliä"};
      int confirmed = JOptionPane.showOptionDialog(playViewCard,
              "Haluatko varmasti keskeyttää pelin ja palata takaisin päävalikkoon?",
              "",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);
  
      if (confirmed == JOptionPane.YES_OPTION) {
          JPanel menuCard = createMenuCard();
          cardPanel.add(menuCard, "MainMenu");
          cardLayout.show(cardPanel, "MainMenu");
      } else if (confirmed == JOptionPane.NO_OPTION) {
          JDialog dialog = (JDialog) JOptionPane.getRootFrame().getOwnedWindows()[0];
          dialog.addWindowListener(new WindowAdapter() {
              @Override
              public void windowClosing(WindowEvent e) {
                  JDialog dialog = (JDialog) e.getWindow();
                  dialog.dispose();
              }
          });
      } else if (confirmed == JOptionPane.CLOSED_OPTION) {
          JDialog dialog = (JDialog) JOptionPane.getRootFrame().getOwnedWindows()[0];
          dialog.addWindowListener(new WindowAdapter() {
              @Override
              public void windowClosing(WindowEvent e) {
                  JDialog dialog = (JDialog) e.getWindow();
                  dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
              }
          });
      }
  }

   private void addKeyboardButtonListeners(JPanel buttonPanel, String word, List<JLabel> labelList) {
      // Add ActionListener to each button in the keyboardPanel


      HirsipuuArvaukset määrä = new HirsipuuArvaukset();
      int arvaustenmäärä = määrä.valittuvaikeus(group);

      for (Component c : buttonPanel.getComponents()) {
         if (c instanceof JButton) {
            ((JButton) c).addActionListener(e -> {
               String letter = ((JButton) c).getText();
               boolean letterFound = false;
               for (int i = 0; i < word.length(); i++) {
                  if (word.charAt(i) == letter.toLowerCase().charAt(0) ||
                        word.charAt(i) == letter.toUpperCase().charAt(0)) {
                     labelList.get(i).setText(letter);
                     letterFound = true;
                     c.setBackground(Color.GREEN);
                     c.setEnabled(false);

                     correctGuess++;
                  }
               }
               if (!letterFound) {
                  // Handle incorrect guess
                  incorrectGuess++;

                  if(incorrectGuess > arvaustenmäärä){
                     gameOver();
                  }

                  c.setBackground(Color.GRAY);
                  c.setEnabled(false);
               }else {
                  if(correctGuess == word.length()){
                     gameWon();
                  }
               }
            });
         }
      }
   }

   private void gameWon() {

      incorrectGuess = 0;
      correctGuess = 0;
      JDialog dialog = new JDialog();
      dialog.setUndecorated(true);
      dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
      dialog.setTitle("Voitit pelin");
      dialog.setModal(true);
      dialog.setResizable(false);
   
      JPanel messagePane = new JPanel();
      JLabel messageLabel = new JLabel("VOITIT! Game Won!");
      messagePane.add(messageLabel);
      dialog.add(messagePane, BorderLayout.CENTER);
   
      JPanel buttonPane = new JPanel();
      JButton playAgainButton = new JButton("Pelaa uudelleen");
      playAgainButton.addActionListener(e -> {
         JPanel newGameCard = createNewGameCard();
         cardPanel.add(newGameCard, "createNewGameCard");
         cardLayout.show(cardPanel, "createNewGameCard");
         dialog.dispose();
      });
      buttonPane.add(playAgainButton);
   
      JButton mainMenuButton = new JButton("Palaa päävalikkoon");
      mainMenuButton.addActionListener(e -> {
         JPanel menuCard = createMenuCard();
         cardPanel.add(menuCard, "MainMenu");
         cardLayout.show(cardPanel, "MainMenu");
         dialog.dispose();
      });
      buttonPane.add(mainMenuButton);
   
      dialog.add(buttonPane, BorderLayout.SOUTH);
      dialog.pack();
      dialog.setLocationRelativeTo(null);
      dialog.setVisible(true);
   }


   private void gameOver() {

      incorrectGuess = 0;
      correctGuess = 0;
      JDialog dialog = new JDialog();
      dialog.setUndecorated(true);
      dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
      dialog.setTitle("Peli ohi");
      dialog.setModal(true);
      dialog.setResizable(false);

      JPanel messagePane = new JPanel();
      JLabel messageLabel = new JLabel("HÄVISIT! Peli ohi!");
      messagePane.add(messageLabel);
      dialog.add(messagePane, BorderLayout.CENTER);

      JPanel buttonPane = new JPanel();
      JButton playAgainButton = new JButton("Pelaa uudelleen");
      playAgainButton.addActionListener(e -> {
         JPanel newGameCard = createNewGameCard();
         cardPanel.add(newGameCard, "createNewGameCard");
         cardLayout.show(cardPanel, "createNewGameCard");
         dialog.dispose();
      });
      buttonPane.add(playAgainButton);

      JButton mainMenuButton = new JButton("Palaa päävalikkoon");
      mainMenuButton.addActionListener(e -> {
         JPanel menuCard = createMenuCard();
         cardPanel.add(menuCard, "MainMenu");
         cardLayout.show(cardPanel, "MainMenu");
         dialog.dispose();
      });
      buttonPane.add(mainMenuButton);

      dialog.add(buttonPane, BorderLayout.SOUTH);
      dialog.pack();
      dialog.setLocationRelativeTo(null);
      dialog.setVisible(true);
   }
  
  
  
  private List<JLabel> addUnderscoreLabels(String word, JPanel panel) {
      List<JLabel> labelList = new ArrayList<>();
      Font font = new Font("Arial", Font.BOLD, 24);
      for (int i = 0; i < word.length(); i++) {
          JLabel label = new JLabel("_ ");
          label.setFont(font);
          label.setHorizontalAlignment(SwingConstants.CENTER);
          panel.add(label);
          labelList.add(label);
      }
      return labelList;
  }

   private JPanel createKeyboardPanel() {
      String row1 = "1234567890";
      String row2 = "QWERTYUIOPÅ";
      String row3 = "ASDFGHJKLÖÄ";
      String row4 = "ZXCVBNM";

      String[] rows = { row1, row2, row3, row4 };
      JPanel buttonPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.weightx = 1.0;
      c.weighty = 0;
      c.fill = GridBagConstraints.BOTH;
      for (int i = 0; i < rows.length; i++) {
         char[] keys = rows[i].toCharArray();
         for (int j = 0; j < keys.length; j++) {
            JButton button = new JButton(Character.toString(keys[j]));
            c.gridx = j;
            c.gridy = i;
            buttonPanel.add(button, c);
         }
      }

      return buttonPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // Get the command from the action event
      String command = e.getActionCommand();
      // Switch to the appropriate card based on the command

      switch (command) {
         case "Uusi Peli":
            cardPanel.add(createNewGameCard(), "NewGame");
            cardLayout.show(cardPanel, "NewGame");
            break;

         case "Säännöt":
            cardPanel.add(createRulesCard(), "Rules");
            cardLayout.show(cardPanel, "Rules");
            break;

         case "Tilastot":
            cardPanel.add(createStatsCard(), "Stats");
            cardLayout.show(cardPanel, "Stats");
            break;

         case "Poistu":
            System.exit(0);
            break;

         case "Päävalikko":
            cardPanel.add(createMenuCard(), "MainMenu");
            cardLayout.show(cardPanel, "MainMenu");
            break;

         default:
            break;
      }
   }

}