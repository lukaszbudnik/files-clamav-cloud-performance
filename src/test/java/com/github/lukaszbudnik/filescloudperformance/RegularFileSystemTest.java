package com.github.lukaszbudnik.filescloudperformance;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class RegularFileSystemTest {

	@Test
	public void smallFiles() throws IOException {
		byte[] data = IOUtils.toByteArray(getClass().getResource("/test-resource.xml"));
		long before = System.currentTimeMillis();
		
		for (int i = 0; i < 10000; i++) {
			File f = File.createTempFile("prefix", "suffix");
			f.deleteOnExit();
			FileUtils.writeByteArrayToFile(f, data);
			byte[] dataRead = FileUtils.readFileToByteArray(f);
			Assert.assertEquals(data.length, dataRead.length);
		}
		
		long after = System.currentTimeMillis();
		
		System.out.println("Time taken to save 1000 files " + data.length + " took " + (after - before) + " ms ");
	}
	
	@Test
	public void largeFiles() throws IOException {
		byte[] data = new byte[1024 * 1024];
		new Random().nextBytes(data);

		Runtime rt = Runtime.getRuntime();
		
		long before = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			File f = File.createTempFile("prefix", "suffix");
			f.deleteOnExit();
			FileUtils.writeByteArrayToFile(f, data);
			byte[] dataRead = FileUtils.readFileToByteArray(f);
			Assert.assertEquals(data.length, dataRead.length);
		}
		
		long after = System.currentTimeMillis();
		long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
		
		System.out.println("Time taken to save 100 files " + data.length + 
				" took " + (after - before) + " ms " +
				" memory used " + usedMB);
	}
	
}
