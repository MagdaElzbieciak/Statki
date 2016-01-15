/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class TrojmasztowiecWodny extends Statek {
   
    private Point[] trojmasztowiec;
    
    public TrojmasztowiecWodny(){
        
        trojmasztowiec = new Point[3];
        trojmasztowiec[0] = new Point(0,0);
        trojmasztowiec[1] = new Point(1,0);
        trojmasztowiec[2] = new Point(2,0);
        
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[trojmasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojmasztowiec.length ; i++)
            kopia[i] = (Point) trojmasztowiec[i].clone();
        
        if(trojmasztowiec[0].x - trojmasztowiec[1].x == -1){
            
            trojmasztowiec[1].x -= 1;
            trojmasztowiec[1].y += 1;
            trojmasztowiec[2].x -= 2;
            trojmasztowiec[2].y += 2;
            
        }
        else if(trojmasztowiec[0].x - trojmasztowiec[1].x == 0){
            
            trojmasztowiec[1].x += 1;
            trojmasztowiec[1].y -= 1;
            trojmasztowiec[2].x += 2;
            trojmasztowiec[2].y -= 2;
        }
        
        
        for(int i = 0; i < trojmasztowiec.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(trojmasztowiec[i].x > 21 || trojmasztowiec[i].x < 0 || trojmasztowiec[i].y > 13 || trojmasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                trojmasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
       Point[] kopia = new Point[trojmasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojmasztowiec.length ; i++)
            kopia[i] = (Point) trojmasztowiec[i].clone();
        for(int i = 0; i < trojmasztowiec.length ; i++){
            
            trojmasztowiec[i].x += x; // dodajemy do x przesuniecie o int x 
            trojmasztowiec[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(trojmasztowiec[i].x > 21 || trojmasztowiec[i].x < 0 || trojmasztowiec[i].y > 13 || trojmasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                trojmasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[trojmasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojmasztowiec.length ; i++)
            kopia[i] = (Point) trojmasztowiec[i].clone();
        return kopia;
        
    }
    
}
