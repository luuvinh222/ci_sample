import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable

Mobile.startExistingApplication(GlobalVariable.gAppId)

Commons.inputEncryptedDataToTestObject(findTestObject('Object Repository/Page_Login/txfPassword'), GlobalVariable.gPassword, 
    GlobalVariable.gTimeout)

Commons.tapOnButton(findTestObject('Object Repository/Page_Login/btnLogin'), GlobalVariable.gTimeout)

String expectedUsername = "LUU XUAN VINH1"
Mobile.verifyElementText(findTestObject('Object Repository/Page_Login/txfUsername'), expectedUsername)

Commons.closeExistingApp(GlobalVariable.gAppId)