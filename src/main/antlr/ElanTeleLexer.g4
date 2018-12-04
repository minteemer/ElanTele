
lexer grammar ElanTeleLexer;

import UnicodeClasses;


DelimitedComment
    : '/*' ( DelimitedComment | . )*? '*/'
      -> channel(HIDDEN)
    ;

LineComment
    : '//' ~[\u000A\u000D]*
      -> channel(HIDDEN)
    ;

WS
    : [\u0020\u0009\u000C]
      -> skip
    ;

NL: '\u000A' | '\u000D' '\u000A';

//SEPARATORS & OPERATIONS

DOT: '.' ;
COMMA: ',' ;
LPAREN: '(' -> pushMode(Inside) ;
RPAREN: ')' ;
LSQUARE: '[' -> pushMode(Inside) ;
RSQUARE: ']' ;
LCURL: '{' ;
RCURL: '}' ;
MULT: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
SEMICOLON: ';' ;
ASSIGNMENT: ':=' ;
DOUBLE_ARROW: '=>' ;
LANGLE: '<' ;
RANGLE: '>' ;
RANGE: '..' ;
LE: '<=' ;
GE: '>=' ;
EXCL_EQ: '/=' ;
EQEQ: '=' ;
CONJ: 'and' ;
DISJ: 'or' ;
XOR: 'xor' ;
NOT: 'not'| 'түгел';


//KEYWORDS

FUNC: 'func' ;
VAR: 'var' ;
IF: 'if' ;
THEN: 'then' ;
IN: 'in' ;
ELSE: 'else' ;
WHEN: 'when' ;
FOR: 'for' ;
WHILE: 'while' ;
RETURN: 'return' ;
IS: 'is' ;
END: 'end' ;
PRINT: 'print' ;
LOOP: 'loop' ;


IntType: 'int' ;
RealType: 'real' ;
StringType: 'string' ;
BoolType: 'bool' ;
EmptyType: 'empty' ;
ArrayType: '[]' ;
TupleType: '{}' ;


ReadInt: 'readInt' ;
ReadReal: 'readReal' ;
ReadString: 'readString' ;

//

DOUBLE_QUOTE_OPEN: '"' -> pushMode(LineString) ;
SINGLE_QUOTE_OPEN: '\'' -> pushMode(LineString) ;

RealLiteral
    : DecDigitNoZero DecDigit* '.' DecDigit+
    ;

IntegerLiteral
    : ('0' | DecDigitNoZero DecDigit*)
    ;

fragment DecDigit
    : UNICODE_CLASS_ND
    ;

fragment DecDigitNoZero
    : UNICODE_CLASS_ND_NoZeros
    ;

fragment UNICODE_CLASS_ND_NoZeros
	: '\u0031'..'\u0039'
	| '\u0661'..'\u0669'
	| '\u06f1'..'\u06f9'
	| '\u07c1'..'\u07c9'
	| '\u0967'..'\u096f'
	| '\u09e7'..'\u09ef'
	| '\u0a67'..'\u0a6f'
	| '\u0ae7'..'\u0aef'
	| '\u0b67'..'\u0b6f'
	| '\u0be7'..'\u0bef'
	| '\u0c67'..'\u0c6f'
	| '\u0ce7'..'\u0cef'
	| '\u0d67'..'\u0d6f'
	| '\u0de7'..'\u0def'
	| '\u0e51'..'\u0e59'
	| '\u0ed1'..'\u0ed9'
	| '\u0f21'..'\u0f29'
	| '\u1041'..'\u1049'
	| '\u1091'..'\u1099'
	| '\u17e1'..'\u17e9'
	| '\u1811'..'\u1819'
	| '\u1947'..'\u194f'
	| '\u19d1'..'\u19d9'
	| '\u1a81'..'\u1a89'
	| '\u1a91'..'\u1a99'
	| '\u1b51'..'\u1b59'
	| '\u1bb1'..'\u1bb9'
	| '\u1c41'..'\u1c49'
	| '\u1c51'..'\u1c59'
	| '\ua621'..'\ua629'
	| '\ua8d1'..'\ua8d9'
	| '\ua901'..'\ua909'
	| '\ua9d1'..'\ua9d9'
	| '\ua9f1'..'\ua9f9'
	| '\uaa51'..'\uaa59'
	| '\uabf1'..'\uabf9'
	| '\uff11'..'\uff19'
	;

BooleanLiteral
    : 'true'
    | 'false'
    ;

Identifier
    : (Letter | '_') (Letter | '_' | DecDigit)*
    | '`' ~('`')+ '`'
    ;


fragment EscapeSeq
    : EscapedIdentifier
    ;


fragment EscapedIdentifier
    : '\\' ('t' | 'b' | 'r' | 'n' | '\'' | '"' | '\\' | '$')
    ;

fragment Letter
    : UNICODE_CLASS_LL
    | UNICODE_CLASS_LM
    | UNICODE_CLASS_LO
    | UNICODE_CLASS_LT
    | UNICODE_CLASS_LU
    | UNICODE_CLASS_NL
    ;


mode Inside ;

Inside_RPAREN: ')' -> popMode, type(RPAREN) ;
Inside_RSQUARE: ']' -> popMode, type(RSQUARE);

Inside_LPAREN: LPAREN -> pushMode(Inside), type(LPAREN) ;
Inside_LSQUARE: LSQUARE -> pushMode(Inside), type(LSQUARE) ;

