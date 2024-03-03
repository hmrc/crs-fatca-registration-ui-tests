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

  private val redirectUrl: String = TestConfiguration.url("crs-fatca-registration-frontend")

  private val redirectionUrlById: By = By.id("redirectionUrl")
  private val affinityGroupById: By  = By.id("affinityGroupSelect")
  private val authSubmitById: By     = By.id("submit-top")
  private val presetDropDownById: By = By.id("presets-dropdown")
  private val presetSubmitById: By   = By.id("add-preset")
  private val identifierCTField: By  = By.id("input-4-0-value")
  private val identifierCTValue      = "1234567890"

  def loadPage: this.type = {
    navigateTo(pageUrl)
    onPage(pageUrl)
    this
  }

  def selectAffinityGroup(affinityGroup: String): Unit =
    selectDropdownById(affinityGroupById).selectByVisibleText(affinityGroup)

  private def selectPresetAsCT(presetType: String): Unit =
    selectDropdownById(presetDropDownById).selectByVisibleText(presetType)

  def loginAsNonAutomatchedOrgAdmin(): RegistrationTypePage.type = {
    loadPage
    sendTextById(redirectionUrlById, redirectUrl)
    selectAffinityGroup("Organisation")
    clickOnById(authSubmitById)
    RegistrationTypePage
  }

  def loginAsAutomatchedOrgAdmin(): IsThisYourBusinessPage.type = {
    loadPage
    sendTextById(redirectionUrlById, redirectUrl)
    selectAffinityGroup("Organisation")
    selectPresetAsCT("CT")
    clickOnById(presetSubmitById)
    sendTextById(identifierCTField, identifierCTValue)
    clickOnById(authSubmitById)
    IsThisYourBusinessPage
  }

}
