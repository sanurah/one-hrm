#!/bin/bash
rm -r *.jar
find ../../../app/target/ -name "*.jar" -exec cp '{}' ./ \;
find ../../../auth/target/ -name "*.jar" -exec cp '{}' ./ \;
docker-compose -p 'one-hrm' up
trap "docker-compose  -p 'one-hrm' down" EXIT
