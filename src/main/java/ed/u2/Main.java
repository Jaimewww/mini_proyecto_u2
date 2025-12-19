package ed.u2;

import ed.u2.model.Node;
import ed.u2.model.SimpleList;
import ed.u2.search.ArraySearch;
import ed.u2.sorting.BubbleSort;
import ed.u2.sorting.InsertionSort;
import ed.u2.sorting.SelectionSort;
import ed.u2.sorting.SortStats;
import ed.u2.util.CsvReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Runner principal para el Mini-Proyecto U2.
 * Ejecuta benchmarks de ordenamiento y pruebas de búsqueda automatizadas
 * según los requisitos del PID 2025.
 */
public class Main {

    // Rutas de recursos
    private static final String CSV_CITAS_CASI = "src/main/java/ed/u2/resources/citas_100_casi_ordenadas.csv";
    private static final String CSV_INVENTARIO = "src/main/java/ed/u2/resources/inventario_500_inverso.csv";
    private static final String CSV_PACIENTES = "src/main/java/ed/u2/resources/pacientes_500.csv";

    public static void main(String[] args) {
        System.out.println("==========================================================================================");
        System.out.println("                        HOSPITAL VETERINARIO UNL - BENCHMARKS                             ");
        System.out.println("==========================================================================================\n");

        // (ordenamiento: agenda, inventario)
        runSortingBenchmarks();

        System.out.println("\n");

        // (agenda, inventario, pacientes)
        runSearchTests();
    }

    // Benchmark de ordenamiento

    private static void runSortingBenchmarks() {
        System.out.println("A) RESULTADOS DE ORDENACIÓN (Mediana de 10 corridas)");
        System.out.printf("%-20s %-10s %-15s %-15s %-15s %-15s%n",
                "Dataset", "n", "Algoritmo", "Comparaciones", "Swaps", "Tiempo (ns)");
        System.out.println("-------------------------------------------------------------------------------------------");

        try {
            // CASO 1: AGENDA (Casi Ordenado) - Usamos Inserción, Burbuja, Selección
            Comparable[] citas = CsvReader.readColumn(CSV_CITAS_CASI, "fechaHora", LocalDateTime::parse);
            int nCitas = citas.length;

            // Evaluar los 3 algoritmos
            printBenchmarkRow("Agenda (Casi Ord)", nCitas, "Inserción", citas, InsertionSort::sort);
            printBenchmarkRow("Agenda (Casi Ord)", nCitas, "Burbuja",   citas, BubbleSort::sort);
            printBenchmarkRow("Agenda (Casi Ord)", nCitas, "Selección", citas, SelectionSort::sort);

            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            // CASO 2: INVENTARIO (Inverso) - Penalización para Insertion/Bubble
            Comparable[] stock = CsvReader.readColumn(CSV_INVENTARIO, "stock", Integer::parseInt);
            int nStock = stock.length;

            printBenchmarkRow("Inventario (Inv)", nStock, "Inserción", stock, InsertionSort::sort);
            printBenchmarkRow("Inventario (Inv)", nStock, "Burbuja",   stock, BubbleSort::sort);
            printBenchmarkRow("Inventario (Inv)", nStock, "Selección", stock, SelectionSort::sort);

        } catch (Exception e) {
            System.err.println("Error ejecutando benchmarks de ordenamiento: " + e.getMessage());
        }
    }

    /**
     * Ejecuta el algoritmo 10 veces, calcula la mediana del tiempo y toma las métricas de comparaciones/swaps.
     */
    private static void printBenchmarkRow(String dataset, int n, String algName, Comparable[] originalData,
                                          Function<Comparable[], SortStats> sortMethod) {
        List<Long> times = new ArrayList<>();
        SortStats stats = null;

        // Ejecutar 10 corridas
        for (int i = 0; i < 10; i++) {
            // Clonar array para no ordenar el original y afectar la siguiente corrida
            Comparable[] copy = originalData.clone();

            // Ejecutar ordenamiento (sin impresiones internas)
            SortStats currentStats = sortMethod.apply(copy);

            times.add(currentStats.timeNano);

            // Guardamos las stats de la primera corrida para obtener swaps/comparaciones (son deterministas)
            if (i == 0) {
                stats = currentStats;
            }
        }

        // Calcular mediana de tiempo
        Collections.sort(times);
        long medianTime = times.get(times.size() / 2);

        System.out.printf("%-20s %-10d %-15s %-15d %-15d %-15d%n",
                dataset, n, algName, stats.comparisons, stats.swaps, medianTime);
    }

