package Mastermind;
import java.util.*;
//Clase que lleva el control de las combinaciones propuestas por el jugador
public class Decoder {
    private static ArrayList<ArrayList<Canica>> combinaciones; //ArrayLists que guarda las combinaciones
    private static HashSet<ArrayList<Canica>> combinacionSinRepetir; //HashSet que utilizaremos para
                                                                    // no tener combinaciones repetidas

    public Decoder(int oportunidades) {
        combinaciones = new ArrayList<>(oportunidades);
        combinacionSinRepetir = new HashSet<>(oportunidades);
    }

    public static ArrayList<ArrayList<Canica>> getCombinaciones() {
        return combinaciones;
    }

    public static void setCombinaciones(ArrayList<ArrayList<Canica>> combinaciones) {
        Decoder.combinaciones = combinaciones;
    }

    //Metodo para ingresar una combinacion
    //Se ingresa el String del color y con eso se crea la canica y
    // se añade al ArrayList.
    public void ingresarCombinacion(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Canica> combinacion = new ArrayList<>(Feedback.getTamañoDeJuego());
        int turnos = Feedback.getTurnos(); //turnos que se aumentan en la clase Feedback

        //guardaremos las combinaciones introducidas por el usuario en este arraylist
        System.out.println("\tCodigos de Colores: [AZ, AM, RO, NE, CA, NA, MO, BL]");
        System.out.println("\t\t[Ingrese canicas] ");

        //Aquí se agregan las respuestas mediante un ciclo for delimitado en el tamaño de juego
        for(int i = 0; i< Feedback.getTamañoDeJuego(); i++) {
            System.out.print("\t\t\tCanica " + (i + 1) + ": ");
            Canica canica = new Canica(scanner.nextLine().toUpperCase());
            combinacion.add(canica);
        }
        //si la combinacion se pudo agregar, se aumentan los turnos del jugador
        boolean exito = añadirCombinacion(combinacion);
        if (exito) {
            turnos ++;
            Feedback.setTurnos(turnos);
        }
    }

    /**
     * Metodo para añadir una combinacion y comprobar que no sea repetida
     * @return True si la combinacion se añadio con exito y no es repetida
     */
    public boolean añadirCombinacion(ArrayList<Canica> combinacion) {
        //Booleano que nos dice si hubo exito al añadir la combinacion a tablero
        //y será true cuando la combinacion no esté dentro del hashset
        boolean exito = combinacionSinRepetir.add(combinacion);

        if (exito)
            //si no está repetida, se agrega al arraylist de combinaciones
            combinaciones.add(combinacion);
        return exito;
    }
}
