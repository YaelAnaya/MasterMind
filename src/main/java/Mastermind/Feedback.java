package Mastermind;
import java.util.*;
//En esta clase se lleva la retroalimentción del juego
public class Feedback {
    private int [][] aciertos; //matriz en la que se guardan los aciertos de cada turno
    private static int turnos = 0; //turnos
    private static int tamañoDeJuego; //tamaño del juego

    public Feedback(int oportunidades, int tamañoDeJuego) {
        aciertos = new int[oportunidades][tamañoDeJuego];
        Feedback.tamañoDeJuego = tamañoDeJuego;
    }

    public int[][] getAciertos() {
        return aciertos;
    }

    public void setAciertos(int[][] aciertos) {
        this.aciertos = aciertos;
    }

    public static int getTurnos() {
        return turnos;
    }

    public static void setTurnos(int turnos) {
        Feedback.turnos = turnos;
    }

    public static int getTamañoDeJuego() {
        return tamañoDeJuego;
    }

    public static void setTamañoDeJuego(int tamañoDeJuego) {
        Feedback.tamañoDeJuego = tamañoDeJuego;
    }

    //Con este metodo obtenemos la combinacion en turno
    // para despues poder comprobar los aciertos
    public ArrayList<Canica> obtenerCombinacion(){
        return Decoder.getCombinaciones().get(turnos - 1);
    }

    /**
     * Metodo para comparar cuantos colores correctos tiene la combinacion ingresada
     * @param combinacion Cantidad de canicas con el color en combinacion
     * @param respuesta Cantidad de canicas con el color en respuesta
     * @return La cantidad de colores correctos
     */
    private int colorCorrecto(int combinacion, int respuesta){
        int coloresCorrecto = 0;
        if(combinacion > 0 && respuesta > 0){
            if(combinacion == respuesta)
                coloresCorrecto = 1;
        }
        return coloresCorrecto;
    }
    /**
     * Metodo para contar aciertos de la ultima combinacion ingresada
     * cuenta los colores de la combinacion y los colores de la respuesta
     * y los compara para sacar cuantos colores son iguales.
     * Luego, cuenta cuantos colores y posiciones son iguales.
     * Con esos datos añade ya sea 2, 1 o 0 al array de aciertos.
     */
    public void contadorDeAciertos(){
        //Se crea un vector con contadores para cada color de canica de combinacion
        int [] coloresCombinacion = new int[8];
        //Se crea un vector con contadores para los colores de la respuesta
        int [] coloresRespuesta = new int[8];

        //con este ciclo ponemos un 1 en el vector cada que identifique un color
        for(int i = 0; i < tamañoDeJuego; i++){
            //Se cuentan los colores de la combinacion ingresada
            switch (obtenerCombinacion().get(i).getColor()) {
                case "AZ":
                    coloresCombinacion[0]++;
                    break;
                case "AM":
                    coloresCombinacion[1]++;
                    break;
                case "RO":
                    coloresCombinacion[2]++;
                    break;
                case "NE":
                    coloresCombinacion[3]++;
                    break;
                case "CA":
                    coloresCombinacion[4]++;
                    break;
                case "NA":
                    coloresCombinacion[5]++;
                    break;
                case "MO":
                    coloresCombinacion[6]++;
                    break;
                case "BL":
                    coloresCombinacion[7]++;
                    break;
            }

            //Se cuentan los colores de la respuesta
            switch (Coder.getRespuesta().get(i).getColor()) {
                case "AZ":
                    coloresRespuesta[0]++;
                    break;
                case "AM":
                    coloresRespuesta[1]++;
                    break;
                case "RO":
                    coloresRespuesta[2]++;
                    break;
                case "NE":
                    coloresRespuesta[3]++;
                    break;
                case "CA":
                    coloresRespuesta[4]++;
                    break;
                case "NA":
                    coloresRespuesta[5]++;
                    break;
                case "MO":
                    coloresRespuesta[6]++;
                    break;
                case "BL":
                    coloresRespuesta[7]++;
                    break;
            }
        }

        //contadores de colores y posiciones correctas
        int coloresCorrectos = 0, posicionesCorrectas = 0;

        //Ciclo que compara los colores de combinacion con respuesta,
        //para saber que colores son acordes con la respuesta
        for (int i = 0; i < 8; i++) {
            coloresCorrectos += colorCorrecto(coloresCombinacion[i], coloresRespuesta[i]);
        }

        //Ciclo para saber cuantas canicas tienes el color y posicion correcto
        for(int i = 0; i < tamañoDeJuego; i++){
            Canica respuesta = Coder.getRespuesta().get(i); //guardamos las canicas en posicion i
            Canica combinacion = obtenerCombinacion().get(i);// en canicas auxiliares; respuesta y combinacion
            if(respuesta.getColor().equals(combinacion.getColor())) //comparamas si son son iguales
                posicionesCorrectas ++; //si estan en la misma posicion y son iguales, incrementa contador
        }

        //Ciclos para añadir valores en el array de aciertos
        //agrega un 2 en las posiciones i menores al contador
        //de posiciones correctas
        for(int i = 0; i < posicionesCorrectas; i++){
            aciertos[turnos - 1][i] = 2;
        }
        //retoma la posicion en la que se quedó el ciclo pasado
        //e introduce un 1 en las posiciones i menores a colores correctos
        for(int i = posicionesCorrectas; i < coloresCorrectos; i++){
            aciertos[turnos - 1][i] = 1;
        }
        //retoma la posiciones en la que se quedó el ciclo pasado
        //e introduce 0 en las posiciones restantes
        for(int i = coloresCorrectos; i < tamañoDeJuego; i++){
            aciertos[turnos - 1][i] = 0;
        }
    }

