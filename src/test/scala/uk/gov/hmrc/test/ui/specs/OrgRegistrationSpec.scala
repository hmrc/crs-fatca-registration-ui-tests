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

    Scenario("Organisation with UTR registers for CRS-FATCA as a limited company ", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAsOrgOrSoleTrader("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterValidUtr("1114567890")
      BusinessNamePage.enterBusinessName("CRSFATCA company")
      IsThisYourBusinessPage.confirmMatchedBusiness()
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .confirmSecondContactAvailabilityYes()
        .addSecondContact()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Auto-matched Organisation with CT enrolment registers for CRS-FATCA", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      BusinessNamePage.confirmBusinessAddressInUkYes()
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .confirmSecondContactAvailabilityYes()
        .addSecondContact()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Organisation without UTR registers for CRS-FATCA as a limited company ", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAsOrgOrSoleTrader("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkNo()
      UtrPage.haveUTRNo()
      BusinessNameWithoutIDPage.enterBusinessNameWithoutID()
      BusinessTradingPage.haveTradingNameYes()
      BusinessTradingPage.enterTradingName()
      BusinessAddressWithoutIDNonUKPage.enterAddressNonUK()
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .confirmSecondContactAvailabilityYes()
        .addSecondContact()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

    Scenario("Organisation with UTR registers for CRS-FATCA as a Sole trader", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAsOrgOrSoleTrader("Sole Trader")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterValidUtr("1114567890")
      YourNamePage.enterYourName("sfirstName", "slastName")
      IsThisYourBusinessPage.confirmMatchedBusiness()
      IndividualEmailPage.enterIndividualEmail()
      IndividualHavePhonePage.confirmIndividualHavePhone()
      IndividualPhonePage.enterIndividualTelephone()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      ConfirmRegistrationPage.checkPage()
    }

  }
}
