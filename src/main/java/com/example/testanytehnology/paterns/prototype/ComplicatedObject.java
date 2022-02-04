package com.example.testanytehnology.paterns.prototype;

class ComplicatedObject implements Copyable {
    private Type type;
    public enum Type {
        ONE, TWO
    }
    public ComplicatedObject copy() {
        return new ComplicatedObject();
    }
    void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ComplicatedObject{" +
                "type=" + type +
                '}';
    }
}
