package practica1;

public class CBC {
	private static byte[] IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
	private byte[] key;
	private byte[] mensaje;
	
	public CBC(byte[] key, byte[] mensaje) {
		this.key = key;
		this.mensaje = mensaje;
	}
	
	
	
	
	public static byte[] cifrar(byte[] key, byte[] msg){
        
        byte[] mensajeCifrado = new byte[msg.length];
        
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            // Copiamos un bloque del mensaje, empezando en la posición i.
            // Recordemos que los bloques tienen longitud 16 bytes.
//            byte[] bloque = Arrays.copyOfRange(msg, i, i+AES128.TAM_BLOQUE_BYTES);
        	
        	//Creamos bloque
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            
            //Hacemos XOR
            bloque_i = Util.xor(bloque_i, IV);
            
            //Ciframos bloque
            bloque_i = AES128.cifrarBloque(key, bloque_i);
            
            //Guardamos IV para el siguiente
            IV = bloque_i;
            
            
            System.arraycopy(bloque_i, 0, mensajeCifrado, i, AES128.TAM_BLOQUE_BYTES);
            
        }
        
        return mensajeCifrado;
    }
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//res = 7649abac8119b246cee98e9b12e9197d
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
		byte[] msg = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a");
		
		 System.out.println(Util.bytesToHexString(cifrar(key, msg)));

	}

}
