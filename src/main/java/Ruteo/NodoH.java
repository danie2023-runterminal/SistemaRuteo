/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
//Nodo para la tabla hash
public class NodoH {
    int key;
    String value;
    NodoH next;
    public NodoH(int k , String v){
        key = k;
        value = v;
        next = null;
    }   
}
