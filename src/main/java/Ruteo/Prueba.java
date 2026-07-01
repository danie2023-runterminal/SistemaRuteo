/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class Prueba {
public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Ruteo...\n");
        int n = 15; 
        HashTable ht = new HashTable(15);
        Grafo g = new Grafo(n);
        int[][] coordenadas = new int[n][2];
        LectorArchivos.cargarDatos("mapa.txt", g, ht, coordenadas);
        System.out.println("--- MAPA ---");
        g.mostrar(ht);
        System.out.println("\nCalculando rutas de Lima a Cusco...");
        
        long t1 = System.currentTimeMillis();
        BellmanFord.rutaMasCorta(g, 0, 3, ht);
        System.out.println("Tiempo: " + (System.currentTimeMillis() - t1) + " ms");
        
        long t2 = System.currentTimeMillis();
        Dijkstra.rutaMasCorta(g, 0, 3, ht);
        System.out.println("Tiempo: " + (System.currentTimeMillis() - t2) + " ms");
        
        long t4 = System.currentTimeMillis();
        AStar.rutaMasCorta(g, 0, 3, ht, coordenadas);
        System.out.println("Tiempo: " + (System.currentTimeMillis() - t4) + " ms");
    }
}
