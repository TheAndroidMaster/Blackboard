package james.blackboard.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import james.blackboard.R;
import james.blackboard.data.ContentData;
import james.blackboard.data.FolderContentData;
import james.blackboard.data.WebLinkData;

public class ContentsAdapter extends RecyclerView.Adapter<ContentsAdapter.ViewHolder> {

    private List<ContentData> contents;

    public ContentsAdapter(List<ContentData> contents) {
        this.contents = contents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentData content = contents.get(position);
        holder.title.setText(content.title);

        if (content.description.length() > 0) {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(content.description);
        } else holder.description.setVisibility(View.GONE);

        switch (getItemViewType(position)) {
            case 1:
                holder.image.setImageResource(R.drawable.ic_link);
                holder.itemView.setTag(((WebLinkData) content).url);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getTag() != null && view.getTag() instanceof String)
                            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((String) view.getTag())));
                    }
                });
                break;
            case 2:
                holder.image.setImageResource(R.drawable.ic_folder);
                holder.itemView.setTag(((FolderContentData) content).action);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            default:
                holder.image.setImageResource(R.drawable.ic_message);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (contents.get(position) instanceof WebLinkData)
            return 1;
        else if (contents.get(position) instanceof FolderContentData)
            return 2;

        return 0;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }
    }

}
