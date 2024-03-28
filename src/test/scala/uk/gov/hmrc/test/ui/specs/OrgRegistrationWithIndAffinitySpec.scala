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

class OrgRegistrationWithIndAffinitySpec extends BaseSpec {

  Feature("Organisation Registration") {

    Scenario(
      "Organisation registration with UTR and with Individual affinity. Second contact option should be not available",
      RegistrationTests,
      ZapTests
    ) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAsOrgOrSoleTrader("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterValidUtr("1234567890")
      BusinessNamePage.enterBusinessName("CRSFATCA company")
      IsThisYourBusinessPage.confirmMatchedBusiness()
      AddContact.continueSettingYourContact()
      AddContact.addFirstContact()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      //Confirmation Page is not available now, update this once it is available
    }

    Scenario(
      "Organisation registration without UTR and with Individual affinity. Second contact option should be not available",
      RegistrationTests,
      ZapTests
    ) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsNonAutomatchedIndAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAsOrgOrSoleTrader("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkNo()
      UtrPage.haveUTRNo()
      BusinessNameWithoutIDPage.enterBusinessNameWithoutID()
      BusinessTradingPage.haveTradingNameYes()
      BusinessTradingPage.enterTradingName()
      BusinessAddressWithoutIDNonUKPage.enterAddressNonUK()
      AddContact.continueSettingYourContact()
      AddContact.addFirstContact()
      CheckYourAnswerPage.confirmAndSendOnCYAPage()
      //Confirmation Page is not available now, update this once it is available
    }

    Scenario("Auto-matched user,login with Individual affinity", RegistrationTests, ZapTests) {

      Given("User logs in as an Individual")
      AuthLoginPage.loginAsAutomatchedIndAdmin()
      When("The user makes their way through regular journey")
      RegistrationTypePage.checkPage()
      RegistrationTypePage.checkH1("What are you registering as?")
    }

  }
}