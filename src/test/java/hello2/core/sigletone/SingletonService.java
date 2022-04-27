package hello2.core.sigletone;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){ // 외부에서 생성을 막기 위해 생성자를 private으로
        System.out.println("싱글톤 객체 로직 호출");
   }
}
