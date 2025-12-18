package ed.u2;

import java.util.Scanner;
import java.time.LocalDateTime;

import ed.u2.util.CsvReader;
import ed.u2.sorting.BubbleSort;
import ed.u2.sorting.InsertionSort;
import ed.u2.sorting.SelectionSort;
import ed.u2.sorting.SortStats;
import ed.u2.search.ArraySearch;
import ed.u2.search.SearchResult;

public class main {

    private static final Scanner scanner = new Scanner(System.in);

    private Comparable[] citas;
    private Comparable[] inventario;

    private boolean citasCargadas = false;
    private boolean citasOrdenadas = false;
    private boolean inventarioCargado = false;
    private boolean inventarioOrdenado = false;
    
    // RUTA DE ARCHIVOS CSV
    private static final String CSV_CITAS = "src/main/java/ed/u2/resources/citas_100.csv";
    private static final String CSV_CITAS_CASI_ORDENADAS = "src/main/java/ed/u2/resources/citas_100_casi_ordenadas.csv";
    private static final String CSV_INVENTARIO = "src/main/java/ed/u2/resources/inventario_500_inverso.csv";
    private static final String CSV_PACIENTES = "src/main/java/ed/u2/resources/pacientes_500.csv";

    // OPCIONES DEL MENÚ PRINCIPAL
    public void mostrarMenuPrincipal() {
        System.out.println("\n----------------------------------------");
        System.out.println("        HOSPITAL VETERINARIO UNL        ");
        System.out.println("----------------------------------------");
        System.out.println("1. Agenda de Citas");
        System.out.println("2. Gestión de Pacientes");
        System.out.println("3. Control de Inventario");
        System.out.println("4. Comparar Algoritmos de Ordenamiento");
        System.out.println("5. Salir");
    }

    // OPCIONES DEL MENÚ DE CITAS
    public void mostrarMenuCitas() {
        System.out.println("\n----------------------------------------");
        System.out.println("           AGENDA DE CITAS              ");
        System.out.println("----------------------------------------");
        System.out.println("1. Cargar citas normales"); // listo
        System.out.println("2. Cargar citas casi ordenadas"); // listo
        System.out.println("3. Ordenar citas"); // listo
        System.out.println("4. Buscar cita exacta"); // Haciendo
        System.out.println("5. Buscar citas por rango");
        System.out.println("6. Volver al menú principal");
    }

    // OPCIONES DEL MENÚ DE ORDENAMIENTO DE CITAS
    public void mostrarMenuOrdenarCitas() {
        System.out.println("\n----------------------------------------");
        System.out.println("     ORDENAR CITAS POR FECHA Y HORA     ");
        System.out.println("----------------------------------------");
        System.out.println("1. Ordenar con metodo Burbuja");
        System.out.println("2. Ordenar con metodo de Inserción");
        System.out.println("3. Ordenar con metodo de Selección");
        System.out.println("4. Volver al menú de citas");
    }   

    // OPCIONES DEL MENÚ DE GESTIÓN DE PACIENTES
    public void mostrarMenuPacientes() {
        System.out.println("\n----------------------------------------");
        System.out.println("        GESTIÓN DE PACIENTES            ");
        System.out.println("----------------------------------------");
        System.out.println("1. Cargar pacientes");
        System.out.println("2. Buscar primer paciente por apellido");
        System.out.println("3. Buscar último paciente por apellido");
        System.out.println("4. Buscar pacientes con prioridad 1");
        System.out.println("5. Volver al menú principal");
    }

    // OPCIONES DEL MENÚ DE CONTROL DE INVENTARIO
    public void mostrarMenuInventario() {
        System.out.println("\n----------------------------------------");
        System.out.println("        CONTROL DE INVENTARIO           ");
        System.out.println("----------------------------------------");
        System.out.println("1. Cargar inventario");
        System.out.println("2. Ordenar inventario por stock");
        System.out.println("3. Buscar producto por stock");
        System.out.println("4. Volver al menú principal");
    }
    
