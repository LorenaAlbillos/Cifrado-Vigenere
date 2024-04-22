import java.util.*;
public class Main {

	private static String alfabeto;
	private static String mensaje;
	private static String k; 
	
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
		Scanner in = new Scanner(System.in);
		System.out.println("Añade el mensaje que quieras decodificar");
		mensaje = in.nextLine();
		System.out.println("Añade la clave de cifrado");
		k = in.nextLine();
		
		
		//Array de la suma de las posiciones del mensaje y de k*
		ArrayList<Integer> sumaZx = new ArrayList<Integer>();
		
		//Tenemos que coger las posiciones del mensaje codificado en el ay almacenarlas en nuestra lista sumaZx
		for(int i = 0; i < mensaje.length(); i++) {
			for(int j = 0; j < alfabeto.length(); j++) {
				if(mensaje.charAt(i) == alfabeto.charAt(j)) {
					//Añadimos la posición de la letra en el alfabeto
					sumaZx.add(j);
				}
			}
		}
		
		System.out.println("POSICIONES DE SUMAZX");
		for(int i = 0; i < sumaZx.size(); i++) {
			System.out.print(sumaZx.get(i) + " ");
		}
		
		/**
		 * Creación de nuestra clave k*
		 * Nuestra clave K* tiene que tener la misma longitud de nuestra lista
		 * Para ello, tenemos que coger la posicion de las letras de la clave en el alfabeto y lo
		 * almacenamos en la lista kEstrella
		 */
		ArrayList<Integer> kEstrella = new ArrayList<Integer>();
		
		for(int i = 0; i < k.length(); i++) {
			for(int z = 0; z < alfabeto.length(); z++) {		
				if((k.charAt(i)) == alfabeto.charAt(z)) {
					//Añadimos la posicion en el alfabeto
					kEstrella.add(z);
				}
			}
		}
		
		int mod = kEstrella.size();
		if(k.length() < sumaZx.size()) {
			//Repetimos los numeros de kEstrella hasta que se llene tantos numeros como cifras tenga
			//el mensaje
			for(int i = k.length(); i < sumaZx.size(); i++) {
				int num = i%mod;
				kEstrella.add(num);
			}
		}
		
		if(k.length() > sumaZx.size()) {
			//Solo cogemos los primeros numeros del mensaje
			System.out.println("FALTA POR HACER");
		}
		
		
		System.out.println("");
		System.out.println("KESTRELLA");
		for(int i = 0; i < kEstrella.size(); i++) {
			System.out.print(kEstrella.get(i) + " ");
		}
		
		System.out.println("\nLongitud de KEstrella = " + kEstrella.size());
		System.out.println("Longitud de sumaZx = " + sumaZx.size());
		
		
		/*
		 * Ahora que ya tenemos la suma en Zx y K* podemos restarlas
		 * y así sacar las posiciones de nuestro mensaje
		 */
		ArrayList<Integer> posMensaje = new ArrayList<Integer>();
		 for(int i = 0; i < sumaZx.size(); i++) {
			 int num = sumaZx.get(i) - kEstrella.get(i);
			 posMensaje.add(num);
		 }
		 
		 for (int i = 0; i < posMensaje.size(); i++) {
			 System.out.print(posMensaje.get(i) + " ");
		 }
		 
		 /*
		  * En base a esto, sacamos la letra del alfabeto del mensaje
		  * El resultado lo almacenamos en el array msg
		  */
		 StringBuffer msg = new StringBuffer();
		 for(int i = 0; i < posMensaje.size(); i++) {
			 for(int j = 0; j < alfabeto.length(); j++) {
				 if(posMensaje.get(i) == j) {
					 msg.append(String.valueOf(alfabeto.charAt(j)));
					 System.out.print(j + " ") ;
				 }
			 }
		 }
		 
		 System.out.println("\nMENSAJE FINAL\n" + msg.toString());
	}
}
