package generics;

public class ClassWithSimilarInterface implements InterfaceA<String>, InterfaceB<Integer>{
    @Override
    public void setData(String data) {

    }

    @Override
    public void setData(Integer data) {

    }
    // 'setData(T)' in 'generics.InterfaceA' clashes with 'setData(T)' in 'generics.InterfaceB';
    // both methods have same erasure, yet neither overrides the other
}
