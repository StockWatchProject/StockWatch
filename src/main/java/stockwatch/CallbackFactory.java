package stockwatch;

public interface CallbackFactory <T, Y> {
    public abstract Callback<T, Y> get(String tag);
}