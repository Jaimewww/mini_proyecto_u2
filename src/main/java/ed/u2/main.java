package ed.u2;

import ed.u2.sorting.BubbleSort;
import ed.u2.sorting.InsertionSort;
import ed.u2.sorting.SelectionSort;
import ed.u2.sorting.SortStats;
import ed.u2.util.CsvReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        try{
            Comparable[] fechas = CsvReader.readColumn("src/main/java/ed/u2/resources/citas_100.csv", "fechaHora", str -> LocalDateTime.parse(str));
            System.out.println(Arrays.toString(fechas));
            SortStats stats = SelectionSort.sort(fechas);
            System.out.println(Arrays.toString(fechas));
            stats.printSummary();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

}
