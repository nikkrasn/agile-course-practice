package ru.unn.agile.BinaryTree.Model;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void canCreateTree() {
        BinaryTree tree = new BinaryTree();
        assertNotNull(tree);
    }

    @Test
    public void nullGetRootInEmptyTree() {
        BinaryTree tree = new BinaryTree();
        assertNull(tree.getRoot());
    }

    @Test
    public void canGetRoot() {
        BinaryTree tree = new BinaryTree();
        Node node = new Node(1, "test");
        tree.insert(node);
        assertEquals(node, tree.getRoot());
    }

    @Test
    public void canGetRootInBigTree() {
        BinaryTree tree = createTestTree(false);
        assertEquals(3, tree.getRoot().getKey());
    }

    @Test
    public void canInsertNodeInRightSubTree() {
        BinaryTree tree = new BinaryTree();
        Node node1 = new Node(1, "test1");
        tree.insert(node1);
        Node node2 = new Node(2, "test2");
        tree.insert(node2);
        assertEquals(node2, tree.getRoot().getRight());
    }

    @Test
    public void canInsertNodeInLeftSubTree() {
        BinaryTree tree = new BinaryTree();
        Node node1 = new Node(2, "test2");
        tree.insert(node1);
        Node node2 = new Node(1, "test1");
        tree.insert(node2);
        assertEquals(node2, tree.getRoot().getLeft());
    }

    @Test
    public void canInsertNodeInBigTree() {
        BinaryTree tree = createTestTree(false);
        Node node = new Node(7, "test7");
        tree.insert(node);
        assertEquals(node, tree.getRoot().getRight().getRight());
    }

    @Test
    public void canDeleteNodeList() {
        BinaryTree tree = createTestTree(false);
        tree.delete(4);
        assertNull(tree.getRoot().getRight().getLeft().getLeft());
    }

    @Test
    public void canDeleteNodeWithOneChild() {
        BinaryTree tree = createTestTree(false);
        tree.delete(5);
        assertEquals(4, tree.getRoot().getRight().getLeft().getKey());
    }

    @Test
    public void canDeleteNodeWithTwoChilds() {
        BinaryTree tree = createTestTree(false);
        tree.delete(1);
        assertEquals(2, tree.getRoot().getLeft().getKey());
    }

    @Test
    public void canFindByValue() {
        BinaryTree tree = new BinaryTree();
        Node node1 = new Node(1, "test1");
        tree.insert(node1);
        ArrayList<Node> nodes = tree.findByValue("test1");
        assertEquals(node1, nodes.get(0));
    }

    @Test
    public void canFindByValueInBigTree() {
        BinaryTree tree = createTestTree(false);
        ArrayList<Node> nodes = tree.findByValue("test5");
        assertEquals(5, nodes.get(0).getKey());
    }

    @Test public void canFindByValueMoreThanOneNodes() {
        BinaryTree tree = createTestTree(true);
        ArrayList<Node> nodes = tree.findByValue("test");
        assertEquals(7, nodes.size());
    }

    private BinaryTree createTestTree(final boolean isSameValue) {
        BinaryTree tree = new BinaryTree();
        int[] treeKeys = {3, 1, 2, 0, 6, 5, 4 };
        for (int i = 0; i < treeKeys.length; i++) {
            String value = isSameValue ? "test" : "test" + String.valueOf(treeKeys[i]);
            Node temp = new Node(treeKeys[i], value);
            tree.insert(temp);
        }
        return tree;
    }
}
