import "tasks/star.wdl" as star_task
import "tasks/samtools.wdl" as samtools

workflow Mapping {
    String? preCommand = ""
    File inputR1
    File? inputR2
    String outputDir
    String sample
    String library
    String readgroup
    String? platform = "illumina"

    call star_task.Star as star {
        input:
            preCommand = "source activate /exports/sasc/dcats/snakemake/.conda/aa42d56d",
            inputR1 = inputR1,
            inputR2 = inputR2,
            outFileNamePrefix = outputDir + "/" + sample + "-" + library + "-" + readgroup + ".bam",
            outSAMattrRGline = "\"ID:" + readgroup +
                "\" \"LB:" + library +
                "\" \"PU:" + platform +
                "\" \"SM:" + sample + "\""
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