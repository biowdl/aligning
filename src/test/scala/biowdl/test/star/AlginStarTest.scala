/*
 * Copyright (c) 2018 Biowdl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package biowdl.test.star

import java.io.File

import nl.biopet.utils.biowdl.fixtureFile
import nl.biopet.utils.biowdl.references.TestReference

trait AlgignStarSingleEnd extends AlgignStarSuccess with TestReference {
  def sample: Option[String] = Some("wgs1")
  def library: Option[String] = Some("lib1")
  def readgroups: Option[List[String]] = Some("rg1" :: Nil)

  def inputR1: Option[List[File]] =
    Some(fixtureFile("samples", "wgs1", "R1.fq.gz") :: Nil)
  def inputR2: Option[List[File]] = None
}

trait AlgignStarPairedEnd extends AlgignStarSingleEnd {
  override def inputR2: Option[List[File]] =
    Some(fixtureFile("samples", "wgs1", "R2.fq.gz") :: Nil)
}

class AlgignStarSingleEndTest extends AlgignStarSingleEnd with AlgignStarSuccess
class AlgignStarPairedEndTest extends AlgignStarPairedEnd with AlgignStarSuccess
