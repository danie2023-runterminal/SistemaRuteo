/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class BellmanFord {
     public static void rutaMasCorta(Grafo g, int s, int destino, HashTable ht) {
        int n = g.getN();
        int[] distancia = new int[n];
        int[] padre = new int[n];
        
        for (int v = 0; v < n; v++) {
            distancia[v] = Integer.MAX_VALUE;
            padre[v] = -1;
        }
        
        distancia[s] = 0;
        
        for (int i = 1; i <= n - 1; i++) {
            for (int u = 0; u < n; u++) {
                NodoGrafo temp = g.getAdj(u);
                
                while (temp != null) {
                    int v = temp.destino;
                    int peso = temp.peso;
                    if (distancia[u] != Integer.MAX_VALUE && distancia[v] > distancia[u] + peso) 
                    {
                        distancia[v] = distancia[u] + peso;
                        padre[v] = u;
                    }
                    temp = temp.next;
                }
            }
        }
        
        for (int u = 0; u < n; u++) {
            NodoGrafo temp = g.getAdj(u);
            while (temp != null) {
                int v = temp.destino;
                int peso = temp.peso;
                if (distancia[u] != Integer.MAX_VALUE && distancia[v] > distancia[u] + peso) {
                    System.out.println("El grafo contiene un ciclo de peso negativo");
                    return;
                }
                temp = temp.next;
            }
        }
        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre "
                + ht.search(s) + " y " + ht.search(destino));
            return;
        }
        Pila pila = new Pila();
        int actual = destino;
        while (actual != -1) {
            pila.push(actual);
            actual = padre[actual];
        }
        System.out.println("\n=== Bellman-Ford ===");
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