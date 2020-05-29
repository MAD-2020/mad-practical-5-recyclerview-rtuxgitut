package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView TaskDescriptionTextView;
    CheckBox TaskStatusCheckBox;
    ConstraintLayout TaskItemConstraintLayout;

    public TaskViewHolder (View itemView) {
        super(itemView);
        this.TaskDescriptionTextView = (TextView) itemView.findViewById(R.id.taskDesc);
        this.TaskStatusCheckBox = (CheckBox) itemView.findViewById(R.id.taskStat);
        this.TaskItemConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.taskList);
    }
}
