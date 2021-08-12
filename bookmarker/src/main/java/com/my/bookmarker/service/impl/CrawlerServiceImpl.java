package com.my.bookmarker.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.my.bookmarker.service.CrawlerService;
import com.my.bookmarker.vo.vanilla.Book;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //����̹� ID
	public static final String WEB_DRIVER_PATH = "src/driver/chromedriver.exe"; //����̹� ���
	
	private void setDriver() {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> crawlBookInfo(int cntBook) {
		setDriver();
		
		List<Book> booksInfo = new ArrayList<Book>();
		
		ChromeOptions options = new ChromeOptions();
		
		WebDriver driver = new ChromeDriver(options);		
		
		String url = "https://www.ypbooks.co.kr/m_main.yp";
		
		driver.get(url);
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		/*
		WebElement el1 = driver.findElement(By.xpath("//*[@id='main']/div/div/div[1]/div[1]/ul/li[2]/a/span"));
		el1.click();
		String mem_url = driver.getCurrentUrl();
		WebElement btn_gen = driver.findElement(By.xpath("//*[@id='fieldBest']/a/span"));
		btn_gen.click();
		List<WebElement> genre_el1= driver.findElements(By.cssSelector("#bestTab li"));
		String tmp_url_g = driver.getCurrentUrl();

		for (int i = 0; i < genre_el1.size(); i++)
		{
			String genre = genre_el1.get(i).getText();
			genre_el1.get(i).click();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			List<WebElement> genre_el2 = driver.findElements(By.className("info-tit"));
			int num = 2;
			for (int k = 0; k < cntBook; k++)
			{
				Book newBook = new Book();
				
				genre_el2.get(k).click();
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				WebElement writer_g = driver.findElement(By.className("author"));
				WebElement title_g = driver.findElement(By.xpath("//*[@id='wrap']/div[1]/div/div[1]/div[1]/div/h3"));
				WebElement desc_g = driver.findElement(By.className("introduce"));
				
				newBook.setTitle(title_g.getText());
				newBook.setContent(desc_g.getText());
				newBook.setWriter(writer_g.getText());
				newBook.setGenres(genre);
				
				booksInfo.add(newBook);
				
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				driver.navigate().back();
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				WebElement btn_more = driver.findElement(By.id("moreBtn"));
				for(int j = 0; j < num; j++)
				{
					btn_more.click();
					try {Thread.sleep(1000);} catch (InterruptedException e) {}
				}
				genre_el2 = driver.findElements(By.className("info-tit"));
			}
			driver.get(tmp_url_g);
			btn_gen = driver.findElement(By.xpath("//*[@id='fieldBest']/a/span"));
			btn_gen.click();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			genre_el1= driver.findElements(By.cssSelector("#bestTab li"));
		}
		
		
		driver.get(mem_url);
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		List<WebElement> el2 = driver.findElements(By.className("info-tit"));
		String tmp_url = driver.getCurrentUrl();
		HashMap<Integer,HashMap<String,String>> book = new HashMap();
		HashMap<String,String> book_info = new HashMap();
		int tmp = 0;
		int count = 9;
		for (int i = 0; i < el2.size(); i++) {
			el2.get(i).click();
			WebElement writer = driver.findElement(By.className("author"));
			WebElement title = driver.findElement(By.xpath("//*[@id='wrap']/div[1]/div/div[1]/div[1]/div/h3"));
			WebElement desc = driver.findElement(By.className("introduce"));
			book_info.put("title",title.getText());
			book_info.put("description",desc.getText());
			book_info.put("writer",writer.getText());
			book.put(++tmp,book_info);
			System.out.println(tmp + "�� :"+ title.getText());
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			driver.get(tmp_url);
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			WebElement btn_more = driver.findElement(By.id("moreBtn"));
			for(int j = 0; j < count; j++)
				{
					btn_more.click();
					try {Thread.sleep(1000);} catch (InterruptedException e) {}
				}
			el2 = driver.findElements(By.className("info-tit"));
		} 
 */

		try {
			if(driver != null) {
				driver.close(); 
				
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return booksInfo;
	}

	@Override
	public List<Book> crawlBookInfoByGenre(int cntBook) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
