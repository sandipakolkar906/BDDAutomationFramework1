package com.qa.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Sandip This class has common method to capture screenshot
 */
public class CaptureScreenshot {

	/**
	 * @param driver
	 * @return This method will capture the screenshot and return it in byte
	 *         format to embed in cucumber Report
	 */
	public static byte[] captureImage(WebDriver driver) {

		TakesScreenshot srcShot = (TakesScreenshot) driver;

		byte[] srcFile = srcShot.getScreenshotAs(OutputType.BYTES);

		return srcFile;

	}

}
