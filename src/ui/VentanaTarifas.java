package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.TarifasController;
import model.dto.OficinaDTO;
import model.dto.PaqueteDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaTarifas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbOficina;
	private JComboBox<OficinaDTO> cbOficinas;
	private JLabel lbPaquetes;
	private JScrollPane scrollPane;
	private JList<PaqueteDTO> list;
	private JLabel lbDetalles;
	private JTextArea taDetalles;
	private JLabel lbPeso;
	private JTextField textField;
	private JButton btPeso;
	private JLabel lbCalculo;
	private JTextArea taPrecio;
	
	private TarifasController tc;
	private JButton btnAsignar;

	/**
	 * Create the frame.
	 */
	public VentanaTarifas() {
		tc = new TarifasController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 866, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbOficina());
		contentPane.add(getCbOficinas());
		contentPane.add(getLbPaquetes());
		contentPane.add(getScrollPane());
		contentPane.add(getLbDetalles());
		contentPane.add(getTaDetalles());
		contentPane.add(getLbPeso());
		contentPane.add(getTextField());
		contentPane.add(getBtPeso());
		contentPane.add(getLbCalculo());
		contentPane.add(getTaPrecio());
		contentPane.add(getBtnAsignar());
		
		obtenerPaquetesPorOficina();
	}
	private JLabel getLbOficina() {
		if (lbOficina == null) {
			lbOficina = new JLabel("Seleccione Oficina:");
			lbOficina.setBounds(10, 11, 246, 14);
		}
		return lbOficina;
	}
	private JComboBox<OficinaDTO> getCbOficinas() {
		if (cbOficinas == null) {
			DefaultComboBoxModel<OficinaDTO> comboBoxModel = new DefaultComboBoxModel<>();
	        for (OficinaDTO o : tc.getOficinas()) {
	            comboBoxModel.addElement(o);
	        }
			cbOficinas = new JComboBox<OficinaDTO>(comboBoxModel);
			cbOficinas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					obtenerPaquetesPorOficina();
				}
			});
			cbOficinas.setBounds(10, 36, 364, 22);
		}
		return cbOficinas;
	}
	private JLabel getLbPaquetes() {
		if (lbPaquetes == null) {
			lbPaquetes = new JLabel("Paquetes en oficina pendientes de envío:");
			lbPaquetes.setBounds(10, 85, 364, 14);
		}
		return lbPaquetes;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 364, 364);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<PaqueteDTO> getList() {
		if (list == null) {
			list = new JList<PaqueteDTO>();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(list.getSelectedValue() != null) {
						getTaDetalles().setText(tc.getInfoPaquete(list.getSelectedValue()));
					}
				}
			});
		}
		return list;
	}
	private JLabel getLbDetalles() {
		if (lbDetalles == null) {
			lbDetalles = new JLabel("Detalles Del Paquete:");
			lbDetalles.setBounds(413, 40, 427, 14);
		}
		return lbDetalles;
	}
	private JTextArea getTaDetalles() {
		if (taDetalles == null) {
			taDetalles = new JTextArea();
			taDetalles.setText("Descripción:\r\nFecha De Emisión:\r\nDirección De Recogida:\r\nDirección Destino:\r\nCliente:\r\nTeléfono: ");
			taDetalles.setEditable(false);
			taDetalles.setBounds(413, 65, 427, 148);
		}
		return taDetalles;
	}
	private JLabel getLbPeso() {
		if (lbPeso == null) {
			lbPeso = new JLabel("Introduzca Peso (Kg):");
			lbPeso.setBounds(413, 235, 124, 14);
		}
		return lbPeso;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(536, 232, 184, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtPeso() {
		if (btPeso == null) {
			btPeso = new JButton("Introducir");
			btPeso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btPeso.setBounds(730, 231, 110, 23);
		}
		return btPeso;
	}
	private JLabel getLbCalculo() {
		if (lbCalculo == null) {
			lbCalculo = new JLabel("Cálculo Del Precio Final: ");
			lbCalculo.setBounds(413, 260, 427, 14);
		}
		return lbCalculo;
	}
	private JTextArea getTaPrecio() {
		if (taPrecio == null) {
			taPrecio = new JTextArea();
			taPrecio.setEditable(false);
			taPrecio.setText("Tipo:\r\nDistancia Ruta:\r\nPeso Producto:\r\nPrecio Base;\r\nTarifa Aplicada:\r\nPrecio Final:");
			taPrecio.setBounds(413, 285, 427, 123);
		}
		return taPrecio;
	}
	
	private void obtenerPaquetesPorOficina() {
		List<PaqueteDTO> paquetes = tc.getPaquetesPorIdOficina(((OficinaDTO)getCbOficinas().getSelectedItem()).getId());

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
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar Precio Final");
			btnAsignar.setBounds(688, 451, 152, 23);
		}
		return btnAsignar;
	}
}
