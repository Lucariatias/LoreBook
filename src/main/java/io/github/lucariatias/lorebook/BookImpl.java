package io.github.lucariatias.lorebook;

import io.github.lucariatias.lorebook.filter.MarkdownFilter;

import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.petebevin.markdown.MarkdownProcessor;


public class BookImpl implements Book {
	
	private LoreBookPanel panel;
	
	private File pageDirectory = new File(System.getProperty("user.home") + File.separator + ".lorebook" + File.separator + "books" + File.separator + "book" + File.separator + "pages");
	
	private Image coverImage;
	private Image pageImage;
	
	private List<Page> pages = new ArrayList<Page>();
	private int leftPageNumber = 0;
	private int rightPageNumber = 1;
	
	public BookImpl(LoreBookPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void loadPages() throws IOException, FontFormatException {
		if (!pageDirectory.exists()) {
			pageDirectory.mkdirs();
		}
		for (File file : pageDirectory.listFiles(new MarkdownFilter())) {
			pages.add(new PageImpl(pageImage, parseMarkdown(file)));
		}
		pages.add(0, new ContentsPage(this, pageImage));
		loadFrontCover();
		loadBackCover();
	}
	
	private void loadFrontCover() throws IOException {
		File coverDirectory = new File(pageDirectory, "covers");
		File file = new File(coverDirectory, "front.md");
		if (file.exists()) {
			pages.add(0, new PageImpl());
			pages.add(1, new PageImpl(coverImage, true, parseMarkdown(file)));
		}
	}
	
	private void loadBackCover() throws IOException {
		File coverDirectory = new File(pageDirectory, "covers");
		File file = new File(coverDirectory, "back.md");
		if (file.exists()) {
			if (pages.size() % 2 != 0) {
				pages.add(new PageImpl(pageImage, false, null));
			}
			pages.add(new PageImpl(coverImage, true, parseMarkdown(file)));
			pages.add(new PageImpl());
		}
	}
	
	private File parseMarkdown(File mdFile) throws IOException {
		Scanner scanner = new Scanner(mdFile);
		StringBuilder mdBuilder = new StringBuilder();
		while (scanner.hasNextLine()) {
			mdBuilder.append(scanner.nextLine());
			mdBuilder.append('\n');
		}
		scanner.close();
		File htmlFile = new File(mdFile.getParent(), mdFile.getName().replace(".md", ".html"));
		MarkdownProcessor mdProcessor = new MarkdownProcessor();
		FileWriter writer = new FileWriter(htmlFile);
		writer.write("<html>\n" + mdProcessor.markdown(mdBuilder.toString()) + "\n</html>");
		writer.close();
		return htmlFile;
	}
	
	@Override
	public List<Page> getPages() {
		return pages;
	}
	
	@Override
	public Page getLeftPage() {
		return leftPageNumber < pages.size() ? pages.get(leftPageNumber) : null;
	}
	
	@Override
	public Page getRightPage() {
		return rightPageNumber < pages.size() ? pages.get(rightPageNumber) : null;
	}
	
	@Override
	public int getLeftPageNumber() {
		return leftPageNumber;
	}
	
	@Override
	public int getRightPageNumber() {
		return rightPageNumber;
	}
	
	@Override
	public void turnPageForward() {
		rightPageNumber += 2;
		leftPageNumber += 2;
		panel.refreshPages();
	}
	
	@Override
	public void turnPageBackward() {
		rightPageNumber -= 2;
		leftPageNumber -= 2;
		panel.refreshPages();
	}
	
	@Override
	public void turnToPage(Page page) {
		leftPageNumber = 0;
		rightPageNumber = 1;
		while (getLeftPage() != page && getRightPage() != page) {
			turnPageForward();
		}
		panel.refreshPages();
	}

	@Override
	public Image getCoverImage() {
		return coverImage;
	}

	@Override
	public void setCoverImage(Image coverImage) {
		this.coverImage = coverImage;
	}

	@Override
	public Image getPageImage() {
		return pageImage;
	}

	@Override
	public void setPageImage(Image pageImage) {
		this.pageImage = pageImage;
	}
	
}
