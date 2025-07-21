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

  def continueSettingYourContact(): this.type = {
    onPage(baseUrl + "/your-contact-details")
    click(submitButtonId)
    this
  }

  def enterFirstContactName(): Unit = {
    onPage(baseUrl + "/contact-name")
    sendKeys(textInputField, "test FirstContactName")
    click(submitButtonId)
  }

  def enterFirstContactEmail(): Unit = {
    onPage(baseUrl + "/email")
    sendKeys(textInputField, "testFirstContactName@example.com")
    click(submitButtonId)
  }

  def firstContactHavePhoneYes(): Unit = {
    onPage(baseUrl + "/have-phone")
    click(yesRadioId)
    click(submitButtonId)
  }

  def enterFirstContactPhone(): Unit = {
    onPage(baseUrl + "/phone")
    sendKeys(textInputField, "0191 498 0000")
    click(submitButtonId)
  }

  def haveSecondContactYes(): this.type = {
    onPage(baseUrl + "/have-second-contact")
    click(yesRadioId)
    click(submitButtonId)
    this
  }

  def haveSecondContactNo(): Unit = {
    onPage(baseUrl + "/have-second-contact")
    click(noRadioId)
    click(submitButtonId)
  }

  def enterSecondContactName(): Unit = {
    onPage(baseUrl + "/second-contact-name")
    sendKeys(textInputField, "test SecondContactName")
    click(submitButtonId)
  }

  def enterSecondContactEmail(): Unit = {
    onPage(baseUrl + "/second-contact-email")
    sendKeys(textInputField, "testSecondContactName@example.com")
    click(submitButtonId)
  }

  def secondContactHavePhoneYes(): Unit = {
    onPage(baseUrl + "/second-contact-have-phone")
    click(yesRadioId)
    click(submitButtonId)
  }

  def enterSecondContactPhone(): Unit = {
    onPage(baseUrl + "/second-contact-phone")
    sendKeys(textInputField, "0191 498 0999")
    click(submitButtonId)
  }

  def addFirstContact(): this.type = {
    enterFirstContactName()
    enterFirstContactEmail()
    firstContactHavePhoneYes()
    enterFirstContactPhone()
    this
  }

  def addSecondContact(): this.type = {
    enterSecondContactName()
    enterSecondContactEmail()
    secondContactHavePhoneYes()
    enterSecondContactPhone()
    this
  }

}
