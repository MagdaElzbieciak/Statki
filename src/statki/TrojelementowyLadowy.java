/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;

public class TrojelementowyLadowy extends Statek{
    
    private Point[] trojelementowy;
    
    public TrojelementowyLadowy(){
        
        trojelementowy = new Point[3];
        trojelementowy[0] = new Point(0,0);
        trojelementowy[1] = new Point(1,0);
        trojelementowy[2] = new Point(2,0);
        
    }
    
    public void rotacja(){
        
        Point[] kopia = new Point[trojelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojelementowy.length ; i++)
            kopia[i] = (Point) trojelementowy[i].clone();
        
        if(trojelementowy[0].x - trojelementowy[1].x == -1){
            
            trojelementowy[1].x -= 1;
            trojelementowy[1].y += 1;
            trojelementowy[2].x -= 2;
            trojelementowy[2].y += 2;
            
        }
        else if(trojelementowy[0].x - trojelementowy[1].x == 0){
            
            trojelementowy[1].x += 1;
            trojelementowy[1].y -= 1;
            trojelementowy[2].x += 2;
            trojelementowy[2].y -= 2;
        }
        
        
        for(int i = 0; i < trojelementowy.length ; i++){ // sprawdzamy czy nie wyszło poza układ
            
            if(trojelementowy[i].x > 21 || trojelementowy[i].x < 0 || trojelementowy[i].y > 13 || trojelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                trojelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
        
    }
    
    public void przesunecie(int x, int y){ //translacja statku
        
        Point[] kopia = new Point[trojelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojelementowy.length ; i++)
            kopia[i] = (Point) trojelementowy[i].clone();
        for(int i = 0; i < trojelementowy.length ; i++){
            
            trojelementowy[i].x += x; // dodajemy do x przesuniecie o int x 
            trojelementowy[i].y += y; // dodajemy do y przesunięcie o int y 
            
            if(trojelementowy[i].x > 21 || trojelementowy[i].x < 0 || trojelementowy[i].y > 13 || trojelementowy[i].y <0){ // sprawdzamy czy punkt nie wyszedł za tablice 
                
                trojelementowy = kopia; // wracamy do poprzedniej kopii 
                return;
            }
        }
    }
    
    public Point[] zwrocPunkty(){
        
        Point[] kopia = new Point[trojelementowy.length];
        // robimy kompie samolotu zeby móc do niej wrócić
        
        for(int i = 0; i < trojelementowy.length ; i++)
            kopia[i] = (Point) trojelementowy[i].clone();
        return kopia; // zwraca tablice punktów 
        
    }
    
}
