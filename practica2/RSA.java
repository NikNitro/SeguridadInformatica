package practica2;

/*************************************************************************
 *  Compilation:  javac RSA.java
 *  Execution:    java RSA N
 *  
 *  Generate an N-bit public and private RSA key and use to encrypt
 *  and decrypt a random message.
 * 
 *  % java RSA 50
 *  public  = 65537
 *  private = 553699199426609
 *  modulus = 825641896390631
 *  message   = 48194775244950
 *  encrpyted = 321340212160104
 *  decrypted = 48194775244950
 *
 *  Known bugs (not addressed for simplicity)
 *  -----------------------------------------
 *  - It could be the case that the message >= modulus. To avoid, use
 *    a do-while loop to generate key until modulus happen to be exactly N bits.
 *
 *  - It's possible that gcd(phi, publicKey) != 1 in which case
 *    the key generation fails. This will only happen if phi is a
 *    multiple of 65537. To avoid, use a do-while loop to generate
 *    keys until the gcd is 1.
 *
 *************************************************************************/

import java.math.BigInteger;
import java.security.SecureRandom;

import practica1.Util;
    

public class RSA {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;

   // generate an N-bit (roughly) public and private key
   RSA(int N) {
      BigInteger p = BigInteger.probablePrime(N/2, random);
      BigInteger q = BigInteger.probablePrime(N/2, random);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

//      modulus    = p.multiply(q);        
      modulus = new BigInteger("388193491555241");
      modulus = new BigInteger("5352201164725758886643338955023163187680868254661527031768896617923750966705650623134506135322459331925455186254481271064447066602887739371119743909350483");
      //El mio es 2^ a 2^ a 6 +1
//      publicKey  = new BigInteger("18446744073709551617");     // common value in practice = 2^16 + 1
//      publicKey = new BigInteger("8817927924850284314415889862456599062178388486590733503677933413725802837072931709603546772427070367772365848821099322550493066131012808332627096566096327");
      publicKey  = new BigInteger("215288934458057"); 
      privateKey = publicKey.modInverse(phi);
      privateKey = new BigInteger("215288934458057");
      privateKey = new BigInteger("8817927924850284314415889862456599062178388486590733503677933413725802837072931709603546772427070367772365848821099322550493066131012808332627096566096327");
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      String s = "";
      s += "public  = " + publicKey  + "\n";
      s += "private = " + privateKey + "\n";
      s += "modulus = " + modulus;
      return s;
   }
 
   public static void main(String[] args) {
      int N = 50;//Integer.parseInt(args[0]);
      RSA key = new RSA(N);
      System.out.println(key);
 
      // create random message, encrypt and decrypt
      //BigInteger message = new BigInteger(N-1, random);

      //// create message by converting string to integer
       String s = "Why So Serious?";
 //      String s = "Dile que soy el que se porta bien contigo en la cama";
 //      s = "324279001009656";
       s = "2169917614497992936958436434309238700657914011772773540750411601208395429222445087433297299805524688341213308484754288339899327359668316552072892939626198";
       byte[] bytes = s.getBytes();
       BigInteger message = new BigInteger(bytes);
      
      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      System.out.println("message   = " + message);
      System.out.println("encrpyted = " + encrypt);
      System.out.println(Util.bytesToString(encrypt.toByteArray()));
      System.out.println("decrypted = " + decrypt);
      System.out.println(Util.bytesToString(decrypt.toByteArray()));
   }
}
