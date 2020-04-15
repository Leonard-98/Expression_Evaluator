import java.util.*;
import java.lang.*;
public class Expression_Evaluator{
    
    public static String findSign(char c){
        String newstring="";
        switch(c){
            case '+':
                newstring=" + ";
                break;
            case '-':
                newstring=" - ";
                break;
            case '/':
                newstring=" / ";
                break;   
            case '*':
                newstring=" * ";
                break; 
            case '(':
                newstring="( ";
                break;
            case ')':
                newstring=" )";
                break;
            default:
                newstring=String.valueOf(c);
        }
        return newstring;
    }
    
    public static String replaceSign(String string){
        String fin="";
        for(int i=0;i<string.length();i++){
            fin+=findSign(string.charAt(i));
        }
        return fin;
    }
    
    public static int order(String c){
        int a=0;
        if(c.equals("+")==true || c.equals("-")==true) a = 2;
        if(c.equals("/")==true || c.equals("*")==true) a = 1;
        return a;
    }
    
    public static int isSymbol(String c){
        int a=0;
        if(c.equals("+")==true||c.equals("-")==true||c.equals("*")==true||c.equals("/")==true||c.equals("(")==true||c.equals(")")==true) a=1;
        else a=0;
        return a;
    }
    
    public static double calcul(String left1,String right1,String op){
        double left=Double.parseDouble(left1);
        double right=Double.parseDouble(right1);
        double result=0.0;
        switch(op){
            case "+":
                result=left+right;
                break;
            case "-":
                result=left-right;
                break;
            case "*":
                result=left*right;
                break;
            case "/":
                result=left/right;
                break;
        }
        return result;
    }
    public static void main(String [] args ){
       Scanner s = new Scanner(System.in);
       String ecuatie  = s.next();
       String ecuatie2 = replaceSign(ecuatie);
       String ec[]=ecuatie2.split(" ");

        ArrayList<String> fp = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        
        for(int i=0;i<ec.length;i++){
           if(isSymbol(ec[i])==1) stack.push(ec[i]);
           else fp.add(ec[i]);
           
           if(stack.size()>=2) {
               String peek = stack.peek();
               if(order(peek)==1){
                   stack.pop();
                   if(order(stack.peek())==1 && peek.equals("(")==false){
                       fp.add(stack.peek());
                       stack.pop();
                       stack.push(peek);
                   }else stack.push(peek);
               }else if(order(peek)==2){
                        stack.pop();
                        if((order(stack.peek())==1||order(stack.peek())==2) && peek.equals("(")==false){
                            fp.add(stack.peek());
                            stack.pop();
                            stack.push(peek);
                         }else stack.push(peek);
                }else if(peek.equals(")")==true){
                    stack.pop();
                    while(stack.peek().equals("(")==false){
                        fp.add(stack.peek());
                        stack.pop();
                    }
                    stack.pop();
                }
           }
       }
       for(String i:stack){
           if(stack.peek().equals("(")==true || stack.peek().equals(")")==true) stack.pop();
           else{
               fp.add(stack.peek());
               stack.pop();
           }
       }
       fp.add(stack.peek());
       stack.pop();
       
       for(String i:fp){
           if(isSymbol(i)==0) stack.push(i);
           else{
               String peek1=stack.peek();
               stack.pop();
               String peek2=stack.peek();
               stack.pop();
               double res=calcul(peek2,peek1,i);
               String ress =String.valueOf(res);
               stack.push(ress);
           }
       }
        //System.out.println(fp);
        System.out.println(stack.peek());
    }
    
}
