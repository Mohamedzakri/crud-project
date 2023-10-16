package it.mohamed.crudproject.repo;

import it.mohamed.crudproject.enums.TaskPriority;
import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class TaskRepoTest {
    @Autowired
    private TaskRepository taskRepository;


    @Test
    void saveTaskRepository_whenTaskEntity_thenSaveTask(){
        UserEntity user = UserEntity.builder().userName("name").build();
        TaskEntity taskEntity = TaskEntity.builder().taskObj("taskObj1").priority(TaskPriority.HIGH).status(TaskStatus.TO_DO).user(user).build();

        taskRepository.save(taskEntity);

        TaskEntity savedTask = taskRepository.findById(taskEntity.getId()).get();

        Assertions.assertThat(savedTask).isNotNull().isEqualTo(taskEntity);
    }

    @Test
    void taskRepository_SaveAll_ReturnSavedTask() {

        //Arrange
        List<TaskEntity> taskEntities = new ArrayList<>();
        TaskEntity taskEntity = TaskEntity.builder()
                .taskObj("task1")
                .priority(TaskPriority.HIGH)
                .status(TaskStatus.DONE)
//                .user(new UserEntity(1, "name1", taskEntities))
                .taskDueDate(new Date(2022 / 2 / 12))
                .build();

        //Act
        TaskEntity savedTask = taskRepository.save(taskEntity);

        //Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isPositive();
    }
}
//
//    @Test
//    public void TaskRepo_GetTaskByStatus_ReturnListOfTasksByStatus() {
//        // Arrange
//        TaskStatus taskStatus = TaskStatus.TO_DO;
//        List<TaskEntity> taskEntities = new ArrayList<>();
//
//        // Create a sample list of TaskEntity objects
//        List<TaskEntity> expectedTaskEntities = Arrays.asList(
//                new TaskEntity(1, "Task1", TaskPriority.HIGH, taskStatus, new UserEntity(1, "name1", taskEntities), Date.valueOf("2022-02-19")),
//                new TaskEntity(2, "Task2", TaskPriority.MEDIUM, TaskStatus.DOING, new UserEntity(1, "name2", taskEntities), Date.valueOf("2022-02-19")),
//                new TaskEntity(3, "Task3", TaskPriority.LOW, TaskStatus.DONE, new UserEntity(1, "name3", taskEntities), Date.valueOf("2022-02-19"))
//        );
//
//        // Call the method to be tested
//        List<TaskEntity> actualTasks = taskRepository.getTaskByStatus(taskStatus);
//
//        // Verify that the expected and actual lists are the same
//        assertEquals(expectedTaskEntities, actualTasks);
//    }

//Class Start
// @DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//public class TaskRepoTest {
//    private final TaskRepository taskRepository;
//
//    @Autowired
//    public TaskRepoTest(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    @Mock
//    private EntityManager entityManager;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    public void TaskRepo_GetTaskByStatus_ReturnListOfTasksByStatus() {
//
//        //Arrange
//        List<TaskEntity> taskEntities = new ArrayList<>();
//        Date taskDueDate = Date.valueOf("2022-02-19");
//        TaskStatus taskStatus = TaskStatus.TO_DO;
//
//        // Create a sample list of TaskEntity objects
//        List<TaskEntity> expectedTaskEntities = Arrays.asList(
//                new TaskEntity(1, "Task1", TaskPriority.HIGH, taskStatus, new UserEntity(1, "name1", taskEntities), taskDueDate),
//                new TaskEntity(2, "Task2", TaskPriority.MEDIUM, TaskStatus.DOING, new UserEntity(1, "name2", taskEntities), taskDueDate),
//                new TaskEntity(3, "Task3", TaskPriority.LOW, TaskStatus.DONE, new UserEntity(1, "name3", taskEntities), taskDueDate)
//        );
//        // Mock the behavior of the EntityManager and Query
//        TypedQuery<TaskEntity> query = mock(TypedQuery.class);
//        when(entityManager.createQuery(anyString(), eq(TaskEntity.class))).thenReturn(query);
//        when(query.setParameter("status", taskStatus)).thenReturn(query);
//        when(query.getResultList()).thenReturn(expectedTaskEntities);
//        // Call the method to be tested
//        List<TaskEntity> actualTasks = taskRepository.getTaskByStatus(taskStatus);
//
//        // Verify that the expected and actual lists are the same
//        assertEquals(expectedTaskEntities, actualTasks);
//
//        // Verify that the query was executed with the correct parameters
//        verify(entityManager).createQuery(anyString(), eq(TaskEntity.class));
//        verify(query).setParameter("status", taskStatus);
//        verify(query).getResultList();
//    }
//} Class end

