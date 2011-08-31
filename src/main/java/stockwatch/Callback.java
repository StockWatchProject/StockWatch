package stockwatch;

public interface Callback <T, Y> {
    public abstract void call(T type, Y arg);
}
