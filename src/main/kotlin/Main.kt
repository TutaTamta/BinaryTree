import java.util.*

class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinarySearchTree {
    private var root: TreeNode? = null

    fun insert(value: Int) {
        root = insertNode(root, value)
    }

    private fun insertNode(node: TreeNode?, value: Int): TreeNode {
        if (node == null) {
            return TreeNode(value)
        }

        if (value < node.value) {
            node.left = insertNode(node.left, value)
        } else if (value > node.value) {
            node.right = insertNode(node.right, value)
        }

        return node
    }

    fun search(value: Int): Boolean {
        return searchNode(root, value) != null
    }

    private fun searchNode(node: TreeNode?, value: Int): TreeNode? {
        if (node == null || node.value == value) {
            return node
        }

        if (value < node.value) {
            return searchNode(node.left, value)
        }

        return searchNode(node.right, value)
    }

    fun remove(value: Int) {
        root = removeNode(root, value)
    }

    private fun removeNode(node: TreeNode?, value: Int): TreeNode? {
        if (node == null) {
            return null
        }

        if (value < node.value) {
            node.left = removeNode(node.left, value)
        } else if (value > node.value) {
            node.right = removeNode(node.right, value)
        } else {
            if (node.left == null && node.right == null) {
                return null
            } else if (node.left == null) {
                return node.right
            } else if (node.right == null) {
                return node.left
            } else {
                val minValue = findMinValue(node.right!!)
                node.value = minValue
                node.right = removeNode(node.right, minValue)
            }
        }

        return node
    }

    private fun findMinValue(node: TreeNode): Int {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    fun breadthFirstTraversal() {
        if (root == null) {
            return
        }

        val queue = LinkedList<TreeNode>()
        queue.add(root!!)

        while (queue.isNotEmpty()) {
            val node = queue.remove()
            print("${node.value} ")

            if (node.left != null) {
                queue.add(node.left!!)
            }

            if (node.right != null) {
                queue.add(node.right!!)
            }
        }
    }

    fun depthFirstTraversal() {
        depthFirstTraversal(root)
    }

    private fun depthFirstTraversal(node: TreeNode?) {
        if (node == null) {
            return
        }

        print("${node.value} ")
        depthFirstTraversal(node.left)
        depthFirstTraversal(node.right)
    }
}
/*
fun main() {
    val bst = BinarySearchTree()
    bst.insert(5)
    bst.insert(3)
    bst.insert(7)
    bst.insert(2)
    bst.insert(4)
    bst.insert(6)
    bst.insert(8)

    print("Breadth-First Traversal:")
    bst.breadthFirstTraversal()
    println()

    print("Depth-First Traversal:")
    bst.depthFirstTraversal()
    println()

    println("Search 4: ${bst.search(4)}")

    bst.remove(3)
    print("Depth-First Traversal after removing 3:")
    bst.depthFirstTraversal()
    println()
}*/
