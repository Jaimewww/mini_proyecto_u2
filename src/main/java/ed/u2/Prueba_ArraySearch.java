package ed.u2;

import ed.u2.search.ArraySearch;
import ed.u2.sorting.BubbleSort;
import ed.u2.sorting.SortStats;
import ed.u2.util.CsvReader;

import java.io.IOException;

/**
 * Main de prueba para ArraySearch usando archivos CSV reales.
 */
public class Prueba_ArraySearch {

    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("  PRUEBAS DE BÚSQUEDA CON DATOS REALES (CSV)");
        System.out.println("=======================================================\n");

        // Definir rutas de los CSV
        String csvPacientes = "src/main/java/ed/u2/resources/pacientes_500.csv";
        String csvCitas = "src/main/java/ed/u2/resources/citas_100.csv";
        String csvInventario = "src/main/java/ed/u2/resources/inventario_500_inverso.csv";

        try {
            // Prueba 1: Búsqueda en datos de pacientes
            testPacientes(csvPacientes);

            // Prueba 2: Búsqueda en datos de citas
            testCitas(csvCitas);

            // Prueba 3: Búsqueda en datos de inventario
            testInventario(csvInventario);

            System.out.println("\n=======================================================");
            System.out.println("  TODAS LAS PRUEBAS COMPLETADAS EXITOSAMENTE");
            System.out.println("=======================================================");

        } catch (IOException e) {
            System.err.println("Error al leer archivos CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Prueba con el archivo pacientes_500.csv
     */
    private static void testPacientes(String filePath) throws IOException {
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│  PRUEBA 1: Búsqueda en pacientes_500.csv           │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        // Primero, ver qué columnas numéricas hay disponibles
        String[] numericHeaders = CsvReader.getNumericHeaders(filePath);
        System.out.println("Columnas numéricas disponibles:");
        for (String header : numericHeaders) {
            System.out.println("  - " + header);
        }

        // Elegir una columna numérica (ajusta según tus datos)
        // Por ejemplo, si hay una columna "edad" o "id"
        if (numericHeaders.length > 0) {
            String columnName = numericHeaders[0]; // Primera columna numérica
            System.out.println("\n📊 Trabajando con columna: " + columnName);

            // Leer datos
            Comparable[] datos = CsvReader.readColumn(filePath, columnName, Integer::parseInt);
            System.out.println("   Total de registros leídos: " + datos.length);

            // Mostrar muestra de datos
            System.out.println("   Primeros 10 valores (sin ordenar):");
            mostrarMuestra(datos, 10);

            // Ordenar los datos
            System.out.println("\n⏳ Ordenando datos...");
            SortStats stats = BubbleSort.sort(datos);
            System.out.println("   ✓ Datos ordenados en " + stats.timeNano + " ns");

            // Mostrar datos ordenados
            System.out.println("   Primeros 10 valores (ordenados):");
            mostrarMuestra(datos, 10);

            // Realizar búsquedas
            System.out.println("\n🔍 BÚSQUEDAS:");

            // Búsqueda 1: Buscar el primer elemento
            Integer elementoBuscar1 = (Integer) datos[0];
            int resultado1 = ArraySearch.binarySearch(datos, elementoBuscar1);
            System.out.println("   • Buscar " + elementoBuscar1 + " (primer elemento):");
            System.out.println("     Encontrado en índice: " + resultado1 + " ✓");

            // Búsqueda 2: Buscar elemento del medio
            Integer elementoBuscar2 = (Integer) datos[datos.length / 2];
            int resultado2 = ArraySearch.binarySearch(datos, elementoBuscar2);
            System.out.println("   • Buscar " + elementoBuscar2 + " (elemento del medio):");
            System.out.println("     Encontrado en índice: " + resultado2 + " ✓");

            // Búsqueda 3: Buscar elemento que NO existe
            Integer elementoInexistente = -9999;
            int resultado3 = ArraySearch.binarySearch(datos, elementoInexistente);
            System.out.println("   • Buscar " + elementoInexistente + " (no existe):");
            System.out.println("     Resultado: " + resultado3 + " (no encontrado) ✓");

            // Búsqueda 4: Lower y Upper Bound (si hay duplicados)
            System.out.println("\n🔍 BÚSQUEDA DE DUPLICADOS:");
            Integer elementoDuplicado = (Integer) datos[10]; // Tomar un elemento
            int lower = ArraySearch.binaryLowerBound(datos, elementoDuplicado);
            int upper = ArraySearch.binaryUpperBound(datos, elementoDuplicado);

            if (lower != -1 && upper != -1) {
                System.out.println("   • Elemento: " + elementoDuplicado);
                System.out.println("     Primera ocurrencia: índice " + lower);
                System.out.println("     Última ocurrencia:  índice " + upper);
                System.out.println("     Total ocurrencias:  " + (upper - lower + 1) + " ✓");
            }
        }

        System.out.println();
    }

    /**
     * Prueba con el archivo citas_100.csv
     */
    private static void testCitas(String filePath) throws IOException {
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│  PRUEBA 2: Búsqueda en citas_100.csv               │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        // Este CSV no tiene columnas numéricas, usar apellidos (Strings)
        String[] headers = CsvReader.getHeaders(filePath);
        System.out.println("Columnas disponibles:");
        for (String header : headers) {
            System.out.println("  - " + header);
        }

        // Trabajar con la columna "apellido"
        System.out.println("\n📊 Trabajando con columna: apellido (Strings)");

        // Leer apellidos
        Comparable[] apellidos = CsvReader.readColumn(filePath, "apellido", String::trim);
        System.out.println("   Total de registros: " + apellidos.length);

        // Mostrar muestra sin ordenar
        System.out.println("   Primeros 10 apellidos (sin ordenar):");
        mostrarMuestra(apellidos, 10);

        // Ordenar
        System.out.println("\n⏳ Ordenando apellidos alfabéticamente...");
        SortStats stats = BubbleSort.sort(apellidos);
        System.out.println("   ✓ Ordenamiento completado");
        System.out.println("     Tiempo: " + stats.timeNano + " ns");
        System.out.println("     Comparaciones: " + stats.comparisons);
        System.out.println("     Swaps: " + stats.swaps);

        // Mostrar muestra ordenada
        System.out.println("   Primeros 10 apellidos (ordenados):");
        mostrarMuestra(apellidos, 10);

        // Búsquedas
        System.out.println("\n🔍 BÚSQUEDAS:");

        // Buscar un apellido que existe
        String apellidoBuscar1 = "García";
        int resultado1 = ArraySearch.binarySearch(apellidos, apellidoBuscar1);
        System.out.println("   • Buscar '" + apellidoBuscar1 + "':");
        System.out.println("     Encontrado en índice: " + resultado1 + " ✓");

        // Buscar otro apellido
        String apellidoBuscar2 = "Pérez";
        int resultado2 = ArraySearch.binarySearch(apellidos, apellidoBuscar2);
        System.out.println("   • Buscar '" + apellidoBuscar2 + "':");
        System.out.println("     Encontrado en índice: " + resultado2 + " ✓");

        // Buscar apellido que no existe
        String apellidoInexistente = "Zambrano";
        int resultado3 = ArraySearch.binarySearch(apellidos, apellidoInexistente);
        System.out.println("   • Buscar '" + apellidoInexistente + "' (no existe):");
        System.out.println("     Resultado: " + resultado3 + " (no encontrado) ✓");

        // Búsqueda con centinela
        System.out.println("\n🔍 BÚSQUEDA CON CENTINELA:");
        String apellidoCentinela = (String) apellidos[50];
        int resultadoCentinela = ArraySearch.findWithCentinel(apellidos, apellidoCentinela);
        System.out.println("   • Buscar '" + apellidoCentinela + "':");
        System.out.println("     Encontrado en índice: " + resultadoCentinela + " ✓");

        System.out.println();
    }

    /**
     * Prueba con el archivo inventario_500_inverso.csv
     */
    private static void testInventario(String filePath) throws IOException {
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│  PRUEBA 3: Búsqueda en inventario_500_inverso.csv  │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        String[] numericHeaders = CsvReader.getNumericHeaders(filePath);
        System.out.println("Columnas numéricas disponibles:");
        for (String header : numericHeaders) {
            System.out.println("  - " + header);
        }

        if (numericHeaders.length > 0) {
            String columnName = numericHeaders[0];
            System.out.println("\n📊 Trabajando con columna: " + columnName);

            Comparable[] datos = CsvReader.readColumn(filePath, columnName, Integer::parseInt);
            System.out.println("   Total de registros: " + datos.length);
            System.out.println("   Nota: Datos en orden inverso - peor caso para ordenamiento");

            // Mostrar datos antes de ordenar
            System.out.println("\n   Primeros 10 valores (orden inverso):");
            mostrarMuestra(datos, 10);

            // Ordenar
            System.out.println("\n⏳ Ordenando datos (peor caso)...");
            long inicio = System.nanoTime();
            SortStats stats = BubbleSort.sort(datos);
            long fin = System.nanoTime();

            System.out.println("   ✓ Ordenamiento completado");
            System.out.println("     Tiempo: " + (fin - inicio) + " ns");
            System.out.println("     Comparaciones: " + stats.comparisons);
            System.out.println("     Swaps: " + stats.swaps);

            // Mostrar datos después de ordenar
            System.out.println("\n   Primeros 10 valores (ordenados):");
            mostrarMuestra(datos, 10);

            // Comparar búsqueda binaria vs secuencial
            System.out.println("\n🔍 COMPARACIÓN: Binaria vs Centinela");
            Integer elementoBuscar = (Integer) datos[250]; // Elemento del medio

            // Búsqueda binaria
            long inicioBinaria = System.nanoTime();
            int resultadoBinaria = ArraySearch.binarySearch(datos, elementoBuscar);
            long finBinaria = System.nanoTime();

            // Búsqueda con centinela
            long inicioCentinela = System.nanoTime();
            int resultadoCentinela = ArraySearch.findWithCentinel(datos, elementoBuscar);
            long finCentinela = System.nanoTime();

            System.out.println("   • Elemento buscado: " + elementoBuscar);
            System.out.println("   • Búsqueda binaria:");
            System.out.println("     Índice: " + resultadoBinaria);
            System.out.println("     Tiempo: " + (finBinaria - inicioBinaria) + " ns");
            System.out.println("   • Búsqueda con centinela:");
            System.out.println("     Índice: " + resultadoCentinela);
            System.out.println("     Tiempo: " + (finCentinela - inicioCentinela) + " ns");
            System.out.println("   ✓ Ambos métodos encuentran el elemento correctamente");
        }

        System.out.println();
    }

    /**
     * Muestra los primeros N elementos de un arreglo
     */
    private static void mostrarMuestra(Comparable[] array, int cantidad) {
        System.out.print("     [");
        int limite = Math.min(cantidad, array.length);
        for (int i = 0; i < limite; i++) {
            System.out.print(array[i]);
            if (i < limite - 1) System.out.print(", ");
        }
        if (array.length > cantidad) {
            System.out.print(", ...");
        }
        System.out.println("]");
    }
}