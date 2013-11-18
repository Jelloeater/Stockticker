package jelloeater.StockTicker;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class TickerWindow extends App{

	private JFrame mainWindow;

	/** Shutdown Hook, used to override application close behavior*/
	static void shutdownHook(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				App.shutdown();				
			}
		}));
	}
	
	
	/**
	 * Launch the application. Main GUI Class
	 */
	static void launchGui(String[] args) {
		
		shutdownHook();
		
		
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
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setBounds(100, 100, 250, 450);
		mainWindow.getContentPane().setLayout(new MigLayout("", "[grow][]", "[211.00,grow][bottom]"));
		JTextPane textPane = new JTextPane();
		mainWindow.getContentPane().add(textPane, "cell 0 0,grow");
		
		
		/*
		WindowListener l = null;
		mainWindow.addWindowListener(l);
		//l.windowClosing();
		 */
		
		JScrollBar scrollBar = new JScrollBar();
		mainWindow.getContentPane().add(scrollBar, "cell 1 0,alignx right,growy");
		
		JTextPane textPane_1 = new JTextPane();
		mainWindow.getContentPane().add(textPane_1, "flowx,cell 0 1,growx,aligny bottom");
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				App.addStockToList();}
			});

			mainWindow.getContentPane().add(btnNewButton, "cell 0 1,alignx right,aligny bottom");
			
			JMenuBar menuBar = new JMenuBar();
			mainWindow.setJMenuBar(menuBar);
			
			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);
			
			JMenuItem mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					App.shutdown();
				}
			});
			mnFile.add(mntmExit);
			
			JMenu mnSettings = new JMenu("Settings");
			menuBar.add(mnSettings);
			
			JMenuItem mntmSetRefresh = new JMenuItem("Set Refresh...");
			mntmSetRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.setRefreshIntervalSecondsGUI();
				}
			});
			mnSettings.add(mntmSetRefresh);
			
			JMenuItem mntmSetSource = new JMenuItem("Set Source...");
			mntmSetSource.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.setQuoteSourceGUI();
				}
			});
			mnSettings.add(mntmSetSource);
			
			JMenu mnHelp = new JMenu("Help");
			menuBar.add(mnHelp);
			
			JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
			mnHelp.add(mntmHelpContents);
			
			JMenuItem mntmRestoreDefaults = new JMenuItem("Restore Defaults");
			mntmRestoreDefaults.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.restoreDefaultsGUI();
				}
			});
			mnHelp.add(mntmRestoreDefaults);
			
			JSeparator separator_1 = new JSeparator();
			mnHelp.add(separator_1);
			
			JMenuItem mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Created by Jesse Schoepfer /n Some stuff", "About", 2);
				}
			});
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

