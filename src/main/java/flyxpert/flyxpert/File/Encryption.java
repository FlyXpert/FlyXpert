package flyxpert.flyxpert.File;
import java.util.Random;
public class Encryption
{
    public static String encrypt(String password, int code)
    {
        String encrypted = "";
        for(int i = 0; i < password.length(); i++)
        {
            encrypted += (char)(password.charAt(i) + code);
        }
        return encrypted;
    }
    public static String constantEncrypt(String password)
    {
        String constantEncryption = "";
        for(int i = 0; i < password.length(); i++)
        {
            constantEncryption += (char)(password.charAt(i) + 'a');
        }
        //System.out.println(constantEncryption);
        return constantEncryption;
    }
    public static String constantDecrypt(String password)
    {
        String constantDecryption = "";
        for(int i = 0; i < password.length(); i++)
        {
            constantDecryption += (char)(password.charAt(i) - 'a');
        }
        //System.out.println(constantDecryption);
        return constantDecryption;

    }

    public static String decrypt(String password, int code)
    {
        String decrypted = "";
        for(int i = 0; i < password.length(); i++)
        {
            decrypted += (char)(password.charAt(i) - code);
        }
        return decrypted;
    }
    public static int generateCode()
    {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(100);
    }


    //Read decrypt
    //Write, encrypt
}
