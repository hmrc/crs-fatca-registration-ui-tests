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

      Given("The user logs in as a non-automatched Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user enters information to achieve a match on their personal data")
      RegistrationTypePage.registerAs("An individual not connected to a business")
      IndividualHaveNiNumberPage.haveNiNumberYes()
      IndividualNiNumberPage.enterNiNumber(individualNino)
      IndividualNamePage.enterName()
      IndividualDOBPage.enterDOB()
      IdentityConfirmedPage.continue()
      And("They enter their contact details")
      IndividualEmailPage.enterEmail()
      IndividualHavePhonePage.havePhoneYes()
      IndividualPhonePage.enterTelephone()
      And("They submit their registration")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Individual without NINO", RegistrationTests, ZapTests) {

      Given("The user logs in as a non-automatched Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user enters information without a NINO to match against")
      RegistrationTypePage.registerAs("An individual not connected to a business")
      IndividualHaveNiNumberPage.haveNiNumberNo()
      IndividualWithoutIdNamePage.enterName()
      IndividualWithoutIdDOBPage.enterDOB()
      IndividualDoYouLiveInTheUkPage.liveInUkYes()
      IndividualUkPostcodePage.enterUkPostcode()
      IndividualIsThisYourAddressPage.matchedAddressYes()
      And("They enter their contact details")
      IndividualEmailPage.enterEmail()
      IndividualHavePhonePage.havePhoneYes()
      IndividualPhonePage.enterTelephone()
      And("They submit their registration")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }
  }
}
