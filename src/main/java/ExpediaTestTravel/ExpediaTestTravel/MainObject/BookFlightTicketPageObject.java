package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.testcomponents.ExpediaFlight.FlightTicketPage;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

public class BookFlightTicketPageObject {
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
			String departingDate, String returningDate)
			throws InterruptedException {
		FlightTicketPage flightTicketPage = new FlightTicketPage();
		flightTicketPage.getFlightLink().click();
		flightTicketPage.getFlightFromTextField().type(fromFlight);
		flightTicketPage.getFlightToTextField().type(toFlight);
		flightTicketPage.getDepartingDateTextField().type(departingDate);
		flightTicketPage.getReturningDateTextField().type(returningDate);
		Thread.sleep(1000);
		flightTicketPage.getSearchButton().click();
	}

	/**
	 * open search Result page, do sorting and select flight
	 * 
	 * @param i
	 */
	public void bookFlight(int i) {
		SearchResultPage searchResultPage = new SearchResultPage();
		searchResultPage.getSortBySelectList().selectByIndex(i);
		searchResultPage.getResultsContainer(0).getSelectButton().click();
	}

}
