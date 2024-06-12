import java.util.Date;
import java.util.Scanner;

public class Tour {
    private Date dateInicio;
    private Date dateFinal;
    private Date dateInicioVerano;
    private Date dateFinalVerano;
    private int eleccion;
    // verano empieza desde el 15 de juanio hasta 20 de septiembre

    Tour() {

    }

    public void selectAPlan() {
        Scanner in = new Scanner(System.in);
        System.out.println("Elija un plan: ");
        System.out.println("1.Visitar la mayor cantidad de landmarks lo mas economico posible");
        System.out.println("2.Visitar la mayor cantidad de landmarks lo mas rapido posible");
        System.out.println("3.??? con comodidad pero si es necesario perder algunas de landmarks");
        System.out.println("ANother stuf");
        eleccion = in.nextInt();
        in.close();
    }

    public void selectADate() {
        System.out.println("Ingrese la fecha de partida");
        System.out.println("Ingrese la fecha de regreso");

        // if (estaEnTemporadaAlta) {
        // precios +30%;
        // }

        // estaEnTemporada baja : -10%
        //temporadaRegular +- 5%
    }

    public void calcularViaje() {
        switch (getEleccion()) {
            case 1:
                //mas rapido
                Herramienta h = new Herramienta();
                h.construirGrafo();
                //h.Fastest();
                break;
            case 2:
                //mas economico
                break;
            case 3:
                //mas confort maybe???
                break;
            case 4:
                //por si aparece alguna otra cosa
                break;
            default:
                //TODO handle exceptions
                break;
        }
        // calcEconomicAsPosible();
        // calcFastestAsPossible();
        // calcConfortableAsPossible();
    }

    public int getEleccion(){
        return eleccion;
    }

}
