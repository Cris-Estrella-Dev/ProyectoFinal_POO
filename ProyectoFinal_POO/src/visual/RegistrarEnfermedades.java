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
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegistrarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreEnfermedad;
	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	private JButton btnGuardar;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEnfermedades dialog = new RegistrarEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarEnfermedades() {
		setBounds(100, 100, 438, 249);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre de la enfermedad:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(24, 6, 172, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombreEnfermedad = new JTextField();
			txtNombreEnfermedad.setBounds(211, 1, 203, 26);
			contentPanel.add(txtNombreEnfermedad);
			txtNombreEnfermedad.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Crónica:");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(24, 79, 61, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			rdbtnSi = new JRadioButton("Si");
			rdbtnSi.setForeground(new Color(255, 255, 255));
			rdbtnSi.setBounds(104, 75, 51, 23);
			contentPanel.add(rdbtnSi);
		}
		{
			rdbtnNo = new JRadioButton("No");
			rdbtnNo.setForeground(new Color(255, 255, 255));
			rdbtnNo.setBounds(171, 75, 51, 23);
			contentPanel.add(rdbtnNo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
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
		
		btnGuardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        
		        String nombre = txtNombreEnfermedad.getText();
		       String answer = rdbtnSi.getText();
		        
		        GuardarDatos.insertarDatosEnfermedad(nombre, answer);
		        JOptionPane.showMessageDialog(RegistrarEnfermedades.this, "Enfermedad registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);


		    }
		});
	}

}
