package src;

public interface Problem<I, O> {
    I[] getTestInputs();
    O solution(I testInput);
}
