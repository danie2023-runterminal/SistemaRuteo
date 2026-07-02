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
   public static void rutaMasCorta(Grafo g, int inicio, int destino, HashTable ht) {
        int n = g.getN();
        int[] distancia = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            distancia[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
            visitado[i] = false;
        }
        distancia[inicio] = 0;
        MinHeap heap = new MinHeap(n * n);
        heap.insertar(inicio, 0);
        while (!heap.isEmpty()) {
            int u = heap.extraerMinimo();
            if (visitado[u]) continue;
            visitado[u] = true;
            if (distancia[u] == Integer.MAX_VALUE) break;
            NodoGrafo temp = g.getAdj(u);
            while (temp != null) {
                int v = temp.destino;
                int peso = temp.peso;
                if (!visitado[v] && distancia[u] + peso < distancia[v]) {
                    distancia[v] = distancia[u] + peso;
                    predecesor[v] = u;
                    heap.insertar(v, distancia[v]);
                }
                temp = temp.next;
            }
        }
        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre "
                + ht.search(inicio) + " y " + ht.search(destino));
            return;
        }
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
            if (!pila.isEmpty()) System.out.print(" -> ");
        }
        System.out.println("\nDistancia total: " + distancia[destino] + " km");
    }
}
