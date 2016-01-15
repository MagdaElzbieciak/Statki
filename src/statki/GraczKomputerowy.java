/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraczKomputerowy implements AdapterRysujacy {
    
    private byte[][] plansza;
    private Statek[] statki;
    private Panel panel;
    private List<Point> mozliwe_strzaly = new ArrayList<Point>();
    private Random r  = new Random();
    
    public GraczKomputerowy(Panel p){
        
        panel = p;
        plansza = new byte[22][14];
        for(int i = 11; i < 22; i++){
            for(int j = 0; j < 14; j++){
                plansza[i][j] = Statek.LAD;
            }
        }
        statki = new Statek[17]; // inicjumemy tab statków
        Statek.rozmiescStatki(statki, plansza);
        for(int i = 0 ; i < 22 ; i++){
            for(int j = 0 ; j < 14 ; j++){
                mozliwe_strzaly.add(new Point(i,j));
            }
        }
    }
    
    public Point generujPunktStrzalu(){
        
        return mozliwe_strzaly.remove( r.nextInt(mozliwe_strzaly.size()) );
       
    }
    
    public boolean strzal(Point p){
        
        if(plansza[p.x][p.y] == Statek.CHYBIENIE || plansza[p.x][p.y] == Statek.TRAFIENIE)
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
    
    public boolean czySaStatki(){
        
        for(int i = 0; i < statki.length; i++){
            if(Statek.czyZyje(statki[i], plansza))return true;
        }
        return false; // nie znaleźliśmy statku konczymy gre
    }
    
    @Override
    public void rysuj(Graphics g) {
    
        for(int i = 0 ; i < statki.length; i++){
            if(!Statek.czyZyje(statki[i], plansza)){
                if(statki[i] == null)continue;
                Point[] tablica_statku = statki[i].zwrocPunkty();
                if(statki[i] instanceof DwuelementowyLadowy || statki[i] instanceof TrojelementowyLadowy || statki[i] instanceof CzteroelementowyLadowy)
                    g.setColor(Color.black);
                else g.setColor(new Color(115,111,111));
                for(Point p: tablica_statku){
                    g.fillOval(20*p.x, 20*p.y, 20, 20);
                }
            }
        }
        for(int i = 0 ; i < plansza.length; i++){
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
    
}
