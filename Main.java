import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileService fileService = new FileService();
        List<Task> tasks = fileService.loadTasks();

        TaskService service = new TaskService(tasks);

        int idCounter = tasks.size() + 1;

        try (Scanner sc = new Scanner(System.in)) {

            while (true) {

                System.out.println("\n1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Mark Task Completed");
                System.out.println("4. Delete Task");
                System.out.println("5. Search Task");
                System.out.println("6. View Completed Tasks");
                System.out.println("7. Exit");

                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter task title: ");
                        String title = sc.nextLine();
                        service.addTask(new Task(idCounter++, title));
                        fileService.saveTasks(service.getAllTasks());
                        break;

                    case 2:
                        service.getAllTasks()
                                .forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter task id: ");
                        int id = sc.nextInt();
                        service.markTaskCompleted(id);
                        fileService.saveTasks(service.getAllTasks());
                        break;

                    case 4:
                        System.out.print("Enter task id to delete: ");
                        int deleteId = sc.nextInt();
                        service.deleteTask(deleteId);
                        fileService.saveTasks(service.getAllTasks());
                        break;

                    case 5:
                        System.out.print("Enter keyword: ");
                        String keyword = sc.nextLine();
                        service.searchTasks(keyword)
                                .forEach(System.out::println);
                        break;

                    case 6:
                        service.getCompletedTasks()
                                .forEach(System.out::println);
                        break;

                    case 7:
                        fileService.saveTasks(service.getAllTasks());
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }
}