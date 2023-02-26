import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    // TODO: COMPONENTS
    JButton option1, option2, option3, option4;
    JLabel flagImageLabel;
    JLabel scoreLabel;
    JLabel regionLabel;

    // TODO: UTILITIES
    public DataManager dMan = new DataManager();

    // TODO: GENERAL FIELDS
    Country currentCountry;
    int score = 0;

    // TODO: IMAGES
    BufferedImage bgImage;

    public MainPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(1000, 600));
        setBackground(new Color(67, 67, 67));

        dMan.loadCountries();

        // TODO: SETUP COMPONENTS
        setupComponents();
        addListeners();

        nextQuestion();

        try {
            bgImage = ImageIO.read(new File("resources/background2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupComponents() {
        flagImageLabel = new JLabel(dMan.countries.get(0).getFlag());
        flagImageLabel.setBounds((int) (getPreferredSize().getWidth() / 2) - 160, 100, 320, 160);
        add(flagImageLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setPreferredSize(new Dimension(400, 200));
        optionsPanel.setBackground(new Color(0, 0, 0, 0));
        optionsPanel.setBounds((int) (getPreferredSize().getWidth() / 2) - 200, 300, 400, 200);
        add(optionsPanel);

        option1 = new JButton();
        option1.setPreferredSize(new Dimension(180, 60));
        option1.setFocusable(false);
        option1.setBackground(new Color(34, 34, 34));
        option1.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        optionsPanel.add(option1, gbc);

        option2 = new JButton();
        option2.setPreferredSize(new Dimension(180, 60));
        option2.setFocusable(false);
        option2.setBackground(new Color(34, 34, 34));
        option2.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        optionsPanel.add(option2, gbc);

        option3 = new JButton();
        option3.setPreferredSize(new Dimension(180, 60));
        option3.setFocusable(false);
        option3.setBackground(new Color(34, 34, 34));
        option3.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        optionsPanel.add(option3, gbc);

        option4 = new JButton();
        option4.setPreferredSize(new Dimension(180, 60));
        option4.setFocusable(false);
        option4.setBackground(new Color(34, 34, 34));
        option4.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        optionsPanel.add(option4, gbc);

        scoreLabel = new JLabel("Score: " + score, JLabel.CENTER);
        scoreLabel.setBounds((int) (getPreferredSize().getWidth() / 2) - 100, 10, 200, 50);
        scoreLabel.setFont(new Font("DIALOG", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(0, 0, 0, 100));
        add(scoreLabel);

        regionLabel = new JLabel("Region: ???", JLabel.CENTER);
        regionLabel.setBounds((int) (getPreferredSize().getWidth() / 2) - 100, 250, 200, 50);
        regionLabel.setForeground(Color.WHITE);
        regionLabel.setFont(new Font("DIALOG", Font.ITALIC, 10));
        add(regionLabel);
    }

//    public Country randCountry(ArrayList<Country> countries) {
//        int r = (int) (Math.random() * countries.size());
//        return countries.get(r);
//    }

    public void nextQuestion() {
        ArrayList<Country> countries = (ArrayList<Country>) dMan.countries.clone();
        int r = (int) (Math.random() * dMan.countries.size());
        currentCountry = countries.remove(r);

        flagImageLabel.setIcon(currentCountry.getFlag());
        //flagImageLabel.setText(currentCountry.getName());

        option1.setText(countries.remove((int) (Math.random() * countries.size())).getName());
        option2.setText(countries.remove((int) (Math.random() * countries.size())).getName());
        option3.setText(countries.remove((int) (Math.random() * countries.size())).getName());
        option4.setText(countries.remove((int) (Math.random() * countries.size())).getName());

        regionLabel.setText("Region: " + currentCountry.getRegion());

        switch ((int) (Math.random() * 4) + 1) {
            case 1:
                option1.setText(currentCountry.getName());
                break;
            case 2:
                option2.setText(currentCountry.getName());
                break;
            case 3:
                option3.setText(currentCountry.getName());
                break;
            case 4:
                option4.setText(currentCountry.getName());
                break;
        }
    }

    public void addListeners() {
        option1.addActionListener(new optionButtonListener());
        option2.addActionListener(new optionButtonListener());
        option3.addActionListener(new optionButtonListener());
        option4.addActionListener(new optionButtonListener());
    }

    public class optionButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getText().equals(currentCountry.getName())) {
                nextQuestion();
                System.out.println("Correct");
                score++;
                scoreLabel.setText("Score: " + score);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect");
                System.out.println("Incorrect");
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // BACKGROUND
        try {
            g.drawImage(bgImage, 0, 0, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Flag Stuff Frame
        Rectangle flagStuffFrame = flagImageLabel.getBounds();
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRoundRect(flagStuffFrame.x - 10, flagStuffFrame.y - 10, flagStuffFrame.width + 20, flagStuffFrame.height + 60, 20, 20);
        g.setColor(new Color(34, 34, 34, 255));
        g.fillRoundRect(flagStuffFrame.x - 5, flagStuffFrame.y - 5, flagStuffFrame.width + 10, flagStuffFrame.height + 50, 20, 20);

        repaint();
    }
}
