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
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object AuthLoginPage extends BasePage {
  override val pageUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"

  private val redirectionUrlById: By    = By.id("redirectionUrl")
  private val affinityGroupById: By     = By.id("affinityGroupSelect")
  private val authSubmitById: By        = By.id("submit-top")
  private val presetDropDownById: By    = By.id("presets-dropdown")
  private val presetSubmitById: By      = By.id("add-preset")
  private val identifierCTField: By     = By.id("input-4-0-value")
  private val redirectUrl: String       = TestConfiguration.url("crs-fatca-registration-frontend")
  private val identifierCTValue: String = generateUtr(automatchedCtUtr)

  private def loadPage: this.type = {
    navigateTo(pageUrl)
    onPage(pageUrl)
    this
  }

  private def selectAffinityGroup(affinityGroup: String): Unit =
    selectDropdownById(affinityGroupById).selectByVisibleText(affinityGroup)

  private def addCtPreset(): Unit = {
    selectDropdownById(presetDropDownById).selectByVisibleText("CT")
    clickOnById(presetSubmitById)
    sendTextById(identifierCTField, identifierCTValue)
  }

  private def submitAuthPage(): Unit = clickOnById(authSubmitById)

  private def submitAuthWithoutEnrolment(affinityGroup: String) = {
    loadPage
    sendTextById(redirectionUrlById, redirectUrl)
    selectAffinityGroup(affinityGroup)
    submitAuthPage()
  }

  private def submitAuthWithCtEnrolment(affinityGroup: String) = {
    loadPage
    sendTextById(redirectionUrlById, redirectUrl)
    selectAffinityGroup(affinityGroup)
    addCtPreset()
    submitAuthPage()
    IsThisYourBusinessPage
  }

  def loginAsNonAutomatchedOrgAdmin(): RegistrationTypePage.type = {
    submitAuthWithoutEnrolment("Organisation")
    RegistrationTypePage
  }

  def loginAsNonAutomatchedIndAdmin(): RegistrationTypePage.type = {
    submitAuthWithoutEnrolment("Individual")
    RegistrationTypePage
  }

  def loginAsAutomatchedOrgAdmin(): IsThisYourBusinessPage.type = {
    submitAuthWithCtEnrolment("Organisation")
    IsThisYourBusinessPage
  }

  def loginAsAutomatchedIndAdmin(): IsThisYourBusinessPage.type = {
    submitAuthWithCtEnrolment("Individual")
    IsThisYourBusinessPage
  }

}
