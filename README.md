# ElanTele dynamic language
## Build
Run `./gradlew build` to build the project (or `bash gradlew build`).

## Run
After building the project, unpack `build/distributions/ElanTele-1.0.tar` 
or `build/distributions/ElanTele-1.0.zip` and run `bin/ElanTele`

## Tatar language support
Run `bun/ElanTele -t` to use ElanTele in Tatar mode

```
вар җыештырылмаган := [1, 4, 88, 2, 11, 100, 55]
яз җыештырылмаган
вар озынлык := 1
әлегә җыештырылмаган[озынлык] бу сан элмәк
    озынлык := озынлык + 1
бетте
вар ә, җ
дәвамында ә эчендә 2..озынлык элмәк
    дәвамында җ эчендә 2..(озынлык - 1) элмәк
        әгәр җыештырылмаган[җ] < җыештырылмаган [җ - 1] бу_очракта
            вар вакытлы := җыештырылмаган[җ]
            җыештырылмаган[җ] := җыештырылмаган[җ - 1]
            җыештырылмаган[җ - 1] := вакытлы
        бетте
    бетте
бетте

яз җыештырылмаган
```