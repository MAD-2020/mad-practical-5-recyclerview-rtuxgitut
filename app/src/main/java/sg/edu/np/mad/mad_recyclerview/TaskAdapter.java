package sg.edu.np.mad.mad_recyclerview;

import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{
    public TaskModel.TaskList taskList;
    //Constructor
    public TaskAdapter(TaskModel.TaskList tList) {
        this.taskList = tList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Declare variable
        TaskViewHolder taskViewHolder;

        //Send data into viewHolder
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tasklist_layout,
                parent,
                false
        );

        taskViewHolder = new TaskViewHolder(item);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        TaskModel.Task task = taskList.getTaskAtIndex(position);
        holder.TaskDescriptionTextView.setText(task.GetTaskDescription());
        holder.TaskStatusCheckBox.setChecked(task.GetTaskStatus());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Build Alert Dialog
                String descOfSelectedItem = taskList.getTaskAtIndex(position).GetTaskDescription();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete");
                builder.setMessage(Html.fromHtml(
                        "<div style='text-align: center'>Are you sure you want to delete<br /><b>" + descOfSelectedItem + "</b>?</div>"
                ));
                builder.setIcon(android.R.drawable.ic_menu_delete); //Search on this, its useful.
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        taskList.removeTask(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No",null);

                builder.create().show();
            }
        };
        holder.TaskDescriptionTextView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return taskList.countTasks();
    }
}
