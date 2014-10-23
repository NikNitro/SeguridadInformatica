package practica1;

public class CTR {
	private static byte[] contador;
	private byte[] key;
	private byte[] msg;

	public CTR(byte[]key, byte[] mensaje) {
		this.key = key;
		this.msg = mensaje;

		System.out.println(Util.bytesToHexString(cifrar(key, msg)));
		System.out.println(Util.bytesToHexString(descifrar(key, cifrar(key, msg))));
		
		
	}
	
	public static byte[] cifrar(byte[] key, byte[] msg){
		contador = new byte[AES128.TAM_BLOQUE_BYTES];
        
        byte[] mensajeCifrado = new byte[msg.length];
        
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            
            System.arraycopy(msg, i, bloque_i, 0, msg.length);//AES128.TAM_BLOQUE_BYTES);
            
            byte[] bloqueCifrado = AES128.cifrarBloque(key, contador);
            
            mensajeCifrado = Util.xor(bloqueCifrado, bloque_i);
            

            incrementar(contador, contador.length-1);
        }
        
        return mensajeCifrado;
    }
	
	
	private static void incrementar(byte[] contador, int ultimo) {
		byte cambiante = contador[ultimo];
		if(cambiante!=127)
			contador[ultimo]++;
		else
			incrementar(contador, ultimo--);
	}
	
	
	public static byte[] descifrar(byte[] key, byte[] mensajeCifrado){
		contador = new byte[AES128.TAM_BLOQUE_BYTES];
        
        byte[] mensaje = new byte[mensajeCifrado.length];
        
        
        for(int i=0; i<mensajeCifrado.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            System.arraycopy(mensajeCifrado, i, bloque_i, 0, mensajeCifrado.length);//AES128.TAM_BLOQUE_BYTES);
            
            byte[] bloqueMensaje = AES128.cifrarBloque(key, contador);
            
            mensaje = Util.xor(bloqueMensaje, bloque_i);
            
        }
        
        return mensaje;
    }
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Res 1636d5ee34f80625d77f8e56ca884345
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
	//	byte[] mensaje = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a");
	//	new CTR(key, mensaje);
		System.out.println(Util.bytesToHexString(CTR.cifrar(key, Util.hexStringToBytes("DCF72940CE"))));
		System.out.println(Util.bytesToHexString(CTR.descifrar(key, Util.hexStringToBytes("753363724886D443DE12A58217C1"))));
	}

}
