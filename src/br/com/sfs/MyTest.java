package br.com.sfs;

import br.com.totvs.controller.ConnectionController;
import br.com.totvs.controller.ProdutoController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class MyTest {
    /*       C L A S S E  M A I N   P A R A   T E S T A R
     *           L Ó G I C A   D E   C Ó D I G O S            */
    public static void main(String args[]) {
        

        
        
//    ConnectionController connControl = new ConnectionController();
//    Connection connected = null;
//    PreparedStatement ps = null;
        ProdutoController pc = new ProdutoController();
//        ArrayList <ProdutoController> arrayPc = new ArrayList();
        
        System.out.println("do PC: "+pc.selectAll());
        
        ArrayList<String> temporario = new ArrayList();
        temporario.add("Sérgio");
        temporario.add("Starke");
        
        do{
            temporario.forEach(res -> System.out.print(res+" "));
            temporario.remove(0);
        } while (temporario.size() > 1);
      //  temporario.remove(0);
    }
}
