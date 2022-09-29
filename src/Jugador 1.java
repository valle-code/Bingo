import java.util.ArrayList;
import java.util.Random;

public class Jugador {

	// Attributes
	private float dinero;
	private String nombre;
	private ArrayList<Carton> cartones;
	private double ludopatia;

	// Constructor
	public Jugador(String nombre) {
		this.dinero = darDinero();
		this.nombre = nombre;
		this.cartones = new ArrayList<Carton>();
		this.ludopatia = createLudopatia();
	}

	// Getter and Setter
	public float getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
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

	@Override
	public String toString() {
		// TODO: rehacer esto mostrando todos los cartones con
		// el formato correcto (un for-each y carton.toString()
		return "nombre: "+nombre+", dinero: "+dinero+", cartones= " + cartones.size();
	}
	
	private float darDinero() {
		Random aleatorio = new Random();
		
		return (float)aleatorio.nextInt(48)+2;
	}
	
	// Declarar la ludopatia
	private double createLudopatia() {
		Random aleatorio = new Random();
		return Math.abs(aleatorio.nextDouble() - 0.7);
	}

	// Métodos
	// Comprar cartones
	public void comprarCartones() {
		int numCartones;

		do {
			numCartones = tomarDecision();
		} while (numCartones < 0 || numCartones > 24);
		for (int i = 0; i < numCartones; i++) {
			cartones.add(new Carton());
			dinero -= 2;
		}
		System.out.println(nombre + " ha comprado " + numCartones + " cartones y le quedan " + dinero + " €");
		
		setCartones(cartones);
	}

	// Inteligencia de los jugadores
	private int tomarDecision() {
		Random aleatorio = new Random();
		double gauss, gaussLudo;

		do {
			gauss = Math.abs(aleatorio.nextGaussian() * 4.5);
			gaussLudo = gauss + gauss * ludopatia;
		} while (gaussLudo > dinero);

		return (int) Math.round((dinero - gaussLudo) / 2);
	}

}
