package ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.CrudClientes;
import data.CrudOficinas;
import model.EntregasController;
import model.RecogidasController;
import model.dto.ClienteDTO;
import model.dto.OficinaDTO;
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
import java.awt.Color;
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
	private JLabel lblPaquetesPendientesDe;
	private JScrollPane scrollPane2;
	private JList<PaqueteDTO> listEntregas;
	private JLabel lbInfoEntrega;
	private JTextArea taEntrega;
	private JLabel lbEntregar;
	private JButton btnEntregar;
	private JLabel lbBloquear;
	private JButton btnBloquear;
	
	private RecogidasController rc;
	private EntregasController ec;
	private JTextArea taFallidas;

	/**
	 * Create the frame.
	 */
	public VentanaTransportista() {
		setResizable(false);
		
		rc = new RecogidasController();
		ec = new EntregasController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 864, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTabbedPane());
		
		cargarTablaConPaquetesPendientes();
		cargarTablaConPaquetesEntrega();
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 419, 377);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 848, 446);
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
			panelRecogida.add(getScrollPane());
			panelRecogida.add(getLbContenidoLista());
		}
		return panelRecogida;
	}
	private JPanel getPanelEntrega() {
		if (panelEntrega == null) {
			panelEntrega = new JPanel();
			panelEntrega.setLayout(null);
			panelEntrega.add(getLblPaquetesPendientesDe());
			panelEntrega.add(getScrollPane2());
			panelEntrega.add(getLbInfoEntrega());
			panelEntrega.add(getTaEntrega());
			panelEntrega.add(getLbEntregar());
			panelEntrega.add(getBtnEntregar());
			panelEntrega.add(getLbBloquear());
			panelEntrega.add(getBtnBloquear());
			panelEntrega.add(getTaFallidas());
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
			lbContenidoLista.setBounds(10, 11, 429, 14);
		}
		return lbContenidoLista;
	}
	private JTextArea getTaRecogida() {
		if (taRecogida == null) {
			taRecogida = new JTextArea();
			taRecogida.setText("Descripción:\r\nDir. Recogida:\r\nCliente:");
			taRecogida.setEditable(false);
			taRecogida.setBounds(439, 36, 384, 116);
		}
		return taRecogida;
	}
	private JLabel getLbInfoPaqueteRecogida() {
		if (lbInfoPaqueteRecogida == null) {
			lbInfoPaqueteRecogida = new JLabel("Información de paquete:");
			lbInfoPaqueteRecogida.setBounds(439, 11, 384, 14);
		}
		return lbInfoPaqueteRecogida;
	}
	private JLabel getLbRecoger() {
		if (lbRecoger == null) {
			lbRecoger = new JLabel("¿Desea marcar este paquete como recogido?");
			lbRecoger.setHorizontalAlignment(SwingConstants.CENTER);
			lbRecoger.setBounds(439, 163, 384, 14);
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
			btnRecoger.setBounds(585, 188, 89, 23);
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
	
	private void cargarTablaConPaquetesEntrega() {
		List<PaqueteDTO> paquetes = ec.getPaquetesAEntregar();
	    DefaultListModel<PaqueteDTO> listModel = new DefaultListModel<>();
	    for (PaqueteDTO p : paquetes) {
	        listModel.addElement(p);
	    }
	    if (listEntregas == null) {
	    	listEntregas = new JList<>(listModel);

	    } else {
	    	listEntregas.setModel(listModel);
	    }
	    listEntregas.revalidate();
	    listEntregas.repaint();
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
	
	private void mostrarInfoEntrega() {
		PaqueteDTO p = listEntregas.getSelectedValue();
		if(p == null) return;
		ClienteDTO c = CrudClientes.obtenerClientePorDNI(p.getDniCliente());
		StringBuilder sb = new StringBuilder();
		sb.append("Descripción: ").append(p.getDescripcion()).append("\n");
		sb.append("Dir. Destino: ").append(p.getDireccionDestino()).append("\n");
		sb.append("Estado: ").append(p.getEstado()).append("\n");
		sb.append("Cliente: ").append(c.getNombre() + ", " + c.getDni()).append("\n");
		getTaEntrega().setText(sb.toString());
		if(p.getEstado().equals("bloqueado"))
			getTaEntrega().setForeground(Color.RED);
		else
			getTaEntrega().setForeground(Color.BLACK);
		getTaFallidas().setText("Este paquete se ha intentado entregar: " + p.getVecesBloqueado() + " veces. Maximo 3.");
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
	
	private void marcarComoEntregado() {
		if(listEntregas.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "No hay ningun paquete seleccionado", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String id = listEntregas.getSelectedValue().getId();
		ec.actualizarEstadoARecibido(id);
		cargarTablaConPaquetesEntrega();
		getTaEntrega().setText("");
	}
	
	private void marcarComoBloqueado() {
		if(listEntregas.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "No hay ningun paquete seleccionado", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		PaqueteDTO p = listEntregas.getSelectedValue();
		if(!ec.actualizarEstadoABloqueado(p)) {
			OficinaDTO o = CrudOficinas.obtenerOficinaPorId(p.getIdOficina());
			String nombreOficina = "";
			if(o != null) nombreOficina = o.getCiudad();
			JOptionPane.showMessageDialog(null, "No se puede volver a enviar."
					+ " Depositar en oficina " + nombreOficina, "Info", JOptionPane.ERROR_MESSAGE);
		};
		cargarTablaConPaquetesEntrega();
		getTaFallidas().setText("");
	}
	
	// Fin comportamiento 
	
	private JLabel getLblPaquetesPendientesDe() {
		if (lblPaquetesPendientesDe == null) {
			lblPaquetesPendientesDe = new JLabel("Paquetes pendientes de ser entregados en el destinatario: ");
			lblPaquetesPendientesDe.setBounds(10, 11, 336, 14);
		}
		return lblPaquetesPendientesDe;
	}
	private JScrollPane getScrollPane2() {
		if (scrollPane2 == null) {
			scrollPane2 = new JScrollPane();
			scrollPane2.setBounds(10, 36, 419, 382);
			scrollPane2.setViewportView(getListEntregas());
		}
		return scrollPane2;
	}
	private JList<PaqueteDTO> getListEntregas() {
		if (listEntregas == null) {
			listEntregas = new JList<PaqueteDTO>();
			listEntregas.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					mostrarInfoEntrega();
				}
			});
		}
		return listEntregas;
	}
	private JLabel getLbInfoEntrega() {
		if (lbInfoEntrega == null) {
			lbInfoEntrega = new JLabel("Información de paquete:");
			lbInfoEntrega.setBounds(438, 11, 384, 14);
		}
		return lbInfoEntrega;
	}
	private JTextArea getTaEntrega() {
		if (taEntrega == null) {
			taEntrega = new JTextArea();
			taEntrega.setText("Descripción:\r\nDir. Destino: \r\nEstado: \r\nCliente:");
			taEntrega.setBounds(439, 35, 384, 119);
		}
		return taEntrega;
	}
	private JLabel getLbEntregar() {
		if (lbEntregar == null) {
			lbEntregar = new JLabel("¿Desea marcar este paquete como entregado?");
			lbEntregar.setHorizontalAlignment(SwingConstants.CENTER);
			lbEntregar.setBounds(439, 200, 384, 14);
		}
		return lbEntregar;
	}
	private JButton getBtnEntregar() {
		if (btnEntregar == null) {
			btnEntregar = new JButton("Entregar");
			btnEntregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					marcarComoEntregado();
				}
			});
			btnEntregar.setBounds(586, 225, 89, 23);
		}
		return btnEntregar;
	}
	private JLabel getLbBloquear() {
		if (lbBloquear == null) {
			lbBloquear = new JLabel("¿Desea bloquear el paquete por falta de destinatario?");
			lbBloquear.setHorizontalAlignment(SwingConstants.CENTER);
			lbBloquear.setBounds(439, 273, 384, 14);
		}
		return lbBloquear;
	}
	private JButton getBtnBloquear() {
		if (btnBloquear == null) {
			btnBloquear = new JButton("Bloquear");
			btnBloquear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					marcarComoBloqueado();
				}
			});
			btnBloquear.setBounds(586, 298, 89, 23);
		}
		return btnBloquear;
	}
	private JTextArea getTaFallidas() {
		if (taFallidas == null) {
			taFallidas = new JTextArea();
			taFallidas.setBounds(444, 366, 378, 29);
		}
		return taFallidas;
	}
}
