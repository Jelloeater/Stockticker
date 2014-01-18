package jelloeater.StockTicker;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import javax.swing.JCheckBoxMenuItem;

class TickerWindow extends App{

	private JFrame mainWindow;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Launch the application. Main GUI Class
	 */
	static void launchGui(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TickerWindow window = new TickerWindow();
					window.mainWindow.setVisible(true);


                    // TODO Task loop goes here?

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void updateGuiWindowText() {
		tickerList.updateTickerList();
		// FIXME set tickerString to GUI window

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
		mainWindow.getContentPane().setLayout(new MigLayout("", "[grow][]", "[211.00,grow][bottom]"));
		final JTextPane tickerWindow = new JTextPane();
		mainWindow.getContentPane().add(tickerWindow, "cell 0 0,grow");

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tickerWindow, popupMenu);

		JMenuItem mntmRefreshList = new JMenuItem("Refresh");                       // REFRESH POPUP
		mntmRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tickerList.updateTickerList();
				tickerWindow.setText(tickerList.outputTickerListToString());
			}
		});
		mntmRefreshList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		popupMenu.add(mntmRefreshList);


		/**
		 * On a window close, spawns dialogue box to check if you really want to close that window
		 * NOTE if you close all the windows, the shutdown hooks will kick in
		 * It listens to the status of the specified JFrame
		 */
		mainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e){
				if (App.shutdownWindow()== 0) {
					((JFrame)(e.getComponent())).dispose();
				} else {
					e.getComponent().setVisible(true);
					// You only need to cast once
				}
			}
		});
		
			
		JScrollBar scrollBar = new JScrollBar();
		mainWindow.getContentPane().add(scrollBar, "cell 1 0,alignx right,growy");

		JTextPane indexWindow = new JTextPane();
		mainWindow.getContentPane().add(indexWindow, "flowx,cell 0 1,growx,aligny bottom");

		JButton addNewStock = new JButton("+");
		addNewStock.setToolTipText("Add a new symbol to the list");
		addNewStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tickerList.addStockToListGUI();}
			});

		mainWindow.getContentPane().add(addNewStock, "cell 0 1,alignx right,aligny bottom");

		JMenuBar menuBar = new JMenuBar();
			mainWindow.setJMenuBar(menuBar);
			
			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);
			
			/**
			 * When clicked, opens shutdown window
			 */
			JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (App.shutdownWindow()== 0) System.exit(0); // Ends up calling hooks
				}
			});
			mnFile.add(mntmExit);
			
			JMenu mnSettings = new JMenu("Settings");
			menuBar.add(mnSettings);
			
			JMenuItem mntmSetRefresh = new JMenuItem("Set Refresh...");
		mntmSetRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmSetRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.setRefreshIntervalSecondsGUI();
				}
			});
			mnSettings.add(mntmSetRefresh);
									
			JMenuItem mntmSetIndex = new JMenuItem("Set Index...");
		mntmSetIndex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmSetIndex.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					settingsProperties.setIndexSymbolGUI();
				}
			});
			mnSettings.add(mntmSetIndex);

		JMenu mnQuoteSource = new JMenu("Quote Source");
		mnSettings.add(mnQuoteSource);

		JRadioButtonMenuItem rdbtnmntmGoogle = new JRadioButtonMenuItem("Google",settingsProperties.isSourceGoogle());
		rdbtnmntmGoogle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		rdbtnmntmGoogle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.setSourceGoogle();
				}
			});
			buttonGroup.add(rdbtnmntmGoogle);
		mnQuoteSource.add(rdbtnmntmGoogle);
			
			/*
			JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Yahoo");
			buttonGroup.add(rdbtnmntmNewRadioItem);
			mnNewMenu.add(rdbtnmntmNewRadioItem);
			
			JRadioButtonMenuItem rdbtnmntmMarketwatch = new JRadioButtonMenuItem("MarketWatch");
			mnNewMenu.add(rdbtnmntmMarketwatch);
			
			JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Refresh Enabled");
			mnSettings.add(chckbxmntmNewCheckItem);
			*/
			
			JMenu mnHelp = new JMenu("Help");
			menuBar.add(mnHelp);
			
			JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
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

