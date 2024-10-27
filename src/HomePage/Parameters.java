package HomePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String MosaferWebSiteURL = "https://www.almosafer.com/en";
	String ExpectedDEfaultLanguage = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	String ExpectedResult = "false";
	LocalDate TodayDate = LocalDate.now();
	int Today = TodayDate.getDayOfMonth();
	int Tomorrow = TodayDate.plusDays(1).getDayOfMonth();
	int TheDayAfterTomorrow = TodayDate.plusDays(2).getDayOfMonth();
	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadg" };
	String[] ArabicCities = { "دبي", "جدة" };
	int RandomEnglishCities = rand.nextInt(EnglishCities.length);
	int RandomArabicCities = rand.nextInt(ArabicCities.length);
	
	public void RandomlyChangeTheWebSiteLanguage () {
		
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomURL = rand.nextInt(URLs.length);

		driver.get(URLs[RandomURL]);
	}

	public void GeneralSetUp() {

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(MosaferWebSiteURL);

	}

}
