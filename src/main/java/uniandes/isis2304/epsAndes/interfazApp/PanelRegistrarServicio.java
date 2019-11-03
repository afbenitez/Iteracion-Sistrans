package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarServicio extends JPanel
{

	public PanelRegistrarServicio(InterfazEpsAndesApp interfaz)
	{
		JTextField dia = new JTextField(1);
		JTextField horario = new JTextField(2);
		JTextField idServicio = new JTextField(15);
		JTextField idIps = new JTextField(15);
		JTextField capacidad = new JTextField(15);
		JTextField capacidadMax = new JTextField(15);
		JTextField estado = new JTextField(2);

		setLayout(new GridLayout(7,1));
		add(new JLabel("Dia: "));
		add(dia);

		add(new JLabel("Horario: "));
		add(horario);

		add(new JLabel("Identificador servicio: "));
		add(idServicio);

		add(new JLabel("Identificador ips: "));
		add(idIps);
		
		add(new JLabel("Capacidad: "));
		add(capacidad);
		
		add(new JLabel("Capacidad maxima: "));
		add(capacidadMax);
		
		add(new JLabel("Estado: "));
		add(estado);
		


		int result = JOptionPane.showConfirmDialog(null, this, "Registrar servicio para la ips", JOptionPane.OK_CANCEL_OPTION);

		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarServicioPrestadoDatos(Integer.parseInt(dia.getText()), Integer.parseInt(horario.getText()), idServicio.getText(), idIps.getText(),
					Long.parseLong(capacidad.getText()), Long.parseLong(capacidadMax.getText()), Integer.parseInt(estado.getText()));
		}
	}
}
