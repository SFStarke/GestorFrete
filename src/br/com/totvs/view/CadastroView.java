package br.com.totvs.view;

import br.com.totvs.controller.ProdutoController;
import br.com.totvs.model.ProdutoModel;
import java.sql.*;
import br.com.totvs.controller.ConnectionController;
import br.com.totvs.controller.FreteController;
import br.com.totvs.model.FreteModel;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class CadastroView extends javax.swing.JFrame {

    ConnectionController connControl = new ConnectionController();
    Connection connected = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ProdutoController connProduto = new ProdutoController();
    FreteController connFrete = new FreteController();

    ProdutoModel produtoModel = new ProdutoModel();
    ArrayList<ProdutoModel> arrayListProduto = new ArrayList<>();
    FreteModel freteModel = new FreteModel();
    ArrayList<FreteModel> arrayLisFrete = new ArrayList<>();

    DefaultTableModel defaultTable = new DefaultTableModel();
    DefaultListModel defaultList = new DefaultListModel();

    ArrayList<String> arrayHistorico = new ArrayList<>();
    double absoluto = 0.0f;

    public CadastroView() {
        initComponents();
        setLocationRelativeTo(null);
        connected = connControl.connForView();
        defaultTable = (DefaultTableModel) Table.getModel();
        btnUpdate.setEnabled(false);
        btnDeleteOne.setEnabled(false);
        selectAll(); // Envia conteudo de produto em BD para JTable "Table"
        listaHistorico(); // Envia conteudo de BD frete para JList "listaHistorico"
    }

    public void CleanField() {
        txtItem.setText(null);
        txtQuantidadeCaixa.setText(null);
        txtAltura.setText(null);
        txtLargura.setText(null);
        txtProfundidade.setText(null);
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDeleteOne.setEnabled(false);
        txtAltura.setEnabled(true);
        txtLargura.setEnabled(true);
        txtProfundidade.setEnabled(true);
        lblVolumeAbsoluto.setText("Informações adicionais do Frete:");
    }

    public void selectAll() { // Envia conteudo de produto em BD para JTable "Table"

        try {
            ps = connected.prepareStatement(
                    "select id, item, caixa, volume, volume_total from produto"
            );
            rs = ps.executeQuery();

            ((DefaultTableModel) Table.getModel()).setRowCount(0);//Limpa jTable
            while (rs.next()) {

                defaultTable.insertRow(defaultTable.getRowCount(), new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                });

                ProdutoModel p = new ProdutoModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                arrayListProduto.add(p);

                absoluto += Double.parseDouble(rs.getString(5));
                double numeroCaminhao = Math.ceil(absoluto / 50);
                lblVolumeAbsoluto.setText("<html><Centre>O Volume Absoluto da lista é de: "
                        + new DecimalFormat(".###").format(absoluto) + " m³. Irá requerer "
                        + new DecimalFormat("").format(numeroCaminhao) + " caminhão(ões) para o(s) frete(s)</html>");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO LISTAR PRODUTO...\n" + e);
        }
    }

    private void listaHistorico() { // Envia conteudo de BD frete para JList "listaHistorico"
        try {
            ps = ps = connected.prepareStatement("select*from frete");
            rs = ps.executeQuery();
            arrayHistorico.removeAll(arrayHistorico);//Impede duplicação de conteudo em ArrayList.

            while (rs.next()) {
                String numeroFrete = rs.getString(1);
                String descriptionProduct = rs.getString(2);
                arrayHistorico.add("Frete Nº: [ " + numeroFrete + " ] \n" + descriptionProduct);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO LISTA HISTÓRICO DE FRETE>\n" + ex);
        }
        defaultList.removeAllElements(); // Impede Duplicação em JList
        arrayHistorico.forEach((listHist) -> {
            defaultList.addElement(listHist);
        });
    }

    private void setTxtFields() {//Preenchimento dos campos txt. pela seleção na jTable
        int spot = Table.getSelectedRow();//Estabelece posição"valor" do cliente selecionado.
        txtId.setText(Table.getModel().getValueAt(spot, 0).toString());
        txtItem.setText(Table.getModel().getValueAt(spot, 1).toString());
        txtQuantidadeCaixa.setText(Table.getModel().getValueAt(spot, 2).toString());

        btnInsert.setEnabled(false);
        txtAltura.setEnabled(false);
        txtLargura.setEnabled(false);
        txtProfundidade.setEnabled(false);

        btnUpdate.setEnabled(true);
        btnDeleteOne.setEnabled(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        txtQuantidadeCaixa = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLargura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProfundidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lblVolumeAbsoluto = new javax.swing.JLabel();
        btnFrete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaHistorico = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();
        btnDeletHistorico = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtId = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDeleteOne = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("* Item:");

        jLabel2.setText("* Quantidade de Caixas:");

        jLabel3.setText("* Altura");

        jLabel4.setText("* Largura");

        jLabel5.setText("* Profundidade");

        Table = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Item", "Quantidade de Caixa", "Volume por Caixa", "Volume Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jLabel6.setText("cm");

        jLabel7.setText("cm");

        jLabel8.setText("cm");

        btnInsert.setText("Salvar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("Gestor de Frete");

        btnDelete.setText("Limpar Lista");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblVolumeAbsoluto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolumeAbsoluto.setText("Informações adicionais do Frete:");
        lblVolumeAbsoluto.setToolTipText("");

        btnFrete.setText("<html><Center>Despachar para Frete</html>");
        btnFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFreteActionPerformed(evt);
            }
        });

        listaHistorico.setModel(defaultList);
        jScrollPane2.setViewportView(listaHistorico);

        jLabel10.setText("Histórico de Fretes Realizados");

        btnDeletHistorico.setText("Limpar Histórico de Fretes");
        btnDeletHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletHistoricoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText(" * Campo Obrigatório para Preenchimento");

        txtId.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        txtId.setText("ID");
        txtId.setEnabled(false);

        btnUpdate.setText("Atualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDeleteOne.setText("Excluir");
        btnDeleteOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteOneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeletHistorico)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(150, 150, 150)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete)
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtQuantidadeCaixa))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3))
                                                .addGap(37, 37, 37))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtProfundidade)
                                            .addComponent(txtAltura, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLargura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDeleteOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnUpdate)))))
                                .addGap(13, 91, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblVolumeAbsoluto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblVolumeAbsoluto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtQuantidadeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(btnInsert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(btnUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProfundidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(btnDeleteOne))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeletHistorico)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed

        if ((txtItem.getText().equals("")) || (txtQuantidadeCaixa.getText().equals(""))
                || (txtAltura.getText().equals("")) || (txtLargura.getText().equals("")) || (txtProfundidade.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos...");
        } else {
            double altura = Double.parseDouble(txtAltura.getText());
            double largura = Double.parseDouble(txtLargura.getText());
            double profundidade = Double.parseDouble(txtProfundidade.getText());

            produtoModel.setItem(txtItem.getText());
            produtoModel.setCaixa(Integer.parseInt(txtQuantidadeCaixa.getText()));
            produtoModel.setVolume((altura * largura * profundidade) / 1000000);

            connProduto.insert(produtoModel);
            absoluto = 0.0f;
            CleanField();
            selectAll(); // Envia conteudo de produto em BD para JTable "Table"
        }

    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int atencao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir toda a lista permanentemente?",
                "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (atencao == JOptionPane.YES_OPTION) {
            connProduto.deleteAll();
        }
        CleanField();
        selectAll(); // Envia conteudo de produto em BD para JTable "Table"
        absoluto = 0.0f;
        lblVolumeAbsoluto.setText("Volume Total para Frete:");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFreteActionPerformed

        String addFrete = "", addFretes = "";
        ArrayList<String> ordenarFrete = new ArrayList();

        try {
            ps = connected.prepareStatement(
                    "select id, item, caixa, volume, volume_total from produto"
            );
            rs = ps.executeQuery();

            if (absoluto <= 50) { // Conteudo do BD Frete, para destinar elementos corretamente ao histórico.
                while (rs.next()) {
                    addFrete += "=> Lote nº [" + rs.getString(1) + "] ("+ rs.getString(3) +") Caixa(s) de "+ rs.getString(2) + ". " + rs.getString(4) + "m³ por caixa ||";
                }
                freteModel.setProdutos(addFrete);
                connFrete.insert(freteModel);

            } else {
                while (rs.next()) {
                    double nFretes = rs.getDouble(5) / 50;
                    double nCaixas = rs.getInt(3);
                    double vTot = rs.getDouble(5);
                    double capacidadeFrete = 00.0f;

                    if (vTot <= 50) {
                        addFrete += "=> Lote nº [" + rs.getString(1) + "] ("+ rs.getString(3) +") Caixa(s) de "+ rs.getString(2) + ". " + rs.getString(4) + "m³ por caixa ||";
                        freteModel.setProdutos(addFrete);
                    }
                    
                    if (vTot > 50 && nCaixas > 1) {
                         capacidadeFrete = 50.0f;

                        for (int i = 1; i <= Math.ceil(nFretes); i++) { //Repete nº de fretes necessários
                            
                            if (capacidadeFrete > rs.getDouble(4)) { 
                                addFretes = "=> Lote nº [" + rs.getString(1) + "] ("+ rs.getString(3) +") Caixa(s) de "+ rs.getString(2) + ". " + rs.getString(4) + "m³ por caixa ||";
                                capacidadeFrete -= rs.getDouble(4);
                            } 
                             freteModel.setProdutos(addFretes);
                                connFrete.insert(freteModel);
                        }
                    }
                }
                connFrete.insert(freteModel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO LISTAR FRETE...\n" + e);
        }
        // arrayListFrete.forEach((listFrete) ->{System.out.println("Conteudo Collection <||> "+listFrete);} ); // teste imprime no console
        connProduto.deleteAll();
        absoluto = 0.0f;
        listaHistorico(); // Envia conteudo de BD frete para JList "listaHistorico"
        CleanField();
        selectAll(); // Envia conteudo de produto em BD para JTable "Table"
    }//GEN-LAST:event_btnFreteActionPerformed

    private void btnDeletHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletHistoricoActionPerformed

        int atencao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir permanentemente o histórico de frete ?",
                "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (atencao == JOptionPane.YES_OPTION) {
            connFrete.deleteAll();
        }
        listaHistorico(); // Envia conteudo de BD frete para JList "listaHistorico"
        CleanField();

    }//GEN-LAST:event_btnDeletHistoricoActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        setTxtFields();
    }//GEN-LAST:event_TableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String item = txtItem.getText();
        int caixa = Integer.parseInt(txtQuantidadeCaixa.getText());
        int id = Integer.parseInt(txtId.getText());
        produtoModel.setItem(item);
        produtoModel.setCaixa(caixa);

        connProduto.update(produtoModel, id);

        CleanField();
        selectAll(); // Envia conteudo de produto em BD para JTable "Table"

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOneActionPerformed

        int atencao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir permanentemente Lote [" + txtId.getText() + "] ?",
                "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (atencao == JOptionPane.YES_OPTION) {
            connProduto.deleteOne(Integer.parseInt(txtId.getText()));
        }

        CleanField();
        selectAll(); // Envia conteudo de produto em BD para JTable "Table"
    }//GEN-LAST:event_btnDeleteOneActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton btnDeletHistorico;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteOne;
    private javax.swing.JButton btnFrete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblVolumeAbsoluto;
    private javax.swing.JList<String> listaHistorico;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtLargura;
    private javax.swing.JTextField txtProfundidade;
    private javax.swing.JTextField txtQuantidadeCaixa;
    // End of variables declaration//GEN-END:variables
}
