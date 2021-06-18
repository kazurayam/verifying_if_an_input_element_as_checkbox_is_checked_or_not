import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path html = projectDir.resolve('dashboard-checkboxes.html')
URL url = html.toFile().toURI().toURL()

WebUI.openBrowser('')
WebUI.navigateToUrl(url.toString())

/*
 * Let's check if the checkbox "In Stock" is checked. If not, this test will fail.
 * 
 * <div class="container checkbox-container" style="">
 *   <label class="checkbox  checkbox--checked  " style="">
 *     <span class="checkbox__box" style="">
 *       <i class="icon-dashboard-icons_checkmark" style=""></i>
 *     </span>
 *     <span class="checkbox__label" style="">In Stock</span>
 *     <input type="checkbox" checked="" style="">
 *   </label>
 * </div>
 */
TestObject input_In_Stock = createTestObject('//span[text()="In Stock"]//following-sibling::input')
WebUI.verifyElementChecked(input_In_Stock, 3)

/*
 * Let's check if the checkbox "Contains Alcohol" is NOT checked. If is checked, this test will fails.
 *
 * <div class="container checkbox-container" style="">
 *   <label class="checkbox  checkbox--checked  " style="">
 *     <span class="checkbox__box" style="">
 *       <i class="icon-dashboard-icons_checkmark" style=""></i>
 *     </span>
 *     <span class="checkbox__label" style="">Contails Alcohol</span>
 *     <input type="checkbox" style="">
 *   </label>
 * </div>
 */
TestObject input_Contains_Alcohol = createTestObject('//span[text()="Contains Alcohol"]//following-sibling::input')
WebUI.verifyElementNotChecked(input_Contains_Alcohol, 3)

WebUI.closeBrowser()

/**
 * utility to create an instance of TestObject with XPath
 * @param xpath
 * @return
 */
TestObject createTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}