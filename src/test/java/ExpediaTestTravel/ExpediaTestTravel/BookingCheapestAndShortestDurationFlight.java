package ExpediaTestTravel.ExpediaTestTravel;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExpediaTestTravel.ExpediaTestTravel.MainObject.FlightTicketPageEXT;
import ExpediaTestTravel.ExpediaTestTravel.MainObject.SearchResultPageEXT;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

/**
 * testCase for booking shortest duration flight
 * 
 * @author mousumisen
 *
 */
public class BookingCheapestAndShortestDurationFlight {

	private String fromFlight = "Beij";
	private String toFlight = "san fra";
	private Date departureDate;
	private Date returningDate;
	private int journeyStartDay = 21;
	private String journeyDuration = "15h0m";
	private int indexofLowestPriceFlight = 0;
	private int indexofShortestFlight = 2;
	
	FlightTicketPageEXT flightTicketPageEXT = new FlightTicketPageEXT();
	SearchResultPageEXT searchResultPageEXT = new SearchResultPageEXT();

	@BeforeTest
	public void init() {
		Date departureDate = this.genarateNextMonday();
		Date returningDate = this.genarateReturnDay(departureDate, journeyStartDay);
		this.departureDate = departureDate;
		this.returningDate = returningDate;
	}

	@Test
	@WebTest
	public void selectTheShorterAndLowestFlight() {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofLowestPriceFlight);
		searchResultPageEXT.selectShorterFlight(journeyDuration);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		searchResultPageEXT.selectShorterFlight(journeyDuration);
		searchResultPageEXT.clickDeclineHotelBookingLink();
		System.out.println("Successfully selected Shortest Duration Flight from Beijing to San Fran. under duration 15 hours.");
	}
	
	@Test
	@WebTest
	public void selectCheapestFlight() {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofLowestPriceFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModuleList");
		
		searchResultPageEXT.selectFirstFlight();
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.title-city-text");
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		
		searchResultPageEXT.selectFirstFlight();
		searchResultPageEXT.clickDeclineHotelBookingLink();
	}
	
	@Test
	@WebTest
	public void selectShortestFlight() {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofShortestFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModuleList");
		
		searchResultPageEXT.selectFirstFlight();
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.title-city-text");
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		
		searchResultPageEXT.selectFirstFlight();
		searchResultPageEXT.clickDeclineHotelBookingLink();
		
	}

	public Date genarateNextMonday() {
		Date today = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(today);
		ca.set(Calendar.WEEK_OF_YEAR, ca.get(Calendar.WEEK_OF_YEAR) + 1);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return ca.getTime();
	}

	public Date genarateReturnDay(Date date, int during) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_YEAR, during);
		return ca.getTime();
	}
	
	public void searchFlightforBooking() {
		flightTicketPageEXT.openWebsite();

		flightTicketPageEXT.searchFlight(fromFlight, toFlight, departureDate, returningDate);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		
	}

}
