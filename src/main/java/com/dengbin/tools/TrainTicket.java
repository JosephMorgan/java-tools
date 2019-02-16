package com.dengbin.tools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Description: 12306火车票
 * @author dengbin
 * @date 2019年1月2日
 * @time 下午5:03:04
 */
public class TrainTicket {
	public WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type","--start-maximized","--disable-infobars");
		driver = new ChromeDriver(options);
//		driver.get("https://kyfw.12306.cn/otn/");
//		Cookie c1 = new Cookie("JSESSIONID", "FDE3B906769F2600D0ED80B351E32006");
//		Cookie c2 = new Cookie("tk", "CQ9kYyGmDPdKBBdQApf_o-UW3u3fYF-jffyvhKIL2TZ35PJtdq1210");
//		Cookie c3 = new Cookie("RAIL_EXPIRATION", "1546721462721");
//		Cookie c4 = new Cookie("RAIL_DEVICEID", "dRcidphWnc-1X9W6jlQUNwL4OfBx405kUnicpnheQQXblD2MLME_qw9L-tmX9TOsBt0kmjVRpumuvcrNrkLJCoXdSFdNd7sBWKz5g2qfA8_NLBghd2MZJvvCF_IOVlOPFCRzY9I9x0Q1OfEtyVlE8JGZdGaZFjCf");
//		Cookie c5 = new Cookie("route", "495c805987d0f5c8c84b14f60212447d");
//		Cookie c6 = new Cookie("BIGipServerotn", "401605130.38945.0000");
//		Cookie c7 = new Cookie("BIGipServerpool_passport", "300745226.50215.0000");
//		Cookie c8 = new Cookie("current_captcha_type", "Z");
//		driver.manage().addCookie(c1);
//		driver.manage().addCookie(c2);
//		driver.manage().addCookie(c3);
//		driver.manage().addCookie(c4);
//		driver.manage().addCookie(c5);
//		driver.manage().addCookie(c6);
//		driver.manage().addCookie(c7);
//		driver.manage().addCookie(c8);
		driver.get("https://kyfw.12306.cn/otn/resources/login.html");
		try {
			//用于手动登录
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get("https://kyfw.12306.cn/otn/leftTicket/init?linktypeid=dc");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void selectTicket() throws AWTException {
		Robot robot = new Robot();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("fromStationText"))).click();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("fromStationText"))).sendKeys("北京");
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("toStationText"))).click();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("toStationText"))).sendKeys("绵阳");
		robot.keyPress(KeyEvent.VK_ENTER);
				
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript("document.getElementById('train_date').removeAttribute('readonly');") ;
        new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("train_date"))).clear();
        new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("train_date"))).sendKeys("2019-01-26");		
	}
	
	@Test(invocationCount=1,threadPoolSize=1)
	public void buyTicket() throws AWTException {
		Set<Cookie> cookies= driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		driver.navigate().refresh();
		
		Robot robot = new Robot();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("fromStationText"))).click();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("fromStationText"))).sendKeys("北京");
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("toStationText"))).click();
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("toStationText"))).sendKeys("绵阳");
		robot.keyPress(KeyEvent.VK_ENTER);
				
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript("document.getElementById('train_date').removeAttribute('readonly');") ;
        new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("train_date"))).clear();
        new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("train_date"))).sendKeys("2019-01-26");	
		
		new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("query_ticket"))).click();
        
		//买票
        if(isElementPresent(By.xpath("//*[@id='ticket_240000G3070J']/td[13]/a"))) {//   *[@id='ticket_24000K13630S']/td[13]/a   *[@id='ticket_240000G3070J']/td[13]/a
        	SendMail.sendMailByQQ("123456@qq.com","123456@qq.com");//有票了就发邮件
        	new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ticket_240000G3070J']/td[13]/a"))).click();        
            new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("normalPassenger_0"))).click();     
            new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("submitOrder_id"))).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       System.out.println(new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("qr_submit_id"))).getText());
       new WebDriverWait(driver, 60, 1).until(ExpectedConditions.visibilityOfElementLocated(By.id("qr_submit_id"))).click();
        	
        }
	}
	
	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
	
    private boolean isElementPresent(By by) {
    	try {
        	driver.findElement(by);
        	return true;
		} catch (Exception e) {
			return false;
		}

    }

}