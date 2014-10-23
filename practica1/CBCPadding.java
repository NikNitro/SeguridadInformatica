package practica1;

public class CBCPadding {
	private static byte[] IV;
	private byte[] key;
	private byte[] mensaje;
	
	public CBCPadding(byte[] key, byte[] mensaje) {
		this.key = key;
		this.mensaje = mensaje;
	}
	
	
	
	
	public static byte[] cifrar(byte[] key, byte[] msg, byte[] IV){
		byte[] mensajeCifrado;
		int aumento = 0;				//El tamaño que va a aumentar el mensaje para que quepa.
        if(msg.length%AES128.TAM_BLOQUE_BYTES ==0) {
        	aumento = AES128.TAM_BLOQUE_BYTES;
        } else {
        	aumento = AES128.TAM_BLOQUE_BYTES - msg.length%AES128.TAM_BLOQUE_BYTES;
        }
    	mensajeCifrado = new byte[msg.length + aumento];
    	
     //   IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
        
        //Booleano para ver si el bloque_i es el ultimo
        boolean esUltimo = false;
        
        for(int i=0; i<msg.length+aumento; i+=AES128.TAM_BLOQUE_BYTES){
        	
        	//Vemos si es el último bloque
        	esUltimo = (msg.length+aumento <= i + AES128.TAM_BLOQUE_BYTES);
        	
            // Copiamos un bloque del mensaje, empezando en la posiciÃ³n i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
        	//Creamos bloque
            byte[] bloque_i = new byte[AES128.TAM_BLOQUE_BYTES];
            if(!esUltimo)
            	System.arraycopy(msg, i, bloque_i, 0, AES128.TAM_BLOQUE_BYTES);
            else{
	            int ultimo = -1;
	           	for(int j = 0; j < msg.length%AES128.TAM_BLOQUE_BYTES; j++) {
	           		bloque_i[j] = msg[i+j];
	           		ultimo = j;
	           	}
	           	for(int j = ultimo+1; j < AES128.TAM_BLOQUE_BYTES; j++) {
	           		bloque_i[j] = (byte)aumento;
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
	
	public static byte[] descifrar(byte[] key, byte[] msg, byte[] IV){
		
	//	IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");
		byte[] mensajeAux = new byte[msg.length];
		
        byte[] mensaje = null;
        
		boolean esUltimo = false;
        
        for(int i=0; i<msg.length; i+=AES128.TAM_BLOQUE_BYTES){
            
            // Copiamos un bloque del mensaje, empezando en la posiciÃ³n i.
            // Recordemos que los bloques tienen longitud 16 bytes.
        	
        	//miramos si es el ultimo bloque
        	esUltimo = msg.length<=i+AES128.TAM_BLOQUE_BYTES;
        	
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
            
            if(!esUltimo)
            	System.arraycopy(bloque_i, 0, mensajeAux, i, AES128.TAM_BLOQUE_BYTES);
            else {
            	int sobran = (int)(bloque_i[bloque_i.length-1]);
          //  	System.out.println(sobran);
            	mensaje = new byte[msg.length-sobran];
            	for(int j = 0; j < AES128.TAM_BLOQUE_BYTES-sobran; j++) {
            		mensaje[j+i] = bloque_i[j];
            	}
            	System.arraycopy(mensajeAux, 0, mensaje, 0, i);
            }
            
        }
        
        
        return mensaje;
    }
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//res para Ejemplo = 646426DBD5859D531FF1EBE45BBA44FE
		byte[] key = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c");
		byte[] msg = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a");
		byte[] res = Util.hexStringToBytes("7649ABAC8119B246CEE98E9B12E9197D8964E0B149C10B7B682E6E39AAEB731C");
		byte[] IV = Util.hexStringToBytes("000102030405060708090A0B0C0D0E0F");

		 System.out.println(Util.bytesToHexString(cifrar(key, Util.stringToBytes("Ejemplo"), IV)));
		 System.out.println(Util.bytesToString(descifrar(key, Util.hexStringToBytes("646426DBD5859D531FF1EBE45BBA44FE"), IV)));

	}

}
