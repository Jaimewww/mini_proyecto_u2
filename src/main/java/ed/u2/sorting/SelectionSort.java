package ed.u2.sorting;

/**
 * Implementacion de Selection Sort (Ordenamiento por seleccion) adaptada para benchmarking.
 */

public class SelectionSort {

    public static SortStats sort(Comparable[] array) {
        long comparisons = 0;
        long swaps = 0;
        int n = array.length;

        long startTime = System.nanoTime(); // Inicio cronometro

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Bucle interno para buscar el menor
            for (int j = i + 1; j < n; j++) {
                comparisons++;

                // Usamos compareTo() para objetos Double.
                // Si array[j] es menor que array[minIndex], devuelve un valor negativo.
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Si el minimo no esta ya en su posicion, intercambiamos
            if (minIndex != i) {
                SortUtils.swap(minIndex, i, array);
                swaps++; // ▪ swaps++
            }
        }

        long endTime = System.nanoTime(); // Fin cronometro

        return new SortStats("Selection Sort", comparisons, swaps, (endTime - startTime));
    }
}