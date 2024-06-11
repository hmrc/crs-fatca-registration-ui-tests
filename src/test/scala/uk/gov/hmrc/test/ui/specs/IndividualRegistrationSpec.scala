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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags._

class IndividualRegistrationSpec extends BaseSpec {

  Feature("Individual Registration") {

    Scenario("Individual with NINO", RegistrationTests, ZapTests) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("An individual not connected to a business")
      IndividualHaveNiNumberPage.confirmIndividualHaveNiNumber()
      IndividualNiNumberPage.enterIndividualNiNumber(generateNino(individualNino))
      IndividualNamePage.enterName("firstName", "lastName")
      IndividualDOBPage.enterDOB()
      IdentityConfirmedPage.checkPage()
      IndividualEmailPage.enterIndividualEmail()
      IndividualHavePhonePage.confirmIndividualHavePhone()
      IndividualPhonePage.enterIndividualTelephone()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Individual without NINO", RegistrationTests, ZapTests) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("An individual not connected to a business")
      IndividualHaveNiNumberPage.confirmIndividualDoesNotHaveNiNumber()
      IndividualWithoutIdNamePage.enterName("firstName", "lastName")
      IndividualWithoutIdDOBPage.enterDOB()
      IndividualWhereDoYouLivePage.confirmUkAddress()
      IndividualUkPostcodePage.enterPostcode()
      IndividualIsThisYourAddressPage.confirmMatchedAddress()
      IndividualEmailPage.enterIndividualEmail()
      IndividualHavePhonePage.confirmIndividualHavePhone()
      IndividualPhonePage.enterIndividualTelephone()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Auto-matched user,login with Individual affinity", RegistrationTests, ZapTests) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsAutomatchedIndAdmin()
      When("The user makes their way through regular journey")
      RegistrationTypePage.checkPage()
    }
  }
}
