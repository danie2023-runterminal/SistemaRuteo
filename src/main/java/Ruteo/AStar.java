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
        int dx = coordenadas[actual][0] - coordenadas[meta][0];
        int dy = coordenadas[actual][1] - coordenadas[meta][1];
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    
    public static void rutaMasCorta(Grafo g, int inicio, int destino, 
                                    HashTable ht, int[][] coordenadas) {
        int n = g.getN();
        int[] distancia = new int[n]; 
        int[] f_costo = new int[n];    
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            distancia[i] = Integer.MAX_VALUE;
            f_costo[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
            visitado[i] = false;
        }
        distancia[inicio] = 0;
        f_costo[inicio] = calcularHeuristica(inicio, destino, coordenadas);
        MinHeap heap = new MinHeap(n * n);
        heap.insertar(inicio, f_costo[inicio]);
        while (!heap.isEmpty()) {
            int u = heap.extraerMinimo();
            if (u == destino) break;
            if (visitado[u]) continue;
            visitado[u] = true;
            if (distancia[u] == Integer.MAX_VALUE) break;
            NodoGrafo temp = g.getAdj(u);
            while (temp != null) {
                int v = temp.destino;
                int peso = temp.peso;
                if (!visitado[v] && distancia[u] + peso < distancia[v]) {
                    distancia[v] = distancia[u] + peso;
                    f_costo[v] = distancia[v] + calcularHeuristica(v, destino, coordenadas);
                    predecesor[v] = u;
                    heap.insertar(v, f_costo[v]);
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
        
        System.out.println("\n=== A-Estrella (A*) ===");
        System.out.print("Ruta: ");
        while (!pila.isEmpty()) {
            System.out.print(ht.search(pila.pop()));
            if (!pila.isEmpty()) System.out.print(" -> ");
        }
        System.out.println("\nDistancia total real: " + distancia[destino] + " km");
    }
}
