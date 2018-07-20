---
layout: default
title: Home
version: develop
latest: true
---

This repository contains a collection of [BioWDL](https://github.com/biowdl)
workflows which can be used for aligning sequencing data. There are currently
two workflows available:
- align-bwamem.wdl: Uses BWA mem to align DNA.
- align-star.wdl: Uses STAR to align RNA.

## Usage

### `align-bwamem.wdl`
`align-bwamem.wdl` can be run using
[Cromwell](http://cromwell.readthedocs.io/en/stable/):
```
java -jar cromwell-<version>.jar run -i inputs.json align-bwamem.wdl.wdl
```

The inputs JSON can be generated using WOMtools as described in the [WOMtools
documentation](http://cromwell.readthedocs.io/en/stable/WOMtool/).

The primary inputs are described below, additional inputs (such as precommands)
are available. Please use the above mentioned WOMtools command to see all
available inputs.

| field | type | default | |
|-|-|-|-|
| inputR1 | `File` | | The first-end input FastQ. |
| inputR2 | `File?` | | The second-end input FastQ. |
| outputDir | `String` | | The directory in which the output will be placed. |
| bwaMem.referenceFasta | `File` | | The reference fasta. |
| bwaMem.indexFiles | `Array[File]` | | The BWA index files. |
| sample | `String` | | An identifier for the sample being aligned. |
| library | `String` | | An identifier for the library being aligned. |
| readgroup | `String` | | An identifier for the readgroup being aligned. |
| platform | `String?` | `"illumina"` | The platform used for sequencing. |

>All inputs have to be preceded by with `AlignBwaMem.`.
Type is indicated according to the WDL data types: `File` should be indicators
of file location (a string in JSON). Types ending in `?` indicate the input is
optional, types ending in `+` indicate they require at least one element.

### `align-star.wdl`
`align-star.wdl` can be run using
[Cromwell](http://cromwell.readthedocs.io/en/stable/):
```
java -jar cromwell-<version>.jar run -i inputs.json align-star.wdl.wdl
```

The inputs JSON can be generated using WOMtools as described in the [WOMtools
documentation](http://cromwell.readthedocs.io/en/stable/WOMtool/).

The primary inputs are described below, additional inputs (such as precommands)
are available. Please use the above mentioned WOMtools command to see all
available inputs.

| field | type | default | |
|-|-|-|-|
| inputR1 | `Array[File]` | |  Input first-end FastQ files. |
| inputR2 | `Array[File]?` | | Input second-end FastQ files. |
| outputDir | `String` | | The directory in which the output will be placed. |
| star.genomeDir | `String` | | The STAR index. |
| sample | `String` | | An identifier for the sample being aligned. |
| library | `String` | | An identifier for the library being aligned. |
| readgroups |`Array[String]` | | Readgroup identifiers in the same order as the associated FastQ files in inputR1 and inputR2. |
| platform | `String?` | `"illumina"` | The platform used for sequencing. |

>All inputs have to be preceded by with `AlignStar.`.
Type is indicated according to the WDL data types: `File` should be indicators
of file location (a string in JSON). Types ending in `?` indicate the input is
optional, types ending in `+` indicate they require at least one element.

## Tool versions
Included in the repository is an `environment.yml` file. This file includes
all the tool version on which the workflow was tested. You can use conda and
this file to create an environment with all the correct tools.

## Output
### `align-bwamem.wdl`
A directory containing a coordinate-sorted BAM file and its index.

### `align-star.wdl`
A directory containing the STAR output, including a coordinate-sorted BAM file
and its index.

## About
These workflows are part of [BioWDL](https://biowdl.github.io/)
developed by [the SASC team](http://sasc.lumc.nl/).

## Contact
<p>
  <!-- Obscure e-mail address for spammers -->
For any question related to these workflows, please use the
<a href='https://github.com/biowdl/aligning/issues'>github issue tracker</a>
or contact
 <a href='http://sasc.lumc.nl/'>the SASC team</a> directly at: <a href='&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;'>
&#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;</a>.
</p>
