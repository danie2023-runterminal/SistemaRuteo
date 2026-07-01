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
//    public void bfs(int start){
//        boolean[] visited = new boolean[n];
//        ColaPrioridad q = new ColaPrioridad();
//        visited[start] = true;
//        q.enqueue(start);
//        while(!q.isEmpty()){
//            int v = q.dequeue();
//            System.out.print(v + " ");
//            NodoGrafo temp = listaAdyacencia[v]; 
//            while(temp != null){
//                int vecino = temp.destino;
//                if(!visited[vecino]){
//                    visited[vecino] = true;
//                    q.enqueue(vecino);
//                }
//                temp = temp.next;
//            }
//        }
//    }
//    public void dfs(int start){
//    boolean[] visited = new boolean[n];
//    Pila pila = new Pila();
//    pila.push(start);
//    while(!pila.isEmpty()){
//        int v = pila.pop();
//        if(!visited[v]){
//            visited[v] = true;
//            System.out.print(v + " ");
//            NodoGrafo temp = listaAdyacencia[v];
//            while(temp != null){
//                int vecino = temp.destino;
//                if(!visited[vecino]){
//                    pila.push(vecino);
//                }
//                temp = temp.next;
//            }
//        }
//    }
//}
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
