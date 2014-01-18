package io.github.lucariatias.lorebook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LoreBookFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoreBookFrame frame = new LoreBookFrame();
					frame.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public LoreBookFrame() {
		setTitle("LoreBook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 708);
		setResizable(false);
		contentPane = new LoreBookPanel();
		setContentPane(contentPane);
	}
	
}
