package pl.put.poznan.transformer.base;

public class myInt {
    private int value;
    public myInt(int a){
        this.value=a;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increment(){
        setValue(getValue()+1);
    }
    public void reset(){
        setValue(3);
    }

}
