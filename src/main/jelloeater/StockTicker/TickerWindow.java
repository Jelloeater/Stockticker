package jelloeater.StockTicker;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
				System.out.println("hi");
			}
		});
		mainWindow.getContentPane().add(btnNewButton, "cell 0 1,alignx right,aligny bottom");
	}

	
}

