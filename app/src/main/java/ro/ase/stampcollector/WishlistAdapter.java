package ro.ase.stampcollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class WishlistAdapter extends ArrayAdapter {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;


    private List<NoteStampInfo> mNotes;

    public WishlistAdapter(@NonNull Context context, int resource, List<NoteStampInfo> stampsNotes) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.mNotes = stampsNotes;
    }

    @Override
    public int getCount() {
        return mNotes.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NoteStampInfo current_stamp = mNotes.get(position);

        viewHolder.noteTextTitle.setText(current_stamp.getTitle());
        viewHolder.noteTextDescription .setText(current_stamp.getText());
        return convertView;
    }


    private class ViewHolder {
        final TextView noteTextTitle;
        final TextView noteTextDescription;
        ;

        ViewHolder(View v) {
            this.noteTextTitle = (TextView) v.findViewById(R.id.text_note_title);
            this.noteTextDescription = (TextView) v.findViewById(R.id.text_note_description);
        }
    }
}