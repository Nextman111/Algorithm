package lesson4;

public class RBTree {

    private static Node root = null;

    class Node {
        Node left, right;
        int value;
        boolean color; // Цвет узла red/black - true/false

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
            color = true;
        }
    }

    Node rotateLeft(Node node) {
        Node child = node.right;
        Node childLeft = child.left;

        child.left = node;
        node.right = childLeft;

        return child;
    }

    Node rotateRight(Node node) {
        Node child = node.left;
        Node childRight = child.right;

        child.right = node;
        node.left = childRight;

        return child;
    }

    boolean isRed(Node node) {
        if (node == null) {
            return false;
        }

        return (node.color == true);
    }

    void colorChange(Node node1, Node node2) {
        boolean tmp = node1.color;
        node1.color = node2.color;
        node2.color = tmp;
    }

    public Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.value) {
            node.left = insert(node.left, data);
        } else if (data > node.value) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
            colorChange(node, node.left);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
            colorChange(node, node.right);
        }

        if (isRed(node.left) && isRed(node.right)) {
            node.color = !node.color;
            node.left.color = false;
            node.right.color = false;
        }

        return node;
    }

    void treePass(Node node) {
        if (node != null) {
            treePass(node.left);
            char color = 'R';

            if (node.color == false)
                color = 'B';

            System.out.print(node.value + color + " ");
            treePass(node.right);
        }
    }
}