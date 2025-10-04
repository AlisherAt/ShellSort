package algorithms;


public class PerformanceTracker {
    private long comparisons;
    private long swaps; // Not directly used in Shell sort, but kept for consistency
    private long arrayAccesses;
    private long memoryAllocations; // Not tracked here (in-place), but available

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
    }

    public void incrementComparison() {
        comparisons++;
    }

    public void incrementSwap() {
        swaps++;
    }

    public void incrementArrayAccess() {
        arrayAccesses++;
    }

    public void incrementMemoryAllocation() {
        memoryAllocations++;
    }

    // Getters
    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getMemoryAllocations() { return memoryAllocations; }

    @Override
    public String toString() {
        return String.format(
                "Comparisons: %d, Swaps: %d, Array Accesses: %d, Memory Allocations: %d",
                comparisons, swaps, arrayAccesses, memoryAllocations
        );
    }
}