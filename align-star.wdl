version 1.0

import "tasks/star.wdl" as star_task
import "tasks/samtools.wdl" as samtools

workflow AlignStar {
    input {
        Array[File] inputR1
        Array[File]? inputR2
        String outputDir
        String sample
        String library
        Array[String] readgroups
        String? platform = "illumina"
    }

    scatter (rg in readgroups) {
        String rgLine =
            '"ID:${rg}" "LB:${library}" "PL:${platform}" "SM:${sample}"'
    }

    call star_task.Star as star {
        input:
            inputR1 = inputR1,
            inputR2 = inputR2,
            outFileNamePrefix = outputDir + "/" + sample + "-" + library + ".",
            outSAMattrRGline = rgLine
    }

    call samtools.Index as samtoolsIndex {
        input:
            bamFilePath = star.bamFile,
            # This will only work if star.outSAMtype == "BAM SortedByCoordinate"
            bamIndexPath = outputDir + "/" + sample + "-" + library +
                ".Aligned.sortedByCoord.out.bai"
    }

    output {
        File bamFile = star.bamFile
        File bamIndexFile = samtoolsIndex.indexFile
    }
}
