package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombrePaciente;
	private JTextField txtCedulaPaciente;
	private JTextField txtTelefonoPaciente;
	private JTextField txtDireccionPaciente;
	private JComboBox cbxEspecialistas;
	private JTextField txtFechaCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {
		setTitle("Registrar cita");
		setBounds(100, 100, 1052, 340);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Cédula:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(18, 39, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtCedulaPaciente = new JTextField();
			txtCedulaPaciente.setBounds(82, 34, 130, 26);
			contentPanel.add(txtCedulaPaciente);
			txtCedulaPaciente.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(250, 39, 61, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtNombrePaciente = new JTextField();
			txtNombrePaciente.setBounds(335, 34, 130, 26);
			contentPanel.add(txtNombrePaciente);
			txtNombrePaciente.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Teléfono:");
			lblNewLabel_2.setForeground(new Color(255, 255, 255));
			lblNewLabel_2.setBounds(525, 39, 69, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtTelefonoPaciente = new JTextField();
			txtTelefonoPaciente.setBounds(591, 34, 145, 26);
			contentPanel.add(txtTelefonoPaciente);
			txtTelefonoPaciente.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Dirección:");
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setBounds(792, 39, 69, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtDireccionPaciente = new JTextField();
			txtDireccionPaciente.setBounds(867, 10, 179, 74);
			contentPanel.add(txtDireccionPaciente);
			txtDireccionPaciente.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Especialista:");
			lblNewLabel_4.setForeground(new Color(255, 255, 255));
			lblNewLabel_4.setBounds(18, 175, 85, 16);
			contentPanel.add(lblNewLabel_4);
		}
		{
			cbxEspecialistas = new JComboBox();
			cbxEspecialistas.setBounds(99, 171, 179, 27);
			contentPanel.add(cbxEspecialistas);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Fecha cita (dd/mm/yyyy):");
			lblNewLabel_5.setForeground(new Color(255, 255, 255));
			lblNewLabel_5.setBounds(383, 175, 168, 16);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtFechaCita = new JTextField();
			txtFechaCita.setBounds(565, 170, 130, 26);
			contentPanel.add(txtFechaCita);
			txtFechaCita.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardarCita = new JButton("Guardar");
				btnGuardarCita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						clearTxt();
					}
				});
				btnGuardarCita.setActionCommand("OK");
				buttonPane.add(btnGuardarCita);
				getRootPane().setDefaultButton(btnGuardarCita);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
	
	public void clearTxt() {
		txtCedulaPaciente.setText("");
		txtNombrePaciente.setText("");
		txtCedulaPaciente.setText("");
		txtDireccionPaciente.setText("");
		txtFechaCita.setText("");
		txtTelefonoPaciente.setText("");
		
		
	}

}
