/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class ColaPrioridad {
    private NodoC frente;
    private NodoC fin;
    
    public ColaPrioridad() {
        frente = null;
        fin = null;
    }
    public void enqueue(int valor) {
        NodoC nuevo = new NodoC(valor);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.next = nuevo;
            fin = nuevo;
        }
    }
    public int dequeue(int[] distancia) {
        if (isEmpty()){
            System.out.println("ColaVacia");
            return -1;
        }
        NodoC temp = frente;
        NodoC menorNodo = frente;
        NodoC anteriorMenor = null;
        NodoC anterior = null;
        while (temp != null) {
            if (distancia[temp.valor] < distancia[menorNodo.valor]) {
                menorNodo = temp;
                anteriorMenor = anterior;
            }
            anterior = temp;
            temp = temp.next;
        }
        if (anteriorMenor == null) {
            frente = frente.next;
        } else {
            anteriorMenor.next = menorNodo.next;
        }
        if(menorNodo == fin) {
            fin = anteriorMenor;
        }return menorNodo.valor;
    }
    public boolean isEmpty() {
        return frente == null;
    }
}
