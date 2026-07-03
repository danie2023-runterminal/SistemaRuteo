/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class Dijkstra {
//   Implementación del algoritmo de Dijkstra para encontrar la ruta más corta 
//  desde un nodo origen a un destino. Este algoritmo es eficiente 
//  utilizando una cola de prioridad (MinHeap), pero asume que no existen 
//  aristas con pesos negativos en el grafo.
   public static void rutaMasCorta(Grafo g, int inicio, int destino, HashTable ht) {
        int n = g.getN();
        int[] distancia = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        // Inicialización de los arreglos: todos los nodos inician como no descubiertos,
        // a una distancia infinita (MAX_VALUE) y sin un nodo previo asignado.
        for (int i = 0; i < n; i++) {
            distancia[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
            visitado[i] = false;
        }
        // El costo de ir del nodo origen a sí mismo es siempre nulo
        distancia[inicio] = 0;
        MinHeap heap = new MinHeap(n * n);
//        Se utiliza un MinHeap como cola de prioridad para extraer eficientemente 
//        el nodo no visitado con la distancia acumulada más corta en cada iteración.
        heap.insertar(inicio, 0);
        while (!heap.isEmpty()) {
            int u = heap.extraerMinimo();
            
            if (visitado[u]) {
                continue;
            }
            visitado[u] = true;
//             Un mismo nodo puede haber sido insertado múltiples veces en el heap con
//            distintas distancias; si ya fue procesado con su ruta óptima, se omite.
            if (distancia[u] == Integer.MAX_VALUE) {
                break;
            }
            NodoGrafo temp = g.getAdj(u);
//            Relajación de aristas: Se evalúan todos los vecinos adyacentes del nodo actual
            while (temp != null) {
                int v = temp.destino;
                int peso = temp.peso;
//                Si el vecino no ha sido fijado (visitado) y se encuentra un camino estrictamente
//                 más corto a través de 'u', se actualizan sus métricas y se encola en el heap.
                if (!visitado[v] && distancia[u] + peso < distancia[v]) {
                    distancia[v] = distancia[u] + peso;
                    predecesor[v] = u;
                    heap.insertar(v, distancia[v]);
                }
                temp = temp.next;
            }
        }
        // Validación para asegurar que el destino fue alcanzado antes de intentar rutear
        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre "
                + ht.search(inicio) + " y " + ht.search(destino));
            return;
        }
        // Reconstrucción del camino utilizando una Pila para invertir el orden.
        // El arreglo predecesor contiene la ruta desde el destino hacia el origen en reversa.
        Pila pila = new Pila();
        int actual = destino;
        while (actual != -1) {
            pila.push(actual);
            actual = predecesor[actual];
        }
        System.out.println("\n=== Dijkstra ===");
        System.out.print("Ruta: ");
        while (!pila.isEmpty()) {
            System.out.print(ht.search(pila.pop()));
            if (!pila.isEmpty()){
                System.out.print(" -> ");
            }
        }
        System.out.println("\nDistancia total: " + distancia[destino] + " km");
    }
}
