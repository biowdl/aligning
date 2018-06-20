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

import nl.biopet.utils.biowdl.Pipeline
import nl.biopet.utils.biowdl.references.Reference

trait AlignStar extends Pipeline with Reference {

  def sample: Option[String]
  def library: Option[String]
  def readgroups: Option[List[String]]
  def platform: Option[String] = None
  def inputR1: Option[List[File]]
  def inputR2: Option[List[File]]
  def genomeDir: Option[File] = starGenomeDir //TODO remove this?

  override def inputs: Map[String, Any] =
    super.inputs ++
      Map(
        "AlignStar.outputDir" -> outputDir.getAbsolutePath,
        "AlignStar.refFasta" -> referenceFasta.getAbsolutePath,
        "AlignStar.refFastaIndex" -> referenceFastaIndexFile.getAbsolutePath,
        "AlignStar.refDict" -> referenceFastaDictFile.getAbsolutePath
      ) ++
      genomeDir.map("AlignStar.star.genomeDir" -> _) ++
      sample.map("AlignStar.sample" -> _) ++
      library.map("AlignStar.library" -> _) ++
      readgroups.map("AlignStar.readgroups" -> _) ++
      platform.map("AlignStar.platform" -> _) ++
      sample.map("AlignStar.sample" -> _) ++
      inputR1.map("AlignStar.inputR1" -> _) ++
      inputR2.map("AlignStar.inputR2" -> _)

  def startFile: File = new File("./align-star.wdl")
}
