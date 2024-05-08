package Utilisateur;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.LineBorder;

import jade.gui.GuiEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import DAO.Information;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UtilisateurGUI extends JFrame
{
	private JPanel contentPane;
	private UtilisateurAgent utilisateurAgent;

	private JTextArea textArea;
	private JScrollPane scrollPane;

	private final ButtonGroup carType = new ButtonGroup();
	private JPanel panel;
	private JTextField depart;
	private JTextField arrivee;
	private JTextField date;
	private JTable table;
	private Image backgroundImage;

	public UtilisateurAgent getUtilisateurAgent() {
		return utilisateurAgent;
	}

	public void setUtilisateurAgent(UtilisateurAgent utilisateurAgent) {
		this.utilisateurAgent = utilisateurAgent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UtilisateurGUI frame = new UtilisateurGUI();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 */
	public UtilisateurGUI(){
		setTitle("Chercher les chambres dans les hotels disponible");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 700);
		contentPane = new JPanel();//new JPanel();
		contentPane = new ImagePanel(new ImageIcon("C:\\Users\\hp\\Downloads\\Recherche-d-information---Multi-Agents-master\\Recherche-d-information---Multi-Agents-master\\src\\bg.jpg").getImage());
		contentPane.setBorder(new LineBorder(SystemColor.inactiveCaption, 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// Adjusting btnEnvoyer
		JButton btnEnvoyer = new JButton("Chercher");
		btnEnvoyer.setForeground(new Color(0, 0, 0));
		btnEnvoyer.setBackground(new Color(201, 233, 246));
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Depart = UtilisateurGUI.this.depart.getText();
				String Arrivee = UtilisateurGUI.this.arrivee.getText();
				String Date = UtilisateurGUI.this.date.getText();
				GuiEvent ev = new GuiEvent(this, 1);
				ev.addParameter(Depart);
				ev.addParameter(Arrivee);
				ev.addParameter(Date);
				System.out.println("ev : " + ev);
				UtilisateurGUI.this.utilisateurAgent.onGuiEvent(ev);
			}
		});
		btnEnvoyer.setFont(new Font("Consolas", Font.BOLD, 15));
		btnEnvoyer.setBounds(350, 350, 158, 32);
		contentPane.add(btnEnvoyer);

		JButton btnViderLaListe = new JButton("Effacer");
		btnViderLaListe.setContentAreaFilled(true);
		btnViderLaListe.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				DefaultTableModel newmodel = new DefaultTableModel(new Object[] {"Ville", "NomHotel", "Date"},0);
				table.setModel(newmodel);
			}
		});
		btnViderLaListe.setForeground(new Color(0,0,0));
		btnViderLaListe.setFont(new Font("Consolas", Font.BOLD, 15));
		btnViderLaListe.setBounds(600, 350, 158, 32);
		btnViderLaListe.setBackground(new Color(201,233,246));
		contentPane.add(btnViderLaListe);

// Adjusting UserChoicePanel
		RoundedPanel UserChoicePanel = new RoundedPanel(null);
		UserChoicePanel.setBounds(300, 33, 500, 300);
		UserChoicePanel.setLayout(new BorderLayout());
		UserChoicePanel.setBackground(new Color(255, 255, 255, 200)); // White with 200/255 opacity
		contentPane.add(UserChoicePanel);
		UserChoicePanel.setLayout(null);


// Depart input field
		depart = new JTextField();
		depart.setBounds(40, 44, 276, 30);
		UserChoicePanel.add(depart);
		depart.setColumns(10);
		depart.setBackground(new Color(245, 245, 245)); // Light Gray
		depart.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2)); // Brown border
		depart.setFont(new Font("Arial", Font.PLAIN, 14)); // Plain Arial font

// Label for Depart input field
		JLabel InputLabel = new JLabel("Ville :");
		InputLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Bold Arial
		InputLabel.setBounds(40, 19, 280, 21);
		UserChoicePanel.add(InputLabel);

// Arrivee input field
		arrivee = new JTextField();
		arrivee.setBounds(40, 113, 276, 30);
		UserChoicePanel.add(arrivee);
		arrivee.setColumns(10);
		arrivee.setBackground(new Color(245, 245, 245)); // Light Gray
		arrivee.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2)); // Brown border
		arrivee.setFont(new Font("Arial", Font.PLAIN, 14)); // Plain Arial font

// Label for Arrivee input field
		JLabel InputLabel2 = new JLabel("NomHotel :");
		InputLabel2.setFont(new Font("Arial", Font.BOLD, 15)); // Bold Arial
		InputLabel2.setBounds(40, 88, 280, 21);
		UserChoicePanel.add(InputLabel2);

// Date input field
		date = new JTextField();
		date.setBounds(40, 179, 276, 30);
		UserChoicePanel.add(date);
		date.setColumns(10);
		date.setBackground(new Color(245, 245, 245)); // Light Gray
		date.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2)); // Brown border
		date.setFont(new Font("Arial", Font.PLAIN, 14)); // Plain Arial font
		date.setText("YYYY-MM-DD"); // Placeholder text for date format


// Label for Date input field
		JLabel InputLabel3 = new JLabel("Date  :");
		InputLabel3.setFont(new Font("Arial", Font.BOLD, 15)); // Bold Arial
		InputLabel3.setBounds(40, 154, 280, 21);
		UserChoicePanel.add(InputLabel3);






		// Creating the JScrollPane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(300, 400, 500, 100);
		contentPane.add(scrollPane_1);

		// Create the JTable with a DefaultTableModel
		table = new JTable(new DefaultTableModel(
				new Object[][]{},
				new String[]{"Ville", "NomHotel", "Date"}
		));

// Customizing the table appearance
		table.setFont(new Font("Arial", Font.PLAIN, 14)); // Modern font
		table.setForeground(Color.BLACK); // Text color
		table.setBackground(Color.WHITE); // Background color

// Customizing the table header
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for header
		header.setForeground(Color.WHITE); // Text color
		header.setBackground(new Color(53, 81, 92)); // Header background color

// Alternating row colors for better readability
		table.setRowHeight(30); // Adjust row height
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (row % 2 == 0) {
					c.setBackground(new Color(240, 248, 255)); // Light Blue
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		});

		scrollPane_1.setViewportView(table);

// Adding a logo JLabel
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("D:\\LSI S4\\AID & SMA\\JadeTest\\src\\logo.png"));
		lblNewLabel.setBounds(600, 80, 1000, 265);
		contentPane.add(lblNewLabel);

		this.setVisible(true);

	}

	public void showMessage(Information info) {
		System.out.println("show message : " + info.getVille());
		DefaultTableModel oldmodel = (DefaultTableModel)this.table.getModel();
		oldmodel.addRow(new Object[]{info.getVille(), info.getNomHotel(), info.getDate()});
		this.table.setModel(oldmodel);
	}

	public void showMessage(String message) {
		List<Information> infos = Information.StringToList(message);
		System.out.println("show message : " + infos);
		DefaultTableModel oldmodel = (DefaultTableModel)this.table.getModel();
		Iterator var4 = infos.iterator();

		while(var4.hasNext()) {
			Information s = (Information)var4.next();
			Object[] o = new Object[]{s.getVille(), s.getNomHotel(), s.getDate()};
			oldmodel.addRow(o);
		}

		this.table.setModel(oldmodel);
	}

	String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = (AbstractButton) buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	public void showNotFound() {
		JOptionPane jop1 = new JOptionPane();
		jop1.showMessageDialog(null, "Aucun resultat n'est trouve", "Information", JOptionPane.INFORMATION_MESSAGE);

	}
}

