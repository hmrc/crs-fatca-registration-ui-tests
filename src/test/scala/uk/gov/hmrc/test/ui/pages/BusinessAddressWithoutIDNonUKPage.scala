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

object BusinessAddressWithoutIDNonUKPage extends BasePage {

  override val pageUrl: String = baseUrl + "/without-id/business-address"

  private val addressLine1 = By.id("addressLine1")
  private val addressLine2 = By.id("addressLine2")
  private val city         = By.id("addressLine3")
  private val region       = By.id("addressLine4")
  private val postcode     = By.id("postCode")

  def enterAddressNonUK(): Unit = {
    onPage(pageUrl)
    sendTextById(addressLine1, "17 Beechfield Manor")
    sendTextById(addressLine2, "Aghalee")
    sendTextById(city, "Paris")
    sendTextById(region, "North")
    sendTextById(postcode, "NE13 2XH")
    countryAutoSelect("Albania")
    submitPageById()
  }

}
