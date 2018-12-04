# Grammar 

DOT: **`.`**  
COMMA: **`,`**  
LPAREN: **`(`**  
RPAREN: **`)`**  
LSQUARE: **`\[`**   
RSQUARE: **`\]`**  
LCURL: **`{`**  
RCURL: **`}`**  
MULT: **`*`**  
DIV: **`/`**  
ADD: **`+`**  
SUB: **`-`**  
COLON: **`:`**  
SEMICOLON: **`;`**  
ASSIGNMENT: **`:=`**  
DOUBLE_ARROW: **`=>`**  
ELVIS: **`?:`**  
LANGLE: **`<`**  
RANGLE: **`>`**  
RANGE: **'.' ;**  
LE: **`<=`**  
GE: **`>=`**  
EXCL_EQ: **`/=`**  
EQEQ: **`=`**  

CONJ: **`and`**  
DISJ: **`or`**  
XOR: **`xor`**  
NOT: **`not`**  

FUNC: **`func`** ;  
VAR: **`var`** ;  
IF: **`if`** ;  
THEN: **`then`** ;  
IN: **`in`** ;  
ELSE: **`else`** ;  
WHEN: **`when`** ;  
FOR: **`for`** ;  
WHILE: **`while`** ;  
RETURN: **`return`** ;  
IS: **`is`** ;  
END: **`end`** ;  
PRINT: **`print`** ;  
LOOP: **`loop`** ;  


IntType: **`int`**  
RealType: **`real`**  
StringType: **`string`**  
BoolType: **`bool`**   
EmptyType: **`empty`**  
ArrayType: **`[]`**  
TupleType: **`{}`**  


ReadInt: **`readInt`**  
ReadReal: **`readReal`**  
ReadString: **`readString`**  
  
Identifier:  `(Letter | '\_') (Letter | '\_' | DecDigit)*`  

## Notation:

**`NL`** - new line  
**`EOF`** - end of file  
**`+`** - one or more  
**`*`** - zero or more  
**`?`** - one or zero  

## Grammar changes

### Body
Since sequence of statements is used many times in many  grammar rules e.g. in if statement, while loop, for loop and etc. was decided to move sequence of statement to separate grammar rule:

#### Was:
`Program : { Statement ‘;’ } EOF`  

#### Become:
`Program :  body EOF`  
`Body: ((Statement ) NL*)+`    

### For loop

Definition of loop:  
`FOR (Identifier IN)? (Expression .. Expression)? LOOP Body END`

The definition allow us to not specify range of values to iterate which does not make sense, so we redefined for loop in following way:  
`FOR (Identifier IN)? (Expression .. Expression)  LOOP  body  END`
    
### Boolean operations

Boolean operations had no priority. So:
```
expression
    : xor
    | expression (XOR xor)
    ;

xor
    : disjuntion
    | xor DISJ disjuntion
    ;

disjuntion
    : relation
    | disjuntion CONJ relation
    ;
```

## Whole grammar

```
program
    : NL* body EOF
    ;

declaration
    : VAR variableDefinition (COMMA variableDefinition)*
    ;

variableDefinition
    : Identifier (ASSIGNMENT expression)?
    ;

statement
    : declaration SEMICOLON?
    | assignment SEMICOLON?
    | if_expression SEMICOLON?
    | loop SEMICOLON?
    | return_expression SEMICOLON?
    | print SEMICOLON?
    ;

assignment
    : reference ASSIGNMENT expression
    ;

if_expression
    : IF expression NL* THEN NL* body NL* else_branch? END
    ;

else_branch
    : ELSE NL* body NL*
    ;

loop
    : while_loop
    | for_loop
    ;
while_loop
    :WHILE expression NL* LOOP NL* body NL*  END
    ;
for_loop
    :FOR (Identifier IN)? (expression RANGE expression) NL* LOOP NL* body NL* END
    ;
return_expression
    : RETURN expression?
    ;

print
    : PRINT expression (COMMA expression)*
    ;

expression
    : xor
    | expression (XOR xor)
    ;

xor
    : disjuntion
    | xor DISJ disjuntion
    ;

disjuntion
    : relation
    | disjuntion CONJ relation
    ;

relation
    : factor ((LE|GE|EXCL_EQ|LANGLE|RANGLE|EQEQ) factor)?
    ;

factor
    : term
    | factor ((ADD|SUB) term)
    ;

term
    : unary
    | term ((MULT|DIV) unary)
    ;

unary
    : reference
    | reference IS typeIndicator
    | (ADD | SUB | NOT)? primary
    ;

primary
    : literal
    | ReadInt
    | ReadReal
    | ReadString
    | functionLiteral
    | LPAREN expression RPAREN
    ;

typeIndicator
    : IntType
    | RealType
    | StringType
    | BoolType
    | EmptyType
    | ArrayType
    | TupleType
    | FUNC
    ;

functionLiteral
    : FUNC (LPAREN Identifier (COMMA Identifier)* RPAREN)? funBody
    ;

funBody
    : IS NL* body NL* END
    | DOUBLE_ARROW expression
    ;

reference
    : variableReference
    | reference arrayElementReference
    | reference functionCallReference
    | reference dictElementNumberReference
    | reference dictElementIdentifierReference
    ;

variableReference
    : Identifier
    ;

arrayElementReference
    : LSQUARE expression RSQUARE
    ;

functionCallReference
    : LPAREN expression? (COMMA expression)* RPAREN
    ;

dictElementIdentifierReference
    : DOT Identifier
    ;

dictElementNumberReference
    : DOT IntegerLiteral
    ;

literal
    : IntegerLiteral
    | RealLiteral
    | lineStringLiteral
    | BooleanLiteral
    | tuple
    | array
    | EmptyType
    ;

lineStringLiteral
    : DOUBLE_QUOTE_OPEN (lineStringContent)* DOUBLE_QUOTE_CLOSE
    | SINGLE_QUOTE_OPEN (lineStringContent)* SINGLE_QUOTE_CLOSE
    ;

lineStringContent
    : LineStrText
    | LineStrEscapedChar
    ;

array
    : (LSQUARE (expression (COMMA expression)*)? RSQUARE)
    | ArrayType
    ;

tuple
    : LCURL tupleElement (COMMA tupleElement)* RCURL
    ;

tupleElement
    : (Identifier ASSIGNMENT)? expression
    ;

body
    : ((statement ) NL*)+
    ;
```