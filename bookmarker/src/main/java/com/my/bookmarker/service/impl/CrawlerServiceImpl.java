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
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
	public static final String WEB_DRIVER_PATH = "src/driver/chromedriver.exe"; //드라이버 경로
	
	private void setDriver() {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// cntBook 개수만큼 각 장르별로 책 정보를 크롤링
	@Override
	public List<Book> crawlBookInfo(int cntBook) {
		setDriver();
		
		// 크롤링 결과 저장을 위한 책 리스트 객체
		List<Book> booksInfo = new ArrayList<Book>();
		
		//크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();
		//브라우저가 눈에 보이지 않고 내부적으로 돈다.
		//설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
		// options.addArguments("headless");
		
		//위에서 설정한 옵션은 담은 드라이버 객체 생성
		//옵션을 설정하지 않았을 때에는 생략 가능하다.
		//WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);		
		
		//이동을 원하는 url
		String url = "https://www.ypbooks.co.kr/m_main.yp";
		
		//WebDriver을 해당 url로 이동한다.
		driver.get(url);
		
		//브라우저 이동시 생기는 로드시간을 기다린다.
		//HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		//tagName "span" 인 모든 태그를 가진 WebElement리스트를 받아온다.
		//WebElement는 html의 태그를 가지는 클래스이다.
		WebElement el1 = driver.findElement(By.xpath("//*[@id='main']/div/div/div[1]/div[1]/ul/li[2]/a/span"));
		el1.click();
		// 장르 베스트 후 종합 베스트를 위해 따로 저장
		String mem_url = driver.getCurrentUrl();
		//장르 부분 부터 시작 
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
				// 새 책 정보를 담을 책 객체
				Book newBook = new Book();
				
				genre_el2.get(k).click();
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				WebElement writer_g = driver.findElement(By.className("author"));
				WebElement title_g = driver.findElement(By.xpath("//*[@id='wrap']/div[1]/div/div[1]/div[1]/div/h3"));
				WebElement desc_g = driver.findElement(By.className("introduce"));
				
				// 책 정보 삽입
				newBook.setTitle(title_g.getText());
				newBook.setContent(desc_g.getText());
				newBook.setWriter(writer_g.getText());
				newBook.setGenres(genre);
				
				// 책 정보 리스트에 삽입
				booksInfo.add(newBook);
				
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				//뒤로 가기 버튼 
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
		
//		// 이제 best 목록 시작
//		driver.get(mem_url);
//		//1초 대기
//		try {Thread.sleep(1000);} catch (InterruptedException e) {}
//		//더보기 버튼 클릭을 위한 변수 
//		List<WebElement> el2 = driver.findElements(By.className("info-tit"));
//		String tmp_url = driver.getCurrentUrl();
//		HashMap<Integer,HashMap<String,String>> book = new HashMap();
//		HashMap<String,String> book_info = new HashMap();
//		int tmp = 0;
//		int count = 9;
//		for (int i = 0; i < el2.size(); i++) {
//			el2.get(i).click();
//			WebElement writer = driver.findElement(By.className("author"));
//			WebElement title = driver.findElement(By.xpath("//*[@id='wrap']/div[1]/div/div[1]/div[1]/div/h3"));
//			WebElement desc = driver.findElement(By.className("introduce"));
//			book_info.put("title",title.getText());
//			book_info.put("description",desc.getText());
//			book_info.put("writer",writer.getText());
//			book.put(++tmp,book_info);
//			System.out.println(tmp + "번 :"+ title.getText());
//			try {Thread.sleep(1000);} catch (InterruptedException e) {}
//			driver.get(tmp_url);
//			try {Thread.sleep(1000);} catch (InterruptedException e) {}
//			WebElement btn_more = driver.findElement(By.id("moreBtn"));
//			for(int j = 0; j < count; j++)
//				{
//					btn_more.click();
//					try {Thread.sleep(1000);} catch (InterruptedException e) {}
//				}
//			el2 = driver.findElements(By.className("info-tit"));
//		}

		try {
			//드라이버가 null이 아니라면
			if(driver != null) {
				//드라이버 연결 종료
				driver.close(); //드라이버 연결 해제
				
				//프로세스 종료
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
