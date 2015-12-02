package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Set;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.html.Label;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

public class SearchResultPageEXT extends SearchResultPage {

	public SearchResultPageEXT() {
		super();
	}

	public SearchResultPageEXT(String siteLocale) {
		super(siteLocale);
	}

	/**
	 * open search Result page, do sorting flight
	 * 
	 * @param i
	 */
	public void sortFlight(int i) {

		// getSortBySelectList().click();
		getSortBySelectList().selectByIndex(i);
	}

	/**
	 * select the first flight
	 */
	public void selectFirstFlight() {
		getResultsContainer(0).getSelectButton().click();
	}

	public void selectShorterFlight(String expectedTime) {
		int size = getResultsContainer().size();
		for (int i = 0; i < size; i++) {
			Label a = getResultsContainer(i).getDurationLabel();
			if (isShorterThanExpected(a.getText(), expectedTime)) {
				getResultsContainer(i).getSelectButton().click();
				break;
			}
		}

	}

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
		
	}

	public void clickDeclineHotelBookingLink() {
		if (getDeclineHotelBookingLink().isElementPresent()) {
			this.switchWindow();
			getDeclineHotelBookingLink().click();
			System.out
					.println("Successfully selected Flight from Beijing to San Fran.");
		} else {
			System.out
					.println(" ->>>>> Successfully selected Flight from Beijing to San Fran.");
		}
	}

	private boolean isShorterThanExpected(String time, String expectedTime) {

		String languageOnPage = this.getHtmlLabel().getAttribute(
				"data-language");
		if (languageOnPage.equals("en_HK")) {
			String expectedHours = expectedTime.substring(0,
					expectedTime.indexOf("h"));
			String expectedMinutes = expectedTime.substring(
					expectedTime.indexOf("h") + 1, expectedTime.indexOf("m"));
			String hours = time.substring(0, time.indexOf("h"));
			String minutes = time.substring(time.indexOf("h") + 1,
					time.indexOf("m"));
			hours = hours.trim();
			minutes = minutes.trim();
			if (Integer.parseInt(expectedHours) < Integer.parseInt(hours)) {
				return false;
			} else if (Integer.parseInt(expectedHours) > Integer
					.parseInt(hours)) {
				return true;
			} else {
				if (Integer.parseInt(expectedMinutes) > Integer
						.parseInt(minutes)) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			String expectedHours = expectedTime.substring(0,
					expectedTime.indexOf("小"));
			String expectedMinutes = expectedTime.substring(
					expectedTime.indexOf("时") + 1, expectedTime.indexOf("分"));
			String hours = time.substring(0, time.indexOf("小"));
			String minutes = time.substring(time.indexOf("时") + 1,
					time.indexOf("分"));
			hours = hours.trim();
			minutes = minutes.trim();
			if (Integer.parseInt(expectedHours) < Integer.parseInt(hours)) {
				return false;
			} else if (Integer.parseInt(expectedHours) > Integer
					.parseInt(hours)) {
				return true;
			} else {
				if (Integer.parseInt(expectedMinutes) > Integer
						.parseInt(minutes)) {
					return false;
				} else {
					return true;
				}
			}
		}

	}
}
