package io.github.lucariatias.lorebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ContentsPagePanel extends PagePanel {
	
	private static final long serialVersionUID = 1L;

	public ContentsPagePanel(final Book book, Page page1) {
		super(page1);
		int yOffset = 16;
		for (final Page page : book.getPages()) {
			if (page.getFile() != null) {
				final JButton button = new JButton(page.getFile().getName().replace(".html", ""));
				button.setBounds(128, yOffset, 224, 32);
				yOffset += 32;
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						book.turnToPage(page);
					}
					
				});
				add(button);
			}
		}
	}
	
}
