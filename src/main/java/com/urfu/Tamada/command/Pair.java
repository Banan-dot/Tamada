package com.urfu.Tamada.command;


public class Pair<X, Y> {
    private final X first;
    private final Y second;

    public Pair(X first, Y second) {
        assert first != null;
        assert second != null;

        this.first = first;
        this.second = second;
    }

    public static <X, Y> Pair<X, Y> create(X a, Y b) {
        return new Pair<>(a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair pairObject)) return false;
        return this.first.equals(pairObject.getFirst()) &&
                this.second.equals(pairObject.getSecond());
    }

    @Override
    public int hashCode() {
        return first.hashCode() ^ second.hashCode();
    }

    public X getFirst() {
        return first;
    }

    public Y getSecond() {
        return second;
    }
}