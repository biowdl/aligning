import "tasks/star.wdl" as star_task
import "tasks/samtools.wdl" as samtools

workflow AlignStar {
    Array[File] inputR1
    Array[File]? inputR2
    String outputDir
    String sample
    String library
    Array[String] rgLine

    call star_task.Star as star {
        input:
            inputR1 = inputR1,
            inputR2 = inputR2,
            outFileNamePrefix = outputDir + sample + "-" + library + ".",
            outSAMattrRGline = rgLine
    }

    call samtools.Index as samtoolsIndex {
        input:
            bamFilePath = star.bamFile
    }

    output {
        File bamFile = star.bamFile
        File bamIndexFile = samtoolsIndex.indexFile
    }
}

