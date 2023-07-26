package visual;

import java.awt.BorderLayout;
import accesoDatos.GuardarDatos;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import accesoDatos.GuardarDatos;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegistrarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreMedico;
	private JTextField txtCedulaMedico;
	private JTextField txtEspecialidadMedico;
	private JTextField txtDisponibilidadMedico;
	private JButton btnGuardarMedico;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarMedico dialog = new RegistrarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarMedico() {
		setBounds(100, 100, 583, 380);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Cedula:");
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
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
			
			
		}
		btnGuardarMedico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        
		        String nombre = txtNombreMedico.getText();
		        String cedula = txtCedulaMedico.getText();
		        String especialidad = txtEspecialidadMedico.getText();
		        String disponibilidad = txtDisponibilidadMedico.getText();
		        
		        GuardarDatos.insertarDatos(nombre, cedula, especialidad, disponibilidad);
		        
		        JOptionPane.showMessageDialog(RegistrarMedico.this, "Médico registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		    }
		});

	}

}
