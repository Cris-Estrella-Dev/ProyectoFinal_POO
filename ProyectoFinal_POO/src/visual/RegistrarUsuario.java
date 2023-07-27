package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.GuardarDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class RegistrarUsuario extends JDialog {

	public JComboBox getCbxTipoUsuario() {
		return cbxTipoUsuario;
	}

	public void setCbxTipoUsuario(JComboBox cbxTipoUsuario) {
		this.cbxTipoUsuario = cbxTipoUsuario;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JPasswordField getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(JPasswordField txtClave) {
		this.txtClave = txtClave;
	}
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnRegistrarse;
	private JComboBox cbxTipoUsuario;
	private JTextField txtIdentificacion;
	private Login logIn;
	private String[] opciones;
	private JLabel lblNewLabel_3;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 432);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Identificación:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(280, 106, 94, 16);
			contentPanel.add(lblNewLabel);
		}
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(427, 101, 195, 26);
		contentPanel.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
	
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de usuario:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(280, 283, 108, 16);
		contentPanel.add(lblNewLabel_1);
		String[] opciones = {"Admin", "Secretaria", "Medico"};
		DefaultComboBoxModel<String> comboBoxModel =  new DefaultComboBoxModel<>(opciones);
		cbxTipoUsuario = new JComboBox(comboBoxModel);
		cbxTipoUsuario.setSelectedIndex(-1);
		cbxTipoUsuario.setBounds(427, 279, 193, 27);
		contentPanel.add(cbxTipoUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("Clave:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(280, 199, 61, 16);
		contentPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("REGISTRAR USUARIO");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(266, 17, 138, 16);
		contentPanel.add(lblNewLabel_3);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(427, 194, 195, 26);
		contentPanel.add(txtClave);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrarse = new JButton("Registrarse");
				btnRegistrarse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String identificacion = txtIdentificacion.getText();
		                    String clave = new String(txtClave.getPassword());
		                    String tipoUsuario = cbxTipoUsuario.getSelectedItem().toString();

		                    GuardarDatos.insertarDatosUsuario(identificacion, clave, tipoUsuario);
		                    JOptionPane.showMessageDialog(RegistrarUsuario.this, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		                    clearTxt();
					}
				});
				btnRegistrarse.setActionCommand("OK");
				buttonPane.add(btnRegistrarse);
				getRootPane().setDefaultButton(btnRegistrarse);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						logIn = new Login();
						dispose();
						logIn.setModal(true);
						logIn.setVisible(true);
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	public void clearTxt() {
		txtIdentificacion.setText("");
		txtClave.setText("");
		cbxTipoUsuario.setSelectedIndex(-1);
		
		
	}
}
