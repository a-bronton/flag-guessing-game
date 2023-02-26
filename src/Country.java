import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class Country {

    private int id;
    private String name, region;
    private ImageIcon flag;
    private String flagFilePath;

    public Country(int id, String name, String region, String flagImagePath) {
        this.id = id;
        this.name = name;
        this.region = region;

        try {
            boolean fileFound = false;
            File dir = new File("resources/flags");
            for (File f : dir.listFiles()) {
                if (f.getName().equals(name + ".png")) {
                    fileFound = true;
                }
            }
            if (fileFound) {
                flag = new ImageIcon("resources/flags/" + name + ".png");
                Image scaled = flag.getImage().getScaledInstance(320, 160, Image.SCALE_DEFAULT);
                flag = new ImageIcon(scaled);
            } else {
                flag = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        flagFilePath = flagImagePath;
    }

    public ImageIcon getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getFlagFilePath() {
        return flagFilePath;
    }

    public String getRegion() {
        return region;
    }
}