Inside_LCURL: LCURL -> type(LCURL) ;
Inside_RCURL: RCURL -> type(RCURL) ;
Inside_DOT: DOT -> type(DOT) ;
Inside_COMMA: COMMA  -> type(COMMA) ;
Inside_MULT: MULT -> type(MULT) ;
Inside_DIV: DIV -> type(DIV) ;
Inside_ADD: ADD  -> type(ADD) ;
Inside_SUB: SUB  -> type(SUB) ;
Inside_XOR: XOR  -> type(XOR) ;
Inside_CONJ: CONJ  -> type(CONJ) ;
Inside_DISJ: DISJ  -> type(DISJ) ;
Inside_COLON: COLON  -> type(COLON) ;
Inside_SEMICOLON: SEMICOLON  -> type(SEMICOLON) ;
Inside_ASSIGNMENT: ASSIGNMENT  -> type(ASSIGNMENT) ;
Inside_DOUBLE_ARROW: DOUBLE_ARROW  -> type(DOUBLE_ARROW) ;
Inside_ELVIS: ELVIS  -> type(ELVIS) ;
Inside_LANGLE: LANGLE  -> type(LANGLE) ;
Inside_RANGLE: RANGLE  -> type(RANGLE) ;
Inside_LE: LE  -> type(LE) ;
Inside_GE: GE  -> type(GE) ;
Inside_EXCL_EQ: EXCL_EQ  -> type(EXCL_EQ) ;
Inside_EQEQ: EQEQ  -> type(EQEQ) ;
Inside_DOUBLE_QUOTE_OPEN: DOUBLE_QUOTE_OPEN -> pushMode(LineString), type(DOUBLE_QUOTE_OPEN) ;
Inside_SINGLE_QUOTE_OPEN: SINGLE_QUOTE_OPEN -> pushMode(LineString), type(SINGLE_QUOTE_OPEN) ;


Inside_VAR: VAR -> type(VAR) ;
Inside_RETURN: RETURN -> type(RETURN) ;
Inside_IF: IF -> type(IF) ;
Inside_ELSE: ELSE -> type(ELSE) ;
Inside_WHEN: WHEN -> type(WHEN) ;
Inside_FOR: FOR -> type(FOR) ;
Inside_WHILE: WHILE -> type(WHILE) ;


Inside_BooleanLiteral: BooleanLiteral -> type(BooleanLiteral) ;
Inside_IntegerLiteral: IntegerLiteral -> type(IntegerLiteral) ;
Inside_RealLiteral: RealLiteral -> type(RealLiteral) ;


Inside_Identifier: Identifier -> type(Identifier) ;
Inside_Comment: (LineComment | DelimitedComment) -> channel(HIDDEN) ;
Inside_WS: WS -> skip ;
Inside_NL: NL -> skip ;


mode LineString ;

DOUBLE_QUOTE_CLOSE
    : '"' -> popMode
    ;

SINGLE_QUOTE_CLOSE
    : '\'' -> popMode
    ;

LineStrText
    : ~('\\' | '\'' | '"')+ | '$'
    ;

LineStrEscapedChar
    : '\\' .
    ;


mode StringExpression ;

StrExpr_RCURL: RCURL -> popMode, type(RCURL) ;

StrExpr_LPAREN: LPAREN -> pushMode(Inside), type(LPAREN) ;
StrExpr_LSQUARE: LSQUARE -> pushMode(Inside), type(LSQUARE) ;


StrExpr_RPAREN: ')' -> type(RPAREN) ;
StrExpr_RSQUARE: ']' -> type(RSQUARE);
StrExpr_LCURL: LCURL -> pushMode(StringExpression), type(LCURL) ;
StrExpr_DOT: DOT -> type(DOT) ;
StrExpr_COMMA: COMMA  -> type(COMMA) ;
StrExpr_MULT: MULT -> type(MULT) ;
StrExpr_DIV: DIV -> type(DIV) ;
StrExpr_ADD: ADD  -> type(ADD) ;
StrExpr_SUB: SUB  -> type(SUB) ;
StrExpr_XOR: XOR  -> type(XOR) ;
StrExpr_CONJ: CONJ  -> type(CONJ) ;
StrExpr_DISJ: DISJ  -> type(DISJ) ;
StrExpr_COLON: COLON  -> type(COLON) ;
StrExpr_SEMICOLON: SEMICOLON  -> type(SEMICOLON) ;
StrExpr_ASSIGNMENT: ASSIGNMENT  -> type(ASSIGNMENT) ;
StrExpr_DOUBLE_ARROW: DOUBLE_ARROW  -> type(DOUBLE_ARROW) ;
StrExpr_ELVIS: ELVIS  -> type(ELVIS) ;
StrExpr_LANGLE: LANGLE  -> type(LANGLE) ;
StrExpr_RANGLE: RANGLE  -> type(RANGLE) ;
StrExpr_LE: LE  -> type(LE) ;
StrExpr_GE: GE  -> type(GE) ;
StrExpr_EXCL_EQ: EXCL_EQ  -> type(EXCL_EQ) ;
StrExpr_IS: IS -> type(IN) ;
StrExpr_EQEQ: EQEQ  -> type(EQEQ) ;
StrExpr_DOUBLE_QUOTE_OPEN: DOUBLE_QUOTE_OPEN -> pushMode(LineString), type(DOUBLE_QUOTE_OPEN) ;
StrExpr_SINGLE_QUOTE_OPEN: SINGLE_QUOTE_OPEN -> pushMode(LineString), type(SINGLE_QUOTE_OPEN) ;


StrExpr_BooleanLiteral: BooleanLiteral -> type(BooleanLiteral) ;
StrExpr_IntegerLiteral: IntegerLiteral -> type(IntegerLiteral) ;
StrExpr_RealLiteral: RealLiteral -> type(RealLiteral) ;

StrExpr_Identifier: Identifier -> type(Identifier) ;
StrExpr_Comment: (LineComment | DelimitedComment) -> channel(HIDDEN) ;
StrExpr_WS: WS -> skip ;
StrExpr_NL: NL -> skip ;