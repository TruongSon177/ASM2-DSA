import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mytree {
    Node root;

    public Mytree() {
        root = null;
    }

    // Node của cây
    class Node {
        Student student;
        Node left, right;

        public Node(Student student) {
            this.student = student;
            left = right = null;
        }
    }

    // Chèn sinh viên vào cây (thêm)
    public void insert(Student student) {
        root = insertAt(root, student);
    }

    private Node insertAt(Node root, Student student) {
        if (root == null) {
            return new Node(student);
        }
        if (student.getId() < root.student.getId()) {
            root.left = insertAt(root.left, student);
        } else if (student.getId() > root.student.getId()) {
            root.right = insertAt(root.right, student);
        }
        return root;
    }


    public boolean updateStudent(int id, String newName, double newScore) {
        Student student = search(id);
        if (student != null) {
            student.setName(newName);
            student.setScore(newScore);
            return true;
        } else {
            return false;
        }
    }


    public Student search(int id) {
        return searchRec(root, id);
    }

    private Student searchRec(Node root, int id) {
        if (root == null) {
            return null;
        }
        if (root.student.getId() == id) {
            return root.student;
        }
        if (id < root.student.getId()) {
            return searchRec(root.left, id);
        } else {
            return searchRec(root.right, id);
        }
    }


    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }
        if (id < root.student.getId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.student.getId()) {
            root.right = deleteRec(root.right, id);
        } else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.student = findMin(root.right);
            root.right = deleteRec(root.right, root.student.getId());
        }
        return root;
    }


    private Student findMin(Node root) {
        Student min = root.student;
        while (root.left != null) {
            min = root.left.student;
            root = root.left;
        }
        return min;
    }


    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.student);
            inorderTraversal(root.right);
        }
    }


    public void sortById() {
        List<Student> students = getStudents();
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
        students.forEach(System.out::println);
    }


    public void sortByRank() {
        List<Student> students = getStudents();
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getScore() > students.get(j + 1).getScore()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
        students.forEach(System.out::println);
    }


    private List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        inorderCollect(root, students);
        return students;
    }

    private void inorderCollect(Node root, List<Student> students) {
        if (root != null) {
            inorderCollect(root.left, students);
            students.add(root.student);
            inorderCollect(root.right, students);
        }
    }
}