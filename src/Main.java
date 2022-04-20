import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        //Festlegung eines einheitlichen Keys
        byte[] decodedKey = Base64.getDecoder().decode("vzxgk9PH/Zj31vjRpAzyolTNJjIQ+/FZ+Q1ns/Jg3EA=");
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        //Festlegung eines zu ver- und entschlüsselnden Textes
        String data = "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        //Zeitmessung und iteratives Ver- und Entschlüsseln
        long start = System.currentTimeMillis();
        for (int i = 10000000; i > 0; i--) {
            data = AES.encrypt(data, originalKey);
            data = AES.decrypt(data, originalKey);
        }
        long ende = System.currentTimeMillis();
        System.out.println("Dauer in ms: " + (ende - start));
    }
}

class AES{
    //Verschlüsselungsfunktion
    //Berechnet einen verschlüsselten Text aus einem Eingabetext und einem Schlüssel
    public static String encrypt(String data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }
    //Entschlüsselungsfunktion
    //Berechnet einen Text aus einem verschlüsselten Eingabetext und einem Schlüssel
    public static String decrypt(String data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }
}
