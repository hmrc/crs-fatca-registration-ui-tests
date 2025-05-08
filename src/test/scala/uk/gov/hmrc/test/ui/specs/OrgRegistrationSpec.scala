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

class OrgRegistrationSpec extends BaseSpec {

  Feature("Organisation Registration") {

    Scenario(
      "Non-Automatched Organisation affinity with UTR registers as a Ltd Company with two contacts",
      RegistrationTests,
      ZapTests
    ) {

      Given("the user logs in as a non-automatched Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user enters information to achieve a match on their organisation's data")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(validCtUtr)
      BusinessNamePage.enterBusinessNameMatched()
      IsThisYourBusinessPage.matchedBusinessYes()
      And("They add the details of two contacts")
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactYes()
        .addSecondContact()
      And("They check their telephone answer")
      CheckYourAnswerPage.clickOnByPartialLinkText(CheckYourAnswerPage.changeHavePhonePartialLinkText)
      AddContact.clickOnBackLink(AddContact.changeHavePhonePageUrl)
      And("They submit their registration")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }

    Scenario(
      "Auto-matched Organisation affinity with CT enrolment registers with one contact",
      RegistrationTests,
      ZapTests
    ) {

      Given("the user logs in as an auto-matched Organisation")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user confirms their matched organisation's information")
      IsThisYourBusinessPage.matchedBusinessYes()
      And("They add the details of one contact")
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactNo()
      And("They submit their registration details")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }

    Scenario(
      "Non-Automatched Organisation affinity without UTR registers as a Ltd Company with one contact",
      RegistrationTests,
      ZapTests
    ) {

      Given("The user logs in as a non-automatched Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user enters information without a UTR to match against")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkNo()
      HaveUtrPage.haveUTRNo()
      BusinessNameWithoutIDPage.enterBusinessName()
      HaveTradingNamePage.haveTradingNameYes()
      BusinessTradingPage.enterTradingName()
      BusinessAddressWithoutIDNonUKPage.enterAddressNonUK()
      And("They add the details of one contact")
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactNo()
      And("They submit their registration details")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Non-Automatched Organisation with UTR registers as a Sole trader", RegistrationTests, ZapTests) {

      Given("The user logs in as a non-automatched Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The ser enters information to achieve a match on their Sole Trader business' data")
      RegistrationTypePage.registerAs("Sole Trader")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(validStUtr)
      YourNamePage.enterYourName()
      IsThisYourBusinessPage.matchedBusinessYes()
      And("They enter their contact details")
      IndividualEmailPage.enterEmail()
      IndividualHavePhonePage.havePhoneYes()
      IndividualPhonePage.enterTelephone()
      And("They submit their registration details")
      CheckYourAnswerPage.confirmAndSend()
      Then("They should land on the Registration Confirmation Page")
      ConfirmRegistrationPage.checkPage()
    }

  }
}
