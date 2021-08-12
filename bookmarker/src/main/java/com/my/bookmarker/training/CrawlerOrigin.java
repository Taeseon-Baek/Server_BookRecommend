package com.my.bookmarker.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class CrawlerOrigin {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";

	public static void main(String[] args) {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);

		String main_url = "http://www.kyobobook.co.kr/index.laf";
		driver.get(main_url);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		List<WebElement> bcat_el = driver.findElements(By.xpath("//*[@id='header']/div[3]/ul/li"));
		//국내도서 , 외국도서 url을 위해
		String tmp_url;
		for (int i = 0; i < bcat_el.size(); i++) {
			if (bcat_el.get(i).getText().equals("국내도서")) {
				bcat_el.get(i).click();
				tmp_url = driver.getCurrentUrl();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				List<WebElement> scat_el = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul"));
				for (int j = 0; j < scat_el.size(); j++) {
					List<WebElement> scat_el2 = driver
							.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li"));
					for (int k = 0; k < scat_el2.size(); k++) {
						try {
							WebElement scat_bt = driver.findElement(
									By.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul"));
							((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')",
									scat_bt);
							List<WebElement> scat_el3 = driver.findElements(By
									.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul/li"));
							for (int l = 0; l < scat_el3.size(); l++) {
								scat_el3.get(l).click();
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								List<WebElement> detail_enter_el = driver.findElements(
										By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
								int page = 1;
								// page 수 제한 거는 변수
								int max = 2;
								for (int o = 0; o < detail_enter_el.size(); o++) 
								{
									String title = detail_enter_el.get(o).getText();
									detail_enter_el.get(o).click();
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
									}
									// 예외처리를 위해 리스트로 처리
									List<WebElement> genre = driver.findElements(
											By.xpath("//*[@id='container']/div[5]/div[1]/div[3]/ul/li/a[3]"));
									List<WebElement> desc = driver.findElements(By.className("box_detail_article"));
									List<WebElement> author = driver.findElements(By.className("name"));
									System.out.println(o + "번 제목 :" + title);
									driver.navigate().back();
									try {
										Thread.sleep(3000);
									} catch (InterruptedException e) {
									}
									if(o == 19 && page < max)
									{
										WebElement page_bt;
										if(page == 1)
											page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a"));
										else
											page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a[2]"));
										page +=1;
										System.out.println(page);
										o = 0;
										page_bt.click();
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
										}
									}
									detail_enter_el = driver.findElements(
											By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
								}
							}
							driver.get(tmp_url);
							System.out.println(tmp_url);
						} catch (NoSuchElementException e) {
							
						}
					}

				}
			} else if (bcat_el.get(i).getText().equals("해외도서")) {

			}
		}
		try {
			if (driver != null) {
				driver.close();

				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
