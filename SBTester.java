public class SBTester{
    
    public static void main(String[] args){
        
        SymbolBalance sb = new SymbolBalance();
        sb.setFile("TestFiles/Test6.java");
        System.out.println(sb.checkFile());
        
    }
    
}