package io.github.lucariatias.lorebook;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoreBookPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private JLabel leftPageNumber;
	private JLabel rightPageNumber;
	
	private JButton btnNextPage;
	private JButton btnPreviousPage;
	
	private Book book;
	
	public LoreBookPanel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (InstantiationException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
		}
		setBounds(0, 0, 960, 672);
		setLayout(null);
		leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 480, 640);
		add(leftPanel);
		rightPanel = new JPanel();
		rightPanel.setBounds(480, 0, 480, 640);
		add(rightPanel);
		
		btnNextPage = new JButton("Next page");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				turnPageForward();
			}
		});
		btnNextPage.setBounds(843, 643, 117, 29);
		add(btnNextPage);
		
		btnPreviousPage = new JButton("Previous page");
		btnPreviousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				turnPageBackward();
			}
		});
		btnPreviousPage.setBounds(0, 643, 117, 29);
		add(btnPreviousPage);
		
		leftPageNumber = new JLabel("");
		leftPageNumber.setBounds(129, 648, 61, 16);
		add(leftPageNumber);
		
		rightPageNumber = new JLabel("");
		rightPageNumber.setBounds(770, 652, 61, 16);
		add(rightPageNumber);
		book = new BookImpl(this);
		try {
			loadBackgrounds();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		try {
			book.loadPages();
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (FontFormatException exception) {
			exception.printStackTrace();
		}
		refreshPages();
	}
	
	public void loadBackgrounds() throws IOException {
		book.setCoverImage(ImageIO.read(getClass().getResourceAsStream("/cover.png")));
		book.setPageImage(ImageIO.read(getClass().getResourceAsStream("/page.png")));
	}
	
	public void turnPageForward() {
		book.turnPageForward();
		refreshPages();
	}
	
	public void turnPageBackward() {
		book.turnPageBackward();
		refreshPages();
	}
	
	public void refreshPages() {
		remove(leftPanel);
		remove(rightPanel);
		if (book.getLeftPage() != null) {
			book.getLeftPage().getPanel().setBounds(0, 0, 480, 640);
			leftPanel = book.getLeftPage().getPanel();
		}
		if (book.getRightPage() != null) {
			book.getRightPage().getPanel().setBounds(480, 0, 480, 640);
			rightPanel = book.getRightPage().getPanel();
		}
		leftPageNumber.setText(book.getLeftPageNumber() + " | page");
		rightPageNumber.setText("page | " + book.getRightPageNumber());
		add(leftPanel);
		add(rightPanel);
		btnNextPage.setEnabled(book.getRightPageNumber() < book.getPages().size() - 1);
		btnPreviousPage.setEnabled(book.getLeftPageNumber() > 0);
		repaint();
	}
}
