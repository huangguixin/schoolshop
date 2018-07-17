package com.gk.thum;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class ThunTest {

	@Test
	public void test() throws Exception {
		File file = new File("D:\\hgx\\12.jpg");
		Thumbnails.of(new FileInputStream(file)).size(60, 60).toFile(new File("D:\\hgx\\121.png"));
		
	}



}
