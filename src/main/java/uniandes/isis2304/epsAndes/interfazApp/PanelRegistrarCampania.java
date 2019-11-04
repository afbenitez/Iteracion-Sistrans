package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarCampania extends JPanel
{

	public PanelRegistrarCampania(InterfazEpsAndesApp interfaz)
	{
	
		JTextField nombre = new JTextField(15);
		JTextField fechaInicio = new JTextField(15);
		JTextField fechaFin = new JTextField(15);
		JTextField organizador = new JTextField(15);

		setLayout(new GridLayout(4,1));
		

		add(new JLabel("Nombre: "));
		add(nombre);

		add(new JLabel("Fecha Inicio: "));
		add(fechaInicio);
		
		add(new JLabel("Fecha Fin: "));
		add(fechaFin);
		
		add(new JLabel("Organizador "));
		add(organizador);
		



		int result = JOptionPane.showConfirmDialog(null, this, "Registrar campaña", JOptionPane.OK_CANCEL_OPTION);

		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarCampaniaDatos(nombre.getText(), fechaInicio.getText(), fechaFin.getText(), organizador.getText());
		}
	}
}
