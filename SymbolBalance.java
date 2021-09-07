import java.util.Scanner;
import java.io.*;
public class SymbolBalance implements SymbolBalanceInterface{
    
    private Scanner input;
    private MyStack<Character> stack;
    private boolean ignore;
    
    public SymbolBalance(){
        
        stack = new MyStack<>();
        ignore = false;
        
    }
    
    public void setFile(String filename){
        
        try{
            
            input = new Scanner(new File(filename));
            stack = new MyStack<>();
            
        }
        
        catch(FileNotFoundException E){
            
            System.out.println("File: " + filename + " cannot be found. Try again!");
            
        }
        
    }
    
    public BalanceError checkFile(){
        
        String testLine;
        Character poppedSymbol;
        int lineNumber = 0;
        
        while(input.hasNextLine()){
            
            testLine = input.nextLine();
            lineNumber++;
            
            for(int i = 0; i < testLine.length(); i++){
                
                if(testLine.charAt(i)=='"'){
                    
                    if(stack.peek() != null && stack.peek() == '"'){
                        
                        stack.pop();
                        ignore = false;
                        
                    }
                    
                    else if(!ignore){
                        
                        stack.push('"');
                        ignore = true;
                        
                    }
                      
                }
                
                else if(!ignore && testLine.charAt(i)=='/' && (i + 1) < testLine.length() && testLine.charAt(i + 1) == '*'){
                    
                    stack.push('*');
                    ignore = true;
                    i++;
                    
                }
                
                else if(testLine.charAt(i)=='*' && (i + 1) < testLine.length() && testLine.charAt(i + 1) == '/'){
                    
                    if(stack.peek() != null && stack.peek() == '*'){
                        
                        stack.pop();
                        ignore = false;
                        i++;
                        
                    }
                    
                    else if(stack.isEmpty()){
                        
                        return new EmptyStackError(lineNumber);
                        
                    }
                    
                    else if(!ignore && stack.peek() != null && stack.peek() != '*'){
                        
                        return new MismatchError(lineNumber, '*', stack.pop());
                        
                    }
                    
                }
                
                if(!ignore){
                    
                    if(testLine.charAt(i)=='{' || testLine.charAt(i)=='(' || testLine.charAt(i)=='['){
                    
                        stack.push(testLine.charAt(i));
                    
                    }
                
                    else if(testLine.charAt(i)=='}'){
                    
                        poppedSymbol = stack.pop();
                    
                        if(poppedSymbol == null){
                        
                            return new EmptyStackError(lineNumber);
                        
                        }
                    
                        else if(poppedSymbol != '{'){
                        
                            return new MismatchError(lineNumber, '}', poppedSymbol);
                        
                        }
                    
                    }
                
                    else if(testLine.charAt(i)==']'){
                    
                        poppedSymbol = stack.pop();
                    
                        if(poppedSymbol == null){
                        
                            return new EmptyStackError(lineNumber);
                        
                        }
                    
                        else if(poppedSymbol != '['){
                        
                            return new MismatchError(lineNumber, ']', poppedSymbol);
                        
                        }
                    
                    }
                
                    else if(testLine.charAt(i)==')'){
                    
                        poppedSymbol = stack.pop();
                    
                        if(poppedSymbol == null){
                        
                            return new EmptyStackError(lineNumber);
                        
                        }
                    
                        if(poppedSymbol != '('){
                        
                            return new MismatchError(lineNumber, ')', poppedSymbol);
                        
                        }
                    
                    }
                
                }
                
            }
            
        }
        
        if(!stack.isEmpty()){
            
            return new NonEmptyStackError(stack.peek(), stack.size());
            
        }
        
        return null;
        
    }
    
}