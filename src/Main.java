import java.util.*;
public class Main {

	private static String alfabeto;
	
	public static void main(String[] args) {
		
		alfabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
		
		System.out.println("LONGITUD ALFABETO");
		System.out.println(alfabeto.length());
		
		Scanner in = new Scanner(System.in);
		System.out.println("¿Que desea hacer?");
		System.out.println("1. Codificaciónn con la versión clásica");
		System.out.println("2. Codificación con la versión en flujo");
		System.out.println("3. Decodificación");
		
		int num = 0;
		try { num = Integer.parseInt(in.nextLine());} 
		catch(Exception e) {System.err.println("Error al introducir el numero");}
		
		
		switch(num) {
		case 1: versionClasica();
			break;
		case 2: versionEnFlujo();
			break;
		case 3: decodificacion();
			break;
		default:
			break;
		}
	
	}
	
	public static void versionClasica() {
		
	}
	
	public static void versionEnFlujo() {
		
	}

	public static void decodificacion() {
		//Array de la suma de las posiciones del mensaje y de k*
		ArrayList<Integer> sumaZx = new ArrayList<Integer>();
		
	}
}
