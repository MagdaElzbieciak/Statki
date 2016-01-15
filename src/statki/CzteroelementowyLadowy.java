/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;


public class CzteroelementowyLadowy extends Statek {

    private Point[] czteroelementowy;
    
    public CzteroelementowyLadowy(){
        
        czteroelementowy = new Point[4];
        czteroelementowy[0] = new Point(0,0);
        czteroelementowy[1] = new Point(1,0);
        czteroelementowy[2] = new Point(2,0);
        czteroelementowy[3] = new Point(3,0);
        
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[czteroelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteroelementowy.length ; i++)
            kopia[i] = (Point) czteroelementowy[i].clone();
        
        if(czteroelementowy[0].x - czteroelementowy[1].x == -1){
            
            czteroelementowy[1].x -= 1;
            czteroelementowy[1].y += 1;
            czteroelementowy[2].x -= 2;
            czteroelementowy[2].y += 2;
            czteroelementowy[3].x -= 3;
            czteroelementowy[3].y += 3;
            
        }
        else if(czteroelementowy[0].x - czteroelementowy[1].x == 0){
            
            czteroelementowy[1].x += 1;
            czteroelementowy[1].y -= 1;
            czteroelementowy[2].x += 2;
            czteroelementowy[2].y -= 2;
            czteroelementowy[3].x += 3;
            czteroelementowy[3].y -= 3;
            
        }
        
        
        for(int i = 0; i < czteroelementowy.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(czteroelementowy[i].x > 21 || czteroelementowy[i].x < 0 || czteroelementowy[i].y > 13 || czteroelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                czteroelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[czteroelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteroelementowy.length ; i++)
            kopia[i] = (Point) czteroelementowy[i].clone();
        for(int i = 0; i < czteroelementowy.length ; i++){
            
            czteroelementowy[i].x += x; // dodajemy do x przesuniecie o int x 
            czteroelementowy[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(czteroelementowy[i].x > 21 || czteroelementowy[i].x < 0 || czteroelementowy[i].y > 13 || czteroelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                czteroelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[czteroelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteroelementowy.length ; i++)
            kopia[i] = (Point) czteroelementowy[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
}
