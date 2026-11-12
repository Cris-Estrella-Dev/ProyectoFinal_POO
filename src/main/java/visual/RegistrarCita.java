package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;
import accesoDatos.GuardarDatos;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombrePaciente;
	private JTextField txtCedulaPaciente;
	private JTextField txtTelefonoPaciente;
	private JTextField txtDireccionPaciente;
	private JComboBox<String> cbxEspecialistas;
	private JTextField txtFechaCita;
	private JTextField txtidmedico;
	private JButton btnGuardarCita;
	private JTextField txtnombreespeciaista;

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {

		ImageIcon imageIcon = Recursos.imagen(Recursos.REGISTRAR_CITA);
		setTitle("Registrar cita");
		setBounds(100, 100, 1169, 959);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtCedulaPaciente = new JTextField();
			txtCedulaPaciente.setToolTipText("Cédula del paciente");
			txtCedulaPaciente.setBounds(156, 133, 130, 26);
			contentPanel.add(txtCedulaPaciente);
			txtCedulaPaciente.setColumns(10);
		}
		{
			txtNombrePaciente = new JTextField();
			txtNombrePaciente.setToolTipText("Nombre del paciente");
			txtNombrePaciente.setBounds(156, 262, 130, 26);
			contentPanel.add(txtNombrePaciente);
			txtNombrePaciente.setColumns(10);
		}
		{
			txtTelefonoPaciente = new JTextField();
			txtTelefonoPaciente.setToolTipText("Teléfono");
			txtTelefonoPaciente.setBounds(138, 383, 145, 26);
			contentPanel.add(txtTelefonoPaciente);
			txtTelefonoPaciente.setColumns(10);
		}
		{
			txtDireccionPaciente = new JTextField();
			txtDireccionPaciente.setToolTipText("Dirección");
			txtDireccionPaciente.setBounds(125, 479, 179, 74);
			contentPanel.add(txtDireccionPaciente);
			txtDireccionPaciente.setColumns(10);
		}
		{

			cbxEspecialistas = new JComboBox<>();
			cbxEspecialistas.setToolTipText("Especialidad");
			cbxEspecialistas.setBounds(138, 622, 179, 27);
			contentPanel.add(cbxEspecialistas);

		}
		{
			txtFechaCita = new JTextField();
			txtFechaCita.setToolTipText("Fecha de la cita (dd/mm/aaaa)");
			txtFechaCita.setBounds(156, 749, 130, 26);
			contentPanel.add(txtFechaCita);
			txtFechaCita.setColumns(10);
		}

		txtidmedico = new JTextField();
		txtidmedico.setVisible(false);
		txtidmedico.setBounds(28, 210, 61, 26);
		contentPanel.add(txtidmedico);
		txtidmedico.setColumns(10);

		ActionListener guardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCita();
			}
		};
		ActionListener buscarEspecialista = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEspecialista();
			}
		};
		ActionListener salir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnGuardarCita = new JButton("Guardar");
			btnGuardarCita.addActionListener(guardar);
			btnGuardarCita.setActionCommand("OK");
			buttonPane.add(btnGuardarCita);
			getRootPane().setDefaultButton(btnGuardarCita);

			JButton btnSalir = new JButton("Salir");
			btnSalir.addActionListener(salir);
			btnSalir.setActionCommand("Cancel");
			buttonPane.add(btnSalir);
		}

		CargarDatos.CargarComboBox(cbxEspecialistas);
		cbxEspecialistas.setSelectedItem(null);

		JButton btnbuscarespecialista = new JButton("Buscar especialista");
		btnbuscarespecialista.setBounds(335, 621, 168, 29);
		contentPanel.add(btnbuscarespecialista);
		btnbuscarespecialista.addActionListener(buscarEspecialista);

		txtnombreespeciaista = new JTextField();
		txtnombreespeciaista.setToolTipText("Especialista seleccionado");
		txtnombreespeciaista.setEditable(false);
		txtnombreespeciaista.setColumns(10);
		txtnombreespeciaista.setBounds(537, 621, 130, 26);
		contentPanel.add(txtnombreespeciaista);

		// Botones dibujados en la imagen de fondo: AÑADIR, BUSCAR ESPECIALISTA y la X.
		JButton zonaAnadir = Recursos.zonaClic(new JButton());
		zonaAnadir.setBounds(756, 349, 284, 98);
		zonaAnadir.addActionListener(guardar);
		contentPanel.add(zonaAnadir);

		JButton zonaBuscar = Recursos.zonaClic(new JButton());
		zonaBuscar.setBounds(756, 476, 284, 98);
		zonaBuscar.addActionListener(buscarEspecialista);
		contentPanel.add(zonaBuscar);

		JButton zonaCerrar = Recursos.zonaClic(new JButton());
		zonaCerrar.setBounds(1116, 4, 46, 46);
		zonaCerrar.addActionListener(salir);
		contentPanel.add(zonaCerrar);

		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(-79, -11, 1248, 944);
		contentPanel.add(lblNewLabel);

	}

	private void buscarEspecialista() {
		String especialidad = (String) cbxEspecialistas.getSelectedItem();
		if (especialidad == null) {
			JOptionPane.showMessageDialog(this, "Seleccione primero una especialidad.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// El listado se abre encima de esta ventana para no perder lo ya escrito.
		ListaEspecialista lista = new ListaEspecialista(this, especialidad);
		lista.setVisible(true);
	}

	private void guardarCita() {
		String cedula = txtCedulaPaciente.getText().trim();
		String nombre = txtNombrePaciente.getText().trim();
		String telefono = txtTelefonoPaciente.getText().trim();
		String direccion = txtDireccionPaciente.getText().trim();
		String fecha = txtFechaCita.getText().trim();

		if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || fecha.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (txtidmedico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Elija una especialidad y use \"Buscar especialista\" para seleccionar al médico.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int idmedico = Integer.parseInt(txtidmedico.getText());

		if (GuardarDatos.insertarDatosCita(cedula, nombre, telefono, direccion, fecha, idmedico)) {
			JOptionPane.showMessageDialog(this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			clearTxt();
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo registrar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clearTxt() {
		txtCedulaPaciente.setText("");
		txtNombrePaciente.setText("");
		txtDireccionPaciente.setText("");
		txtFechaCita.setText("");
		txtTelefonoPaciente.setText("");
		txtidmedico.setText("");
		txtnombreespeciaista.setText("");
		cbxEspecialistas.setSelectedIndex(-1);
	}

	/** Recibe el médico elegido en {@link ListaEspecialista}. */
	public void agregardatostextbox(String id, String nombre) {
		txtidmedico.setText(id);
		txtnombreespeciaista.setText(nombre);
	}
}
