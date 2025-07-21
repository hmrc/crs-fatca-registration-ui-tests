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

//TODO Remove this file post discussion with Dev Team, if it is covered as part of unit test
class OrgRegistrationProblemSpec extends BaseSpec {

  Feature("Organisation Registration Problem Page Validation") {

    Scenario("Organisation with Non-Matched ID - business-not-identified", RegistrationTests, ZapTests) {
      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(matchingCtUtr)
      And("They enter a business name that does not match the UTR they gave")
      BusinessNamePage.enterBusinessName("non matched")
      Then("The user should land on the 'Business not identified' page")
      BusinessNotIdentifiedPage.onPage()
    }

    Scenario("Organisation with Non-Matched business Address - business-not-identified", RegistrationTests, ZapTests) {
      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(matchingCtUtr)
      BusinessNamePage.enterBusinessName("matched")
      And("They indicate that their business is not the one that was returned by matching the UTR and business name")
      IsThisYourBusinessPage.matchedBusinessNo()
      Then("The user should land on the 'Business not identified' page")
      BusinessNotIdentifiedPage.onPage()
    }

    Scenario(
      "CT Auto-matched Org with Non-Matched business Address - Different Business",
      RegistrationTests,
      ZapTests
    ) {
      Given("User logs in as an Organisation with a CT enrolment")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user indicates that their business is not the one that was returned as part of auto-matching")
      IsThisYourBusinessPage.matchedBusinessNo()
      Then("The user should land on the 'Different business' page")
      DifferentBusinessPage.onPage()
    }

    Scenario("Organisation that is already registered", RegistrationTests, ZapTests) {
      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      And("They enter the UTR and name of a business that is already registered")
      UtrPage.enterUtr(alreadyRegCtUtr)
      BusinessNamePage.enterBusinessName("already registered")
      IsThisYourBusinessPage.matchedBusinessYes()
      And("They add the details of one contact")
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactNo()
      And("They submit their registration")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the 'Organisation Already Registered' page")
      OrganisationWithUtrPreRegistered.onPage()
    }

    Scenario("CT Auto-matched Organisation - Unable to change business", RegistrationTests, ZapTests) {
      Given("User logs in as an Organisation with a CT enrolment")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      IsThisYourBusinessPage.matchedBusinessYes()
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactNo()
      And("They try to change details about the business that were returned as part of auto-matching")
      CheckYourAnswerPage.clickOnByPartialLinkText(CheckYourAnswerPage.changeYourBusinessPartialLinkText)
      Then("They should lnd on the 'Unable to change business' page")
      UnableToChangeBusinessPage.onPage()
    }
  }
}
