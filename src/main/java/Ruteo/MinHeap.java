/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class MinHeap {
    private int[] nodos;
    private int[] distancias;
    private int tamano;
    private int capacidad;
    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        nodos = new int[capacidad];
        distancias = new int[capacidad];
        tamano = 0;
    }
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }
    
    private void heapifyUp(int i) {
        while (i > 0 && distancias[i] < distancias[parent(i)]) {
            int tempNodo = nodos[i];
            int tempDist = distancias[i];
            nodos[i] = nodos[parent(i)];
            distancias[i] = distancias[parent(i)];
            nodos[parent(i)] = tempNodo;
            distancias[parent(i)] = tempDist;
            i = parent(i);
        }
    }
    private void heapifyDown(int i) {
        while (left(i) < tamano) {
            int hijoIzq = left(i);
            int hijoDer = right(i);
            int menor = hijoIzq;
            if (hijoDer < tamano && distancias[hijoDer] < distancias[menor]) {
                menor = hijoDer;
            }
            if (distancias[i] > distancias[menor]) {
                int tempNodo = nodos[i];
                int tempDist = distancias[i];
                nodos[i] = nodos[menor];
                distancias[i] = distancias[menor];
                nodos[menor] = tempNodo;
                distancias[menor] = tempDist;
                i = menor;
            } else {
                break;
            }
        }
    }
    public void insertar(int nodo, int distancia) {
        nodos[tamano] = nodo;
        distancias[tamano] = distancia;
        tamano++;
        heapifyUp(tamano - 1);
    }
    public int extraerMinimo() {
        if (tamano == 0) {
            return -1;
        }
        int minNodo = nodos[0];
        nodos[0] = nodos[tamano - 1];
        distancias[0] = distancias[tamano - 1];
        tamano--;
        if (tamano > 0) {
            heapifyDown(0);
        }
        return minNodo;
    }
    public void eliminarEn(int i) {
        if (i <0 || i>=tamano) {
            return;
        }
        int tempNodo = nodos[i];
        int tempDist = distancias[i];
        nodos[i] = nodos[tamano - 1];
        distancias[i] = distancias[tamano - 1];
        nodos[tamano - 1] = tempNodo;
        distancias[tamano - 1] = tempDist;
        tamano--;
        if (i > 0 && distancias[i] < distancias[parent(i)]) {
            heapifyUp(i);
        } else {
            heapifyDown(i);
        }
    }
    public boolean isEmpty() {
        return tamano == 0;
    }
    public void imprimir(HashTable ht) {
        for (int i = 0; i < tamano; i++) {
            System.out.print(ht.search(nodos[i]) + 
                "(" + distancias[i] + "km) ");
        }
        System.out.println();
    }
}
