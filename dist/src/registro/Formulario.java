package registro;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Formulario extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Formulario() {
        initComponents();

        obtenerAnio();
        obternerFechaActualCF();

        this.setResizable(false);

        try {
            inicio();
        } catch (Exception e) {
        }

        this.setLocationRelativeTo(null);
        this.setTitle("Sistema de Seguimiento de Beneficios Sociales");
        labelTitulo.setFont(new Font("Verdana", 3, 30));
        labelTitulo.setForeground(new Color(0, 64, 122));
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        labelTitulo1.setText(anioTitulo);
        labelTitulo1.setFont(new Font("Verdana", 3, 30));
        labelTitulo1.setForeground(new Color(0, 64, 122));

        jtDocumentaciones.getTableHeader().setFont(new Font("Verdana", Font.ITALIC, 11));
        jtDocumentaciones.getTableHeader().setOpaque(false);
        jtDocumentaciones.getTableHeader().setBackground(new Color(0, 64, 122));
        jtDocumentaciones.getTableHeader().setForeground(new Color(255, 255, 255));
        jtDocumentaciones.setRowHeight(25);
        jtDocumentaciones.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 90));
        try {
            jtDocumentaciones.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String anioConsulta = cbxAnio.getSelectedItem().toString();

            String sql = "SELECT * FROM beneficios WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY numero";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("FECHA ACTUAL");
            modelo.addColumn("  Nº");
            modelo.addColumn("                               NOMBRE");
            modelo.addColumn("         MOTIVO");
            modelo.addColumn("  FECHA DE MOTIVO");
            modelo.addColumn("<html><center>FECHA DE RECEPCIÓN DE DOCUMENTO");
            modelo.addColumn("<html><center>FECHA DE ENVIO A ASESORIA LEGAL");
            modelo.addColumn("          DOCUMENTO PENDIENTE POR ENVIAR");
            modelo.addColumn(" VALORES QUE DEBE");

            int[] anchos = {0, 0, 40, 220, 45, 50, 50, 50, 220, 45};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else if (i == 1) {
                    jtDocumentaciones.getColumnModel().getColumn(1).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setPreferredWidth(anchos[i]);
                } else if (i == 2) {
                    jtDocumentaciones.getColumnModel().getColumn(2).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = "  " + rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

            iniciarCaja();

        } catch (SQLException ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelTitulo1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDocumentaciones = new colorearCelda();
        jLabel3 = new javax.swing.JLabel();
        cbxAnio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cajaTextoNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cajaTextoNombre = new javax.swing.JTextField();
        cajaTextoPendiente = new javax.swing.JTextField();
        jftfMotivo = new javax.swing.JFormattedTextField();
        jftfRecepcion = new javax.swing.JFormattedTextField();
        jftfEnvio = new javax.swing.JFormattedTextField();
        cbxMotivo = new javax.swing.JComboBox();
        cbxValores = new javax.swing.JComboBox();
        botonGuardar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        cajaTextoBuscar = new javax.swing.JTextField();
        labelFechaEst = new javax.swing.JLabel();
        cajaTextoAnio = new javax.swing.JTextField();
        botonGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setText("BENEFICIOS SOCIALES GESTIÓN -");

        labelTitulo1.setText("XX");

        jtDocumentaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jtDocumentaciones.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jtDocumentaciones.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDocumentacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDocumentaciones);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel3.setText("AÑO:");

        cbxAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cbxAnio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAnioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel4.setText("Nº:");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel5.setText("NOMBRE:");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel6.setText("MOTIVO:");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel7.setText("FECHA DE MOTIVO:");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel8.setText("FECHA DE RECEPCIÓN DE DOCUMENTO:");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel9.setText("FECHA DE ENVIO A ASESORIA LEGAL:");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel10.setText("VALORES QUE DEBE:");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel11.setText("DOCUMENTO PENDIENTE POR ENVIAR:");

        jftfMotivo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jftfMotivo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftfMotivoActionPerformed(evt);
            }
        });

        jftfRecepcion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jftfRecepcion.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftfRecepcionActionPerformed(evt);
            }
        });

        jftfEnvio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jftfEnvio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftfEnvioActionPerformed(evt);
            }
        });

        cbxMotivo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"JUBILACIÓN", "FALLECIMIENTO", "RETIRO"}));

        cbxValores.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Ningúno", "Solvencia Universitaria", "Cas", "Inicio de Tramite", "Todos los Valores"}));

        botonGuardar.setBackground(new java.awt.Color(0, 64, 122));
        botonGuardar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(0, 64, 122));
        botonGuardar.setText("GUARDAR");
        botonGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonModificar.setBackground(new java.awt.Color(0, 64, 122));
        botonModificar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(0, 64, 122));
        botonModificar.setText("MODIFICAR");
        botonModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonModificar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonEliminar.setBackground(new java.awt.Color(0, 64, 122));
        botonEliminar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(0, 64, 122));
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonLimpiar.setBackground(new java.awt.Color(0, 64, 122));
        botonLimpiar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonLimpiar.setForeground(new java.awt.Color(0, 64, 122));
        botonLimpiar.setText("LIMPIAR");
        botonLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        botonBuscar.setBackground(new java.awt.Color(0, 64, 122));
        botonBuscar.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        botonBuscar.setForeground(new java.awt.Color(0, 64, 122));
        botonBuscar.setText("BUSCAR NOMBRE");
        botonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        cajaTextoBuscar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoBuscarActionPerformed(evt);
            }
        });

        labelFechaEst.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelFechaEst.setText("REGISTRO DEL AÑO:");

        cajaTextoAnio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoAnioActionPerformed(evt);
            }
        });

        botonGenerar.setBackground(new java.awt.Color(0, 64, 122));
        botonGenerar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonGenerar.setForeground(new java.awt.Color(0, 64, 122));
        botonGenerar.setText("GENERAR REPORTE");
        botonGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4).addComponent(cajaTextoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jftfRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cajaTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel5).addComponent(jLabel9)).addComponent(jftfEnvio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(96, 96, 96).addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(97, 97, 97).addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 21, Short.MAX_VALUE))).addGap(97, 97, 97).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6).addComponent(jLabel11)).addGap(103, 103, 103).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel10).addComponent(jLabel7).addComponent(jftfMotivo))).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addComponent(cajaTextoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE).addComponent(cbxValores, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE).addComponent(botonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(333, 333, 333).addComponent(labelTitulo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(labelTitulo1)).addComponent(jLabel8)).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cbxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(cajaTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(labelFechaEst).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cajaTextoAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(95, 95, 95)))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelTitulo).addComponent(labelTitulo1)).addGap(28, 28, 28).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(cbxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(botonBuscar).addComponent(cajaTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(labelFechaEst).addComponent(cajaTextoAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel6).addComponent(jLabel7)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cajaTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jftfMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(39, 39, 39).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jLabel9).addComponent(jLabel10).addComponent(jLabel11)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jftfRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jftfEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cbxValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(32, 32, 32).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonModificar).addComponent(botonEliminar).addComponent(botonLimpiar).addComponent(botonGuardar).addComponent(botonGenerar)).addContainerGap(42, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Conexion con = new Conexion();
            Connection conn = (Connection) con.getConexion();

            JasperReport reporte = null;
            String path = "src\\reportes\\Beneficio.jasper";

            Map parametro = new HashMap();
            parametro.put("gestion", cbxAnio.getSelectedItem().toString());

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

            view.setExtendedState(view.MAXIMIZED_BOTH);

        } catch (JRException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jftfMotivoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jftfRecepcionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jftfEnvioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        PreparedStatement ps = null;
        int indice = 0;
        try {
            Conexion objCon = new Conexion();
            Connection conexion = (Connection) objCon.getConexion();
            ps = conexion.prepareStatement("INSERT INTO beneficios (fecha_actual, numero, nombre, motivo, fecha_mot, fecha_rec, fecha_env, doc_pend, val_debe) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, cajaTextoAnio.getText() + obtenerFechaActualSF());
            ps.setString(2, cajaTextoNumero.getText());
            ps.setString(3, cajaTextoNombre.getText());
            ps.setString(4, cbxMotivo.getSelectedItem().toString());
            ps.setString(5, jftfMotivo.getText());
            ps.setString(6, jftfRecepcion.getText());
            ps.setString(7, jftfEnvio.getText());
            ps.setString(8, cajaTextoPendiente.getText());
            ps.setString(9, cbxValores.getSelectedItem().toString());
            ps.execute();

            reiniciar();

            JOptionPane.showMessageDialog(null, "Registrado con Éxito", "Realizado", JOptionPane.INFORMATION_MESSAGE);

            limpiar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Registrar", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex);
        }
    }

    private void cbxAnioActionPerformed(java.awt.event.ActionEvent evt) {
        reiniciar();
        refrescarEntradas();
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        labelTitulo1.setText(anioTitulo);
        //cajaTextoAnio.setText(anioTitulo);
    }

    private void jtDocumentacionesMouseClicked(java.awt.event.MouseEvent evt) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion objCon = new Conexion();
            java.sql.Connection conn = objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String id = jtDocumentaciones.getValueAt(Fila, 0).toString();

            ps = conn.prepareStatement("SELECT fecha_actual, numero, nombre, motivo, fecha_mot, fecha_rec, fecha_env, doc_pend, val_debe FROM beneficios WHERE id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                //cajaTextoFechaActual.setText(darFormato(rs.getDate("fecha_actual")));
                cajaTextoAnio.setText(recibirAnio(rs.getDate("fecha_actual")));
                cajaTextoNumero.setText(rs.getString("numero"));
                cajaTextoNombre.setText(rs.getString("nombre"));
                cbxMotivo.setSelectedItem(rs.getString("motivo"));
                jftfMotivo.setText(rs.getString("fecha_mot"));
                jftfRecepcion.setText(rs.getString("fecha_rec"));
                jftfEnvio.setText(rs.getString("fecha_env"));
                cajaTextoPendiente.setText(rs.getString("doc_pend"));
                cbxValores.setSelectedItem(rs.getString("val_debe"));
            }
        } catch (SQLException ex) {
            //System.out.println(ex.toString());
        }
    }

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {

        //int Fila = jtDocumentaciones.getSelectedRow();
        PreparedStatement ps = null;
        try {
            Conexion objCon = new Conexion();
            Connection conn = (Connection) objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String codigo = jtDocumentaciones.getValueAt(Fila, 0).toString();
            //System.out.println("Modificando " + codigo);

            ps = conn.prepareStatement("UPDATE beneficios SET fecha_actual=?, numero=?, nombre=?, motivo=?, fecha_mot=?, fecha_rec=?, fecha_env=?, doc_pend=?, val_debe=?  WHERE id=?");

            ps.setString(1, cajaTextoAnio.getText() + obtenerFechaActualSF());
            System.out.println(cajaTextoAnio.getText() + obtenerFechaActualSF());
            ps.setString(2, cajaTextoNumero.getText());
            ps.setString(3, cajaTextoNombre.getText());
            ps.setString(4, cbxMotivo.getSelectedItem().toString());
            ps.setString(5, jftfMotivo.getText());
            ps.setString(6, jftfRecepcion.getText());
            ps.setString(7, jftfEnvio.getText());
            ps.setString(8, cajaTextoPendiente.getText());
            ps.setString(9, cbxValores.getSelectedItem().toString());
            ps.setString(10, codigo);

            ps.execute();

            reiniciar();

            JOptionPane.showMessageDialog(null, "El Registro se ha sido Modificado", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            /*jtProductos.setValueAt(txtCodigo.getText(), Fila, 0);
            jtProductos.setValueAt(txtNombre.getText(), Fila, 1);
            jtProductos.setValueAt(txtPrecio.getText(), Fila, 2);
            jtProductos.setValueAt(txtCantidad.getText(), Fila, 3);*/

            refrescarEntradas();

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error al Modificar la Documentación");
            JOptionPane.showMessageDialog(null, "Error al Modificar el Registro", "Error", JOptionPane.ERROR_MESSAGE);

            //System.out.println(ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha modificado ningún Registro, ya que no se escogió alguno", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        PreparedStatement ps = null;
        try {

            Conexion objCon = new Conexion();
            Connection conn = (Connection) objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String codigo = jtDocumentaciones.getValueAt(Fila, 0).toString();
            //System.out.println("eliminando " + codigo);

            ps = conn.prepareStatement("DELETE FROM beneficios WHERE id=?");
            ps.setString(1, codigo);
            ps.execute();

            //modelo.removeRow(Fila);
            reiniciar();
            JOptionPane.showMessageDialog(null, "El Registro ha sido Eliminado", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            refrescarEntradas();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha eliminado ningún Registro, ya que no se escogió alguno", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        refrescarEntradas();
        reiniciar();
        iniciarCaja();
    }

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String campo = cajaTextoBuscar.getText();
        String anioConsulta = cbxAnio.getSelectedItem().toString();
        String sql = "";
        String where = "";
        if (!"".equals(campo)) {
            //where = "WHERE tram = '" + campo + "'";
            where = "WHERE nombre LIKE '%" + campo + "%'";
            sql = "SELECT * FROM beneficios " + where + " AND YEAR(fecha_actual) = " + anioConsulta + " ORDER BY numero";
        } else {
            sql = "SELECT * FROM beneficios WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY numero";
        }
        //SELECT * FROM tabla WHERE campo LIKE '%valorrecibido%'
        try {
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jtDocumentaciones.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = (Connection) conn.getConexion();

            //String sql = "SELECT id, tram, nombre, detalle, tipo, trab_re, entregado_a, fecha_prepar, fecha_entrega FROM documentos " + where;
            //sql = "SELECT * FROM documentos " + where +" AND YEAR(fecha_actual) = "+anioConsulta;
            //System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("FECHA ACTUAL");
            modelo.addColumn("  Nº");
            modelo.addColumn("                               NOMBRE");
            modelo.addColumn("         MOTIVO");
            modelo.addColumn("  FECHA DE MOTIVO");
            modelo.addColumn("<html><center>FECHA DE RECEPCIÓN DE DOCUMENTO");
            modelo.addColumn("<html><center>FECHA DE ENVIO A ASESORIA LEGAL");
            modelo.addColumn("          DOCUMENTO PENDIENTE POR ENVIAR");
            modelo.addColumn(" VALORES QUE DEBE");

            int[] anchos = {0, 0, 40, 220, 45, 50, 50, 50, 220, 45};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else if (i == 1) {
                    jtDocumentaciones.getColumnModel().getColumn(1).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setPreferredWidth(anchos[i]);
                } else if (i == 2) {
                    jtDocumentaciones.getColumnModel().getColumn(2).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = "  " + rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (Exception ex) {
            //System.err.println(ex.toString());
        }
    }

    private void cajaTextoBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cajaTextoAnioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void reiniciar() {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jtDocumentaciones.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String anioConsulta = cbxAnio.getSelectedItem().toString();

            String sql = "SELECT * FROM beneficios WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY numero";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("FECHA ACTUAL");
            modelo.addColumn("  Nº");
            modelo.addColumn("                               NOMBRE");
            modelo.addColumn("         MOTIVO");
            modelo.addColumn("  FECHA DE MOTIVO");
            modelo.addColumn("<html><center>FECHA DE RECEPCIÓN DE DOCUMENTO");
            modelo.addColumn("<html><center>FECHA DE ENVIO A ASESORIA LEGAL");
            modelo.addColumn("          DOCUMENTO PENDIENTE POR ENVIAR");
            modelo.addColumn(" VALORES QUE DEBE");

            int[] anchos = {0, 0, 40, 220, 45, 50, 50, 50, 220, 45};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else if (i == 1) {
                    jtDocumentaciones.getColumnModel().getColumn(1).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(1).setPreferredWidth(anchos[i]);
                } else if (i == 2) {
                    jtDocumentaciones.getColumnModel().getColumn(2).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(2).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = "  " + rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

            iniciarCaja();

        } catch (SQLException ex) {
        }
    }

    private void limpiar() {
        //cajaTextoNumero.setText("");
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        cajaTextoAnio.setText(anioTitulo);
        cajaTextoNombre.setText("");
        //cbxMotivo.setSelectedIndex(0);
        jftfMotivo.setText("");
        jftfRecepcion.setText("");
        jftfEnvio.setText("");
        cajaTextoPendiente.setText("");
        //cbxValores.setSelectedIndex(0);
    }

    private void refrescarEntradas() {
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        cajaTextoAnio.setText(anioTitulo);
        cajaTextoBuscar.setText("");
        //cajaTextoNumero.setText("");
        cajaTextoNombre.setText("");
        cbxMotivo.setSelectedIndex(0);
        jftfMotivo.setText("");
        jftfRecepcion.setText("");
        jftfEnvio.setText("");
        cajaTextoPendiente.setText("");
        cbxValores.setSelectedIndex(0);
    }

    public String obtenerFechaActualSF() {
        Calendar fecha = new GregorianCalendar();
        //int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return "-" + mes + "-" + dia;
    }

    public void obtenerAnio() {
        String anioString = "";
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        String[] opciones = new String[10];
        for (int i = 0; i < 10; i++) {
            anioString = Integer.toString(año);
            opciones[i] = anioString;
            año = año - 1;
        }
        cbxAnio.setModel(new javax.swing.DefaultComboBoxModel(opciones));
    }

    public String recibirAnio(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);
        return Integer.toString(anio);
    }

    public void obternerFechaActualCF() {

        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);

        cajaTextoAnio.setText(Integer.toString(año));
    }

    public void iniciarCaja() {

        try {
            PreparedStatement ps3 = null;
            ResultSet rs2 = null;
            Conexion conn2 = new Conexion();
            java.sql.Connection conecta = conn2.getConexion();

            String anioConsulta = cbxAnio.getSelectedItem().toString();

            String sql2 = "SELECT numero FROM beneficios WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY numero DESC LIMIT 1";
            ps3 = conecta.prepareStatement(sql2);
            rs2 = ps3.executeQuery();

            if (rs2.next()) {
                //while (rs2.next()) {
                int ultimo = rs2.getInt(1);
                ultimo++;
                cajaTextoNumero.setText(Integer.toString(ultimo));
                //}
            } else {
                cajaTextoNumero.setText("1");
            }

        } catch (SQLException ex) {
        }
    }

    private void inicio() throws ParseException {

        jftfMotivo.setEnabled(true);
        jftfMotivo.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("   ##  /  ##  /  ##")));

        jftfRecepcion.setEnabled(true);
        jftfRecepcion.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("   ##  /  ##  /  ##")));

        jftfEnvio.setEnabled(true);
        jftfEnvio.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("   ##  /  ##  /  ##")));

    }

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JTextField cajaTextoAnio;
    private javax.swing.JTextField cajaTextoBuscar;
    private javax.swing.JTextField cajaTextoNombre;
    private javax.swing.JTextField cajaTextoNumero;
    private javax.swing.JTextField cajaTextoPendiente;
    private javax.swing.JComboBox cbxAnio;
    private javax.swing.JComboBox cbxMotivo;
    private javax.swing.JComboBox cbxValores;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jftfEnvio;
    private javax.swing.JFormattedTextField jftfMotivo;
    private javax.swing.JFormattedTextField jftfRecepcion;
    private javax.swing.JTable jtDocumentaciones;
    private javax.swing.JLabel labelFechaEst;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTitulo1;
    // End of variables declaration
}
