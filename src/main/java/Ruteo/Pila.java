/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class Pila {
    private NodoP tope;
    
    public Pila() {
        tope = null;
    }
    
    public void push(int valor) {
        NodoP nuevo = new NodoP(valor);
        nuevo.next = tope;
        tope = nuevo;
    }
    
    public int pop() {
        if (isEmpty()) {
            System.out.println("PilaVacia");
            return -1;
        }
        int valor = tope.valor;
        tope = tope.next;
        return valor;
    }
    
    public boolean isEmpty() {
        return tope == null;
    } 
}
