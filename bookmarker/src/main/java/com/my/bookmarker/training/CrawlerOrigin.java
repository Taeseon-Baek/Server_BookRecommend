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
	static ChromeOptions options = new ChromeOptions();
	static WebDriver driver = new ChromeDriver(options);
	static String tmp_url;

	public static void main(String[] args) {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String main_url = "http://www.kyobobook.co.kr/index.laf";
		driver.get(main_url);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		List<WebElement> bcat_el = driver.findElements(By.xpath("//*[@id='header']/div[3]/ul/li"));
		// 국내도서 , 외국도서 url을 위해
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
					WebElement scat_bt;
					List<WebElement> scat_el3;
					for (int k = 0; k < scat_el2.size(); k++) {
						try {
							scat_bt = driver.findElement(
									By.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul"));
							((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')",
									scat_bt);
							scat_el3 = driver.findElements(By
									.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul/li"));
							for (int l = 0; l < scat_el3.size(); l++) {
								scat_el3.get(3).click();
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								get_info(driver);
								scat_bt = driver.findElement(By.xpath(
										"//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul"));
								((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')",
										scat_bt);
								scat_el3 = driver.findElements(By.xpath(
										"//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul/li"));
							}
						} catch (NoSuchElementException e) {
							scat_el2.get(k).click();
							get_info(driver);
							scat_bt = driver.findElement(
									By.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul"));
							((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')",
									scat_bt);
							scat_el3 = driver.findElements(By
									.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li[" + (k + 1) + "]/ul/li"));
						}
						scat_el2 = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul[" + (j + 1) + "]/li"));
					}
					scat_el = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul"));
				}
				// 해외도서 부분 작성 해야하는데 지금 굳이 필요할지 모르겠음...
			}
			break;
		}
		try {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
		} catch (Exception e) {

		}
	}

	// 책 분류에 들어간 책 리스트 페이지에 들어가 책 하나하나의 정보를 들고 오는 함수 ..
	public static void get_info(WebDriver driver) {
		List<WebElement> detail_enter_el = driver
				.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
		//for image and 19 예외처리
		List<WebElement> detail_src = driver
				.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[1]/div/a"));
		// 페이지 변수
		int page = 1;
		// page 수 제한 거는 변수
		int max = 1;
		// 페이지 넘어가기 위한 변수
		WebElement page_bt;
		// 책의 정보를 읽는 for 구문
		for (int o = 0; o < detail_enter_el.size(); o++) {
			// 제목은 일부러 따로 해놓는게 편해서 했음
			String title = detail_enter_el.get(o).getText();
			// 이 부분에 성인 19세 예외처리 부분
			if(detail_src.get(o).getAttribute("href").contains("19"))
				o++;
			detail_enter_el.get(o).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// 예외처리를 위해 리스트로 처리 정확히 책 정보를 들고오는 부분...
			// 필요한 정보는 여기서 수정 가능
			List<WebElement> genre = driver
					.findElements(By.xpath("//*[@id='container']/div[5]/div[1]/div[3]/ul/li/a[3]"));
			List<WebElement> desc = driver.findElements(By.className("box_detail_article"));
			List<WebElement> author = driver.findElements(By.className("name"));
			System.out.println(o + "번 제목 :" + title);
			driver.navigate().back();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			// 몇 page를 크롤링 할지 정한다.
			// max라는 변수가 제한 하는 숫자이고 이걸 우리가 컨트롤 하면 됨
			if (o == 19 && page < max) {
				if (page == 1) {
					page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a"));
				} else {
					page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a[2]"));
				}
				JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
				page += 1;
				System.out.println(page);
				o = 0;
				jscriptExecutor.executeScript("arguments[0].click();", page_bt);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} else if (page == max && o == 19) {
				driver.get(tmp_url);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				break;
			}
			detail_enter_el = driver.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
			detail_src = driver
				.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[1]/div/a"));
		}
		System.out.println("complete");
	}
}
