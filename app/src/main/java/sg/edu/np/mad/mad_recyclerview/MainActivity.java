package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Declaring Variables
    private RecyclerView RecyclerView;
    private EditText TaskDescription;
    private Button TaskButton;
    private TaskAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskModel.TaskList taskList = new TaskModel.TaskList();
        taskList.addTask("Buy milk");
        taskList.addTask("Send postage");
        taskList.addTask("Buy Android development book");

        //Linking variables to their xml.
        TaskDescription =  (EditText) findViewById(R.id.taskDescription);
        TaskButton =  (Button) findViewById(R.id.taskButton);
        RecyclerView = (RecyclerView) findViewById(R.id.taskList);

        //Apply onclick listener to addTaskButton
        TaskButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Save as variable so as to not have to re-type expression.
                String newTaskDescription = TaskDescription.getText().toString();
                //Reset entered text
                TaskDescription.setText("");

                //Basic validation
                if (newTaskDescription.length() > 0) {
                    //taskList variable is passed into the adapter in another section
                    taskListAdapter.taskList.addTask(newTaskDescription);
                    taskListAdapter.notifyDataSetChanged();
                    showNewEntry(RecyclerView, taskListAdapter.taskList);

                }
            }
        });

        taskListAdapter = new TaskAdapter(taskList);
        LinearLayoutManager taskLayoutManager = new LinearLayoutManager (this);

        RecyclerView.setLayoutManager(taskLayoutManager);
        RecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.setAdapter(taskListAdapter);
    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rec RecyclerView for scrolling to
     * @param taskList TaskList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rec, TaskModel.TaskList taskList){
        //scroll to the last item of the recyclerview
        rec.scrollToPosition(taskList.countTasks() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rec.getWindowToken(), 0);
    }
}
