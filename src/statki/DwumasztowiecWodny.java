/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class DwumasztowiecWodny extends Statek {
    
    private Point[] dwumasztowiec;
    
    public DwumasztowiecWodny(){
        
        dwumasztowiec = new Point[2];
        dwumasztowiec[0] = new Point(0,0);
        dwumasztowiec[1] = new Point(1,0);
        
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[dwumasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwumasztowiec.length ; i++)
            kopia[i] = (Point) dwumasztowiec[i].clone();
        
        if(dwumasztowiec[0].x - dwumasztowiec[1].x == -1){
            
            dwumasztowiec[1].x -= 1;
            dwumasztowiec[1].y += 1;
            
        }
        else if(dwumasztowiec[0].x - dwumasztowiec[1].x == 0){
            
            dwumasztowiec[1].x += 1;
            dwumasztowiec[1].y -= 1;
        }
        
        for(int i = 0; i < dwumasztowiec.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(dwumasztowiec[i].x > 21 || dwumasztowiec[i].x < 0 || dwumasztowiec[i].y > 13 || dwumasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                dwumasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[dwumasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwumasztowiec.length ; i++)
            kopia[i] = (Point) dwumasztowiec[i].clone();
        for(int i = 0; i < dwumasztowiec.length ; i++){
            
            dwumasztowiec[i].x += x; // dodajemy do x przesuniecie o int x 
            dwumasztowiec[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(dwumasztowiec[i].x > 21 || dwumasztowiec[i].x < 0 || dwumasztowiec[i].y > 13 || dwumasztowiec[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                dwumasztowiec = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[dwumasztowiec.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < dwumasztowiec.length ; i++)
            kopia[i] = (Point) dwumasztowiec[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
}
