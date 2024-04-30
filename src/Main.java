import java.math.BigInteger;
import java.util.*;
public class Main {

	private static String alfabeto;
	private static String mensaje;
	private static String k; 
	private static int n;
	private static int s;
	private static int mod;
	private static int eleccion;
	
	public static void main(String[] args) {
		
		alfabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
		mod = alfabeto.length();
		
		System.out.println("LONGITUD ALFABETO");
		System.out.println(alfabeto.length());
		
		Scanner in = new Scanner(System.in);
		System.out.println("¿Que desea hacer?");
		System.out.println("1. Codificaciónn con la versión clásica");
		System.out.println("2. Codificación con la versión en flujo");
		System.out.println("3. Decodificación con la versión clásica");
		System.out.println("4. Decodificación con la versión en flujo");
		
		int num = 0;
		try { num = Integer.parseInt(in.nextLine());} 
		catch(Exception e) {System.err.println("Error al introducir el numero");}
		
		System.out.println("Añade el mensaje que quieras codificar");
		mensaje = in.nextLine();
		System.out.println("Añade la clave de cifrado");
		k = in.nextLine();
	
		n = mensaje.length();
		
		switch(num) {
		case 1: eleccion = 1;
			codificacion();
			
			break;
		case 2: eleccion = 2;
			codificacion();
			
			break;
		case 3: eleccion = 1;
			decodificacionClasica();
			
			break;
		case 4: eleccion = 2;
			decodificacionEnFlujo();
			
			break;
		default:
			break;
		}
	
	}
	
