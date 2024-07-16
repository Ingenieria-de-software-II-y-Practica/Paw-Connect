/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.LoginAndSignUp.src.loginandsignup;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Backend.Controller.Controller;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;
import javax.swing.*;

/**
 *
 * @author audre
 */
public class MenuR extends javax.swing.JFrame {
    public ArrayList<Post> publicaciones;
    public Refugio refugio = new Refugio();
    Post post = new Post();
    /**
     * Creates new form MenuR
     */
    public MenuR(Usuario usuario) {
        this.refugio = (Refugio) usuario;
        this.publicaciones = Controller.getRefugioPosts(refugio.getId());
        initComponents();
    }
    public MenuR() {
        this.publicaciones = Controller.getRefugioPosts(refugio.getId());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SALIR = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        HOME = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BUSCAR = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        Descrip4 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        Img4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        Descrip2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        Img2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        Descrip3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        Img3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel(); //Post
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        Descrip1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Img1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        EligeImg = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        textArea2 = new java.awt.TextArea();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        Imagen = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen3.png"))); // NOI18N
        jPanel1.add(jLabel4, new AbsoluteConstraints(110, 30, 140, 150));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText(this.refugio.getNombre());
        jPanel1.add(jLabel3, new AbsoluteConstraints(150, 180, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen2.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new AbsoluteConstraints(0, 0, 360, 270));

        SALIR.setBackground(new java.awt.Color(247, 247, 247));
        SALIR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                SALIRMouseMoved(evt);
            }
        });
        SALIR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SALIRMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SALIRMouseExited(evt);
            }
        });
        SALIR.setLayout(new AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen6.png"))); // NOI18N
        SALIR.add(jLabel7, new AbsoluteConstraints(20, 30, 50, 50));

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("SALIR");
        SALIR.add(jLabel8, new AbsoluteConstraints(86, 50, 70, 20));

        jPanel1.add(SALIR, new AbsoluteConstraints(0, 490, 360, 110));

        HOME.setBackground(new java.awt.Color(247, 247, 247));
        HOME.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                HOMEMouseMoved(evt);
            }
        });
        HOME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HOMEMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HOMEMouseExited(evt);
            }
        });
        HOME.setLayout(new AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen4.png"))); // NOI18N
        HOME.add(jLabel5, new AbsoluteConstraints(20, 30, -1, 50));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("HOME");
        HOME.add(jLabel6, new AbsoluteConstraints(90, 50, 70, 20));

        jPanel1.add(HOME, new AbsoluteConstraints(0, 270, 360, 110));

        BUSCAR.setBackground(new java.awt.Color(247, 247, 247));
        BUSCAR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BUSCARMouseMoved(evt);
            }
        });
        BUSCAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BUSCARMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BUSCARMouseExited(evt);
            }
        });
        BUSCAR.setLayout(new AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen5.png"))); // NOI18N
        BUSCAR.add(jLabel9, new AbsoluteConstraints(20, 30, -1, 50));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("PUBLICA A UN AMIGO/A");
        BUSCAR.add(jLabel10, new AbsoluteConstraints(86, 50, 230, 20));

        jPanel1.add(BUSCAR, new AbsoluteConstraints(0, 380, 360, 110));

        getContentPane().add(jPanel1, new AbsoluteConstraints(0, 0, -1, 780));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new AbsoluteConstraints(320, 0, 1180, 80));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 153, 153));
        jLabel11.setText("BIENVENIDOS A PAW CONNECT");
        jPanel2.add(jLabel11, new AbsoluteConstraints(230, 40, -1, -1));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Mis publicaciones");
        //---Publicaciones----//
        jPanel2.add(jLabel13, new AbsoluteConstraints(410, 100, -1, -1));

        jPanel19.setBackground(new java.awt.Color(255, 236, 236));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel19.setLayout(new AbsoluteLayout());

        jButton14.setBackground(new java.awt.Color(247, 247, 247));
        jButton14.setForeground(new java.awt.Color(51, 51, 51));
        jButton14.setText("Editar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton14, new AbsoluteConstraints(20, 110, -1, 30));

        jButton15.setBackground(new java.awt.Color(247, 247, 247));
        jButton15.setForeground(new java.awt.Color(51, 51, 51));
        jButton15.setText("Eliminar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPostButton(evt, publicaciones.get(3).getId(), Img4, Descrip4, jPanel21, jPanel20);
            }
        });
        jPanel19.add(jButton15, new AbsoluteConstraints(20, 150, -1, 30));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new AbsoluteLayout());
        Descrip4.setText(publicaciones.get(3).getTitulo()+"\n "+publicaciones.get(3).getDescripcion());
        jPanel20.add(Descrip4, new AbsoluteConstraints(0, 0, 220, 160));

        jPanel19.add(jPanel20, new AbsoluteConstraints(110, 20, 220, 160));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new AbsoluteLayout());
        Img4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/"+publicaciones.get(3).getFoto().getPath())));
        jPanel21.add(Img4, new AbsoluteConstraints(0, 0, 70, 70));

        jPanel19.add(jPanel21, new AbsoluteConstraints(20, 20, 70, 70));

        jPanel2.add(jPanel19, new AbsoluteConstraints(540, 390, 350, 200));

        jPanel13.setBackground(new java.awt.Color(255, 236, 236));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(247, 247, 247));
        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setText("Editar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton10, new AbsoluteConstraints(20, 110, -1, 30));

        jButton11.setBackground(new java.awt.Color(247, 247, 247));
        jButton11.setForeground(new java.awt.Color(51, 51, 51));
        jButton11.setText("Eliminar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPostButton(evt, publicaciones.get(1).getId(), Img2, Descrip2, jPanel15, jPanel14);
            }
        });
        jPanel13.add(jButton11, new AbsoluteConstraints(20, 150, -1, 30));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new AbsoluteLayout());
        Descrip2.setText(publicaciones.get(1).getTitulo()+"\n "+publicaciones.get(1).getDescripcion());
        jPanel14.add(Descrip2, new AbsoluteConstraints(0, 0, 220, 160));

        jPanel13.add(jPanel14, new AbsoluteConstraints(110, 20, 220, 160));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new AbsoluteLayout());
        Img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/"+publicaciones.get(1).getFoto().getPath())));
        jPanel15.add(Img2, new AbsoluteConstraints(0, 0, 70, 70));

        jPanel13.add(jPanel15, new AbsoluteConstraints(20, 20, 70, 70));

        jPanel2.add(jPanel13, new AbsoluteConstraints(540, 170, 350, 200));

        jPanel16.setBackground(new java.awt.Color(255, 236, 236));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel16.setLayout(new AbsoluteLayout());

        jButton12.setBackground(new java.awt.Color(247, 247, 247));
        jButton12.setForeground(new java.awt.Color(51, 51, 51));
        jButton12.setText("Editar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton12, new AbsoluteConstraints(20, 110, -1, 30));

        jButton13.setBackground(new java.awt.Color(247, 247, 247));
        jButton13.setForeground(new java.awt.Color(51, 51, 51));
        jButton13.setText("Eliminar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPostButton(evt, publicaciones.get(2).getId(), Img3, Descrip3, jPanel18, jPanel17);
            }
        });
        jPanel16.add(jButton13, new AbsoluteConstraints(20, 150, -1, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new AbsoluteLayout());
        Descrip3.setText(publicaciones.get(2).getTitulo()+"\n "+publicaciones.get(2).getDescripcion());
        jPanel17.add(Descrip3, new AbsoluteConstraints(0, 0, 220, 160));

        jPanel16.add(jPanel17, new AbsoluteConstraints(110, 20, 220, 160));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new AbsoluteLayout());
        Img3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/"+publicaciones.get(2).getFoto().getPath())));
        jPanel18.add(Img3, new AbsoluteConstraints(0, 0, 70, 70));

        jPanel16.add(jPanel18, new AbsoluteConstraints(20, 20, 70, 70));

        jPanel2.add(jPanel16, new AbsoluteConstraints(120, 390, 350, 200));

        jPanel4.setBackground(new java.awt.Color(255, 236, 236));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(247, 247, 247));
        jButton6.setForeground(new java.awt.Color(51, 51, 51));
        jButton6.setText("Editar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new AbsoluteConstraints(20, 110, -1, 30));

        jButton9.setBackground(new java.awt.Color(247, 247, 247));
        jButton9.setForeground(new java.awt.Color(51, 51, 51));
        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPostButton(evt, publicaciones.get(0).getId(), Img1, Descrip1, jPanel10, jPanel7);
            }
        });
        jPanel4.add(jButton9, new AbsoluteConstraints(20, 150, -1, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new AbsoluteLayout());
        Descrip1.setText(publicaciones.get(0).getTitulo()+"\n "+publicaciones.get(0).getDescripcion());
        jPanel7.add(Descrip1, new AbsoluteConstraints(0, 0, 220, 160));

        jPanel4.add(jPanel7, new AbsoluteConstraints(110, 20, 220, 160));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new AbsoluteLayout());
        Img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/"+publicaciones.get(0).getFoto().getPath())));
        jPanel10.add(Img1, new AbsoluteConstraints(0, 0, 70, 70));


        jPanel4.add(jPanel10, new AbsoluteConstraints(20, 20, 70, 70));

        jPanel2.add(jPanel4, new AbsoluteConstraints(120, 170, 350, 200));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanel2.add(jLabel15, new AbsoluteConstraints(-140, -140, -1, 710));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel14.setToolTipText("");
        jPanel2.add(jLabel14, new AbsoluteConstraints(680, 80, 650, 720));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new AbsoluteLayout());
        //---Publicaciones----//

        jLabel35.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 153, 153));
        jLabel35.setText("PUBLICALOS, ASI PUEDEN ENCONTRAR UN HOGAR");
        jLabel35.setToolTipText("");
        jPanel5.add(jLabel35, new AbsoluteConstraints(290, 50, -1, -1));

        EligeImg.setBackground(new java.awt.Color(255, 236, 236));
        EligeImg.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        EligeImg.setForeground(new java.awt.Color(51, 51, 51));
        EligeImg.setText("Elige una imagen");
        EligeImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EligeImgActionPerformed(evt);
            }
        });
        jPanel5.add(EligeImg, new AbsoluteConstraints(80, 110, 170, 30));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Tipo");
        jPanel5.add(jLabel37, new AbsoluteConstraints(430, 360, -1, -1));

        textArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textArea2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textArea2.setForeground(new java.awt.Color(51, 51, 51));
        jPanel5.add(textArea2, new AbsoluteConstraints(260, 150, 710, 110));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Agrega una breve descripcion");
        jPanel5.add(jLabel38, new AbsoluteConstraints(260, 110, -1, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Edad ");
        jPanel5.add(jLabel39, new AbsoluteConstraints(80, 480, -1, -1));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(51, 51, 51));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField4, new AbsoluteConstraints(80, 500, 260, 30));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(51, 51, 51));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField5, new AbsoluteConstraints(80, 380, 260, 30));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Nombre");
        jPanel5.add(jLabel41, new AbsoluteConstraints(80, 360, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todos", "Perro", "Gato" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox3, new AbsoluteConstraints(430, 380, 230, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Verificacion (Selecciona si lo cumple)");
        jPanel5.add(jLabel42, new AbsoluteConstraints(720, 350, -1, 30));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todos", "Grande", "Mediano", "Pequeño" }));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox4, new AbsoluteConstraints(430, 440, 230, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Tamaño");
        jPanel5.add(jLabel43, new AbsoluteConstraints(430, 420, -1, 20));

        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox2.setText("Se lleva con otros animales?");
        jCheckBox2.setContentAreaFilled(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox2, new AbsoluteConstraints(720, 470, 220, 30));

        jCheckBox6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox6.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox6.setText("Desparacitado/a");
        jCheckBox6.setContentAreaFilled(false);
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox6, new AbsoluteConstraints(720, 410, 140, 30));

        jCheckBox7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox7.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox7.setText("Se lleva con niños?");
        jCheckBox7.setContentAreaFilled(false);
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox7, new AbsoluteConstraints(720, 440, 170, 30));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Agrega sus caracteristicas");
        jPanel5.add(jLabel44, new AbsoluteConstraints(80, 330, -1, 20));

        jCheckBox8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox8.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox8.setText("Vacunas");
        jCheckBox8.setContentAreaFilled(false);
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBox8, new AbsoluteConstraints(720, 380, 100, 30));

        jButton4.setBackground(new java.awt.Color(255, 236, 236));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 51));
        jButton4.setText("Publicar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new AbsoluteConstraints(450, 550, 160, 50));
        jPanel5.add(Imagen, new AbsoluteConstraints(80, 150, 170, 170));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel45.setToolTipText("");
        jPanel5.add(jLabel45, new AbsoluteConstraints(-140, -140, -1, 710));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel46.setToolTipText("");
        jPanel5.add(jLabel46, new AbsoluteConstraints(680, 80, 650, 720));

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 153, 153));
        jLabel16.setText("EDITAR PUBLICACION");
        jLabel16.setToolTipText("");
        jPanel3.add(jLabel16, new AbsoluteConstraints(460, 40, -1, -1));

        textArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textArea1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textArea1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.add(textArea1, new AbsoluteConstraints(80, 150, 890, 110));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Descripcion");
        jPanel3.add(jLabel18, new AbsoluteConstraints(80, 120, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Edad ");
        jPanel3.add(jLabel19, new AbsoluteConstraints(430, 400, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1, new AbsoluteConstraints(430, 430, 260, 30));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(51, 51, 51));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new AbsoluteConstraints(80, 360, 260, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Nombre");
        jPanel3.add(jLabel22, new AbsoluteConstraints(80, 330, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Verificacion (Selecciona si lo cumple)");
        jPanel3.add(jLabel23, new AbsoluteConstraints(720, 330, -1, 30));

        jComboBox2.setBackground(new java.awt.Color(255, 246, 246));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todos", "Grande", "Mediano", "Pequeño" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox2, new AbsoluteConstraints(430, 360, 230, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Tamaño");
        jPanel3.add(jLabel24, new AbsoluteConstraints(430, 330, -1, 20));

        jCheckBox1.setBackground(new java.awt.Color(255, 246, 246));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setText("Se lleva con otros animales?");
        jCheckBox1.setContentAreaFilled(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, new AbsoluteConstraints(720, 450, 220, 30));

        jCheckBox3.setBackground(new java.awt.Color(255, 246, 246));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox3.setText("Desparacitado/a");
        jCheckBox3.setContentAreaFilled(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox3, new AbsoluteConstraints(720, 390, 140, 30));

        jCheckBox4.setBackground(new java.awt.Color(255, 246, 246));
        jCheckBox4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox4.setText("Se lleva con niños?");
        jCheckBox4.setContentAreaFilled(false);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox4, new AbsoluteConstraints(720, 420, 170, 30));

        jCheckBox5.setBackground(new java.awt.Color(255, 246, 246));
        jCheckBox5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox5.setText("Vacunas");
        jCheckBox5.setContentAreaFilled(false);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox5, new AbsoluteConstraints(720, 360, 100, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Caracteristicas");
        jPanel3.add(jLabel25, new AbsoluteConstraints(80, 290, -1, 20));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel21.setToolTipText("");
        jPanel3.add(jLabel21, new AbsoluteConstraints(-140, -140, -1, 710));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Imagen9.png"))); // NOI18N
        jLabel26.setToolTipText("");
        jPanel3.add(jLabel26, new AbsoluteConstraints(680, 80, 650, 720));

        jButton3.setBackground(new java.awt.Color(255, 236, 236));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Guardar cambios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new AbsoluteConstraints(450, 550, 160, 50));

        jTabbedPane1.addTab("tab2", jPanel3);

        getContentPane().add(jTabbedPane1, new AbsoluteConstraints(340, 40, 1160, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    protected void eliminarPostButton(ActionEvent evt, int idPost, JLabel imagen, JLabel descripcion, JPanel original, JPanel descripcionOirginal) {
        if (Controller.eliminarPost(idPost)== "OK") {
            original.remove(imagen);      
            descripcionOirginal.remove(descripcion);
        }
    }

    private void SALIRMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SALIRMouseMoved
        SALIR.setBackground(new Color(214,180,248));
    }//GEN-LAST:event_SALIRMouseMoved

    private void SALIRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SALIRMouseClicked
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_SALIRMouseClicked

    private void SALIRMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SALIRMouseExited
        SALIR.setBackground(new Color(247,247,247));
    }//GEN-LAST:event_SALIRMouseExited

    private void HOMEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HOMEMouseMoved
       HOME.setBackground(new Color(214,180,248));
    }//GEN-LAST:event_HOMEMouseMoved

    private void HOMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HOMEMouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_HOMEMouseClicked

    private void HOMEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HOMEMouseExited
        HOME.setBackground(new Color(247,247,247));
    }//GEN-LAST:event_HOMEMouseExited

    private void BUSCARMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BUSCARMouseMoved
        BUSCAR.setBackground(new Color(214,180,248));
    }//GEN-LAST:event_BUSCARMouseMoved

    private void BUSCARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BUSCARMouseClicked
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_BUSCARMouseClicked

    private void BUSCARMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BUSCARMouseExited
         BUSCAR.setBackground(new Color(247,247,247));
    }//GEN-LAST:event_BUSCARMouseExited

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if((String) jComboBox2.getSelectedItem() != "Mostrar todos"){
            if(Controller.modificarPost(post.getId(), jTextField2.getText(), textArea1.getText(), jCheckBox5.isSelected(),jCheckBox4.isSelected(),jCheckBox1.isSelected(), jCheckBox3.isSelected(), jTextField1.getText(), (String) jComboBox2.getSelectedItem(), post.getTipoMascota()).equals("OK")){
                MenuR MenuFrame = new MenuR();
                MenuFrame.setVisible(true);
                MenuFrame.pack();
                MenuFrame.setLocationRelativeTo(null);
                this.dispose();
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            textArea1.setText(publicaciones.get(0).getDescripcion());
            jTextField2.setText(publicaciones.get(0).gettitulo());
            jTextField1.setText(publicaciones.get(0).getEdad());
            jComboBox2.setSelectedItem(publicaciones.get(0).getTamaño());
            jCheckBox5.setSelected(publicaciones.get(0).getVerificacion().isVacunas());
            jCheckBox4.setSelected(publicaciones.get(0).getVerificacion().isNiños());
            jCheckBox3.setSelected(publicaciones.get(0).getVerificacion().isDesparacitado());
            jCheckBox1.setSelected(publicaciones.get(0).getVerificacion().isOtrasMascotas());
            post = publicaciones.get(0);
            jTabbedPane1.setSelectedIndex(2);
        } catch(Exception e){
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try{
            textArea1.setText(publicaciones.get(1).getDescripcion());
            jTextField2.setText(publicaciones.get(1).gettitulo());
            jTextField1.setText(publicaciones.get(1).getEdad());
            jComboBox2.setSelectedItem(publicaciones.get(1).getTamaño());
            jCheckBox5.setSelected(publicaciones.get(1).getVerificacion().isVacunas());
            jCheckBox4.setSelected(publicaciones.get(1).getVerificacion().isNiños());
            jCheckBox3.setSelected(publicaciones.get(1).getVerificacion().isDesparacitado());
            jCheckBox1.setSelected(publicaciones.get(1).getVerificacion().isOtrasMascotas());
            post = publicaciones.get(1);
            jTabbedPane1.setSelectedIndex(2);
        } catch(Exception e){
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try{
            textArea1.setText(publicaciones.get(2).getDescripcion());
            jTextField2.setText(publicaciones.get(2).gettitulo());
            jTextField1.setText(publicaciones.get(2).getEdad());
            jComboBox2.setSelectedItem(publicaciones.get(2).getTamaño());
            jCheckBox5.setSelected(publicaciones.get(2).getVerificacion().isVacunas());
            jCheckBox4.setSelected(publicaciones.get(2).getVerificacion().isNiños());
            jCheckBox3.setSelected(publicaciones.get(2).getVerificacion().isDesparacitado());
            jCheckBox1.setSelected(publicaciones.get(2).getVerificacion().isOtrasMascotas());
            post = publicaciones.get(2);
            jTabbedPane1.setSelectedIndex(2);
        } catch(Exception e){
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try{
            textArea1.setText(publicaciones.get(3).getDescripcion());
            jTextField2.setText(publicaciones.get(3).gettitulo());
            jTextField1.setText(publicaciones.get(3).getEdad());
            jComboBox2.setSelectedItem(publicaciones.get(3).getTamaño());
            jCheckBox5.setSelected(publicaciones.get(3).getVerificacion().isVacunas());
            jCheckBox4.setSelected(publicaciones.get(3).getVerificacion().isNiños());
            jCheckBox3.setSelected(publicaciones.get(3).getVerificacion().isDesparacitado());
            jCheckBox1.setSelected(publicaciones.get(3).getVerificacion().isOtrasMascotas());
            post = publicaciones.get(3);
            jTabbedPane1.setSelectedIndex(2);
        } catch(Exception e){
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private String EligeImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EligeImgActionPerformed
        String Ruta = "";
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JGP & PNG","jpg","png");
        jFileChooser.setFileFilter(filter);
        
        int respuesta = jFileChooser.showOpenDialog(this);
        
        if (respuesta == JFileChooser.APPROVE_OPTION){
            Ruta = jFileChooser.getSelectedFile().getPath();
            
            Image mImage = new ImageIcon(Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImage.getScaledInstance(Imagen.getWidth(), Imagen.getHeight() , Image.SCALE_SMOOTH));
            Imagen.setIcon(mIcono);
        }
        return Ruta;
    }//GEN-LAST:event_EligeImgActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        Controller.publicarPost(jTextField5.getText(), textArea2.getText(), jCheckBox8.isSelected(), jCheckBox7.isSelected(), jCheckBox2.isSelected(), jCheckBox6.isSelected(), jTextField4.getText(), (String) jComboBox4.getSelectedItem(), (String) jComboBox3.getSelectedItem(), new File(EligeImgActionPerformed(evt)));daw
        MenuR MenuFrame = new MenuR();
        MenuFrame.setVisible(true);
        MenuFrame.pack();
        MenuFrame.setLocationRelativeTo(null);
        this.dispose();
    }

  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BUSCAR;
    private javax.swing.JLabel Descrip1;
    private javax.swing.JLabel Descrip2;
    private javax.swing.JLabel Descrip3;
    private javax.swing.JLabel Descrip4;
    private javax.swing.JButton EligeImg;
    private javax.swing.JPanel HOME;
    private javax.swing.JLabel Imagen;
    private javax.swing.JLabel Img1;
    private javax.swing.JLabel Img2;
    private javax.swing.JLabel Img3;
    private javax.swing.JLabel Img4;
    private javax.swing.JPanel SALIR;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2; //Pagina inicio
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private java.awt.TextArea textArea1;
    private java.awt.TextArea textArea2;
    // End of variables declaration//GEN-END:variables



}