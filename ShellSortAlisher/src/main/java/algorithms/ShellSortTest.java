package algorithms;

import java.util.Arrays;
import java.util.Random;

public class ShellSortTest {

    private static final long RANDOM_SEED = 12345; // for reproducible results

    public static void main(String[] args) {
        Random rand = new Random(RANDOM_SEED);
        int[] sizes = {5, 8, 10, 12, 15, 20, 25, 30, 50, 100}; // 10 test cases

        System.out.println("🧪 Shell Sort Lab Test: 10 Random Arrays");
        System.out.println("Random seed: " + RANDOM_SEED);
        System.out.println("=" .repeat(100));
        System.out.println();

        for (int testIndex = 0; testIndex < sizes.length; testIndex++) {
            int size = sizes[testIndex];
            System.out.println("🔹 TEST " + (testIndex + 1) + " | Array size: " + size);

            // Generate random array
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt(100); // values 0–99
            }

            System.out.println("   Original: " + Arrays.toString(original));
            System.out.println();

            // Test each gap sequence
            for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
                int[] arr = original.clone();
                ShellSort sorter = new ShellSort();
                PerformanceTracker tracker = sorter.getTracker();

                long start = System.nanoTime();
                sorter.sort(arr, seq);
                long end = System.nanoTime();
                long timeNs = end - start;

                // Validate
                if (!isSorted(arr)) {
                    System.err.println("   ❌ ERROR: Not sorted with " + seq);
                    continue;
                }

                System.out.printf("   %-10s → Sorted: %s%n", seq + ":", Arrays.toString(arr));
                System.out.printf("               Comparisons: %d | Array Accesses: %d | Time: %d ns%n",
                        tracker.getComparisons(),
                        tracker.getArrayAccesses(),
                        timeNs);
                System.out.println();
            }
            System.out.println("-".repeat(90));
            System.out.println();
        }

        System.out.println("✅ All 10 tests completed.");
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}