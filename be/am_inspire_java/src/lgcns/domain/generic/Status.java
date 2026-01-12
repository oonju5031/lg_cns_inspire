package lgcns.domain.generic;

public class Status<T> {

    private T code;

    public void setCode(T code) {
        this.code = code;
    }
    public T getCode() {
        return this.code;
    }
    
}
