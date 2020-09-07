package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		String SO = System.getProperty("os.name");
		RedesController rede = new RedesController();
		int opc = 0;
		while(opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Menu:"
					+ "\n1- Ipv4"
					+ "\n2- Ping"
					+ "\n9- Sair"
					+ "\nEscolha uma opção:"));
			switch(opc) {
				case 1: 
					JOptionPane.showMessageDialog(null, rede.ip(SO));
					break;
				case 2:
					JOptionPane.showMessageDialog(null, rede.ping(SO));
					break;
				case 9:
					JOptionPane.showMessageDialog(null, "bye bye...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Error, escolha uma opção acima");
			}
		}
	}

}
