package practica1;

public class ECB {
	public ECB(byte[] k, byte[] m) {
		byte[] c = ModoECB.cifrar(k, m); 
		System.out.println("c = " + Util.bytesToHexString(c)); 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] k = Util.hexStringToBytes("2b7e151628aed2a6abf7158809cf4f3c"); 
		byte[] m = Util.hexStringToBytes("6bc1bee22e409f96e93d7e117393172a"); 
		new ECB(k, m);
	}

}
