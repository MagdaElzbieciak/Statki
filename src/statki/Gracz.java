/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Gracz implements AdapterRysujacy{
    
    private Statek statekPodniesiony;
    
    private byte[][] plansza;
    private Statek[] statki;
    private Panel panel;
    
    public Gracz(Panel p){
        
        panel = p;
        plansza = new byte[22][14];
        for(int i = 11; i < 22; i++){
            for(int j = 0; j < 14; j++){
                plansza[i][j] = Statek.LAD;
            }
        }
        statki = new Statek[17]; // na poczatku jest null;
    }
    
    public boolean strzal(Point p){
        
        if(plansza[p.x][p.y]==Statek.CHYBIENIE || plansza[p.x][p.y]==Statek.TRAFIENIE )
            return false;
        for(int i = 0; i < statki.length; i++){
            
            if(Statek.strzal(statki[i], p)){
                plansza[p.x][p.y] = Statek.TRAFIENIE;
                return true;
            }
        }
        plansza[p.x][p.y] = Statek.CHYBIENIE;
        return true;
    }
    
    public int liczStatki(RodzajStatku rodzaj){ // liczy ile statków danego typu pozostało do zestrzelenia
        
        int licznik = 0;
        for(int i = 0 ; i <statki.length ; i++){
            
            switch(rodzaj){
                case Samolot : {
                    if(statki[i] instanceof Samolot && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case DwuelementowyLadowy : {
                    if(statki[i] instanceof DwuelementowyLadowy && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case TrojelementowyLadowy : {
                    if(statki[i] instanceof TrojelementowyLadowy && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case CzteroelementowyLadowy : {
                    if(statki[i] instanceof CzteroelementowyLadowy && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case JednomasztowiecWodny : {
                    if(statki[i] instanceof JednomasztowiecWodny && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case DwumasztowiecWodny : {
                    if(statki[i] instanceof DwumasztowiecWodny && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case TrojMasztowiecWodny : {
                    if(statki[i] instanceof TrojmasztowiecWodny && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
                case CzteromasztowiecWodny : {
                    if(statki[i] instanceof CzteromasztowiecWodny && Statek.czyZyje(statki[i], plansza))
                        licznik++;
                    break;
                }
            }     
        }
        return licznik;
    }
    
    public boolean czySaStatki(){ // do zakończenia gry
        
        for(int i = 0; i < statki.length; i++){
            if(Statek.czyZyje(statki[i], plansza))return true;
        }
        return false; // nie znaleźliśmy statku konczymy gre
    }
    
    public boolean podniesStatek(RodzajStatku rodzaj){ // metoda podniosi statek i wpisuje go do chwytaka
        
        
        switch(rodzaj){
            case Samolot : {
                if(liczStatki(rodzaj)<1){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case DwuelementowyLadowy : {
                if(liczStatki(rodzaj)<3){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case TrojelementowyLadowy : {
                if(liczStatki(rodzaj)<2){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case CzteroelementowyLadowy : {
                if(liczStatki(rodzaj)<1){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case JednomasztowiecWodny : {
                if(liczStatki(rodzaj)<4){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case DwumasztowiecWodny : {
                if(liczStatki(rodzaj)<3){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case TrojMasztowiecWodny : {
                if(liczStatki(rodzaj)<2){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            case CzteromasztowiecWodny : {
                if(liczStatki(rodzaj)<1){
                    statekPodniesiony = FabrykaStatkow.GenerujStatek(rodzaj);
                    return true;
                }
                else
                    return false;
            }
            default: return false;
                
        }
    }
    public boolean czyWszystkieDodane(){
        
        for(int i = 0 ; i <statki.length ;i++){
            
            if(statki[i] == null)
                return false;
        }
        return true; 
    }

    @Override
    public void rysuj(Graphics g) {
        
        for(Statek s : statki){
            if(s==null)continue;
            Point[] tablica_statku = s.zwrocPunkty();
            if(s instanceof DwuelementowyLadowy || s instanceof TrojelementowyLadowy || s instanceof CzteroelementowyLadowy)
                g.setColor(Color.black);
            else g.setColor(new Color(115,111,111));
            for(Point p: tablica_statku){
                g.fillOval(20*p.x, 20*p.y, 20, 20);
            }
            
        }
        
        if(statekPodniesiony != null){
            Point[] tablica_statku = statekPodniesiony.zwrocPunkty();
            if(statekPodniesiony instanceof DwuelementowyLadowy || statekPodniesiony instanceof TrojelementowyLadowy || statekPodniesiony instanceof CzteroelementowyLadowy)
                g.setColor(Color.black);
            else g.setColor(new Color(115,111,111));
            for(Point p: tablica_statku){
                g.fillOval(20*p.x, 20*p.y, 20, 20);
            }
            g.setColor(Color.red);
            for(Point p: tablica_statku){
                g.drawOval(20*p.x, 20*p.y, 20, 20);
            }
        }
        for(int i = 0 ; i < plansza.length; i++){ // rysowanie strzalow
            for(int j = 0 ; j < plansza[i].length; j++){
                if(plansza[i][j] == Statek.TRAFIENIE){
                    g.setColor(Color.red);
                    g.fillOval(i*20 + 5, j*20 + 5 ,10 , 10);
                }
                else if(plansza[i][j] == Statek.CHYBIENIE){
                    g.setColor(Color.blue);
                    g.fillOval(i*20 + 5, j*20 + 5 ,10 , 10);
                }
            }
        }
   
    }
    
    public void przesunStatekPodniesiony(int x, int y){
        
        if(statekPodniesiony!=null)
            statekPodniesiony.przesunecie(x, y);
    }
    
    public void obrocStatekPodniesiony(){
        
        if(statekPodniesiony!=null)
            statekPodniesiony.rotacja();
    }
    
    public void upuscStatekPodniesiony(){
        
        if(statekPodniesiony == null) return;
        if(Statek.sprawdzPodloze(statekPodniesiony, plansza)){
            boolean kolizja = false;
            for(int i = 0 ; i < statki.length ; i++){
                if(statki[i] != null){
                    if(Statek.kolizjaStatkow(statekPodniesiony, statki[i])){
                        kolizja = true;
                        break;
                    }
                }
            }
            if(!kolizja){
                for(int i = 0 ; i < statki.length; i++){
                    if(statki[i] == null ){
                        statki[i] = statekPodniesiony;
                        statekPodniesiony = null;
                        break;
                    }
                }
            }
        }
        
                
    }
    
    public int ileDoDodania(RodzajStatku rodzaj){ // metoda podniosi statek i wpisuje go do chwytaka
        
        
        switch(rodzaj){
            case Samolot : {
                return 1 - liczStatki(rodzaj);
            }
            case DwuelementowyLadowy : {
                return 3 - liczStatki(rodzaj);
            }
            case TrojelementowyLadowy : {
                return 2 - liczStatki(rodzaj);
            }
            case CzteroelementowyLadowy : {
                return 1 - liczStatki(rodzaj);
            }
            case JednomasztowiecWodny : {
                return 4 - liczStatki(rodzaj);
            }
            case DwumasztowiecWodny : {
                return 3 - liczStatki(rodzaj);
            }
            case TrojMasztowiecWodny : {
                return 2 - liczStatki(rodzaj);
            }
            case CzteromasztowiecWodny : {
                return 1 - liczStatki(rodzaj);
            }
            default: return 0;
                
        }
    }
    
    public void losujStatki(){
        
        statki = new Statek[17];
        Statek.rozmiescStatki(statki, plansza);
    }
    
    public void zapisDoPliku(File plik){
        
        if(czyWszystkieDodane()){
            try{
                ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream(plik));
                for(int i = 0; i < statki.length; i++){
                    zapis.writeObject(statki[i]);
                }
                zapis.writeObject(plansza);
                zapis.close();
            }
            catch(Exception e){

            }
        }
    }
    
    public void odczytZPliku(File plik){
        
        statki = new Statek[17];
        try{
            ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream(plik));
            for(int i = 0 ; i < statki.length; i++){
                statki[i] = (Statek)odczyt.readObject();
            }
            plansza = (byte[][])odczyt.readObject();
            odczyt.close();
        }
        catch(Exception e){
            
        }
    }
}
