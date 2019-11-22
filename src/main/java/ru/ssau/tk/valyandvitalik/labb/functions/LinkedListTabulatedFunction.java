package ru.ssau.tk.valyandvitalik.labb.functions;

import ru.ssau.tk.valyandvitalik.labb.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) throws IllegalArgumentException {
        if (count < 2) {
            throw new IllegalArgumentException();
        }
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

    public int floorIndexOfX(double x) throws IllegalArgumentException {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < Left Bound");
        }
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
        if (!(left.x < x && x > right.x)) {
            throw new InterpolationException();
        }
        return super.interpolate(x, left.x, right.x, head.y, right.y);
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                if (node == null) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = node.next;
                    if (node == head) {
                        node = null;
                    }
                    return point;
                } else {
                    throw new NoSuchElementException();
                }
            }


        };
    }

    protected class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

    }
}