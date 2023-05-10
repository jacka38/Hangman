package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HirsipuuPäävalikko extends JFrame implements ActionListener {

   private JButton newGameButton, rulesButton, statsButton, exitButton;
   private JPanel cardPanel;
   private CardLayout cardLayout;
   public JComboBox kategorialista;
   private int incorrectGuess = 0;
   private int amountOfLettersGuessed = 0;
   private int ActualCorrect = 0;
   private int points = 0;
   private int guessesLeft;
   private String word;
   public ButtonGroup group;

   public HirsipuuPäävalikko() {
      setTitle("Hirsipuu");
      setSize(800, 700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Initialize UI components
      initUI();

      // Center the frame on the screen
      setLocationRelativeTo(null);

      // Make the frame visible
      setVisible(true);

      Dimension min = new Dimension(800, 700);
      setMinimumSize(min);
   }

   private void initUI() {
      //initialize GUI

      // Create a container panel to hold all the cards
      cardPanel = new JPanel(new CardLayout());
      HirsipuuTilastot tilastot = new HirsipuuTilastot();
      tilastot.setFileTilastot();
      cardLayout = (CardLayout) cardPanel.getLayout();
      cardPanel.add(createMenuCard(), "Menu");
      cardPanel.add(createNewGameCard(), "Uusi Peli");
      cardPanel.add(createRulesCard(), "Säännöt");
      cardPanel.add(createStatsCard(), "Tilastot");

      // Set the content pane of the JFrame to the container panel
      getContentPane().add(cardPanel);

      cardLayout.show(cardPanel, "Menu");

      // Set the size and visibility of the JFrame
      setSize(800, 700);
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
      JPanel menuPanel = new JPanel();
      menuPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();

      c.anchor = GridBagConstraints.CENTER;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(10, 0, 0, 0);
      c.gridx = 1;
      c.gridy = 1;
      c.weightx = 1;
      c.weighty = 0;
      menuPanel.add(titleLabel, c);

      JLabel label = new JLabel();
      Image img = new ImageIcon(this.getClass().getResource("HirsipuuKuvat/hirsipuu.gif")).getImage();
      Image newImage = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
      label.setIcon(new ImageIcon(newImage));
      c.insets = new Insets(10, 10, 0, 0);
      c.weighty = 0;
      c.gridy = 2;
      menuPanel.add(label, c); // Add the label containing the gif to the menuPanel

      c.insets = new Insets(10, 0, 0, 0);
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

      JLabel help = new JLabel(" ?");
      help.setForeground(Color.MAGENTA);
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

      JPanel difficultyTitle = new JPanel(new BorderLayout());
      JLabel difficultyLabel = new JLabel("Vaikeustasot:");

      difficultyTitle.add(difficultyLabel, BorderLayout.LINE_START);

      difficultyTitle.setToolTipText(
            "<html>" + "Vaikeustasot määrittävät montako arvausta saa mennä väärin" + "<br>" + "Helppo = 12"
                  + "<br>" + "Keskivaikea = 8" + "<br>" + "Vaikea = 4" + "<br>" + "Mahdoton = 1" + "</html>");
      difficultyTitle.add(help, BorderLayout.CENTER);

      c.gridy = 3;
      c.gridx = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      c.insets = new Insets(50, 0, 0, 0);
      newGameCard.add(difficultyTitle, c);

      c.gridx = 2;
      c.gridy = 3;
      c.insets = new Insets(50, 30, 0, 0);
      newGameCard.add(helppo, c);

      c.insets = new Insets(0, 30, 0, 0);
      c.gridy = 4;
      newGameCard.add(Keskivaikea, c);
      c.gridy = 5;
      newGameCard.add(Vaikea, c);

      c.gridy = 6;
      c.weighty = 1;
      newGameCard.add(Mahdoton, c);

      c.insets = new Insets(0, 0, 0, 0);
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
      //Create frame for the rules

      // Create the panel for the rules card
      JPanel rulesCard = new JPanel(new BorderLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JButton backButton = createBackButton();

      JLabel rulesTitle = createTitleLabel("Säännöt");
      rulesCard.add(rulesTitle, BorderLayout.NORTH);

      bottomPanel.add(backButton, BorderLayout.LINE_END);
      rulesCard.add(bottomPanel, BorderLayout.PAGE_END);

      HirsipuuSäännöt hirsipuuRules = new HirsipuuSäännöt();
      String rules = hirsipuuRules.GetSäännöt();
      JTextArea rulesTextArea = new JTextArea(rules);
      rulesTextArea.setEditable(false);
      rulesTextArea.setBackground(getBackground());
      rulesTextArea.setFont(new Font("ARIAL", Font.PLAIN, 14));

      // Put the text area inside a scroll pane
      JScrollPane scrollPane = new JScrollPane(rulesTextArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

      rulesCard.add(scrollPane, BorderLayout.CENTER);

      return rulesCard;
   }

   private JPanel createStatsCard() {
      // Create the frame for the stats

      JPanel statsCard = new JPanel(new GridBagLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JPanel top3Panel = new JPanel(new GridBagLayout());
      JPanel top3TitlePanel = new JPanel(new GridBagLayout());
      JPanel viimeisinpeliPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      JButton backButton = createBackButton();

      JLabel statsTitle = createTitleLabel("Tilastot");
      c.gridx = 0;
      c.gridy = 0;
      c.weightx = 0.5;
      c.weighty = 1;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      statsCard.add(statsTitle, c);
      HirsipuuTilastot tilastot = new HirsipuuTilastot();
      tilastot.createTextareas();
      JLabel top3pelitTitle = new JLabel("Top 3 pelit");
      top3pelitTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
      JLabel vikaTitle = new JLabel("Viimeisin peli");
      vikaTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
      JLabel top1title = new JLabel("Top 1:");
      top1title.setFont(new Font("ARIAL", Font.BOLD, 14));
      JLabel top2title = new JLabel("Top 2:");
      top2title.setFont(new Font("ARIAL", Font.BOLD, 14));
      JLabel top3title = new JLabel("Top 3:");
      top3title.setFont(new Font("ARIAL", Font.BOLD, 14));
      JTextArea top1 = tilastot.getTop1text();
      top1.setBackground(getBackground());
      JTextArea top2 = tilastot.getTop2text();
      top2.setBackground(getBackground());
      JTextArea top3 = tilastot.getTop3text();
      top3.setBackground(getBackground());
      JTextArea vika = tilastot.getVikatext();
      vika.setBackground(getBackground());

      c = new GridBagConstraints();

      c.gridx = 0;
      c.gridy = 0;
      c.weightx = 0.5;
      c.weighty = 1;
      c.anchor = GridBagConstraints.PAGE_START;
      viimeisinpeliPanel.add(vikaTitle, c);
      c.gridy = 1;
      c.weighty = 0;
      c.insets = new Insets(40, 0, 0, 0);
      viimeisinpeliPanel.add(vika, c);

      c.gridy = 1;
      c.weightx = 0.5;
      c.weighty = 1;
      c.anchor = GridBagConstraints.PAGE_START;
      statsCard.add(viimeisinpeliPanel, c);

      c = new GridBagConstraints();

      c.gridx = 0;
      c.gridy = 0;
      c.anchor = GridBagConstraints.PAGE_START;
      c.weightx = 0.5;
      c.weighty = 1;
      top3TitlePanel.add(top3pelitTitle, c);

      c.gridy = 2;
      c.weightx = 0.5;
      c.weighty = 1;
      c.anchor = GridBagConstraints.CENTER;
      c.insets = new Insets(50, 0, 40, 0);
      statsCard.add(top3TitlePanel, c);

      c = new GridBagConstraints();

      c.insets = new Insets(0, 0, 10, 0);
      c.gridx = 1;
      c.gridy = 2;
      c.weightx = 0.5;
      c.weighty = 0;
      top3Panel.add(top2title, c);

      c.gridy = 3;
      c.weightx = 0.5;
      top3Panel.add(top2, c);

      c = new GridBagConstraints();
      c.insets = new Insets(0, 0, 10, 0);
      c.gridx = 1;
      c.gridy = 0;
      c.weighty = 0;
      c.weightx = 0.5;

      top3Panel.add(top1title, c);

      c.gridy = 1;
      c.weightx = 0;
      top3Panel.add(top1, c);

      c = new GridBagConstraints();
      c.insets = new Insets(0, 0, 10, 0);
      c.gridx = 1;
      c.gridy = 4;
      c.weighty = 0;
      c.weightx = 0.5;
      top3Panel.add(top3title, c);

      c.gridy = 5;
      c.weightx = 0;
      top3Panel.add(top3, c);

      c = new GridBagConstraints();
      c.insets = new Insets(0, 0, 0, 0);
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.CENTER;
      c.gridy = 3;
      c.weightx = 1;
      c.weighty = 1;

      statsCard.add(top3Panel, c);

      c = new GridBagConstraints();
      c.gridy = 4;
      c.weightx = 0.5;
      c.weighty = 1;
      c.anchor = GridBagConstraints.LAST_LINE_END;
      statsCard.add(backButton, c);

      return statsCard;
   }

   private JPanel createPlayViewCard() {
      // set to zero for new game
      incorrectGuess = 0;
      amountOfLettersGuessed = 0;
      points = 0;
      // Create the panel for the playview
      JPanel playViewCard = new JPanel(new BorderLayout());
      JPanel bottomPanel = new JPanel(new BorderLayout());
      JButton backButton = createBackButton();
      HirsipuuHaeSana sananvalinta = new HirsipuuHaeSana();
      String Valittusana = sananvalinta.SanaTiedosto(kategorialista.getSelectedItem().toString());
      word = Valittusana;

      JLabel gameTitle = createTitleLabel("Hirsipuu");
      playViewCard.add(gameTitle, BorderLayout.NORTH);

      // Create the panel for the hangman lines and word lines
      JPanel picturePanel = new JPanel(new BorderLayout());
      JLabel imgLabel = new JLabel();
      Image img = new ImageIcon(this.getClass().getResource("HirsipuuKuvat/BaseTemplate.png")).getImage();
      int leveys = getWidth() -200;
      int korkeus = getHeight() -300;
      Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);

      imgLabel.setIcon(new ImageIcon(newImage));

      cardPanel.addComponentListener(new ComponentListener() {
         public void componentResized(ComponentEvent e) {
            int leveys = getWidth() - 200;
            int korkeus = getHeight() -300;
            Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);
            imgLabel.setIcon(new ImageIcon(newImage));
         }

         @Override
         public void componentMoved(ComponentEvent e) {
         }

         @Override
         public void componentShown(ComponentEvent e) {
         }

         @Override
         public void componentHidden(ComponentEvent e) {
         }

      });
      picturePanel.add(imgLabel, BorderLayout.CENTER);

      // Create the points counter label and add it to the left of the hangman image
      JLabel pointsLabel = new JLabel("Pisteet: 0");
      pointsLabel.setFont(new Font("Arial", Font.BOLD, 14));
      pointsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

      HirsipuuArvaukset määrä = new HirsipuuArvaukset();
      int arvaustenmäärä = määrä.valittuvaikeus(group);
      JLabel guessesLeftLabel = new JLabel("Vääriä arvauksia jäljellä: " + arvaustenmäärä);
      guessesLeftLabel.setFont(new Font("Arial", Font.BOLD, 14));
      guessesLeftLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

      // Create a JPanel that contains the pointsLabel and guessesLeftLabel
      JPanel scorePanel = new JPanel(new GridLayout(2, 1, 0, 10));
      scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      scorePanel.add(pointsLabel);
      scorePanel.add(guessesLeftLabel);

      JPanel gameInfo = new JPanel(new BorderLayout());
      gameInfo.add(scorePanel, BorderLayout.WEST);
      playViewCard.add(gameInfo, BorderLayout.NORTH);

      JPanel pictureContainer = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.weightx = 1.0;
      c.weighty = 1.0;
      pictureContainer.add(picturePanel, c);

      JPanel wordPanel = new JPanel();
      wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.X_AXIS));
      wordPanel.add(Box.createHorizontalGlue()); // add glue to center wordPanel horizontally

      int wordLength = Valittusana.length(); // Add the labels for each letter in the random word
      List<JLabel> labelList = addUnderscoreLabels(Valittusana, wordPanel); // Add the labels for each letter in the
                                                                            // random word

      wordPanel.add(Box.createHorizontalGlue()); // add glue to center wordPanel horizontally

      JPanel hangmanPanel = new JPanel(new BorderLayout());
      hangmanPanel.add(pictureContainer, BorderLayout.NORTH);
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

      addKeyboardButtonListeners(buttonPanel, Valittusana, labelList, imgLabel, pointsLabel, guessesLeftLabel);

      backButton.addActionListener(e -> {
         showConfirmationDialog(playViewCard);
      });

      return playViewCard;
   }

   private void showConfirmationDialog(JPanel playViewCard) {
      //Confirmation pop up if you leave the game

      String[] options = { "Kyllä, Palaa Päävalikkoon", "En, jatka peliä" };
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

   private void addKeyboardButtonListeners(JPanel buttonPanel, String word, List<JLabel> labelList, JLabel imgLabel,
         JLabel pointsLabel, JLabel guessesLeftLabel) {
      // Add ActionListener to each button in the keyboardPanel

      HirsipuuArvaukset määrä = new HirsipuuArvaukset();
      int arvaustenmäärä = määrä.valittuvaikeus(group);
      String[] imagePaths = { "HirsipuuKuvat/Hangman1.png", "HirsipuuKuvat/Hangman2.png", "HirsipuuKuvat/Hangman3.png",
            "HirsipuuKuvat/Hangman4.png",
            "HirsipuuKuvat/Hangman5.png", "HirsipuuKuvat/Hangman6.png", "HirsipuuKuvat/Hangman7.png",
            "HirsipuuKuvat/Hangman8.png",
            "HirsipuuKuvat/Hangman9.png", "HirsipuuKuvat/Hangman10.png", "HirsipuuKuvat/Hangman11.png",
            "HirsipuuKuvat/Hangman12.png" };

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
                     points += 5;
                     c.setBackground(Color.GREEN);
                     c.setEnabled(false);

                     pointsLabel.setText("Pisteet: " + points);
                     guessesLeft = arvaustenmäärä - incorrectGuess;
                     guessesLeftLabel.setText("Vääriä arvauksia jäljellä: " + guessesLeft);
                     amountOfLettersGuessed++;
                  }
               }
               if (letterFound) {
                  ActualCorrect++;
               }
               if (!letterFound) {
                  // Handle incorrect guess
                  points = Math.max(points - 2, 0); // remove 2 points for an incorrect guess but keep the points >= 0
                  incorrectGuess++;
                  guessesLeft = arvaustenmäärä - incorrectGuess;

                  // Update the hangman picture
                  int imageIndex = (incorrectGuess - 1) * (imagePaths.length - 1) / arvaustenmäärä;
                  if (imageIndex < imagePaths.length - 1) {
                     Image img = new ImageIcon(this.getClass().getResource(imagePaths[imageIndex])).getImage();
                     int leveys = getWidth() - 200;
                     int korkeus = getHeight() -300;
                     Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);
                     cardPanel.addComponentListener(new ComponentListener() {
                        public void componentResized(ComponentEvent e) {
                           int leveys = getWidth() - 200;
                           int korkeus = getHeight() -300;
                           Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);
                           imgLabel.setIcon(new ImageIcon(newImage));
                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) {
                        }
                     });
                     imgLabel.setIcon(new ImageIcon(newImage));
                  }

                  if (incorrectGuess > arvaustenmäärä) {
                     // Update the hangman picture to the last one
                     Image img = new ImageIcon(this.getClass().getResource(imagePaths[imagePaths.length - 1]))
                           .getImage();
                     int leveys = getWidth() - 200;
                     int korkeus = getHeight() -300;
                     Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);
                     cardPanel.addComponentListener(new ComponentListener() {
                        public void componentResized(ComponentEvent e) {
                           int leveys = getWidth() - 200;
                           int korkeus = getHeight() -300;
                           Image newImage = img.getScaledInstance(leveys, korkeus, Image.SCALE_FAST);
                           imgLabel.setIcon(new ImageIcon(newImage));
                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) {
                        }
                     });
                     imgLabel.setIcon(new ImageIcon(newImage));

                     gameOver(word, labelList);
                  }

                  pointsLabel.setText("Pisteet: " + points);
                  guessesLeftLabel.setText("Vääriä arvauksia jäljellä: " + guessesLeft);
                  c.setBackground(Color.GRAY);
                  c.setEnabled(false);
               } else {
                  checkIfWordIsComplete(word, amountOfLettersGuessed);
               }
            });
         }
      }
   }

   private void checkIfWordIsComplete(String word, int amountOfLettersGuessed) {
      // check if the word is completed

      boolean allReplaced = true;
      for (int j = 0; j < word.length(); j++) {
         if (word.charAt(j) == '_') {
            allReplaced = false;
            break;
         }
      }

      if (allReplaced) {
         boolean onlySpaces = true;
         for (int j = 0; j < word.length(); j++) {
            if (word.charAt(j) != ' ' && word.charAt(j) != '_') {
               onlySpaces = false;
               break;
            }
         }
         if (onlySpaces || amountOfLettersGuessed == word.replace(" ", "").length()) {
            gameWon();
         }
      }
   }

   private void gameWon() {
      // game won method

      HirsipuuTilastot tilastot = new HirsipuuTilastot();
      tilastot.setFileTilastot();
      tilastot.writeLastGame(points, word, kategorialista, group, incorrectGuess + ActualCorrect);
      tilastot.writeTop3List(points, word, kategorialista, group, incorrectGuess + ActualCorrect);
      HirsipuuSound win = new HirsipuuSound();
      win.setWinsound();
      win.play();

      ActualCorrect = 0;
      incorrectGuess = 0;
      amountOfLettersGuessed = 0;

      JDialog dialog = new JDialog();
      dialog.setUndecorated(true);
      dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
      dialog.setTitle("Voitit pelin");
      dialog.setModal(true);
      dialog.setResizable(false);

      JPanel messagePane = new JPanel();
      JLabel messageLabel = new JLabel("VOITIT! Sinulla oli " + points + " pistettä");
      messagePane.add(messageLabel);
      dialog.add(messagePane, BorderLayout.CENTER);
      points = 0; // reset points for future games

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
      try {
         win.stop();
      } catch (Exception e) {

      }
   }

   private void gameOver(String word, List<JLabel> labelList) {
      // game lost method


      // Set all the hidden letters in the word to be visible
      for (int i = 0; i < word.length(); i++) {
         labelList.get(i).setText(Character.toString(word.charAt(i)));
      }
      HirsipuuTilastot tilastot = new HirsipuuTilastot();
      tilastot.setFileTilastot();
      tilastot.writeLastGame(0, word, kategorialista, group, incorrectGuess + ActualCorrect);

      incorrectGuess = 0;
      amountOfLettersGuessed = 0;
      points = 0;
      ActualCorrect = 0;

      HirsipuuSound dead = new HirsipuuSound();
      dead.setSound();
      dead.play();

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
      try {
         dead.stop();
      } catch (Exception e) {
      }
   }

   private List<JLabel> addUnderscoreLabels(String word, JPanel panel) {
      // adding the underscores for all the letters in the word

      List<JLabel> labelList = new ArrayList<>();
      Font font = new Font("Arial", Font.BOLD, 24);
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         JLabel label;
         if (c == ' ') {
            label = new JLabel(" ");
         } else {
            label = new JLabel("_ ");
         }
         label.setFont(font);
         label.setHorizontalAlignment(SwingConstants.CENTER);
         panel.add(label);
         labelList.add(label);
      }
      return labelList;
   }

   private JPanel createKeyboardPanel() {
      // creating the keyboard layout for the playview

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