package jelloeater.StockTicker;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;

class TickerWindow extends App{

	private JFrame mainWindow;

	/**
	 * Launch the application.
	 */
	static void launchGui(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TickerWindow window = new TickerWindow();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	TickerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setBounds(100, 100, 250, 450);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(new MigLayout("", "[grow][]", "[211.00,grow][bottom]"));
		
		JTextPane textPane = new JTextPane();
		mainWindow.getContentPane().add(textPane, "cell 0 0,grow");
		
		JScrollBar scrollBar = new JScrollBar();
		mainWindow.getContentPane().add(scrollBar, "cell 1 0,alignx right,growy");
		
		JTextPane textPane_1 = new JTextPane();
		mainWindow.getContentPane().add(textPane_1, "flowx,cell 0 1,growx,aligny bottom");
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.addStockToList();
			}
		});
			mainWindow.getContentPane().add(btnNewButton, "cell 0 1,alignx right,aligny bottom");
			
			JMenuBar menuBar = new JMenuBar();
			mainWindow.setJMenuBar(menuBar);
			
			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);
			
			JMenuItem mntmExit = new JMenuItem("Exit");
			mntmExit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			mnFile.add(mntmExit);
			
			JMenu mnSettings = new JMenu("Settings");
			menuBar.add(mnSettings);
			
			JMenuItem mntmSetRefresh = new JMenuItem("Set Refresh...");
			mnSettings.add(mntmSetRefresh);
			
			JMenuItem mntmSetSource = new JMenuItem("Set Source...");
			mnSettings.add(mntmSetSource);
			
			JMenu mnHelp = new JMenu("Help");
			menuBar.add(mnHelp);
			
			JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
			mnHelp.add(mntmHelpContents);
			
			JMenuItem mntmRestoreDefaults = new JMenuItem("Restore Defaults");
			mntmRestoreDefaults.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) { // Sets defaults
				}
			});
			mnHelp.add(mntmRestoreDefaults);
			
			JSeparator separator_1 = new JSeparator();
			mnHelp.add(separator_1);
			
			JMenuItem mntmAbout = new JMenuItem("About");
			mnHelp.add(mntmAbout);
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

