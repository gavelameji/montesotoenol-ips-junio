package ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.CrudClientes;
import model.RecogidasController;
import model.dto.ClienteDTO;
import model.dto.PaqueteDTO;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaTransportista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private JPanel panelRecogida;
	private JPanel panelEntrega;
	private JList<PaqueteDTO> list;
	private JLabel lbContenidoLista;
	private JTextArea taRecogida;
	private JLabel lbInfoPaqueteRecogida;
	private JLabel lbRecoger;
	private JButton btnRecoger;
	
	private RecogidasController rc;

	/**
	 * Create the frame.
	 */
	public VentanaTransportista() {
		
		rc = new RecogidasController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 864, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getTabbedPane());
		contentPane.add(getLbContenidoLista());
		
		cargarTablaConPaquetesPendientes();
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 25, 429, 453);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(439, 6, 409, 472);
			tabbedPane.addTab("Recogida De Paquetes", null, getPanelRecogida(), null);
			tabbedPane.addTab("Entrega De Paquetes", null, getPanelEntrega(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanelRecogida() {
		if (panelRecogida == null) {
			panelRecogida = new JPanel();
			panelRecogida.setLayout(null);
			panelRecogida.add(getTaRecogida());
			panelRecogida.add(getLbInfoPaqueteRecogida());
			panelRecogida.add(getLbRecoger());
			panelRecogida.add(getBtnRecoger());
		}
		return panelRecogida;
	}
	private JPanel getPanelEntrega() {
		if (panelEntrega == null) {
			panelEntrega = new JPanel();
		}
		return panelEntrega;
	}
	
	private JList<PaqueteDTO> getList() {
		if (list == null) {
			list = new JList<PaqueteDTO>();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					mostrarInfoPaquete();
				}
			});
		}
		return list;
	}
	private JLabel getLbContenidoLista() {
		if (lbContenidoLista == null) {
			lbContenidoLista = new JLabel("Paquetes pendientes de ser recogidos a domicilio por el transportista: ");
			lbContenidoLista.setBounds(10, 6, 429, 14);
		}
		return lbContenidoLista;
	}
	private JTextArea getTaRecogida() {
		if (taRecogida == null) {
			taRecogida = new JTextArea();
			taRecogida.setText("Descripción:\r\nDir. Recogida:\r\nCliente:");
			taRecogida.setEditable(false);
			taRecogida.setBounds(10, 36, 384, 116);
		}
		return taRecogida;
	}
	private JLabel getLbInfoPaqueteRecogida() {
		if (lbInfoPaqueteRecogida == null) {
			lbInfoPaqueteRecogida = new JLabel("Información de paquete:");
			lbInfoPaqueteRecogida.setBounds(10, 11, 384, 14);
		}
		return lbInfoPaqueteRecogida;
	}
	private JLabel getLbRecoger() {
		if (lbRecoger == null) {
			lbRecoger = new JLabel("¿Desea marcar este paquete como recogido?");
			lbRecoger.setHorizontalAlignment(SwingConstants.CENTER);
			lbRecoger.setBounds(10, 163, 384, 14);
		}
		return lbRecoger;
	}
	private JButton getBtnRecoger() {
		if (btnRecoger == null) {
			btnRecoger = new JButton("Recoger");
			btnRecoger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					marcarComoRecogido();
				}
			});
			btnRecoger.setBounds(156, 188, 89, 23);
		}
		return btnRecoger;
	}
	
	// Comportamiento
	
	private void cargarTablaConPaquetesPendientes() {
		List<PaqueteDTO> paquetes = rc.getPaquetesARecoger();
	    DefaultListModel<PaqueteDTO> listModel = new DefaultListModel<>();
	    for (PaqueteDTO p : paquetes) {
	        listModel.addElement(p);
	    }
	    if (list == null) {
	    	list= new JList<>(listModel);

	    } else {
	    	list.setModel(listModel);
	    }
	    list.revalidate();
	    list.repaint();
	}
	
	private void mostrarInfoPaquete() {
		PaqueteDTO p = list.getSelectedValue();
		if(p == null) return;
		ClienteDTO c = CrudClientes.obtenerClientePorDNI(p.getDniCliente());
		StringBuilder sb = new StringBuilder();
		sb.append("Descripción: ").append(p.getDescripcion()).append("\n");
		sb.append("Dir. Recogida: ").append(p.getDireccionOrigen()).append("\n");
		sb.append("Cliente: ").append(c.getNombre() + ", " + c.getDni()).append("\n");
		getTaRecogida().setText(sb.toString());
	}
	
	private void marcarComoRecogido() {
		if(list.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "No hay ningun paquete seleccionado", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String id = list.getSelectedValue().getId();
		rc.actualizarEstadoPaquete(id);
		cargarTablaConPaquetesPendientes();
		getTaRecogida().setText("");
	}
}
