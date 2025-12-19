# Mini-proyecto U2: Agenda e Inventario Inteligentes

**Asignatura:** Estructura de Datos (Unidad 2: Ordenación y Búsqueda)  
[cite_start]**Proyecto Integrador:** Software de administración del hospital veterinario de la UNL[cite: 171].

## Propósito del Proyecto

Este módulo tiene como objetivo gestionar citas médicas, inventario y pacientes aplicando y comparando algoritmos de ordenación y búsqueda. [cite_start]El sistema justifica mediante evidencias (benchmarks) qué algoritmo es más conveniente según la naturaleza de los datos y la estructura utilizada (Arreglos vs. Listas Enlazadas - SLL)[cite: 173, 174].

## Funcionalidades (Alcance MVP)

[cite_start]El proyecto se divide en tres módulos principales[cite: 175]:

### 1. Agenda de Citas (Arrays)
* **Estructura:** Arreglo de objetos.
* [cite_start]**Carga de Datos:** Soporta archivos `citas_100.csv` y `citas_100_casi_ordenadas.csv`[cite: 178].
* [cite_start]**Ordenamiento:** Ordena por `fechaHora` utilizando **Insertion Sort**, comparando su rendimiento contra **Bubble Sort** y **Selection Sort**[cite: 177].
* [cite_start]**Búsqueda:** Implementa búsqueda binaria para fechas exactas y búsquedas por rangos utilizando *Lower Bound* y *Upper Bound*[cite: 181].

### 2. Gestión de Pacientes (Simple Linked List)
* [cite_start]**Estructura:** Lista Simplemente Enlazada (SLL) con nodos que contienen `id`, `apellido` y `prioridad`[cite: 183].
* **Búsqueda:**
    * Secuencial: Primera y última coincidencia por apellido.
    * [cite_start]Filtrado: `findAll` para encontrar todos los pacientes con `prioridad == 1`[cite: 184].

### 3. Control de Inventario (Arrays)
* **Estructura:** Arreglo de objetos.
* [cite_start]**Carga de Datos:** Carga `inventario_500_inverso.csv` (peor caso para algunos algoritmos)[cite: 187].
* [cite_start]**Ordenamiento:** Ordena por `stock` numérico[cite: 189].
* [cite_start]**Búsqueda:** Búsqueda binaria por cantidad de stock, manejando duplicados y extremos[cite: 191].

## Tecnologías

* [cite_start]**Lenguaje:** Java (JDK 21)[cite: 193].
* **Gestor de Dependencias:** Maven.
* **Bibliotecas:**
    * `junit` (Pruebas unitarias).
    * `commons-csv` (Lectura de archivos CSV).

## Estructura del Proyecto

[cite_start]El código está organizado en los siguientes paquetes[cite: 193]:

* `ed.u2.model`: Definición de estructuras de datos (`Node`, `SimpleList`).
* `ed.u2.sorting`: Algoritmos de ordenamiento (`BubbleSort`, `InsertionSort`, `SelectionSort`, `SortStats`).
* `ed.u2.search`: Algoritmos de búsqueda (`ArraySearch`, `SearchResult`).
* `ed.u2.util`: Utilidades de lectura de archivos (`CsvReader`).
* `ed.u2`: Clase principal (`Main`) que ejecuta las pruebas.

## Metodología de Pruebas (Benchmarking)

[cite_start]El sistema incluye un **Runner** (`Main.java`) que ejecuta las pruebas de rendimiento automáticamente[cite: 193].

1.  [cite_start]**Métricas:** Se miden tiempo de ejecución (nanosegundos), número de comparaciones y número de intercambios (swaps)[cite: 196].
2.  [cite_start]**Precisión:** Para los algoritmos de ordenamiento, se calcula la **mediana de 10 corridas**, descartando las primeras ejecuciones para estabilizar la JVM (warm-up)[cite: 196, 197].
3.  **Casos de Prueba:**
    * [cite_start]*Casi Ordenado:* Pruebas de Insertion Sort vs otros en la Agenda[cite: 217].
    * [cite_start]*Inverso:* Pruebas de penalización en Inventario para Bubble/Insertion[cite: 218].

## Ejecución

Para correr las pruebas y generar las tablas de evidencia:

1.  Clonar el repositorio.
2.  Compilar el proyecto con Maven.
3.  Ejecutar la clase `ed.u2.Main`.

```bash
mvn clean compile exec:java -Dexec.mainClass="ed.u2.Main"
