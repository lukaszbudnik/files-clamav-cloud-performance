package com.github.lukaszbudnik.filescloudperformance;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.philvarner.clamavj.ClamScan;
import com.philvarner.clamavj.ScanResult;
import com.philvarner.clamavj.ScanResult.Status;

public class ClamavjTest {

	@Test
	public void detectInfected() throws Exception {
		byte[] data = IOUtils.toByteArray(getClass().getResource("/eicar.txt"));

		ClamScan scanner = new ClamScan("localhost", 3310, 60000);

		ScanResult result = scanner.scan(data);
		Assert.assertTrue(result.getStatus() == Status.FAILED);
		Assert.assertEquals("stream: Eicar-Test-Signature FOUND",
				result.getResult());

	}
	
}
