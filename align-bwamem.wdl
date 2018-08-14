version 1.0

import "tasks/bwa.wdl" as bwa
import "tasks/samtools.wdl" as samtools

workflow AlignBwaMem {
    input {
        String outputDir
        String sample
        String library
        String readgroup
        String? platform = "illumina"
        File inputR1
        Array[File] indexFiles
        File refFasta
        File? inputR2
    }

    String prefixPath = outputDir + "/" + sample + "-" + library + "-" + readgroup

    call bwa.Mem as bwaMem {
        input:
            inputR1 = inputR1,
            inputR2 = inputR2,
            outputPath = prefixPath + ".bam",
            readgroup = "@RG\tID:${sample}-${library}-${readgroup}\tSM:${sample}\tLB:${library}\tPL:${platform}",
            indexFiles = indexFiles,
            referenceFasta = refFasta
    }

    call samtools.Index as samtoolsIndex {
        input:
            bamFilePath = bwaMem.bamFile,
            bamIndexPath = prefixPath + ".bai"
    }

    output {
        File bamFile = bwaMem.bamFile
        File bamIndexFile = samtoolsIndex.indexFile
    }
}
