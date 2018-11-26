---
layout: default
title: align-bwamem
version: develop
latest: false
---

This workflow uses BWA mem to map DNA sequencing data to a reference genome.

## Usage
You can run the workflow using
[Cromwell](http://cromwell.readthedocs.io/en/stable/):
```bash
java -jar cromwell-<version>.jar run -i inputs.json align-bwamem.wdl
```

### Inputs
Inputs are provided through a JSON file. The minimally required inputs are
described below and a template containing all possible inputs can be generated
using Womtool as described in the
[WOMtool documentation](http://cromwell.readthedocs.io/en/stable/WOMtool/).
See [this page](/inputs.html) for some additional general notes and information
about pipeline inputs.

```json
{
  "AlignBwaMem.library": "A identifier for the sequencing library",
  "AlignBwaMem.sample": "A identifier for the sample",
  "AlignBwaMem.readgroup": "A identifier for the readgroup (eg. sequencing lane)",
  "AlignBwaMem.inputFastq": {
    "R1": "The path to the first-end fastq file",
    "R2": "The second-end fastq file. This input may be ignored for single-end sequencing experiments"
  },
  "AlignBwaMem.bwaIndex": {
    "fastaFile": "A path to the fasta file from the bwa index",
    "indexFiles": "A list containing the other bwa index files"
  },
  "AlignBwaMem.outputDir": "The path to the output directory"
}
```

Some additional inputs which may be of interest are:
```json
{
  "AlignBwaMem.platform": "The sequencing platform used, this defaults to 'illumina'",
  "AlignBwaMem.bwaMem.threads": "The number of threads to be used by BWA mem, this defaults to 2",
}
```

#### Example
```json
{
  "AlignBwaMem.library": "lib1",
  "AlignBwaMem.sample": "s1",
  "AlignBwaMem.readgroup": "lane1",
  "AlignBwaMem.inputFastq": {
    "R1": "/home/user/data/patient1/R1.fq.gz"
  },
  "AlignBwaMem.bwaIndex": {
    "fastaFile": "/home/user/genomes/human/bwa/GRCh38.fasta",
    "indexFiles": [
      "/home/user/genomes/human/bwa/GRCh38.fasta.sa",
      "/home/user/genomes/human/bwa/GRCh38.fasta.amb",
      "/home/user/genomes/human/bwa/GRCh38.fasta.ann",
      "/home/user/genomes/human/bwa/GRCh38.fasta.bwt",
      "/home/user/genomes/human/bwa/GRCh38.fasta.pac"
    ]
  },
  "AlignBwaMem.outputDir": "/home/user/mapping/results",
  "AlignBwaMem.bwaMem.threads": 8
}
```

### output
This workflow produces a directory containing a coordinate-sorted BAM file and
its index.
