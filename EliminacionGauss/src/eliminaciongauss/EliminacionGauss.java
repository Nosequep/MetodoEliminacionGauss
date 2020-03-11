/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eliminaciongauss;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author abrah
 */
public class EliminacionGauss {

    private DecimalFormat formato;
    private double[][] matriz1;
    private double[][] matriz2;
    private double[] soluciones;
    
    public EliminacionGauss(){
        this.formato = new DecimalFormat("#0.0000");
    }
    public int leerEcuaciones(){
        
        Scanner teclado = new Scanner(System.in);
        

        int canEcuaciones = 3;
        this.matriz1 = new double[canEcuaciones][4];
        
        System.out.println("Ingrese los valores de los coeficientes de las 3 ecuaciónes: ");
        
        for(int f = 0; f < canEcuaciones; f++){
            System.out.println("---Ecuación" +(f+1)+"---");
            for(int c = 0; c < 3; c++){
                System.out.println("coeficiente x"+(c+1)+": ");
                this.matriz1[f][c] = teclado.nextDouble();
            }
            System.out.println("resultado: ");
            this.matriz1[f][3] = teclado.nextDouble();
        }
        
        
        return canEcuaciones;
    }
    
    public void eliminacionGauss(){
        this.matriz2 = this.matriz1.clone();
        this.eliminacionAdelante();
        this.calcularSolucion();
        
    }
    
    private void eliminacionAdelante(){
        
        
        for(int n = 0; n < 2; n++){
            for(int f = n; f < this.matriz2.length; f++){
                if(this.matriz1[f][n] != 0 || this.matriz1[f][0] != 1){
                    double divisor = this.matriz2[f][n];
                    for(int c = n; c < this.matriz1[f].length; c++){
                        this.matriz2[f][c] = Double.parseDouble(this.formato.format((this.matriz2[f][c]/divisor)));
                    }
                }

                if(f != n){
                    double multiplicador = this.matriz2[f][n]*(-1);
                    for(int c = n; c < this.matriz1[f].length; c++){
                        this.matriz2[f][c] = Double.parseDouble(this.formato.format(this.matriz2[f][c]+(this.matriz2[n][c]*multiplicador)));
                    }
                }
                
            
            }
        }
        
        
    }
    
    private void calcularSolucion(){
        this.soluciones = new double[3];
        
        for(int f = this.matriz2.length-1 ; f >= 0; f--){
            double suma = 0;
            int count = 0;
            for(int c = 0; c < this.matriz2[f].length-1 ; c++){

                if(this.matriz2[f][c] != 0){

                    if(count != 0){

                        suma += (this.matriz2[f][c] * this.soluciones[c]);    
                    }
                    count++;
                }
            }

            this.soluciones[f] = (suma*-1) + this.matriz2[f][this.matriz2[f].length-1];
        }
    }
    
    public void despligaSolucion(){
        
        
        System.out.println("-------------Solución-----------------");
        for(int f = 0; f< this.soluciones.length; f++){
            System.out.println("x" + (f+1) + ": " + this.soluciones[f]);
        }
    }
    

    public static void main(String[] args) {
        
       EliminacionGauss prueba = new EliminacionGauss();
       prueba.leerEcuaciones();
       prueba.eliminacionGauss();
       prueba.despligaSolucion();
    }
    
}
