package com.myth.demo03;

/**
 * 有序性
 */
public class TestB {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = a + b;
    }

    // TestB.java的字节码文件
    // 指令重排就是Code下的顺序可能因为编译器为了优化代码所产生的顺序重新排序
    /**
     * Compiled from "TestB.java"
     * public class com.myth.demo03.TestB {
     *   public com.myth.demo03.TestB();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: bipush        10
     *        2: istore_1
     *        3: bipush        20
     *        5: istore_2
     *        6: iload_1
     *        7: iload_2
     *        8: iadd
     *        9: istore_3
     *       10: return
     * }
     */
}
