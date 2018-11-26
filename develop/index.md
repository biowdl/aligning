---
layout: default
title: Home
version: develop
latest: false
---

This repository contains a collection of [BioWDL](https://github.com/biowdl)
workflows which can be used for aligning sequencing data. There are currently
two workflows available:
- [align-bwamem.wdl](./align-bwamem.html): Uses BWA mem to align DNA.
- [align-star.wdl](./align-star.html): Uses STAR to align RNA.

## About
These workflows are part of [BioWDL](https://biowdl.github.io/)
developed by [the SASC team](http://sasc.lumc.nl/).

## Dependency requirements and tool versions
Included in the repository is an `environment.yml` file. This file includes
all the tool version on which the workflow was tested. You can use conda and
this file to create an environment with all the correct tools.

## Contact
<p>
  <!-- Obscure e-mail address for spammers -->
For any questions about running these workflows and feature request (such as
adding additional options), please use the
<a href='https://github.com/biowdl/aligning/issues'>github issue tracker</a>
or contact
 <a href='http://sasc.lumc.nl/'>the SASC team</a> directly at: <a href='&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;'>
&#115;&#97;&#115;&#99;&#64;&#108;&#117;&#109;&#99;&#46;&#110;&#108;</a>.
</p>
