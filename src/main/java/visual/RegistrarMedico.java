package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accesoDatos.GuardarDatos;

/** Registra un médico nuevo o, si se abre con los datos de uno existente, lo modifica. */
public class RegistrarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreMedico;
	private JTextField txtCedulaMedico;
	private JTextField txtEspecialidadMedico;
	private JTextField txtDisponibilidadMedico;
	private JButton btnGuardarMedico;
	private JButton btnSalir;

	/** Id del médico que se está modificando, o null si es un registro nuevo. */
	private final Integer idMedico;

	public RegistrarMedico() {
		this(null);
	}

	/** Abre el formulario para modificar un médico existente. */
	public RegistrarMedico(int idMedico, String nombre, String cedula, String especialidad, String disponibilidad) {
		this(Integer.valueOf(idMedico));
		txtNombreMedico.setText(nombre);
		txtCedulaMedico.setText(cedula);
		txtEspecialidadMedico.setText(especialidad);
		txtDisponibilidadMedico.setText(disponibilidad);
	}

	private RegistrarMedico(Integer idMedico) {
		this.idMedico = idMedico;
		setTitle(idMedico == null ? "Registrar médico" : "Modificar médico");
		setBounds(100, 100, 583, 380);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Cédula:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(25, 18, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtCedulaMedico = new JTextField();
			txtCedulaMedico.setBounds(90, 13, 130, 26);
			contentPanel.add(txtCedulaMedico);
			txtCedulaMedico.setColumns(10);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setForeground(new Color(255, 255, 255));
			lblNombre.setBounds(309, 18, 61, 16);
			contentPanel.add(lblNombre);
		}
		{
			txtNombreMedico = new JTextField();
			txtNombreMedico.setColumns(10);
			txtNombreMedico.setBounds(421, 13, 130, 26);
			contentPanel.add(txtNombreMedico);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Especialidad:");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(25, 113, 87, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtEspecialidadMedico = new JTextField();
			txtEspecialidadMedico.setBounds(124, 108, 130, 26);
			contentPanel.add(txtEspecialidadMedico);
			txtEspecialidadMedico.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Disponibilidad:");
			lblNewLabel_2.setForeground(new Color(255, 255, 255));
			lblNewLabel_2.setBounds(304, 113, 101, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtDisponibilidadMedico = new JTextField();
			txtDisponibilidadMedico.setColumns(10);
			txtDisponibilidadMedico.setBounds(421, 108, 130, 26);
			contentPanel.add(txtDisponibilidadMedico);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardarMedico = new JButton("Guardar");
				btnGuardarMedico.setActionCommand("OK");
				buttonPane.add(btnGuardarMedico);
				getRootPane().setDefaultButton(btnGuardarMedico);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}


		}
		btnGuardarMedico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        guardarMedico();
		    }
		});
	}

	private void guardarMedico() {
		String nombre = txtNombreMedico.getText().trim();
		String cedula = txtCedulaMedico.getText().trim();
		String especialidad = txtEspecialidadMedico.getText().trim();
		String disponibilidad = txtDisponibilidadMedico.getText().trim();

		if (nombre.isEmpty() || cedula.isEmpty() || especialidad.isEmpty() || disponibilidad.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// El médico inicia sesión con su cédula como identificación, que debe tener 11 caracteres.
		if (cedula.length() != 11) {
			JOptionPane.showMessageDialog(this, "La cédula debe tener 11 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (idMedico == null) {
			if (GuardarDatos.insertarDatosMedico(nombre, cedula, especialidad, disponibilidad)) {
				JOptionPane.showMessageDialog(this, "Médico registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				clearTxt();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo registrar el médico. Verifique que la cédula no esté ya registrada.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if (GuardarDatos.actualizarDatosMedico(idMedico, nombre, cedula, especialidad, disponibilidad)) {
				JOptionPane.showMessageDialog(this, "Médico modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo modificar el médico.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void clearTxt() {
		txtCedulaMedico.setText("");
		txtNombreMedico.setText("");
		txtEspecialidadMedico.setText("");
		txtDisponibilidadMedico.setText("");
	}
}
