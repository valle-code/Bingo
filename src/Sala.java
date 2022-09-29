import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Sala {

	// Atributos
	private ArrayList<Integer> bombo;
	private ArrayList<Jugador> jugadores;
	private float bote;

	public Sala() {
		this.bombo = rellenarBombo();
		this.jugadores = generarJugadores();
		this.bote = 0.f;
	}

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

	public float getBote() {
		return bote;
	}

	public void setBote(int bote) {
		this.bote = bote;
	}

	@Override
	public String toString() {
		String jugadoresString = "\n";
		
		for (Jugador player : jugadores) {
			jugadoresString += player.toString() + "\n";
		}
		
		return "Jugadores=" + jugadores.size() + ", bote=" + bote + jugadoresString;
	}

	/**
	 * 
	 * Genera un bombo del 1 al 99 y lo desordena
	 * 
	 * @author Gonzalo
	 * @return ArrayList con el bombo
	 */
	private ArrayList<Integer> rellenarBombo() {
		setBombo(new ArrayList<Integer>()); // Vacia el bombo que haya
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		for (int i = 1; i <= 99; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		return numeros;
	}

	/**
	 * 
	 * Genera los jugadores de la sala
	 * 
	 * @author Daniel
	 * @return ArrayList con los jugadores
	 */
	private ArrayList<Jugador> generarJugadores() {
		Random aleatorio = new Random();
		int numJugadores = aleatorio.nextInt(24) + 2;
		
		ArrayList<Jugador> listaPlayer = new ArrayList<Jugador>();

		for (int i = 1; i < numJugadores + 1; i++) {
			listaPlayer.add(new Jugador("Jugador " + i));
		}

		System.out.println("Ya han entrado todos los "+numJugadores+" jugadores\n");
		return listaPlayer;
	}

	/**
	 * 
	 * Saca una bola del bombo y la elimina
	 * 
	 * @author Daniel
	 * 
	 * @return el numero de la bola
	 */
	private int sacarBola() {
		return bombo.remove(0);
	}

	/**
	 * 
	 * Prepara una nueva partida llenando el bombo 
	 * y vendiendo los cartones a los jugadores
	 * 
	 * @author Gonzalo
	 */
	public void iniciarRonda() {
		setBombo(rellenarBombo());
		
		for (Jugador player : getJugadores()) {
			player.comprarCartones();
		}

		// Dos € por carton
		bote += cantidadDeCartonesTotales() * 2;
	}

	/**
	 * 
	 * Cuenta la cantidad total de cartones y elimina a quien
	 * haya comprado 0 cartones (lo haya decidio o no pudiera)
	 * 
	 * @author Gonzalo
	 * 
	 * @return La cantidad de cartones
	 */
	private int cantidadDeCartonesTotales() {
		int cantidadTotal = 0;
		int numCartonesJ;
		System.out.println("\n");
		
		for (int i=0; i<jugadores.size(); i++) {
			Jugador player = jugadores.get(i);
			numCartonesJ = player.getNumCartones();
			
			if(numCartonesJ > 0) {
				cantidadTotal += numCartonesJ;
			} else {
				System.out.println(player.getNombre()+" se ha decido ir de la sala con "+player.getDinero()+"€");
				jugadores.remove(jugadores.indexOf(player));
				i--; //reajuste por eliminar
			}
		}
		
		System.out.println("\n");
		return cantidadTotal;
	}


	/**
	 * Juega una ronda de bingo y al final elimina a los que se quedan sin dinero
	 * 
	 * @author Daniel y Gonzalo
	 */
	public void jugarPartidaBingo() {
		int indexJugador;
		int indexCarton;
		int cantidadJugadores;
		int cantidadCartonesJ;
		ArrayList<Carton> cartonesJugador;
		Carton carton;
		boolean lineaCantada = false;
		boolean bingoCantado = false;
		int bola;

		iniciarRonda();
		
		cantidadJugadores = jugadores.size();
		
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
						lineaCantada = true;
						repartirPremio(indexJugador, "LINEA");
					} else if (carton.checkBingo()) {
						bingoCantado = true; // Fuerza finalizar
						repartirPremio(indexJugador, "BINGO");
					}

					indexCarton++;
				} while (!bingoCantado && indexCarton < cantidadCartonesJ);

				indexJugador++;
			} while (!bingoCantado && indexJugador < cantidadJugadores);

		} while (!bingoCantado);
		
		limpiarEnBancarrota();
	}
	
	/**
	 * Da el premio de la linea o el bingo al jugador ganador
	 * @author Daniel 
	 * 
	 * @param index numero del jugador
	 * @param tipo "LINEA"/"BINGO"
	 */
	private void repartirPremio(int index, String tipo) {
		double premio = 0;
		
		switch(tipo) {
		
		case "LINEA":
			premio = Math.round(0.18*bote);
			break;
			
		case "BINGO":
			premio = Math.round(0.48*bote);
			break;
		}
		
		System.out.println("\n\n¡¡"+tipo+"!! El " + jugadores.get(index).getNombre() + " recibe " + premio + " €\n\n");
		jugadores.get(index).setDinero(jugadores.get(index).getDinero()+(premio));
		bote -= premio;
	}
	
	/**
	 * @author Gonzalo
	 * Elimina de la lista todos los jugadores con dinero insuficiente
	 */
	private void limpiarEnBancarrota() {
		System.out.println("\n");
		
		for (int i=0; i<jugadores.size(); i++) {
			Jugador player = jugadores.get(i);
			
			if(player.getDinero() < 2) {
				System.out.println(player.getNombre()+" se ha quedado sin dinero :(");
				jugadores.remove(jugadores.indexOf(player));
				i--; //reajuste por eliminar
			}
		}
		
		System.out.println("\n");
	}
}