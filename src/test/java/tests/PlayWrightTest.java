package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@Test
public class PlayWrightTest {
	 public static void main(String[] args) {
		  Playwright playwright = Playwright.create();
		 Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
		  Page page = browser.newPage();
		  page.navigate("https://amdocs.com");
		  System.out.println("Hello.. I am " + page.title());
		  browser.close();
		 	}
	 }

