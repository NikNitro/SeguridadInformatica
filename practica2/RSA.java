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
import java.util.Scanner;
    

public class RSA {
	public class Par {
		BigInteger i1;
		BigInteger i2;
	}
	
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;
   private BigInteger p;
   private BigInteger q;
   private Clave key;

   public RSA(int N) {
	   modulus = crearModulo(N);
	   Par primos = crearClave(p, q);
	   publicKey = primos.i1;
	   privateKey = primos.i2;
	   key = new Clave(modulus, publicKey, privateKey);
	   
   }
   
   public Clave generarClave() {
	   BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
		  BigInteger e = phi.subtract(one);
		  e = BigInteger.probablePrime(50, random);
		  BigInteger d = e.modInverse(phi);
		  
		  return new Clave(p.multiply(q), e, d);
   }
   
   //crearmodulo. Le pasas el número de bits, calcula los primos p y q, así como el módulo n=p.q
   public BigInteger crearModulo(int NBits) {
	   do {
		   p = BigInteger.probablePrime(NBits/2, random);
		   q = BigInteger.probablePrime(NBits/2, random);
	   } while(p.equals(q));
	   return (p).multiply(q);
   }
   
   //crearclave. Le pasas los primos p y q y te devuelve dos exponentes e y d de forma que e.d=1 mod (p-1)(q-1)
   public Par crearClave(BigInteger pe, BigInteger cu) {
	  BigInteger phi = (pe.subtract(one)).multiply(cu.subtract(one));
	  Par primos = new Par();
	  BigInteger e = phi.subtract(one);
	  e = BigInteger.probablePrime(50, random);
	  BigInteger d = e.modInverse(phi);
	  primos.i1 = e;
	  primos.i2 = d;
	  
	  
	  return primos;
   }
   
   //cifrar. Le pasas el mensaje, el exponente e y el módulo y te devuelve el texto cifrado
   public static String cifrar(String std, BigInteger publicKey, BigInteger modulo) {
	   BigInteger mensaje = new BigInteger(std);
	   BigInteger encriptado = mensaje.modPow(publicKey, modulo);
	   return encriptado.toString();
   }
   
   //descifrar. Le pasas el mensaje, el exponente e y el módulo y te devuelve el texto cifrado
   public static String descifrar(String std, BigInteger publicKey, BigInteger modulo) {
	   BigInteger mensajeCod = new BigInteger(std);
	   BigInteger original = mensajeCod.modPow(publicKey, modulo);
	   return original.toString();
   } 
   
   public static void main(String [] args) {
	   System.out.println("Introduzca el tamaño deseado para la clave");
	   Scanner sc = new Scanner(System.in);
	   int N = sc.nextInt();
	  
	   RSA rsa1 = new RSA(N);

	   RSA rsa2 = new RSA(N);

	   Clave clave1 = rsa1.generarClave();

	   //Con el mismo módulo salen los mismos mensajes (No siempre), no así con diferentes módulos.
	   
//	   Clave clave2= rsa2.generarClave(); 
	   Clave clave2= rsa1.generarClave(); 
	   
	   System.out.println(clave1);
	   System.out.println(clave2);
	   sc = new Scanner(System.in);
	   System.out.println("Introduzca el mensaje");
	   String std = sc.nextLine();
	   std = (new BigInteger(std.getBytes())).toString();

	   String cifrado1 = RSA.cifrar(std, clave1.getPub(), clave1.getMod());
	   
	   String cifrado2 = RSA.cifrar(cifrado1, clave2.getPub(), clave2.getMod());
	   System.out.println("Mensaje doblemente cifrado: "+ cifrado2);
	   String cifrado3 = RSA.descifrar(cifrado2, clave1.getPriv(), clave1.getMod());

	   String cifrado4 = RSA.descifrar(cifrado3, clave2.getPriv(), clave2.getMod());

	   System.out.println("Mensaje de vuelta: "+ cifrado4);
	   
	   System.out.println(cifrado4.equals(std)) ;
   }
}