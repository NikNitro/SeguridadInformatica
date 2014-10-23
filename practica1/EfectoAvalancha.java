package practica1;
import practica1.*;

public class EfectoAvalancha {
	private byte[] msg;
	private byte[] key;

	public EfectoAvalancha(String std, byte[] key) {
		this.key = key;
		msg = Util.stringToBytes(std);
		
		this.pruebaECB();   System.out.println();
		this.pruebaCTR();   System.out.println();
		this.pruebaCBC();   System.out.println();
		
	}
	
	public void pruebaECB() {
		byte[] cifrado = ModoECB.cifrar(key, msg);
		byte[] cambiar = msg;
	//	cambiar[18] = Util.cambiarBit(cambiar[18], 3);
		cambiar = ModoECB.cifrar(key, cambiar);
		//En el ejercicio pone que se cambiia el bit antes de cifrar pero debiera ser despues
		cambiar[18] = Util.cambiarBit(cambiar[18], 3);	
		
		cifrado = ModoECB.descifrar(key, cifrado);
		cambiar = ModoECB.descifrar(key, cambiar);

		System.out.println("ECB   Original : "+Util.bytesToString(cifrado));
		System.out.println("ECB Modificada : "+Util.bytesToString(cambiar));
		
		int cambios = 0;
		for(int i = 0; i < cifrado.length; i++) {
			cambios += Util.contarBitsDiferentes(cifrado[i], cambiar[i]);
		}
		System.out.println("Cambios en ECB: "+cambios);
		
	}
	
	public void pruebaCTR() {
		byte[] cifrado = CTR.cifrar(key, msg);
		byte[] cambiar = msg;
	//	cambiar[18] = Util.cambiarBit(cambiar[18], 3);
		cambiar = CTR.cifrar(key, cambiar);
		//En el ejercicio pone que se cambiia el bit antes de cifrar pero debiera ser despues
		cambiar[18] = Util.cambiarBit(cambiar[18], 3);	
		
		cifrado = CTR.descifrar(key, cifrado);
		cambiar = CTR.descifrar(key, cambiar);

		System.out.println("CTR   Original : "+Util.bytesToString(cifrado));
		System.out.println("CTR Modificada : "+Util.bytesToString(cambiar));

		int cambios = 0;
		for(int i = 0; i < cifrado.length; i++) {
			cambios += Util.contarBitsDiferentes(cifrado[i], cambiar[i]);
		}
		System.out.println("Cambios en CTR: "+cambios);
	}
	
	public void pruebaCBC() {
		byte[] cifrado = CBC.cifrar(key, msg);
		byte[] cambiar = msg;
	//	cambiar[18] = Util.cambiarBit(cambiar[18], 3);
		cambiar = CBC.cifrar(key, cambiar);
		//En el ejercicio pone que se cambiia el bit antes de cifrar pero debiera ser despues
		cambiar[18] = Util.cambiarBit(cambiar[18], 3);	
		
		cifrado = CBC.descifrar(key, cifrado);
		cambiar = CBC.descifrar(key, cambiar);

		System.out.println("CBC   Original : "+Util.bytesToString(cifrado));
		System.out.println("CBC Modificada : "+Util.bytesToString(cambiar));

		int cambios = 0;
		for(int i = 0; i < cifrado.length; i++) {
			cambios += Util.contarBitsDiferentes(cifrado[i], cambiar[i]);
		}
		System.out.println("Cambios en CBC: "+cambios);
	}
	
	
	
	public static void main(String[] args) {
		String std = "Este es un texto de ejemplo largo que ocupa 48 B";
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
		new EfectoAvalancha(std, key);

	}

}
