---
layout: default
title: align-star
version: develop
latest: false
---

This workflow uses STAR to map RNA sequencing data to a reference genome.

## Usage
You can run the workflow using
[Cromwell](http://cromwell.readthedocs.io/en/stable/):
```bash
java -jar cromwell-<version>.jar run -i inputs.json align-star.wdl
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
  "AlignStar.readgroups": "A list of readgroup identifiers, one for each fastq file (pair) provided",
  "AlignStar.sample": "A sample identifier",
  "AlignStar.library": "A sequencing library identifier",
  "AlignStar.outputDir": "The path to the output directory",
  "AlignStar.inputR1": "A list of first-end fastq files (in the same order as the associated readgroup identifiers)",
  "AlignStar.inputR2": "A list of second-end fastq files (in the same order as the associated readgroup identifiers). This input may be ignored for single-end sequencing experiments",
  "AlignStar.starIndexDir": "The path to the STAR index"
}
```

Some additional inputs which may be of interest are:
```json
{
  "AlignStar.platform": "The sequencing platform used, this defaults to 'illumina'",
  "AlignStar.star.runThreadN": "The number of threads to be used, defaults to 1",
  "AlignStar.star.twopassMode": "The two-pass mode to be used (if any)"
}
```

#### Example
```json
{
  "AlignStar.readgroups": [
    "lane1",
    "lane2"
  ],
  "AlignStar.sample": "s1",
  "AlignStar.library": "lib1",
  "AlignStar.outputDir": "/home/user/mapping/results",
  "AlignStar.inputR1": [
    "/home/user/data/patient1/lane1_R1.fq.gz",
    "/home/user/data/patient1/lane2_R1.fq.gz"
  ],
  "AlignStar.inputR2": [
    "/home/user/data/patient1/lane1_R2.fq.gz",
    "/home/user/data/patient1/lane2_R2.fq.gz"
  ],
  "AlignStar.starIndexDir": "/home/user/genomes/human/star",
  "AlignStar.star.runThreadN": "The number of threads to be used, defaults to 1",
}
```

### output
This workflow produces a directory containing the STAR output, including a
coordinate-sorted BAM file and its index.
