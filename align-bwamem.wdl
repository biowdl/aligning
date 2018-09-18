version 1.0

import "tasks/bwa.wdl" as bwa
import "tasks/common.wdl" as common

workflow AlignBwaMem {
    input {
        String outputDir
        String sample
        String library
        String readgroup
        String? platform = "illumina"
        FastqPair inputFastq
        BwaIndex bwaIndex
    }

    String prefixPath = outputDir + "/" + sample + "-" + library + "-" + readgroup

    call bwa.Mem as bwaMem {
        input:
            inputFastq = inputFastq,
            outputPath = prefixPath + ".bam",
            readgroup = "@RG\tID:${sample}-${library}-${readgroup}\tSM:${sample}\tLB:${library}\tPL:${platform}",
            bwaIndex = bwaIndex
    }

    output {
        IndexedBamFile bamFile = bwaMem.bamFile
    }
}
