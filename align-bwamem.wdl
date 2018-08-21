version 1.0

import "tasks/bwa.wdl" as bwa
import "tasks/samtools.wdl" as samtools
import "tasks/bwa.wdl" as bwa

workflow AlignBwaMem {
    input {
        String outputDir
        String sample
        String library
        String readgroup
        String? platform = "illumina"
        File inputR1
        File? inputR2
        BwaIndex bwaIndex
    }

    String prefixPath = outputDir + "/" + sample + "-" + library + "-" + readgroup

    call bwa.Mem as bwaMem {
        input:
            inputR1 = inputR1,
            inputR2 = inputR2,
            outputPath = prefixPath + ".bam",
            readgroup = "@RG\tID:${sample}-${library}-${readgroup}\tSM:${sample}\tLB:${library}\tPL:${platform}",
            bwaIndex = bwaIndex
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
