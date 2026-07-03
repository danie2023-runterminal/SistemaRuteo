/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruteo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author JoseDaniel
 */

//Clase base adaptada de una plantilla de terceros.
//Encargada de leer un archivo de texto secuencialmente para inicializar 
//las estructuras de datos del proyecto (Grafo, Tabla Hash y Coordenadas).
public class LectorArchivos {
    public static void cargarDatos(String ruta, Grafo g, HashTable ht, int[][] coordenadas) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean leyendoNodos = false;
            boolean leyendoAristas = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()){
                    continue;
                }

                if (linea.equals("---NODOS---")) {
                    leyendoNodos = true; 
                    leyendoAristas = false; 
                    continue;
                } else if (linea.equals("---ARISTAS---")) {
                    leyendoNodos = false;
                    leyendoAristas = true; 
                    continue;
                }

                if (leyendoNodos) {
                    String[] datos = linea.split(",");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    
                    ht.insert(id, nombre);
                    coordenadas[id][0] = Integer.parseInt(datos[2]); // X
                    coordenadas[id][1] = Integer.parseInt(datos[3]); // Y
                    
                } else if (leyendoAristas) {
                    String[] datos = linea.split(",");
                    int origen = Integer.parseInt(datos[0]);
                    int destino = Integer.parseInt(datos[1]);
                    int peso = Integer.parseInt(datos[2]);
                    
                    g.agregarArista(origen, destino, peso);
                }
            }
            System.out.println("Archivo cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
