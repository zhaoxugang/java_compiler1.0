grammar Assign;
stat : ID '=' expr ';' ;
expr : INT ;
ID : [a-z]+ ;
INT : [0-9]+ ;