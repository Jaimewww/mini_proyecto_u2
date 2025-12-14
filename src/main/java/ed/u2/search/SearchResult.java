package ed.u2.search;

/**
 * Clase para almacenar el resultado de una busqueda en un arreglo
 * @author Jaime Landazuri
 */

public class SearchResult {
    private int index;
    private Comparable value;

    public SearchResult(int index, Comparable value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public Comparable getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{Index: " + index + ", Value: " + value + "}";
    }
}