import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotContainTheFollowingQuery() {
        SimpleTask simpleTask = new SimpleTask(30, "Позвонить родственникам");

        String[] subtasks = {"Кефир", "Бургер", "Напиток"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                1864,
                "Выход Бета версии приложения",
                "Приложение Сбербанк",
                "В субботу после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        boolean[] expected = {false, false, false};
        boolean[] actual = new boolean[3];

        Task[] arrayTask = todos.findAll();

        for (int i = 0; i < arrayTask.length; i++) {
            actual[i] = arrayTask[i].matches("BIONICLE");
        }
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldContainTheFollowingRequest() {
        SimpleTask simpleTask = new SimpleTask(30, "Позвонить родственникам");

        String[] subtasks = {"Кефир", "Бургер", "Напиток"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                1864,
                "Выход Бета версии приложения",
                "Приложение Сбербанк",
                "В субботу после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        boolean[] expected = {false, false, true};
        boolean[] actual = new boolean[3];

        Task[] arrayTask = todos.findAll();

        for (int i = 0; i < arrayTask.length; i++) {
            actual[i] = arrayTask[i].matches("Сбербанк");
        }
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetter() {
        SimpleTask simpleTask = new SimpleTask(9, "Позвонить родителям");
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(97, subtasks);

        Meeting meeting = new Meeting(
                9797,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        int[] expected = { 9, 97, 9797 };
        int[] actual = {simpleTask.getId(), epic.getId(), meeting.getId()};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldContainAnEmptyRequest() {
        SimpleTask simpleTask = new SimpleTask(5, "Звонок другу");

        String[] subtasks = {"Макароны","Овсянка","Картошка"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выход Бета приложения",
                "Приложение Сбербанк",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("BIONICLE");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldContainsTheSearchQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Выходим к другу");

        String[] subtasks = {"Выходим за макаронами","Выходим за овсянкой","Выходим за картошкой"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выход Бета приложения",
                "Приложение Сбербанк",
                "Вторник"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("Выход");
        Assertions.assertArrayEquals(expected, actual);
    }
}