package com.example.juancarlos.laboratorioandroid;
import com.parse.*;
import android.content.*;
import android.view.*;
import android.widget.*;

/**
 * Created by juancarlos on 13/10/14.
 */
public class FoodAdapter extends ParseQueryAdapter<ParseObject> {

    public FoodAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Food");
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.food_item, null);
        }

        super.getItemView(object, v, parent);

        ParseImageView foodImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("Image");

        if(imageFile!=null)
        {
            foodImage.setParseFile(imageFile);
            foodImage.loadInBackground();
        }

        TextView nameTextView = (TextView) v.findViewById(R.id.name);
        nameTextView.setText(object.getString("Name"));

        TextView typeView = (TextView) v.findViewById(R.id.type);
        typeView.setText(object.getString("Type"));
        return v;
    }
}
