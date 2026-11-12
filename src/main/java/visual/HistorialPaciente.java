package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

/** Historial clínico de un paciente: todas sus consultas, de la más reciente a la más antigua. */
public class HistorialPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCedulaPaciente;
	private JLabel lblNombrePaciente;
	private JLabel lblDireccionPaciente;
	private JLabel lblTelefonoCliente;
	private final JTable tablaHistorialClinico = new JTable();
	private JButton btnVerEnfermedadesCronicas;
	private JButton btnVerVacunasPaciente;
	private JButton btnSalir;

	/**
	 * Create the dialog.
	 */
	public HistorialPaciente(Window owner, final String cedula, String nombre, String direccion, String telefono) {
		super(owner, "Paciente", ModalityType.APPLICATION_MODAL);

		setBackground(new Color(112, 128, 144));
		setBounds(100, 100, 1157, 775);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);



		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(347, 28, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNombrePaciente = new JLabel("");
			lblNombrePaciente.setForeground(new Color(255, 255, 255));
			lblNombrePaciente.setBounds(408, 16, 189, 38);
			contentPanel.add(lblNombrePaciente);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cédula:");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(45, 28, 61, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblCedulaPaciente = new JLabel("");
			lblCedulaPaciente.setForeground(new Color(255, 255, 255));
			lblCedulaPaciente.setBounds(118, 28, 189, 16);
			contentPanel.add(lblCedulaPaciente);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Dirección:");
			lblNewLabel_2.setForeground(new Color(255, 255, 255));
			lblNewLabel_2.setBounds(601, 28, 70, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblDireccionPaciente = new JLabel("");
			lblDireccionPaciente.setForeground(new Color(255, 255, 255));
			lblDireccionPaciente.setBounds(678, 6, 226, 69);
			contentPanel.add(lblDireccionPaciente);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Teléfono:");
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setBounds(916, 28, 70, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			lblTelefonoCliente = new JLabel("");
			lblTelefonoCliente.setForeground(new Color(255, 255, 255));
			lblTelefonoCliente.setBounds(992, 28, 159, 16);
			contentPanel.add(lblTelefonoCliente);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("HISTORIAL CLINICO");
			lblNewLabel_4.setForeground(new Color(255, 255, 255));
			lblNewLabel_4.setBounds(496, 155, 135, 16);
			contentPanel.add(lblNewLabel_4);
		}
		JScrollPane scrollPane = new JScrollPane(tablaHistorialClinico);
		scrollPane.setBounds(6, 192, 1145, 510);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerEnfermedadesCronicas = new JButton("Ver enfermedades cronicas del paciente");
				btnVerEnfermedadesCronicas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new ListarEnfermedadesCronicasPaciente(HistorialPaciente.this, cedula).setVisible(true);
					}
				});
				btnVerEnfermedadesCronicas.setActionCommand("OK");
				buttonPane.add(btnVerEnfermedadesCronicas);
			}
			{
				btnVerVacunasPaciente = new JButton("Ver vacunas del paciente");
				btnVerVacunasPaciente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new ListarVacunasPaciente(HistorialPaciente.this, cedula).setVisible(true);
					}
				});
				btnVerVacunasPaciente.setActionCommand("OK");
				buttonPane.add(btnVerVacunasPaciente);
			}
			{

				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}

		agregardatospaciente(cedula, nombre, direccion, telefono);
		CargarDatos.cargarHistorialPaciente(tablaHistorialClinico, cedula);
	}

	public  void agregardatospaciente(String cedula, String nombre, String direccion, String telefono) {
		lblCedulaPaciente.setText(cedula);
		lblNombrePaciente.setText(nombre);
		lblDireccionPaciente.setText(direccion);
		lblTelefonoCliente.setText(telefono);
	}

}