    //Pruebas de busqueda

    private static void runSearchTests() {
        System.out.println("B) RESULTADOS DE BÚSQUEDA");
        System.out.printf("%-20s %-25s %-15s %-20s %-10s%n",
                "Colección", "Clave/Predicado", "Método", "Salida", "Correcto");
        System.out.println("-------------------------------------------------------------------------------------------");

        try {
            // AGENDA (Array Ordenado) - Binaria Exacta y Rangos
            Comparable[] citasRaw = CsvReader.readColumn(CSV_CITAS_CASI, "fechaHora", LocalDateTime::parse);
            // Ordenamos el array UNA VEZ para poder buscar (requisito de búsqueda binaria)
            InsertionSort.sort(citasRaw);

            // Prueba: Binaria Exacta
            LocalDateTime targetDate = LocalDateTime.parse("2025-03-15T15:15"); // CITA-040 del csv
            long tStart = System.nanoTime();
            int idx = ArraySearch.binarySearch(citasRaw, targetDate);
            long tEnd = System.nanoTime();

            printSearchRow("Agenda (Array)", targetDate.toString(), "Binaria",
                    idx != -1 ? "Index: " + idx : "No encontrado",
                    idx != -1);

            // Prueba: Rango (Lower/Upper Bound)
            LocalDateTime startRange = LocalDateTime.parse("2025-03-01T00:00");
            LocalDateTime endRange   = LocalDateTime.parse("2025-03-02T23:59");

            int lb = ArraySearch.lowerBoundRange(citasRaw, startRange);
            int ub = ArraySearch.upperBoundRange(citasRaw, endRange);
            int countRange = ub - lb;

            printSearchRow("Agenda (Array)", "[01-03, 02-03]", "Rango (Bounds)",
                    "Citas: " + countRange,
                    countRange > 0);


            // inventario (Array Ordenado por Stock) - Duplicados
            Comparable[] stockRaw = CsvReader.readColumn(CSV_INVENTARIO, "stock", Integer::parseInt);
            // Ordenamos para poder usar la busqueda binaria
            InsertionSort.sort(stockRaw);

            // Prueba: Buscar stock con duplicados (ej. hay varios items con stock 490)
            // asumimos una busqueda con stock 250.
            Integer targetStock = 250;
            int lower = ArraySearch.binaryLowerBound(stockRaw, targetStock);
            int upper = ArraySearch.binaryUpperBound(stockRaw, targetStock);

            String outputInv = (lower != -1) ? "Idx [" + lower + "-" + upper + "]" : "No encontrado";
            printSearchRow("Inventario (Array)", "Stock " + targetStock, "Binaria Bounds",
                    outputInv, lower != -1);


            // PACIENTES (SLL) - Secuencial y FindAll
            // Nota: Cargamos listas separadas para simular la bu{squeda por campos
            SimpleList<String> apellidosList = CsvReader.readColumnList(CSV_PACIENTES, "apellido", String::trim);
            SimpleList<Integer> prioridadList = CsvReader.readColumnList(CSV_PACIENTES, "prioridad", Integer::parseInt);

            // Prueba: FindFirst (Apellido)
            String searchLastname = "Pérez";
            Node<String> nodeFirst = apellidosList.firstCoincidence(s -> s.equals(searchLastname));
            printSearchRow("Pacientes (SLL)", "Apellido=" + searchLastname, "Sec. Primera",
                    nodeFirst != null ? "Encontrado" : "Null",
                    nodeFirst != null);

            // Prueba: FindLast (Apellido)
            Node<String> nodeLast = apellidosList.lastCoincidence(s -> s.equals(searchLastname));
            printSearchRow("Pacientes (SLL)", "Apellido=" + searchLastname, "Sec. Última",
                    nodeLast != null ? "Encontrado" : "Null",
                    nodeLast != null);

            // Prueba: FindAll (Prioridad == 1)
            SimpleList<Node<Integer>> criticalPatients = prioridadList.findAll(node -> node.getValue() == 1);
            printSearchRow("Pacientes (SLL)", "Prioridad==1", "FindAll",
                    "Total: " + criticalPatients.size(),
                    criticalPatients.size() > 0);

        } catch (Exception e) {
            System.err.println("Error en pruebas de búsqueda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printSearchRow(String col, String key, String method, String out, boolean correct) {
        System.out.printf("%-20s %-25s %-15s %-20s %-10s%n",
                col, key, method, out, correct ? "Si" : "No");
    }
}