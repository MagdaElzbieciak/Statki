/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class CzteromasztowiecWodny extends Statek {
    
    private Point[] czteromasztowiec;
    
    public CzteromasztowiecWodny(){
        
        czteromasztowiec = new Point[4];
        czteromasztowiec[0] = new Point(0,0);
        czteromasztowiec[1] = new Point(1,0);
        czteromasztowiec[2] = new Point(2,0);
        czteromasztowiec[3] = new Point(3,0);
        
    }
    
    public void rotacja(){
        
       Point[] kopia = new Point[czteromasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteromasztowiec.length ; i++)
            kopia[i] = (Point) czteromasztowiec[i].clone();
        
        if(czteromasztowiec[0].x - czteromasztowiec[1].x == -1){
            
            czteromasztowiec[1].x -= 1;
            czteromasztowiec[1].y += 1;
            czteromasztowiec[2].x -= 2;
            czteromasztowiec[2].y += 2;
            czteromasztowiec[3].x -= 3;
            czteromasztowiec[3].y += 3;
            
        }
        else if(czteromasztowiec[0].x - czteromasztowiec[1].x == 0){
            
            czteromasztowiec[1].x += 1;
            czteromasztowiec[1].y -= 1;
            czteromasztowiec[2].x += 2;
            czteromasztowiec[2].y -= 2;
            czteromasztowiec[3].x += 3;
            czteromasztowiec[3].y -= 3;
            
        }
        
        
        for(int i = 0; i < czteromasztowiec.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(czteromasztowiec[i].x > 21 || czteromasztowiec[i].x < 0 || czteromasztowiec[i].y > 13 || czteromasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                czteromasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[czteromasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteromasztowiec.length ; i++)
            kopia[i] = (Point) czteromasztowiec[i].clone();
        for(int i = 0; i < czteromasztowiec.length ; i++){
            
            czteromasztowiec[i].x += x; // dodajemy do x przesuniecie o int x 
            czteromasztowiec[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(czteromasztowiec[i].x > 21 || czteromasztowiec[i].x < 0 || czteromasztowiec[i].y > 13 || czteromasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                czteromasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[czteromasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < czteromasztowiec.length ; i++)
            kopia[i] = (Point) czteromasztowiec[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
    
}
