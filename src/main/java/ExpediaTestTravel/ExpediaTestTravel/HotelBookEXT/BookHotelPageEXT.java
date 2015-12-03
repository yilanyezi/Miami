package ExpediaTestTravel.ExpediaTestTravel.HotelBookEXT;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.testcomponents.ExpediaHotel.BookHotelPage;

/**
 * Extended class of BookHotelPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
public class BookHotelPageEXT extends BookHotelPage {

	public BookHotelPageEXT() {
		super();
	}

	public BookHotelPageEXT(String siteLocale) {
		super(siteLocale);
	}
	
	/**
	 * Booking your final hotel selection and go to payment page
	 * @throws InterruptedException
	 */
	public void bookFinalHotel() {
		getBookHotelButton().click();
		getSearchedHotelContainer().getBookFinalHotelButton().clickAndExpect(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#pay-later-button")));
		getPayLaterOptionButton().clickAndExpect(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#payments")));
		System.out.println("Successfully booking cheapest Hotel");
	}
	
	
}
