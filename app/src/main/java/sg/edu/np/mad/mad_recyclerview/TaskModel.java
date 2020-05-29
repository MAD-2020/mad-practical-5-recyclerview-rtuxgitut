package sg.edu.np.mad.mad_recyclerview;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    //Using a model in anticipation for week 6 database.
    public static class Task {
        private String taskDesc;
        private Boolean taskStat = false; //Default state

        //Constructor
        public Task(String description) {
            this.taskDesc = description;
        }

        //For checking/ un-checking box
        public void TaskStatusUpdate(Boolean completed) {
            this.taskStat = completed;
        }

        public String GetTaskDescription() {
            return (this.taskDesc);
        }

        public Boolean GetTaskStatus() {
            return (this.taskStat);
        }
    }

    //Created a class so I don't have to keep recalling TaskListModel.Task
    public static class TaskList {

        private List<Task> taskList = new ArrayList<> ();

        //Initiate
        public TaskList() {}

        //Basic functions
        public void addTask(String description) { this.taskList.add(new Task(description)); }

        public void removeTask(int index) { this.taskList.remove(index); }

        public Task getTaskAtIndex(Integer index) { return this.taskList.get(index); }

        public Integer countTasks() { return this.taskList.size(); }
    }
}
