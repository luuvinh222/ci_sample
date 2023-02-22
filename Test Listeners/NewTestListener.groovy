import org.testng.ITestResult

import com.kms.katalon.core.annotation.AfterTestCase

class NewTestListener {
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def void takeScreenshot(ITestResult result) {
		Commons.captureScreenshot(result)
	}
}