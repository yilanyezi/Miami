package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.html.Label;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.BasicPageImpl;
import com.paypal.selion.testcomponents.ExpediaFlight.ReviewTripPage;
import com.paypal.selion.testcomponents.ExpediaFlight.SearchResultPage;

/**
 * Extended class of SearchResultPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
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
		getSortBySelectList().selectByIndex(i);
	}

	/**
	 * select the first flight
	 */
	public void selectFlight() {
		getResultsContainer(0).getSelectButton().clickAndExpect(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#flightModule0")));
	}

	public void selectShorterFlight(String expectedTime) {
		int size = getResultsContainer().size();
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			flag = false;
			Label a = getResultsContainer(i).getDurationLabel();
			if (isShorterThanExpected(a.getText(), expectedTime)) {
				getResultsContainer(i).getSelectButton().click();
				flag = true;
				break;
			}
		}
		if(!flag){
			System.err.println("no ticket");
		}

	}

	/**
	 * check whether any pop-up/new tab is displaying or not
	 * calling switch window method 
	 */
	public void clickDeclineHotelBookingLink() {
		if (getDeclineHotelBookingLink().isElementPresent()) {
		//	getDeclineHotelBookingLink().clickAndExpect(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#forcedChoiceNoThanks")));
			getDeclineLink().click();
			System.out.println("Successfully selected Flight from Beijing to San Fran.");
		}
	}
		
	/**
	 * check the time duration of flight
	 * @param time
	 * @param expectedTime
	 * @return
	 */
	private boolean isShorterThanExpected(String time, String expectedTime) {

		String languageOnPage = this.getHtmlLabel().getAttribute("data-language");
		System.out.println(languageOnPage);
		if (languageOnPage.contains("en_")) {
			
			System.out.println(time);
			//if the site is in English language
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
			
			System.out.println(time);
			//if the site is in Chinese language
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
