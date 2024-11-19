package v;

import java.lang.reflect.Constructor;

//add jvm option
//--add-opens java.base/java.lang=ALL-UNNAMED
public class AVoid {
    public static void main(String[] args) throws Exception {
        var constructor = Void.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        var voidInstance = constructor.newInstance();

        System.out.println("Void instance: " + voidInstance);
        System.out.println("Void class: " + voidInstance.getClass());
    }
}
