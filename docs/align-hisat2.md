---
layout: default
title: align-star
---

This workflow uses HISAT2 to map RNA sequencing data to a reference genome.

## Usage
You can run the workflow using
[Cromwell](http://cromwell.readthedocs.io/en/stable/):
```bash
java -jar cromwell-<version>.jar run -i inputs.json align-hisat2.wdl
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

  "AlignHisat2.readgroups": "A list of readgroup identifiers, one for each fastq file (pair) provided",
  "AlignHisat2.sample": "A sample identifier",
  "AlignHisat2.library": "A sequencing library identifier",
  "AlignHisat2.outputDir": "The path to the output directory",
  "AlignHisat2.inputReads": "A list of fastq pairs (R2 is optional; see the example below) in the same order as the associated readgroup identifiers",
  "AlignHisat2.indexFiles": "The path to the HISAT2 index files"
}
```

Some additional inputs which may be of interest are:
```json
{
  "AlignHisat2.platform": "The sequencing platform used, this defaults to 'illumina'",
  "AlignHisat2.hisat2.threads": "The number of threads to be used, defaults to 1"
}
```

#### Example
```json
{
  "AlignHisat2.inputReads": [
    {
      "R1": "/data/patient1/lane1_R1.fq.gz",
      "R2": "/data/patient1/lane1_R2.fq.gz"
    },{
      "R1": "/data/patient1/lane2_R1.fq.gz",
      "R2": "/data/patient1/lane2_R2.fq.gz"
    }
  ],
  "AlignHisat2.outputDir": "/mapping/results",
  "AlignHisat2.sample": "s1",
  "AlignHisat2.library": "lib1",
  "AlignHisat2.readgroups": ["lane1", "lane2"],
  "AlignHisat2.indexFiles": [
    "/hisat2_index/reference.1.ht2",
    "/hisat2_index/reference.2.ht2",
    "/hisat2_index/reference.3.ht2",
    "/hisat2_index/reference.4.ht2",
    "/hisat2_index/reference.5.ht2",
    "/hisat2_index/reference.6.ht2",
    "/hisat2_index/reference.7.ht2",
    "/hisat2_index/reference.8.ht2"
  ],
  "AlignHisat2.hisat2.threads": 4
}
```

### output
This workflow produces a directory containing the coordinate-sorted BAM
file and its index.
