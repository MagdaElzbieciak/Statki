/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Danuta
 */
public class Panel extends JPanel {
    
    private AdapterRysujacy adapter;
    
    public Panel(int y){
        
        setBounds(10, y, 440, 280);
        setBackground(Color.green);
    }
    
    public void dodajAdapterRysujacy(AdapterRysujacy a){
        
        adapter = a;
        
    }
    
    public void paintComponent(Graphics g){
        
        g.setColor(new Color(0, 202, 202));
        g.fillRect(0, 0, 220, 280);
        g.setColor(new Color(26, 134, 58));
        g.fillRect(220, 0, 220, 280);
        g.setColor(Color.black);
        for(int i = 0; i < 13; i++){
            g.drawLine(0, 20+i*20, 440, 20+i*20);
        }
         for(int i = 0; i < 21; i++){
            g.drawLine(20+i*20, 0, 20+i*20, 280);
        }
        if(adapter!=null)
            adapter.rysuj(g);
    }
    
}
