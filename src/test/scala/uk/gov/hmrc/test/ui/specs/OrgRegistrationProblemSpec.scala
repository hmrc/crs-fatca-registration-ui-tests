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

//TODO Remove this file post discussion with Dev Team , if it is covered as part of unit test
class OrgRegistrationProblemSpec extends BaseSpec {

  Feature("Organisation Registration Problem Page Validation") {

    Scenario("Organisation with Non-Matched ID - business-not-identified", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(NonMatchingCtUtr)
      BusinessNamePage.enterBusinessNameNonMatched()
      BusinessNotIdentifiedPage.validatePageHeader("The details you entered did not match our records")
    }

    Scenario("Organisation with Non-Matched business Address - business-not-identified", RegistrationTests, ZapTests) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      RegistrationTypePage.registerAs("Limited Company")
      RegisteredAddressInUkPage.registeredAddressInUkYes()
      UtrPage.enterUtr(validCtUtr)
      BusinessNamePage.enterBusinessNameMatched()
      IsThisYourBusinessPage.matchedBusinessNo()
      BusinessNotIdentifiedPage.validatePageHeader("The details you entered did not match our records")
    }

    Scenario(
      "Auto Matched Organisation with Non-Matched business Address - Different Business",
      RegistrationTests,
      ZapTests
    ) {

      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      IsThisYourBusinessPage.matchedBusinessNo()
      DifferentBusinessPage.validatePageHeader("You’re unable to use this service with this Government Gateway user ID")
    }
// This test is not working after DAC6-3508 changes , will review again after design changes
//    Scenario("Organisation with UTR Pre registered", RegistrationTests, ZapTests) {
//
//      Given("User logs in as an Organisation")
//      AuthLoginPage.loginAsNonAutomatchedOrgAdmin()
//      When("The user makes their way through the journey")
//      RegistrationTypePage.registerAs("Limited Company")
//      RegisteredAddressInUkPage.registeredAddressInUkYes()
//      UtrPage.enterUtr(preRegUtr)
//      BusinessNamePage.enterBusinessNameMatched()
//      OrganisationWithUtrPreRegistered.validatePageHeader("Your organisation is already registered to use this service")
//    }

    Scenario("Auto-matched Organisation with CT enrolment - Unable to change business", RegistrationTests, ZapTests) {
      Given("User logs in as an Organisation")
      AuthLoginPage.loginAsAutomatchedOrgAdmin()
      When("The user makes their way through the journey")
      IsThisYourBusinessPage.matchedBusinessYes()
      AddContact
        .continueSettingYourContact()
        .addFirstContact()
        .haveSecondContactNo()
      CheckYourAnswerPage.validatePageHeader("Check your answers before you register for CRS and FATCA")
      //Commenting below code to figure out id for change button on CYA
      //CheckYourAnswerPage.clickOnChangeYourBusinessAddress()
      //UnableToChangeBusinessPage.validatePageHeader("You’re unable to change your business details")
    }
  }
}
