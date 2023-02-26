import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataManager {

    ArrayList<Country> countries = new ArrayList<Country>();

    public DataManager() {

    }

    public void loadCountries() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("resources/countryData.csv"));

            String line = in.readLine();
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");
                countries.add(new Country(Integer.parseInt(data[0]), data[1], data[2], data[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = countries.size() - 1; i > 0; i--) {
            if (countries.get(i).getFlag() == null) {
                System.out.print("Removed " + countries.get(i).getName());
                countries.remove(i);
                System.out.print(": flag = null\n");
            }
        }
    }
}
