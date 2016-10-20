package com.ssj.gameUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DFA {    
	
	private static List<String> arrt = new ArrayList<String>();  
    private static Node rootNode = new Node("R"); 
        
    private static ArrayList<CustomString> searchWord(String content) {  
    	int a = 0;    
        char[] chars = content.toCharArray();    
        Node node = rootNode;
        StringBuffer word = new StringBuffer();
        ArrayList<CustomString> words = new ArrayList<CustomString>();  
        while(a<chars.length) {    
            node = findNode(node,String.valueOf(chars[a]));    
            if(node == null) {    
                node = rootNode;    
                a = a - word.length();    
                word.setLength(0);    
            } else if(node.flag == 1) {    
                word.append(chars[a]);  
                words.add(new CustomString(word.toString()));    
                a = a - word.length() + 1;    
                word.setLength(0);    
                node = rootNode;    
            } else {    
                word.append(chars[a]);    
            }             
            a++;    
        }   
        node = null;
        word = null;
        chars = null;
        return words;
    }
    
    
    public static String getDFAStr(String content){
    	ArrayList<CustomString> list = searchWord(content);
    	Collections.sort(list,new CustomString() );
    	for(int i = 0;i<list.size();i++){
    		String src = list.get(i).s;
    		StringBuffer sb = new StringBuffer();
    		for(int j = 0;j<src.length();j++){
    			sb.append("*");
    		}
    		content =  content.replaceAll(src, sb.toString());
    		sb = null;
    	}
    	list.clear();
    	list = null;
    	return content;
    }
    
    public static boolean hasKeyWords(String content){
    	int a = 0;    
        char[] chars = content.toCharArray();    
        Node node = rootNode;
        StringBuffer word = new StringBuffer();
        while(a<chars.length) {    
            node = findNode(node,String.valueOf(chars[a]));    
            if(node == null) {    
                node = rootNode;    
                a = a - word.length();    
                word.setLength(0);    
            } else if(node.flag == 1) {    
            	return true;
            } else {
                word.append(chars[a]);    
            }             
            a++;    
        }   
        node = null;
        word = null;
        return false;
    }
    
    public static void initial(InputStream in){
    	try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.trim().equals(""))
					continue;
				arrt.add(line.trim());
			}

			br.close();
			createTree();
			arrt.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
        
    private static void createTree() {    
        for(String str : arrt) {    
            char[] chars = str.toCharArray();    
            if(chars.length > 0)    
                insertNode(rootNode, chars, 0);    
        }    
    }    
        
    private static void insertNode(Node node, char[] cs, int index) {    
        Node n = findNode(node, String.valueOf(cs[index]));    
        if(n == null) {    
            n = new Node(String.valueOf(cs[index]));    
            node.nodes.put(String.valueOf(cs[index]),n);    
        }
        
        if(index == (cs.length-1))    
            n.flag = 1;    
                
        index++;    
        if(index<cs.length)    
            insertNode(n, cs, index);    
    }    
        
    private static Node findNode(Node node, String c) {    
        HashMap<String,Node> nodes = node.nodes;         
        return nodes.get(c);    
    }    
        
    public static void main(String[] args) {
//    	initial(); 
//    	long now = System.currentTimeMillis();
//    	for(int i = 0;i<100000000;i++){
//    		DFA.hasKeyWords("毛泽东");
//    	}
//    	System.err.println(System.currentTimeMillis() - now);
    }
    
    private static class CustomString implements Comparator<CustomString>{    	
    	public String s;
    	public CustomString(){
    		
    	}
    	
    	public CustomString(String s){
    		this.s = s;
    	}
    	
		@Override
		public int compare(CustomString o1, CustomString o2) {			
			return o2.s.length() - o1.s.length();
		}
    }
        
    private static class Node {    
        @SuppressWarnings("unused")
		public String c;    
        public int flag; //1：表示终结，0：延续    
        public HashMap<String,Node> nodes = new HashMap<String,Node>();    
            
        public Node(String c) {    
            this.c = c;    
            this.flag = 0;    
        }    
    }    
}  