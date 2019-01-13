grammar Expr ;
import CommonLexerRulers ;
prog : stat+ ;
stat : expr NEWLINW
     | ID '=' expr NEWLINE
     | NEWLINE
     ;
expr : expr ( '*'|'/') expr
     | expr ('+'|'-') expr
     | INT
     | ID
     | '(' expr ')'
     ;
