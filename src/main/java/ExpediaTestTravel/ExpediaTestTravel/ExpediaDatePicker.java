package ExpediaTestTravel.ExpediaTestTravel;

import java.util.Calendar;
import java.util.Date;
import com.paypal.selion.platform.html.AbstractElement;
import com.paypal.selion.platform.html.ParentTraits;
import com.paypal.selion.platform.html.support.HtmlElementUtils;

public class ExpediaDatePicker extends AbstractElement {

	private String prevMonthLocator;
	private String nextMonthLocator;

	public ExpediaDatePicker(ParentTraits parent, String locator) {
		super(parent, locator);
		initDateWidgetLocators();
	}

	public ExpediaDatePicker(String locator, String controlName,
			ParentTraits parent) {
		super(locator, controlName, parent);
		initDateWidgetLocators();
	}

	public ExpediaDatePicker(String locator, String controlName) {
		super(locator, controlName);
		initDateWidgetLocators();
	}

	public ExpediaDatePicker(String locator) {
		super(locator);
		initDateWidgetLocators();
	}

	public void initDateWidgetLocators() {
		this.prevMonthLocator = "css=.btn-paging.btn-secondary.prev";
		this.nextMonthLocator = "css=.btn-paging.btn-secondary.next";
	}

	public void set(Date date) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(new Date());

		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);

		int calculateMonthDifference = (dateCalendar.get(Calendar.YEAR) - currentCalendar
				.get(Calendar.YEAR))
				* 12
				+ dateCalendar.get(Calendar.MONTH)
				- currentCalendar.get(Calendar.MONTH);
		if (calculateMonthDifference > 10) {
			throw new RuntimeException(
					"Please select another Date for your journey.");
		} else {
			if (calculateMonthDifference>1) {
				for(int i=1; i<calculateMonthDifference;i++) {
					HtmlElementUtils.locateElement(this.nextMonthLocator).click();
				}
				this.clickDayOfMonthInSecondSection(dateCalendar.get(Calendar.DAY_OF_MONTH));
			} else if(calculateMonthDifference == 1) {
				this.clickDayOfMonthInSecondSection(dateCalendar.get(Calendar.DAY_OF_MONTH));
			} else {
				this.clickDayOfMonthInFirstSection(dateCalendar.get(Calendar.DAY_OF_MONTH));
			}
		}
	}
	
	private void  clickDayOfMonthInSecondSection(int dayOfMonth){
		HtmlElementUtils.locateElement("css=.cal section:nth-child(4)>:last-child li:nth-child("+dayOfMonth+") a").click();
	}
	private void  clickDayOfMonthInFirstSection(int dayOfMonth){
		HtmlElementUtils.locateElement("css=.cal section:nth-child(2)>:last-child li:nth-child("+dayOfMonth+") a").click();
	}
}
