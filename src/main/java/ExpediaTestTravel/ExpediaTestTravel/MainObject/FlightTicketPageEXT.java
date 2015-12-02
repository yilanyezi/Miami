package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaFlight.FlightTicketPage;

public class FlightTicketPageEXT extends FlightTicketPage {

	public FlightTicketPageEXT() {
		super();
	}

	public FlightTicketPageEXT(String siteLocale) {
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
	

	/**
	 * Search flight and open the search result page
	 * 
	 * @param fromFlight
	 * @param toFlight
	 * @param departingDate
	 * @param returningDate
	 * @throws InterruptedException
	 */
	public void searchFlight(String fromFlight, String toFlight,
			Date departingDate, Date returningDate) {
		//FlightTicketPage flightTicketPage = new FlightTicketPage();
		getFlightLink().click();
		getFlightFromTextField().type(fromFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.results");
		getFlightFromLink().click();
		getFlightToTextField().type(toFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.results");
		getFlightToLink().click();
		
		getDepartingDateTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(departingDate);
		getReturningDateTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(returningDate);
		//WebDriverWaitUtils.waitUntilTextPresent(getReturningDateTextFieldValue());
		getSearchButton().click();
	}

}
