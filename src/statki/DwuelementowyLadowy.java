/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class DwuelementowyLadowy extends Statek{
    
    private Point[] dwuelementowy;
    
    public DwuelementowyLadowy(){
        
        dwuelementowy = new Point[2];
        dwuelementowy[0] = new Point(0,0);
        dwuelementowy[1] = new Point(1,0);
        
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[dwuelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwuelementowy.length ; i++)
            kopia[i] = (Point) dwuelementowy[i].clone();
        
        if(dwuelementowy[0].x - dwuelementowy[1].x == -1){
            
            dwuelementowy[1].x -= 1;
            dwuelementowy[1].y += 1;
            
        }
        else if(dwuelementowy[0].x - dwuelementowy[1].x == 0){
            
            dwuelementowy[1].x += 1;
            dwuelementowy[1].y -= 1;
        }
        
        for(int i = 0; i < dwuelementowy.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(dwuelementowy[i].x > 21 || dwuelementowy[i].x < 0 || dwuelementowy[i].y > 13 || dwuelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                dwuelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
       Point[] kopia = new Point[dwuelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwuelementowy.length ; i++)
            kopia[i] = (Point) dwuelementowy[i].clone();
        for(int i = 0; i < dwuelementowy.length ; i++){
            
            dwuelementowy[i].x += x; // dodajemy do x przesuniecie o int x 
            dwuelementowy[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(dwuelementowy[i].x > 21 || dwuelementowy[i].x < 0 || dwuelementowy[i].y > 13 || dwuelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                dwuelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[dwuelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwuelementowy.length ; i++)
            kopia[i] = (Point) dwuelementowy[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
    
}
