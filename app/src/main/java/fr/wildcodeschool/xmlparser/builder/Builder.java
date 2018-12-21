package fr.wildcodeschool.xmlparser.builder;

public class Builder<T> {
    protected T object;

    public Builder(T object) {
        this.object = object;
    }

    public T build() {
        return this.object;
    }
}
