package com.compiler.antlr.LabeledExpr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Calc {
    public static void main(String args[]) throws Exception{
        File inputFile = null;
        if (args.length > 0){
            inputFile = new File(args[0]);
        }else{
            inputFile = new File("./test.txt");
        }
        InputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);

        /*ParseTree tree = parser.stat();
        EvalVisiter eval = new EvalVisiter();
        eval.visit(tree);
        System.out.println(tree.toStringTree(parser));*/
    }
}
