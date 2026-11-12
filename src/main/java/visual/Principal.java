package visual;
import accesoDatos.CargarDatos;
import accesoDatos.Conexion;
import accesoDatos.MiServidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class Principal extends JFrame {

	/** Tamaño para el que se diseñó la pantalla principal. */
	private static final int ANCHO_DISENO = 1728;
	private static final int ALTO_DISENO = 1105;

	private JPanel contentPane;
	private RegistrarCita registrarCita;
	private RegistrarMedico registrarMedico;
	private PerfilMedico perfilMedico;
	private RegistrarEnfermedades registrarEnfermedades;
	private RegistrarVacunas registrarVacunas;
	private Login login;
	private JPanel panelRegCita;
	private JPanel panelPaciente;
	private JPanel panelRegEnfermedad;
	private JPanel panelRegVacuna;
	private JPanel panelRespaldo;
	private String tipoUsuarioActual;
	private JPanel panelRegMedico;
	private JPanel panelListarMedico;
	private ListarMedicos listarMedicos;
	Respaldo_socket respaldo_socket;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// El servidor de respaldo vive dentro de la aplicación; si el puerto ya
		// está ocupado es porque se está ejecutando por separado.
		MiServidor.iniciarEnSegundoPlano();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);

					String errorConexion = Conexion.probar();
					if (errorConexion != null) {
						JOptionPane.showMessageDialog(login, mensajeSinConexion(errorConexion), "Sin conexión a la base de datos", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static String mensajeSinConexion(String detalle) {
		return "No se pudo conectar a la base de datos:\n" + Conexion.getUrl() + "\n\n"
				+ "Inicie la base de datos con \"docker compose up -d\" e intente de nuevo.\n\n"
				+ "Detalle: " + detalle;
	}

	
	
	/**
	 * Create the frame.
	 */
	public Principal(String tipoUsuario) {
		this.tipoUsuarioActual = tipoUsuario;
		ImageIcon imageIconMainPage = Recursos.imagen(Recursos.PRINCIPAL);
		ImageIcon imageIconMenu = Recursos.imagen(Recursos.MENU);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 1728, 1105);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
			}
		});
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setBounds(0, 0, screenWidth, screenHeight);

		// En pantallas más pequeñas que el diseño aparecen barras de
		// desplazamiento en lugar de quedar parte del menú fuera de alcance.
		contentPane.setPreferredSize(new Dimension(ANCHO_DISENO, ALTO_DISENO));
		JScrollPane scroll = new JScrollPane(contentPane);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		setContentPane(scroll);
		
		registrarCita = new RegistrarCita();
		registrarEnfermedades = new RegistrarEnfermedades();
		registrarVacunas =  new RegistrarVacunas();
		login = new Login();
		contentPane.setLayout(null);
		
		
		
		
		
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 6, 351, 1143);
		panelMenu.setBorder(null);
		panelMenu.setBackground(new Color(255, 255, 255));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		panelPaciente = new JPanel();
		panelPaciente.setBorder(null);
		panelPaciente.addMouseListener(new PanelButtonMouseAdapter(panelPaciente) {
			public void mouseClicked(MouseEvent e) {
				
				if(tipoUsuarioActual.equals("Admin")) {
					
					panelRegMedico.setVisible(false);
					panelListarMedico.setVisible(false);
				}
				
				if (tipoUsuarioActual.equals("Medico")) {
					
					ListaPacientes listaPacientes = new ListaPacientes();
					listaPacientes.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permiso para acceder a este apartado.");
				}
				
				
			}
		});
		
		
		panelPaciente.setBackground(new java.awt.Color(0,0,0,1));
		panelPaciente.setBounds(23, 465, 275, 58);
		panelMenu.add(panelPaciente);
		panelPaciente.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("PACIENTE");
		lblNewLabel_3.setBounds(103, 24, 61, 16);
		panelPaciente.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		panelRegEnfermedad = new JPanel();
		panelRegEnfermedad.setBorder(null);
		panelRegEnfermedad.addMouseListener(new PanelButtonMouseAdapter(panelRegEnfermedad) {
			public void mouseClicked(MouseEvent e) {
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
				if (tipoUsuarioActual.equals("Admin")) {
					registrarEnfermedades.setModal(true);
					registrarEnfermedades.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permiso para acceder a este apartado.");
				}
				
				
			}
		});
		panelRegEnfermedad.setBackground(new java.awt.Color(0,0,0,1));
		panelRegEnfermedad.setBounds(23, 575, 275, 58);
		panelMenu.add(panelRegEnfermedad);
		panelRegEnfermedad.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("ENFERMEDADES");
		lblNewLabel_4.setBounds(99, 17, 158, 16);
		panelRegEnfermedad.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		
		panelRegVacuna = new JPanel();
		panelRegVacuna.setBorder(null);
		panelRegVacuna.addMouseListener(new PanelButtonMouseAdapter(panelRegVacuna) {
			public void mouseClicked(MouseEvent e) {
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
				if (tipoUsuarioActual.equals("Admin")) {
					registrarVacunas.setModal(true);
					registrarVacunas.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permiso para acceder a este apartado.");
				}
				
				
			}
		});
		panelRegVacuna.setBackground(new java.awt.Color(0,0,0,1));
		panelRegVacuna.setBounds(23, 682, 275, 58);
		panelMenu.add(panelRegVacuna);
		panelRegVacuna.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("VACUNAS");
		lblNewLabel_5.setBounds(98, 19, 126, 16);
		panelRegVacuna.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		
		panelRespaldo = new JPanel();
		panelRespaldo.setBorder(null);
		panelRespaldo.addMouseListener(new PanelButtonMouseAdapter(panelRespaldo) {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				respaldo_socket = new Respaldo_socket();
				respaldo_socket.setModal(true);
				respaldo_socket.setVisible(true);
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
			}
		});
		panelRespaldo.setBackground(new java.awt.Color(0,0,0,1));
		panelRespaldo.setBounds(6, 796, 292, 65);
		panelMenu.add(panelRespaldo);
		panelRespaldo.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("RESPALDO");
		lblNewLabel_6.setBounds(116, 25, 113, 16);
		panelRespaldo.add(lblNewLabel_6);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		
		panelRegCita = new JPanel();
		panelRegCita.setBorder(null);
		panelRegCita.setBackground(new java.awt.Color(0,0,0,1));
		panelRegCita.addMouseListener(new PanelButtonMouseAdapter(panelRegCita) {
			public void mouseClicked(MouseEvent e) {
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
				if(tipoUsuarioActual.equals("Secretaria")) {
					registrarCita.setModal(true);
					registrarCita.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permiso para acceder a este apartado.");
				}
				
				
			}
		});
		
		
		panelRegCita.setBounds(37, 229, 249, 71);
		panelMenu.add(panelRegCita);
	
		panelRegCita.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CITA");
		lblNewLabel.setBounds(104, 32, 30, 16);
		panelRegCita.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JPanel panelCerrarSesion = new JPanel();
		panelCerrarSesion.setBorder(null);
		panelCerrarSesion.setBackground(new java.awt.Color(0,0,0,1));
		panelCerrarSesion.setBounds(23, 912, 275, 71);
		panelMenu.add(panelCerrarSesion);
		panelCerrarSesion.setLayout(null);
		panelCerrarSesion.addMouseListener(new PanelButtonMouseAdapter(panelCerrarSesion) {

				public void mouseClicked(MouseEvent e) {
					dispose();
					login.setVisible(true);
					
				}
			
		});
		
		JLabel lblNewLabel_8 = new JLabel("CERRAR SESION");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(91, 27, 134, 24);
		panelCerrarSesion.add(lblNewLabel_8);
		
		JPanel panelMedico = new JPanel();
		panelMedico.addMouseListener(new PanelButtonMouseAdapter(panelMedico) {
			public void mouseClicked(MouseEvent e) {
				if (tipoUsuarioActual.equals("Admin")) {
					panelRegMedico.setVisible(true);
					panelListarMedico.setVisible(true);
				
					
					
					
				}else if (tipoUsuarioActual.equals("Medico")) {
		
					panelRegMedico.setVisible(false);
					panelListarMedico.setVisible(false);
					// Se crea al abrirlo para que muestre las citas más recientes.
					perfilMedico = new PerfilMedico();
					perfilMedico.setModal(true);
					perfilMedico.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permiso para acceder a este apartado.");
				}
		
				
			}
		});
		panelMedico.setBorder(null);
		panelMedico.setLayout(null);
		panelMedico.setBackground(new java.awt.Color(0,0,0,1));
		panelMedico.setBounds(23, 337, 222, 83);
		panelMenu.add(panelMedico);
		
		JLabel lblNewLabel_2 = new JLabel("MEDICO");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(111, 38, 51, 16);
		panelMedico.add(lblNewLabel_2);
		
		panelRegMedico = new JPanel();
		panelRegMedico.setBorder(null);
		panelRegMedico.setBounds(244, 337, 134, 71);
		panelMenu.add(panelRegMedico);
		panelRegMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				registrarMedico = new RegistrarMedico();
				registrarMedico.setModal(true);
				registrarMedico.setVisible(true);
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
			}
		});
		panelRegMedico.setLayout(null);
		panelRegMedico.setBackground(new java.awt.Color(0,0,0,1));
		panelRegMedico.setVisible(false);
		
		// En dos líneas: el menú mide 351 px de ancho y el texto completo se cortaba.
		JLabel lblNewLabel_2_1 = new JLabel("<html>REGISTRAR<br>MEDICO</html>");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(6, 16, 100, 34);
		panelRegMedico.add(lblNewLabel_2_1);
		
		panelListarMedico = new JPanel();
		panelListarMedico.setBorder(null);
		panelListarMedico.setBounds(244, 408, 134, 54);
		panelMenu.add(panelListarMedico);
		panelListarMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarMedicos =  new ListarMedicos();
				listarMedicos.setModal(true);
				listarMedicos.setVisible(true);
				panelRegMedico.setVisible(false);
				panelListarMedico.setVisible(false);
				
			}
		});
		panelListarMedico.setLayout(null);
		panelListarMedico.setBackground(new java.awt.Color(0,0,0,1));
		panelListarMedico.setVisible(false);
		
		JLabel lblNewLabel_2_2 = new JLabel("<html>LISTAR<br>MEDICOS</html>");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setBounds(6, 10, 100, 34);
		panelListarMedico.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_7 = new JLabel(imageIconMenu);
		lblNewLabel_7.setBounds(-21, -20, 383, 1222);
		panelMenu.add(lblNewLabel_7);
		
		JLabel lblNewLabel_1 = new JLabel(imageIconMainPage);
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setBounds(345, 6, 1430, 1126);
		contentPane.add(lblNewLabel_1);
		
		JPanel panelGraficoPastel = new JPanel(new BorderLayout());
		panelGraficoPastel.setBounds(448, 586, 750, 348);
		contentPane.add(panelGraficoPastel);
		// El gráfico va por encima de la imagen de fondo.
		contentPane.setComponentZOrder(panelGraficoPastel, 0);
		
		if (tipoUsuarioActual.equals("Admin")) {
            deshabilitarComponentes(panelRegCita);
            deshabilitarComponentes(panelPaciente);
        } else if (tipoUsuarioActual.equals("Secretaria")) {
            deshabilitarComponentes(panelMedico);
            deshabilitarComponentes(panelPaciente);
            deshabilitarComponentes(panelRegEnfermedad);
            deshabilitarComponentes(panelRegVacuna);
            deshabilitarComponentes(panelRespaldo);
        } else if (tipoUsuarioActual.equals("Medico")) {
            deshabilitarComponentes(panelRegCita);
            deshabilitarComponentes(panelRegEnfermedad);
            deshabilitarComponentes(panelRegVacuna);
            deshabilitarComponentes(panelRespaldo);
        }
		
		// Citas registradas por médico.
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		for (Map.Entry<String, Integer> medico : CargarDatos.citasPorMedico().entrySet()) {
			dataset.setValue(medico.getKey(), medico.getValue());
		}

		JFreeChart chart = ChartFactory.createPieChart(
		        "Médicos más visitados",
		        dataset,
		        true,
		        true,
		        false
		);

		// Personaliza el gráfico
		PiePlot<?> plot = (PiePlot<?>) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No hay datos para mostrar");
		plot.setCircular(true); // Gráfico circular
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setShadowPaint(null);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(750, 348));
		panelGraficoPastel.add(chartPanel, BorderLayout.CENTER);
	
	}
	
	
	

	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel =  panel;
		}
		
		
	}
	private void deshabilitarComponentes(Container container) {
	    for (Component component : container.getComponents()) {
	        if (component instanceof Container) {
	            deshabilitarComponentes((Container) component); 
	        }
	        component.setEnabled(false); 
	    }
	   
	}

}