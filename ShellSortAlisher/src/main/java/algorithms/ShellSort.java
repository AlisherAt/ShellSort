package algorithms;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;


public class ShellSort {

    public enum GapSequence {
        SHELL, KNUTH, SEDGEWICK
    }

    private final PerformanceTracker tracker;

    public ShellSort() {
        this.tracker = new PerformanceTracker();
    }

    public PerformanceTracker getTracker() {
        return tracker;
    }


    public void sort(int[] arr, GapSequence gapSequence) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        tracker.reset();

        if (arr.length <= 1) {
            return;
        }

        int[] gaps = generateGaps(arr.length, gapSequence);
        for (int gap : gaps) {
            // Perform gapped insertion sort
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                tracker.incrementArrayAccess(); // read arr[i]
                int j = i;
                // Shift elements until correct position found
                while (j >= gap) {
                    tracker.incrementComparison();
                    tracker.incrementArrayAccess(); // read arr[j - gap]
                    if (arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        tracker.incrementArrayAccess(); // write arr[j]
                        j -= gap;
                    } else {
                        break;
                    }
                }
                if (j != i) {
                    arr[j] = temp;
                    tracker.incrementArrayAccess(); // write final position
                }
            }
        }
    }


    private int[] generateGaps(int n, GapSequence type) {
        return switch (type) {
            case SHELL -> generateShellGaps(n);
            case KNUTH -> generateKnuthGaps(n);
            case SEDGEWICK -> generateSedgewickGaps(n);
        };
    }

    private int[] generateShellGaps(int n) {
        int gap = n / 2;
        int[] temp = new int[32]; // enough for n up to 2^31
        int count = 0;
        while (gap > 0) {
            temp[count++] = gap;
            gap /= 2;
        }
        return Arrays.copyOf(temp, count);
    }

    private int[] generateKnuthGaps(int n) {
        int k = 1;
        int gap;
        int[] temp = new int[32];
        int count = 0;
        while ((gap = (int) ((Math.pow(3, k) - 1) / 2)) < n) {
            temp[count++] = gap;
            k++;
        }
        // Reverse to descending order
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[count - 1 - i];
        }
        return result;
    }

    private int[] generateSedgewickGaps(int n) {

        int[] temp = new int[32];
        int count = 0;
        int k = 0;
        while (true) {
            int gap;
            if (k % 2 == 0) {
                int pow2 = (int) Math.pow(2, k / 2);
                int pow4 = pow2 * pow2;
                gap = 9 * pow4 - 9 * pow2 + 1;
            } else {
                int pow2 = (int) Math.pow(2, (k + 1) / 2);
                int pow4 = pow2 * pow2;
                gap = pow4 - 3 * pow2 + 1;
            }
            if (gap >= n) break;
            temp[count++] = gap;
            k++;
        }
        if (count == 0) {
            return new int[]{1}; // fallback
        }
        // Reverse to descending order
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[count - 1 - i];
        }
        return result;
    }

    // Convenience method using Shell's default sequence
    public void sort(int[] arr) {
        sort(arr, GapSequence.SHELL);
    }
}