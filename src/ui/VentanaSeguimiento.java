package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import model.Localizador;
import model.SeguimientoController;
import model.dto.UbicacionDTO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaSeguimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbDni;
	private JTextField tfDni;
	private JButton btnNewButton;
	private JLabel lbLocalizadores;
	private JComboBox<Localizador> cbLocalizadores;
	private JTextArea taInfo;
	private JLabel lbInfo;
	private JLabel lbRecorrido;
	private JScrollPane scrollPane;
	private JList<UbicacionDTO> listUbicaciones;
	private JLabel lbActual;
	private JTextArea taActual;
	private JButton btnCerrar;
	
	private SeguimientoController sc;

	/**
	 * Create the frame.
	 */
	public VentanaSeguimiento() {
		
		sc = new SeguimientoController("");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbDni());
		contentPane.add(getTfDni());
		contentPane.add(getBtnNewButton());
		contentPane.add(getLbLocalizadores());
		contentPane.add(getCbLocalizadores());
		contentPane.add(getTaInfo());
		contentPane.add(getLbInfo());
		contentPane.add(getLbRecorrido());
		contentPane.add(getScrollPane());
		contentPane.add(getLbActual());
		contentPane.add(getTaActual());
		contentPane.add(getBtnCerrar());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("Introduzca DNI de usuario:");
			lbDni.setBounds(10, 11, 190, 14);
		}
		return lbDni;
	}
	private JTextField getTfDni() {
		if (tfDni == null) {
			tfDni = new JTextField();
			tfDni.setBounds(10, 34, 190, 20);
			tfDni.setColumns(10);
		}
		return tfDni;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Buscar Localizadores");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarLocalizadores();
				}
			});
			btnNewButton.setBounds(212, 33, 165, 23);
		}
		return btnNewButton;
	}
	private JLabel getLbLocalizadores() {
		if (lbLocalizadores == null) {
			lbLocalizadores = new JLabel("Localizadores de seguimiento de envíos:");
			lbLocalizadores.setBounds(387, 11, 291, 14);
		}
		return lbLocalizadores;
	}
	private JComboBox<Localizador> getCbLocalizadores() {
		if (cbLocalizadores == null) {
			DefaultComboBoxModel<Localizador> comboBoxModel = new DefaultComboBoxModel<>();
	        for (Localizador l : sc.getLocalizadores()) {
	            comboBoxModel.addElement(l);
	        }
			cbLocalizadores = new JComboBox<Localizador>(comboBoxModel);
			cbLocalizadores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarSeguimiento();
				}
			});
			cbLocalizadores.setBounds(387, 33, 378, 22);
		}
		return cbLocalizadores;
	}
	private JTextArea getTaInfo() {
		if (taInfo == null) {
			taInfo = new JTextArea();
			taInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			taInfo.setEditable(false);
			taInfo.setText("Descripción:\r\nFecha Emision: \r\nDirección Origen:\r\nDirección Destino:\r\nPeso: Precio: ");
			taInfo.setBounds(10, 107, 755, 105);
		}
		return taInfo;
	}
	private JLabel getLbInfo() {
		if (lbInfo == null) {
			lbInfo = new JLabel("Información general del pedido: ");
			lbInfo.setBounds(10, 84, 215, 14);
		}
		return lbInfo;
	}
	private JLabel getLbRecorrido() {
		if (lbRecorrido == null) {
			lbRecorrido = new JLabel("Ubicaciones Seguidas: ");
			lbRecorrido.setBounds(10, 223, 190, 14);
		}
		return lbRecorrido;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setBounds(10, 248, 338, 217);
			scrollPane.setViewportView(getListUbicaciones());
		}
		return scrollPane;
	}
	private JList<UbicacionDTO> getListUbicaciones() {
		if (listUbicaciones == null) {
	        listUbicaciones = new JList<UbicacionDTO>();
			listUbicaciones.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		}
		return listUbicaciones;
	}
	private JLabel getLbActual() {
		if (lbActual == null) {
			lbActual = new JLabel("Estado Actual: ");
			lbActual.setBounds(387, 223, 378, 14);
		}
		return lbActual;
	}
	private JTextArea getTaActual() {
		if (taActual == null) {
			taActual = new JTextArea();
			taActual.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			taActual.setEditable(false);
			taActual.setText("Su pedido se encuentra actualmente en:\r\nAlmacen:\r\nCiudad:");
			taActual.setBounds(387, 245, 378, 158);
		}
		return taActual;
	}
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBounds(676, 442, 89, 23);
		}
		return btnCerrar;
	}
	
	// Comportamiento.
	private void cargarLocalizadores() {
		String dni = getTfDni().getText();
		sc = new SeguimientoController(dni);
		DefaultComboBoxModel<Localizador> comboBoxModel = new DefaultComboBoxModel<>();
		List<Localizador> localizadores = sc.getLocalizadores();
	    for (Localizador l : localizadores) {
	        comboBoxModel.addElement(l);
	    }
	    
	    if (cbLocalizadores == null) {
	        cbLocalizadores = new JComboBox<>(comboBoxModel);
	    } else {
	        cbLocalizadores.setModel(comboBoxModel);
	    }
	    cbLocalizadores.revalidate();
	    cbLocalizadores.repaint();
	}
	
	private void mostrarDetalles() {
		Localizador l = (Localizador)getCbLocalizadores().getSelectedItem();
		getTaInfo().setText(l.obtenerDetallesDePaquete());
	}
	
	private void mostrarUbicaciones() {
		
	    List<UbicacionDTO> ubicaciones = ((Localizador)getCbLocalizadores().getSelectedItem()).getUbicaciones();

	    DefaultListModel<UbicacionDTO> listModel = new DefaultListModel<>();
	    for (UbicacionDTO u : ubicaciones) {
	        listModel.addElement(u);
	    }
	    if (listUbicaciones == null) {
	    	listUbicaciones = new JList<>(listModel);

	    } else {
	    	listUbicaciones.setModel(listModel);
	    }
	    listUbicaciones.revalidate();
	    listUbicaciones.repaint();
	    
	    getTaActual().setText(((Localizador)getCbLocalizadores().getSelectedItem()).obtenerInfoUbicacionActual());
	}
	
	private void mostrarSeguimiento() {
		mostrarDetalles();
		mostrarUbicaciones();
	}
}
