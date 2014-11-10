package practica1;

public class CBC {
	private static byte[] IV;
	private byte[] key;
	private byte[] mensaje;
	
	public CBC(byte[] key, byte[] mensaje) {
		this.key = key;
		this.mensaje = mensaje;
	}
	
	public static void setIV(byte[] IVs) {
		IV = IVs;
	}
	
	
	
	
	public static byte[] cifrar(byte[] key, byte[] msg){
		
		IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
        
        byte[] mensajeCifrado = new byte[msg.length];
        
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            // Copiamos un bloque del mensaje, empezando en la posición i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
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
	
	public static byte[] descifrar(byte[] key, byte[] msg){
		
		IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
        
        byte[] mensaje = new byte[msg.length];
        
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            // Copiamos un bloque del mensaje, empezando en la posición i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
        	//Creamos bloque
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            
            //Guardamos IV para despu�s
            byte[] IVaux = bloque_i;
            
            
            //Desciframos bloque
            bloque_i = AES128.descifrarBloque(key, bloque_i);

            //Hacemos XOR
            bloque_i = Util.xor(bloque_i, IV);
            
            //Guardamos el IV reservado para el siguiente
            IV = IVaux;
            
            
            System.arraycopy(bloque_i, 0, mensaje, i, AES128.TAM_BLOQUE_BYTES);
            
        }
        
        return mensaje;
    }
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//res = 7649abac8119b246cee98e9b12e9197d
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
		byte[] msg = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a");
		byte[] res = Util.hexStringToBytes("7649abac8119b246cee98e9b12e9197d");

		 System.out.println(Util.bytesToHexString(cifrar(key, msg)));
		 System.out.println(Util.bytesToHexString(descifrar(key, res)));

	}

}
