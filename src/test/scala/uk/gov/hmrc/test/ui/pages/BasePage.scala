/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import uk.gov.hmrc.test.ui.utils.IdGenerators

trait BasePage extends BrowserDriver with Matchers with IdGenerators {

  case class PageNotFoundException(message: String) extends Exception(message)

  val pageUrl: String
  val baseUrl: String             = TestConfiguration.url("crs-fatca-registration-frontend") + "/register"
  private val submitButtonId: By  = By.id("submit")
  private val yesRadioId: By      = By.id("value")
  private val noRadioId: By       = By.id("value-no")
  private val countryDropdown: By = By.id("country")
  private val countryOption: By   = By.id("country__option--0")
  private val pageHeader: By      = By.tagName("h1")

  def navigateTo(url: String): Unit =
    driver.navigate().to(url)

  def onPage(url: String): Unit =
    if (driver.getCurrentUrl != url)
      throw PageNotFoundException(
        s"Expected '$url' page, but found '${driver.getCurrentUrl}' page."
      )

  def sendTextById(id: By, textToEnter: String): Unit = {
    driver.findElement(id).clear()
    driver.findElement(id).sendKeys(textToEnter)
  }

  def selectDropdownById(id: By): Select = new Select(driver.findElement(id: By))

  def countryAutoSelect(countryName: String): Unit = {
    driver.findElement(countryDropdown).click()
    driver.findElement(countryDropdown).sendKeys(countryName)
    driver.findElement(countryOption).click()
  }
  def clickOnById(id: By): Unit =
    driver.findElement(id).click()

  def submitPageById(): Unit =
    driver.findElement(submitButtonId).click()

  def clickOnYesRadioButton(): Unit =
    clickOnById(yesRadioId)

  def clickOnNoRadioButton(): Unit =
    clickOnById(noRadioId)

  def checkH1(h1: String): Assertion = driver.findElement(pageHeader).getText should include(h1)

  def clickOnLinkByHrefPageName(pageName: String): Unit =
    driver.findElement(By.cssSelector("a[href$="+s"$pageName]")).click()

  def clickBackLink(pageUrl: String): Unit = {
    onPage(pageUrl)
    driver.findElement(By.linkText("Back")).click()
  }
}
