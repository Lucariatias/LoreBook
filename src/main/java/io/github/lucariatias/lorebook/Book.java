package io.github.lucariatias.lorebook;

import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

public interface Book {
	
	public void loadPages() throws IOException, FontFormatException;
	
	public List<Page> getPages();
	
	public Page getLeftPage();
	
	public Page getRightPage();
	
	public int getLeftPageNumber();
	
	public int getRightPageNumber();

	public void turnPageForward();
	
	public void turnPageBackward();
	
	public void turnToPage(Page page);
	
	public Image getCoverImage();
	
	public void setCoverImage(Image coverImage);
	
	public Image getPageImage();
	
	public void setPageImage(Image pageImage);
	
}
