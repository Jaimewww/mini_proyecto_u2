package ed.u2.sorting;

/**
 * Clase para almacenar y mostrar las estadísticas de un algoritmo de ordenamiento, util para benchmarking.
 */

public class SortStats {
    public String algorithmName;
    public long comparisons;
    public long swaps;
    public long timeNano;

    public SortStats(String algorithmName, long comparisons, long swaps, long timeNano) {
        this.algorithmName = algorithmName;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.timeNano = timeNano;
    }

    public void printSummary() {
        System.out.println("----------------------------------------");
        System.out.println("\nResumen: " + algorithmName + "\n");
        System.out.println("Tiempo de ejecución: " + timeNano + " ns (" + (timeNano / 1_000_000.0) + " ms)");
        System.out.println("Comparaciones   : " + comparisons);
        System.out.println("Intercambios    : " + swaps);
        System.out.println("----------------------------------------");
    }
}