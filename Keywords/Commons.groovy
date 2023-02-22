import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.testng.ITestResult

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import io.appium.java_client.AppiumDriver


/**
 * General description: List out some common keywords used to web application
 * 
 * Keyword list:
 *                  verifyObjectVisible					: Custom keyword is used to verify if the given object is visible on a mobile screen
 * 					verifyObjectEnabled					: Custom keyword is used to verify if the given object is enabled on a mobile screen
 * 					verifyObjectChecked					: Custom keyword is used to verify if the given object is checked on a mobile screen
 *                  inputDataToTestObject				: Custom keyword is used to input a text string to object on a mobile screen
 *                  printStepMessage					: Custom keyword is used to print step message to console
 *					selectOnRadioButton					: Custom keyword is used to select a radio button on a mobile screen
 *					selectOnCheckbox					: Custom keyword is used to select a check box on a mobile screen
 *					clickToButton						: Custom keyword is used to select a button on a mobile screen
 *					getTextFromTestObject				: Custom keyword is used to get a text string from object(button, radio button, label,...) on a mobile screen
 *					getTextFromInput					: Custom keyword is used to get a text string from object(with html tag name input) on a mobile screen
 *					selectComboBoxItemByIndex			: Custom keyword is used to select all items that have a index matching the "index" argument on a mobile screen
 *					selectComboBoxItemByValue			: Custom keyword is used to select all options that have a value matching the "value" argument on a mobile screen
 *					selectListViewItem					: Custom keyword is used to selected a item from list view on a mobile screen
 *					selectMenuItem						: Custom keyword is used to selected a item from menu on a mobile screen
 * 
 * @since 08/02/2023 
 * 
 * @author ABA Automation team
 */
public class Commons {
	/**
	 * Custom keyword name: verifyObjectVisible(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to verify object existence on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 *			true if element presents; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static Boolean verifyObjectVisible(TestObject objPath, int iTimeOut) {
		try{
			if(Mobile.verifyElementVisible(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE)) {
				return true
			} else {
				KeywordUtil.logInfo("Object '" + objPath.objectId.split('/').last() + "' is not existence.")
				return false
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Object is not existence in Object Repository.")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyObjectEnabled(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to verify object enabled on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static Boolean verifyObjectEnabled(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object is enabled or not
			if(Mobile.verifyElementAttributeValue(objPath, "enabled", "true", iTimeOut, FailureHandling.CONTINUE_ON_FAILURE)) {
				return true
			} else {
				KeywordUtil.markFailed("Object '" + objPath.objectId.split('/').last() + "'is disabled.")
				return false
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Object is not existence in Object Repository.")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyObjectEnabled(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to verify if the given object is checked on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */	
	@Keyword
	def static Boolean verifyObjectChecked(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object is enabled or not
			if(Mobile.verifyElementChecked(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == false) {
				return false
			} else {
				KeywordUtil.logInfo("Object '" + objPath.objectId.split('/').last() + "' is already checked.")
				return true
			}
		} catch(Exception ex) {
			return false
		}
	}

	/**
	 * Custom keyword name: inputDataToTestObject(TestObject objPath, String sText, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to inputting a text string to object on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param sText		: The text string is input in object
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void inputDataToTestObject(TestObject objPath, String sText, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					Mobile.setText(objPath, sText, iTimeOut)
					if(getTextFromObjectByInput(objPath, iTimeOut).equals(sText)) {
						KeywordUtil.markPassed("The object is inputed data successful.")
					} else {
						KeywordUtil.markFailed("The object is input data not match with typed data.")
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: inputEncryptedDataToTestObject(String sObjPath, String sText, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to inputting a text string to object on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param sText		: The text string is input in object
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void inputEncryptedDataToTestObject(TestObject objPath, String sEncryptedText, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					Mobile.setEncryptedText(objPath, sEncryptedText, iTimeOut)
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}
	/**
	 * Custom keyword name: printStepMessage(String sPageNameOrScreen, String sAction, String sObjName)</br></br>
	 *
	 * General description: Custom keyword is used to print step message to console</br></br>
	 *
	 * @param sPageNameOrScreen	: The name of page or screen
	 * @param sAction      		: The name of action
	 * @param objName    		: The name of object
	 *
	 * @return
	 * 			None
	 *
	 * @since 17/02/2023
	 *
	 * @author ABA Automation team
	 */

	@Keyword
	def static void printStepMessage(String sPageNameOrScreen, String sAction, String sObjName) {
		KeywordUtil.logInfo(sPageNameOrScreen + " - " + sAction + " - " + sObjName)
	}

	/**
	 * Custom keyword name: clickToRadioButton(String sObjPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to clicked a radio button on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectOnRadioButton(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					if(verifyObjectChecked(objPath, iTimeOut)) {
						Mobile.tap(objPath, iTimeOut)
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: clickToCheckBox(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to clicked a check box on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectOnCheckBox(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					if(verifyObjectChecked(objPath, iTimeOut)) {
						Mobile.tap(objPath, iTimeOut)
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: clickToButton(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to clicked a button on a mobile screen</br></br>
	 *
	 * @param objPath	: The represent a web element in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void tapOnButton(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					Mobile.tap(objPath, iTimeOut)
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: getTextFromObject(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to getting a text string from object(button, radio button, label,...) on a mobile screen</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			innerText of the test object
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */	
	@Keyword
	def static String getTextFromObject(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					return Mobile.getText(objPath, iTimeOut)
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: getTextFromObjectByInput(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to getting a text string from object(with html tag name input) on a mobile screen</br></br>
	 *
	 * @param objPath		: The object in the Object Repository
	 * @param iTimeOut		: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			innerText of the test object
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static String getTextFromObjectByInput(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if(verifyObjectEnabled(objPath, iTimeOut)) {
					return Mobile.getAttribute(objPath, "value", iTimeOut)
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}


	/**
	 * Custom keyword name: selectComboBoxItemByIndex(TestObject objPath, int itemIndex, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to select all items that have a index matching the "index" argument on a mobile screen</br></br>
	 *
	 * @param objPath		: The object in the Object Repository
	 * @param itemIndex		: The index index range of the options to select from object. Index starts from 0.
	 * @param iTimeOut		: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectComboBoxItemByIndex(TestObject objPath, int sItemIndex, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if (verifyObjectEnabled(objPath, iTimeOut)) {
					if(sItemIndex >= 0) {
						Mobile.selectListItemByIndex(objPath, sItemIndex, iTimeOut)
					} else {
						KeywordUtil.markFailed("The item index typed is lower than 0")
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: selectComboBoxItemByValue(TestObject objPath, String itemValue, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used to select all items that have a value matching the "value" argument on a mobile screen</br></br>
	 *
	 * @param sObjPath		: The element logical name path in the Object Repository
	 * @param itemValue		: The value of the options to select from object
	 * @param iTimeOut		: The time for waiting object is displayed on mobile screen
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectComboBoxItemByLabel(TestObject objPath, String sItemLabel, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectVisible(objPath, iTimeOut)) {
				if (verifyObjectEnabled(objPath, iTimeOut)) {
					if(!sItemLabel.isEmpty()) {
						Mobile.selectListItemByLabel(objPath, sItemLabel, iTimeOut)
					} else {
						KeywordUtil.markFailed("The item value typed is null")
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: closeExistingApp(String appId)
	 *
	 * General description: Custom keyword is used to close application
	 *
	 * @param appId		: The application ID
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void closeExistingApp(String appId) {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		driver.terminateApp(appId)
	}
}