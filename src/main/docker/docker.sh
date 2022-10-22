#!/bin/bash
rm -r *.jar
find ../../../one/target/ -name "*.jar" -exec cp '{}' ./ \;
docker-compose up
trap "docker-compose down" EXIT
