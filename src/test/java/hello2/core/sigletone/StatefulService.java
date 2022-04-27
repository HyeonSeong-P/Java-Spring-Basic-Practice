package hello2.core.sigletone;

public class StatefulService {
    /**
     * 무상태 설계 이전, 싱글톤으로 세팅할 건데 공유되는 변수가 있는 경우
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
     **/

    /** 무상태 설계 버전 */
    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
