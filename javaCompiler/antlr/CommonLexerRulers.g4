lexer grammar CommonLexerRulers ;
ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE : '\r' ? '\n' ;
WS : [\t]+ -> skip ;