package ExpediaTestTravel.ExpediaTestTravel;

import org.testng.annotations.Test;

import ExpediaTestTravel.ExpediaTestTravel.MainObject.BookFlightTicketPageObject;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

/**
 * testCase for booking cheapest flight
 * 
 * @author mousumisen
 *
 */
public class BookCheapestFlightTest {

	private String fromFlight = "Beijing";
	private String toFlight = "San Francisco, CA, United States (QSF-All Airports)";
	private String departingDate = "2015/12/07";
	private String returningDate = "2015/12/28";
	private int indexofLowestPriceFlight = 0;

	ElementUtility elementUtility = new ElementUtility();
	BookFlightTicketPageObject bookFlightTicketPageObject = new BookFlightTicketPageObject();
	SearchResultPage searchResultPage = new SearchResultPage();

	@Test
	@WebTest
	public void SearchFlights() throws InterruptedException {
		bookFlightTicketPageObject.openWebsite();
		bookFlightTicketPageObject.searchFlight(fromFlight, toFlight,
				departingDate, returningDate);

		// select lowest price flight for departure
		bookFlightTicketPageObject.bookFlight(indexofLowestPriceFlight);

		// wait until the search result container is loaded
		WebDriverWaitUtils.waitUntilElementIsInvisible(searchResultPage
				.getResultsContainer().getText());

		// select lowest price flight for return
		bookFlightTicketPageObject.bookFlight(indexofLowestPriceFlight);

		// switch window
		elementUtility.switchWindow();

		// print successful message after successful completion of testcase
		System.out
				.println("Successfully selected cheapest Flight from Beijing to San Fran..");

	}

}
