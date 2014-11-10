package practica3;



/**
 * @author NikNitro
 * 
 * Esta mal porque acaba repitiendo valores. *Solucionado con el contador, aunque lo hace inseguro.
 *
 */
public class Random {
	private double semilla;
	private Hash h;
	private double contador = 0;

	/**
	 * 
	 * @param std debe ser una String de valores hexadecimales
	 */
	public void init(int x){
		semilla = x;
		h = new Hash();
	}
	public double getRandom() {
		
		byte[] b = h.doHash(Double.toString(semilla));
		int aux = 0;
		for(byte by : b)
			aux = aux + by;
		semilla = ((aux+contador++)%1000)/1000;
		return semilla;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		r.init(1);

		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());
		System.out.println(r.getRandom());

	}

}
