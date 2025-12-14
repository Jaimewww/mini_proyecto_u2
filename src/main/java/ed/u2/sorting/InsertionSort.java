package ed.u2.sorting;


/**
 * Implementación del algoritmo de ordenamiento por inserción y adaptada para benchmarking.
 */

public class InsertionSort {

    public static SortStats sort(Comparable[] array) {
        long comparisons = 0;
        long swaps = 0; // En Insertion Sort, contamos los desplazamientos como swaps
        int n = array.length;

        long startTime = System.nanoTime(); // Inicio cronómetro

        // Se recorre desde el segundo elemento hasta el final
        for (int i = 1; i < n; i++) {
            Comparable key = array[i];
            int j = i - 1;

            // Bucle interno para desplazamientos
            while (j >= 0) {
                comparisons++; // Contamos la comparación que vamos a hacer

                if (array[j].compareTo(key) > 0) {
                    // Desplazamiento
                    array[j + 1] = array[j];
                    swaps++; // Contamos el desplazamiento
                    j--;
                } else {
                    // Si no es mayor, terminamos el bucle interno
                    break;
                }
            }
            // Insertamos la clave en su posición correcta
            array[j + 1] = key;
        }

        long endTime = System.nanoTime(); // Fin cronómetro

        return new SortStats("Insertion Sort", comparisons, swaps, (endTime - startTime));
    }
}