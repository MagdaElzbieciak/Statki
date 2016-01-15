/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main extends JFrame implements ActionListener, KeyListener, ItemListener, MouseListener { //ItemListener - KomboBox , ActionListener - Button 

    private Panel panel_gracza;
    private Panel panel_przeciwnika;
    private boolean nowa_gra = false;
    private boolean start = false;
    private GraczKomputerowy gracz_komputerowy;
    private Gracz gracz;
    private JTextArea statki_gracza;
    private JTextArea statki_gracza_komputerowego;
    private JLabel wiadomosc;
    
    public Main(){
    
        super("Statki");
        setBounds(10, 10, 650, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(this);
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        panel_gracza = new Panel(10);
        panel_przeciwnika = new Panel(360);
        add(panel_gracza);
        add(panel_przeciwnika);
        
        JButton b = new JButton("Nowa gra");
        b.setBounds(10, 300, 100, 30);
        b.addActionListener(this);
        b.setActionCommand("Nowa gra");
        add(b);
        
        b = new JButton("Start");
        b.setBounds(120, 300, 100, 30);
        b.addActionListener(this);
        b.setActionCommand("Start");
        add(b);
        
        b = new JButton("Generuj mapę");
        b.setBounds(230, 300, 120, 30);
        b.addActionListener(this);
        b.setActionCommand("Generuj mape");
        add(b);
        
        b = new JButton("Zapis mapy");
        b.setBounds(360, 300, 100, 30);
        b.addActionListener(this);
        b.setActionCommand("Zapis mapy");
        add(b);
        
        b = new JButton("Odczyt mapy");
        b.setBounds(470, 300, 120, 30);
        b.addActionListener(this);
        b.setActionCommand("Odczyt mapy");
        add(b);
        
        JLabel lable = new JLabel("Wybór statku");
        lable.setBounds(460, 10, 155, 20);
        add(lable);
        
        String[] nazwy_statkow = {"","samolot", "dwuelementowy lądowy", "trójelementowy lądowy", "czteroelementowy lądowy", "jednomasztowiec wodny", "dwumasztowiec wodny", "trójmasztowiec wodny", "czteromasztowiec wodny" };
        JComboBox wybor = new JComboBox(nazwy_statkow);
        wybor.setBounds(460, 30, 155, 30);
        wybor.addItemListener(this);
        add(wybor);
        
        lable = new JLabel();
        lable.setBounds(460, 70, 60, 215);
        try {
            lable.setIcon(new ImageIcon(ImageIO.read(new File("statki.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        add(lable);
        
        statki_gracza = new JTextArea(); 
        statki_gracza.setBounds(530, 72, 60, 215);
        statki_gracza.setBorder(null);
        statki_gracza.setFont(new Font("Arial", Font.BOLD, 21));
        statki_gracza.setBackground(new Color(238,238,238));
        add(statki_gracza);
        statki_gracza.setText("");
        
        lable = new JLabel();
        lable.setBounds(460, 420, 60, 215);
        try {
            lable.setIcon(new ImageIcon(ImageIO.read(new File("statki.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        add(lable);
        
        statki_gracza_komputerowego = new JTextArea();
        statki_gracza_komputerowego.setBounds(530, 422, 60, 215);
        statki_gracza_komputerowego.setBorder(null);
        statki_gracza_komputerowego.setFont(new Font("Arial", Font.BOLD, 21));
        statki_gracza_komputerowego.setBackground(new Color(238,238,238));
        add(statki_gracza_komputerowego);
        statki_gracza_komputerowego.setText("");
        
        wiadomosc = new JLabel();
        wiadomosc.setFont(new Font("Arial", Font.BOLD, 18));
        wiadomosc.setBounds(215, 330, 150, 30);
        add(wiadomosc);
        
        
        setVisible(true);
        requestFocus();
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public void uaktulanijIloscStatkow(){
        if(nowa_gra && !start){
            statki_gracza.setText("");
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.CzteroelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.TrojelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.DwuelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.CzteromasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.TrojMasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.DwumasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.JednomasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.ileDoDodania(RodzajStatku.Samolot) + "");
            statki_gracza_komputerowego.setText("");
        }
        else if(nowa_gra && start){ // ile zostało do zabicia gracza
            statki_gracza.setText("");
            statki_gracza.append( gracz.liczStatki(RodzajStatku.CzteroelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.TrojelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.DwuelementowyLadowy) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.CzteromasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.TrojMasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.DwumasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.JednomasztowiecWodny) + "\n" );
            statki_gracza.append( gracz.liczStatki(RodzajStatku.Samolot) + "");
            
            statki_gracza_komputerowego.setText("");
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.CzteroelementowyLadowy) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.TrojelementowyLadowy) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.DwuelementowyLadowy) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.CzteromasztowiecWodny) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.TrojMasztowiecWodny) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.DwumasztowiecWodny) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.JednomasztowiecWodny) + "\n" );
            statki_gracza_komputerowego.append( gracz_komputerowy.liczStatki(RodzajStatku.Samolot) + "");
        }
    }
    
    public void koniecGry(){
        
        nowa_gra = false;
        start = false;
        if(gracz.czySaStatki()){
            wiadomosc.setForeground(Color.blue);
            wiadomosc.setText("Wygrałeś! :)");//wygrał gracz 
        }
        else{
            wiadomosc.setForeground(Color.red);
            wiadomosc.setText("Przegrałeś! :("); // wygrał gracz komputerowy
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Nowa gra")){
            nowa_gra = true;
            start = false;
            gracz_komputerowy = new GraczKomputerowy(panel_przeciwnika);
            gracz = new Gracz(panel_gracza);
            panel_przeciwnika.dodajAdapterRysujacy(gracz_komputerowy);
            panel_gracza.dodajAdapterRysujacy(gracz);
            uaktulanijIloscStatkow();
            panel_gracza.repaint();
            panel_przeciwnika.repaint();
            wiadomosc.setText("");
        }
        else if(e.getActionCommand().equals("Start")){
            if(nowa_gra){
                if(gracz.czyWszystkieDodane()){
                    start = true;
                    uaktulanijIloscStatkow();
                }
            }
            
        }
        else if(e.getActionCommand().equals("Generuj mape")){
            if(nowa_gra && !start){
                gracz.losujStatki();
                panel_gracza.repaint();
                uaktulanijIloscStatkow();
            }
            
        }
        else if(e.getActionCommand().equals("Zapis mapy")){
            
            if(nowa_gra && !start){
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){

                    File plik = chooser.getSelectedFile();
                    gracz.zapisDoPliku(plik);
                }
            }  
        }
        else if(e.getActionCommand().equals("Odczyt mapy")){
            
            if(nowa_gra && !start){
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                    File plik = chooser.getSelectedFile();
                    gracz.odczytZPliku(plik);
                    panel_gracza.repaint();
                }
            } 
            
            
        }
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("KEY");
        if(nowa_gra && !start){
            switch(e.getKeyCode()){
                case KeyEvent.VK_R:{
                    gracz.obrocStatekPodniesiony();
                    break;
                }
                case KeyEvent.VK_RIGHT:{
                    gracz.przesunStatekPodniesiony(1, 0);
                    break;
                }
                case KeyEvent.VK_LEFT:{
                    gracz.przesunStatekPodniesiony(-1, 0);
                    break;
                }
                case KeyEvent.VK_UP:{
                    gracz.przesunStatekPodniesiony(0, -1);
                    break;
                }
                case KeyEvent.VK_DOWN:{
                    gracz.przesunStatekPodniesiony(0, 1);
                    break;
                }
                case KeyEvent.VK_ENTER:{
                    gracz.upuscStatekPodniesiony();
                    uaktulanijIloscStatkow();
                    break;
                }
            }
            panel_gracza.repaint();
        }
        requestFocus();
    }
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(((JComboBox)e.getSource()).getSelectedIndex() == 0) return;
        
        System.out.println(((JComboBox)e.getSource()).getSelectedIndex());
        if(nowa_gra && !start){
            switch(((JComboBox)e.getSource()).getSelectedIndex()){
                case 1:{
                    gracz.podniesStatek(RodzajStatku.Samolot);
                    break;
                }
                case 2:{
                    gracz.podniesStatek(RodzajStatku.DwuelementowyLadowy);
                    break;
                }
                case 3:{
                    gracz.podniesStatek(RodzajStatku.TrojelementowyLadowy);
                    break;
                }
                case 4:{
                    gracz.podniesStatek(RodzajStatku.CzteroelementowyLadowy);
                    break;
                }
                case 5:{
                    gracz.podniesStatek(RodzajStatku.JednomasztowiecWodny);
                    break;
                }
                case 6:{
                    gracz.podniesStatek(RodzajStatku.DwumasztowiecWodny);
                    break;
                }
                case 7:{
                    gracz.podniesStatek(RodzajStatku.TrojMasztowiecWodny);
                    break;
                }
                case 8:{
                    gracz.podniesStatek(RodzajStatku.CzteromasztowiecWodny);
                    break;
                }
            }
        }
        panel_gracza.repaint();
        ((JComboBox)e.getSource()).setSelectedIndex(0);
        requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //wspolrzedne dobrane eksperymentalnie
        if( e.getX() >= 18 && e.getX() < 458 && e.getY() >= 390 && e.getY() < 670 && nowa_gra && start ){
            if( !gracz_komputerowy.strzal( new Point( ((e.getX()-18)/20),((e.getY()-390)/20)) ) )
                return;
            panel_przeciwnika.repaint();
            uaktulanijIloscStatkow();
            if(!gracz_komputerowy.czySaStatki())
                koniecGry();
            else{
                gracz.strzal( gracz_komputerowy.generujPunktStrzalu() );
                panel_gracza.repaint();
                uaktulanijIloscStatkow();
                if(!gracz.czySaStatki()){
                    koniecGry();
                }
            }
                
        }
        requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
