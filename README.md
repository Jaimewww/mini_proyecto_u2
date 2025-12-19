# Mini-proyecto U2: Agenda e Inventario Inteligentes

**Asignatura:** Estructura de Datos (Unidad 2: Ordenación y Búsqueda)  
**Proyecto Integrador:** Software de administración del hospital veterinario de la UNL.

## Propósito del Proyecto

Este módulo tiene como objetivo gestionar citas médicas, inventario y pacientes aplicando y comparando algoritmos de ordenación y búsqueda. El sistema justifica mediante evidencias (benchmarks) qué algoritmo es más conveniente según la naturaleza de los datos y la estructura utilizada (Arreglos vs. Listas Enlazadas - SLL).

## Funcionalidades (Alcance MVP)

El proyecto se divide en tres módulos principales:

### 1. Agenda de Citas (Arrays)
* **Estructura:** Arreglo de objetos.
**Carga de Datos:** Soporta archivos `citas_100.csv` y `citas_100_casi_ordenadas.csv`.
**Ordenamiento:** Ordena por `fechaHora` utilizando **Insertion Sort**, comparando su rendimiento contra **Bubble Sort** y **Selection Sort**.
**Búsqueda:** Implementa búsqueda binaria para fechas exactas y búsquedas por rangos utilizando *Lower Bound* y *Upper Bound*.

### 2. Gestión de Pacientes (Simple Linked List)
**Estructura:** Lista Simplemente Enlazada (SLL) con nodos que contienen `id`, `apellido` y `prioridad`.
* **Búsqueda:**
    * Secuencial: Primera y última coincidencia por apellido.
    * Filtrado: `findAll` para encontrar todos los pacientes con `prioridad == 1`.

### 3. Control de Inventario (Arrays)
* **Estructura:** Arreglo de objetos.
**Carga de Datos:** Carga `inventario_500_inverso.csv` (peor caso para algunos algoritmos).
**Ordenamiento:** Ordena por `stock` numérico.
**Búsqueda:** Búsqueda binaria por cantidad de stock, manejando duplicados y extremos.

## Tecnologías

* **Lenguaje:** Java (JDK 21).
* **Gestor de Dependencias:** Maven.
* **Bibliotecas:**
    * `junit` (Pruebas unitarias).
    * `commons-csv` (Lectura de archivos CSV).

## Estructura del Proyecto

El código está organizado en los siguientes paquetes:

* `ed.u2.model`: Definición de estructuras de datos (`Node`, `SimpleList`).
* `ed.u2.sorting`: Algoritmos de ordenamiento (`BubbleSort`, `InsertionSort`, `SelectionSort`, `SortStats`).
* `ed.u2.search`: Algoritmos de búsqueda (`ArraySearch`, `SearchResult`).
* `ed.u2.util`: Utilidades de lectura de archivos (`CsvReader`).
* `ed.u2`: Clase principal (`Main`) que ejecuta las pruebas.

## Metodología de Pruebas (Benchmarking)

El sistema incluye un **Runner** (`Main.java`) que ejecuta las pruebas de rendimiento automáticamente.

1.  **Métricas:** Se miden tiempo de ejecución (nanosegundos), número de comparaciones y número de intercambios (swaps).
2.  **Precisión:** Para los algoritmos de ordenamiento, se calcula la **mediana de 10 corridas**, descartando las primeras ejecuciones para estabilizar la JVM (warm-up).
3.  **Casos de Prueba:**
    * *Casi Ordenado:* Pruebas de Insertion Sort vs otros en la Agenda.
    * *Inverso:* Pruebas de penalización en Inventario para Bubble/Insertion.

## Ejecución

Para correr las pruebas y generar las tablas de evidencia:

1.  Clonar el repositorio.
2.  Compilar el proyecto con Maven.
3.  Ejecutar la clase `ed.u2.Main`.

```bash
mvn clean compile exec:java -Dexec.mainClass="ed.u2.Main"
