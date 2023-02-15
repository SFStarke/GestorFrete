package br.com.totvs.controller;

import br.com.totvs.model.FreteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class FreteController {

    private final Connection conn;
    private PreparedStatement ps;

    public FreteController() {
        conn = ConnectionController.connection();
    }

    public boolean insert(FreteModel frete) {

        try {
            ps = conn.prepareStatement(
                    "insert into frete (produtos) value (?)"
            );
            ps.setString(1, frete.getProdutos());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CRIAR FRETE...\n" + ex);
            System.out.println(ex);
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            ps = conn.prepareStatement("delete from frete");
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR FRETE...");
            return false;
        }
    }
}
