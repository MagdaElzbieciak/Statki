/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statki;

/**
 *
 * @author Danuta
 */
public class FabrykaStatkow {
    
    public static Statek GenerujStatek(RodzajStatku rodzaj){
        
        switch(rodzaj){
            case Samolot : return new Samolot();
            case DwuelementowyLadowy : return new DwuelementowyLadowy();
            case TrojelementowyLadowy : return new TrojelementowyLadowy();
            case CzteroelementowyLadowy : return new CzteroelementowyLadowy();
            case JednomasztowiecWodny : return new JednomasztowiecWodny();
            case DwumasztowiecWodny : return new DwumasztowiecWodny();
            case TrojMasztowiecWodny : return new TrojmasztowiecWodny();
            case CzteromasztowiecWodny : return new CzteromasztowiecWodny();
            default: return null;
                
        }
        
    }
    
}
