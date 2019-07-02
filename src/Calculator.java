import java.util.ArrayList;

public class Calculator {

    private Score[] scores = new Score[7];
    private char[] operators = new char[8];
    private ArrayList<Result> results = new ArrayList<>();

    // 输入幸运数字、当前分数（从上至下的顺序）和使用满分券的数量
    private int luckyNumber = 8;
    private double[] curScores = new double[]{858,959,864,31,3.07,966,884};
    private int numOfFullScore = 2;

    public Calculator() {
        super();
    }
}
