import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Sala {

	// Atributos
	private ArrayList<Integer> bombo;
	private ArrayList<Jugador> jugadores;
	private int bote;

	// Constructor
	public Sala() {
		this.bombo = generarBombo();
		this.jugadores = generarJugadores();
		this.bote = 0;
	}

	// Getter and Setter
	public ArrayList<Integer> getBombo() {
		return bombo;
	}

	public void setBombo(ArrayList<Integer> bombo) {
		this.bombo = bombo;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getBote() {
		return bote;
	}

	public void setBote(int bote) {
		this.bote = bote;
	}

	// To string
	@Override
	public String toString() {
		// TODO: rehacer esto mostrando todos los jugadores con
		// el formato correcto (un for-each y jugador.toString()
		return "Sala [bombo=" + bombo + ", jugadores=" + jugadores + ", bote=" + bote + "]";
	}

	// Métodos
	// Crear bombo
	public ArrayList<Integer> generarBombo() {
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		for (int i = 1; i <= 99; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		return numeros;
	}

	// Generar jugadores
	private ArrayList<Jugador> generarJugadores() {
		Random aleatorio = new Random();
		int numJugadores = aleatorio.nextInt(24) + 2;
		;
		ArrayList<Jugador> listaPlayer = new ArrayList<Jugador>();

		System.out.println("Hay " + numJugadores + " jugadores");

		for (int i = 1; i < numJugadores + 1; i++) {
			listaPlayer.add(new Jugador("Jugador " + i));
		}

		System.out.println("Ya han entrado todos los jugadores");
		return listaPlayer;
	}

	// Sacar Numeros
	public int sacarBola() {
		int numero;

		numero = bombo.get(0);
		bombo.remove(0);

		return numero;
	}

	public void iniciarRonda() {
		ArrayList<Jugador> jugadores = getJugadores();

		// Compran cartones todos lo jugadores
		for (Jugador player : jugadores) {
			player.comprarCartones();
			// TODO: Quitar jugadores que
			// jueguen 0 (en clase Jugador)
		}

		// Dos € por carton
		bote += cantidadDeCartonesTotales() * 2;
	}

	private int cantidadDeCartonesTotales() {
		int cantidad = 0;

		for (Jugador player : getJugadores()) {
			cantidad += player.getCartones().size();
		}

		return cantidad;
	}

	public void jugarPartidaBingo() {
		int indexJugador;
		int indexCarton;
		int cantidadJugadores = jugadores.size();
		int cantidadCartonesJ;
		ArrayList<Carton> cartonesJugador;
		Carton carton;
		boolean lineaCantada = false;
		boolean bingoCantado = false;
		int bola;

		do {
			bola = sacarBola();
			System.out.println("Ha salido el " + bola);
			indexJugador = 0;

			// Parsear jugadores
			do {
				cartonesJugador = jugadores.get(indexJugador).getCartones();
				cantidadCartonesJ = cartonesJugador.size();
				indexCarton = 0;

				// Parsear cartones jugador
				do {
					// Comprobar carton
					carton = cartonesJugador.get(indexCarton);

					// Si está el numero se tacha
					if (carton.contains(bola)) {
						carton.tacharNum(bola);
					}

					// Comprobar lineas o bingo
					if (!lineaCantada && carton.checkLinea()) {
						// TODO: dar el premio de la linea
						lineaCantada = true;
						System.out.println("LINEA \n" + carton.toString());
					} else if (carton.checkBingo()) {
						bingoCantado = true;
						System.out.println("BINGO!!! \n" + carton.toString());
						// TODO: Dar el premio del bingo
						// Forzar finalizar
					}
					
					indexCarton++;
				} while (!bingoCantado && indexCarton < cantidadCartonesJ);
				
				indexJugador++;
			} while (!bingoCantado && indexJugador < cantidadJugadores);

		} while (!bingoCantado);
		
		// El '--indexJugador' resta uno en el indexJugador que se añade al salir del bucle
		System.out.println("Ha ganado: "+jugadores.get(--indexJugador).toString());

	}

}
