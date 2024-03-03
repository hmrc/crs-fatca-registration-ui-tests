/*
 * Copyright 2024 HM Revenue & Customs
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

object ContactPage extends BasePage {
  override val pageUrl: String = baseUrl + "/your-contact-details"
  private val textInputField   = By.id("value")

  def continueSettingYourContact(): Unit =
    submitPageById()

  def enterFirstContactName(): Unit = {
    sendTextById(textInputField, "test FirstContactName")
    submitPageById()
  }

  def enterFirstContactEmail(): Unit = {
    sendTextById(textInputField, "testFirstContactName@test.com")
    submitPageById()
  }

  def confirmFirstContactTelephoneAvailable(): Unit = {
    clickOnYesRadioButton()
    submitPageById()
  }

  def enterFirstContactTelephone(): Unit = {
    sendTextById(textInputField, "01642 123456")
    submitPageById()
  }

  def confirmSecondContactAvailabilityYes(): Unit = {
    clickOnYesRadioButton()
    submitPageById()
  }

  def confirmSecondContactAvailabilityNo(): Unit = {
    clickOnNoRadioButton()
    submitPageById()
  }

  def enterSecondContactName(): Unit = {
    sendTextById(textInputField, "test SecondContactName")
    submitPageById()
  }

  def enterSecondContactEmail(): Unit = {
    sendTextById(textInputField, "testSecondContactName@test.com")
    submitPageById()
  }

  def confirmSecondContactTelephoneAvailable(): Unit = {
    clickOnYesRadioButton()
    submitPageById()
  }

  def enterSecondContactTelephone(): Unit = {
    sendTextById(textInputField, "01642 123456")
    submitPageById()
  }
}
