package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.CrudOficinas;
import data.CrudPaquetes;
import model.PaqueteController;
import model.dto.OficinaDTO;
import model.dto.PaqueteDTO;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaVerEnvios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbDni;
	private JPanel topPanel;
	private JTextField tfDni;
	private JButton btnBuscar;
	private JTable tbPedidos;
	
	private PaqueteController pc;
	private String dni;
	private JScrollPane panelTabla;

	/**
	 * Create the frame.
	 */
	public VentanaVerEnvios() {
		
		pc = new PaqueteController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTopPanel(), BorderLayout.NORTH);
		contentPane.add(getPanelTabla(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("Mis Pedidos: Introduzca DNI para ver su histórico de Pedidos: ");
		}
		return lbDni;
	}
	private JPanel getTopPanel() {
		if (topPanel == null) {
			topPanel = new JPanel();
			topPanel.add(getLbDni());
			topPanel.add(getTfDni());
			topPanel.add(getBtnBuscar());
		}
		return topPanel;
	}
	private JTextField getTfDni() {
		if (tfDni == null) {
			tfDni = new JTextField();
			tfDni.setColumns(10);
		}
		return tfDni;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});
		}
		return btnBuscar;
	}
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			DefaultTableModel tableModel = new DefaultTableModel();
			String[] columnNames = {"ID", "Descripción", "Fecha Emisión", "Estado", "Recoger a Domicilio"};
	        tableModel.setColumnIdentifiers(columnNames);
	        tbPedidos = new JTable(tableModel);
		}
		return tbPedidos;
	}
	
	private JScrollPane getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JScrollPane(getTbPedidos());
		}
		return panelTabla;
	}
	
	// Comportamiento.
	private void actualizarTabla() {
		DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"ID", "Descripción", "Fecha Emisión", "Estado", "Recoger a Domicilio"};
        tableModel.setColumnIdentifiers(columnNames);
		for (PaqueteDTO paquete : pc.filtrarPaquetesPorDni(dni)) {
            Object[] row = {
                paquete.getId(),
                paquete.getDescripcion(),
                paquete.getFechaEmision(),
                paquete.getEstado(),
                paquete.paraRecogerADomicilio(),
            };
            tableModel.addRow(row);
        }
		tbPedidos.setModel(tableModel);
		
		if (tbPedidos.getTableHeader() == null) {
	        tbPedidos.createDefaultColumnsFromModel();
	    }
	}
	
	private void buscar() {
		verificarCampo();
		this.dni = getTfDni().getText().trim();
		avisarEntregasBloqueadas(dni);
		actualizarTabla();
	}
	
	private void verificarCampo() {
		
		String texto = getTfDni().getText();
		boolean numerico = true;
		
		if (texto == null || texto.isEmpty()) {
	        numerico = false;
	    }
		
	    for (char caracter : texto.toCharArray()) {
	        if (!Character.isDigit(caracter)) {
	            numerico =  false;
	            break;
	        }
	    } 
	    
		if(!numerico) {
			JOptionPane.showMessageDialog(this, 
					"Formato no válido", "Error", JOptionPane.ERROR_MESSAGE);
			getTfDni().setText("");
		}
	}
	
	private void avisarEntregasBloqueadas(String dni) {
		List<PaqueteDTO> entregasBloqueadas = CrudPaquetes.obtenerPaquetesEnOficina(dni);
		if(entregasBloqueadas.size() == 0)
			return;
		StringBuilder sb = new StringBuilder();
		OficinaDTO o;
		for(PaqueteDTO p: entregasBloqueadas) {
			o = CrudOficinas.obtenerOficinaPorId(p.getIdOficina());
			sb.append(p).append(" en oficina ").append(o).append("\n");
		}
		JOptionPane.showMessageDialog(this, 
				"Los siguientes paquetes no han podido ser entregados y deben ser recogidos"
				+ " en oficina:\n" + sb.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
	}
}
