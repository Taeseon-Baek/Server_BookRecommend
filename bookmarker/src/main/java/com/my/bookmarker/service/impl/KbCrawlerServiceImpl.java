package com.my.bookmarker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.my.bookmarker.service.CrawlerService;
import com.my.bookmarker.vo.vanilla.Book;

public class KbCrawlerServiceImpl implements CrawlerService {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; 
	public static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver"; 
	
	private void setDriver() {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> crawlBookInfo(int cntBook) {
		// TODO Auto-generated method stub
		setDriver();
		List<Book> booksInfo = new ArrayList<Book>();
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		//KYOBO main page url
		String main_url = "http://www.kyobobook.co.kr/index.laf";
		driver.get(main_url);
		List<WebElement> bcat_el= driver.findElements(By.cssSelector("#header > div.navigation_bar > ul.gnb_main.add_ li"));
		for(int i = 0; i <bcat_el.size(); i++) {
			switch(bcat_el.get(i).getText())
			{
				case "국내소설":
					crawler_Bcat(bcat_el.get(i),driver);
				case "해외소설":
					break;
			}
		}
		
		
		return null;
	}

	@Override
	public List<Book> crawlBookInfoByGenre(int cntBook) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public List<Book> crawler_Bcat(WebElement Bcat, WebDriver driver)
	{
		Bcat.click();
		List<WebElement> mcat_el = driver.findElements(By.cssSelector("#main_snb > div.nav_category > ul li"));
		for(int i = 0; i < mcat_el.size(); i++)
		{
			System.out.println(mcat_el.get(i).getText());
		}
		return null;
	}
	

}
