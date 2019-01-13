package com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SelectSetGeter {

    private static final String EPSILON = "epsilon";
    private static final String RULEEND= "re";

    public static void main(String args[]){
        String[] N = {"E",  "E1", "T", "T1", "F"};
        String[] T = {"(", ")", "id", "+", "*", EPSILON};
        HashMap<String, String[][]> rules = new HashMap<>();
        rules.put("E", new String[][]{{"T", "E1"}});
        rules.put("E1", new String[][]{{"+", "T", "E1"},{EPSILON}});
        rules.put("T", new String[][]{{"F", "T1"}});
        rules.put("T1", new String[][]{{"*", "F", "T1"},{EPSILON}});
        rules.put("F", new String[][]{{"(", "E", ")"},{"id"}});
        Cfg cfg = new Cfg(N, T,rules);
        HashMap<String, Set<String>> firsetSetMap = getFirstSet(cfg);
        HashMap<String, Set<String>> followSetMap = getFollowSet(cfg, firsetSetMap);
        System.out.print("{");
        for(String first : followSetMap.get("F")){
            System.out.print("\"" + first + "\" ");
        }
        System.out.println("}");
    }

    public static HashMap<String, Set<String>> getFirstSet(Cfg cfg){//求first集
        HashMap<String, Set<String>> firstSetMap = new HashMap<>();
        String[] N = cfg.getN();
        for(String n : N){
            firstSetMap.put(n, new HashSet<String>());
        }
        boolean setIsChanging = true;
        int preSize = 0;
        while(setIsChanging){
            HashMap<String, String[][]> rules = cfg.getRules();
            for(String key : rules.keySet()){
                Set<String> firstSet0 = new HashSet<>();
                String[][] value = rules.get(key);
                for(String[] rule : value){
                    if(isTerminator(cfg, rule[0])){
                        firstSet0.add(rule[0]);
                    }else{
                        //System.out.println(rule[0]);
                        firstSet0.addAll(firstSetMap.get(rule[0]));
                    }
                }
                firstSetMap.get(key).addAll(firstSet0);
            }
            int size = getSetMapSize(firstSetMap);
            if(size != preSize){
                preSize = size;
                setIsChanging = true;
            }else{
                setIsChanging = false;
            }
        }
        return firstSetMap;
    }

    public static HashMap<String, Set<String>> getFollowSet(Cfg cfg, HashMap<String, Set<String>> firstSetMap){//求follow集
        HashMap<String, Set<String>> followSetMap = new HashMap<>();
        String[] N = cfg.getN();
        String[] T = cfg.getT();
        HashMap<String, String[][]> rules = cfg.getRules();
        boolean setIsChanging = true;
        int presize = 0;
        for(String n : N){
            followSetMap.put(n, new HashSet<>());
            followSetMap.get(n).add(RULEEND);
        }
        while(setIsChanging) {
            for (String key : rules.keySet()) {
                String[][] value = rules.get(key);
                for (int i = 0; i < value.length; i++) {//取出每一条规则
                    for (int j = value[i].length; j >= 1; j--) {
                        Set<String> temp = new HashSet<>(followSetMap.get(key));
                        if(j == value[i].length){
                            if(!isTerminator(cfg, value[i][j-1])) {
                                followSetMap.get(value[i][j-1]).addAll(temp);
                            }
                            continue;
                        }
                        if (isTerminator(cfg, value[i][j])) {
                            temp.clear();
                            temp.add(value[i][j]);
                        } else {
                            Set<String> firstSet = firstSetMap.get(value[i][j]);
                            if (isContainsEpsilon(firstSet)) {
                                temp.addAll(firstSet);
                            } else {
                                temp.clear();
                                temp.addAll(firstSet);
                            }
                        }
                        if(!isTerminator(cfg, value[i][j-1])) {
                            followSetMap.get(value[i][j - 1]).addAll(temp);
                        }
                    }
                }
            }
            int size = getSetMapSize(followSetMap);
            if(presize == size){
                break;
            }else{
                presize = size;
            }
        }
        return followSetMap;
    }

    private static boolean isContainsEpsilon(Set<String> set){
        if(set.contains(EPSILON)){
            return true;
        }
        return false;
    }

    private static int getSetMapSize(HashMap<String, Set<String>> firstSetMap) {
        int size = 0;
        for(String key : firstSetMap.keySet()){
            size += firstSetMap.get(key).size();
        }
        return size;
    }

    private static boolean isTerminator(Cfg cfg, String s){//判断是否为终结符
        String[] T = cfg.getT();
        for(int i = 0;i < T.length; i++){
            if(T[i].equals(s)){
                return true;
            }
        }
        return false;
    }
}
class Cfg{
    private String[] N;
    private String[] T;
    private HashMap<String, String[][]> rules;

    public Cfg() {
    }

    public Cfg(String[] n, String[] t, HashMap<String, String[][]> rules) {
        N = n;
        T = t;
        this.rules = rules;
    }

    public String[] getN() {
        return N;
    }

    public void setN(String[] n) {
        N = n;
    }

    public String[] getT() {
        return T;
    }

    public void setT(String[] t) {
        T = t;
    }

    public HashMap<String, String[][]> getRules() {
        return rules;
    }

    public void setRules(HashMap<String, String[][]> rules) {
        this.rules = rules;
    }
}
