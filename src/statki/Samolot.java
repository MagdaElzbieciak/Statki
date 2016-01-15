/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class Samolot extends Statek{
    
    private Point[] samolot;
            
    public Samolot(){
        
        samolot = new Point[4]; // deklarujemy punkty samolotu 
        samolot[0] = new Point(1,0);
        samolot[1] = new Point(0,1);
        samolot[2] = new Point(1,1);
        samolot[3] = new Point(2,1);
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[samolot.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < samolot.length ; i++)
            kopia[i] = (Point) samolot[i].clone();
        
        if(samolot[0].x - samolot[1].x == 1 && samolot[0].y - samolot[1].y == -1 ){ // pierwszy układ (1), sprawdzamy w jakiej jest pozycji 
            
            samolot[0].x -= 1; // różnica miedzy  1 a 2 
            samolot[2].x -= 1;
            samolot[2].y += 1;
            samolot[3].x -= 1;
            
        }
        else if (samolot[0].x - samolot[1].x == 0 && samolot[0].y - samolot[1].y == -1 ){ // układ drugi
            
            samolot[1].x += 1; // różnica miedzy 2 a 3
            samolot[1].y -= 1;
            samolot[2].x += 2;
            samolot[2].y -= 2;
            
        }
        else if (samolot[0].x - samolot[1].x == -1 && samolot[0].y - samolot[1].y == 0) { //układ trzeci 
            
            samolot[0].y += 1; // różnica miedzy 3 a 4
            samolot[2].x -= 1;
            samolot[2].y += 1;
            samolot[3].y += 1;
            System.out.println("kupka");
            
        }
        else if (samolot[0].x - samolot[1].x == -1 && samolot[0].y - samolot[1].y == 1){ // układ czwarty
            
            samolot[0].x += 1; // rożnica miedzy 4 a 1 
            samolot[0].y -= 1;
            samolot[1].x -= 1;
            samolot[1].y += 1;
            samolot[3].x += 1;
            samolot[3].y -= 1;
        }
        for(int i = 0; i < samolot.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(samolot[i].x > 21 || samolot[i].x < 0 || samolot[i].y > 13 || samolot[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                samolot = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[samolot.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < samolot.length ; i++)
            kopia[i] = (Point) samolot[i].clone();
        
        for(int i = 0; i < samolot.length ; i++){
            
            samolot[i].x += x; // dodajemy do x przesuniecie o int x 
            samolot[i].y += y; // dodajemy do y przesunięcie o int y 
            if(samolot[i].x > 21 || samolot[i].x < 0 || samolot[i].y > 13 || samolot[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                samolot = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        return samolot.clone();
        
    }

           
    
}
