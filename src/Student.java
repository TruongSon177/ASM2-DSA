public class Student {
    private int id;
    private String name;
    private double score;
    private String rank;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.rank = calculateRank();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
        this.rank = calculateRank();
    }

    public String calculateRank() {
        if (score >= 0 && score < 5.0) {
            return "Fail";
        } else if (score >= 5.0 && score < 6.5) {
            return "Medium";
        } else if (score >= 6.5 && score < 7.5) {
            return "Good";
        } else if (score >= 7.5 && score < 9.0) {
            return "Very Good";
        } else if (score >= 9.0 && score <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Score: " + score + ", Rank: " + rank;
    }
}