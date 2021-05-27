package Mastermind;
import java.util.*;
//Clase que genera el codigo aleatorio
public class Coder {
    private static ArrayList<Canica> respuesta; //ArrayList para guardar la respuesta
    private final String[] color = {"AZ", "AM", "RO", "NE", //Vector con los posibles colores
                                    "CA", "NA", "MO", "BL"};
    private int tamañoDeJuego; //cantidad de canicas de la respuesta

    public Coder(int tamañoDeJuego) {
        this.tamañoDeJuego = tamañoDeJuego;
        respuesta = new ArrayList<>(tamañoDeJuego);
    }

    public static ArrayList<Canica> getRespuesta() {
        return respuesta;
    }

    public static void setRespuesta(ArrayList<Canica> respuesta) {
        Coder.respuesta = respuesta;
    }

    //Con este metodo creamos una respuesta aleatoria
    public void crearRespuesta(){
        //El ciclo corre mientras 'respuesta' no este lleno.
        while (respuesta.size() < tamañoDeJuego){
            String color = colorRandom(); //creamos el código aleatorio
            Canica canica = new Canica(color);//creamos una canica con el color random
            boolean duplicado = false;        //determinara si la canica está duplicada

            /*
                Ciclo foreach para cada canica dentro del arraylist respuesta.
                Para cada canica dentro del arraylist respuesta se comparara la nueva
                canica creada. Si se encuentra que hay dos iguales se marcara
                como duplicada y no se agregará.
            */

            for(Canica canicaDuplicada : respuesta){
                if (canica.getColor().equals(canicaDuplicada.getColor())) {
                    duplicado = true;
                    break;
                }
            }
            //Si la canica no es marcada como duplicada, se añadira al ArrayList de respuesta
            if(!duplicado)
                respuesta.add(canica);
        }
    }
    //Metodo que retorna un color de una posicion random de los clores disponibles
    public String colorRandom(){
        Random random = new Random();
        int num = random.nextInt(8);
        return color[num];
    }

    //Este método imprime la respuesta
    public void mostrarRespuesta(){
        System.out.print("Respuesta[");
        for (Canica canica : respuesta) {
            System.out.print(canica.getColor() + "\t");
        }
        System.out.println("]");
    }

}
