package ed.u2.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ed.u2.model.SimpleList;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CsvReader {

    // Lee una columna especifica de un archivo CSV y la convierte en un arreglo.
    public static <T extends Comparable<T>> Comparable[] readColumn(
            String filePath,
            String columnName,
            Function<String, T> parser) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        List<T> dataList = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);
                // Limpieza básica
                String cleanedValue = value.replace(',', '.').trim();

                if (!cleanedValue.isEmpty()) {
                    try {
                        T parsedValue = parser.apply(cleanedValue);
                        dataList.add(parsedValue);
                    } catch (Exception e) {
                        System.err.println("Ignorando valor inválido: '" + value + "'");
                    }
                }
            }
        }

        return dataList.toArray(new Comparable[0]);
    }

    public static <T extends Comparable<T>> SimpleList<T> readColumnList(
            String filePath,
            String columnName,
            Function<String, T> parser) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        SimpleList<T> sll = new SimpleList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);
                String cleanedValue = value.replace(',', '.').trim();

                if (!cleanedValue.isEmpty()) {
                    try {
                        T parsedValue = parser.apply(cleanedValue);
                        sll.pushBack(parsedValue);
                    } catch (Exception e) {
                        System.err.println("Error parseando: " + value);
                    }
                }
            }
        }
        return sll;
    }

    // Devuelve un arreglo con los nombres de las cabeceras (headers) del CSV.
    public static String[] getHeaders(String filePath) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            return csvParser.getHeaderMap().keySet().toArray(new String[0]);
        }
    }

    // Lee hasta `limit` valores (no vacíos) de la columna especificada y los devuelve como strings.
    public static String[] readSampleValues(String filePath, String columnName, int limit) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        List<String> samples = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);
                if (value == null) continue;
                String v = value.trim();
                if (!v.isEmpty()) {
                    samples.add(v);
                    if (samples.size() >= limit) break;
                }
            }
        }

        return samples.toArray(new String[0]);
    }

    // Devuelve únicamente los headers cuya columna contiene valores Integer.
    // Recorre el archivo y marca como válidas las columnas que tienen al menos
    // un valor entero y no presentan valores no numéricos (entre los registros).
    public static String[] getNumericHeaders(String filePath) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            // Obtener headers
            List<String> headers = new ArrayList<>(csvParser.getHeaderMap().keySet());
            int n = headers.size();
            boolean[] ok = new boolean[n];
            boolean[] seenNumeric = new boolean[n];
            for (int i = 0; i < n; i++) ok[i] = true;

            for (CSVRecord csvRecord : csvParser) {
                for (int i = 0; i < n; i++) {
                    if (!ok[i]) continue; // ya descartada
                    String h = headers.get(i);
                    String value = csvRecord.get(h);
                    if (value == null) continue;
                    String v = value.replace(',', '.').trim();
                    if (v.isEmpty()) continue;
                    try {
                        Integer.parseInt(v);
                        seenNumeric[i] = true;
                    } catch (NumberFormatException e) {
                        // valor no numérico: descartar header
                        ok[i] = false;
                    }
                }
            }

            List<String> numeric = new ArrayList<>();
            for (int i = 0; i < n; i++) if (ok[i] && seenNumeric[i]) numeric.add(headers.get(i));
            return numeric.toArray(new String[0]);
        }
    }
}