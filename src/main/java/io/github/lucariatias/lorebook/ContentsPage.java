package io.github.lucariatias.lorebook;

import java.awt.Image;


public class ContentsPage extends PageImpl {
	
	private Book book;
	
	private ContentsPagePanel panel;
	
	public ContentsPage(Book book, Image bg) {
		super(bg, false, null);
		this.book = book;
	}
	
	@Override
	public PagePanel getPanel() {
		panel = panel != null ? panel : new ContentsPagePanel(book, this);
		return panel;
	}
	
}
