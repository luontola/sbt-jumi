#!/bin/bash
set -eux

sbt clean test scripted publish-signed
