package com.github.lukaszbudnik.filescloudperformance;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ClamfsTest {

	@Test(expected = FileNotFoundException.class)
	public void detectInfected() throws Exception {
		byte[] data = IOUtils.toByteArray(getClass().getResource("/eicar.txt"));

		File clamfsDirectory = new File("/clamfs/tmp");
		
		File f = new File(clamfsDirectory, "eicar.txt");
		f.deleteOnExit();
		FileUtils.writeByteArrayToFile(f, data);
		
		// clamfs performs scan on-access so we need to actually read it
		FileUtils.readFileToByteArray(f);
	}

}
