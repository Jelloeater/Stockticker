package jelloeater.StockTicker;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import javax.swing.JCheckBoxMenuItem;

class TickerView extends main {

	private JFrame mainWindow;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Launch the application. Main GUI Class
	 */
	static void launchGui(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TickerView window = new TickerView();
					window.mainWindow.setVisible(true);


					// TODO Task loop goes here?

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Takes GUI windows to be updated */
	public void updateTickerWindowGUI(JTextPane tickerWindow, JTextPane indexWindow) {
		tickerListController.updateTickerList();
		tickerWindow.setText(tickerListController.outputTickerListToString());
		tickerListController.updateIndexInfo();
		indexWindow.setText(tickerListController.outputIndexToString());
	}

	/**
	 * Create the application.
	 */
	TickerView() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		mainWindow = new JFrame();
		mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(TickerView.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		mainWindow.setTitle("Stock Ticker");
		mainWindow.setBounds(100, 100, 250, 450);
		mainWindow.getContentPane().setLayout(new MigLayout("", "[grow][]", "[211.00,grow][bottom]"));
		final JTextPane tickerWindow = new JTextPane();
		mainWindow.getContentPane().add(tickerWindow, "flowx,cell 0 0,grow");


		/**
		 * On a window close, spawns dialogue box to check if you really want to close that window
		 * NOTE if you close all the windows, the shutdown hooks will kick in
		 * It listens to the status of the specified JFrame
		 */
		mainWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				if (main.shutdownWindow() == 0) {
					((JFrame) (e.getComponent())).dispose();
				} else {
					e.getComponent().setVisible(true);
					// You only need to cast once
				}
			}
		});
		
		
				JScrollBar scrollBar = new JScrollBar();
				mainWindow.getContentPane().add(scrollBar, "cell 1 0,alignx right,growy");

		final JTextPane indexWindow = new JTextPane();
		mainWindow.getContentPane().add(indexWindow, "flowx,cell 0 1,growx,aligny bottom");
		
				JButton addStockToList = new JButton("+");
				addStockToList.setToolTipText("Add a new symbol to the list");
				addStockToList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tickerListController.addStockToListGUI();
						updateTickerWindowGUI(tickerWindow, indexWindow);
						// FIXME add ticker window update method call``
					}
					});
				
						mainWindow.getContentPane().add(addStockToList, "cell 0 1,alignx right,aligny bottom");
		
		JButton removeStockFromList = new JButton("-");
		removeStockFromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tickerListController.removeStockFromListGUI();
				updateTickerWindowGUI(tickerWindow, indexWindow);
			}
		});
		removeStockFromList.setToolTipText("Remove a symbol to the list");
		mainWindow.getContentPane().add(removeStockFromList, "cell 0 1");

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
				if (main.shutdownWindow() == 0) System.exit(0); // Ends up calling hooks
			}
		});
		mnFile.add(mntmExit);

			JMenu mnSettings = new JMenu("Settings");
			menuBar.add(mnSettings);

		JMenuItem mntmSetRefresh = new JMenuItem("Set Refresh...");                               // REFRESH LIST //
		mntmSetRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmSetRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settingsProperties.setRefreshIntervalSecondsGUI();
				}
			});

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME move code to method
				updateTickerWindowGUI(tickerWindow, indexWindow);
			}
		});
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnSettings.add(mntmRefresh);
		mnSettings.add(mntmSetRefresh);

			JMenuItem mntmSetIndex = new JMenuItem("Set Index...");
		mntmSetIndex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmSetIndex.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					settingsProperties.setIndexSymbolGUI(); // Set it in settings only
					tickerListController.setIndexTickerSymbol(settingsProperties.getIndexSymbol());
				}
			});
			mnSettings.add(mntmSetIndex);

		JMenuItem mntmClearList = new JMenuItem("Clear List");
		mntmClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tickerListController.deleteListFIle(settingsProperties.getTickerListFilePath());
				// Deletes the main list file
				tickerListController.clearList();
				// Clears the

			}
		});
		mnSettings.add(mntmClearList);

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


}

