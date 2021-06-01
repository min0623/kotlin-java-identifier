class Outer01 {
    int num;

    // inner class
    private class Inner01 {
        public void print() {
            System.out.println("This is an inner class");
            System.out.println(num);
        }
    }

    // Accessing he inner class from the method within
    void displayInner() {
        Inner01 inner = new Inner01();
        inner.print();
    }

    void increase() {
        num = num + 1;
    }

    void decrease() {
        num = num - 1;
    }

    int getNum() {
        return num;
    }
}