import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HirsipuuPaavalikko extends JFrame implements ActionListener {
   private JButton newGameButton, rulesButton, statsButton, exitButton;
   private JPanel cardPanel, menuPanel;
   private CardLayout cardLayout;

   public HirsipuuPaavalikko() {
      setTitle("Hirsipuu");
      setSize(600, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Initialize UI components
      initUI();

      // Center the frame on the screen
      setLocationRelativeTo(null);

      // Add a component listener to the frame to handle window resizing
      addComponentListener(new ComponentAdapter() {
         public void componentResized(ComponentEvent e) {
            // Center the buttons horizontally in the panel
            centerMenuButtons();
         }
      });

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
      Image img = new ImageIcon(this.getClass().getResource("hirsipuu.gif")).getImage();
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

   private JPanel createNewGameCard() {
      // Create the panel for the new game card
      JPanel newGameCard = new JPanel();
      newGameCard.add(new JLabel("Uusi Peli"));

      return newGameCard;
   }

   private JPanel createRulesCard() {
      // Create the panel for the rules card
      JPanel rulesCard = new JPanel();
      rulesCard.add(new JLabel("Säännöt"));
      JButton backButton = new JButton("Palaa päävalikkoon");
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cardPanel.add(createMenuCard(), "MainMenu");
            cardLayout.show(cardPanel, "MainMenu");
         }
      });

      rulesCard.add(backButton);


   
      HirsipuuSäännöt jtn = new HirsipuuSäännöt();
      String rules = jtn.GetSäännöt();
      JTextArea  rulesTextArea = new JTextArea(rules);
      rulesTextArea.setEditable(false);
      rulesCard.add(rulesTextArea);
      return rulesCard;
   }


   private JPanel createStatsCard() {
      // Create the panel for the stats card
      JPanel statsCard = new JPanel();
      statsCard.add(new JLabel("Tilastot"));
      return statsCard;
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

   private void centerMenuButtons() {
      //Center the menu when resizing
      Dimension contentPaneSize = getContentPane().getSize(); 
      Dimension buttonPanelSize = ((JPanel) getContentPane().getComponent(0)).getPreferredSize();
      
      int buttonPanelY = (contentPaneSize.height - buttonPanelSize.height) / 2;
      
      ((JPanel) getContentPane().getComponent(0)).setAlignmentY(Component.CENTER_ALIGNMENT);
      ((JPanel) getContentPane().getComponent(0)).setLocation(0, buttonPanelY);
  }
   
}