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

      //El mio es 2^ a 2^ a 6 +1
//      publicKey  = new BigInteger("18446744073709551617");     // common value in practice = 2^16 + 1
      
      //Pa copiar y pegar:  = new BigInteger("");
      
      /////MIOS
      publicKey = new BigInteger("9572261724031235796456314212640623041413402685960825411881370312888698894344434671827185869290063553436171999701677258313392648125597291350235839223805009");
      
      privateKey = new BigInteger("2053233575096191586008083891753287738799157838261000058019502708362682466023248622612499439108586736966707886273699047104399457877749348360576482374707089");

      modulus    = new BigInteger("4751506162242368333090875698896539663719955960553803723873359272516468729936881369379201527611724011846157486665829339370815585329144926134910764653285953");
      ////
      
      
      ///PARA PROBAR
      publicKey = new BigInteger("2053233575096191586008083891753287738799157838261000058019502708362682466023248622612499439108586736966707886273699047104399457877749348360576482374707089");
//      privateKey = new BigInteger("8817927924850284314415889862456599062178388486590733503677933413725802837072931709603546772427070367772365848821099322550493066131012808332627096566096327");
//      modulus = new BigInteger("5352201164725758886643338955023163187680868254661527031768896617923750966705650623134506135322459331925455186254481271064447066602887739371119743909350483");
      
      /// 
   
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
       String s = "Mañana, por fin, es viernes";
 //      String s = "Dile que soy el que se porta bien contigo en la cama";
 //      s = "370347363518813";
 //        s = "Espero que ya funcione";  //Firma de CGG
       byte[] bytes = s.getBytes();
       BigInteger message = new BigInteger(bytes);
      
      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt;// = key.decrypt(encrypt);
      decrypt = key.decrypt(message);
      System.out.println("message   = " + message);
      System.out.println("encrpyted = " + encrypt);
      System.out.println(new String(encrypt.toByteArray()));
      System.out.println("decrypted = " + decrypt);
      System.out.println(new String(decrypt.toByteArray()));
   }
}
