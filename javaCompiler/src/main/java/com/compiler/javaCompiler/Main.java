package com.compiler.javaCompiler;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        File file = new File("./compare.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        Lexemer lexemer = new Lexemer(reader);
        Token token = null;
        ParserRuleContext prc;
        Parser parser;
        while(true){
            token = lexemer.nextToken();
            if(token == null){
                break;
            }
            System.out.println(token.getTokenType());
        }
    }
}
