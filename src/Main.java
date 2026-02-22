import java.util.Scanner;
import excepciones.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CentroAtencion centro = new CentroAtencion();
        int opcion;

        do {
            System.out.println("\n===== MENU CENTRO DE ATENCION =====");
            System.out.println("1. Registrar turno");
            System.out.println("2. Atender siguiente");
            System.out.println("3. Cancelar turno por ID");
            System.out.println("4. Insertar turno urgente");
            System.out.println("5. Mostrar cola");
            System.out.println("6. Ver estadisticas");
            System.out.println("0. Salir");
            System.out.print("Seleccione opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    try {
                        System.out.print("Ingrese ID: ");
                        int id = sc.nextInt();

                        System.out.print("Ingrese tipo (P/G): ");
                        char tipo = sc.next().charAt(0);

                        System.out.print("Ingrese tiempo estimado (1-60): ");
                        int tiempo = sc.nextInt();

                        centro.registrarTurno(id, tipo, tiempo);
                        System.out.println("Turno registrado correctamente.");
                    } catch (IdRepetidoException | TipoInvalidoException | TiempoInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    centro.atenderSiguiente();
                    System.out.println("Se atendio el siguiente turno.");
                    break;

                case 3:
                    System.out.print("Ingrese ID a cancelar: ");
                    int idCancelar = sc.nextInt();
                    centro.cancelarTurnoPorId(idCancelar);
                    break;

                case 4:
                    try {
                        System.out.print("Ingrese ID: ");
                        int idUrgente = sc.nextInt();

                        System.out.print("Ingrese tipo (P/G): ");
                        char tipoUrgente = sc.next().charAt(0);

                        System.out.print("Ingrese tiempo estimado: ");
                        int tiempoUrgente = sc.nextInt();

                        System.out.print("Ingrese posicion donde insertar: ");
                        int pos = sc.nextInt();

                        centro.insertarTurnoUrgente(idUrgente, tipoUrgente, tiempoUrgente, pos);
                        System.out.println("Turno urgente insertado.");
                    } catch (IdRepetidoException | TipoInvalidoException | TiempoInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    centro.mostrarCola();
                    break;

                case 6:
                    centro.estadisticas();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
