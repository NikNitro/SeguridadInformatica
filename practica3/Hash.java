package practica3;

import practica1.*;
/**
 * Por supuesto he de dejar claro que ésta funcion que retorna un hash solo sirve para aprendizaje. 
 * Que a nadie se le ocurra usarla para algo serio.
 * @author NikNitro
 *
 */


//Como modo de operación usaré CBC para asegurarme de que cambiando un solo bit puede cambiar bastante.
public class Hash {
	private final byte[] IV = new byte[16];
	private final byte[] key= new byte[16];
	private byte[] entrada;
	
	public Hash()  {
	}
	
	
	
	public byte[] doHash(String std) {

		entrada = Util.stringToBytes(std);
		byte[] salida = CBCPadding.cifrar(key, entrada, IV);
		
		
		byte[] retur = new byte[16];
		for(int i = 0; i < 16; i++) {
			retur[i] = salida[salida.length - 16 + i];
			
		}
		
		return retur;
	}
	

	public static void main(String[] args) {

		Hash h = new Hash();

		System.out.println(Util.bytesToHexString(Util.stringToBytes(("hola"))));
		System.out.println(Util.bytesToHexString(h.doHash("hola")));
		System.out.println(Util.bytesToHexString(h.doHash("holb")));

	}

}
