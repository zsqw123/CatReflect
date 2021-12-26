import com.zsqw123.catreflect.CatClass;

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

    static class A {
        A(int a) {
            ClassSampleKt.log("Sample.A#A(int) called");
        }
    }

    public static void main(String[] args) {
        CatClass.from("Sample").method("staticPrivate").invoke(1, 2);
        CatClass.from(Sample.class).constructor(1).method("staticPrivate").invoke(1, 2);
        CatClass.from("Sample").constructor(1).method("callPrivate").invoke(3);
        CatClass.from("Sample").prop("b").set(1);
        ClassSampleKt.log(CatClass.from("Sample").prop("b").get());
    }
}
