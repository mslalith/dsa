package src;

public class RunningProblem implements Problem<Integer, String> {

    public static void main(String[] args) {
        RunningProblem problem = new RunningProblem();
        System.out.println(problem.solution(0));
    }

    @Override
    public Integer[] getTestInputs() {
        return new Integer[0];
    }

    @Override
    public String solution(Integer testInput) {
        return null;
    }
}
