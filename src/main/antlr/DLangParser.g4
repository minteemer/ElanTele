parser grammar DLangParser;

options { tokenVocab = DLangParser; }

program
    : NL* (statement (SEMICOLON|NL)*)+ EOF
    ;

declaration
    : VAR variableDefinition (COMMA variableDefinition)
    ;

variableDefinition
    : Identifier (ASSIGNMENT expression)?
    ;

statement
    : declaration |assignment| if |loop|return|print
    ;

assignment
    : reference ASSIGNMENT expression
    ;

if
    : IF expression THEN body (ELSE body) END
    ;

loop
    : (WHILE expression THEN body END)
    | (FOR Identifier IN? expression RANGE expression)
    ;

return
    : RETURN (expression)?
    ;

print
    : expression (COMMA expression)?
    ;

expression
    : relation ( (OR|AND|XOR) relation)*
    ;

relation
    : factor ((LE|GE|EXCL_EQ|LANGLE|RANGLE|EQEQ) factor)?
    ;

factor
    : term ((ADD|SUB) term)*
    ;

term
    : unary ((MULT|DIV) unary)*
    ;

unary
    : reference
    | reference IS typeIndicator
    | (ADD|SUB|NOT)? primary
    ;

primary
    : literal
    | ReadInt
    | ReadReal
    | ReadString
    | functionLiteral
    |
    ;
typeIndicator
    : INTTYPE|REALTYPE|STRINGTYPE
    | EMPTY
    | ARRAYTYPE
    | TUPPLETYPE
    | FUNC
    ;
functionLiteral
    : FUNC (LPAREN Identifier (COMMA Identifier)* RPAREN)? funBody
    ;

funBody
    : IS body END
    |expression
    ;

reference
    :Identifier
    |reference LSQUARE expression RSQUARE
    |reference LPAREN expression (COMMA expression)* RPAREN
    |reference DOT Identifier
    ;

literal
    : INTEGER
    | REAL
    | STRING
    | BOOLEAN
    | TUPLE
    | ARRAY
    | EMPTY
    ;
boolean
    : TRUE
    | FALSE
    ;

array
    : LSQUARE (expression (COMMA expression)*)? RSQUARE
    ;
tupple
    : LCURL tuppleElement (COMMA tuppleElement)* RCURL
    ;
tuppleElement
    : Identifier ASSIGNMENT expression
    ;
body
    : declaration
    |statement
    |expression
    ;