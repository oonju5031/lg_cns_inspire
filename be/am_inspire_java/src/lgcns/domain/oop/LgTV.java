package lgcns.domain.oop;

import lgcns.domain.oop.util.Tv;

public class LgTV implements Tv {

    private static final LgTV instance = new LgTV();
    private LgTV() {

    }
    public static LgTV getInstance() {
        return instance;
    }

    @Override
    public void powerOn() {
        System.out.println("LgTV powerOn()");
    }

    @Override
    public void powerOff() {
        System.out.println("LgTV powerOff()");
    }
    
}
