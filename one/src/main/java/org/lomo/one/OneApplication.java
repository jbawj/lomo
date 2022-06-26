package org.lomo.one;

import java.util.*;
import java.util.stream.Stream;

public class OneApplication {
    MultiplyOperation multiply = new MultiplyOperation();
    DivideOperation divide = new DivideOperation();
    SubstituteWithLeftOperation substituteWithLeft = new SubstituteWithLeftOperation();
    SubstituteWithRightOperation substituteWithRight = new SubstituteWithRightOperation();
    TakeLeftOperation takeLeft = new TakeLeftOperation();
    TakeRightOperation takeRight = new TakeRightOperation();

    public LinkedList<Step> takeStep(long input, boolean prohibitDivide) {
        LinkedList<Step> divResults = prohibitDivide ? new LinkedList<>() : takeStep(divide, input);

        LinkedList<Step> takeLestResults = takeStep(takeLeft, input);

        LinkedList<Step> takeRightResults = takeStep(takeRight, input);

        LinkedList<Step> subLeftResults = takeStep(substituteWithLeft, input);

        LinkedList<Step> subRightResults = takeStep(substituteWithRight, input);

        return Stream.of(divResults, takeLestResults, takeRightResults, subLeftResults, subRightResults)
                .filter(Objects::nonNull)
                .filter(l -> !l.isEmpty())
                .filter(l -> l.getLast().result == 1)
                .min(Comparator.comparingInt(LinkedList::size))
                .orElse(new LinkedList<>());
    }


    private LinkedList<Step> takeStep(Operation op, long input) {
        var steps = new LinkedList<Step>();
        if (!op.isApplicable(input)) {
            return steps;
        }

        var step = new Step(op, input);
        steps.add(step);

        if (step.result == 1) {
            return steps;
        }

        var results =  takeStep(step.result, false);

        steps.addAll(results);

        return steps;
    }

    public List<Step> run(long input) {
        var steps = new LinkedList<Step>();

        Optional<Operation> applicable = Stream.of(divide, takeLeft, takeRight, substituteWithLeft, substituteWithRight)
                .filter(op -> op.isApplicable(input)).findFirst();

        if (applicable.isEmpty()) {
            steps.addAll(multiply(input));
        }

        LinkedList<Step> result = takeStep(steps.size() > 0 ? steps.getLast().result : input, steps.size() > 0);
        while (result.isEmpty() || result.getLast().result != 1) {
            steps.addAll(multiply(steps.isEmpty() ? input : steps.getLast().result));
            result = takeStep(steps.size() > 0 ? steps.getLast().result : input, steps.size() > 0);
        }
        steps.addAll(result);
        return steps;

    }

    private LinkedList<Step> multiply(long input) {
        LinkedList<Step> steps = new LinkedList<>();
        Step step = new Step(multiply, input);
        steps.add(step);

        while (Stream.of(this.takeLeft, takeRight, substituteWithLeft, substituteWithRight).noneMatch(op -> op.isApplicable(steps.getLast().result))) {
            Step s = new Step(multiply, steps.getLast().result);
            steps.add(s);
        }

        return steps;
    }

    public static class Step {
        final Operation operation;
        final long input;
        final long result;
        public Step(Operation operation, long input) {
            this.operation = operation;
            this.input = input;
            this.result = operation.calc(input);
        }

        @Override
        public String toString() {
            return "Step{" +
                    "operation=" + operation +
                    ", input=" + input +
                    ", result=" + result +
                    '}';
        }
    }

    public static void main(String[] args) {
//        long input = 333333333;
        long input = 333333333333333L;
//        long input = 3;
        System.out.println("Input = " + input);

        var run = new OneApplication().run(input);

        System.out.println(run.size());
        System.out.println(run);
    }
}
