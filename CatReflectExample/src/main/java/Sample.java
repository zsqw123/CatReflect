class Sample {
    private void callPrivate(int i) {
        ClassSampleKt.log("Sample#callPrivate [int] called");
    }

    private static void staticPrivate(int i0, int i1) {
        ClassSampleKt.log("Sample#staticPrivate [int, int] called");
    }

    private Sample() {
        ClassSampleKt.log("Sample() called");
    }

    private Sample(int i) {
        ClassSampleKt.log("Sample(int) called");
    }

    private int a = 0;
    private static int b = 0;
}
