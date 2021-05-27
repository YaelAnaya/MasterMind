package Mastermind;
import java.util.*;
//en esta clase se lleva acabo el juego de MasterMind
public class Mastermind {
    public static void main(String[] args) {
        String opcion; //Variable que servira para saber si quiere jugar de nuevo
        do{
            int tamañoDeJuego; //Cantidad de canicas de la respuesta
            int oportunidades; //Cantidad de oportunidades para adivinar
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bienvenid@ a MASTERMIND.\n"
                    + "Tamaño de juego(4/6): ");

            //Si ingresa 6 o mas, el juego es de 6
            //Si ingresa menos de 6, el juego es de 4
            if (scanner.nextInt() >= 6) {
                tamañoDeJuego = 6;
            } else {
                tamañoDeJuego = 4;
            }

            //Se pregunta e ingresa la cantidad de oportunidades, si la respuesta es mayor
            // que 15 oportunidades == 15, en caso de ser <= 0, oportunidades == 1
            System.out.print("Oportunidades (1/15): ");

            if(scanner.nextInt() >= 15) {
                oportunidades = 15;
            }
            else{
                oportunidades = 1;
            }

            //Se crean instancias de Decoder, Coder y Feedback
            Decoder decoder = new Decoder(oportunidades);
            Coder coder = new Coder(tamañoDeJuego);
            Feedback feedback = new Feedback(oportunidades, tamañoDeJuego);

            //Se crea la respuesta
            coder.crearRespuesta();

            //Variable que nos dira si hay victoria o no
            boolean victoria = false;

            //ciclo que va a correr el juego dependiendo de las oportunidades elegidas por el jugador
            for (int i = 0; i < oportunidades; i++) {
                //Llamamos al metodo estatico de getTurnos dentro de feedback
                //para mostrar el turno en el que se encuentra el jugador y
                //le sumamos un 1, porque la variable se inicializa en 0
                System.out.println("Turno: [ " + (Feedback.getTurnos() + 1) + " ]");

                decoder.ingresarCombinacion();
                feedback.contadorDeAciertos();
                feedback.mostrarAciertos();
                feedback.mostrarCombinacionPasada();
                //comprobamos si hay una victoria; retorna true si hay victoria
                victoria = feedback.victoria();
                if(victoria) {
                    //Se muestra un mensaje dependiendo de si hubo victoria
                    System.out.println("¡FELICIDADES, GANASTE! :D");
                    break;
                }
            }
            //Si victoria = false imprime el siguiente mensaje
            if(!victoria)
                System.out.println("¡SUERTE PARA LA PROXIMA! :(");

            //Se imprime la respuesta
            coder.mostrarRespuesta();
            //Mostramos el puntaje obtenido por el jugador
            feedback.mostrarPuntajeFinal(oportunidades);

            //Se pregunta si quiere jugar de nuevo
            System.out.print("¿Jugar de nuevo?(Si/No): ");
            opcion = scanner.next().toLowerCase();
            System.out.println();

            //El ciclo del juego completo continua mientras se quiera seguir jugando
        }while (opcion.equals("si"));

    }
}
