package io.github.lucariatias.lorebook;

import java.awt.Image;
import java.io.File;

public interface Page {
	
	public PagePanel getPanel();
	
	public boolean isCover();
	
	public Image getBackground();
	
	public void setBackground(Image bg);
	
	public File getFile();
	
	public void setFile(File file);
	
}
