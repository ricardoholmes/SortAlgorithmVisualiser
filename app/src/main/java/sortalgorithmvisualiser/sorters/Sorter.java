package sortalgorithmvisualiser.sorters;

import sortalgorithmvisualiser.mvc.Controller;

public abstract class Sorter {
    protected Controller controller;
    protected double delay;
    protected int sizeOfArray;
    protected boolean sortAscending;

    protected boolean shouldStop;

    /**
     * @param c
     *      the controller
     * 
     * @param numberOfElements
     *      the number of numbers in the list that is to be sorted
     * 
     * @param delay
     *      the length of time to sleep after moving a value, in milliseconds
     * 
     * @param sortAscending
     *      whether the list is being sorted in ascending order (true) or in
     *      descending order (false)
     */
    public final void initialise(Controller c, int sizeOfArray, boolean sortAscending, double delay) {
        controller = c;
        this.sizeOfArray = sizeOfArray;
        this.delay = delay;
        this.sortAscending = sortAscending;
    }

    /**
     * @return the name of the sorting algorithm
     */
    public abstract String getName();

    /**
     * Sorts the list
     */
    public abstract void sort();

    /**
     * @param a
     *      a value from the list
     * 
     * @param b
     *      a value from the list, different to {@code a}
     * 
     * @param sortAscending
     *      whether the list is being sorted in ascending order (true) or in
     *      descending order (false)
     * 
     * @return whether or not {@code a} should be followed by {@code b} if sorted in
     *          the order given by {@code sortAscending}
     */
    protected final boolean inOrder(int a, int b, boolean sortAscending) {
        controller.setComparing(a, b);

        // sortAscending AND !(a > b)
        // OR
        // !sortAscending AND (a > b)
        // THEREFORE
        // sortAscending XOR a > b
        return sortAscending ^ (a > b);
    }

    protected final void sleep(double delay) {
        long millis = (long)delay;
        int nanos = (int)((delay % 1) * 1_000_000);

        try {
            Thread.sleep(0, nanos);
        } catch (InterruptedException e) { }

        for (int i = 0; i < millis; i++) {
            if (shouldStop) {
                return;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) { }
        }
    }

    public final void stop() {
        shouldStop = true;
    }
}
