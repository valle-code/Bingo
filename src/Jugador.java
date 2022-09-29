import java.util.ArrayList;
import java.util.Random;

public class Jugador {

	private double dinero;
	private String nombre;
	private ArrayList<Carton> cartones;
	private double ludopatia;

	public Jugador(String nombre) {
		this.dinero = darDineroInicial();
		this.nombre = nombre;
		this.cartones = new ArrayList<Carton>();
		this.ludopatia = createLudopatia();
	}
	
	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Carton> getCartones() {
		return cartones;
	}

	public void setCartones(ArrayList<Carton> cartones) {
		this.cartones = cartones;
	}
	
	private double darDineroInicial() {
		return (double) new Random().nextInt(48) + 2;
	}

	@Override
	public String toString() {
		String cartonesString = "";
		
		for (Carton carton: cartones) {
			cartonesString += carton.toString() + "\n";
		}
		return "Nombre: " + nombre + ", dinero: " + dinero + ", ludopatia: "
		+ludopatia+", cartones: "+cartones.size()+"\n" +cartonesString;
	}

	/**
	 * Limpia los anteriores cartones y compra dependiendo 
	 * de su dinero y grado de ludopatia
	 * 
	 * @author Daniel 
	 */
	public void comprarCartones() {
		cartones = new ArrayList<Carton>();
		int numCartones;
		
		numCartones = tomarDecision();
		
		for (int i = 0; i < numCartones; i++) {
			cartones.add(new Carton());
			dinero -= 2;
		}
		
		System.out.println(nombre + " ha comprado " + numCartones + " cartones y le quedan " + dinero + " €");

		setCartones(cartones);
		System.out.println(toString());
	}

	/**
	 * Le da al jugador un valor entre 0 y 0.7 de ludopatia
	 * 
	 * @author Daniel y Gonzalo
	 * 
	 * @return grado de ludopatia
	 */
	private double createLudopatia() {
		Random aleatorio = new Random();
		return Math.abs(aleatorio.nextDouble() - 0.7); // Valor absoluto entre -0.7 y 0.7
	}

	/**
	 * Devuelve un numero de cartones entre 0 y 24
	 * en un una distribucion normal teniendo
	 * en cuenta su dinero y ludopatia 
	 * 
	 * @author Daniel y Gonzalo
	 * 
	 * @return numero de cartones
	 */
	private int tomarDecision() {
		Random aleatorio = new Random();
		double gauss, gaussLudo;
		int cantidadCartones;

		do {
			// Valor absoluto de la distribucion normal con varianza de 4.5
			gauss = Math.abs(aleatorio.nextGaussian() * 4.5);
			
			// Aumento del valor en funcion de la ludopatia
			gaussLudo = gauss + gauss * ludopatia;
		} while (gaussLudo > dinero);
		
		// Numero de cartones truncado
		cantidadCartones = (int) (dinero - gaussLudo) / 2;
		
		if(cantidadCartones>24) {
			cantidadCartones = 24;
		}
		
		return cantidadCartones; 
	}	
	
	/**
	 * @author Daniel 
	 * 
	 * @return el numero de cartones
	 */
	public int getNumCartones() {
		return cartones.size();
	}

}