    // OPCIONES DEL MENÚ DE COMPARACIÓN DE ALGORITMOS DE ORDENAMIENTO
    public void mostrarMenuComparacion() {
        System.out.println("\n----------------------------------------");
        System.out.println("  COMPARAR ALGORITMOS DE ORDENAMIENTO   ");
        System.out.println("----------------------------------------");
        System.out.println("1. Comparar con citas");
        System.out.println("2. Comparar con inventario");
        System.out.println("3. Volver al menú principal");
    }
    
    // MENÚ PRINCIPAL
    public void menuPrincipal() {
        
        String opcion;

        do {
            mostrarMenuPrincipal();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuCitas();
                    break;
                case "2":
                    menuPacientes();
                    break;
                case "3":
                    menuInventario();
                    break;
                case "4":
                    break;
                case "5":
                    System.out.println("----------------------------------------");
                    System.out.println("Saliendo del programa... ¡Adiós!\n");
                    break;
                default:
                    System.out.println("\n----------------------------------------");
                    System.out.println("            OPCIÓN INVÁLIDA             ");
                    System.out.println("----------------------------------------");
                    System.out.println("Por favor, ingrese un número del 1 al 5.\n");
            }

        } while (!opcion.equals("5"));
    }

    // MENÚ DE CITAS
    public void menuCitas() {
        
        String opcion;

        do {
            mostrarMenuCitas();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
            case "1":
                cargarCitasNormales();
                break;
            case "2":
                cargarCitasCasiOrdenadas();
                break;
            case "3":
                menuOrdenarCitas();
                break;
            case "4":
                buscarCitaExacta();
                break;
            case "5":
                buscarCitasPorRango();
                break;
            case "6":
                System.out.println("----------------------------------------");
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("----------------------------------------");
                System.out.println("Opción inválida.");
        }

        } while (!opcion.equals("6"));
    }

    // MENÚ DE ORDENAMIENTO DE CITAS
    public void menuOrdenarCitas() {

        String opcion;

        do {
            mostrarMenuOrdenarCitas();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
            case "1":
                ordenarCitasBurbuja();
                break;
            case "2":
                ordenarCitasInsercion();
                break;
            case "3":
                ordenarCitasSeleccion();
                break;
            case "4":
                System.out.println("----------------------------------------");
                System.out.println("Volviendo al menú de citas...");
                break;
            default:
                System.out.println("\n----------------------------------------");
                System.out.println("            OPCIÓN INVÁLIDA             ");
                System.out.println("----------------------------------------");
                System.out.println("Por favor, ingrese un número del 1 al 4.\n");
        }

        } while (!opcion.equals("4"));
    }

    // MENÚ DE GESTIÓN DE PACIENTES
    public void menuPacientes() {

        String opcion;

        do {
            mostrarMenuPacientes();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    cargarPacientes();
                    break;
                case "2":
                    buscarPrimerPacientePorApellido();
                    break;
                case "3":
                    buscarUltimoPacientePorApellido();
                    break;
                case "4":
                    buscarPacientesConPrioridad1();
                    break;
                case "5":
                    System.out.println("----------------------------------------");
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("\n----------------------------------------");
                    System.out.println("            OPCIÓN INVÁLIDA             ");
                    System.out.println("----------------------------------------");
                    System.out.println("Por favor, ingrese un número del 1 al 5.\n");
            }

        } while (!opcion.equals("5"));
    }

    // MENÚ DE CONTROL DE INVENTARIO
    public void menuInventario() {

        String opcion;

        do {
            mostrarMenuInventario();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    cargarInventario();
                    break;
                case "2":
                    ordenarInventario();
                    break;
                case "3":
                    buscarProductoPorStock();
                    break;
                case "4":
                    System.out.println("----------------------------------------");
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("\n----------------------------------------");
                    System.out.println("            OPCIÓN INVÁLIDA             ");
                    System.out.println("----------------------------------------");
                    System.out.println("Por favor, ingrese un número del 1 al 4.\n");
            }

        } while (!opcion.equals("4"));
    }

    // MENÚ DE COMPARACIÓN DE ALGORITMOS DE ORDENAMIENTO
    public void menuComparacion() {

        String opcion;

        do {
            mostrarMenuComparacion();
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    compararAlgoritmosCitas();
                    break;
                case "2":
                    compararAlgoritmosInventario();
                    break;
                case "3":
                    System.out.println("----------------------------------------");
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("\n----------------------------------------");
                    System.out.println("            OPCIÓN INVÁLIDA             ");
                    System.out.println("----------------------------------------");
                    System.out.println("Por favor, ingrese un número del 1 al 3.\n");
            }

        } while (!opcion.equals("3"));
    }

    // CARGA DE DATOS DE citas_100.csv
    public void cargarCitasNormales() {
        try {
            citas = CsvReader.readColumn(
                    CSV_CITAS,
                    "fechaHora",
                    LocalDateTime::parse);
            citasCargadas = true;
            citasOrdenadas = false;
            System.out.println("----------------------------------------");
            System.out.println("Citas cargadas exitosamente:\n");
            for (Comparable fecha : citas) {
                System.out.println("- " + fecha);
            }
        } catch (Exception error) {
            System.out.println("----------------------------------------");
            System.err.println("Error al cargar los datos de citas: " + error.getMessage());
        }
    }

    // CARGA DE DATOS DE citas_100_casi_ordenadas.csv
    public void cargarCitasCasiOrdenadas() {
        try {
            citas = CsvReader.readColumn(
                    CSV_CITAS_CASI_ORDENADAS,
                    "fechaHora",
                    LocalDateTime::parse);
            citasCargadas = true;
            citasOrdenadas = false;
            System.out.println("----------------------------------------");
            System.out.println("Citas cargadas exitosamente:\n");
            for (Comparable fecha : citas) {
                System.out.println("- " + fecha);
            }
        } catch (Exception error) {
            System.out.println("----------------------------------------");
            System.err.println("Error al cargar los datos de citas: " + error.getMessage());
        }
    }

    // CARGA DE DATOS DE pacientes_500.csv
    public void cargarPacientes() {
        // Implementar carga de pacientes
    }

    // CARGA DE DATOS DE inventario_500_inverso.csv
    public void cargarInventario() {
        try {
            inventario = CsvReader.readColumn(
                    CSV_INVENTARIO,
                    "stock",
                    Integer::parseInt);
            inventarioCargado = true;
            inventarioOrdenado = false;

            System.out.println("----------------------------------------");
            System.out.println("Inventario cargado exitosamente:\n");
            for (Comparable item : inventario) {
                System.out.println("- " + item);
            }
        } catch (Exception error) {
            System.out.println("----------------------------------------");
            System.err.println("Error al cargar los datos de inventario: " + error.getMessage());
        }
    }

    // ORDENAR CITAS CON BUBBLE SORT
    public void ordenarCitasBurbuja() {
        if (citas == null) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue las citas primero.");
            return;
        }

        Comparable[] copia = citas.clone();
        SortStats stats = BubbleSort.sort(copia);
        citas = copia;
        citasOrdenadas = true;
                    
        System.out.println("----------------------------------------");
        System.out.println("\nOrdenamiento con Burbuja completado");
        stats.printSummary();
    }

    // ORDENAR CITAS CON INSERTION SORT
    public void ordenarCitasInsercion() {
        if (citas == null) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue las citas primero.");
            return;
        }

        Comparable[] copia = citas.clone();
        SortStats stats = InsertionSort.sort(copia);
        citas = copia;
        citasOrdenadas = true;
        
        System.out.println("----------------------------------------");
        System.out.println("\nOrdenamiento con Inserción completado");
        stats.printSummary();
    }

    // ORDENAR CITAS CON SELECTION SORT
    public void ordenarCitasSeleccion() {
        if (citas == null) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue las citas primero.");
            return;
        }

        Comparable[] copia = citas.clone();
        SortStats stats = SelectionSort.sort(copia);
        citas = copia;
        citasOrdenadas = true;
        
        System.out.println("----------------------------------------");
        System.out.println("\nOrdenamiento con Selección completado");
        stats.printSummary();
    }

    // ORDENAR INVENTARIO CON INSERTION SORT
    public void ordenarInventario() {

        if (inventario == null) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue el inventario primero.");
            return;
        }

        Comparable[] copia = inventario.clone();
        SortStats stats = InsertionSort.sort(copia);
        inventario = copia;
        inventarioOrdenado = true;
                    
        System.out.println("----------------------------------------");
        System.out.println("\nOrdenamiento con Inserción completado");
        stats.printSummary();
    }

    // BUSCAR CITA EXACTA POR FECHA Y HORA
    public void buscarCitaExacta() {
        if (!citasCargadas || !citasOrdenadas) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue y ordene las citas primero.");
            return;
        }

        System.out.println("\n----------------------------------------");
        System.out.println("     BÚSQUEDA EXACTA POR FECHA Y HORA     ");
        System.out.println("----------------------------------------");
        System.out.println("Ingrese la fecha y hora a buscar");
        System.out.println("Formato: yyyy-MM-ddTHH:mm");
        System.out.print("> ");

        try {
            LocalDateTime fechaBuscada = LocalDateTime.parse(scanner.nextLine());

            int indice = ArraySearch.binarySearch(citas, fechaBuscada);

            if (indice != -1) {
                SearchResult resultado = new SearchResult(indice, citas[indice]);
                System.out.println("\nCita encontrada");
                System.out.println(resultado);
            } else {
                System.out.println("----------------------------------------");
                System.out.println("\nNo se encontró una cita con esa fecha y hora.");
            }

        } catch (Exception error) {
            System.out.println("----------------------------------------");
            System.out.println("Formato de fecha inválido.");
        }
    }

    // BUSCAR CITAS POR RANGO DE FECHAS Y HORAS
    public void buscarCitasPorRango() {
        if (!citasCargadas) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, cargue las citas primero.");
            return;
        }

        if (!citasOrdenadas) {
            System.out.println("----------------------------------------");
            System.out.println("Por favor, ordene las citas primero.");
            return;
        }

        try {
            System.out.println("\n----------------------------------------");
            System.out.println("      BÚSQUEDA DE CITAS POR RANGO        ");
            System.out.println("----------------------------------------");

            System.out.print("Fecha INICIO (yyyy-MM-ddTHH:mm:ss): ");
            LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());

            System.out.print("Fecha FIN (yyyy-MM-ddTHH:mm:ss): ");
            LocalDateTime fin = LocalDateTime.parse(scanner.nextLine());

            if (inicio.isAfter(fin)) {
                System.out.println("----------------------------------------");
                System.out.println("La fecha inicio no puede ser mayor que la fecha fin.");
                return;
            }

            int li = ArraySearch.lowerBoundRange(citas, inicio);
            int ls = ArraySearch.upperBoundRange(citas, fin);

            if (li >= ls) {
                System.out.println("----------------------------------------");
                System.out.println("No se encontraron citas en ese rango.");
                return;
            }

            System.out.println("\n----------------------------------------");
            System.out.println("Citas encontradas:\n");

            for (int i = li; i < ls; i++) {
                SearchResult resultado = new SearchResult(i, citas[i]);
                System.out.println(resultado);
            }

        } catch (Exception error) {
            System.out.println("----------------------------------------");
            System.out.println("Formato de fecha inválido.");
        }
    }

    // BUSCAR PRIMER PACIENTE POR APELLIDO
    public void buscarPrimerPacientePorApellido() {
        // Implementar búsqueda de primer paciente por apellido
    }

    // BUSCAR ÚLTIMO PACIENTE POR APELLIDO
    public void buscarUltimoPacientePorApellido() {
        // Implementar búsqueda de último paciente por apellido
    }

    // BUSCAR PACIENTES CON PRIORIDAD 1
    public void buscarPacientesConPrioridad1() {
        // Implementar búsqueda de pacientes con prioridad 1
    }

    // BUSCAR PRODUCTO POR STOCK
    public void buscarProductoPorStock() {
        // Implementar búsqueda de producto por stock
    }

    // COMPARAR ALGORITMOS DE ORDENAMIENTO CON CITAS
    public void compararAlgoritmosCitas() {
        // Implementar comparación de algoritmos con citas
    }

    // COMPARAR ALGORITMOS DE ORDENAMIENTO CON INVENTARIO
    public void compararAlgoritmosInventario() {
        // Implementar comparación de algoritmos con inventario
    }
    
    private void cerrarRecursos() {
        scanner.close();
    }
    
    // MÉTODO MAIN
    public static void main(String[] args) {
        main app = new main();
        app.menuPrincipal();
        app.cerrarRecursos();
    }

}
