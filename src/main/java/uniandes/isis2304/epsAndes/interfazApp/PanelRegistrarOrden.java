package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarOrden extends JPanel
{
	public PanelRegistrarOrden(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField fecha = new JTextField(15);
		JTextField idMedico = new JTextField(15);
		JTextField idUsuario = new JTextField(15);
		JTextField idServicio = new JTextField(15);
		JTextField medicamentos = new JTextField(15);
		
		
		setLayout(new GridLayout(6,1));
		add(new JLabel("Identificador: "));
		add(id);
		
		add(new JLabel("Fecha: "));
		add(fecha);
		
		add(new JLabel("Identificador medico: "));
		add(idMedico);
		
		add(new JLabel("Identificador usuario: "));
		add(idUsuario);
		
		add(new JLabel("Identificador servicio: "));
		add(idServicio);
		
		add(new JLabel("Medicamentos: "));
		add(medicamentos);
		

	
		int result = JOptionPane.showConfirmDialog(null, this, "Registrar Orden", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarOrdenDatos(Long.parseLong(id.getText()), fecha.getText(), Long.parseLong(idMedico.getText()), Long.parseLong(idUsuario.getText()), idServicio.getText(), medicamentos.getText());
		}
	}
}
