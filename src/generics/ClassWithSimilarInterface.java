package generics;

public class ClassWithSimilarInterface implements InterfaceA<String>{
    @Override
    public void setData(String data) {

    }

    //, InterfaceB<Integer>
//    @Override
//    public void setData(Integer data) {
//
//    }
    // 'setData(T)' in 'generics.InterfaceA' clashes with 'setData(T)' in 'generics.InterfaceB';
    // both methods have same erasure, yet neither overrides the other
}
