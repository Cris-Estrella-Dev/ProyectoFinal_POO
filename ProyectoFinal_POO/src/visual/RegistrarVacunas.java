package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.GuardarDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegistrarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JButton btnGuardarVacuna;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarVacunas dialog = new RegistrarVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarVacunas() {
		setTitle("Registrar vacuna");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre de la vacuna:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(21, 25, 141, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			 textField = new JTextField();
			textField.setBounds(163, 20, 208, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardarVacuna = new JButton("Guardar");
				btnGuardarVacuna.setActionCommand("OK");
				buttonPane.add(btnGuardarVacuna);
				getRootPane().setDefaultButton(btnGuardarVacuna);
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
		
		btnGuardarVacuna.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        
		        String nombre = textField.getText();
		       
		        if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrarVacunas.this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Evita continuar con el inicio de sesión si hay campos vacíos.
                }
		        GuardarDatos.insertarDatosVacuna(nombre);
		        JOptionPane.showMessageDialog(RegistrarVacunas.this, "Vacuna registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        clearTxt();

		    }
		});
	}
	
	public void clearTxt() {
		textField.setText("");
	}

}
