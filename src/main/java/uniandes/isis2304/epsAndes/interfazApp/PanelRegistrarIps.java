package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarIps extends JPanel
{

	public PanelRegistrarIps(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField nombre = new JTextField(15);
		
		JTextField ubicacion = new JTextField(15);
		

		setLayout(new GridLayout(4,1));
		add(new JLabel("Identificador: "));
		add(id);

		add(new JLabel("Nombre: "));
		add(nombre);

		add(new JLabel("Ubicacion: "));
		add(ubicacion);


		add(new JLabel("Tipo Ips: "));
		JComboBox combo1 = new JComboBox();
		combo1.addItem("HOSPITAL");
		combo1.addItem("CENTRO DIAGNOSTICO");
		combo1.addItem("LABORATORIO");
		combo1.addItem("CENTRO MEDICO");
		add(combo1);

	
		int result = JOptionPane.showConfirmDialog(null, this, "Registrar ips", JOptionPane.OK_CANCEL_OPTION);

		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarIpsDatos(Long.parseLong(id.getText()), nombre.getText(),combo1.getSelectedItem().toString(), ubicacion.getText());
		}
	}
}