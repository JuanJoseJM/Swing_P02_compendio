/**
 * VentanaDialogo.java
 * Nov 18, 2024 11:16:43 PM
 * @autor Juan José Juárez Mensales
 */
package practica02;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;

/**
 * Clase VentanaDialogo
 */
public class VentanaDialogo extends JDialog {
    private JTextField txtNombre, txtApellidos, txtDNI, txtTelefono;
    private JTextField txtFechaEntrada, txtFechaSalida, txtDiasEstancia;
    
    /*
     * Constructor de la VentanaDialogo
     */
    public VentanaDialogo(JFrame parent) {
        super(parent, "Alta Reservas", true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 3, screenSize.height / 2);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Datos del Cliente", crearPanelDatosCliente());
        tabbedPane.addTab("Datos de la Habitación", crearPanelDatosHabitacion());
        add(tabbedPane, BorderLayout.CENTER);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            if (validarDatos()) {
                JOptionPane.showMessageDialog(this, "Reserva registrada correctamente.");
                dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Panel para los datos del cliente.
     */
    private JPanel crearPanelDatosCliente() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2), "Datos del Cliente", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        txtApellidos = new JTextField(15);
        panel.add(txtApellidos, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        txtDNI = new JTextField(10);
        panel.add(txtDNI, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(10);
        panel.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Fecha Entrada:"), gbc);
        gbc.gridx = 1;
        txtFechaEntrada = new JTextField(LocalDate.now().toString(), 10);
        txtFechaEntrada.setEditable(false);
        panel.add(txtFechaEntrada, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Fecha Salida:"), gbc);
        gbc.gridx = 1;
        txtFechaSalida = new JTextField(LocalDate.now().plusDays(1).toString(), 10);
        txtFechaSalida.setEditable(false);
        panel.add(txtFechaSalida, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Días Estancia:"), gbc);
        gbc.gridx = 1;
        txtDiasEstancia = new JTextField("1", 5);
        txtDiasEstancia.setEditable(false);
        panel.add(txtDiasEstancia, gbc);

        return panel;
    }

    /**
     * Panel para los datos de la habitación.
     */
    private JPanel crearPanelDatosHabitacion() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 255, 240));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2), "Datos de la Habitación", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo Habitación:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> cmbTipoHabitacion = new JComboBox<>(new String[]{"Simple", "Doble", "Suite"});
        panel.add(cmbTipoHabitacion, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nº Habitaciones:"), gbc);
        gbc.gridx = 1;
        JSpinner spnHabitaciones = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        panel.add(spnHabitaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("¿Niños?"), gbc);
        gbc.gridx = 1;
        JCheckBox chkNinos = new JCheckBox();
        panel.add(chkNinos, gbc);

        return panel;
    }

    /**
     * Valido los datos ingresados.
     */
    private boolean validarDatos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!txtDNI.getText().matches("\\d{8}[A-Za-z]")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 8 números y una letra.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!txtTelefono.getText().matches("\\d{9}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener 9 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}