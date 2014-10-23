package practica1;

public class EfectoAvalancha {
	private byte[] msg;
	private byte[] key;

	public EfectoAvalancha(String std, byte[] key) {
		this.key = key;
		msg = Util.stringToBytes(std);
		
		//Cargo varias veces el mensaje porque se modifica por culpa de la función cambiarBit.
		
		System.out.println("Prueba A: Cambio antes del cifrado");
		this.pruebaECBa();   System.out.println();
		msg = Util.stringToBytes(std);
		this.pruebaCTRa();   System.out.println();
		msg = Util.stringToBytes(std);
		this.pruebaCBCa();   System.out.println();
		msg = Util.stringToBytes(std);
		
		System.out.println();
		System.out.println("Prueba B: Cambio antes del descifrado");
		this.pruebaECBb();   System.out.println();
		this.pruebaCTRb();   System.out.println();
		this.pruebaCBCb();   System.out.println();
		
	}
	
	public void pruebaECBa() {
		byte[] cifrado = ModoECB.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar[18] = Util.cambiarBit(cambiar[18], 0);
		cambiar = ModoECB.cifrar(key, cambiar);
		
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
	
	public void pruebaCTRa() {
		byte[] cifrado = CTR.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar[18] = Util.cambiarBit(cambiar[18], 3);
		cambiar = CTR.cifrar(key, cambiar);
		
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
	
	public void pruebaCBCa() {
		byte[] cifrado = CBC.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar[18] = Util.cambiarBit(cambiar[18], 3);
		cambiar = CBC.cifrar(key, cambiar);
		
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
	
	public void pruebaECBb() {
		byte[] cifrado = ModoECB.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar = ModoECB.cifrar(key, cambiar);

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
	
	public void pruebaCTRb() {
		byte[] cifrado = CTR.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar = CTR.cifrar(key, cambiar);
		
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
	
	public void pruebaCBCb() {
		byte[] cifrado = CBC.cifrar(key, msg);
		byte[] cambiar = msg;
		cambiar = CBC.cifrar(key, cambiar);
		
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
