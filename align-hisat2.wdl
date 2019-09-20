version 1.0

import "tasks/common.wdl" as common
import "tasks/hisat2.wdl" as hisat2Task
import "tasks/samtools.wdl" as samtools

workflow AlignHisat2 {
    input {
        Array[FastqPair]+ inputReads # Using a struct here makes scattering easier
        String outputDir = "."
        String sample
        String library
        Array[String] readgroups
        String platform = "illumina"
        Array[File]+ indexFiles

        Map[String, String] dockerImages = {
            # quay.io/biocontainers/mulled-v2-a97e90b3b802d1da3d6958e0867610c718cb5eb1
            # is a combination of hisat2 and samtools
            # hisat2=2.1.0, samtools=1.8
            "hisat2": "quay.io/biocontainers/mulled-v2-a97e90b3b802d1da3d6958e0867610c718cb5eb1:2388ff67fc407dad75774291ca5038f40cac4be0-0",
            "samtools": "quay.io/biocontainers/samtools:1.8--h46bd0b3_5"}
    }

    scatter (rg in zip(readgroups, inputReads)){
        String readgroup = rg.left
        FastqPair reads = rg.right

        call hisat2Task.Hisat2 as hisat2 {
            input:
                indexFiles = indexFiles,
                inputR1 = reads.R1,
                inputR2 = reads.R2,
                outputBam = outputDir + "/" + sample + "-" + library + "-" + readgroup + ".bam",
                sample = sample,
                library = library,
                readgroup = readgroup,
                platform = platform,
                dockerImage = dockerImages["hisat2"]
        }
    }

    call samtools.Merge as samtoolsMerge {
        input:
            bamFiles =  hisat2.bamFile,
            outputBamPath = outputDir + "/" + sample + "-" + library + ".bam",
            dockerImage = dockerImages["samtools"]
    }

    output {
        IndexedBamFile bamFile = {
            "file": samtoolsMerge.outputBam,
            "index": samtoolsMerge.outputBamIndex
        }
    }
}
