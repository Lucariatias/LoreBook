package io.github.lucariatias.lorebook;

import java.awt.Image;
import java.io.File;

public class PageImpl implements Page {
	
	private Image bg;
	private boolean isCover;
	private File file;
	private PagePanel panel;
	
	public PageImpl(Image bg, boolean isCover, File file) {
		this.bg = bg;
		this.isCover = isCover;
		this.file = file;
	}
	
	public PageImpl(Image bg, File file) {
		this(bg, false, file);
	}
	
	public PageImpl() {}
	
	@Override
	public PagePanel getPanel() {
		panel = panel != null ? panel : new PagePanel(this);
		return panel;
	}

	@Override
	public Image getBackground() {
		return bg;
	}

	@Override
	public void setBackground(Image bg) {
		this.bg = bg;
	}
	
	@Override
	public boolean isCover() {
		return isCover;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}
	
}
