package com.my.bookmarker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.my.bookmarker.service.CrawlerService;
import com.my.bookmarker.vo.vanilla.Book;

@Service
public class CrawlerServiceImpl implements CrawlerService {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";
	static ChromeOptions options;
	static WebDriver driver;
	String url = "http://www.kyobobook.co.kr/indexKor.laf?mallGb=KOR&orderClick=c1a";

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

		options = new ChromeOptions();

		driver = new ChromeDriver(options);

		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		// 국내도서 해외도서가 필요할 경우 코드 수정 다시하겠습니다.
		// 국내도서 카테고리 큰칸
		List<WebElement> bcat_el = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul"));
		for (int i = 0; i < bcat_el.size(); i++) {
			// 첫번째 큰 카테고리의 원소들을 모으기 위해 !
			List<WebElement> mcat_el = driver
					.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li"));
			WebElement scat_bt;
			List<WebElement> scat_el;
			for (int j = 0; j < mcat_el.size(); j++) {
				try {
					scat_bt = driver.findElement(
							By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li[" + (j + 1) + "]/ul"));
					((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')", scat_bt);
					scat_el = driver.findElements(
							By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li[" + (j + 1) + "]/ul/li"));
					for (int k = 0; k < scat_el.size(); k++) {
						scat_el.get(k).click();
						System.out.println("error");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						booksInfo.addAll(get_info(driver, cntBook, booksInfo));
						driver.get(url);
						scat_bt = driver.findElement(
								By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li[" + (j + 1) + "]/ul"));
						((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')", scat_bt);
						scat_el = driver.findElements(
								By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li[" + (j + 1) + "]/ul/li"));
					}
				} catch (NoSuchElementException e) {
					scat_el = driver.findElements(
							By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li[" + (j + 1) + "]"));
					get_info(driver, cntBook, booksInfo);
				}
				mcat_el = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul[" + (i + 1) + "]/li"));
			}
			scat_el = driver.findElements(By.xpath("//*[@id='main_snb']/div[1]/ul"));
		}
		// 해외도서 부분 작성 해야하는데 지금 굳이 필요할지 모르겠음...

		try {
			if (driver != null) {
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

	public List<Book> get_info(WebDriver driver, int cntbook, List<Book> booksInfo) {
		int max = cntbook / 20;
		int page = 1;
		int cnt = 0;
		Book newBook = new Book();
		WebElement page_bt;
		List<WebElement> detail_enter_el = driver
				.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
		// for image and 19 예외처리
		List<WebElement> detail_19 = driver
				.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[1]/div/a"));
		// 책의 정보를 읽는 for 구문
		for (int o = 0; o < detail_enter_el.size(); o++) {
			// 제목은 일부러 따로 해놓는게 편해서 했음
			// 이 부분에 성인 19세 예외처리 부분
			if (detail_19.get(o).getAttribute("href").contains(" '19')"))
				o++;
			detail_enter_el.get(o).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			// 예외처리를 위해 리스트로 처리 정확히 책 정보를 들고오는 부분...
			// 필요한 정보는 여기서 수정 가능
			// 장르 변수
			String genre = "";
			List<WebElement> genre_el = driver.findElements(By.className("location"));

			for (int i = 0; i < genre_el.size(); i++) {
				genre = genre + "/" + genre_el.get(i).getText();
			}
			WebElement title = driver.findElement(By.xpath("//*[@id='container']/div[2]/form/div[1]/h1/strong"));
			WebElement src;
			if (title.getText().equals("향연: 사랑에 관하여")) {
				src = driver
						.findElement(By.xpath("//*[@id=\"container\"]/div[2]/form/div[2]/div[1]/div/img"));
			} else {
				src = driver
						.findElement(By.xpath("//*[@id='container']/div[2]/form/div[2]/div[1]/div/a/img"));

			}
			List<WebElement> desc = driver.findElements(By.className("box_detail_article"));
			List<WebElement> author = driver.findElements(By.className("name"));
			newBook.setTitle(title.getText());
			newBook.setContent(desc.get(0).getText());
			newBook.setWriter(author.get(0).getText());
			newBook.setGenres(genre);
			newBook.setImageUrl(src.getAttribute("src"));
			booksInfo.add(newBook);
			System.out.println(cnt + "번 객체 :" + newBook.toString());
			cnt++;
			driver.navigate().back();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (cnt == cntbook) {
				return booksInfo;
			}
			if (o == 19 && page <= max) {
				if (page == 1) {
					page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a"));
				} else {
					page_bt = driver.findElement(By.xpath("//*[@id='eventPaging']/div/a[2]"));
				}
				JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
				o = 0;
				page++;
				jscriptExecutor.executeScript("arguments[0].click();", page_bt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			detail_enter_el = driver.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[2]/div[1]"));
			detail_19 = driver.findElements(By.xpath("//*[@id='prd_list_type1']/li/div/div[1]/div[1]/div/a"));
		}
		return booksInfo;
	}
}