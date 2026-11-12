package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import accesoDatos.*;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class ListarMedicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public  JTable TablaMedicos = new JTable();

	
	private final String[] columnNames = {"ID", "Nombre", "Cedula", "Especialidad", "Disponibilidad"};
	private JButton btnModificar;
	private JButton btnEliminar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarMedicos dialog = new ListarMedicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarMedicos() {
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		TablaMedicos.setModel(model);
		setBackground(new Color(112, 128, 144));
		setTitle("Listar médicos");
		setBounds(100, 100, 964, 722);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(6, 6, 952, 643);
		contentPanel.add(panel);
		panel.setLayout(null);
		TablaMedicos.setBounds(17, 18, 907, 607);
		panel.add(TablaMedicos);

		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("ELIMINAR");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = TablaMedicos.getSelectedRow();
						if (index>=0) {
							int option = JOptionPane.showConfirmDialog(null, "Estas seguro de que deseas eliminar al medico? ", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if(option == JOptionPane.OK_OPTION) {
					
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								
							}
						}
						
					}
				});
				btnEliminar.setEnabled(true);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				btnModificar = new JButton("MODIFICAR");
				btnModificar.setEnabled(true);
				btnModificar.setActionCommand("Cancel");
				buttonPane.add(btnModificar);
			}
		}
		JTableHeader header = TablaMedicos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12)); 
        header.setEnabled(false);
        header.setBackground(Color.DARK_GRAY); 
        header.setForeground(Color.WHITE); 
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TablaMedicos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		 JScrollPane scrollPane = new JScrollPane(TablaMedicos);
		 scrollPane.setEnabled(false);
	        scrollPane.setBounds(17, 18, 907, 606);
	        panel.add(scrollPane);
	        
	        TablaMedicos.addMouseListener(new MouseAdapter() {
	        	public void mouseClicked(MouseEvent arg0) {
	        		int index = TablaMedicos.getSelectedRow();
					
					if(index>=0) {
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
					}

						
	        	}
			});

	        CargarDatos.CargarConsulta(TablaMedicos, "Medicos");
	       
		
		 btnEliminar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int filaSeleccionada = TablaMedicos.getSelectedRow();

	                if (filaSeleccionada != -1) { 
	                    int idSeleccionado = (int) TablaMedicos.getValueAt(filaSeleccionada, 0);

	                  
	                    int eliminado = CargarDatos.EliminarRegistro("Medicos",idSeleccionado);

	         
	                    if (eliminado > 0) {
	                        JOptionPane.showMessageDialog(ListarMedicos.this, "Registro eliminado correctamente.");
	                        
	                  
	                		CargarDatos.CargarConsulta(TablaMedicos, "Medicos");
	                    } else {
	                        JOptionPane.showMessageDialog(ListarMedicos.this, "Hubo un problema al eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(ListarMedicos.this, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	                }
	            }
	     
	        });
		
		

	}
	
	



	 
	
}






