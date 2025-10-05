# Individual Analysis Report: HeapSort & ShellSort
#Course: Algorithmic Analysis
#Author: Alisher Akhmet SE-2425
#Date: 06.10.2025

1. Algorithm Overview
HeapSort builds a binary heap and then sorts by moving the largest element to the end each time.
 It always works in O(n log n) time and uses almost no extra memory.
 The algorithm is reliable and works the same for any kind of input.
ShellSort improves insertion sort by comparing elements that are several positions apart.
 It uses different gap sequences (Shell, Knuth, Sedgewick) and reduces the gap until it becomes 1.
 The result depends on which gap sequence is used — some are faster than others.

2. Code Review
The HeapSort code is clean and modular.
 It includes a PerformanceTracker to count comparisons, swaps, and array accesses.
 Edge cases like empty arrays and duplicates are tested with JUnit.
 A BenchmarkRunner measures speed and saves results to a CSV file.
The ShellSort code supports several gap sequences.
 It uses its own tracker for comparisons and accesses, and it prints results for 10 random arrays.
 The output clearly shows how different gap sequences affect speed.

3. Results Summary
HeapSort performs steadily and matches the expected O(n log n) growth.
 It works best for large arrays and gives consistent results.
ShellSort sorts all arrays correctly but runs slower for large data, especially with simple gaps.
 Knuth and Sedgewick gaps give much better performance than the basic Shell sequence.

4. Conclusion
Both algorithms are implemented well.
 HeapSort is faster and more reliable for big datasets, while ShellSort is easier to experiment with and shows how gap sequences change performance.
 Adding graphs, comments, and CSV output for ShellSort would make the project even better.