    /**
     * Metodo que identifica si la combinacion ingresada y la respuesta son iguales
     * @return True si son iguales, tonces hay victoria
     */
    public boolean victoria(){
        //Contador para ver cuantas canicas son iguales
        int canicasIguales = 0;

        //Ciclo que corre tamañoDeJuego veces
        for(int i = 0; i < tamañoDeJuego; i++){
            Canica respuesta = Coder.getRespuesta().get(i); //guardamos las canicas en posicion i
            Canica combinacion = obtenerCombinacion().get(i);// en canicas auxiliares; respuesta y combinacion

            //Si los colores de ambas canicas son iguales, se incrementa en
            // uno el contador de canicasIguales
            if(respuesta.getColor().equals(combinacion.getColor()))
                canicasIguales++;
        }

        //Si las canicas iguales y el tamañoDeJuego son iguales entonces
        // tenemos victoria. por lo que se retorna true
        return tamañoDeJuego == canicasIguales;
    }

    //Este metodo muestra los acierto obetinos en el turno
    public void mostrarAciertos(){
        System.out.print("Aciertos: [");
        for (int i = 0; i < tamañoDeJuego; i++) {
            System.out.print(aciertos[turnos - 1][i] + "\t");
        }
        System.out.println("]");
    }

    //Este metodo muestra la combinacion del turno pasado
    public void mostrarCombinacionPasada(){
        System.out.print("Combinacion pasada: [");
        for (int i = 0; i < tamañoDeJuego; i++) {
            System.out.print(Decoder.getCombinaciones().get(turnos - 1).get(i).getColor() + "\t");
        }
        System.out.println("]");
    }

    //muestra y calcula el puntaje obtenido por el jugador
    public void mostrarPuntajeFinal(int oportunidades){
        // calculamos restando 100 al porcentaje de
        // probabilidad que tiene de adivinar la respuesta
        //se guardan los puntos obtenidos
        float puntos = 100 - (((float)turnos/(float)oportunidades) * 100);
        System.out.println("Puntaje final : [ " + puntos + " ]");
    }
}
