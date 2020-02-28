# ElanTele dynamic language
*By Timur Valiev, Vasiliy Alabugin, Nikolay Matyashov, 
Ilgizar Murzakov and Arsen Kuzmin*
## Build
Run `./gradlew build` to build the project (or `bash gradlew build`).

## Run
After building the project, unpack `build/distributions/ElanTele-1.0.tar` 
or `build/distributions/ElanTele-1.0.zip` and run `bin/ElanTele` to start REPL
or `bin/ElanTele example.et` to execute a file.


## Grammar 
[Grammar](grammar.md)

## Example of code
```
var unsorted := [1, 4, 88, 2, 11, 100, 55]
print unsorted
var length := 1
while unsorted[length] is int loop
    length := length + 1
end
var i, j
for i in 2..length loop
    for j in 2..(length - 1) loop
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
Run `./ElanTele -t` to use ElanTele in Tatar mode

Example of bubble sort in Tatar ElanTele:
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
        әгәр җыештырылмаган[җ] < җыештырылмаган [җ - 1] буочракта
            вар вакытлы := җыештырылмаган[җ]
            җыештырылмаган[җ] := җыештырылмаган[җ - 1]
            җыештырылмаган[җ - 1] := вакытлы
        бетте
    бетте
бетте
яз җыештырылмаган
```

You can find it in [example.et](example.et). Run `./ElanTele -t example.et` to see it in action.

## ElanTele Syntax highlighting
You can use syntax file `ElanTele.sublime-syntax` written for Sublime Text 3 
in order to use our language more comfortably.

Installation manual (Windows):
1) Find `User` folder:
    - Go to Preferences > Browse Packages... . Browse `User` folder 
    in opened window 
    
    OR
    - Go to `C:\Users\%USER%\AppData\Roaming\Sublime Text 3\Packages\User`
2) Move `ElanTele.sublime-syntax` file inside `User` folder
3) After restarting Sublime go to View > Syntax > Elan Tele
