# ElanTele dynamic language
## Build
Run `./gradlew build` to build the project (or `bash gradlew build`).

## Run
After building the project, unpack `build/distributions/ElanTele-1.0.tar` 
or `build/distributions/ElanTele-1.0.zip` and run `bin/ElanTele`

## Tatar language support
Run `bin/ElanTele -t` to use ElanTele in Tatar mode

Example of bubble sort in Tatar ElanTele:
```
вар җыештырылмаган := [1, 4, 88, 2, 11, 100, 55]
яз җыештырылмаган
вар озынлык := 0
әлегә җыештырылмаган[озынлык] бу сан элмәк
    озынлык := озынлык + 1
бетте
вар ә, җ
дәвамында ә эчендә 1..озынлык элмәк
    дәвамында җ эчендә 1..(озынлык - 1) элмәк
        әгәр җыештырылмаган[җ] < җыештырылмаган [җ - 1] бу_очракта
            вар вакытлы := җыештырылмаган[җ]
            җыештырылмаган[җ] := җыештырылмаган[җ - 1]
            җыештырылмаган[җ - 1] := вакытлы
        бетте
    бетте
бетте
яз җыештырылмаган
```

You can find it in `in.txt`. Run `bin/ElanTele -t in.txt` to see it in action.