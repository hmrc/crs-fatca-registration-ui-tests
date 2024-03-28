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

object AddContact extends BasePage {
  override val pageUrl: String = baseUrl
  private val textInputField   = By.id("value")

  def continueSettingYourContact(): Unit = {
    onPage(baseUrl + "/your-contact-details")
    submitPageById()
  }

  def enterFirstContactName(): Unit = {
    onPage(baseUrl + "/contact-name")
    sendTextById(textInputField, "test FirstContactName")
    submitPageById()
  }

  def enterFirstContactEmail(): Unit = {
    onPage(baseUrl + "/email")
    sendTextById(textInputField, "testFirstContactName@test.com")
    submitPageById()
  }

  def confirmFirstContactTelephoneAvailable(): Unit = {
    onPage(baseUrl + "/have-phone")
    clickOnYesRadioButton()
    submitPageById()
  }

  def enterFirstContactTelephone(): Unit = {
    onPage(baseUrl + "/phone")
    sendTextById(textInputField, "01642 123456")
    submitPageById()
  }

  def confirmSecondContactAvailabilityYes(): Unit = {
    onPage(baseUrl + "/have-second-contact")
    clickOnYesRadioButton()
    submitPageById()
  }

  def confirmSecondContactAvailabilityNo(): Unit = {
    onPage(baseUrl + "/have-second-contact")
    clickOnNoRadioButton()
    submitPageById()
  }

  def enterSecondContactName(): Unit = {
    onPage(baseUrl + "/second-contact-name")
    sendTextById(textInputField, "test SecondContactName")
    submitPageById()
  }

  def enterSecondContactEmail(): Unit = {
    onPage(baseUrl + "/second-contact-email")
    sendTextById(textInputField, "testSecondContactName@test.com")
    submitPageById()
  }

  def confirmSecondContactTelephoneAvailable(): Unit = {
    onPage(baseUrl + "/second-contact-have-phone")
    clickOnYesRadioButton()
    submitPageById()
  }

  def enterSecondContactTelephone(): Unit = {
    onPage(baseUrl + "/second-contact-phone")
    sendTextById(textInputField, "01642 123456")
    submitPageById()
  }

  def addFirstContact(): Unit = {
    enterFirstContactName()
    enterFirstContactEmail()
    confirmFirstContactTelephoneAvailable()
    enterFirstContactTelephone()
  }

  def addSecondContact(): Unit = {
    enterSecondContactName()
    enterSecondContactEmail()
    confirmSecondContactTelephoneAvailable()
    enterSecondContactTelephone()
  }

}
