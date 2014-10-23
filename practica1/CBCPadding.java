package practica1;

public class CBCPadding {
	private static byte[] IV;
	private byte[] key;
	private byte[] mensaje;
	
	public CBCPadding(byte[] key, byte[] mensaje) {
		this.key = key;
		this.mensaje = mensaje;
	}
	
	
	
	
	public static byte[] cifrar(byte[] key, byte[] msg){
		byte[] mensajeCifrado;
		int aumento = 0;				//El tamaño que va a aumentar el mensaje para que quepa.
        if(msg.length%AES128.TAM_BLOQUE_BYTES ==0) {
        	aumento = AES128.TAM_BLOQUE_BYTES;
        } else {
        	aumento = AES128.TAM_BLOQUE_BYTES - msg.length%AES128.TAM_BLOQUE_BYTES;
        }
    	mensajeCifrado = new byte[msg.length + aumento];
    	
        IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
        
        //Booleano para ver si el bloque_i es el ultimo
        boolean esUltimo = false;
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
        	
        	//Vemos si es el último bloque
        	esUltimo = !(msg.length > i + AES128.TAM_BLOQUE_BYTES);
        	
            // Copiamos un bloque del mensaje, empezando en la posiciÃ³n i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
        	//Creamos bloque
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            if(!esUltimo)
            	System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            else{
            	if(aumento!=AES128.TAM_BLOQUE_BYTES) {
	            	int ultimo = 0;
	            	for(int j = 0; j < msg.length%AES128.TAM_BLOQUE_BYTES; j++) {
	            		bloque_i[j] = msg[i+j];
	            		ultimo = j;
	            	}
	            	for(int j = ultimo+1; j < AES128.TAM_BLOQUE_BYTES; j++) {
	            		bloque_i[j] = (byte)aumento;
	            	}
            	} else {
                	System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            		int ultimoBloque = mensajeCifrado.length-1-AES128.TAM_BLOQUE_BYTES;
            		for(int j = 0; j < AES128.TAM_BLOQUE_BYTES; j++) {
            			mensajeCifrado[ultimoBloque+j] = (byte)aumento;
            		}
            	}
            }
            
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
            
            // Copiamos un bloque del mensaje, empezando en la posiciÃ³n i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
        	//Creamos bloque
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            
            //Guardamos IV para después
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
		//res para Ejemplo = 646426DBD5859D531FF1EBE45BBA44FE
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
		byte[] msg = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a");
		byte[] res = Util.hexStringToBytes("7649abac8119b246cee98e9b12e9197d");

		 System.out.println(Util.bytesToHexString(cifrar(key, Util.stringToBytes("Ejemplo"))));
		 System.out.println(Util.bytesToHexString(cifrar(key, msg)));

	}

}
