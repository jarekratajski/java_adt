package perf;
import expression.simple.ExampleExpr;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class EvalBenchmark {
    @Benchmark
    public void evalPatternMatching(Blackhole bh) {
        bh.consume(ExampleExpr.exampleExpr.eval());
    }

    @Benchmark
    public void evalPolimorphic(Blackhole bh) {
        bh.consume(ExampleExpr.exampleExpr.evalp());
    }
}