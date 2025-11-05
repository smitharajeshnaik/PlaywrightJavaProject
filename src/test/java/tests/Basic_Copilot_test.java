package tests;

import org.testng.annotations.Test;

import base.BaseTest;


public class Basic_Copilot_test extends BaseTest {
	
	@Test
	public void copilotTest() {
		// Minimal test: open Google and print the page URL
		page.navigate("https://www.amazon.com");
		System.out.println("Opened URL: " + page.url());
		
	}

}