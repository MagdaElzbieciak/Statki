/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;


public class JednomasztowiecWodny extends Statek{
    
    private Point[] jednomasztowiec;
    
    public JednomasztowiecWodny(){
        
        jednomasztowiec = new Point[1];
        jednomasztowiec[0] = new Point(0,0);
        
        
    }
    
    public void rotacja(){
        
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[jednomasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < jednomasztowiec.length ; i++)
            kopia[i] = (Point) jednomasztowiec[i].clone();
        for(int i = 0; i < jednomasztowiec.length ; i++){
            
            jednomasztowiec[i].x += x; // dodajemy do x przesuniecie o int x 
            jednomasztowiec[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(jednomasztowiec[i].x > 21 || jednomasztowiec[i].x < 0 || jednomasztowiec[i].y > 13 || jednomasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                jednomasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[jednomasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < jednomasztowiec.length ; i++)
            kopia[i] = (Point) jednomasztowiec[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
    
}
