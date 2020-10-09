package club.plus1.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    List<Person> list;

    public ListAdapter(Context context, List<Person> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person = list.get(position);
        holder.edit_name.setText(person.getName());
        holder.edit_phone.setText(person.getPhone());
        holder.edit_email.setText(person.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_photo;
        EditText edit_name, edit_phone, edit_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_photo = itemView.findViewById(R.id.image_photo);
            edit_name = itemView.findViewById(R.id.edit_name);
            edit_phone = itemView.findViewById(R.id.edit_phone);
            edit_email = itemView.findViewById(R.id.edit_email);
        }
    }
}
