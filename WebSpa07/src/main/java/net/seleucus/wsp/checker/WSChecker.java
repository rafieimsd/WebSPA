package main.java.net.seleucus.wsp.checker;

/**
 *
 * @author masoud
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.java.net.seleucus.wsp.crypto.ActionNumberCrypto;
import main.java.net.seleucus.wsp.crypto.PassPhraseCrypto;
import main.java.net.seleucus.wsp.main.WSGestalt;
import main.java.net.seleucus.wsp.main.WSVersion;
import main.java.net.seleucus.wsp.main.WebSpa;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Random;

public class WSChecker extends WSGestalt {

    public WSChecker(final WebSpa myWebSpa) {
        super(myWebSpa);
    }

    @Override
    public void exitConsole() {
        printlnWithTimeStamp("Goodbye!\n");
    }

    @Override
    public void runConsole() {

        println("");
        println("WebSpa - Single HTTP/S Request Authorisation- ");
//		println("version " + WSVersion.getValue() + " (webspa@seleucus.net)"); 		
        println("-this is checker mode!");
        println("test scipts: ");
        println("Enter the number of password you want: ");

        int counter = readLineRequiredInt("Enter the number of passwords you want:", 1, 20);
        int lenght = readLineRequiredInt("Enter the lenght of passwords you want:", 6, 12);
        printPassphraseSet(generatePassphraseSet(counter, lenght));

//        CharSequence password = readPasswordRequired("Your pass-phrase for that host");
//        println("password is:" + password);
//        println("allbytes:"+getKnock(password, 8));
//        byte[] passKnockBytes = PassPhraseCrypto.getHashedPassPhraseNow(password);
//        println("encoded password is:" + passKnockBytes);
        println("test scipts end. ");

    }

    public String getKnock(CharSequence passPhrase, int actionNumber) {
        byte[] passKnockBytes = PassPhraseCrypto.getHashedPassPhraseNow(passPhrase);
        byte[] actionKnockBytes = ActionNumberCrypto.getHashedActionNumberNow(passPhrase, actionNumber);

        byte[] allBytes = ArrayUtils.addAll(passKnockBytes, actionKnockBytes);

        return Base64.encodeBase64URLSafeString(allBytes);
    }

    private String[] generatePassphraseSet(int counter, int lenght) {

        String passPhraseSet[] = new String[counter];
        for (int i = 0; i < counter; i++) {
            String tempPass = "";
            for (int j = 0; j < lenght; j++) {

                tempPass += (j % 2 == 0 ? getCharSeed() : getIntSeed());
//                System.out.println("tempPass: " + tempPass);

            }
            passPhraseSet[i] = tempPass;
            System.out.println("tempPass" + (i + 1) + ": " + tempPass);

        }

        return passPhraseSet;
    }

    private void printPassphraseSet(String[] passPhraseSet) {

    }

    private String getCharSeed() {
        Date dateobj = new Date();
//        System.out.println("raw date: " + dateobj.getTime());
//        System.out.println("8 date: " + String.valueOf(dateobj.getTime()).substring(8));
        String temp = String.valueOf(dateobj.getTime());
        String last9Char = temp.substring(temp.length() - 9);
//        System.out.println("last date: " + last9Char);
        int randomValue = Integer.valueOf(last9Char);
        Random rand = new Random();
        randomValue += (rand.nextInt(8888) + 1);
        char seed = (char) (97 + (randomValue % 26));
//        System.out.println("seed: " + seed);

        return String.valueOf(seed);
    }

    private String getIntSeed() {
        Date dateobj = new Date();
//        System.out.println("raw date: " + dateobj.getTime());
//        System.out.println("8 date: " + String.valueOf(dateobj.getTime()).substring(8));
        String temp = String.valueOf(dateobj.getTime());
        String lastChar = temp.substring(temp.length() - 1);
//        System.out.println("last date: " + last9Char);
        int seedInt = Integer.valueOf(lastChar);
        Random rand = new Random();
        seedInt += (rand.nextInt(8888) + 1);
//        System.out.println("seed: " + seed);
        String seed = String.valueOf(seedInt);

        return seed.substring(seed.length() - 1);
    }

}
