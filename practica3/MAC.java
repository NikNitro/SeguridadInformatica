package practica3;

import practica1.*;

//Lo haré con HMAC. Si me da por ahi le implemento el otro...
public class MAC {
	private final byte[] IV = new byte[16];
	private byte[] key= new byte[16];
	private byte[] entrada;
	
	public MAC()  {
	}
	
	
	public void initKey(byte[] key) {
		this.key=key;
	}
	
	public byte[] doMAC(byte[] msg) {
		
		entrada = msg;
		byte[] salida = CBCPadding.cifrar(key, entrada, IV);
		
		
		byte[] retur = new byte[16];
		for(int i = 0; i < 16; i++) {
			retur[i] = salida[salida.length - 16 + i];
			
		}
		
		return retur;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
