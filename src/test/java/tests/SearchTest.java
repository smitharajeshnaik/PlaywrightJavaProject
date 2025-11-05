package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Arrays;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void searchJacket() {
        // Navigate to Amazon
        page.navigate("https://www.amazon.com");

        // Try to dismiss cookie / regional modal if present
        try {
            page.locator("#sp-cc-accept").click(new Locator.ClickOptions().setTimeout(2000));
        } catch (Exception ignored) {
        }

        // Try multiple selectors to find the search box
        List<String> selectors = Arrays.asList(
                "#twotabsearchtextbox",
                "input[name='field-keywords']",
                "input[aria-label*='Search']",
                "input[placeholder*='Search']"
        );

        String found = null;
        for (String sel : selectors) {
            try {
                page.waitForSelector(sel, new Page.WaitForSelectorOptions()
                        .setTimeout(3000)
                        .setState(WaitForSelectorState.VISIBLE));
                found = sel;
                break;
            } catch (Exception e) {
                // try next
            }
        }

        if (found == null) {
            try {
                page.waitForSelector("header input[type='text']", new Page.WaitForSelectorOptions()
                        .setTimeout(3000)
                        .setState(WaitForSelectorState.VISIBLE));
                found = "header input[type='text']";
            } catch (Exception e) {
                Assert.fail("Search box not found on Amazon page. Tried selectors: " + selectors);
            }
        }

        Locator searchBox = page.locator(found);
        // Focus, fill, and press Enter to submit the search
        searchBox.focus();
        searchBox.fill("jacket");
        searchBox.press("Enter");

        // Wait for results container
        page.waitForSelector("div.s-main-slot", new Page.WaitForSelectorOptions().setTimeout(10000).setState(WaitForSelectorState.VISIBLE));

        // Basic assertions: either URL or title or results container indicates a search
        String url = page.url().toLowerCase();
        String title = page.title().toLowerCase();
        boolean hasResults = page.locator("div.s-main-slot").count() > 0;

        Assert.assertTrue(url.contains("k=") || title.contains("jacket") || hasResults,
                "Expected search results for 'jacket' (url/title/results). url=" + url + " title=" + title + " resultsCount=" + page.locator("div.s-main-slot").count());
    }
}
