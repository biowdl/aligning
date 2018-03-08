import "wdl-tasks/bwa.wdl" as bwa
import "wdl-tasks/samtools.wdl" as samtools

workflow Mapping {
    String outputDir
    String sample
    String library
    String readgroup
    File inputR1
    File? inputR2

    call bwa.BwaMem as bwaMem {
        input:
            inputR1 = inputR1,
            inputR2 = inputR2,
            outputPath = outputDir + "/" + sample + "-" + library + "-" + readgroup + ".bam",
            readgroup = "@RG\tID:${sample}-${library}-${readgroup}\tSM:${sample}\tLB:${library}"
    }

    call samtools.Index as samtoolsIndex {
        input:
            bamFilePath = bwaMem.bamFile
    }

    output {
        File bamFile = bwaMem.bamFile
        File bamIndexFile = samtoolsIndex.indexFile
    }
}