import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Calculator {

    // 更新分数
    public static final double ADD_MAX_SCORE = 1500.0;
    public static final double ADD_MIN_SCORE = 871.0;

    public static final double MIN_MAX_SCORE = 10;
    public static final double MIN_MIN_SCORE = 24;

    public static final double MUL_MAX_SCORE = 0.0;
    public static final double MUL_MIN_SCORE = 0.0;

    public static final double DIV_MAX_SCORE = 1.4;
    public static final double DIV_MIN_SCORE = 1.49;

    private Score[] scores = new Score[7];
    private char[] operators = new char[8];
    private ArrayList<Result> results = new ArrayList<>();

    // 输入幸运数字、当前分数（从上至下的顺序）和使用满分券的数量
    private int luckyNumber = 7;
    private double[] curScores = new double[]{1050,1.48,22,891,1.47,1013,1019};
    private int numOfFullScore = 1;

    public static void main(String[] args) throws ScriptException {

        Calculator calculator = new Calculator();
        calculator.printResults();
    }

    public Calculator() {
        super();
    }

    public void printResults() throws ScriptException {
        this.generateCalcData();
        this.computing(0, "");
        this.firstFilter();
        this.results.sort(Comparator.comparing(Result::getRes).reversed());
        for (Result result : this.results) {
            String[] strings = result.getEquation().split("[+\\-*/]");
            int i = 0, countFullScore = 0;
            for (String s : strings) {
                double value = Double.valueOf(s);
                if (Math.abs(value - this.scores[i++].getMaxScore()) < 0.01) {
                    countFullScore++;
                }
            }
            if (countFullScore == this.numOfFullScore) {
                System.out.println(result.getEquation() + " 结果是：" + result.getRes());
            }
        }
    }

    private void generateCalcData() {
        for (int i = 6, j = 0; i >= 0; i--, j++) {
            double curScore = curScores[j];
            this.scores[i] = new Score();
            this.scores[i].setCurScore(curScore);
            if (curScore <= ADD_MAX_SCORE && curScore >= ADD_MIN_SCORE) { // 加法
                this.scores[i].setMaxScore(ADD_MAX_SCORE);
                this.scores[i].setMinScore(ADD_MIN_SCORE);
                this.operators[i] = '+';
            } else if (curScore <= MUL_MAX_SCORE && curScore >= MUL_MIN_SCORE) { // 乘法
                this.scores[i].setMaxScore(MUL_MAX_SCORE);
                this.scores[i].setMinScore(MUL_MIN_SCORE);
                this.operators[i] = '*';
            } else if (curScore <= MIN_MIN_SCORE && curScore >= MIN_MAX_SCORE) { // 减法
                this.scores[i].setMaxScore(MIN_MAX_SCORE);
                this.scores[i].setMinScore(MIN_MIN_SCORE);
                this.operators[i] = '-';
            } else if (curScore <= DIV_MIN_SCORE && curScore >= DIV_MAX_SCORE) {
                this.scores[i].setMaxScore(DIV_MAX_SCORE);
                this.scores[i].setMinScore(DIV_MIN_SCORE);
                this.operators[i] = '/';
            }
        }
    }

    private void computing(int index, String res) {
        if (index < 7) {
            this.computing(index + 1, res + "" + this.scores[index].getMaxScore() + this.operators[index + 1]);
            this.computing(index + 1, res + "" + this.scores[index].getCurScore() + this.operators[index + 1]);
            this.computing(index + 1, res + "" + this.scores[index].getMinScore() + this.operators[index + 1]);
        }
        if (index == 7) {
            Result result = new Result(res.substring(0, res.length() - 1));
            this.results.add(result);
        }
    }

    private void firstFilter() throws ScriptException {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        Iterator<Result> iterator = this.results.iterator();
        while (iterator.hasNext()) {
            Result result = iterator.next();
            Double res = (Double) jse.eval(result.getEquation());
            if (this.match(res.intValue())) {
                result.setRes(res.intValue());
            } else {
                iterator.remove();
            }
        }
    }

    private boolean match(int num) {
        int[] table = new int[10];
        while (num > 0) {
            table[num % 10]++;
            num /= 10;
        }
        return table[this.luckyNumber] >= 2;
    }
}
