

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

   public HirsipuuPaavalikko() {
      setTitle("Hirsipuu");
      setSize(600, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Initialize UI components
      initUI();

      // Center the frame on the screen
      setLocationRelativeTo(null);

      /*
       **** THIS IS BROKEN ATM, IT DOESNT RECENTER CORRECTLY WHEN COMING BACK TO MENU
       * PAGE AND IS BUGGY****
       * // Add a component listener to the frame to handle window resizing
       * addComponentListener(new ComponentAdapter() {
       * public void componentResized(ComponentEvent e) {
       * // Center the buttons horizontally in the panel
       * centerMenuButtons();
       * }
       * });
       */

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
      JPanel menuCard = new JPanel();

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
      newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      rulesButton.setMaximumSize(largestButtonSize);
      rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      statsButton.setMaximumSize(largestButtonSize);
      statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      exitButton.setMaximumSize(largestButtonSize);
      exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Add action listeners to the buttons
      newGameButton.addActionListener(this);
      rulesButton.addActionListener(this);
      statsButton.addActionListener(this);
      exitButton.addActionListener(this);

      // Create the panel for the menu card
      menuPanel = new JPanel();
      menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
      menuPanel.add(Box.createVerticalGlue()); // Add vertical glue to center the buttons

      menuPanel.add(titleLabel);
      menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

      JLabel label = new JLabel();
      Image img = new ImageIcon(this.getClass().getResource("hirsipuu1.gif")).getImage();
      Image newImage = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
      label.setIcon(new ImageIcon(newImage));
      label.setAlignmentX(Component.CENTER_ALIGNMENT);
      menuPanel.add(label); // Add the label containing the gif to the menuPanel

      menuPanel.add(newGameButton);
      menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

      menuPanel.add(rulesButton);
      menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

      menuPanel.add(statsButton);
      menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

      menuPanel.add(exitButton);
      menuPanel.add(Box.createVerticalGlue()); // Add vertical glue to center the buttons

      menuCard.add(menuPanel, BorderLayout.CENTER);

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
      ButtonGroup group = new ButtonGroup();
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

      return playViewCard;
   }

   private void addKeyboardButtonListeners(JPanel buttonPanel, String word, List<JLabel> labelList) {
      // Add ActionListener to each button in the keyboardPanel
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
                  }
               }
               if (!letterFound) {
                  // Handle incorrect guess
                  int incorrectGuess = 0;
                  incorrectGuess++;

                  c.setBackground(Color.GRAY);
                  c.setEnabled(false);
               }
            });
         }
      }
   }
  
  private List<JLabel> addUnderscoreLabels(String word, JPanel panel) {
      List<JLabel> labelList = new ArrayList<>();
      for (int i = 0; i < word.length(); i++) {
          JLabel label = new JLabel("_ ");
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


   

   /*
    **** THIS IS BROKEN ATM, IT DOESNT RECENTER CORRECTLY WHEN COMING BACK TO MENU
    * PAGE AND IS BUGGY****
    * private void centerMenuButtons() {
    * //Center the menu when resizing
    * Dimension contentPaneSize = getContentPane().getSize();
    * Dimension buttonPanelSize = ((JPanel)
    * getContentPane().getComponent(0)).getPreferredSize();
    * 
    * int buttonPanelY = (contentPaneSize.height - buttonPanelSize.height) / 2;
    * 
    * ((JPanel)
    * getContentPane().getComponent(0)).setAlignmentY(Component.CENTER_ALIGNMENT);
    * ((JPanel) getContentPane().getComponent(0)).setLocation(0, buttonPanelY);
    * }
    */

}