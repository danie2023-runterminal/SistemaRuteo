/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class AStar {
    private static int calcularHeuristica(int actual, int meta, int[][] coordenadas) {
        // Cálculo de las distancias en los ejes cartesianos X e Y
        int dx = coordenadas[actual][0] - coordenadas[meta][0];
        int dy = coordenadas[actual][1] - coordenadas[meta][1];
        // Se aplica la fórmula de la hipotenusa para obtener la distancia recta exacta.
        // El resultado de Math.sqrt (que es double) se castea explícitamente a int 
        // porque la estructura del grafo maneja los pesos y distancias como números enteros.
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    
    public static void rutaMasCorta(Grafo g, int inicio, int destino,HashTable ht, int[][] coordenadas) {
        int n = g.getN();
        // distancia[] representa g(n): el costo real exacto desde el inicio hasta el nodo actual
        int[] distancia = new int[n]; 
        // f_costo[] representa f(n): el costo total estimado (distancia real + heurística)
        int[] f_costo = new int[n];    
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        // Inicialización de estructuras
        for (int i = 0; i < n; i++) {
            distancia[i] = Integer.MAX_VALUE;
            f_costo[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
            visitado[i] = false;
        }
        distancia[inicio] = 0;
        // El costo inicial es la estimación desde el origen hasta la meta
        f_costo[inicio] = calcularHeuristica(inicio, destino, coordenadas);
        // El MinHeap prioriza los nodos basándose en f_costo, no solo en la distancia,
        // garantizando que se exploren primero los caminos que "apuntan" al destino.
        MinHeap heap = new MinHeap(n * n);
        heap.insertar(inicio, f_costo[inicio]);
        while (!heap.isEmpty()) {
            int u = heap.extraerMinimo();
            // Optimización clave de A*: Si el nodo extraído con el menor costo estimado 
            // es nuestro destino, hemos garantizado la ruta óptima y abortamos el ciclo.
            if (u == destino) break;
            if (visitado[u]) continue;
            visitado[u] = true;
            if (distancia[u] == Integer.MAX_VALUE) break;
            NodoGrafo temp = g.getAdj(u);
            // Relajación de aristas informada por la heurística
            while (temp != null) {
                int v = temp.destino;
                int peso = temp.peso;
                if (!visitado[v] && distancia[u] + peso < distancia[v]) {
                    // Se actualiza el costo real (g)
                    distancia[v] = distancia[u] + peso;
                    // Se recalcula el costo total (f) = costo real + nueva estimación al objetivo
                    f_costo[v] = distancia[v] + calcularHeuristica(v, destino, coordenadas);
                    predecesor[v] = u;
                    heap.insertar(v, f_costo[v]);
                }
                temp = temp.next;
            }
        }
        // Validación en caso de que el destino esté aislado
        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre "
                + ht.search(inicio) + " y " + ht.search(destino));
            return;
        }
        // Reconstrucción del trayecto óptimo utilizando la Pila
        Pila pila = new Pila();
        int actual = destino;
        while (actual != -1) {
            pila.push(actual);
            actual = predecesor[actual];
        }
        
        System.out.println("\n=== A-Estrella (A*) ===");
        System.out.print("Ruta: ");
        while (!pila.isEmpty()) {
            System.out.print(ht.search(pila.pop()));
            if (!pila.isEmpty()) System.out.print(" -> ");
        }
        System.out.println("\nDistancia total real: " + distancia[destino] + " km");
    }
}
