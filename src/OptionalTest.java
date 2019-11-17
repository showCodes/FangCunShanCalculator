import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    private String name;

    private int age;

    private List<String> strs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getStrs() {
        return strs;
    }

    public void setStrs(List<String> strs) {
        this.strs = strs;
    }

    @Override
    public String toString() {
        return "OptionalTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws RPCException {

        OptionalTest optionalTest = new OptionalTest();
        List<String> strs = new ArrayList<>(3);
        strs.add("abc");
        strs.add("bcd");
        strs.add("abb");
        optionalTest.setStrs(strs);
//        optionalTest.setName("111");
        Optional.ofNullable(optionalTest).map(OptionalTest::getStrs)
                .orElseThrow(() -> new RPCException("参数optionalTest不能为空")).stream().map(s -> {if (s.contains("a")) return s;
            return null;
        }).forEach(System.out::println);
    }
}
