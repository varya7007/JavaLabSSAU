package ru.ssau.tk.valyandvitalik.labb.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private double[] xValues;
    private double[] yValues;
    private int count;
    private Node head;

    private void addNode(double x, double y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        if (head == null) {
            head = node;
            node.next = node;
            node.prev = node;
        } else {
            node.prev = head.prev;
            node.next = head;
            head.prev.next = node;
        }
        head.prev = node;
        count++;
        }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            xFrom = xFrom - xTo;
            xTo = xFrom + xTo;
            xFrom = xTo - xFrom;
        }
        double stepX = (xTo - xFrom) / (count - 1);
        if (xFrom != xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom + stepX * i, source.apply(xFrom + stepX * i));
            }
        } else {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return null;
    }

    private Node getNode(int index) {
        Node finding = head;
        for (int i = 0; i < index; i++) {
            finding = finding.next;
        }
        return finding;
    }

    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double y) {
        getNode(index).y = y;
    }

    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (x == this.getX(i))
                return i;
        }
        return -1;
    }

    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (y == this.getY(i))
                return i;
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        if (x > head.prev.x) {
            return count;
        }
        for (int i = 1; i < count; i++) {
            if (x < getX(i)) {
                return i - 1;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        Node right = head.prev.prev;
        return super.interpolate(x, head.x, right.x, head.y, right.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        Node left = head.next;
        return super.interpolate(x, left.x, head.prev.x, left.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        return super.interpolate(x, left.x, right.x, head.y, right.y);
    }

    protected class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

    }
}