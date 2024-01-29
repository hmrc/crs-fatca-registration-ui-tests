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
import org.scalatest.matchers.should.Matchers
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import uk.gov.hmrc.domain._

trait BasePage extends BrowserDriver with Matchers {

  case class PageNotFoundException(message: String) extends Exception(message)

  val pageUrl: String
  val baseUrl: String        = TestConfiguration.url("crs-fatca-registration-frontend") + "/register"
  val submitButtonId: By     = By.id("submit")
  val randomisedNino: String = new Generator().nextNino.toString()
  val randomisedUtr: String  = new SaUtrGenerator().nextSaUtr.toString()

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

  def clickOnById(id: By): Unit =
    driver.findElement(id).click()

  def submitPageById(): Unit =
    driver.findElement(submitButtonId).click()
}
