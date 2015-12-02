package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Date;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.html.Label;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaFlight.FlightTicketPage;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

public class BookFlightTicketPageObject {
	
	
	private SearchResultPage searchResultPage ;
	/**
	 * open Web Site and maximization
	 */
	public void openWebsite() {
		// open driver and URL
		Grid.driver().get("https://www.expedia.com.hk/");
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
			Date departingDate, Date returningDate)
			{
		FlightTicketPage flightTicketPage = new FlightTicketPage();
		flightTicketPage.getFlightLink().click();
		flightTicketPage.getFlightFromTextField().type(fromFlight);
		flightTicketPage.getFlightToTextField().type(toFlight);
		flightTicketPage.getDepartingDateTextField().click();
		WebDriverWaitUtils.waitUntilElementIsVisible("css=.cal");
		flightTicketPage.getDepartingAndReturningExpediaDatePicker().set(departingDate);
		flightTicketPage.getReturningDateTextField().click();
		WebDriverWaitUtils.waitUntilElementIsVisible("css=.cal");
		flightTicketPage.getDepartingAndReturningExpediaDatePicker().set(returningDate);
		flightTicketPage.getSearchButton().click();
	}
	
	/**
	 * open search Result page, do sorting flight
	 * 
	 * @param i
	 */
	public void sortFlight(int i) {
		if(searchResultPage==null){
			searchResultPage = new SearchResultPage();
		}
		searchResultPage.getSortBySelectList().selectByIndex(i);
	}
	
	/**
	 * select the first flight
	 */
	public void selectFirstFlight(){
		if(searchResultPage==null){
			searchResultPage = new SearchResultPage();
		}
		searchResultPage.getResultsContainer(0).getSelectButton().click();
	}
	/**
	 * 
	 */
	public void selectShorterFlight(String expectedTime){
		if(searchResultPage==null){
			searchResultPage = new SearchResultPage();
		}
		int size = searchResultPage.getResultsContainer().size();
		for(int i=0;i<size;i++){
			Label a = searchResultPage.getResultsContainer(i).getDurationLabel();
			if(isShorterThanExpected(a.getText(),expectedTime)){
				searchResultPage.getResultsContainer(i).getSelectButton().click();
				break;
			}
		}
	}
	

	public boolean isShorterThanExpected(String time, String expectedTime ){
		String expectedHours = expectedTime.substring(0,expectedTime.indexOf("小"));
		String expectedMinutes = expectedTime.substring(expectedTime.indexOf("时")+1, expectedTime.indexOf("分"));
		String hours = time.substring(0,time.indexOf("小"));
		String minutes = time.substring(time.indexOf("时")+1, time.indexOf("分"));
		hours = hours.trim();
		minutes = minutes.trim();
		if(Integer.parseInt(expectedHours)<Integer.parseInt(hours)){
			return false;
		}else if(Integer.parseInt(expectedHours)>Integer.parseInt(hours)){
			return true;
		}else{
			if(Integer.parseInt(expectedMinutes)>Integer.parseInt(minutes)){
				return false;
			}else{
				return true;
			}
		}
	}
	
	public void clickDeclineHotelBookingLink(){
		if(searchResultPage==null){
			searchResultPage = new SearchResultPage();
		}
		searchResultPage.getDeclineHotelBookingLink().click();
	}
	
	
	

}
