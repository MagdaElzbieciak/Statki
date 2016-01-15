/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

public abstract class Statek implements Serializable {
    
    public static final byte WODA = 0;
    public static final byte LAD = 1;
    public static final byte TRAFIENIE = 2;
    public static final byte CHYBIENIE = 3;
    
    public void rotacja(){
        
    }
    
    public void przesunecie(int x, int y){
        
    }
    
    public Point[] zwrocPunkty(){
        
        return null;
    }
    
    public static boolean kolizjaStatkow(Statek s1, Statek s2){
        
        Point[] tab_s1 = s1.zwrocPunkty();
        Point[] tab_s2 = s2.zwrocPunkty();
        for(int i = 0; i < tab_s1.length; i++){
            for(int j = 0; j < tab_s2.length; j++){
                
                double odleglosc = Math.sqrt(Math.pow((tab_s2[j].x-tab_s1[i].x), 2)+Math.pow((tab_s2[j].y-tab_s1[i].y), 2));
                if(odleglosc<2){
                    return true; // jeżeli jest kolizcja to zwracamy true
                }
            }
        }
        
        return false; //  jeżeli nie ma kolizcji to zwracamy false
    }
    
    public static boolean sprawdzPodloze(Statek s1, byte[][] plansza){
        
        
        if(s1 instanceof DwuelementowyLadowy || s1 instanceof TrojelementowyLadowy || s1 instanceof CzteroelementowyLadowy){
            Point[] tablica_statku = s1.zwrocPunkty();
            for(int i = 0 ; i <tablica_statku.length; i++){
                if(plansza[tablica_statku[i].x][tablica_statku[i].y] == WODA) // jeśli woda to wtedy nie możemy postawić , woda- 0 
                    return false;
            }
        }
        else if(s1 instanceof JednomasztowiecWodny || s1 instanceof DwumasztowiecWodny || s1 instanceof TrojmasztowiecWodny || s1 instanceof CzteromasztowiecWodny){
            Point[] tablica_statku = s1.zwrocPunkty();
            for(int i = 0 ; i <tablica_statku.length; i++){
                if(plansza[tablica_statku[i].x][tablica_statku[i].y] == LAD) // jeśli ląd 
                    return false;
            }
        }
        
        return true;
    }
    
    public static boolean strzal(Statek s1, Point p){
        
        Point[] tablica_statku = s1.zwrocPunkty();
        for(int i = 0; i < tablica_statku.length; i++){
            
            if(tablica_statku[i].x == p.x && tablica_statku[i].y == p.y) // sprawdzam czy punkt od statku jest równy punktowi od strzału p 
                return true;
            
        }
        
        return false;
    }
    
    public static boolean czyZyje(Statek s, byte[][] plansza){
        
        Point[] tab_s = s.zwrocPunkty();
        for(int i = 0; i < tab_s.length; i++){
            
            if(plansza[tab_s[i].x][tab_s[i].y] != TRAFIENIE)
            return true;
        }
        
        return false;
        
    }
    
    public static void rozmiescStatki(Statek[] statki, byte[][] plansza){
        
        Random r  = new Random();
        Statek s = null;
        boolean kontrolna = true;
        int x, y ;
        //czteromasztowy wodny
        s = FabrykaStatkow.GenerujStatek(RodzajStatku.CzteromasztowiecWodny);
        while(kontrolna){
            if(r.nextBoolean()){
                s.rotacja();
            }
            x = r.nextInt(11);
            y = r.nextInt(14);
            s.przesunecie(x, y);
            
            if(!Statek.sprawdzPodloze(s, plansza)){
                s.przesunecie(-x, -y);
                continue;
            }
            boolean kolizja = false;
            for(int i = 0 ; i < statki.length ; i++){
                if(statki[i] != null){
                    if(Statek.kolizjaStatkow(s, statki[i])){
                        kolizja = true;
                        break;
                    }
                }
            }
            if(!kolizja){
                for(int i = 0 ; i < statki.length; i++){
                    if(statki[i] == null ){
                        statki[i] = s;
                        s = null;
                        break;
                    }
                }
                kontrolna = false;
            }
            else{
                s.przesunecie(-x, -y);
            }
        }
        
        // trojmasztowiec wodny
        
        for(int j = 0; j < 2; j++){
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.TrojMasztowiecWodny);
            while(kontrolna){
                if(r.nextBoolean()){
                    s.rotacja();
                }
                x = r.nextInt(11);
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        }
        
        // dwumasztowiec wodny
        
        for(int j = 0; j < 3; j++){
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.DwumasztowiecWodny);
            while(kontrolna){
                if(r.nextBoolean()){
                    s.rotacja();
                }
                x = r.nextInt(11);
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        }
        
        // jednomasztowiec wodny
        
        for(int j = 0; j < 4; j++){
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.JednomasztowiecWodny);
            while(kontrolna){
                
                x = r.nextInt(11);
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        }
        
        // czteromasztowiec ladowy
        
        
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.CzteroelementowyLadowy);
            while(kontrolna){
                if(r.nextBoolean()){
                    s.rotacja();
                }
                x = r.nextInt(11)+11;
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        
        // trojmasztowiec ladowy
        
        for(int j = 0; j < 2; j++){
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.TrojelementowyLadowy);
            while(kontrolna){
                if(r.nextBoolean()){
                    s.rotacja();
                }
                x = r.nextInt(11)+11;
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        }
        
        // dwumasztowiec ladowy
        
        for(int j = 0; j < 3; j++){
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.DwuelementowyLadowy);
            while(kontrolna){
                if(r.nextBoolean()){
                    s.rotacja();
                }
                x = r.nextInt(11)+11;
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        }
        
        // samolot
        
        
            kontrolna = true;
            s = FabrykaStatkow.GenerujStatek(RodzajStatku.Samolot);
            while(kontrolna){
                int rot = r.nextInt(4);
                for(int i = 0 ; i <= rot ; i++){
                    s.rotacja();
                }
                x = r.nextInt(22);
                y = r.nextInt(14);
                s.przesunecie(x, y);

                if(!Statek.sprawdzPodloze(s, plansza)){
                    s.przesunecie(-x, -y);
                    continue;
                }
                boolean kolizja = false;
                for(int i = 0 ; i < statki.length ; i++){
                    if(statki[i] != null){
                        if(Statek.kolizjaStatkow(s, statki[i])){
                            kolizja = true;
                            break;
                        }
                    }
                }
                if(!kolizja){
                    for(int i = 0 ; i < statki.length; i++){
                        if(statki[i] == null ){
                            statki[i] = s;
                            s = null;
                            break;
                        }
                    }
                    kontrolna = false;
                }
                else{
                    s.przesunecie(-x, -y);
                }
            }
        
    }
}
