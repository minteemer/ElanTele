parser grammar ElanTeleParser;

options { tokenVocab = ElanTeleLexer; }

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
    : IF expression NL* THEN NL* body NL* (ELSE NL* body NL*)? END
    ;

loop
    : (WHILE expression NL* LOOP NL* body NL*  END)
    | (FOR (Identifier IN)? (expression RANGE expression) NL* LOOP NL* body NL* END)
    ;

return_expression
    : RETURN expression?
    ;

print
    : PRINT expression (COMMA expression)?
    ;

expression
    : relation
    | expression ((OR|AND|XOR) relation)
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
    : Identifier
    | reference LSQUARE expression RSQUARE
    | reference LPAREN expression (COMMA expression)* RPAREN
    | reference DOT IntegerLiteral
    | reference DOT Identifier
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
    : ((declaration | statement | expression) NL?)+
    ;