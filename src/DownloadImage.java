import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage {

    public static DataManager dMan = new DataManager();

    public static void main(String[] args) {
        dMan.loadCountries();
        for (Country c : dMan.countries) {
            String imageUrl = c.getFlagFilePath();

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                File directory = new File("resources/flags");
                for (File f : directory.listFiles()) {
                    if (f.getName().equals(c.getFlagFilePath())) {
                        continue;
                    }
                }

                File imageFile = new File("resources/flags/" + c.getName() + ".png");
                System.out.println(imageFile.createNewFile());

                URL url = new URL(imageUrl);
                inputStream = url.openStream();
                outputStream = new FileOutputStream(imageFile);

                byte[] buffer = new byte[2048];
                int length;

                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

} 