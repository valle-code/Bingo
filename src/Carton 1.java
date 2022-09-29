import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Carton {
	// TODO: comprobar que existe, eliminar y comprobar bingo y linea

	private ArrayList<Integer> numeros;
	private int tachados;

	public Carton() {
		this.numeros = createCarton();
		setTachados(0);
	}

	/** Crea un carton nuevo
	 * 
	 * @return ArrayList<Integer> de numeros
	 */
	private ArrayList<Integer> createCarton() {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		Random rd = new Random();

		for (int i = 0; i < 10; i++) {
			numeros.add(rd.nextInt((10 * (i + 1)) - (10 * i)) + 10 * i);
			numeros.add(getNewNum(i, 0, (int) numeros.get(numeros.size()-1)));
			numeros.add(getNewNum(i, (int) numeros.get(numeros.size()-2), (int) numeros.get(numeros.size()-1)));
		}

		Collections.sort(numeros);

		return numeros;
	}

	/**
	 * Consigue un numero completamente nuevo en la decena dada
	 * 
	 * @param i      numero de la decena actual
	 * @param decena el resto de la decena actual
	 * 
	 * @return El numero nuevo
	 */
	private Integer getNewNum(int i, int posUno, int posDos) {
		int num;
		Random rd = new Random();

		do {
			num = rd.nextInt((10*(i+1))-(10*i))+10 * i; // Rango de la decena
		} while (num == posUno || num == posDos); // Es diferente

		return num;
	}

	public ArrayList<Integer> getNumeros() {
		return numeros;
	}

	public void setNumeros(ArrayList<Integer> numeros) {
		this.numeros = numeros;
	}

	public int getTachados() {
		return tachados;
	}

	public void setTachados(int tachados) {
		this.tachados = tachados;
	}

	public boolean contains(int num) {
		return numeros.contains(num);
	}

	/** Tacha una linea en el boleto
	 * 
	 * @param bola numero que ha salido del bombo
	 */
	public void tacharNum(int bola) {
		numeros.set(numeros.indexOf((Integer) bola), 0);
		tachados++;
	}
	
	/** Comprueba si se ha hecho linea en el carton
	 * 
	 * @return boolean: si se hace o no linea
	 */
	public boolean checkLinea() {
		int tachadosLinea;
		int index;
		int linea = 0;
		int numAct;
		
		// Solo puede si ya hay mas de 10 tachados en el carton
		if (tachados > 10) {
			do {
				tachadosLinea = 0;
				index = 0;
				do {
					numAct = numeros.get((index * 3) + linea);
					if (numAct == 0) {
						tachadosLinea++;
					}
					index++;
				// Pasa si encuentra algo que no es 0 o acaba linea
				} while(numAct == 0 && index<10);
				linea++; // Salta a la siguiente linea
			} while (tachadosLinea < 10 && linea < 3);
			
			// Comprueba si al final se han contado 10 tachados
			// o simplemente se ha llegado al final del carton
			return tachadosLinea == 10;
		} else {
			return false;
		}
	}
	
	/** Comprueba si ya están los 30 numeros
	 * 	del carton tachados
	 * 
	 * @return boolean: si tiene o no bingo
	 */
	public boolean checkBingo() {
		return tachados==30;
	}

	@Override
	public String toString() {
		String str = "";

		// Itera de ""linea"" en ""linea""
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 10; j++) {
				str += numeros.get((j * 3) + i) + "\t";
			}
			str += "\n";
		}

		return str;
	}

}
