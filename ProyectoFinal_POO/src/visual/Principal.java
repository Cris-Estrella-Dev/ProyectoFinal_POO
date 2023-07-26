package visual;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;



public class Principal extends JFrame {

	private JPanel contentPane;
	private RegistrarCita registrarCita;
	private RegistrarMedico registrarMedico;
	private PerfilMedico perfilMedico;
	private HistorialPaciente historialPaciente;
	private RegistrarEnfermedades registrarEnfermedades;
	private RegistrarVacunas registrarVacunas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setUndecorated(true);
					frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 696);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        

        setBounds(0, 0, screenWidth, screenHeight);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		registrarCita = new RegistrarCita();
		registrarMedico = new RegistrarMedico();
		perfilMedico = new PerfilMedico();
		historialPaciente = new HistorialPaciente();
		registrarEnfermedades = new RegistrarEnfermedades();
		registrarVacunas =  new RegistrarVacunas();
	
		
		
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(112, 128, 144));
		panelMenu.setBounds(0, 0, 261, 1149);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblHome = new JLabel("");
		lblHome.setBounds(88, 6, 127, 96);
		lblHome.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-casa-48.png"));
		panelMenu.add(lblHome);
		
		JPanel panelMedico = new JPanel();
		panelMedico.addMouseListener(new PanelButtonMouseAdapter(panelMedico) {
			public void mouseClicked(MouseEvent e) {
				registrarMedico.setVisible(true);
				registrarMedico.setModal(true);
			}
		});
		panelMedico.setBackground(new Color(112, 128, 144));
		panelMedico.setBounds(6, 284, 249, 58);
		panelMenu.add(panelMedico);
		panelMedico.setLayout(null);
		
		JLabel lblDoctor = new JLabel("");
		lblDoctor.setBounds(18, -16, 51, 83);
		panelMedico.add(lblDoctor);
		lblDoctor.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-doctor-35.png"));
		
		JLabel lblNewLabel_2 = new JLabel("MEDICO");
		lblNewLabel_2.setBounds(81, 24, 51, 16);
		panelMedico.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		
		JPanel panelPaciente = new JPanel();
		panelPaciente.addMouseListener(new PanelButtonMouseAdapter(panelPaciente) {
			public void mouseClicked(MouseEvent e) {
				historialPaciente.setVisible(true);
				historialPaciente.setModal(true);
			}
		});
		panelPaciente.setBackground(new Color(112, 128, 144));
		panelPaciente.setBounds(6, 429, 249, 58);
		panelMenu.add(panelPaciente);
		panelPaciente.setLayout(null);
		
		JLabel lblPaciente = new JLabel("");
		lblPaciente.setBounds(17, 0, 61, 58);
		panelPaciente.add(lblPaciente);
		lblPaciente.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-hombre-35.png"));
		
		JLabel lblNewLabel_3 = new JLabel("PACIENTE");
		lblNewLabel_3.setBounds(79, 24, 61, 16);
		panelPaciente.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		JPanel panelRegEnfermedad = new JPanel();
		panelRegEnfermedad.addMouseListener(new PanelButtonMouseAdapter(panelRegEnfermedad) {
			public void mouseClicked(MouseEvent e) {
				registrarEnfermedades.setVisible(true);
				registrarEnfermedades.setModal(true);
			}
		});
		panelRegEnfermedad.setBackground(new Color(112, 128, 144));
		panelRegEnfermedad.setBounds(6, 591, 249, 54);
		panelMenu.add(panelRegEnfermedad);
		panelRegEnfermedad.setLayout(null);
		
		JLabel lblBacteria = new JLabel("");
		lblBacteria.setBounds(17, 6, 61, 37);
		panelRegEnfermedad.add(lblBacteria);
		lblBacteria.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-bacteria-35.png"));
		
		JLabel lblNewLabel_4 = new JLabel("ENFERMEDADES");
		lblNewLabel_4.setBounds(75, 16, 158, 16);
		panelRegEnfermedad.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		
		JPanel panelRegVacuna = new JPanel();
		panelRegVacuna.addMouseListener(new PanelButtonMouseAdapter(panelRegVacuna) {
			public void mouseClicked(MouseEvent e) {
				registrarVacunas.setVisible(true);
				registrarVacunas.setModal(true);
			}
		});
		panelRegVacuna.setBackground(new Color(112, 128, 144));
		panelRegVacuna.setBounds(6, 726, 249, 54);
		panelMenu.add(panelRegVacuna);
		panelRegVacuna.setLayout(null);
		
		JLabel lblVacuna = new JLabel("");
		lblVacuna.setBounds(17, 6, 61, 37);
		panelRegVacuna.add(lblVacuna);
		lblVacuna.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-vacuna-35.png"));
		
		JLabel lblNewLabel_5 = new JLabel("VACUNAS");
		lblNewLabel_5.setBounds(77, 16, 126, 16);
		panelRegVacuna.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		
		JPanel panelAdministracion = new JPanel();
		panelAdministracion.addMouseListener(new PanelButtonMouseAdapter(panelAdministracion) {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panelAdministracion.setBackground(new Color(112, 128, 144));
		panelAdministracion.setBounds(6, 888, 249, 58);
		panelMenu.add(panelAdministracion);
		panelAdministracion.setLayout(null);
		
		JLabel lblAdministracion = new JLabel("");
		lblAdministracion.setBounds(17, 6, 61, 33);
		panelAdministracion.add(lblAdministracion);
		lblAdministracion.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-check-list-35-2.png"));
		
		JLabel lblNewLabel_6 = new JLabel("ADMINISTRACION");
		lblNewLabel_6.setBounds(75, 16, 113, 16);
		panelAdministracion.add(lblNewLabel_6);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBackground(new Color(112, 128, 144));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 0, 0));
		btnSalir.setBounds(74, 1035, 115, 29);
		panelMenu.add(btnSalir);
		
		JPanel panelRegCita = new JPanel();
		panelRegCita.addMouseListener(new PanelButtonMouseAdapter(panelRegCita) {
			public void mouseClicked(MouseEvent e) {
				registrarCita.setVisible(true);
				registrarCita.setModal(true);
			}
		});
		
		
		panelRegCita.setBounds(6, 133, 249, 71);
		panelMenu.add(panelRegCita);
		panelRegCita.setBackground(new Color(112, 128, 144));
		panelRegCita.setLayout(null);
		
		JLabel lblCita = new JLabel("");
		lblCita.setBounds(17, 6, 55, 71);
		panelRegCita.add(lblCita);
		lblCita.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-calendario-35.png"));
		
		JLabel lblNewLabel = new JLabel("CITA");
		lblNewLabel.setBounds(84, 32, 30, 16);
		panelRegCita.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(778, 293, 512, 417);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/Copia de logoSunny.png"));
	
	}
	
	
	

	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel =  panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(120,144,160));
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(112,128,144));
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(112,128,144));
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(112,128,144));
			
		}
	}
}
