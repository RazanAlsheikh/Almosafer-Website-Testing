package HomePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends Parameters {

	@BeforeTest
	public void SetUp() {

		GeneralSetUp();

		// Change country message which appeared once we open AlmorasefWebSite :
		WebElement GreenButtonMessage = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		GreenButtonMessage.click();
	}

	@Test(priority = 1)
	public void checkDefaultLanguageIsEnglish() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedDEfaultLanguage);
	}

	@Test(priority = 2)
	public void CheckDefaultCurrency() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid ='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() {

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {

		WebElement Footer = driver.findElement(By.tagName("footer"));

		boolean ActualResult = Footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 5)
	public void CheckSaysTabIsnotSelected() {

		WebElement StaysTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualResult = StaysTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test(priority = 6)
	public void CheckDepartureDate() {

		List<WebElement> DepartureAndReturnDate = driver.findElements(By.className("hNjEjT"));

		String ActualDepartureDate = DepartureAndReturnDate.get(0).getText();
		String ActualReturnDate = DepartureAndReturnDate.get(1).getText();

		int ActualDepartureDateAsInt = Integer.parseInt(ActualDepartureDate);
		int ActualReturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepartureDateAsInt, Tomorrow);
		Assert.assertEquals(ActualReturnDateAsInt, TheDayAfterTomorrow);
	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {

		RandomlyChangeTheWebSiteLanguage();
	}

	@Test(priority = 8)
	public void FillStayTab() {

		WebElement StaysTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		StaysTab.click();

		WebElement SearchStaysField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		String WebsiteURL = driver.getCurrentUrl();

		if (WebsiteURL.contains("ar")) {
			SearchStaysField.sendKeys(ArabicCities[RandomArabicCities]);
		}

		else {
			SearchStaysField.sendKeys(EnglishCities[RandomEnglishCities]);
		}

		WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement FirstResult = ListOfCities.findElements(By.tagName("li")).get(1);
		FirstResult.click();
	}

	@Test(priority = 9)
	public void RandomlySelectNumbersOfVisitors() {

		WebElement SelectorOfVisitors = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorOfVisitors);

		int RandomIndex = rand.nextInt(2);
		select.selectByIndex(RandomIndex);

		WebElement SearchStaysButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchStaysButton.click();
	}

	@Test(priority = 10)
	public void CheckLoadingBarFullyCompleted() throws InterruptedException {

		boolean ExpectedResult = true;

		Thread.sleep(18000);
		String LoadingBarResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']")).getText();

		boolean Results = LoadingBarResult.contains("مكان إقامة") || LoadingBarResult.contains("found");

		Assert.assertEquals(Results, ExpectedResult);
	}
}
