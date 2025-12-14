package ed.u2.search;

/**
 * Clase utilitaria para algoritmos de búsqueda en arreglos.
 *
 * IMPORTANTE: Los métodos de búsqueda binaria asumen que el arreglo está ORDENADO
 * de forma ascendente.
 */
public class ArraySearch {

    /**
     * Búsqueda binaria clásica.
     *
     * @param array  Arreglo ordenado de elementos Comparable
     * @param target Elemento a buscar
     * @return índice donde se encuentra el elemento, o -1 si no existe
     */
    public static int binarySearch(Comparable[] array, Comparable target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = array[mid].compareTo(target);

            if (comparison == 0) {
                return mid; // Elemento encontrado
            } else if (comparison < 0) {
                left = mid + 1; // Buscar en la mitad derecha
            } else {
                right = mid - 1; // Buscar en la mitad izquierda
            }
        }

        return -1; // Elemento no encontrado
    }

    /**
     * Búsqueda binaria que retorna el índice de la PRIMERA ocurrencia (lower bound).
     * Útil cuando hay elementos duplicados en el arreglo ordenado.
     *
     * @param array  Arreglo ordenado de elementos Comparable
     * @param target Elemento a buscar
     * @return índice de la primera aparición, o -1 si no existe
     */
    public static int binaryLowerBound(Comparable[] array, Comparable target) {
        int left = 0;
        int right = array.length - 1;
        int result = -1; // Almacena el índice candidato

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = array[mid].compareTo(target);

            if (comparison == 0) {
                result = mid;      // Posible primera aparición
                right = mid - 1;   // Seguir buscando a la izquierda
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result; // Retorna -1 si no se encontró
    }

    /**
     * Búsqueda binaria que retorna el índice de la ÚLTIMA ocurrencia (upper bound).
     * Útil cuando hay elementos duplicados en el arreglo ordenado.
     *
     * @param array  Arreglo ordenado de elementos Comparable
     * @param target Elemento a buscar
     * @return índice de la última aparición, o -1 si no existe
     */
    public static int binaryUpperBound(Comparable[] array, Comparable target) {
        int left = 0;
        int right = array.length - 1;
        int result = -1; // Almacena el índice candidato

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = array[mid].compareTo(target);

            if (comparison == 0) {
                result = mid;     // Posible última aparición
                left = mid + 1;   // Seguir buscando a la derecha
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result; // Retorna -1 si no se encontró
    }

    /**
     * Búsqueda secuencial utilizando la técnica del centinela.
     * Reduce el número de comparaciones eliminando la verificación de límites.
     *
     * NOTA: Este metodo funciona en arreglos NO ordenados.
     *
     * @param array  Arreglo de elementos Comparable
     * @param target Elemento a buscar
     * @return índice donde se encuentra el elemento, o -1 si no existe
     */
    public static int findWithCentinel(Comparable[] array, Comparable target) {
        int n = array.length;

        // Guardamos el último elemento para restaurarlo después
        Comparable last = array[n - 1];

        // Colocamos el elemento buscado como centinela al final
        array[n - 1] = target;

        int i = 0;

        // Búsqueda secuencial sin verificar límites
        // El centinela garantiza que siempre se encontrará el elemento
        while (array[i].compareTo(target) != 0) {
            i++;
        }

        // Restauramos el último elemento original
        array[n - 1] = last;

        // Verificamos si se encontró dentro del arreglo original
        // o si solo encontramos el centinela
        if (i < n - 1 || last.compareTo(target) == 0) {
            return i;
        }

        return -1; // Elemento no encontrado
    }
}