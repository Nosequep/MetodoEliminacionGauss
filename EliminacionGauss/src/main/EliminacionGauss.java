/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author labcisco
 */
public class EliminacionGauss {

    private double[][] mInicio;
    private double[][] mProcedimiento;
    private double[] soluciones;
    
    
    public int leerEcuaciones(){
        
        Scanner teclado = new Scanner(System.in);
        

        int canEcuaciones = 3;
        this.mInicio = new double[canEcuaciones][4];
        
        System.out.println("Ingrese los valores de los coeficientes de las 3 ecuaciónes: ");
        
        for(int f = 0; f < canEcuaciones; f++){
            System.out.println("---Ecuación" +(f+1)+"---");
            for(int c = 0; c < 3; c++){
                System.out.println("coeficiente x"+(c+1)+": ");
                this.mInicio[f][c] = teclado.nextDouble();
            }
            System.out.println("resultado: ");
            this.mInicio[f][3] = teclado.nextDouble();
        }
        
        
        return canEcuaciones;
    }
    
    public void eliminacionGauss(double[][] matriz){
        this.mInicio = matriz;
        this.mProcedimiento = this.mInicio.clone();
        this.eliminacionAdelante(this.mProcedimiento);
        this.sustitucionAtras(this.mProcedimiento);
    }
    
    private void eliminacionAdelante(double[][] matriz){


        //Hace las iteraciónes necesarias por cada pivote
        for(int p = 0; p < matriz.length-1; p++){
            this.pivoteo(matriz, p);
            for(int f = p+1; f < matriz.length; f++){

                double igualacion = matriz[f][p]/matriz[p][p];

                //Aplica las operaciones a la fila (ecuación)
                for(int c = p; c < matriz[f].length; c++){
                    matriz[f][c] = matriz[f][c] - (matriz[p][c]*igualacion);
                }
            }
        }
        
    } 
    
    private void pivoteo(double[][] matriz, int fila){
        double aux;
        for(int f = fila; f < matriz.length-1; f++){
         
            for(int c = fila; c < matriz.length-1; c++){
                if(matriz[c][fila] < matriz[c+1][fila]){
                    
                    for(int i = 0; i < matriz[f].length; i++){
                        aux = matriz[c][i];
                        matriz[c][i] = matriz[c+1][i];
                        matriz[c+1][i] = aux;    
                    }
                }
            }
        }
    }
    
    private void sustitucionAtras(double[][] matriz){
        this.soluciones = new double[3];
        
        for(int f = matriz.length-1 ; f >= 0; f--){
            double suma = 0;
            int count = 0;
            for(int c = f; c < matriz[f].length-1 ; c++){
                if(count != 0){

                    suma += (matriz[f][c] * this.soluciones[c]);    
                }
                count++;
            }
            this.soluciones[f] = (matriz[f][matriz[f].length-1]+(-1*suma))/matriz[f][f];
        }
    }
    
    public void desplegarSolucion(){
        System.out.println("-------------Solución-----------------");
        for(int f = 0; f< this.soluciones.length; f++){
            System.out.println("x" + (f+1) + ": " + this.soluciones[f]);
        }
    }
    
    public void imprimir(double[][] matriz){
        System.out.println("-----------------------------------");
        for(int f = 0; f < matriz.length; f++){
            for(int c = 0; c < matriz[f].length; c++){
                System.out.print(matriz[f][c] + ", ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------");
    }
    
    public static void main(String[] args) {
        
        EliminacionGauss p = new EliminacionGauss();
        
       
        double[][] c = {
            {3,4,3,10},
            {1,5,-1, 7},
            {6,3,7, 15}};
        
         
//        p.leerEcuaciones();
        p.eliminacionGauss(c);
        p.desplegarSolucion();
   
    }
    
}
