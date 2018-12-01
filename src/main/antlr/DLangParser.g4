parser grammar DLangParser;

options { tokenVocab = DLangLexer; }

program
    : NL* (statement (NL)*)+ EOF
    ;

declaration
    : VAR variableDefinition (COMMA variableDefinition)*
    ;

variableDefinition
    : Identifier (ASSIGNMENT expression)?
    ;

statement
    :declaration
    |assignment
    |if_expression
    |loop
    |return_expression
    |print
    ;

assignment
    : reference ASSIGNMENT expression
    ;

if_expression
    : IF expression THEN body (ELSE body) END
    ;

loop
    : (WHILE expression THEN body END)
    | (FOR Identifier IN? expression RANGE expression)
    ;

return_expression
    : RETURN (expression)?
    ;

print
    :PRINT expression (COMMA expression)?
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
    : IntegerLiteral
    | RealLiteral
    | lineStringLiteral
    | BooleanLiteral
    | tupple
    | array
    | EmptyLiteral
    ;
lineStringLiteral
    : QUOTE_OPEN (lineStringContent | lineStringExpression)* QUOTE_CLOSE
    ;
lineStringContent
    : LineStrText
    | LineStrEscapedChar
    | LineStrRef
    ;

lineStringExpression
    : LineStrExprStart expression RCURL
    ;
boolean_literal
    :
    BooleanLiteral
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
    | statement
    | expression
    ;