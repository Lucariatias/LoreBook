package io.github.lucariatias.lorebook;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Page page;
	
	public PagePanel(Page page) {
		this.page = page;
		setLayout(null);
		if (page.getFile() != null) {
			try {
				Scanner scanner = new Scanner(page.getFile());
				StringBuilder contentBuilder = new StringBuilder();
				while (scanner.hasNextLine()) {
					contentBuilder.append(scanner.nextLine());
					contentBuilder.append('\n');
				}
				scanner.close();
				JLabel content = new JLabel(contentBuilder.toString());
				content.setHorizontalAlignment(page.isCover() ? SwingConstants.CENTER : SwingConstants.LEFT);
				content.setVerticalAlignment(page.isCover() ? SwingConstants.CENTER : SwingConstants.TOP);
				content.setForeground(page.isCover() ? Color.WHITE : Color.BLACK);
				content.setBounds(16, 16, 448, 608);
				add(content);
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	public Page getPage() {
		return page;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (page.getBackground() != null) {
			graphics.drawImage(page.getBackground(), 0, 0, null);
		}
	}
	
}
