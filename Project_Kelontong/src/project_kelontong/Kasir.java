/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_kelontong;

import database.koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.AbstractMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

//import project_kelontong.report1.jrxml;
/**
 *
 * @author PY7
 */
public class Kasir extends javax.swing.JFrame {

    String Tanggal;
    private DefaultTableModel model;
    private Connection c = new koneksi().connect();
    private String sql;
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txTotalBayar.setText(String.valueOf(totalBiaya));
        txTampil.setText("Rp "+ totalBiaya +",00");
    }
    
    private void autonumber(){
        try {
            
            Statement s = c.createStatement();
            sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_transaksi").substring(3);
                String TR = "" +(Integer.parseInt(NoFaktur )+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txNoTransaksi.setText("TRX" + Nol + TR);
            } else {
                txNoTransaksi.setText("TRX0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void loadData(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{
            txNoTransaksi.getText(),
            txIDBarang.getText(),
            txNamaBarang.getText(),
            txJumlah.getText(),
            txHarga.getText(),
            txTotalBayar.getText()
        });
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txNoTransaksi.setText("");
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        txJumlah.setText("");
        autonumber();
    }
    
    public void clear(){
        
        txTotalBayar.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
        txTampil.setText("0");
    }
    
    public void clear2(){
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        txJumlah.setText("");
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.parseInt(txJumlah.getText());
        harga = Integer.parseInt(txHarga.getText());
        total = jumlah * harga;
        
        txTotalBayar.setText(String.valueOf(total));
        
        loadData();
        totalBiaya();
        clear2();
        txIDBarang.requestFocus();
    }
    
    public Kasir() {
        initComponents();
        
        //Create Table
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("No Transaksi");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        txTanggal.setText(s.format(date));
        txTotalBayar.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txNoTransaksi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txTanggal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txIDBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txNamaBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txJumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        txTampil = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txTotalBayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txBayar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txKembalian = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("PENJUALAN");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_kelontong/png/icons8_Login_35px.png"))); // NOI18N
        jButton1.setText("Kembali");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(325, 325, 325))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, -1));

        jLabel2.setText("No Transaksi");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 104, -1, -1));

        txNoTransaksi.setEnabled(false);
        getContentPane().add(txNoTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 101, 160, -1));

        jLabel5.setText("Tanggal");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 104, -1, -1));

        txTanggal.setEnabled(false);
        getContentPane().add(txTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 101, 160, -1));

        jLabel6.setText("ID Barang");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 223, -1, -1));
        getContentPane().add(txIDBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 245, 94, -1));

        jLabel7.setText("Nama Barang");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 223, -1, -1));
        getContentPane().add(txNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 245, 162, -1));

        jLabel8.setText("Harga");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 223, -1, -1));
        getContentPane().add(txHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 245, 160, -1));

        jLabel9.setText("Jumlah");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 223, -1, -1));

        txJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 245, 160, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 280, 668, 236));

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, -1, 50));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 336, 73, 50));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 534, 73, 50));

        txTampil.setBackground(new java.awt.Color(255, 153, 153));
        txTampil.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txTampil.setText("Rp. 0");
        getContentPane().add(txTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 534, 304, 50));

        jLabel10.setText("Total Bayar");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 527, -1, -1));

        txTotalBayar.setEnabled(false);
        getContentPane().add(txTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 524, 160, -1));

        jLabel11.setText("Bayar");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 558, -1, -1));

        txBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBayarActionPerformed(evt);
            }
        });
        getContentPane().add(txBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 555, 160, -1));

        jLabel12.setText("Kembalian");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 592, -1, -1));

        txKembalian.setEnabled(false);
        getContentPane().add(txKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 589, 160, -1));

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        getContentPane().add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 245, 66, -1));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 780, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJumlahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_txJumlahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txBayar.setText("0");
        txKembalian.setText("0");
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;
        
        total = Integer.parseInt(txTotalBayar.getText());
        bayar = Integer.parseInt(txBayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            JOptionPane.showMessageDialog(null, "Terima Kasih \n Kembalian anda : " + kembalian + "Rp");
            txKembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txBayarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String noTransaksi = txNoTransaksi.getText();
        String tanggal = txTanggal.getText();
        String total = txTotalBayar.getText();
        String bayar = txBayar.getText();
        String kembali = txKembalian.getText();
        
        try {
            
            sql = "INSERT INTO transaksi (id_transaksi, waktu, total, bayar, kembali)VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, noTransaksi);
            p.setString(2, tanggal);
            p.setString(3, total);
            p.setString(4, bayar);
            p.setString(5, kembali);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            //System.out.println("simpan penjualan error");
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan database: " + e.getMessage());
        }
        
        try {
            
            int baris = jTable1.getRowCount();
            sql = "INSERT INTO detail_transaksi(id_transaksi, id_barang, jumlah, subtotal) VALUES(?,?,?,?)";
            PreparedStatement p = c.prepareStatement(sql);
            for (int i = 0; i < baris; i++) {
                p.setString(1, String.valueOf(jTable1.getValueAt(i, 0)));
                p.setString(2, String.valueOf(jTable1.getValueAt(i, 1)));
                p.setString(3, String.valueOf(jTable1.getValueAt(i, 3)));
                p.setString(4, String.valueOf(jTable1.getValueAt(i, 5)));
                
                p.addBatch();
                
            }
            p.executeBatch();
            p.close();
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error");
        }
        
        try {  
                   
            String report = ("/media/juragan/Kuliah/Semester 4/Basdat 2/UAS/Project_Kelontong/src/project_kelontong/report2.jrxml");
            HashMap hash = new HashMap();
            hash.put("kode",txNoTransaksi.getText());
            JasperReport Jrpt = JasperCompileManager.compileReport(report);
            JasperPrint Jprint = JasperFillManager.fillReport(Jrpt, hash, c);
            JasperViewer.viewReport(Jprint, false);
        } catch (Exception rptexcpt) {
            System.out.println("Ga Bisa : "+rptexcpt);
        }
           
        clear();
        utama();
        autonumber();
        kosong();
        txTampil.setText("Rp. 0");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        ListBarang a = new ListBarang();
        a.setVisible(true);
    }//GEN-LAST:event_btnCariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        karyawan_poenya kp = new karyawan_poenya();
        kp.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txBayar;
    public static javax.swing.JTextField txHarga;
    public static javax.swing.JTextField txIDBarang;
    public static javax.swing.JTextField txJumlah;
    private javax.swing.JTextField txKembalian;
    public static javax.swing.JTextField txNamaBarang;
    private javax.swing.JTextField txNoTransaksi;
    private javax.swing.JTextField txTampil;
    private javax.swing.JTextField txTanggal;
    private javax.swing.JTextField txTotalBayar;
    // End of variables declaration//GEN-END:variables
}
