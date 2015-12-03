package ExpediaTestTravel.ExpediaTestTravel.HotelBookEXT;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.testcomponents.ExpediaHotel.SearchHotelResultPage;

/**
 * Extended class of SearchHotelResultPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
public class SearchHotelResultPageEXT extends SearchHotelResultPage {

	public SearchHotelResultPageEXT() {
		super();
	}

	public SearchHotelResultPageEXT(String siteLocale) {
		super(siteLocale);
	}
	
	/**
	 * open search Result page, do sorting hotel by price
	 */
	public void sortHotelByPrice() {
		getSortByPriceButton().clickAndExpect(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#bcol")));
	}
	
	/**
	 * open details of first hotel
	 */
	public void openHotelDetails() {
		getResultsContainer(0).getSelectLink().click();
		switchWindow();
	}
	
	/**
	 * switch window
	 */
	public void switchWindow() {
		  String window = Grid.driver().getWindowHandle();
		  Set<String> windows = Grid.driver().getWindowHandles();
		  String newWin="";
		  for (String windowHandel : windows) {
		   if (!windowHandel.equals(window)) {
		    newWin =windowHandel ;
		    Grid.driver().switchTo().window(newWin);
		   }
		  }		  
		 }
}
