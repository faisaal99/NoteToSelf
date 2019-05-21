package com.faisal.notetoself;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {
    private String mFilename;
    private Context mContext;

    public JSONSerializer(String fn, Context context) { // Constructor that takes in a filename and the context
        mFilename = fn;
        mContext = context;
    }

    public void save(List<Note> notes) throws IOException, JSONException {
        // Make an array in JSON format
        JSONArray jArray = new JSONArray();

        // Load it with the notes
        for (Note n : notes) {
            jArray.put(n.convertToJSON());
        }

        Writer writer = null;

        try {
            OutputStream out = mContext.openFileOutput(mFilename, mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jArray.toString());
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Note> load() throws IOException, JSONException {

        ArrayList<Note> noteList = new ArrayList<Note>();
        BufferedReader reader = null;

        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

        } finally {// This will always run
            if (reader != null) {
                reader.close();
            }
        }

        return noteList; }
}
