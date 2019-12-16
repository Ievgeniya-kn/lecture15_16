package com.lecture16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SeleniumTest {

    private WebDriver webDriverChrome;

    private List<UrlToTitle> mappingUrlToTitle;
    private String currentTitle;
    private String currentURL;


    @BeforeClass
    public void beforeClassSetup() {
        WebDriverManager.chromedriver().setup();
        webDriverChrome = new ChromeDriver();

        mappingUrlToTitle = new ArrayList<UrlToTitle>();
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/", "SeleniumHQ Browser Automation"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/about/", "About Selenium"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/projects/", "Selenium Projects"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/downloads/", "Downloads"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/documentation/en/", "The Selenium Browser Automation Project :: Documentation for Selenium"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/support/", "Selenium Support"));
        mappingUrlToTitle.add(new UrlToTitle("https://selenium.dev/blog/", "Selenium Blog"));
    }

    @AfterClass
    public void afterClassClean() {
        webDriverChrome.quit();
    }

    @Test(priority = 1)
    public void verifyArrayURLEqualsTitle() {

        for (UrlToTitle url : mappingUrlToTitle) {
            try {
                verifyTitleForLink(url.getUrl(), url.getTitle());
            } catch (AssertionError err) {
                System.out.println("Just a  try to have all Titles w/o breakdown");
                System.out.println("Unexpected title for URL " + url.getUrl());
                err.printStackTrace();
            }
        }
    }

    @Test(priority = 2)
    public void verifyNavigateBack() {
        String baseUrl;
        baseUrl = mappingUrlToTitle.get(0).getUrl();
        webDriverChrome.get(baseUrl);

        List<UrlToTitle> mappingUrlToTitleNew;
        mappingUrlToTitleNew = mappingUrlToTitle.subList(1, 7);

        mappingUrlToTitleNew.forEach(urlToTitle -> {
            verifyNavigateBackToMainPage(urlToTitle.getUrl(), baseUrl);
        });

    }

    private void verifyNavigateBackToMainPage(String url, String baseURL) {
        webDriverChrome.get(url);
        System.out.println("url " + url);
        webDriverChrome.navigate().back();
        System.out.println("Back to " + webDriverChrome.getCurrentUrl());
        currentURL = webDriverChrome.getCurrentUrl();
        assertEquals(baseURL, currentURL);

    }

    private void verifyTitleForLink(String url, String expiredTitle) {
        webDriverChrome.get(url);
        currentTitle = webDriverChrome.getTitle();
        assertEquals(currentTitle, expiredTitle);
    }


}
