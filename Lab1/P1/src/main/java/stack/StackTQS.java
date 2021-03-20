package stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class StackTQS<T> {

    private int bound = 10;
    private LinkedList<T> array = new LinkedList<T>();

    public void push(T elem){

        if(array.size() + 1 > bound){
            throw new IllegalStateException();
        }

        array.push(elem);

    }

    public T pop(){

        if(array.size() == 0){
            throw new NoSuchElementException();
        }

        return array.pop();
    }

    public T peek(){

        if(array.size() == 0){
            throw new NoSuchElementException();
        }

        return array.peek();
    }

    public int size(){
        return array.size();
    }

    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getBound(){
        return bound;
    }

    public void setBound(int bound) {
         this.bound = bound;
    }
}
