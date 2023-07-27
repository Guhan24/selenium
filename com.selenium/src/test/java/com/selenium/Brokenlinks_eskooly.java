package com.selenium;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinks_eskooly {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(co);
		driver.get("https://eskooly.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				try {
					HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
					connection.setRequestMethod("HEAD");
					connection.connect();
					int responseCode = connection.getResponseCode();
					if (responseCode >= 400) {
						System.out.println("Broken link found: " + url + " (Response Code: " + responseCode + ")");
						// Handle the broken link as needed (e.g., log or take a screenshot)
					}
				} catch (IOException e) {
					System.out.println("Exception occurred while checking link: " + url);
					e.printStackTrace();
				}
			}
		}
		{
			//				TakesScreenshot obj =(TakesScreenshot)driver;
			//				File source = obj.getScreenshotAs(OutputType.FILE);
			//				File destination =new File(s);
			//				org.openqa.selenium.io.FileHandler.copy(source, destination);
			//	
			//		        }

			driver.quit();



		}

	}}
