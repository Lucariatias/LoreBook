package io.github.lucariatias.lorebook.filter;

import java.io.File;
import java.io.FileFilter;


public class MarkdownFilter implements FileFilter {
	
	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(".md");
	}
	
}
