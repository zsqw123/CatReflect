class Sample {
    private void callPrivate(int i) {
        System.out.println("Sample#callPrivate [int] called");
    }

    private static void staticPrivate(int i0, int i1) {
        System.out.println("Sample#staticPrivate [int, int] called");
    }

    private Sample() {
        System.out.println("Sample() called");
    }

    private Sample(int i) {
        System.out.println("Sample(int) called");
    }

    private int a = 0;
    private static int b = 0;
}
