import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Clase principal del programa
public class Hospital {
    public static void main(String[] args) {
        // Creamos una cola de prioridad para los pacientes
        PriorityQueue<Paciente> pq = new PriorityQueue<>();

        // Leer el archivo de pacientes y agregarlos a la cola de prioridad
        try {
            File file = new File("pacientes.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // Seleccionar los datos del paciente del archivo
                String[] datos = scanner.nextLine().split(",");
                // Crear un nuevo paciente con los datos y agregarlo a la cola de prioridad
                Paciente paciente = new Paciente(datos[0].trim(), datos[1].trim(), Character.toUpperCase(datos[2].trim().charAt(0)));
                pq.add(paciente);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Manejo de errores si no se puede encontrar el archivo
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }

        // Menú para interactuar con el sistema de atención de pacientes
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        while (seguir) {
            // Mostrar opciones disponibles
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Insertar paciente");
            System.out.println("2. Atender paciente");
            System.out.println("3. Mostrar orden de atención de pacientes");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            // Leer la opción seleccionada por el usuario
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    // Insertar un nuevo paciente
                    sc.nextLine(); // Limpiar el buffer del teclado
                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese el síntoma del paciente: ");
                    String sintoma = sc.nextLine();
                    System.out.print("Ingrese la prioridad del paciente (A-E): ");
                    char codigo = Character.toUpperCase(sc.next().charAt(0)); // Convertir a mayúscula
                    // Crear un nuevo paciente con los datos ingresados y agregarlo a la cola de prioridad
                    Paciente newPaciente = new Paciente(nombre, sintoma, codigo);
                    pq.add(newPaciente);
                    System.out.println("Paciente insertado correctamente.");
                    break;
                case 2:
                    // Atender al próximo paciente en la cola de prioridad
                    if (!pq.isEmpty()) {
                        Paciente pacienteDone = pq.poll();
                        System.out.println("Paciente atendido:");
                        pacienteDone.printInfo();
                    } else {
                        System.out.println("La cola de pacientes está vacía.");
                    }
                    break;
                case 3:
                    // Mostrar el orden de atención de los pacientes en la cola de prioridad
                    System.out.println("Orden de atención de pacientes:");
                    PriorityQueue<Paciente> copia = new PriorityQueue<>(pq);
                    while (!copia.isEmpty()) {
                        Paciente nextPaciente = copia.poll();
                        nextPaciente.printInfo();
                    }
                    break;
                case 4:
                    // Salir del programa
                    seguir = false;
                    break;
                default:
                    // Mensaje de error para opciones no válidas
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
        // Cerrar el scanner
        sc.close();
    }
}

// Clase que representa un paciente
class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigo;

    // Constructor para crear un nuevo paciente
    public Paciente(String nombre, String sintoma, char codigo ) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigo = codigo;
    }

    // Método para comparar pacientes por prioridad
    @Override
    public int compareTo(Paciente otro) {
        return this.codigo - otro.codigo;
    }

    // Método para imprimir la información del paciente
    public void printInfo() {
        System.out.println(nombre + ", " + sintoma + ", " + codigo);
    }
}
