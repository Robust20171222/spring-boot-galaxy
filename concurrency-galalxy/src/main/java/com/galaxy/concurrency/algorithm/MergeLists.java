package com.galaxy.concurrency.algorithm;

/**
 * Created by wangpeng
 * Date: 2018/10/21
 * Time: 08:20
 *
 * @see http://wiki.jikexueyuan.com/project/for-offer/question-senenteen.html
 */
public class MergeLists {

    Node head;

    /**
     * 插入节点到链表的尾部
     *
     * @param node
     */
    public void addToTheLast(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    /**
     * 打印链表的元素
     */
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /* Let us create two sorted linked
           lists to test the methods
           Created lists:
               llist1: 5->10->15,
               llist2: 2->3->20
        */
        MergeLists llist1 = new MergeLists();
        MergeLists llist2 = new MergeLists();

        // Node head1 = new Node(5);
        llist1.addToTheLast(new Node(5));
        llist1.addToTheLast(new Node(10));
        llist1.addToTheLast(new Node(15));

        // Node head2 = new Node(2);
        llist2.addToTheLast(new Node(2));
        llist2.addToTheLast(new Node(3));
        llist2.addToTheLast(new Node(20));

        llist1.head = new Gfg().sortedMerge2(llist1.head,
                llist2.head);
        llist1.printList();
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Gfg {
    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     *
     * @param headA 第一个有序链表
     * @param headB 第二个有序链表
     * @return 合并后的有序链表头
     */
    Node sortedMerge(Node headA, Node headB) {

        // 创建一个临时结点，用于添加元素时方便
        Node dummyNode = new Node(0);
        // 用于指向合并后的新链的尾结点
        Node tail = dummyNode;

        while (true) {
            // 如果第一个链表的元素未处理完将其，接到合并链表的最后一个结点之后
            if (headA == null) {
                tail.next = headB;
                break;
            }

            // 如果第二个链表的元素未处理完将其，接到合并链表的最后一个结点之后
            if (headB == null) {
                tail.next = headA;
                break;
            }

            // 当两个链表都不为空就进行合并操作，比较大小，合并较小的元素
            if (headA.data <= headB.data) {
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }

            // 将指针移动到合并后的链表的末尾
            tail = tail.next;
        }

        return dummyNode.next;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     * 【使用的是递归的解法，不推荐，递归调用的时候会有方法入栈，需要更多的内存】
     *
     * @param head1 第一个有序链表
     * @param head2 第二个有序链表
     * @return 合并后的有序链表头
     */
    Node sortedMerge2(Node head1, Node head2) {
        // 如果第一个链表为空，返回第二个链表头结点
        if (head1 == null) {
            return head2;
        }
        // 如果第二个链表为空，返回第一个链表头结点
        if (head2 == null) {
            return head1;
        }
        // 记录两个链表中头部较小的结点
        Node tmp = head1;
        if (tmp.data < head2.data) {
            // 如果第一个链表的头结点小，就递归处理第一个链表的下一个结点和第二个链表的头结点
            tmp.next = sortedMerge2(head1.next, head2);
        } else {
            // 如果第二个链表的头结点小，就递归处理第一个链表的头结点和第二个链表的头结点的下一个结点
            tmp = head2;
            tmp.next = sortedMerge2(head1, head2.next);
        }
        // 返回处理结果
        return tmp;
    }
}
