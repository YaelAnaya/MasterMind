package Mastermind;

//En esta clase se lleva la retroalimentción del juego
public class Feedback {

    public static void agregarCombinacion(boolean combinacionNoDuplicada){
        if(combinacionNoDuplicada)
            System.out.println("Combinacion ingresada exitosamente.");
        else
            System.out.println("Combinacion repetida.");
    }
}
