package ed.u2.sorting;

/**
 * Utilidades para los metodos de ordenamiento
 */

public class SortUtils {

    /**
     * Este metodo guarda una variable temporal para realizar un intercambio en un arreglo
     * @param x
     * @param y
     * @param array
     */
    public static void swap(int x, int y, Comparable[] array) {
        Comparable temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
