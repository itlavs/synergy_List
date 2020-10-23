package club.plus1.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Person person = list.get(position);
        holder.text_name.setText(person.getName());
        holder.text_phone.setText(person.getPhone());
        holder.text_email.setText(person.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(EditActivity.POSITION, position);
                intent.putExtra(EditActivity.NAME, person.getName());
                intent.putExtra(EditActivity.PHONE, person.getPhone());
                intent.putExtra(EditActivity.EMAIL, person.getEmail());
                ((MainActivity)context).startActivityForResult(intent, EditActivity.EDIT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_photo;
        TextView text_name, text_phone, text_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_photo = itemView.findViewById(R.id.image_photo);
            text_name = itemView.findViewById(R.id.text_name);
            text_phone = itemView.findViewById(R.id.text_phone);
            text_email = itemView.findViewById(R.id.text_email);
        }
    }
}
