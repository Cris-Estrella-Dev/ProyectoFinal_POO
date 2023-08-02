package visual;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private RegistrarUsuario registrarUsuario;
	private JTextField txtIdentificacion;
	private JComboBox cbxTipoUsuario;
	private String[] opciones;
	private JPasswordField txtClave;
	private Principal principal;
	private JButton btnSalir;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public Login() {


	
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
		registrarUsuario = new RegistrarUsuario();
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
		
		JButton okButton = new JButton("");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String identificacion = txtIdentificacion.getText();
                 String clave = new String(txtClave.getPassword());
                 String tipoUsuario = Integer.toString(cbxTipoUsuario.getSelectedIndex());
                 boolean inicioSesionCorrecto = false;
                 
                 if (identificacion.isEmpty() || clave.isEmpty() || cbxTipoUsuario.getSelectedIndex()<=-1 || identificacion.length() != 11) {
                     JOptionPane.showMessageDialog(Login.this, "Todos los campos deben de estar llenos y el campo identificacion debe tener 11 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                     return; // Evita continuar con el inicio de sesión si hay campos vacíos.
                 }else {
                	  
                	  tipoUsuario = cbxTipoUsuario.getSelectedItem().toString();
	                  inicioSesionCorrecto = GuardarDatos.verificarInicioSesion(identificacion, clave, tipoUsuario);
	                  CargarDatos.identificarMedico(identificacion);

				}

                
                 
                 
                
                 
                 if (inicioSesionCorrecto) {
                	 
                	 dispose();
                	 principal = new Principal(tipoUsuario);
                	 principal.setUndecorated(true);
                	 principal.setVisible(true);
                	 principal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                	 JOptionPane.showMessageDialog(Login.this, "Hola "+tipoUsuario+ ", Bienvenido a Clinica Sunny." , "Éxito", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                    
                     JOptionPane.showMessageDialog(Login.this, "Credenciales incorrectas o tipo de usuario incorrecto. Intente nuevamente.", "Error", JOptionPane.INFORMATION_MESSAGE);
                     return;
                 }

                 clearTxt();
             }
			
			
		});
		okButton.setBounds(212, 247, 117, 46);
		okButton.setBorder(null);
		okButton.setActionCommand("OK");
		contentPanel.add(okButton);
		
		JButton btnRegistrase = new JButton("");
		btnRegistrase.setBounds(490, 266, 117, 46);
		btnRegistrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();

				registrarUsuario.setVisible(true);
			}
		});
		btnRegistrase.setBorder(null);
		btnRegistrase.setForeground(Color.BLACK);
		contentPanel.add(btnRegistrase);
		
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(-6, 0, 707, 395);
		contentPanel.add(lblNewLabel);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(655, 0, 46, 32);
		btnSalir.setActionCommand("Cancel");
		contentPanel.add(btnSalir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setVisible(false);
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
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
