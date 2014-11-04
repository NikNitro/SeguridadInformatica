package practica2;

import java.math.BigInteger;

public class Clave {
	private BigInteger modulus;
	private BigInteger publicKey;
	private BigInteger privateKey;
	
	public Clave(BigInteger modulus, BigInteger publicKey, BigInteger privateKey) {
		this.modulus = modulus;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public BigInteger getMod(){
		return modulus;
	}
	public BigInteger getPub(){
		return publicKey;
	}
	public BigInteger getPriv(){
		return privateKey;
	}
	
	public String toString() {
		String std = "";
		std += "Publica: " + publicKey + "\n";
		std += "Privada: " + privateKey + "\n";
		std += "Modulo: "  + modulus + "\n";
		return std;
	}
	
}
