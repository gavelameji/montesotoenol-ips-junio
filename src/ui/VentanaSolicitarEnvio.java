package ui;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import data.CrudOficinas;
import model.SolicitudController;
import model.dto.ClienteDTO;
import model.dto.OficinaDTO;
import model.dto.PaqueteDTO;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaSolicitarEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelAlta;
	private JPanel panelSolicitud;
	private JCheckBox cbAlta;
	private JLabel lbDni;
	private JLabel lbNombre;
	private JLabel lbTelefono;
	private JLabel lbDomicilio;
	private JTextField tfDni;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField tfDomicilio;
	private JLabel lbSolicitud;
	private JLabel lbDescripcion;
	private JTextField tfDescripcion;
	private JLabel lbDniFormulario;
	private JTextField tfDniFormulario;
	private JLabel lbDireccionOrigen;
	private JTextField tfDireccionOrigen;
	private JCheckBox cbDomicilio;
	private JLabel lbOficina;
	private JComboBox<OficinaDTO> cbOficinas;
	private JLabel lbDireccionDestino;
	private JTextField tfDireccionDestino;
	private JButton btSolicitar;
	
	private SolicitudController sc;
	private List<OficinaDTO> oficinas;

	/**
	 * Create the frame.
	 */
	public VentanaSolicitarEnvio() {
		sc = new SolicitudController();
		oficinas = CrudOficinas.obtenerOficinas();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelAlta());
		contentPane.add(getPanelSolicitud());
		contentPane.add(getCbAlta());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		asociarOficinaADireccion();
	}

	private JPanel getPanelAlta() {
		if (panelAlta == null) {
			panelAlta = new JPanel();
			panelAlta.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panelAlta.setBounds(6, 35, 737, 115);
			panelAlta.setLayout(null);
			panelAlta.add(getLbDni());
			panelAlta.add(getLbNombre());
			panelAlta.add(getLbTelefono());
			panelAlta.add(getLbDomicilio());
			panelAlta.add(getTfDni());
			panelAlta.add(getTfNombre());
			panelAlta.add(getTfTelefono());
			panelAlta.add(getTfDomicilio());
		}
		return panelAlta;
	}
	private JPanel getPanelSolicitud() {
		if (panelSolicitud == null) {
			panelSolicitud = new JPanel();
			panelSolicitud.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panelSolicitud.setBounds(6, 161, 737, 301);
			panelSolicitud.setLayout(null);
			panelSolicitud.add(getLbSolicitud());
			panelSolicitud.add(getLbDescripcion());
			panelSolicitud.add(getTfDescripcion());
			panelSolicitud.add(getLbDniFormulario());
			panelSolicitud.add(getTfDniFormulario());
			panelSolicitud.add(getLbDireccionOrigen());
			panelSolicitud.add(getTfDireccionOrigen());
			panelSolicitud.add(getCbDomicilio());
			panelSolicitud.add(getLbOficina());
			panelSolicitud.add(getCbOficinas());
			panelSolicitud.add(getLbDireccionDestino());
			panelSolicitud.add(getTfDireccionDestino());
			panelSolicitud.add(getBtSolicitar());
		}
		return panelSolicitud;
	}
	private JCheckBox getCbAlta() {
		if (cbAlta == null) {
			cbAlta = new JCheckBox("Deseo darme de alta como usuario");
			cbAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarPanelCliente();
				}
			});
			cbAlta.setBounds(6, 5, 283, 23);
		}
		return cbAlta;
	}
	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("DNI:");
			lbDni.setBounds(10, 11, 28, 14);
		}
		return lbDni;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre y Apellidos: ");
			lbNombre.setBounds(10, 50, 105, 14);
		}
		return lbNombre;
	}
	private JLabel getLbTelefono() {
		if (lbTelefono == null) {
			lbTelefono = new JLabel("Nº De Teléfono:");
			lbTelefono.setBounds(10, 90, 105, 14);
		}
		return lbTelefono;
	}
	private JLabel getLbDomicilio() {
		if (lbDomicilio == null) {
			lbDomicilio = new JLabel("Domicilio, Ciudad:");
			lbDomicilio.setBounds(362, 28, 105, 14);
		}
		return lbDomicilio;
	}
	private JTextField getTfDni() {
		if (tfDni == null) {
			tfDni = new JTextField();
			tfDni.setEnabled(false);
			tfDni.setBounds(122, 8, 209, 20);
			tfDni.setColumns(10);
		}
		return tfDni;
	}
	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setEnabled(false);
			tfNombre.setColumns(10);
			tfNombre.setBounds(122, 47, 209, 20);
		}
		return tfNombre;
	}
	private JTextField getTfTelefono() {
		if (tfTelefono == null) {
			tfTelefono = new JTextField();
			tfTelefono.setEnabled(false);
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(122, 87, 209, 20);
		}
		return tfTelefono;
	}
	private JTextField getTfDomicilio() {
		if (tfDomicilio == null) {
			tfDomicilio = new JTextField();
			tfDomicilio.setEnabled(false);
			tfDomicilio.setBounds(360, 47, 367, 20);
			tfDomicilio.setColumns(10);
		}
		return tfDomicilio;
	}
	private JLabel getLbSolicitud() {
		if (lbSolicitud == null) {
			lbSolicitud = new JLabel("Formulario de solicitud de envío: ");
			lbSolicitud.setBounds(10, 11, 319, 14);
		}
		return lbSolicitud;
	}
	private JLabel getLbDescripcion() {
		if (lbDescripcion == null) {
			lbDescripcion = new JLabel("Descripción Del Envío:");
			lbDescripcion.setBounds(337, 35, 176, 14);
		}
		return lbDescripcion;
	}
	private JTextField getTfDescripcion() {
		if (tfDescripcion == null) {
			tfDescripcion = new JTextField();
			tfDescripcion.setBounds(337, 54, 390, 20);
			tfDescripcion.setColumns(10);
		}
		return tfDescripcion;
	}
	private JLabel getLbDniFormulario() {
		if (lbDniFormulario == null) {
			lbDniFormulario = new JLabel("DNI de Emisor:");
			lbDniFormulario.setBounds(10, 35, 159, 14);
		}
		return lbDniFormulario;
	}
	private JTextField getTfDniFormulario() {
		if (tfDniFormulario == null) {
			tfDniFormulario = new JTextField();
			tfDniFormulario.setColumns(10);
			tfDniFormulario.setBounds(10, 54, 292, 20);
		}
		return tfDniFormulario;
	}
	private JLabel getLbDireccionOrigen() {
		if (lbDireccionOrigen == null) {
			lbDireccionOrigen = new JLabel("Dirección De Origen:");
			lbDireccionOrigen.setBounds(10, 114, 176, 14);
		}
		return lbDireccionOrigen;
	}
	private JTextField getTfDireccionOrigen() {
		if (tfDireccionOrigen == null) {
			tfDireccionOrigen = new JTextField();
			tfDireccionOrigen.setEnabled(false);
			tfDireccionOrigen.setBounds(10, 139, 292, 20);
			tfDireccionOrigen.setColumns(10);
		}
		return tfDireccionOrigen;
	}
	private JCheckBox getCbDomicilio() {
		if (cbDomicilio == null) {
			cbDomicilio = new JCheckBox("Recogida A Domicilio");
			cbDomicilio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarSeleccionDeOficina();
				}
			});
			cbDomicilio.setBounds(10, 84, 216, 23);
		}
		return cbDomicilio;
	}
	private JLabel getLbOficina() {
		if (lbOficina == null) {
			lbOficina = new JLabel("Oficina De Origen:");
			lbOficina.setBounds(337, 114, 176, 14);
		}
		return lbOficina;
	}
	private JComboBox<OficinaDTO> getCbOficinas() {
		if (cbOficinas == null) {
			DefaultComboBoxModel<OficinaDTO> comboBoxModel = new DefaultComboBoxModel<>();
	        for (OficinaDTO o : oficinas) {
	            comboBoxModel.addElement(o);
	        }
			cbOficinas = new JComboBox<OficinaDTO>(comboBoxModel);
			cbOficinas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					asociarOficinaADireccion();
				}
			});
			cbOficinas.setBounds(337, 137, 390, 22);
		}
		return cbOficinas;
	}
	private JLabel getLbDireccionDestino() {
		if (lbDireccionDestino == null) {
			lbDireccionDestino = new JLabel("Dirección De Destino:");
			lbDireccionDestino.setBounds(10, 179, 176, 14);
		}
		return lbDireccionDestino;
	}
	private JTextField getTfDireccionDestino() {
		if (tfDireccionDestino == null) {
			tfDireccionDestino = new JTextField();
			tfDireccionDestino.setColumns(10);
			tfDireccionDestino.setBounds(10, 201, 292, 20);
		}
		return tfDireccionDestino;
	}
	
	private JButton getBtSolicitar() {
		if (btSolicitar == null) {
			btSolicitar = new JButton("Solicitar Envío");
			btSolicitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registrar();
				}
			});
			btSolicitar.setBounds(596, 267, 131, 23);
		}
		return btSolicitar;
	}
	
	// Comportamiento
	
	
	private void activarPanelCliente() {
		if(getCbAlta().isSelected()) {
			getTfDni().setEnabled(true);
			getTfNombre().setEnabled(true);
			getTfTelefono().setEnabled(true);
			getTfDomicilio().setEnabled(true);
		} else {
			getTfDni().setEnabled(false);
			getTfNombre().setEnabled(false);
			getTfTelefono().setEnabled(false);
			getTfDomicilio().setEnabled(false);
		}
	}
	
	private void activarSeleccionDeOficina() {
		if(getCbDomicilio().isSelected()) {
			getCbOficinas().setEnabled(false);
			getTfDireccionOrigen().setEnabled(true);
		} else {
			getCbOficinas().setEnabled(true);
			getTfDireccionOrigen().setEnabled(false);
		}
	}
	
	private void asociarOficinaADireccion() {
		getTfDireccionOrigen().setText(((OficinaDTO)(cbOficinas.getSelectedItem())).getDireccion());
	}
	private ClienteDTO registrarCliente() {
		
		String dni = getTfDni().getText();
		String nombre = getTfNombre().getText();
		String telefono = getTfTelefono().getText();
		String domicilio = getTfDomicilio().getText();
		
		return new ClienteDTO(dni, nombre, telefono, domicilio);
		
	}
	
	private PaqueteDTO registrarPaquete() {
		String dni = getTfDniFormulario().getText();
		String descripcion = getTfDescripcion().getText();
		String direccionOrigen = getTfDireccionOrigen().getText();
		String direccionDestino = getTfDireccionDestino().getText();
		boolean recogidaADomicilio = getCbDomicilio().isSelected();
		
		String idOficina = null;
		
		if(!getCbDomicilio().isSelected()) {
			idOficina = ((OficinaDTO)(getCbOficinas().getSelectedItem())).getId();
		}
		
		return new PaqueteDTO(UUID.randomUUID().toString(), descripcion, Date.valueOf(LocalDate.now()),
				"pendiente", direccionOrigen, direccionDestino, recogidaADomicilio, dni, null, idOficina);
	}
	
	private void registrar() {
		
		if(!verificarCampos()) {
			JOptionPane.showMessageDialog(this, 
					"No deben haber campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(getCbAlta().isSelected()) {
			boolean res = sc.grabarCliente(registrarCliente());
			if(!res) {
				JOptionPane.showMessageDialog(this, 
						"El cliente ya estaba registrado", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} 
		}
		boolean res = sc.grabarPedido(registrarPaquete());
		if(!res) {
			JOptionPane.showMessageDialog(this, 
					"El cliente no existe, debe registrarse como usuario", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this, 
				"Paquete registrado con éxito", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean verificarCampos() {
		String[] campos = new String[8];
		campos[0] = getTfDniFormulario().getText();
		campos[1] = getTfDescripcion().getText();
		campos[2] = getTfDireccionOrigen().getText();
		campos[3] = getTfDireccionDestino().getText();
		
		for(int i=0; i<4; i++) {
			if(campos[i].isBlank()) return false;
		}
		
		if(getCbAlta().isSelected()) {
			campos[4] = getTfDni().getText();
			campos[5] = getTfNombre().getText();
			campos[6] = getTfTelefono().getText();
			campos[7] = getTfDomicilio().getText();
			for(int i=4; i<campos.length; i++) {
				if(campos[i].isBlank()) return false;
			}
		}
		return true;
		
	}
}
