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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.domain.{Generator, SaUtrGenerator}

trait IdGenerators {

  val randomisedNino: String = new Generator().nextNino.toString()
  val randomisedUtr: String  = new SaUtrGenerator().nextSaUtr.toString()

  //prefixes
  val ctutr          = "111"
  val preRegUtr      = "222"
  val individualNino = "AA1"

  def generateUtr(prefix: String) =
    prefix + randomisedUtr.substring(3)

  def generateNino(prefix: String) =
    prefix + randomisedNino.substring(3)

}
