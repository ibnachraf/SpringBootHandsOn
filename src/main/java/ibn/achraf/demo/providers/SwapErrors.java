package ibn.achraf.demo.providers;

public sealed class SwapErrors extends Exception {

    public SwapErrors(String message) {
        super(message);
    }

    public static final class AmountTooLowError extends SwapErrors {
        public AmountTooLowError() {
            super("Amount too low");
        }
    }

    public static final class AmountTooHigh extends SwapErrors {
        public AmountTooHigh() {
            super("Amount too high");
        }
    }
}
