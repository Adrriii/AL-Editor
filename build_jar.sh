#!/bin/sh

gradle clean
gradle jar
cp -R assets/ build/libs/