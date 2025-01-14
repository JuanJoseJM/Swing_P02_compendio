/**
 * VentanaPrincipal.java
 * Nov 13, 2024 9:25:32 AM
 * @autor Juan José Juárez Mensales
 */
package practica02;

import javax.swing.*;
import java.awt.*;


/**
 * Clase VentanaPrincipal.
 */
public class VentanaPrincipal extends JFrame {
	
    /**
     * Constructor de la ventana principal.
     */
    public VentanaPrincipal() {
        setTitle("Gestión Hotel - Pegaso Del Ocaso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(400,400);
        setLocationRelativeTo(null);

        setIconImage(new ImageIcon(getClass().getResource("/recursos/Anagrama.png")).getImage());
        setLayout(new BorderLayout());
        add(crearPanelCentral(), BorderLayout.CENTER);
        setJMenuBar(crearBarraDeMenus());
    }
    
    /*
     * 
     * JPanel para el logo y los botones.
     */

    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        try {
            JLabel lblLogo = new JLabel(escalarImagen("/recursos/Anagrama.png", 250, 250));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panelCentral.add(lblLogo, gbc);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("No se pudo cargar el logo");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelCentral.add(errorLabel, gbc);
        }

        // Alta Reservas
        JButton btnAltaReservas = new JButton("Alta Reservas");
        btnAltaReservas.setIcon(escalarImagen("/recursos/fArriba.png", 32, 32)); // Imagen escalada
        btnAltaReservas.addActionListener(e -> mostrarDialogoAltaReservas());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelCentral.add(btnAltaReservas, gbc);

        // Botón Baja Reservas
        JButton btnBajaReservas = new JButton("Baja Reservas");
        btnBajaReservas.setIcon(escalarImagen("/recursos/fAbajo.png", 32, 32)); // Imagen escalada
        btnBajaReservas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción no disponible"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCentral.add(btnBajaReservas, gbc);

        return panelCentral;
    }
    
    
    /*
     * Imagen Escalada
     * 
     */

    private ImageIcon escalarImagen(String rutaImagen, int ancho, int alto) {
        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
            return null;
        }
    }
    
   
    /**
     * Jmenú con opciones de Archivo, Registro y Ayuda.
     * 
     */
    private JMenuBar crearBarraDeMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> System.exit(0));
        menuArchivo.add(salir);

        JMenu menuRegistro = new JMenu("Registro");
        JMenuItem altaReservas = new JMenuItem("Alta Reservas");
        altaReservas.setAccelerator(KeyStroke.getKeyStroke('A', 0)); // Sin Alt
        altaReservas.addActionListener(e -> mostrarDialogoAltaReservas());
        JMenuItem bajaReservas = new JMenuItem("Baja Reservas");
        bajaReservas.setAccelerator(KeyStroke.getKeyStroke('B', 0)); // Sin Alt
        bajaReservas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción no disponible"));
        menuRegistro.add(altaReservas);
        menuRegistro.add(bajaReservas);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem acercaDe = new JMenuItem("Acerca de");
        acercaDe.addActionListener(e -> JOptionPane.showMessageDialog(this, "Gestión Hotel Pegaso Del Ocaso - Hecho por: Juan José Juárez Mensales"));
        menuAyuda.add(acercaDe);

        menuBar.add(menuArchivo);
        menuBar.add(menuRegistro);
        menuBar.add(menuAyuda);
        return menuBar;
    }
    
    
    /**
     * Muestra el diálogo de alta de reservas.
     */
    private void mostrarDialogoAltaReservas() {
        VentanaDialogo dialogo = new VentanaDialogo(this);
        dialogo.setVisible(true);
    }
}