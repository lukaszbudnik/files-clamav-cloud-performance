#!/bin/bash

cd /tmp
git clone https://github.com/philvarner/clamavj.git
cd clamavj
mvn -DskipTests install

