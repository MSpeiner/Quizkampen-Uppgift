package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    // vi har skapat 2 metoder. antalomgångar och antalfrågor.
    public int antalOmgangar() {
        int antalOmgangarInt = 1; // default värde ifall vi inte kan läsa properties filen
        try (InputStream inputFil = new FileInputStream("src/Utils/quizkampen.properties")) {

            Properties properties = new Properties();
            // läser in propeties-filen till min properties variabel
            properties.load(inputFil);

            // returnera antal omgångar
            String antalOmgangarString = properties.getProperty("antalOmgangar");
            // konverterar till int
            antalOmgangarInt = Integer.parseInt(antalOmgangarString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return antalOmgangarInt;
    }


    public int antalFragor() {
        int antalFragaInt = 1; // default värde ifall vi inte kan läsa properties filen
        try (InputStream inputFil = new FileInputStream("src/Utils/quizkampen.properties")) {

            Properties properties = new Properties();
            // läser in propeties-filen till min properties variabel
            properties.load(inputFil);

            // returnera antal frågor
            String antalOmgangarString = properties.getProperty("antalFragaPerOmgang");
            // konverterar till int
            antalFragaInt = Integer.parseInt(antalOmgangarString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return antalFragaInt;
    }
}
