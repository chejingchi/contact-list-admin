/**
 * @author chejingchi
 *         创建时间:2017/4/7 上午11:19
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
public class JavaTest {
    static int[] arr = new int[]{6, 3, 4, 5, 8, 9, 7, 1, 2};

    public static void main(String[] args) {
        test();

    }

    private static void test() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=temp;
                }
            }
            for (int a : arr) {
                System.out.print(a + ",");
            }
            System.out.println("");
        }
    }
}
