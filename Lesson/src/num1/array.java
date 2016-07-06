package num1;

/**
 * Created by Tuyu on 2016/4/20 19:16 .
 */
//单例模式
public class array {
    private static array ourInstance = new array();

    public static array getInstance() {
        return ourInstance;
    }

    private array() {
    }
}
