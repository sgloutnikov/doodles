package singleton;


/**
 * Thread save singleton initialization.
 * This method is called Initialization-on-demand holder idiom
 */
public class Singleton {

    private Singleton() {
    }

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

}
