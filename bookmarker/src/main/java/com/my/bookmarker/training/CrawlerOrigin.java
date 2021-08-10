package com.my.bookmarker.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;

public class CrawlerOrigin {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //����̹� ID
	public static final String WEB_DRIVER_PATH = "src/driver/chromedriver.exe"; //����̹� ���
    public static void main(String[] args) {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//ũ�� ������ ���� ��ü ����
		ChromeOptions options = new ChromeOptions();
		//�������� ���� ������ �ʰ� ���������� ����.
		//�������� ���� �� ���� ũ�� â�� �����ǰ�, � ������ ����Ǵ��� Ȯ���� �� �ִ�.
		// options.addArguments("headless");
		
		//������ ������ �ɼ��� ���� ����̹� ��ü ����
		//�ɼ��� �������� �ʾ��� ������ ���� �����ϴ�.
		//WebDriver��ü�� �� �ϳ��� ������ â�̶� �����Ѵ�.
		WebDriver driver = new ChromeDriver(options);		
		
		//�̵��� ���ϴ� url
		String url = "https://www.ypbooks.co.kr/m_main.yp";
		
		//WebDriver�� �ش� url�� �̵��Ѵ�.
		driver.get(url);
		
		//������ �̵��� ����� �ε�ð��� ��ٸ���.
		//HTTP����ӵ����� �ڹ��� ������ �ӵ��� �� ������ ������ ���������� 1�ʸ� ����Ѵ�.
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		//tagName "span" �� ��� �±׸� ���� WebElement����Ʈ�� �޾ƿ´�.
		//WebElement�� html�� �±׸� ������ Ŭ�����̴�.
		WebElement el1 = driver.findElement(By.xpath("//*[@id='main']/div/div/div[1]/div[1]/ul/li[2]/a/span"));
		el1.click();
		// �帣 ����Ʈ �� ���� ����Ʈ�� ���� ���� ����
		String mem_url = driver.getCurrentUrl();
		//�帣 �κ� ���� ���� 
		WebElement btn_gen = driver.findElement(By.xpath("//*[@id='fieldBest']/a/span"));
		btn_gen.click();
		List<WebElement> genre_el1= driver.findElements(By.cssSelector("#bestTab li"));
		HashMap<Integer,HashMap<String,String>> gbook = new HashMap();
		HashMap<String,String> gbook_info = new HashMap();
		String tmp_url_g = driver.getCurrentUrl();

		for (int i = 0; i < genre_el1.size(); i++)
		{
			String genre = genre_el1.get(i).getText();
			genre_el1.get(i).click();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			List<WebElement> genre_el2 = driver.findElements(By.className("info-tit"));
			int tmp_g = 0;
			int num = 2;
			for (int k = 0; k < genre_el2.size(); k++)
			{
				genre_el2.get(k).click();
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				WebElement writer_g = driver.findElement(By.className("author"));
				WebElement title_g = driver.findElement(By.xpath("//*[@id='wrap']/div[1]/div/div[1]/div[1]/div/h3"));
				WebElement desc_g = driver.findElement(By.className("introduce"));
				gbook_info.put("title",title_g.getText());
				gbook_info.put("description",desc_g.getText());
				gbook_info.put("writer",writer_g.getText());
				gbook_info.put("genre",genre);
				gbook.put(++tmp_g,gbook_info);
				System.out.println(tmp_g + "�� :"+ desc_g.getText());
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				//�ڷ� ���� ��ư 
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
		// ���� best ��� ����
		driver.get(mem_url);
		//1�� ���
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		//������ ��ư Ŭ���� ���� ���� 
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

		try {
			//����̹��� null�� �ƴ϶��
			if(driver != null) {
				//����̹� ���� ����
				driver.close(); //����̹� ���� ����
				
				//���μ��� ����
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    }
}
