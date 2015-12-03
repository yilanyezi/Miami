package ExpediaTestTravel.ExpediaTestTravel.HotelBookEXT;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaHotel.SearchHotelPage;

/**
 * Extended class of SearchHotelPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
public class SearchHotelPageEXT extends SearchHotelPage{

	public SearchHotelPageEXT() {
		super();
	}

	public SearchHotelPageEXT(String siteLocale) {
		super(siteLocale);
	}
	
	/**
	 * open Web Site and maximization
	 */
	public void openWebsite() {
		// open driver and URL
		Grid.driver().get("http://expedia.com");
		// window size will be maximized
		Grid.driver().manage().window().maximize();
	}
	
	public void searchHotel(String hotelDestination, Date checkInDate, Date checkOutDate) {
		getHotelLink().click();
		this.getHotelDestinationTextField().type(hotelDestination);
		
		//calling wait method to check whether the element is present or not
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.results");
		getHotelDestinationLink().click();
		
		//Click and wait until the web element is visible or not
		getHotelCheckInTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(checkInDate);
		
		//Click and wait until the web element is visible or not
		getHotelCheckOutTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(checkOutDate);
		getSearchButton().click();
	}
	

}
