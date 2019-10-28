import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * handling a single question or displaying the hidden city name
 *
 * @author Anzu
 * @version 1.0
 */
public class Question {

    // cityName, underscores, cityList, wrongLetters, count as fields
    private String cityName;
    private String underscores = "";
    private List<String> cityList = new ArrayList<String>();
    private String wrongLetters;
    private int counts;

    // construction
    public Question() {
        // default constructor
        setCityName();
        wrongLetters = "";
        counts = 0;
    }

    /**
     * check if check if input is contained in city name
     * @return  true if contains, otherwise false
     */
    public boolean checkChar(String inputStr) {

        if (!cityName.contains(inputStr)){
            wrongLetters += inputStr + Properties.SPACE;
            counts ++;
            return false;
        }
        return true;
    }

    /**
     * fill the word int he underscores
     * @param inputChar
     * @return underscores
     */
    public String fillLetters(char inputChar) {
        int result = 0;
        StringBuilder sb = new StringBuilder(underscores);
        // switch the underscores to the letter
        result = cityName.indexOf(inputChar);
        for (int i = 0; i < cityName.length(); i++) {
            if (result == -1) {
                break;
            }
            sb.setCharAt(result*2, inputChar);
            // StringBuilder to String
            underscores = sb.toString();
            result = cityName.indexOf(inputChar, result +1);
        }
        return underscores;
    }

    /**
     * print messages
     * @param inputChar
     */
    public void printMsg(char inputChar) {
        System.out.println("You are guessing: " + fillLetters(inputChar));
        System.out.printf("You have guessed (%d) wrong letters: %s\n", counts, wrongLetters);

    }

    /**
     * set city name
     */
    public void setCityName() {
        try {
            FileInputStream is  = new FileInputStream(Properties.FILENAME);
            InputStreamReader in = new InputStreamReader(is,"UTF-8");
            BufferedReader inb = new BufferedReader(in);
            String line;
            // read the cities.text and put the cities to the array
            while ((line = inb.readLine()) != null) {
                cityList.add(line);
            }
            inb.close();
            in.close();
            is.close();
        }
        catch (IOException e) {
            System.err.println( e);
        }
        // get a city name randomly
        Random random = new Random();
        int randomValue = random.nextInt(cityList.size());
        cityName = cityList.get(randomValue);
    }

    // underscores "_" in place of the real letters
    public String replaceUnderscores() {
        // change city name to underscores
        for (int j = 0; j < getCityName().length(); j++){
            if (cityName.charAt(j) == Properties.SPACE){
                underscores += Properties.DOUBLESPACED;
            } else {
                underscores += Properties.UNDERSCORE;
                underscores += Properties.SPACE;
            }
        }
        return underscores;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCounts() {
        return counts;
    }
}
