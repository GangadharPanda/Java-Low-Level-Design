package generics;

public class ClassWithSameInstanceOfAWithDiffDataType implements InterfaceA<String>{
    //,InterfaceA<Integer>

    @Override
    public void setData(String data) {

    }
    // The Collision: After Type Erasure, both interfaces demand the exact same method signature
    // (void setData(Object)).
    // The JVM can not create two setData method with the same Object.
}
