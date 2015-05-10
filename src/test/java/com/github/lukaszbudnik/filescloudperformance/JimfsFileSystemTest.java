package com.github.lukaszbudnik.filescloudperformance;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

public class JimfsFileSystemTest {

	@Test
	public void smallFiles() throws IOException {
		byte[] data = IOUtils.toByteArray(getClass().getResource("/test-resource.xml"));

		FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
		
		long before = System.currentTimeMillis();
		
		
		for (int i = 0; i < 10000; i++) {
			Path p = fs.getPath(UUID.randomUUID().toString());
			Files.write(p, data, StandardOpenOption.CREATE_NEW);
		}
		
		long after = System.currentTimeMillis();
		
		System.out.println("Time taken to save 1000 files " + data.length + " took " + (after - before) + " ms ");
	}
	
	@Test
	public void largeFiles() throws IOException {
		byte[] data = new byte[1024 * 1024];
		new Random().nextBytes(data);

		FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
		
		Runtime rt = Runtime.getRuntime();
		
		long before = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			Path p = fs.getPath(UUID.randomUUID().toString());
			Files.write(p, data, StandardOpenOption.CREATE_NEW);
		}
		
		long after = System.currentTimeMillis();
		long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
		
		System.out.println("Time taken to save 100 files " + data.length + 
				" took " + (after - before) + " ms " +
				" memory used " + usedMB);
	}
	
	
}
