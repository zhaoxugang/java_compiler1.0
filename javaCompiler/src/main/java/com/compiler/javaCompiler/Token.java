package com.compiler.javaCompiler;

public class Token {
    private TokenType tokenType;
    private String  lexeme;

    public Token(TokenType tokenType, String lexeme) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}
enum TokenType{
    IF,//if
    LPAREN,//(
    ID,//vars
    LE,//小于
    LQ,//小于等于
    EQ,//等于
    GE,//大于
    GQ,//大于等于
    ASSIGN,//赋值
    BLANK,//空白符
    ADD,//加
    SUB,//减
    MUL,//乘
    DIV,//除
    ABSTRACT,
    ASSERT,
    BOOLEAN,
    BREAK,
    BYTE,
    CASE,
    CATCH,
    CHAR,
    CLASS,
    CONST,
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EXTENDS,
    FINAL,
    FINALLY,
    FLOAT,
    FOR,
    GOTO,
    IMPLEMENTS,
    IMPORT,
    INSTANTOF,
    INT,
    INTERFACE,
    LONG,
    NATIVE,
    NEW,
    PACKAGE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    RETURN,
    SHORT,
    STATIC,
    NUMERIC,
    STRING,
    RPAREN,
    BLPAREN,
    BRPAREN,
    SEMICOLON,
    COMMA,
    COLON,
    LBRACKET,
    RBRACKET,
    STRICFP
}
