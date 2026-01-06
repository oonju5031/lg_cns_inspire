package lgcns.domain.loop;

public class LoopDemo {

    public int rangeSum(int start, int end) {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            sum += i;
        }

        return sum;
    }

    /**
     * 1부터 100 사이의 난수를 만든 뒤,
     * 1부터 해당 난수까지의 누적합을 계산하여 반환한다.
     * @return summation
     */
    public int randomRangeSum() {
        int sum = 0;
        int end = (int) (Math.random() * 100 + 1);

        System.out.println(end);

        for (int i = 1; i <= end; i++) {
            sum += i;
        }

        return sum;
    }

    public void timesTable() {

        labelExample :  // for문 labeling
        for (int row = 2; row <= 9; row++) {
            System.out.println();

            for (int col = 1; col <= 9; col++) {

                if (row == 5) {
                    break labelExample;  // label을 통해 바깥 loop를 break
                }

                System.out.printf("%d * %d = %d\t", row, col, row * col);
            }

            System.out.println();
        }
    }
}
