package br.com.totvs.controller;

import br.com.totvs.model.ProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class ProdutoController {

    private final Connection conn;
    private PreparedStatement ps;
    private double volumeTotal;

    public ProdutoController() {
        conn = ConnectionController.connection();
    }

    public boolean insert(ProdutoModel get) {
        try {
            ps = conn.prepareStatement(
                    "insert into produto(item, caixa, volume, volume_total) values(?,?,?,?)"
            );
            ps.setString(1, get.getItem());
            ps.setInt(2, get.getCaixa());
            ps.setDouble(3, get.getVolume());
            double volumeTotal = get.getCaixa() * get.getVolume();
            if (get.getVolume() <= 50) {
                ps.setDouble(4, volumeTotal);
                ps.execute();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Volume Cúbico não suportado para Transporte:\n"
                        + "Adéque para que por Caixa, seja no máximo de 50 Métros Cúbicos");
                return false;
            }

        } catch (com.mysql.jdbc.MysqlDataTruncation error) {
            JOptionPane.showMessageDialog(null, "Extrapolação no valor permetido para Banco de Dados:\n"
                    + "Nº de Caixas.\nPor favor, Refaça o Item com quantidade menor...");
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CRIAR PRODUTO...\n" + e);
            return false;
        }
    }

    public boolean update(ProdutoModel get, int idItem) {
        try {
            ps = conn.prepareStatement(
                    "update produto set item=? where id=?;"
            );
            ps.setString(1, get.getItem());
            ps.setInt(2, idItem);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO NA ATUALIZAÇÃO DO PRODUTO...\n" + e);
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            ps = conn.prepareStatement("delete from produto");
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR PRODUTOS...");
            return false;
        }
    }

    public boolean deleteOne(int idItem) {
        try {
            ps = conn.prepareStatement("delete from produto where id=?");
            ps.setInt(1, idItem);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR PRODUTOS...");
            return false;
        }
    }

    // IMPORTANTE: Método não usado, apenas para consulta didática.
    public ArrayList<ProdutoModel> selectAll() {
        ArrayList<ProdutoModel> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement("select item, caixa, volume from produto"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoModel s = new ProdutoModel( //String item, int caixa, double volume
                        rs.getString(1), rs.getInt(2), rs.getDouble(3)
                );
                list.add(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO LISTAR PRODUTO...\n" + e);
        }
        return list;
    }

}

// Método abaixo para Consulta didática:
//	public Connection connect(){ //
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/totvs","root",""
//					);
//			return conn;
//		} catch (ClassNotFoundException | SQLException e) {
//			JOptionPane.showMessageDialog(null, "ERRO NA CONECÇÃO...\n"+e);
//			return null;
//		}
//	}