	public static void codificacion() {
		
		//Array de la suma de las posiciones del mensaje y de k*
		ArrayList<Integer> posMensaje = new ArrayList<Integer>();
				
		//Tenemos que coger las posiciones del mensaje codificado en el ay almacenarlas en nuestra lista sumaZx
		for(int i = 0; i < mensaje.length(); i++) {
			for(int j = 0; j < alfabeto.length(); j++) {
				if(mensaje.charAt(i) == alfabeto.charAt(j)) {
					//Añadimos la posición de la letra en el alfabeto
					posMensaje.add(j);
				}
			}
		}
		
		System.out.println("POSICIONES DEL MENSAJE");
		for(int i = 0; i < posMensaje.size(); i++) {
			System.out.print(posMensaje.get(i) + " ");
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
		
		int mod2 = kEstrella.size();
		System.out.println("\nLA LONGITUD DE KESTRELLA ES = " + kEstrella.size());
		//System.out.println("LA LONGITUD DEL MENSAJE ES = " + posMensaje.size());
		
		/*
		 * Parte distinta a la version clasica
		 * ¡¡¡PUEDE CAMBIAR EN EL EXAMEN!!!
		 */
		if(k.length() < posMensaje.size()) {
			System.out.println("bbbbbbbbbbbbbbbbbbbb");
			System.out.println(eleccion);
			switch(eleccion) {
				case 1: versionClasica(posMensaje, kEstrella, mod2);
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
					break;
				case 2: versionEnFlujo(posMensaje, kEstrella);
					break;
				default:
					break;
			}
		}
		
		
		if(k.length() > posMensaje.size()) {
			//Solo cogemos los primeros numeros del mensaje
			System.out.println("FALTA POR HACER");
		}
		  
		System.out.println("");
		
		System.out.println("POSMENSASJE");
		for(int i = 0; i < posMensaje.size(); i++) {
			System.out.print(posMensaje.get(i) + " ");
		}
		
		System.out.println("\nKESTRELLA");
		for(int i = 0; i < kEstrella.size(); i++) {
			System.out.print(kEstrella.get(i) + " ");
		}
		
		System.out.println("\nLA LONGITUD DE KESTRELLA ES = " + kEstrella.size());
		System.out.println("LA LONGITUD DEL MENSAJE ES = " + posMensaje.size());
		
		ArrayList<Integer> sumaZx = new ArrayList<Integer>();
		 for(int i = 0; i < posMensaje.size(); i++) {
			 int num = Math.floorMod(posMensaje.get(i) + kEstrella.get(i), mod);
			 sumaZx.add(num);
		 }
		 
		 System.out.println("\nLAS POSICIONES DE SUMAZX SON = ");
		 for (int i = 0; i < sumaZx.size(); i++) {
			 System.out.print(sumaZx.get(i) + " ");
		 }
		 
		 /*
		  * En base a esto, sacamos la letra del alfabeto del mensaje
		  * El resultado lo almacenamos en el array msg
		  */
		 StringBuffer msg = new StringBuffer();
		 for(int i = 0; i < sumaZx.size(); i++) {
			 for(int y = 0; y < alfabeto.length(); y++) {
				 if(sumaZx.get(i) == y) {
					 msg.append(String.valueOf(alfabeto.charAt(y)));
					 //System.out.print(j + " ") ;
				 }
			 }
		 }

		 System.out.println("\nMENSAJE FINAL\n" + msg.toString());
		
	}
	
	public static void versionClasica(ArrayList<Integer> posMensaje, ArrayList<Integer> kEstrella, int mod2) {
		//Repetimos los numeros de kEstrella hasta que se llene tantos numeros como cifras tenga
		//el mensaje
		int j = 0;
		for(int i = kEstrella.size(); i < posMensaje.size(); i++) {
			kEstrella.add(kEstrella.get(j));
			j = (j+1)%mod2;
		}
		System.out.println("LONGITUD DE KESTRELLA ES = " + kEstrella.size());
	}
	
	public static void versionEnFlujo(ArrayList<Integer> posMensaje, ArrayList<Integer> kEstrella) {
		int xi = 0; // Variable acumuladora para cálculo de posición
		  int s = k.length(); // Tamaño inicial de `kEstrella`
		    
		    // Reinicio de `j` y control del bucle interno
		    int j = 0; // Reiniciar `j` para cada ciclo externo
		    
		    // Bucle externo para extender `kEstrella`
		    for (int z = kEstrella.size()-1; z < posMensaje.size()-1; z++) {
		        xi = 0; // Reiniciar `xi` para cada ciclo externo
		        
		        // Bucle interno para calcular posiciones
		        for (int i = kEstrella.size() - 1; j < s; i--) {
		            // Multiplicación y suma
		            xi += Math.floorMod(kEstrella.get(i) * kEstrella.get(j), mod);
		            //System.out.println("Valor de Xi: " + xi + " Valor de kEstrella y " + kEstrella.get(i)+ " j: " + kEstrella.get(j));
		            
		            if (xi < 0) {
		                xi += mod; // Ajustar si es negativo
		            }
		            
		            j++; // Incrementar `j` para evitar bucles indefinidos
		        }
		        
		        xi = Math.floorMod(xi, mod);
		        System.out.println( " xi con mod 84 = " + xi);
		        System.out.println("==================");

		        // Añadir el resultado final a `kEstrella`
		        kEstrella.add(xi);
		        
		        // Reiniciar `j` para la siguiente iteración del bucle externo
		        j = 0; 
		    }
	}


	public static void decodificacionClasica() {
		
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
		
		int mod2 = k.length();
		/*
		 * Parte distinta a la version clasica
		 * ¡¡¡PUEDE CAMBIAR EN EL EXAMEN!!!
		 */
		if(k.length() < sumaZx.size()) {
			//Repetimos los numeros de kEstrella hasta que se llene tantos numeros como cifras tenga
			//el mensaje
			int j = 0;
			for(int i = kEstrella.size(); i < sumaZx.size(); i++) {
				kEstrella.add(kEstrella.get(j));
				j = (j+1)%mod2;
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
			 num = Math.floorMod(num, mod);
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
	
	public static void decodificacionEnFlujo() {
		
		n = mensaje.length();
		s = k.length();
		
		//Array de la suma de las posiciones del mensaje y de k*
		ArrayList<BigInteger> sumaZx = new ArrayList<>();
		
		//Tenemos que coger las posiciones del mensaje codificado en el ay almacenarlas en nuestra lista sumaZx
		for(int i = 0; i < mensaje.length(); i++) {
			for(int j = 0; j < alfabeto.length(); j++) {
				if(mensaje.charAt(i) == alfabeto.charAt(j)) {
					//Añadimos la posición de la letra en el alfabeto
					sumaZx.add(BigInteger.valueOf(j));
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
		ArrayList<BigInteger> kEstrella = new ArrayList<>();
		
		for(int i = 0; i < k.length(); i++) {
			for(int z = 0; z < alfabeto.length(); z++) {		
				if((k.charAt(i)) == alfabeto.charAt(z)) {
					//Añadimos la posicion en el alfabeto
					kEstrella.add(BigInteger.valueOf(z));
				}
			}
		}
		
		System.out.println("\nPOSICIONES DE KESTRELLA");
		for(int i = 0; i < kEstrella.size(); i++) {
			System.out.print(kEstrella.get(i) + " ");
		}
		
		System.out.println("\nLAS Xi SON = ");
		BigInteger xi = BigInteger.ZERO;
		if(k.length() < sumaZx.size()) {
			BigInteger num = BigInteger.ZERO;
			//i >= s+1 que es la nueva posicion a guardar en kEstrella
			//pero como estamos en java y se empieza a contrar desde el 0 i >= s
			//Implementación de la recurrencia
			//PUEDE CAMBIAR EN EL EXAMEN
			int j = 0;
			for(int z = kEstrella.size()-1; z < sumaZx.size()-1; z++) {
				for(int i = kEstrella.size()-1; j < s; i--) {
					//System.out.println("xi es: " + kEstrella.get(j) + " * " + kEstrella.get(i));
					xi = xi.add(kEstrella.get(i).multiply(kEstrella.get(j))); // Multiplicación
					//System.out.print(xi + " + ");
					num = xi.remainder(BigInteger.valueOf(mod)); // Módulo con BigInteger
	                if (num.compareTo(BigInteger.ZERO) < 0) {
	   	             num = num.add(BigInteger.valueOf(mod));
	   	         	}
	                xi = num;
					j++;
				}
				//System.out.print("= " + xi + "\n");
				
                //System.out.println("xi es = " + xi);
                //System.out.println();
				System.out.println(xi + " mod 84 = " + num);
				j = 0; //reseteamos j
				kEstrella.add(xi);
				xi = BigInteger.ZERO; //reseteamos xi
			}
			
		}
		
		
		if(k.length() > sumaZx.size()) {
			//Solo cogemos los primeros numeros del mensaje
			System.out.println("FALTA POR HACER");
		}
		
		System.out.println("\nSUMAZX ES = ");
		for(int i = 0; i < sumaZx.size(); i++) {
			System.out.print(sumaZx.get(i) + " ");
		}
		
		System.out.println("");
		System.out.println("KESTRELLA ES = ");
		for(int i = 0; i < kEstrella.size(); i++) {
			System.out.print(kEstrella.get(i) + " ");
		}
		
		System.out.println("\nLongitud de KEstrella = " + kEstrella.size());
		System.out.println("Longitud de sumaZx = " + sumaZx.size());
		
		
		/*
		 * Ahora que ya tenemos la suma en Zx y K* podemos restarlas
		 * y así sacar las posiciones de nuestro mensaje
		 * 
		 * Como empezamos a contar desde 0, nuestro modulo es mod-1
		 */
		ArrayList<BigInteger> posMensaje = new ArrayList<>();
		 for(int i = 0; i < sumaZx.size(); i++) {
			 BigInteger resultado = sumaZx.get(i).subtract(kEstrella.get(i));
	         resultado = resultado.remainder(BigInteger.valueOf(mod)); //Para el modulo
	         // Si el resultado es negativo, ajustamos para que sea positivo
	         if (resultado.compareTo(BigInteger.ZERO) < 0) {
	             resultado = resultado.add(BigInteger.valueOf(mod));
	         }
			 posMensaje.add(resultado);
		 }
		 
		 for (int i = 0; i < posMensaje.size(); i++) {
			 System.out.print(posMensaje.get(i) + " ");
		 }
		 
		 /*
		  * En base a esto, sacamos la letra del alfabeto del mensaje
		  * El resultado lo almacenamos en el array msg
		  */
		 StringBuffer msg = new StringBuffer();
		 for (BigInteger pos : posMensaje) {
	            int posInt = pos.intValue(); // Convertimos a int para obtener el índice del alfabeto
	            msg.append(alfabeto.charAt(posInt));
	     }
		 
		 System.out.println("\nMENSAJE FINAL\n" + msg.toString());
	}
}
