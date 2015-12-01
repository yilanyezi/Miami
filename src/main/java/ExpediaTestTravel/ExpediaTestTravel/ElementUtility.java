package ExpediaTestTravel.ExpediaTestTravel;

import java.util.Set;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

public class ElementUtility {

	SearchResultPage searchResultPage = new SearchResultPage();

	/**
	 * switch window method
	 */
	public void switchWindow() {
		String window = Grid.driver().getWindowHandle();
		Set<String> windows = Grid.driver().getWindowHandles();

		for (String windowName : windows) {
			if (!window.equals(windowName)) {
				window = windowName;
			}
		}
		Grid.driver().switchTo().window(window);
		searchResultPage.getDeclineHotelBookingLink().click();
	}

}
