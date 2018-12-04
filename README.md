# ElanTele dynamic language
## Build
Run `./gradlew build` to build the project (or `bash gradlew build`).

## Run
After building the project, unpack `build/distributions/ElanTele-1.0.tar` 
or `build/distributions/ElanTele-1.0.zip` and run `bin/ElanTele`


## Grammar 
[Gramar](https://hackmd.io/s/H1EhcAXk4)

## Example of code
```
var unsorted := [1, 4, 88, 2, 11, 100, 55]
print unsorted
var length := 0
while unsorted[length] is int loop
    length := length + 1
end
var i, j
for i in 1..length loop
    for j in 1..(length - 1) loop
        if unsorted[j] < unsorted [j - 1] then
            var temp := unsorted[j]
            unsorted[j] := unsorted[j - 1]
            unsorted[j - 1] := temp
        end
    end
end
print unsorted
```

## Tatar language support
Run `bun/ElanTele -t` to use ElanTele in Tatar mode

```
вариацион җыештырылмаган := [1, 4, 88, 2, 11, 100, 55]
яз җыештырылмаган
вариацион озынлык := 0
әлегә җыештырылмаган[озынлык] бу сан элмәк
    озынлык := озынлык + 1
бетте
вариацион ә, җ
дәвамында ә эчендә 1..озынлык элмәк
    дәвамында җ эчендә 1..(озынлык - 1) элмәк
        әгәр җыештырылмаган[җ] < җыештырылмаган [җ - 1] бу_очракта
            вариацион вакытлы := җыештырылмаган[җ]
            җыештырылмаган[җ] := җыештырылмаган[җ - 1]
            җыештырылмаган[җ - 1] := вакытлы
        бетте
    бетте
бетте
яз җыештырылмаган
```