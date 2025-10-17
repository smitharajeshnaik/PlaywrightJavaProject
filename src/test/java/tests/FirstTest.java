package tests;

import org.testng.annotations.Test;

import base.BaseTest;

public class FirstTest extends BaseTest {
	
	@Test
	public void verifyTitle() {
		page.navigate("https://amazon.com");
		System.out.println("My Page is " + page.title()); 
	}
}
	// public static void main(String[] args) {
	// Playwright playwright = Playwright.create();
	// Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	// Page page = browser.newPage();
	// page.navigate("https://amdocs.com");
	// System.out.println("Hello.. I am " + page.title());
	// browser.close();
	//	}
//}
