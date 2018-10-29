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

trait AlignStarSingleEnd extends AlignStarSuccess with TestReference {
  override def sample: Option[String] = Some("rna3")
  override def library: Option[String] = Some("lib1")
  override def readgroups: Option[List[String]] = Some("rg1" :: Nil)

  override def inputR1: Option[List[File]] =
    Some(fixtureFile("samples", "rna3", "R1.fq.gz") :: Nil)
  override def inputR2: Option[List[File]] = None

}

trait AlignStarPairedEnd extends AlignStarSingleEnd {
  override def inputR2: Option[List[File]] =
    Some(fixtureFile("samples", "rna3", "R2.fq.gz") :: Nil)
}

trait AlignStarMultipleReadgroupsSingleEnd
    extends AlignStarSuccess
    with TestReference {
  override def sample: Option[String] = Some("rna3")
  override def library: Option[String] = Some("lib1")
  override def readgroups: Option[List[String]] = Some(List("rg1", "rg2"))

  override def inputR1: Option[List[File]] =
    Some(
      List(fixtureFile("samples", "rna3", "rg1_1.fq.gz"),
           fixtureFile("samples", "rna3", "rg2_1.fq.gz")))
  override def inputR2: Option[List[File]] = None
}

trait AlignStarMultipleReadgroupsPairedEnd
    extends AlignStarMultipleReadgroupsSingleEnd {
  override def inputR2: Option[List[File]] =
    Some(
      List(fixtureFile("samples", "rna3", "rg1_2.fq.gz"),
           fixtureFile("samples", "rna3", "rg2_2.fq.gz")))
}

class AlignStarSingleEndTest extends AlignStarSingleEnd with AlignStarSuccess
class AlignStarPairedEndTest extends AlignStarPairedEnd with AlignStarSuccess
class AlignStarMultipleReadgroupsSingleEndTest
    extends AlignStarMultipleReadgroupsSingleEnd
    with AlignStarSuccess
class AlignStarMultipleReadgroupsPairedEndTest
    extends AlignStarMultipleReadgroupsPairedEnd
    with AlignStarSuccess
