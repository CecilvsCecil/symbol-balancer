public class MyStack<T> implements MyStackInterface<T>{
    
    private T[] a;
    private int currentSize;
    private static final int INITIAL_CAPACITY = 10;
    
    public MyStack(){
        
        resize(INITIAL_CAPACITY);
        currentSize = 0;
        
    }
    
    public void push(T x){
        
        if(currentSize == a.length){
            
            resize((currentSize * 2) + 1);
            
        }
        
        a[currentSize++] = x;
        
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int newCap){
        
        if(newCap < currentSize){
            
            return;
            
        }
        
        T[] oldArray = a;
        a = (T[]) new Object[newCap];
        
        for(int i = 0; i < currentSize; i++){
            
            a[i] = oldArray[i];
            
        }
        
    }
    
    public T pop(){
        
        if(currentSize == 0){
            
            return null;
            
        }
        
        return a[--currentSize];
        
    }
    
    public T peek(){
        
        if(currentSize == 0){
            
            return null;
            
        }
        
        return a[currentSize - 1];
        
    }
    
    public boolean isEmpty(){
        
        return currentSize == 0;
        
    }
    
    public int size(){
        
        return currentSize;
        
    }
    
}