/*
package com.apartment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class LoadTest {
  @Test
  public void f() {
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
*/

package com.apartment;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoadTest {
 public static String driverPath = "/Users/dhungana/Downloads/chromedriver";
// public static WebDriver driver;
// System.setProperty("webdriver.chrome.driver", "/Users/dhungana/Downloads/chromedriver/");

//	System.out.println("launching chrome browser");
//	System.setProperty("webdriver.chrome.driver", driverPath);
//	 
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.apartments.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoad() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("quickSearchLookup")).clear();
    driver.findElement(By.id("quickSearchLookup")).sendKeys("Irving, TX");
    driver.findElement(By.xpath("//div[@id='minMaxRangeControl']/div/a/span")).click();
    driver.findElement(By.cssSelector("input.minRentInput")).clear();
    driver.findElement(By.cssSelector("input.minRentInput")).sendKeys("500");
    driver.findElement(By.cssSelector("input.maxRentInput")).clear();
    driver.findElement(By.cssSelector("input.maxRentInput")).sendKeys("1000");
    driver.findElement(By.id("quickSearchPropertyStyles")).click();
    driver.findElement(By.cssSelector("a.go > span")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
//    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
