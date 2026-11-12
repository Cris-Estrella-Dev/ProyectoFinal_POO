package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JTextField txtAlergicoA;
	private JTextField txtAntecedentesPatologicos;
	private JTextField txtSufreDeAlgo;
	private JTextField txtOperadoDe;
	private JTextField txtHabitosToxicos;
	private JTextField txtMotivoCita;
	private JTextField txtDerivarEspecialista;
	private ListarVacunas listarVacunas;
	private ListarEnfermedades listarEnfermedades;
	private HistorialPaciente historialPaciente;
	private JLabel lblNombrePaciente;
	private JLabel lblTelefonoPaciente;
	private JLabel lblDireccionPaciente;
	private JLabel lblFechaCitaPaciente;
	public JTextField txtidvacunas;
	public JTextField txtnombrevacuna;
	private JTextField txtenfermedades;
	private JTextField txtidenfermedad;
	private JTextField txtfechaconsulta;
	private JButton btnNewButton;
	private JTextField txtcedula;
	
	private  String nombre1;
	private  String telefono1;
	private  String direccion1;
	private  String fechacita1;
	private  String cedula1;
	
	private final String nombre = nombre1;
	private final String telefono = telefono1;
	private final String direccion = direccion1;
	private final String fechacita = fechacita1;
	private final String cedula = cedula1;
	


	
	public static void main(String[] args) {
		try {
			Consultas dialog = new Consultas();
			dialog.setUndecorated(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consultas() {
		ImageIcon imageIcon = new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/INTENTA CON ESTE BRO-20.png");
		setTitle("Consultas");
		setBounds(100, 100, 1194, 962);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("NOMBRE:");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(28, 182, 78, 61);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO:");
			lblTelefono.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblTelefono.setForeground(new Color(255, 255, 255));
			lblTelefono.setBounds(326, 204, 100, 16);
			contentPanel.add(lblTelefono);
		}
		{
			JLabel lblDireccion = new JLabel("DIRECCION:");
			lblDireccion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblDireccion.setForeground(new Color(255, 255, 255));
			lblDireccion.setBounds(587, 204, 100, 16);
			contentPanel.add(lblDireccion);
		}
		{
			JLabel lblFechaDeLa = new JLabel("FECHA DE LA CITA:");
			lblFechaDeLa.setForeground(new Color(255, 255, 255));
			lblFechaDeLa.setBounds(912, 205, 132, 16);
			contentPanel.add(lblFechaDeLa);
		}
		{
			 lblNombrePaciente = new JLabel("");
			 lblNombrePaciente.setForeground(new Color(255, 255, 255));
			lblNombrePaciente.setBounds(107, 204, 190, 26);
			contentPanel.add(lblNombrePaciente);
		}
		{
			lblTelefonoPaciente = new JLabel("");
			lblTelefonoPaciente.setForeground(new Color(255, 255, 255));
			lblTelefonoPaciente.setBounds(416, 204, 159, 16);
			contentPanel.add(lblTelefonoPaciente);
		}
		{
			lblDireccionPaciente = new JLabel("");
			lblDireccionPaciente.setForeground(new Color(255, 255, 255));
			lblDireccionPaciente.setBounds(682, 170, 218, 86);
			contentPanel.add(lblDireccionPaciente);
		}
		{
			lblFechaCitaPaciente = new JLabel("");
			lblFechaCitaPaciente.setForeground(new Color(255, 255, 255));
			lblFechaCitaPaciente.setBounds(1037, 204, 132, 16);
			contentPanel.add(lblFechaCitaPaciente);
		}
		
		
		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setBorder(null);
		txtEdadPaciente.setBounds(582, 684, 202, 26);
		contentPanel.add(txtEdadPaciente);
		txtEdadPaciente.setColumns(10);
		
		JRadioButton rdbtnSexoMasculino = new JRadioButton("");
		rdbtnSexoMasculino.setBorder(null);
		rdbtnSexoMasculino.setForeground(new Color(255, 255, 255));
		rdbtnSexoMasculino.setBounds(688, 759, 33, 23);
		contentPanel.add(rdbtnSexoMasculino);
		
		JRadioButton rdbtnSexoFemenino = new JRadioButton("");
		rdbtnSexoFemenino.setBorder(null);
		rdbtnSexoFemenino.setForeground(new Color(255, 255, 255));
		rdbtnSexoFemenino.setBounds(936, 759, 23, 23);
		contentPanel.add(rdbtnSexoFemenino);
		
		txtAlergicoA = new JTextField();
		txtAlergicoA.setBorder(null);
		txtAlergicoA.setBounds(582, 471, 252, 26);
		contentPanel.add(txtAlergicoA);
		txtAlergicoA.setColumns(10);
		
		txtAntecedentesPatologicos = new JTextField();
		txtAntecedentesPatologicos.setBorder(null);
		txtAntecedentesPatologicos.setBounds(582, 541, 427, 26);
		contentPanel.add(txtAntecedentesPatologicos);
		txtAntecedentesPatologicos.setColumns(10);
		
		txtSufreDeAlgo = new JTextField();
		txtSufreDeAlgo.setBorder(null);
		txtSufreDeAlgo.setBounds(582, 327, 301, 26);
		contentPanel.add(txtSufreDeAlgo);
		txtSufreDeAlgo.setColumns(10);
		
		txtOperadoDe = new JTextField();
		txtOperadoDe.setBorder(null);
		txtOperadoDe.setBounds(582, 396, 208, 26);
		contentPanel.add(txtOperadoDe);
		txtOperadoDe.setColumns(10);
		
		txtHabitosToxicos = new JTextField();
		txtHabitosToxicos.setBorder(null);
		txtHabitosToxicos.setBounds(582, 612, 446, 26);
		contentPanel.add(txtHabitosToxicos);
		txtHabitosToxicos.setColumns(10);
		
		txtMotivoCita = new JTextField();
		txtMotivoCita.setBorder(null);
		txtMotivoCita.setBounds(49, 405, 427, 139);
		contentPanel.add(txtMotivoCita);
		txtMotivoCita.setColumns(10);
		
		JButton btnListaVacunas = new JButton("");
		btnListaVacunas.setBorder(null);
		btnListaVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listarVacunas = new ListarVacunas();
				listarVacunas.setVisible(true);
				dispose();
			}
		});
		btnListaVacunas.setBounds(416, 601, 88, 61);
		contentPanel.add(btnListaVacunas);
		
		JButton btnVerListaEnfermedades = new JButton("");
		btnVerListaEnfermedades.setBorder(null);
		btnVerListaEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				listarEnfermedades = new ListarEnfermedades();
				listarEnfermedades.setVisible(true);
				
				ListarEnfermedades.setCampo1(txtnombrevacuna.getText());
				ListarEnfermedades.setCampo2(txtidvacunas.getText());
				PerfilMedico permedico = new PerfilMedico();
				
				agregardatoscitas(nombre, telefono, direccion, fechacita, cedula);
				dispose();
			}
		});
		btnVerListaEnfermedades.setBounds(416, 684, 83, 61);
		contentPanel.add(btnVerListaEnfermedades);
		historialPaciente = new HistorialPaciente();
		
		txtDerivarEspecialista = new JTextField();
		txtDerivarEspecialista.setBounds(30, 828, 374, 26);
		contentPanel.add(txtDerivarEspecialista);
		txtDerivarEspecialista.setColumns(10);
		
		txtidvacunas = new JTextField();
		txtidvacunas.setBounds(505, 612, 33, 26);
		contentPanel.add(txtidvacunas);
		txtidvacunas.setColumns(10);
		
		txtnombrevacuna = new JTextField();
		txtnombrevacuna.setBounds(32, 655, 372, 26);
		contentPanel.add(txtnombrevacuna);
		txtnombrevacuna.setColumns(10);
		
		txtenfermedades = new JTextField();
		txtenfermedades.setBounds(30, 738, 374, 26);
		contentPanel.add(txtenfermedades);
		txtenfermedades.setColumns(10);
		
		txtidenfermedad = new JTextField();
		txtidenfermedad.setBounds(511, 701, 23, 26);
		contentPanel.add(txtidenfermedad);
		txtidenfermedad.setColumns(10);
		
		txtfechaconsulta = new JTextField();
		txtfechaconsulta.setColumns(10);
		txtfechaconsulta.setBounds(742, 780, 182, 26);
		contentPanel.add(txtfechaconsulta);
		
		JLabel lblNewLabel_12 = new JLabel(imageIcon);
		lblNewLabel_12.setBounds(-47, -29, 1285, 956);
		contentPanel.add(lblNewLabel_12);
		{
			JButton cancelButton = new JButton("");
			cancelButton.setBorder(null);
			cancelButton.setBounds(552, 847, 301, 42);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					boolean existe = CargarDatos.existeRegistro(txtcedula.getText());
					if (existe) {
						//aqui va la logica si resulta que existe el paciente, roba los datos que ya cargaste aqui + la cedula y haz un inser to con eso
						//para que esta logica siempre funcione las secretaria debe trabajar con cedula de verdad y si la misma gente hizo varias cita debe de ser la misma cedula
						historialPaciente.setVisible(true);

					}else {
	                    JOptionPane.showMessageDialog(Consultas.this, "El paciente no esta registrado", "Error", JOptionPane.ERROR_MESSAGE);

					}
					
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton okButton = new JButton("");
			okButton.setBorder(null);
			okButton.setBounds(875, 847, 281, 36);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			
			btnNewButton = new JButton("");
			btnNewButton.setBorder(null);
			btnNewButton.setBounds(1119, 6, 69, 49);
			contentPanel.add(btnNewButton);
			Date fechaActual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
			txtfechaconsulta.setText(formatoFecha.format(fechaActual));
			
			txtcedula = new JTextField();
			txtcedula.setBounds(30, 226, 130, 26);
			contentPanel.add(txtcedula);
			txtcedula.setColumns(10);
			
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					 	int idenfermedad = Integer.parseInt(txtidenfermedad.getText());
				        int idvacuna = Integer.parseInt(txtidenfermedad.getText());
				        int idcita = 1;
				        String fecha = txtfechaconsulta.getText();
				      
				       
				        if (txtidenfermedad.getText().isEmpty() || txtidenfermedad.getText().isEmpty() || fecha.isEmpty()) {
		                    JOptionPane.showMessageDialog(Consultas.this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		                    return; // Evita continuar con el inicio de sesión si hay campos vacíos.
		                }
				        
				        boolean existe = CargarDatos.existeRegistro(txtcedula.getText());
						if (!existe) {
							
							GuardarDatos.insertarDatospaciente(lblNombrePaciente.getText(), txtcedula.getText(), lblDireccionPaciente.getText(), lblTelefonoPaciente.getText());
					        JOptionPane.showMessageDialog(Consultas.this, "Este paciente no habias sido agregado, asi que fue agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

						}
				        
				        GuardarDatos.insertarDatosConsulta(idcita, idenfermedad , idvacuna, fecha);;
				        JOptionPane.showMessageDialog(Consultas.this, "consulta registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			
			
			
		}
	}
	
	
	public void agregardatoscitas(String nombre, String telefono, String direccion, String fechacita, String cedula) {
		
		lblNombrePaciente.setText(nombre);
		lblTelefonoPaciente.setText(telefono);
		lblDireccionPaciente.setText(direccion);
		lblFechaCitaPaciente.setText(fechacita);
		txtcedula.setText(cedula);
		
		
	}
	
	public void agregardatosvacuna(String nombre, String idvacuna) {
		txtnombrevacuna.setText(nombre);
		txtidvacunas.setText(idvacuna);
		
	}
	
	public void agregardatosenfermedad(String nombre, String idenfermo) {
		txtenfermedades.setText(nombre);
		txtidenfermedad.setText(idenfermo);
		
	}
}
