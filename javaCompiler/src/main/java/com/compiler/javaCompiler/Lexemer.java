package com.compiler.javaCompiler;

import java.io.IOException;
import java.io.InputStreamReader;

import static com.compiler.javaCompiler.TokenType.LPAREN;

public class Lexemer {

    private InputStreamReader reader;
    private int limit = 0;
    private int pos = 0;
    private char[][] buf = new char[2][1024];
    private int selectedBuf = -1;
    private int preLimit = -1;
    private boolean nextBufIsFilled = false;
    private int nextLimit = -1;
    private boolean hasMore = true;
    private static final char EOF = (char)-1;

    public Lexemer(InputStreamReader reader) {
        this.reader = reader;
    }

    public Token nextToken(){
        Token token = null;
        while(true) {
            char c = peek();
            if (c == EOF) {
                hasMore = false;
                return null;
            }
            switch (c) {
                case '<':
                    c = next();
                    switch (c) {
                        case '=':
                            token = new Token(TokenType.LQ, null);
                            break;
                        default:
                            unread();
                            token = new Token(TokenType.LE, null);
                            break;
                    }
                    break;
                case '=':
                    c = next();
                    switch (c) {
                        case '=':
                            token = new Token(TokenType.EQ, null);
                            break;
                        default:
                            unread();
                            token = new Token(TokenType.ASSIGN, null);
                            break;
                    }
                    break;
                case '>':
                    c = next();
                    switch (c) {
                        case '=':
                            token = new Token(TokenType.GQ, null);
                            break;
                        default:
                            unread();
                            token = new Token(TokenType.GE, null);
                            break;
                    }
                    break;
                case '(':
                    token = new Token(TokenType.LPAREN, null);
                    break;
                case ')':
                    token = new Token(TokenType.RPAREN, null);
                    break;
                case '{':
                    token = new Token(TokenType.BLPAREN, null);
                    break;
                case '}':
                    token = new Token(TokenType.BRPAREN, null);
                    break;
                case '[':
                    token = new Token(TokenType.LBRACKET, null);
                    break;
                case ']':
                    token = new Token(TokenType.RBRACKET, null);
                    break;
                case ';':
                    token = new Token(TokenType.SEMICOLON, null);
                    break;
                case ',':
                    token = new Token(TokenType.COMMA, null);
                    break;
                case ':':
                    token = new Token(TokenType.COLON, null);
                    break;
                case '\r':
                case '\n':
                case 32:
                    break;
                default:
                    if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                        token = sequenceToken();
                    } else if (c >= 48 && c <= 57) {
                        token = numericToken();
                    } else if (c == '"') {
                        token = StringToken();
                    }
            }
            next();
            if(token != null){
                break;
            }
        }
        return token;
    }

    private Token StringToken() {//字符串解析
        StringBuilder sequence = new StringBuilder();
        boolean isEscape = false;
        char c = next();//字符串不包含双引号
        sequence.append(c);
        while(true){
            c = next();
            if(c == '\\'){
                isEscape = true;
                continue;
            }
            if(isEscape){//转移字符
                sequence.append(c);
                isEscape = false;
            }else{
                if(c == '\"'){
                    break;
                }
            }
            if(c == EOF){
                System.out.println("parser error: expect " + sequence);
                System.exit(-1);
            }
            sequence.append(c);
        }
        return new Token(TokenType.STRING, sequence.toString());
    }

    private Token numericToken() {//数字解析
        StringBuilder sequence = new StringBuilder();
        char c = peek();
        sequence.append(c);
        while(true){
            c = next();
            if (c == '\r' || c == '\n' || c == 32 || c == EOF) {
                break;
            }
            if(c < 48 || c > 57){//解析错误
                unread();
                break;
            }
            sequence.append(c);
        }
        return new Token(TokenType.NUMERIC, sequence.toString());
    }

    private Token sequenceToken() {//开头必须是英文文字母
        StringBuilder sequence = new StringBuilder();
        char c = peek();
        sequence.append(c);
        while(true) {
            c = next();
            if (c == '\r' || c == '\n' || c == 32 || c == EOF) {
                break;
            }
            if((c < 48 && c != 36) || c > 57 && c < 65 || c > 90 && c < 97 || c > 122){
                unread();
                break;
            }
            sequence.append(c);
        }
        TokenType tokenType = KeyWordsTable.getTokenType(sequence.toString());
        if(tokenType != null){
            return new Token(tokenType, null);
        }else{
            return new Token(TokenType.ID, sequence.toString());
        }
    }

    private void unread(){
        if(pos == 0){
            if(preLimit == -1){
                System.out.println("数组越界：" + preLimit);
            }else{
                nextLimit = limit;
                nextBufIsFilled = true;
                limit = preLimit;
                pos = limit - 1;
                selectedBuf ^=1;
            }
        }else{
            pos --;
        }
    }

    private char peek(){
        if(limit <= pos){
            pos = 0;
            fillBuf();
        }
        return buf[selectedBuf][pos];
    }

    private char next(){
        pos++;
        if(limit <= pos){
            pos = 0;
            fillBuf();
        }
        return buf[selectedBuf][pos];
    }

    private void fillBuf(){//填充缓冲区
        try {
            if(selectedBuf == -1) {
                selectedBuf = 0;
            }else{
                selectedBuf ^= 1;
            }
            if(!nextBufIsFilled) {
                limit = reader.read(buf[selectedBuf]);
            }else{
                preLimit = -1;
                nextBufIsFilled = false;
                limit = nextLimit;
                nextLimit = -1;
            }
            if(limit == -1){//已经到了文件末尾
                buf[selectedBuf][pos] = EOF;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
