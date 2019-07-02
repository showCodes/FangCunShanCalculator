import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

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
            if (curScore <= 1000 && curScore >= 523) { // 加法
                this.scores[i].setMaxScore(1000.0);
                this.scores[i].setMinScore(523.0);
                this.operators[i] = '+';
            } else if (curScore <= 4 && curScore >= 2.6) { // 乘法
                this.scores[i].setMaxScore(4.0);
                this.scores[i].setMinScore(2.6);
                this.operators[i] = '*';
            } else if (curScore <= 39 && curScore >= 20) { // 减法
                this.scores[i].setMaxScore(20);
                this.scores[i].setMinScore(39);
                this.operators[i] = '-';
            } else if (curScore <= 1.81 && curScore >= 1.4) {
                this.scores[i].setMaxScore(1.4);
                this.scores[i].setMinScore(1.81);
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
