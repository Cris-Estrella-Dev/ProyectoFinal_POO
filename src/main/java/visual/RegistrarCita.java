package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;
import accesoDatos.GuardarDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	 JTextField txtidmedico;
	private JButton btnGuardarCita;
	private String[] opciones;
	private JTextField txtnombreespeciaista;
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
		
		ImageIcon imageIcon = new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/REGISTRAR CITA AHORA SI-23-23.png");
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
			txtCedulaPaciente.setBounds(156, 133, 130, 26);
			contentPanel.add(txtCedulaPaciente);
			txtCedulaPaciente.setColumns(10);
		}
		{
			txtNombrePaciente = new JTextField();
			txtNombrePaciente.setBounds(156, 262, 130, 26);
			contentPanel.add(txtNombrePaciente);
			txtNombrePaciente.setColumns(10);
		}
		{
			txtTelefonoPaciente = new JTextField();
			txtTelefonoPaciente.setBounds(138, 383, 145, 26);
			contentPanel.add(txtTelefonoPaciente);
			txtTelefonoPaciente.setColumns(10);
		}
		{
			txtDireccionPaciente = new JTextField();
			txtDireccionPaciente.setBounds(125, 479, 179, 74);
			contentPanel.add(txtDireccionPaciente);
			txtDireccionPaciente.setColumns(10);
		}
		{
			
			cbxEspecialistas = new JComboBox();
			cbxEspecialistas.setBounds(138, 622, 179, 27);
			contentPanel.add(cbxEspecialistas);
			cbxEspecialistas.setSelectedItem(null);

		}
		{
			txtFechaCita = new JTextField();
			txtFechaCita.setBounds(156, 749, 130, 26);
			contentPanel.add(txtFechaCita);
			txtFechaCita.setColumns(10);
		}
		
		txtidmedico = new JTextField();
		txtidmedico.setVisible(false);
		txtidmedico.setBounds(28, 210, 61, 26);
		contentPanel.add(txtidmedico);
		txtidmedico.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				{

					btnGuardarCita = new JButton("Guardar");
					buttonPane.add(btnGuardarCita);
					btnGuardarCita.setActionCommand("OK");
					getRootPane().setDefaultButton(btnGuardarCita);
				}
				
				btnGuardarCita.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				     
				        
				        String cedula = txtCedulaPaciente.getText();
				        String nombre = txtNombrePaciente.getText();
				        String telefono = txtTelefonoPaciente.getText();
				        String direccion = txtDireccionPaciente.getText();
				        String fecha = txtFechaCita.getText();
				        int idmedico = Integer.parseInt(txtidmedico.getText());
				       
				        if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || fecha.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrarCita.this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Evita continuar con el inicio de sesión si hay campos vacíos.
                }
				        
				        GuardarDatos.insertarDatosCita(cedula, nombre, telefono, direccion, fecha, idmedico);
				        JOptionPane.showMessageDialog(RegistrarCita.this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				        	
				    }
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
		

		CargarDatos.CargarComboBox(cbxEspecialistas);
		
		JButton btnbuscarespecialista = new JButton("Buscar especialista");
		btnbuscarespecialista.setBounds(335, 621, 168, 29);
		contentPanel.add(btnbuscarespecialista);
		btnbuscarespecialista.setActionCommand("OK");
		
		btnbuscarespecialista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEspecialista = (String) cbxEspecialistas.getSelectedItem();
                ListaEspecialista showEspecialistaForm = new ListaEspecialista(selectedEspecialista);
                dispose();
                showEspecialistaForm.setVisible(true);
            }
        });
		
		txtnombreespeciaista = new JTextField();
		txtnombreespeciaista.setColumns(10);
		txtnombreespeciaista.setBounds(537, 621, 130, 26);
		contentPanel.add(txtnombreespeciaista);
		
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(-79, -11, 1248, 944);
		contentPanel.add(lblNewLabel);
		
	}
	
	public void clearTxt() {
		txtCedulaPaciente.setText("");
		txtNombrePaciente.setText("");
		txtCedulaPaciente.setText("");
		txtDireccionPaciente.setText("");
		txtFechaCita.setText("");
		txtTelefonoPaciente.setText("");
		cbxEspecialistas.setSelectedIndex(-1);
		
		
	}
	
	public void agregardatostextbox(String id, String nombre) {
		
		txtidmedico.setText(id);
		txtnombreespeciaista.setText(nombre);
		
	}
	}

