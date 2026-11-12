package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
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

		 String fontPath = "/Users/cristianbenelyon/Library/Fonts/GothamBold.ttf";
		 ImageIcon imageIcon = new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/LOGIN SCREEN WITHOUT TEXT-08 2-08.png");
	     Font customFont = loadFontFromFile(fontPath);
		
		setBackground(new Color(112, 128, 144));
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
		setBounds(100, 100, 697, 386);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Color miColor = Color.decode("#086992");
		String[] opciones = {"Admin", "Secretaria", "Medico"};
		String hintTextIdentificacion = "IDENTIFICACION";
		String hintTextClave = "CONTRASEÑA";
		DefaultComboBoxModel<String> comboBoxModel =  new DefaultComboBoxModel<>(opciones);

		txtIdentificacion = new JTextField(hintTextIdentificacion,20);
		txtIdentificacion.setForeground(Color.LIGHT_GRAY);
		txtIdentificacion.setBounds(174, 111, 242, 32);
		txtIdentificacion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				 if (txtIdentificacion.getText().equals(hintTextIdentificacion)) {
	                    txtIdentificacion.setText("");
	                    txtIdentificacion.setForeground(Color.WHITE);
	                }
			}
			@Override
			public void focusLost(FocusEvent e) {
				 if (txtIdentificacion.getText().isEmpty()) {
	                    txtIdentificacion.setText(hintTextIdentificacion);
	                    txtIdentificacion.setForeground(Color.LIGHT_GRAY); 
	                }
			}
		});
		contentPanel.setLayout(null);

		txtIdentificacion.setBackground(new java.awt.Color(0,0,0,1));
		txtIdentificacion.setBorder(null);
		txtIdentificacion.setFont(new Font("Dialog", Font.PLAIN, 18));
	
		txtIdentificacion.setColumns(20);
		contentPanel.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		cbxTipoUsuario = new JComboBox(comboBoxModel);
		cbxTipoUsuario.setBounds(167, 197, 231, 38);
		cbxTipoUsuario.setBorder(null);
		cbxTipoUsuario.setSelectedIndex(-1);
		contentPanel.add(cbxTipoUsuario);

		txtClave = new JPasswordField(hintTextClave,20);
		txtClave.setEchoChar((char) 0);
		txtClave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(txtClave.getPassword()).equals(hintTextClave)) {
                   txtClave.setText("");
                   txtClave.setForeground(Color.WHITE);
                   txtClave.setEchoChar('*'); // Restaurar el echoChar para ocultar el texto ingresado
               }
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(txtClave.getPassword()).isEmpty()) {
                   txtClave.setText(hintTextClave);
                   txtClave.setForeground(Color.LIGHT_GRAY);
                   txtClave.setEchoChar((char) 0); // Para que se muestre como en un JTextField
               }
			}
		});
		
		txtClave.setForeground(Color.LIGHT_GRAY);
		txtClave.setBounds(174, 153, 225, 32);
		txtClave.setBorder(null);
		txtClave.setBackground(new java.awt.Color(0,0,0,1));
		txtClave.setFont(new Font("Dialog", Font.PLAIN, 18));
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
		                    String tipoUsuario = Integer.toString(cbxTipoUsuario.getSelectedIndex());
		                    
		                    if(identificacion.length() == 11) {
			                	tipoUsuario = cbxTipoUsuario.getSelectedItem().toString();
			                    GuardarDatos.insertarDatosUsuario(identificacion, clave, tipoUsuario);
			                    JOptionPane.showMessageDialog(RegistrarUsuario.this, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                    	
		                    }else {
		                    	if (identificacion.isEmpty() || clave.isEmpty() || cbxTipoUsuario.getSelectedIndex()<=-1) {
			                    	JOptionPane.showMessageDialog(RegistrarUsuario.this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				                     return; // No continúes con el proceso de inicio de sesión
				                 }else {
					                    JOptionPane.showMessageDialog(RegistrarUsuario.this, "la cedula debe tener 11 caracteres", "Fail", JOptionPane.INFORMATION_MESSAGE);
									
								}
							}
		                    
		                   

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
						
						logIn.setVisible(true);
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(-6, 0, 707, 395);
		contentPanel.add(lblNewLabel);
	}
	
	public void clearTxt() {
		txtIdentificacion.setText("");
		txtClave.setText("");
		cbxTipoUsuario.setSelectedIndex(-1);
		
		
	}
	public static Font loadFontFromFile(String path) {
        try {
            File fontFile = new File(path);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
           
            return new Font("Arial", Font.PLAIN, 12);
        }
    }
}