package visual;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import accesoDatos.GuardarDatos;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private RegistrarUsuario registrarUsuario;
	private JTextField txtIdentificacion;
	private JComboBox cbxTipoUsuario;
	private String[] opciones;
	private JPasswordField txtClave;
	private Principal principal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setUndecorated(true);
			dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		
		
		
		setBackground(new Color(112, 128, 144));
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 482);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Identificacion");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(304, 108, 92, 16);
			contentPanel.add(lblNewLabel);
		}
		

		String[] opciones = {"Admin", "Secretaria", "Medico"};

		DefaultComboBoxModel<String> comboBoxModel =  new DefaultComboBoxModel<>(opciones);
		registrarUsuario = new RegistrarUsuario();
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(458, 103, 130, 26);
		contentPanel.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(304, 185, 61, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de usuario");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(304, 271, 105, 16);
		contentPanel.add(lblNewLabel_2);
		
		cbxTipoUsuario = new JComboBox(comboBoxModel);
		cbxTipoUsuario.setSelectedIndex(-1);
		cbxTipoUsuario.setBounds(458, 269, 138, 23);
		contentPanel.add(cbxTipoUsuario);
		
		JLabel lblNewLabel_3 = new JLabel("Si aún no tienes un usuario registrador, pulsa aquí:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(6, 421, 328, 16);
		contentPanel.add(lblNewLabel_3);
		
		JButton btnRegistrase = new JButton("Registrarse");
		btnRegistrase.setForeground(new Color(0, 0, 0));
		btnRegistrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				registrarUsuario.setModal(true);
				registrarUsuario.setVisible(true);
			}
		});
		btnRegistrase.setBounds(346, 416, 117, 29);
		contentPanel.add(btnRegistrase);
		
		JLabel lblNewLabel_4 = new JLabel("LOGIN");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(335, 16, 61, 16);
		contentPanel.add(lblNewLabel_4);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(458, 180, 130, 26);
		contentPanel.add(txtClave);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ingresar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String identificacion = txtIdentificacion.getText();
		                 String clave = new String(txtClave.getPassword());
		                 String tipoUsuario = cbxTipoUsuario.getSelectedItem().toString();
		                 
		                 boolean inicioSesionCorrecto = GuardarDatos.verificarInicioSesion(identificacion, clave, tipoUsuario);
		                 
		                 if (inicioSesionCorrecto) {
		                	 
		                	 dispose();
		                	 principal = new Principal(tipoUsuario);
		                	 principal.setUndecorated(true);
		                	 principal.setVisible(true);
		                	 principal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		                	 JOptionPane.showMessageDialog(Login.this, "Hola "+tipoUsuario+ ", Bienvenido a Clinica Sunny." , "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                 } else {
		                     System.out.println("Credenciales incorrectas o tipo de usuario incorrecto. Intente nuevamente.");
		                     JOptionPane.showMessageDialog(Login.this, "Credenciales incorrectas o tipo de usuario incorrecto. Intente nuevamente.", "Error", JOptionPane.INFORMATION_MESSAGE);
		                 }

		                 clearTxt();
		             }
					}
				);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void clearTxt() {
		txtIdentificacion.setText("");
		txtClave.setText("");
		cbxTipoUsuario.setSelectedIndex(-1);
		
		
	}
	

	
	
	
}
