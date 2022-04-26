package com.example.productlist.ui.search;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.productlist.MainActivity;
import com.example.productlist.R;
import com.example.productlist.db.Fish;
import com.example.productlist.db.Result;
import com.example.productlist.ui.home.MyListAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {
    private static final String DATE_FORMAT = "MM.dd HH:mm";

    private View searchFragmentView;
    private ListView searchResultsListView;
    private Button searchButton;
    private EditText searchText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchFragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        searchResultsListView = searchFragmentView.findViewById(R.id.searchListView);
        searchText = searchFragmentView.findViewById(R.id.search_fish);
        searchButton = searchFragmentView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this::search);
        return searchFragmentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void search(View view) {
        searchResultsListView.setAdapter(null);
        List<Fish> searchedFish = MainActivity.fishDao.searchBySpeciesName(searchText.getText().toString());
        System.out.println(searchedFish.size());
        if (!searchedFish.isEmpty()) {
            Fish[] fish = new Fish[searchedFish.size()];
            for (int i = 0; i < searchedFish.size(); i++) {
                fish[i] = searchedFish.get(i);
            }
            String[] mainTitles = Arrays.stream(fish).map(Fish::getSpeciesName).toArray(String[]::new);
            MyListAdapter adapter = new MyListAdapter(getActivity(), fish, mainTitles);
            searchResultsListView.setAdapter(adapter);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Result result = new Result(dateFormat.format(new Date()), searchText.getText().toString(),
                searchedFish.size());
        MainActivity.db.resultDao().insert(result);
    }
}