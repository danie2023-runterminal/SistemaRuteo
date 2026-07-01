/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;

/**
 *
 * @author JoseDaniel
 */
public class HashTable {
      int capacity;
    NodoH[] table;
    public HashTable(int size){
        capacity = size;
        table = new NodoH[capacity];
    }
    int hashFunction(int key){
        return key%capacity;
    }
    public void insert(int key, String value) {
    int index = hashFunction(key);
    if (table[index] == null) {
        table[index] = new NodoH(key, value);
    } else {
        NodoH node = table[index];
        while (node.next != null && node.key != key) {
            node = node.next;
        }
        if (node.key == key) {
            node.value = value;
        } else {
            node.next = new NodoH(key, value);
        }
    }
}
    public String search(int key) {
    int index = hashFunction(key);
    NodoH node = table[index];
    while (node != null && node.key != key) {
        node = node.next;
    }
    if (node == null){ 
        return "-1";
    }
    return node.value;
}
    public void remove(int key) {
    int index = hashFunction(key);
    if (table[index] == null) return;
    if (table[index].key == key) {
        table[index] = table[index].next;
    } else {
        NodoH node = table[index];
        while (node.next != null && node.next.key != key) {
            node = node.next;
        }

        if (node.next != null) {
            node.next = node.next.next;
        }
    }
}
}
