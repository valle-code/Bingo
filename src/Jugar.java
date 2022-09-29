import java.util.Scanner;

public class Jugar {

	public static void main(String[] args) {
		Sala sala = new Sala();
		Scanner lector = new Scanner(System.in);
		
		do {
			sala.jugarPartidaBingo();
			System.out.println("Pulsa ENTER para jugar otra ronda");
		} while (lector.nextLine().equals("") && sala.getJugadores().size()>1);
		
		System.out.println(sala.toString());
		System.out.println("\n\n---------------------------- LA SALA HA CERRADO ----------------------------");
		lector.close();
	}

}
