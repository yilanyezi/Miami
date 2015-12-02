package ExpediaTestTravel.ExpediaTestTravel;

import java.util.Calendar;
import java.util.Date;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import ExpediaTestTravel.ExpediaTestTravel.MainObject.BookFlightTicketPageObject;

public class FlightTicketTest {

	private String fligthFrom = "北京";
	private String flightTo = "旧金山, 加利福尼亚, 美国 (SFO-旧金山国际机场)";
	private Date departTime;
	private Date returningDate;
	private int period = 21;
	private String time = "15小时0分钟";
	private ElementUtility elementUtility = new ElementUtility();
	private BookFlightTicketPageObject bookFlightTicketPageObject = new BookFlightTicketPageObject();
	private int indexOfLowestPriceFlight = 0;

	@BeforeTest
	public void init() {
		Date departTime = this.genarateNextMonday();
		Date returningDate = this.genarateReturnDay(departTime, period);
		this.departTime = departTime;
		this.returningDate = returningDate;

	}

	@Test
	@WebTest
	public void selectTheShorterAndLowestFlight() {
		bookFlightTicketPageObject.openWebsite();
		bookFlightTicketPageObject.searchFlight(fligthFrom, flightTo, departTime, returningDate);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=select[name='sort']");
		bookFlightTicketPageObject.sortFlight(indexOfLowestPriceFlight);
		bookFlightTicketPageObject.selectShorterFlight(time);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.title-city-text");
		bookFlightTicketPageObject.selectShorterFlight(time);
		bookFlightTicketPageObject.clickDeclineHotelBookingLink();
	}
	
	/**
	 * 
	 * method to get next Monday
	 * 
	 * @return
	 */
	public Date genarateNextMonday() {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}
	
	/**
	 * method to get the returning date
	 * 
	 * @param date
	 *            the departing date
	 * @param period
	 *            how long will stay,valued by day
	 * @return
	 */
	public Date genarateReturnDay(Date date, int period) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_YEAR, period);
		return ca.getTime();
	}

}
