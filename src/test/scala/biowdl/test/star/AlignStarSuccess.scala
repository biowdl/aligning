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

import htsjdk.samtools.{
  SAMFileHeader,
  SAMReadGroupRecord,
  SamReader,
  SamReaderFactory
}
import nl.biopet.utils.biowdl.PipelineSuccess
import org.testng.annotations.Test

trait AlignStarSuccess extends AlignStar with PipelineSuccess {
  val bamFile: File = new File(outputDir,
    s"$sample-$library.Aligned.sortedByCoord.out.bam")
  val baiFile: File = new File(outputDir,
    s"$sample-$library.Aligned.sortedByCoord.out.bai")

  addMustHaveFile(bamFile)
  addMustHaveFile(baiFile)

  val bamReader: SamReader = SamReaderFactory.makeDefault().open(bamFile)

  @Test
  def testReadgroups(): Unit = {
    val readGroups = bamReader.getFileHeader.getReadGroups
    readGroups.size shouldBe readgroups.get.size

    readgroups.get.foreach(rg => {
      val correctReadgroup: SAMReadGroupRecord = new SAMReadGroupRecord(rg)
      correctReadgroup.setPlatform(platform.getOrElse("illumina"))
      correctReadgroup.setLibrary(library.get)
      correctReadgroup.setSample(sample.get)

      correctReadgroup
        .equivalent(bamReader.getFileHeader.getReadGroup(rg)) shouldBe true
    })
  }

  @Test
  def testSortOrder(): Unit = {
    bamReader.getFileHeader.getSortOrder shouldBe SAMFileHeader.SortOrder.coordinate
  }
}
