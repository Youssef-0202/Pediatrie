package ma.zs.gestion_service_pediatrie.zynerator.util;

public class UniqueID {
    private static long current = System.currentTimeMillis();

    private UniqueID() {
    }

    static public synchronized long get() {
        return current++;
    }
}