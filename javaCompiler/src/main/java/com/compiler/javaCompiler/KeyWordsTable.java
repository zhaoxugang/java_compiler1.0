package com.compiler.javaCompiler;

import java.util.HashMap;

public class KeyWordsTable {
    public final static HashMap<String, TokenType> table = new HashMap(64);

    static {
        table.put("abstract", TokenType.ABSTRACT);
        table.put("assert", TokenType.ASSERT);
        table.put("boolean", TokenType.BOOLEAN);
        table.put("break", TokenType.BREAK);
        table.put("byte", TokenType.BYTE);
        table.put("case", TokenType.CASE);
        table.put("catch", TokenType.CATCH);
        table.put("char", TokenType.CHAR);
        table.put("class", TokenType.CLASS);
        table.put("const", TokenType.CONST);
        table.put("continue", TokenType.CONTINUE);
        table.put("default", TokenType.DEFAULT);
        table.put("do", TokenType.DO);
        table.put("double", TokenType.DOUBLE);
        table.put("else", TokenType.ELSE);
        table.put("enum", TokenType.ENUM);
        table.put("extends", TokenType.EXTENDS);
        table.put("final", TokenType.FINAL);
        table.put("finally", TokenType.FINALLY);
        table.put("float", TokenType.FLOAT);
        table.put("for", TokenType.FOR);
        table.put("goto", TokenType.GOTO);
        table.put("if", TokenType.IF);
        table.put("implements", TokenType.IMPLEMENTS);
        table.put("import", TokenType.IMPORT);
        table.put("instanceof", TokenType.INSTANTOF);
        table.put("int", TokenType.INT);
        table.put("interface", TokenType.INTERFACE);
        table.put("long", TokenType.LONG);
        table.put("native", TokenType.NATIVE);
        table.put("new", TokenType.NEW);
        table.put("package", TokenType.PACKAGE);
        table.put("private", TokenType.PRIVATE);
        table.put("protected", TokenType.PROTECTED);
        table.put("public", TokenType.PUBLIC);
        table.put("return", TokenType.RETURN);
        table.put("short", TokenType.SHORT);
        table.put("static", TokenType.STATIC);
        table.put("strictfp", TokenType.STRICFP);
    }

    public static TokenType getTokenType(String desc){
        return table.get(desc);
    }
}
