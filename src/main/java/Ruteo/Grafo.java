/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class Grafo {
     private NodoGrafo[] listaAdyacencia;
    private int n;

    public Grafo(int n) {
        this.n = n;
        listaAdyacencia = new NodoGrafo[n];
    } //En  el arreglo listaAdyacencia cada posicion representa un nodo
    //Dentro de cada nodo, tenemos sus conexiones que son representadas como listas enlazadas
    public void agregarArista(int origen, int destino, int peso) {
         NodoGrafo nuevo = new NodoGrafo(destino, peso);
        nuevo.next = listaAdyacencia[origen];
        listaAdyacencia[origen] = nuevo;
        
        NodoGrafo nuevo2 = new NodoGrafo(origen, peso);
        nuevo2.next = listaAdyacencia[destino];
        listaAdyacencia[destino] = nuevo2;
    }
    public void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
            NodoGrafo temp = listaAdyacencia[i];
            while (temp != null) {
                System.out.print(temp.destino + " -> ");
                temp = temp.next;
            }
            System.out.println("NULL");
        }
    }
 public void mostrar(HashTable ht) {
        for (int i = 0; i < n; i++) {
            System.out.print(ht.search(i) + " -> ");
            NodoGrafo temp = listaAdyacencia[i];
            while (temp != null) {
                System.out.print(ht.search(temp.destino) 
                    + "(" + temp.peso + "km) -> ");
                temp = temp.next;
            }
            System.out.println("NULL");
        }
    }
    
    public NodoGrafo getAdj(int i) {
        return listaAdyacencia[i];
    }
    
    public int getN() {
        return n;
    }

}
