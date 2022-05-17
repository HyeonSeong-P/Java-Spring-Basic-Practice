package hello2.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** lombok 을 사용하면 아래와 같이 어노테이션만으로 getter, setter, 생성자, ToString 등을 어노테이션만으로 설정 가능하다.
 * 실무에서 굉장히 많이 쓰임 **/
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("aaa");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
