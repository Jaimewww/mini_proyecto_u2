package ed.u2.sorting;


/**
 * Implementación del algoritmo de ordenamiento Bubble Sort (Ordenamiento de burbuja) adaptada para benchmarking.
 */

public class BubbleSort {

    public static SortStats sort(Comparable[] array) {
        long comparisons = 0;
        long swaps = 0;
        int n = array.length;
        boolean swapped;

        long startTime = System.nanoTime(); // Inicio medicion tiempo

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {

                comparisons++;
                if (array[j].compareTo(array[j + 1]) > 0) {

                    SortUtils.swap(j, j + 1, array);

                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        long endTime = System.nanoTime(); // Fin medicion tiempo

        return new SortStats("Bubble Sort", comparisons, swaps, (endTime - startTime));
    }
}